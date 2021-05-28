package cn.dlut.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dlut.dao.ControllerDao;
import cn.dlut.dao.FlowDao;
import cn.dlut.dao.LinkDao;
import cn.dlut.dao.PortDao;
import cn.dlut.dao.PortStatsDao;
import cn.dlut.dao.RyuGroupDescDao;
import cn.dlut.dao.RyuGroupFeaturesDao;
import cn.dlut.dao.RyuGroupStatsDao;
import cn.dlut.dao.RyuMeterConfigDao;
import cn.dlut.dao.RyuMeterFeaturesDao;
import cn.dlut.dao.RyuMeterStatsDao;
import cn.dlut.dao.SwitchDao;
import cn.dlut.entity.Controller;
import cn.dlut.entity.Flow;
import cn.dlut.entity.Link;
import cn.dlut.entity.Port;
import cn.dlut.entity.PortStats;
import cn.dlut.entity.RyuGroupDesc;
import cn.dlut.entity.RyuGroupFeatures;
import cn.dlut.entity.RyuGroupStats;
import cn.dlut.entity.RyuMeterConfig;
import cn.dlut.entity.RyuMeterFeatures;
import cn.dlut.entity.RyuMeterStats;
import cn.dlut.entity.Switch;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.RyuInfoToDBService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.HttpRequest;
import cn.dlut.util.JsonUtil;

@Service("RyuService")
public class RyuInfoToDBServiceImpl extends AbstractBaseService implements RyuInfoToDBService {
	//get controller URL, possible existing multiple URLs, URLs are separated by “;"
	private static final String org_STRHTTPPRE = AppProperties.Ryu_preurl(); 
	public static ArrayList<String> extra_STRHTTPPRE = new ArrayList<String>(); //could be modified by add controller service
	private static String STRHTTPSWSIDS;
	private static String STRHTTPSWSPORTS;
	private static String STRHTTPSWSDESC;
	private static String STRHTTPSWSFLOW;
	private static String STRHTTPPORTDESC;
	private static String STRHTTPGROUP;
	private static String STRHTTPGROUPDESC;
	private static String STRHTTPGROUPFEATURES;
	private static String STRHTTPMETER;
	private static String STRHTTPMETERCONFIG;
	private static String STRHTTPMETERFEATURES;
	
	private static String STRHTTPLINK;
	private static String STRHTTPFIREWALLENABLE;

	private static final int SWSMAXNUM = 40;
	private final int HTTPERROR = 1;
	private final int INSERTERROR = 2;
	private static int nSwsNumber;
	private static String[] swsDpid = new String[SWSMAXNUM];

	@Autowired
	private RyuMeterStatsDao daoRyuMeterStats;

	@Autowired
	private RyuMeterConfigDao daoRyuMeterConfig;

	@Autowired
	private RyuMeterFeaturesDao daoRyuMeterFeatures;

	@Autowired
	private SwitchDao daoSwitch;

	
	@Autowired
	private LinkDao daoLink;
	
	@Autowired
	private ControllerDao daoController;

	@Autowired
	private FlowDao daoFlow;

	@Autowired
	private RyuGroupStatsDao daoGroup;

	@Autowired
	private RyuGroupDescDao daoGroupDesc;

	@Autowired
	private RyuGroupFeaturesDao daoGroupFeatures;

	@Autowired
	private PortStatsDao daoPortStats;

	@Autowired
	private PortDao daoPort;

	public void SetArguments(String STR) {
		STRHTTPSWSIDS = STR + AppProperties.RyuSwsIds();
		STRHTTPSWSPORTS = STR + AppProperties.RyuSwsPorts();
		STRHTTPSWSDESC = STR + AppProperties.RyuSwsDesc();
		STRHTTPSWSFLOW = STR + AppProperties.RyuSwsFlow();
		STRHTTPPORTDESC = STR + AppProperties.RyuPortDesc();
		STRHTTPGROUP = STR + AppProperties.RyuGroup();
		STRHTTPGROUPDESC = STR + AppProperties.RyuGroupDesc();
		STRHTTPGROUPFEATURES = STR + AppProperties.RyuGroupFeatures();
		STRHTTPMETER = STR + AppProperties.RyuMeter();
		STRHTTPMETERCONFIG = STR + AppProperties.RyuMeterConfig();
		STRHTTPMETERFEATURES = STR + AppProperties.RyuMeterFeatures();
		STRHTTPLINK=STR+AppProperties.RyuLink();
		STRHTTPFIREWALLENABLE=STR+AppProperties.RyuFireWallEnable();
	}

	public int Init(int controller_id) { // init Switch, get dpid and save into the array of swsDpid
		nSwsNumber = 0;
		String strSwsDpid = null;
		try {
			strSwsDpid = HttpRequest.readContentFromGet(STRHTTPSWSIDS);
			strSwsDpid = ", "
					+ strSwsDpid.substring(1, strSwsDpid.length() - 1) + ", ";
			int indexfront = strSwsDpid.indexOf(", ", 0);
			int indexback = strSwsDpid.indexOf(", ", indexfront + 1); // could normally resolve
			while (indexback != -1) {
				swsDpid[nSwsNumber++] = strSwsDpid.substring(indexfront + 2,
						indexback);
				indexfront = indexback;
				indexback = strSwsDpid.indexOf(", ", indexfront + 1);
			}
		} catch (IOException e) {
			System.out.println("Getting Ryu's switches id error!");
			return HTTPERROR;
		}
		FireWallEnable(controller_id);
		return 0;
	}
	
	
	/*
	 * @see cn.dlut.service.RyuService#FireWallEnable()
	 * FireWallEnable()call Ryu restful API,enable all Ryu related switch's firewall capacity,
	 * download special rule; when rest_firewall service initialized，may default set off the switching capacity among elements
	 * after downloading special rule, would restart the switching capacity.
	 * 
	 * FireWallEnable return a string, when all switches configures success, the string is firewall enable success;
	 * otherwise, would alert which switch failed to enalbe firewall.
	 * return value would reflect to the portal during initializing stage 
	 *
	 */
	public String FireWallEnable(int controller_id){
		String HTTPresult = "";
		String formatSwsDpid = "";
		Map<String, String> MapResult = new HashMap<String, String>();
		for(int i = 0;i < nSwsNumber;i ++){
			formatSwsDpid = String.format("%16s", swsDpid[i]);//add zero number for switch id
			formatSwsDpid = formatSwsDpid.replaceAll(" ", "0");
			try{
				HTTPresult = HttpRequest.readContentFromPut(STRHTTPFIREWALLENABLE + formatSwsDpid);//put
			} catch(IOException e) {
				System.out.println(formatSwsDpid+ "firewall enable error cause" + e.toString());
				MapResult.put(formatSwsDpid, "Error in HTTP request");
				continue;
			}
			Object[] ob = JsonUtil.getObjectArray4Json(HTTPresult);
			for(int j = 0;j < ob.length;j ++){
				//analyze return value, check if success
				Map<?, ?> map1 = JsonUtil.getMap4Json(ob[j].toString());
				Map<?, ?> map2 = JsonUtil.getMap4Json(map1.get("command_result").toString());
				String switch_id = map1.get("switch_id").toString();
				String result = map2.get("result").toString().trim();
				if(result.equals("success"))
				{
					AclRuleServiceImpl aclrule = new AclRuleServiceImpl();
					//if(!aclrule.AddSpecialRule(switch_id).equals("Success!"))//special rule
					//	result = "Add special rule failed";
					System.out.println(aclrule.AddSpecialRule(switch_id, controller_id));
				}
				else result = "Firewall enable failed";
				MapResult.put(switch_id, result);
			}
		}

		String ans = new String();
		String fail = new String();
		ans = "";
		fail = "[Ryu]WARNING IN FIREWALL ENABLE:\n";
		Set<String> keys = MapResult.keySet();
		for(String key:keys){
			String str = key.replaceAll("0", " ");//remove zero number for switch id
			str = str.trim();
			//if failed to enable firewall, return failure reason
			if(MapResult.get(key).equals("Add special rule failed"))
				ans += "switch_id=" + str + ":add special rule failed\n";
			else if(MapResult.get(key).equals("Firewall enable failed"))
				ans += "switch_id=" + str + ":firewall enable failed\n";
			else if(MapResult.get(key).equals("Error in HTTP request"))
				ans += "switch_id=" + str + ":error in HTTP request\n";
		}
		if(ans.equals(""))
			ans = "[Ryu]Switch firewall enable successd.";
		else ans = fail + ans;
		return ans;
	}

	public int InsertSwitchDesc(String controller_url) { // insert Switchinfo
		for (int k = 0; k < nSwsNumber; k++) {
			String strSwsDesc = "";
			try {
				strSwsDesc = HttpRequest.readContentFromGet(STRHTTPSWSDESC
						+ swsDpid[k]);
			} catch (IOException e) {
				System.out.println("Getting Ryu's switches' desc Error!");
				return HTTPERROR;
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1 = JsonUtil.getMap4Json(strSwsDesc);
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2 = JsonUtil.getMap4Json(map1.get(swsDpid[k]).toString());
			Switch s = new Switch();
			s.setDp_desc(map2.get("dp_desc").toString()); // practically received switch may not match defined switch
			s.setSerial_num(map2.get("serial_num").toString());
			s.setSw_desc(map2.get("sw_desc").toString());
			s.setHw_desc(map2.get("hw_desc").toString());
			s.setMfr_desc(map2.get("mfr_desc").toString());
			s.setDp_id(swsDpid[k]);
			s.setCtrl_id(daoController.getIdByRestUrl(controller_url+'/'));
			s.setCtime(new java.util.Date());
			try {
				daoSwitch.insert(s);
			} catch (Exception e) {
				System.out.println("Switches info insert error!");
				return INSERTERROR;
			}
		}
		return 0;
	}

	@Override
	public int InsertCtrlInfo(String controller_url, int controller_id) { // insert Ryu controllerinfo
		Controller c = new Controller();
		String ip;
		int end = controller_url.lastIndexOf(':');
		ip = controller_url.substring(7, end);
		c.setType("Ryu");
		c.setIp(ip);
		c.setId(controller_id);
		c.setRest_url(controller_url + '/');
		c.setCtime(new java.util.Date());
		try {
			daoController.insert(c);
		} catch (Exception e) {
			System.out.println("Controller insert error!");
			return INSERTERROR;
		}
		return 0;
	}

	@Override
	public int InsertPort(String controller_url) { // insert Ryu PortStats
		for (int k = 0; k < nSwsNumber; k++) {
			String strPort = "";
			try {
				strPort = HttpRequest.readContentFromGet(STRHTTPSWSPORTS
						+ swsDpid[k]); 
			} catch (IOException e) {
				System.out.print("Get Ryu's Port error!");
				return HTTPERROR;
			}
			Map<?, ?> map1 = JsonUtil.getMap4Json(strPort);
			Object[] ob = JsonUtil.getObjectArray4Json(map1.get(swsDpid[k])
					.toString());
			for (int i = 0; i < ob.length; i++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2 = JsonUtil.getMap4Json(ob[i].toString());
				PortStats p = new PortStats();
				/*Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id", String.valueOf(ryu_id));*/
				p.setDp_id(swsDpid[k]);
				if((map2.get("port_no").toString()).equals("LOCAL")) 
					p.setPort_no(Long.parseLong("65535"));
				else 
					p.setPort_no(Long.parseLong(map2.get("port_no").toString()));
					
				p.setCollisions(Integer.parseInt(map2.get("collisions")
						.toString()));
				p.setRcv_bytes(Long.parseLong(map2.get("rx_bytes").toString()));
				p.setRcv_drop(Integer.parseInt(map2.get("rx_dropped")
						.toString()));
				p.setRcv_err(Integer.parseInt(map2.get("rx_errors").toString()));
				p.setRcv_frm_err(Integer.parseInt(map2.get("rx_frame_err")
						.toString()));
				p.setRcv_over_err(Integer.parseInt(map2.get("rx_over_err")
						.toString()));
				p.setRcv_packets(Integer.parseInt(map2.get("rx_packets")
						.toString()));
				p.setTrsm_bytes(Long.parseLong(map2.get("tx_bytes").toString()));
				p.setTrsm_drop(Integer.parseInt(map2.get("tx_dropped")
						.toString()));
				p.setTrsm_err(Integer
						.parseInt(map2.get("tx_errors").toString()));
				p.setTrsm_packets(Integer.parseInt(map2.get("tx_packets")
						.toString()));
				p.setRcv_CRC_err(Integer.parseInt(map2.get("rx_crc_err")
						.toString()));
				// p.setDuration_sec(Long.parseLong(map2.get("duration_sec").toString()));
				// p.setDuration_nsec(map2.get("duration_nsec").toString());
				p.setUpdate_time(new Date());
				try {
					daoPortStats.insert(p);
				} catch (Exception e) {
					System.out.println("PortStats insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	public int InsertFlow(String controller_url) { // insert Ryu Flowinfo
		int ryu_id = daoController.getIdByRestUrl(controller_url+"/");
		for (int k = 0; k < nSwsNumber; k++) {
			String string = "";
			try {
				string = HttpRequest.readContentFromGet(STRHTTPSWSFLOW
						+ swsDpid[k]);
			} catch (IOException e) {
				System.out.println("Getting Ryu's switches'flow error!");
				return HTTPERROR;
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1 = JsonUtil.getMap4Json(string);
			Object[] objects = JsonUtil.getObjectArray4Json(map1
					.get(swsDpid[k]).toString());
			for (int j = 0; j < objects.length; j++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2 = JsonUtil.getMap4Json(objects[j].toString());
				Flow flow = new Flow();
				flow.setAction(map2.get("actions").toString());
				Map<?, ?> map3 = JsonUtil.getMap4Json(map2.get("match").toString());
				flow.setDl_type(String.valueOf(map3.get("dl_type")));
				flow.setDl_src(String.valueOf(map3.get("dl_src")));
				flow.setDl_dst(String.valueOf(map3.get("dl_dst")));
				if (String.valueOf(map3.get("dl_vlan")) != "null")
					flow.setDl_vlan(Integer.parseInt(String.valueOf(map3
							.get("dl_vlan"))));
				if (String.valueOf(map3.get("nw_proto")) != "null")
					flow.setNw_proto(Integer.parseInt(String.valueOf(map3
							.get("nw_proto"))));
				if (String.valueOf(map3.get("nw_tos")) != "null")
					flow.setNw_tos(Integer.parseInt(String.valueOf(map3
							.get("nw_tos"))));
				flow.setNw_src(String.valueOf(map3.get("nw_src")));
				flow.setNw_dst(String.valueOf(map3.get("nw_dst")));
				if (String.valueOf(map3.get("nw_src_masklen")) != "null")
					flow.setNw_src_masklen(Integer.parseInt(String.valueOf(map3
							.get("nw_src_masklen"))));
				if (String.valueOf(map3.get("nw_dst_masklen")) != "null")
					flow.setNw_dst_masklen(Integer.parseInt(String.valueOf(map3
							.get("nw_dst_masklen"))));
				if (String.valueOf(map3.get("tp_src")) != "null")
					flow.setTp_src(Integer.parseInt(String.valueOf(map3
							.get("tp_src"))));
				if (String.valueOf(map3.get("tp_dst")) != "null")
					flow.setTp_dst(Integer.parseInt(String.valueOf(map3
							.get("tp_dst"))));
				if (String.valueOf(map3.get("in_port")) != "null")
					flow.setIn_port(Integer.parseInt(String.valueOf(map3
							.get("in_port"))));
				// compared with floodlight, Ryu has extra parameters
				// flow.setDl_vlan_pcp();
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id", String.valueOf(ryu_id));
				flow.setSwitch_id(daoSwitch.getId(para));
				if (String.valueOf(map2.get("table_id")) != "null")
					flow.setTable_id(Integer.parseInt(String.valueOf(map2
							.get("table_id"))));

				if (String.valueOf(map2.get("byte_count")) != "null")
					flow.setByte_count(Integer.parseInt(String.valueOf(map2
							.get("byte_count"))));

				// System.out.println("-------------"+map2.get("packet_count"));
				if (map2.get("packet_count") != "null")
					flow.setPacket_count(Double.parseDouble(String.valueOf(map2
							.get("packet_count"))));
				if (String.valueOf(map2.get("idle_timeout")) != "null")
					flow.setIdle_timeout(Integer.parseInt(String.valueOf(map2
							.get("idle_timeout"))));
				if (String.valueOf(map2.get("hard_timeout")) != "null")
					flow.setHard_timeout(Integer.parseInt(String.valueOf(map2
							.get("hard_timeout"))));
				if (String.valueOf(map2.get("duration_sec")) != "null")
					flow.setDuration_sec(Integer.parseInt(String.valueOf(map2
							.get("duration_sec"))));
				if (String.valueOf(map2.get("duration_nsec")) != "null")
					flow.setDuration_nsec(Integer.parseInt(String.valueOf(map2
							.get("duration_nsec"))));
				if (String.valueOf(map2.get("priority")) != "null")
					flow.setPriority(Integer.parseInt(String.valueOf(map2
							.get("priority"))));
				flow.setCookie(String.valueOf(map2.get("cookie")));
				flow.setCtime(new java.util.Date());

				try {
					daoFlow.insert(flow);
				} catch (Exception e) {
					System.out.println("Flow insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	public int InsertPortDesc(String controller_url) { // insert PortDesc
		int ryu_id = daoController.getIdByRestUrl(controller_url+"/");
		for (int k = 0; k < nSwsNumber; k++) {
			String str = "";
			try {
				str = HttpRequest.readContentFromGet(STRHTTPPORTDESC
						+ swsDpid[k]);
			} catch (IOException e) {
				System.out
						.println("Getting Ryu's switches' ports description Error:");
				return HTTPERROR;
			}

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1 = JsonUtil.getMap4Json(str);
			Object[] objects = JsonUtil.getObjectArray4Json(map1
					.get(swsDpid[k]).toString());
			for (int j = 0; j < objects.length; j++) {
				Map<String, Object> map2 = new HashMap<String, Object>();

				map2 = JsonUtil.getMap4Json(objects[j].toString());

				Port port = new Port();
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id", String.valueOf(ryu_id));
				port.setSwitch_id(daoSwitch.getId(para));
				
				if((map2.get("port_no").toString()).equals("LOCAL")) 
					port.setPort_no(Long.parseLong("65535"));
				else 
					port.setPort_no(Long.parseLong(map2.get("port_no").toString()));
				
				port.setMac_addr(map2.get("hw_addr").toString());
				port.setPort_name(map2.get("name").toString());
				port.setConfig(Integer.parseInt(map2.get("config").toString()));
				port.setState(Integer.parseInt(map2.get("state").toString()));
				port.setCurr(Integer.parseInt(map2.get("curr").toString()));
				port.setAdvertised(Integer.parseInt(map2.get("advertised")
						.toString()));
				port.setSupported(Integer.parseInt(map2.get("supported")
						.toString()));
				port.setPeer(Integer.parseInt(map2.get("peer").toString()));
				// port.setCurr_speed(map2.get("curr_speed").toString());
				// port.setMax_speed(Integer.parseInt(map2.get("max_speed").toString()));
				port.setCtime(new java.util.Date());
				try {
					daoPort.insert(port);
				} catch (Exception e) {
					System.out
							.println("Port description stats info insert error:");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	public int InsertQueue() { //TODO

		return 0;
	}

	@Override
	public int InsertGroup() { // insert GroupStats
		for (int k = 0; k < nSwsNumber; k++) {
			String str = "";
			try {
				str = HttpRequest.readContentFromGet(STRHTTPGROUP + swsDpid[k]);
			} catch (IOException e) {
				System.out
						.println("Getting Ryu's switches' group stats Error!");
				return HTTPERROR;
			}

			Map<String, Object> map1 = new HashMap<String, Object>();
			map1 = JsonUtil.getMap4Json(str);
			Object[] objects = JsonUtil.getObjectArray4Json(map1
					.get(swsDpid[k]).toString());

			for (int j = 0; j < objects.length; j++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2 = JsonUtil.getMap4Json(objects[j].toString());

				RyuGroupStats group = new RyuGroupStats();
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id",
						String.valueOf(daoController.getIdByType("Ryu")));
				group.setSwitch_id(daoSwitch.getId(para));
				group.setCtrl_id(daoController.getIdByType("Ryu"));
				group.setLength(Integer.parseInt(map2.get("length").toString()));
				group.setGroup_id(Integer.parseInt(map2.get("group_id")
						.toString()));
				group.setRef_count(Integer.parseInt(map2.get("ref_count")
						.toString()));
				group.setPacket_count(Integer.parseInt(map2.get("packet_count")
						.toString()));
				group.setByte_count(Integer.parseInt(map2.get("byte_count")
						.toString()));
				group.setDuration_sec(Integer.parseInt(map2.get("duration_sec")
						.toString()));
				group.setDuration_nsec(map2.get("duration_nsec").toString());
				group.setBucket_stats(map2.get("bucket_stats").toString());
				group.setCtime(new java.util.Date());

				try {
					daoGroup.insert(group);
				} catch (Exception e) {
					System.out.println("Group info insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	public int InsertGroupDesc() { // insert GroupDesc
		for (int k = 0; k < nSwsNumber; k++) {
			String str = "";
			try {
				str = HttpRequest.readContentFromGet(STRHTTPGROUPDESC
						+ swsDpid[k]);
			} catch (IOException e) {
				System.out
						.println("Getting Ryu's switches' group description stats Error!");
				return HTTPERROR;
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1 = JsonUtil.getMap4Json(str);
			Object[] objects = JsonUtil.getObjectArray4Json(map1
					.get(swsDpid[k]).toString());

			for (int j = 0; j < objects.length; j++) {

				Map<String, Object> map2 = new HashMap<String, Object>();
				map2 = JsonUtil.getMap4Json(objects[j].toString());

				RyuGroupDesc groupdesc = new RyuGroupDesc();
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id",
						String.valueOf(daoController.getIdByType("Ryu")));
				groupdesc.setSwitch_id(daoSwitch.getId(para));
				groupdesc.setCtrl_id(daoController.getIdByType("Ryu"));
				groupdesc.setType(map2.get("type").toString());
				groupdesc.setGroup_id(Integer.parseInt(map2.get("group_id")
						.toString()));
				groupdesc.setBuckets(map2.get("buckets").toString());
				groupdesc.setCtime(new java.util.Date());

				try {
					daoGroupDesc.insert(groupdesc);
				} catch (Exception e) {
					System.out.println("Group description stats insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	public int InsertGroupFeatures() { // insert GroupsFeatures
		int ryu_id = daoController.getIdByType("Ryu");
		for (int k = 0; k < nSwsNumber; k++) {
			String str = "";
			try {
				str = HttpRequest.readContentFromGet(STRHTTPGROUPFEATURES
						+ swsDpid[k]);
			} catch (IOException e) {
				System.out
						.println("Getting Ryu's switches' group features stats Error!");
				return HTTPERROR;
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1 = JsonUtil.getMap4Json(str);
			Object[] objects = JsonUtil.getObjectArray4Json(map1
					.get(swsDpid[k]).toString());
			for (int j = 0; j < objects.length; j++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2 = JsonUtil.getMap4Json(objects[j].toString());
				RyuGroupFeatures groupfeatures = new RyuGroupFeatures();
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id", String.valueOf(ryu_id));
				groupfeatures.setSwitch_id(daoSwitch.getId(para));
				groupfeatures.setCtrl_id(ryu_id);
				groupfeatures.setTypes(map2.get("types").toString());
				groupfeatures.setCapabilities(map2.get("group_id").toString());
				groupfeatures.setMax_groups(map2.get("max_groups").toString());
				groupfeatures.setActions(map2.get("actions").toString());
				groupfeatures.setCtime(new java.util.Date());

				try {
					daoGroupFeatures.insert(groupfeatures);
				} catch (Exception e) {
					System.out.println("Group description stats insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	public int InsertMeter() { // insert MeterStats
		int ryu_id = daoController.getIdByType("Ryu");
		for (int k = 0; k < nSwsNumber; k++) {
			String strMeter = "";
			try {
				strMeter = HttpRequest.readContentFromGet(STRHTTPMETER
						+ swsDpid[k]);
			} catch (IOException e) {
				System.out.print("Get MeterStats error!");
				return HTTPERROR;
			}
			Map<?, ?> map1 = JsonUtil.getMap4Json(strMeter);
			Object[] ob = JsonUtil.getObjectArray4Json(map1.get(swsDpid[k])
					.toString());
			for (int i = 0; i < ob.length; i++) {
				Map<?, ?> map2 = JsonUtil.getMap4Json(ob[i].toString());
				RyuMeterStats p = new RyuMeterStats();
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id", String.valueOf(ryu_id));
				p.setSwitch_id(daoSwitch.getId(para));
				p.setCtrl_id(ryu_id);
				p.setMeter_id(Integer.parseInt(map2.get("meter_id").toString()));
				p.setLen(Integer.parseInt(map2.get("len").toString()));
				p.setFlow_count(Integer.parseInt(map2.get("flow_count")
						.toString()));
				p.setPacket_in_count(Integer.parseInt(map2.get(
						"packet_in_count").toString()));
				p.setByte_in_count(Integer.parseInt(map2.get("byte_in_count")
						.toString()));
				p.setDuration_sec(Long.parseLong(map2.get("duration_sec")
						.toString()));
				p.setDuration_nsec(map2.get("duration_nsec").toString());
				p.setBandStats(map2.get("band_stats").toString());
				p.setCtime(new Date());
				try {
					daoRyuMeterStats.insert(p);
				} catch (Exception e) {
					System.out.println("RyuMeterStats insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	public static String[] fromString(String s) {
		List<String> list = new ArrayList<String>();
		int indexfront = s.indexOf('\"', 0);
		int indexback = s.indexOf('\"', indexfront + 1);
		while (indexfront != -1) {
			list.add(s.substring(indexfront + 1, indexback));
			indexfront = s.indexOf('\"', indexback + 1);
			indexback = s.indexOf('\"', indexfront + 1);
		}
		return list.toArray(new String[list.size()]);
	}

	@Override
	public int InsertMeterConfig() { // insert MeterConfig
		int ryu_id = daoController.getIdByType("Ryu");
		for (int k = 0; k < nSwsNumber; k++) {
			String strMeterConfig = "";
			try {
				strMeterConfig = HttpRequest
						.readContentFromGet(STRHTTPMETERCONFIG + swsDpid[k]);
			} catch (IOException e) {
				System.out.print("Get MeterConfig error!");
				return HTTPERROR;
			}
			Map<?, ?> map1 = JsonUtil.getMap4Json(strMeterConfig);
			Object[] ob = JsonUtil.getObjectArray4Json(map1.get(swsDpid[k])
					.toString());
			for (int i = 0; i < ob.length; i++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				RyuMeterConfig p = new RyuMeterConfig();
				map2 = JsonUtil.getMap4Json(ob[i].toString());
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id", String.valueOf(ryu_id));
				p.setSwitch_id(daoSwitch.getId(para));
				p.setCtrl_id(ryu_id);
				p.setMeter_id(Integer.parseInt(map2.get("meter_id").toString()));
				p.setFlags(map2.get("flags").toString());
				p.setBands(map2.get("bands").toString());
				p.setCtime(new Date());
				try {
					daoRyuMeterConfig.insert(p);
				} catch (Exception e) {
					System.out.print("RyuMeterConfig insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	public int InsertMeterFeatures() { // insert MeterFeatures
		int ryu_id = daoController.getIdByType("Ryu");
		for (int k = 0; k < nSwsNumber; k++) {
			String strMeterFeatures = "";
			try {
				strMeterFeatures = HttpRequest
						.readContentFromGet(STRHTTPMETERFEATURES + swsDpid[k]);
			} catch (IOException e) {
				System.out.print("Get MeterFeatures error!");
				return HTTPERROR;
			}
			Map<?, ?> map1 = JsonUtil.getMap4Json(strMeterFeatures);
			Object[] ob = JsonUtil.getObjectArray4Json(map1.get(swsDpid[k])
					.toString());
			for (int i = 0; i < ob.length; i++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				RyuMeterFeatures p = new RyuMeterFeatures();
				map2 = JsonUtil.getMap4Json(ob[i].toString());
				Map<String, String> para = new HashMap<String, String>();
				para.put("dp_id", swsDpid[k]);
				para.put("ctrl_id", String.valueOf(ryu_id));
				p.setSwitch_id(daoSwitch.getId(para));
				p.setCtrl_id(ryu_id);
				p.setMax_Meter(Integer.parseInt(map2.get("max_meter")
						.toString()));
				p.setMax_Bands(Integer.parseInt(map2.get("max_bands")
						.toString()));
				p.setMax_Color(Integer.parseInt(map2.get("max_color")
						.toString()));
				p.setBand_Types(map2.get("band_types").toString());
				p.setCapabilities(map2.get("capabilities").toString());
				p.setCtime(new Date());

				try {
					daoRyuMeterFeatures.insert(p);
				} catch (Exception e) {
					System.out.print("RyuMeterFeatures insert error!");
					return INSERTERROR;
				}
			}
		}
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void RyuInfoRmAndInsert() {

		List<String> origin_STRHTTPPRE = new ArrayList<String>();
		origin_STRHTTPPRE = Arrays.asList(org_STRHTTPPRE.split(";"));
		// add controller_url from temporary config
		origin_STRHTTPPRE.addAll(extra_STRHTTPPRE);
		//for(String controller_url: STRHTTPPRE) System.out.println("ryu++++++++++++++++++++++++++++"+controller_url);
		//controller_url represents the url of specified controller in ryu_preurl
		int i = 0;
		for(String controller_url: origin_STRHTTPPRE) {
			this.SetArguments(controller_url);
			if (Init(500+i) == 0) {
				//ryu controller id start from 500
				InsertCtrlInfo(controller_url,500+i);
				InsertSwitchDesc(controller_url);
				InsertPort(controller_url);
				InsertPortDesc(controller_url);
				// InsertMeter();
				// InsertMeterConfig();
				// InsertMeterFeatures();
				// InsertGroup();
				// InsertGroupDesc();
				// InsertGroupFeatures();
				InsertFlow(controller_url);
				InsertLink(controller_url);
			} else {
				System.out.println("failed to initializing");
				return;
			}
			i++;
		 }
	}

	@Override
	public int InsertLink(String controller_url) {
		int ryu_id = daoController.getIdByRestUrl(controller_url+"/");
		String str = "";
		try {
			str = HttpRequest.readContentFromGet(STRHTTPLINK);
		} catch (IOException e) {
			System.out.println("Get Ryu's Link error!");
			return HTTPERROR;
		}
		Object[] ob = JsonUtil.getObjectArray4Json(str);
		for (int i = 0; i < ob.length; i++) {
			Map<?, ?> map1, map2, map3;
			map1 = JsonUtil.getMap4Json(ob[i].toString());
			map2 = JsonUtil.getMap4Json(map1.get("src").toString());
			map3 = JsonUtil.getMap4Json(map1.get("dst").toString());
			Link l = new Link();
			Map<String, String> mapSwitchId1 = new HashMap<String, String>();
			Map<String,String> mapSwitchId2=new HashMap<String,String>();
			Map<String, Integer> mapPortId1 = new HashMap<String, Integer>();
			Map<String,Integer> mapPortId2=new HashMap<String,Integer>();
			mapSwitchId1.put("dp_id", String.valueOf(Integer.parseInt(map2.get("dpid").toString(),16)));
			mapSwitchId1.put("ctrl_id", String.valueOf(ryu_id));
			Integer switch_id = daoSwitch.getId(mapSwitchId1);
//			System.out.println(mapSwitchId1);
			mapPortId1.put("switch_id", switch_id);
			mapPortId1.put("port_no",Integer.parseInt(map2.get("port_no").toString()));
//			System.out.println(switch_id);
			if (switch_id != 0)
			{
				l.setSrc_switch_id(switch_id);
				l.setSrc_port_id(daoPort.getId(mapPortId1));
			}
			mapSwitchId2.put("dp_id", String.valueOf(Integer.parseInt(map3.get("dpid").toString(),16)));
			mapSwitchId2.put("ctrl_id", String.valueOf(ryu_id));
			switch_id = daoSwitch.getId(mapSwitchId2);
			mapPortId2.put("switch_id", switch_id);
			mapPortId2.put("port_no",Integer.parseInt(map3.get("port_no").toString()));
//			System.out.println(switch_id);
			if (switch_id != 0)
			{
				l.setDst_switch_id(switch_id);
				l.setDst_port_id(daoPort.getId(mapPortId2));
			}
			l.setCtime(new Date());

			try {

				System.out.println(l.toString());
				daoLink.insert(l);

			} catch (Exception e) {
				System.out.print("RyuLink insert error!");
				return INSERTERROR;
			}
		}
		return 0;
	}
	
}