package cn.dlut.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dlut.dao.ControllerDao;
import cn.dlut.dao.FlowDao;
import cn.dlut.dao.HostDao;
import cn.dlut.dao.LinkDao;
import cn.dlut.dao.PortDao;
import cn.dlut.dao.PortStatsDao;
import cn.dlut.dao.SwitchDao;
import cn.dlut.entity.Controller;
import cn.dlut.entity.Flow;
import cn.dlut.entity.Link;
import cn.dlut.entity.Port;
import cn.dlut.entity.PortStats;
import cn.dlut.entity.Switch;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.DelByCtrlIdService;
import cn.dlut.service.FloodInfoToDBService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.HttpRequest;
import cn.dlut.util.JsonUtil;

@Service("floodInfoToDBService")
public class FloodInfoToDBServiceImpl extends AbstractBaseService implements FloodInfoToDBService {
	//get controller URL, possible existing multiple URLs, URLs are separated by “;"
	private static final String org_STRHTTPPRE = AppProperties.flood_preurl();
	public static ArrayList<String> extra_STRHTTPPRE = new ArrayList<String>(); //could be modified by add controller service
	private static String STRHTTPSWSIDS;
	private static String STRHTTPSWSPORTS;
	private static String STRHTTPSWSDESC;
	@SuppressWarnings("unused")
	private static String STRHTTPSWSTABLE;
	private static String STRHTTPSWSFEATURES;
	private static String STRHTTPLINKINFO;
	private static String STRHTTPFLOWINFO;
	private static String STRCTRLID;
	private static final int SWSMAXNUM = 40;
	private static int nSwsNumber;
	private static String[] swsDpid = new String[SWSMAXNUM];

	@Autowired
	private DelByCtrlIdService dbcs;
	@Autowired
	private ControllerDao daoController;
	@Autowired
	private SwitchDao daoSwitch;
	@Autowired
	private PortDao daoPort;
	@Autowired
	private PortStatsDao daoPortStats;
	@Autowired
	private FlowDao daoFlow;
	@SuppressWarnings("unused")
	@Autowired
	private HostDao daoHost;
	@Autowired
	private LinkDao daoLink;
	// temporarily records the port statistics this application currently get.
	private PortStats portStats = new PortStats();
	// records the port statistics this application got last time.
	private List<List<PortStats>> lastPortStatss = new ArrayList<List<PortStats>>();
	//private List<PortStats> lastPortStats = new ArrayList<PortStats>();
	private boolean isFirstPortStats = true;
	private boolean[] isFirstPortStatss;

	/**
	 * directly against specified URL and ctrl_id, finish initialization of the corresponding command to obtain information
	 * @param STR controller URL
	 * @param ctrl_id controller ID
	 */
	public void SetArguments(String STR,int ctrl_id) {
		STRHTTPSWSIDS = STR + AppProperties.floodSwsIds();
		STRHTTPSWSPORTS = STR + AppProperties.floodSwsPorts();
		STRHTTPSWSDESC = STR + AppProperties.floodSwsDesc();
		STRHTTPSWSTABLE = STR + AppProperties.floodSwsTable();
		STRHTTPSWSFEATURES = STR + AppProperties.floodSwsFeatures();
		STRHTTPLINKINFO = STR + AppProperties.floodLinkInfo();
		STRHTTPFLOWINFO = STR + AppProperties.floodSwsFlow();
		STRCTRLID = String.valueOf(ctrl_id);
	}

	/**
	 * insert specified controller entries by controller URL and controller ID
	 * @param ctrl_url controller URL
	 * @param id controller ID
	 */
	public void InsertCtrlInfo(String ctrl_url,int id) {
		String ip;
		int end = ctrl_url.lastIndexOf(':');
		ip = ctrl_url.substring(7, end);
		Controller c = new Controller();
		c.setCtime(new java.util.Date());
		c.setRest_url(ctrl_url);
		c.setType("Floodlight");
		c.setIp(ip);
		c.setId(id);
		try {			
			daoController.insert(c);
		} catch (Exception e) {
			System.out.println("controller info insert error!");
		}
	}
	@Override
	/**fixed BUG:
	 * BUG1: in ACTION field of flow table, if action leave empty, flow table is ignored.
	 * BUG2: set the iteration of searching switch_id to outside loop, reduce meaningless db access.
	 * fix: the action field saved in db have bean resolved, no need to resolved again
	 * */
	public void InsertFlowInfo() {
		Map <String, Object> map1 = new HashMap <String, Object>();
		String strFlowInfo;
		int i;
		int j;
		try {
			strFlowInfo = HttpRequest.readContentFromGet(STRHTTPFLOWINFO);
			System.out.println("flow_info"+strFlowInfo);
		} catch (IOException e) {
			System.out.println("Getting Floodlight's switches' flow error!");
			
			return;
		}
		
		map1 = JsonUtil.getMap4Json(strFlowInfo);
		for(i = 0;i < map1.size();i++)
		{
			Object[] objects;//initializing or not?
			objects = JsonUtil.getObjectArray4Json(map1.get(swsDpid[i]).toString());
			//which kind of switch info are saved in the array of swsdpids[]，according to this info to access map

			Map<String, String> mapSwitchId = new HashMap<String, String>();
			mapSwitchId.put("dp_id", swsDpid[i]);
			mapSwitchId.put("ctrl_id", STRCTRLID);
			int switch_id=daoSwitch.getId(mapSwitchId);
			
			for(j = 0;j < objects.length;j++)
			{
				Map <String, Object> map2 = new HashMap <String, Object>();
				map2 = JsonUtil.getMap4Json(objects[j].toString());
				Map <String, Object> mapMatch = new HashMap <String, Object> ();
				mapMatch = JsonUtil.getMap4Json(map2.get("match").toString());
			
				Flow flow = new Flow();
				flow.setSwitch_id(switch_id);				
				flow.setTable_id(Integer.parseInt(map2.get("tableId").toString()));
				//if((Boolean) map2.get("byteCount"))
				//(Integer) map2.get("byteCount")>0
				if(map2.get("byteCount")!=null)
				flow.setByte_count(Long.parseLong(map2.get("byteCount").toString()));
				//if((Boolean) map2.get("packet_count"))
				//System.out.println(map2.get("packet_count"));
				// && (Integer) map2.get("packet_count")>0
				if(map2.get("packet_count")!=null)
				flow.setPacket_count(Double.parseDouble(String.valueOf(map2
						.get("packet_count"))));
				flow.setIdle_timeout(Integer.parseInt(map2.get("idleTimeout").toString()));
				flow.setHard_timeout(Integer.parseInt(map2.get("hardTimeout").toString()));
				flow.setDuration_sec(Integer.parseInt(map2.get("durationSeconds").toString()));
				flow.setDuration_nsec(Integer.parseInt(map2.get("durationNanoseconds").toString()));
				flow.setPriority(Integer.parseInt(map2.get("priority").toString()));
				flow.setCookie(map2.get("cookie").toString());			
				flow.setDl_type(mapMatch.get("dataLayerType").toString());
				flow.setDl_src(mapMatch.get("dataLayerSource").toString());
				flow.setDl_dst(mapMatch.get("dataLayerDestination").toString());
				flow.setDl_vlan(Integer.parseInt(mapMatch.get("dataLayerVirtualLan").toString()));
				flow.setNw_proto(Integer.parseInt(mapMatch.get("networkProtocol").toString()));
				flow.setNw_tos(Integer.parseInt(mapMatch.get("networkTypeOfService").toString()));
				flow.setNw_src(mapMatch.get("networkSource").toString());
				flow.setNw_dst(mapMatch.get("networkDestination").toString());
				flow.setNw_src_masklen(Integer.parseInt(mapMatch.get("networkSourceMaskLen").toString()));
				flow.setNw_dst_masklen(Integer.parseInt(mapMatch.get("networkDestinationMaskLen").toString()));
				flow.setTp_src(Integer.parseInt(mapMatch.get("transportSource").toString()));
				flow.setTp_dst(Integer.parseInt(mapMatch.get("transportDestination").toString()));
				flow.setIn_port(Integer.parseInt(mapMatch.get("inputPort").toString()));
				flow.setAction(map2.get("actions").toString());
				flow.setCtime(new java.util.Date());

				daoFlow.insert(flow);

			}
		}
	}
	
	@Override
	public void InsertHostInfo() {
		// TODO 

	}

	@Override
	/**
	 * insert port info
	 * 	get corresponding port info by dp_iP
	 *  iteration for all switches outside loop, iteration for all ports of specified switch inside loop
	 */
	public void InsertPortInfo() {
		Map <String, Object> map1 = new HashMap <String, Object>();

		String strPortInfo;
		int i;
		int j;

		try {
			strPortInfo = HttpRequest.readContentFromGet(STRHTTPSWSFEATURES);
		} catch (IOException e) {
			System.out
					.println("Getting Floodlight's switches' ports info Error!");

			return;
		}

		map1 = JsonUtil.getMap4Json(strPortInfo);
		for (i = 0; i < nSwsNumber; i++) {
			Map <String, Object> map2 = new HashMap <String, Object>();
			map2 = JsonUtil.getMap4Json(map1.get(swsDpid[i]).toString());

			Object[] objects;
			objects = JsonUtil
					.getObjectArray4Json(map2.get("ports").toString());
			for (j = 0; j < objects.length; j++) {
				Map <String, Object> map3 = new HashMap <String, Object>();
				map3 = JsonUtil.getMap4Json(objects[j].toString());

				Port port = new Port();

				Map<String, String> mapSwitchId = new HashMap<String, String>();
				mapSwitchId.put("dp_id", swsDpid[i]);
				mapSwitchId.put("ctrl_id", STRCTRLID);
				port.setSwitch_id(daoSwitch.getId(mapSwitchId));

				port.setPort_no(Integer.parseInt(map3.get("portNumber")
						.toString()));
				port.setMac_addr(map3.get("hardwareAddress").toString());
				port.setPort_name(map3.get("name").toString());
				port.setCtime(new java.util.Date());

				try {
					daoPort.insert(port);
				} catch (Exception e) {
					System.out.println("Port info insert error!");
					return;
				}
			}
		}
	}

	/**
	 * return the port statistics of every period.
	 * @param num ：represent which one controller(in the case of multiple floodlight controller, only one controller, num==1）
	 */
	@Override
	public void InsertPortStats(int num) {
		Map <String, Object> map1 = new HashMap <String, Object>();

		String strPortStats;
		int i;
		int j;

		try {
			strPortStats = HttpRequest.readContentFromGet(STRHTTPSWSPORTS);
		} catch (IOException e) {
			System.out
					.println("Getting Floodlight's switches' ports statistics Error!");

			return;
		}

		map1 = JsonUtil.getMap4Json(strPortStats);
		if (isFirstPortStatss[num]) {
			List<PortStats> lastPortStats = new ArrayList<PortStats>();
			for (i = 0; i < nSwsNumber; i++) {
				Object[] objects;
				objects = JsonUtil.getObjectArray4Json(map1.get(swsDpid[i])
						.toString());
				for (j = 0; j < objects.length; j++) {
					Map <String, Object> map2 = new HashMap <String, Object>();
					map2 = JsonUtil.getMap4Json(objects[j].toString());

					/*Map<String, String> mapSwitchId = new HashMap<String, String>();
					mapSwitchId.put("dp_id", swsDpid[i]);
					mapSwitchId.put("ctrl_id", STRCTRLID);*/
					portStats.setDp_id(swsDpid[i]);

					//System.out.println(map2.get("portNumber").toString());
					portStats.setPort_no(Long.parseLong(map2
							.get("portNumber").toString()));
					portStats.setRcv_packets(Integer.parseInt(map2.get(
							"receivePackets").toString()));
					portStats.setTrsm_packets(Integer.parseInt(map2.get(
							"transmitPackets").toString()));
					portStats.setRcv_bytes(Long.parseLong(map2.get(
							"receiveBytes").toString()));
					portStats.setTrsm_bytes(Long.parseLong(map2.get(
							"transmitBytes").toString()));
					portStats.setRcv_drop(Integer.parseInt(map2.get(
							"receiveDropped").toString()));
					portStats.setTrsm_drop(Integer.parseInt(map2.get(
							"transmitDropped").toString()));
					portStats.setRcv_err(Integer.parseInt(map2.get(
							"receiveErrors").toString()));
					portStats.setTrsm_err(Integer.parseInt(map2.get(
							"transmitErrors").toString()));
					portStats.setRcv_frm_err(Integer.parseInt(map2.get(
							"receiveFrameErrors").toString()));
					portStats.setRcv_over_err(Integer.parseInt(map2.get(
							"receiveOverrunErrors").toString()));
					portStats.setRcv_CRC_err(Integer.parseInt(map2.get(
							"receiveCRCErrors").toString()));
					portStats.setCollisions(Integer.parseInt(map2.get(
							"collisions").toString()));

					PortStats lastPortStat = new PortStats();
					lastPortStat.setDp_id(portStats.getDp_id());
					lastPortStat.setPort_no(portStats.getPort_no());
					lastPortStat.setRcv_packets(portStats.getRcv_packets());
					lastPortStat.setTrsm_packets(portStats.getTrsm_packets());
					lastPortStat.setRcv_bytes(portStats.getRcv_bytes());
					lastPortStat.setTrsm_bytes(portStats.getTrsm_bytes());
					lastPortStat.setRcv_drop(portStats.getRcv_drop());
					lastPortStat.setTrsm_drop(portStats.getTrsm_drop());
					lastPortStat.setRcv_err(portStats.getRcv_err());
					lastPortStat.setTrsm_err(portStats.getTrsm_err());
					lastPortStat.setRcv_frm_err(portStats.getRcv_frm_err());
					lastPortStat.setRcv_over_err(portStats.getRcv_over_err());
					lastPortStat.setRcv_CRC_err(portStats.getRcv_CRC_err());
					lastPortStat.setCollisions(portStats.getCollisions());
					lastPortStat.setUpdate_time(new java.util.Date());

					PortStats tmp;
					tmp = lastPortStat;
					lastPortStat = portStats;
					portStats = tmp;

					lastPortStats.add(lastPortStat);

					/*try {
						daoPortStats.insert(portStats);
					} catch (Exception e) {
						System.out.println("PortStats insert error!");
						return;
					}*/
				}
			}
			lastPortStatss.add(lastPortStats);
			isFirstPortStatss[num] = false;
		} else// not the first time
		{
			List<PortStats> lastPortStats = lastPortStatss.get(num);
			int count = 0;
			for (i = 0; i < nSwsNumber; i++) {
				Object[] objects;
				objects = JsonUtil.getObjectArray4Json(map1.get(swsDpid[i])
						.toString());
				for (j = 0; j < objects.length; j++) {
					Map <String, Object> map2 = new HashMap <String, Object>();
					map2 = JsonUtil.getMap4Json(objects[j].toString());

					/*Map<String, String> mapSwitchId = new HashMap<String, String>();
					mapSwitchId.put("dp_id", swsDpid[i]);
					mapSwitchId.put("ctrl_id", STRCTRLID);*/
					portStats.setDp_id(swsDpid[i]);

					portStats.setPort_no(Integer.parseInt(map2
							.get("portNumber").toString()));
					portStats.setRcv_packets(Integer.parseInt(map2.get(
							"receivePackets").toString()));
					portStats.setTrsm_packets(Integer.parseInt(map2.get(
							"transmitPackets").toString()));
					portStats.setRcv_bytes(Long.parseLong(map2.get(
							"receiveBytes").toString()));
					portStats.setTrsm_bytes(Long.parseLong(map2.get(
							"transmitBytes").toString()));
					portStats.setRcv_drop(Integer.parseInt(map2.get(
							"receiveDropped").toString()));
					portStats.setTrsm_drop(Integer.parseInt(map2.get(
							"transmitDropped").toString()));
					portStats.setRcv_err(Integer.parseInt(map2.get(
							"receiveErrors").toString()));
					portStats.setTrsm_err(Integer.parseInt(map2.get(
							"transmitErrors").toString()));
					portStats.setRcv_frm_err(Integer.parseInt(map2.get(
							"receiveFrameErrors").toString()));
					portStats.setRcv_over_err(Integer.parseInt(map2.get(
							"receiveOverrunErrors").toString()));
					portStats.setRcv_CRC_err(Integer.parseInt(map2.get(
							"receiveCRCErrors").toString()));
					portStats.setCollisions(Integer.parseInt(map2.get(
							"collisions").toString()));

					PortStats lastPortStat = lastPortStats.get(count);
					lastPortStat.setDp_id(portStats.getDp_id());
					lastPortStat.setPort_no(portStats.getPort_no());
					lastPortStat.setRcv_packets(portStats.getRcv_packets()
							- lastPortStat.getRcv_packets());
					lastPortStat.setTrsm_packets(portStats.getTrsm_packets()
							- lastPortStat.getTrsm_packets());
					lastPortStat.setRcv_bytes(portStats.getRcv_bytes()
							- lastPortStat.getRcv_bytes());
					lastPortStat.setTrsm_bytes(portStats.getTrsm_bytes()
							- lastPortStat.getTrsm_bytes());
					lastPortStat.setRcv_drop(portStats.getRcv_drop()
							- lastPortStat.getRcv_drop());
					lastPortStat.setTrsm_drop(portStats.getTrsm_drop()
							- lastPortStat.getTrsm_drop());
					lastPortStat.setRcv_err(portStats.getRcv_err()
							- lastPortStat.getRcv_err());
					lastPortStat.setTrsm_err(portStats.getTrsm_err()
							- lastPortStat.getTrsm_err());
					lastPortStat.setRcv_frm_err(portStats.getRcv_frm_err()
							- lastPortStat.getRcv_frm_err());
					lastPortStat.setRcv_over_err(portStats.getRcv_over_err()
							- lastPortStat.getRcv_over_err());
					lastPortStat.setRcv_CRC_err(portStats.getRcv_CRC_err()
							- lastPortStat.getRcv_CRC_err());
					lastPortStat.setCollisions(portStats.getCollisions()
							- lastPortStat.getCollisions());
					lastPortStat.setUpdate_time(new java.util.Date());

					PortStats tmp;
					tmp = lastPortStat;
					lastPortStats.set(count, portStats);
					count++;
					portStats = tmp;

					try {
						daoPortStats.insert(portStats);

					} catch (Exception e) {
						System.out.println(e);
						System.out.println("PortStats insert error!");
						return;
					}
				}
			}
			lastPortStatss.set(num, lastPortStats);
		}
	}

	/**
	 * return the port statistics from this application began.
	 */
	public void InsertPortStats_2() {
		Map <String, Object> map1 = new HashMap <String, Object> ();

		String strPortStats;
		int i;
		int j;

		try {
			strPortStats = HttpRequest.readContentFromGet(STRHTTPSWSPORTS);
		} catch (IOException e) {
			System.out
					.println("Getting Floodlight's switches' ports statistics Error!");

			return;
		}

		map1 = JsonUtil.getMap4Json(strPortStats);
		for (i = 0; i < nSwsNumber; i++) {
			Object[] objects;
			objects = JsonUtil.getObjectArray4Json(map1.get(swsDpid[i])
					.toString());
			for (j = 0; j < objects.length; j++) {
				Map <String, Object> map2 = new HashMap <String, Object>();
				map2 = JsonUtil.getMap4Json(objects[j].toString());

				PortStats portStats = new PortStats();

				/*Map<String, String> mapSwitchId = new HashMap<String, String>();
				mapSwitchId.put("dp_id", swsDpid[i]);
				mapSwitchId.put("ctrl_id", STRCTRLID);*/
				portStats.setDp_id(swsDpid[i]);

				portStats.setPort_no(Integer.parseInt(map2.get("portNumber")
						.toString()));
				portStats.setRcv_packets(Integer.parseInt(map2.get(
						"receivePackets").toString()));
				portStats.setTrsm_packets(Integer.parseInt(map2.get(
						"transmitPackets").toString()));
				portStats.setRcv_bytes(Integer.parseInt(map2
						.get("receiveBytes").toString()));
				portStats.setTrsm_bytes(Integer.parseInt(map2.get(
						"transmitBytes").toString()));
				portStats.setRcv_drop(Integer.parseInt(map2.get(
						"receiveDropped").toString()));
				portStats.setTrsm_drop(Integer.parseInt(map2.get(
						"transmitDropped").toString()));
				portStats.setRcv_err(Integer.parseInt(map2.get("receiveErrors")
						.toString()));
				portStats.setTrsm_err(Integer.parseInt(map2.get(
						"transmitErrors").toString()));
				portStats.setRcv_frm_err(Integer.parseInt(map2.get(
						"receiveFrameErrors").toString()));
				portStats.setRcv_over_err(Integer.parseInt(map2.get(
						"receiveOverrunErrors").toString()));
				portStats.setRcv_CRC_err(Integer.parseInt(map2.get(
						"receiveCRCErrors").toString()));
				portStats.setCollisions(Integer.parseInt(map2.get("collisions")
						.toString()));
				portStats.setUpdate_time(new java.util.Date());

				try {
					daoPortStats.insert(portStats);
				} catch (Exception e) {
					System.out.println("Port info insert error!");
					return;
				}
			}
		}
	}

	@Override
	public void InsertSwitchInfo() {
		String strSwsDesc;
		String strSwsFeatures;

		try {
			System.out.println(STRHTTPSWSDESC);
			strSwsDesc = HttpRequest.readContentFromGet(STRHTTPSWSDESC);
		} catch (IOException e) {
			System.out.println("Getting Floodlight's switches' desc Error!");
			return;
		}

		try {
			System.out.println(STRHTTPSWSFEATURES);
			strSwsFeatures = HttpRequest.readContentFromGet(STRHTTPSWSFEATURES);
		} catch (IOException e) {
			System.out
					.println("Getting Floodlight's switches' features error!");
			return;
		}

		Map <String, Object> map1 = new HashMap<String, Object>();
		map1 = JsonUtil.getMap4Json(strSwsDesc);
		int i;
		int j;
		for (i = 0; map1!=null && i < map1.size(); i++) {
			Object[] objects;
			objects = JsonUtil.getObjectArray4Json(map1.get(swsDpid[i])
					.toString());

			Map <String, Object> map3 = new HashMap<String, Object>();
			map3 = JsonUtil.getMap4Json(strSwsFeatures);
			Map <String, Object> map4 = new HashMap <String, Object>();
			map4 = JsonUtil.getMap4Json(map3.get(swsDpid[i]).toString());

			for (j = 0; j < objects.length; j++) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2 = JsonUtil.getMap4Json(objects[j].toString());

				Switch s = new Switch();
				s.setDp_desc(map2.get("datapathDescription").toString());
				s.setSerial_num(map2.get("serialNumber").toString());
				s.setSw_desc(map2.get("softwareDescription").toString());
				s.setHw_desc(map2.get("hardwareDescription").toString());
				s.setMfr_desc(map2.get("manufacturerDescription").toString());// ?????
				s.setDp_id(swsDpid[i]);
				s.setCtrl_id(Integer.parseInt(STRCTRLID));

				s.setN_tables(Integer.parseInt(map4.get("tables").toString()));
				s.setCtime(new java.util.Date());

				try {
					daoSwitch.insert(s);
				} catch (Exception e) {
					System.out.println("Switches info insert error!");
					return;
				}
			}
		}
	}

	@Override
	public void InsertLinkInfo() {
		String strLinkInfo;

		try {
			strLinkInfo = HttpRequest.readContentFromGet(STRHTTPLINKINFO);
		} catch (IOException e) {
			System.out.println("Getting Floodlight's links info error!");
			return;
		}

		Object[] objects;
		objects = JsonUtil.getObjectArray4Json(strLinkInfo);

		int i;
		for (i = 0; i < objects.length; i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1 = JsonUtil.getMap4Json(objects[i].toString());

			Link link = new Link();
			int nSrcSwitchId;
			int nDstSwitchId;

			Map<String, String> mapSwitchId = new HashMap<String, String>();
			mapSwitchId.put("dp_id", map1.get("src-switch").toString());// ??????
			mapSwitchId.put("ctrl_id", STRCTRLID);
			nSrcSwitchId = daoSwitch.getId(mapSwitchId);
			mapSwitchId.clear();

			mapSwitchId.put("dp_id", map1.get("dst-switch").toString());
			mapSwitchId.put("ctrl_id", STRCTRLID);
			nDstSwitchId = daoSwitch.getId(mapSwitchId);

			link.setSrc_switch_id(nSrcSwitchId);
			link.setDst_switch_id(nDstSwitchId);

			Map<String, Integer> mapPortId = new HashMap<String, Integer>();
			mapPortId.put("switch_id", nSrcSwitchId);
			mapPortId.put("port_no",
					Integer.parseInt(map1.get("src-port").toString()));
			link.setSrc_port_id(daoPort.getId(mapPortId));
			mapPortId.clear();

			mapPortId.put("switch_id", nDstSwitchId);
			mapPortId.put("port_no",
					Integer.parseInt(map1.get("dst-port").toString()));
			link.setDst_port_id(daoPort.getId(mapPortId));

			link.setCtime(new java.util.Date());

			try {
				daoLink.insert(link);
			} catch (Exception e) {
				System.out.println("Link info insert error!");
			}

		}
	}

	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
	public void FloodInfoRmAndInsert() {
		//fix bug: after URL which defined in config file have changed, db can delete original out dated controller
		List<Controller> ctrl=daoController.getAll();
		if(!ctrl.isEmpty())
		{	
			for(Controller c:ctrl)
			{
				// delete controller related link, host, flow, port, switch info	
				dbcs.delByCtrlId(c.getId());
			}
		}
		
		List<String> origin_STRHTTPPRE = new ArrayList<String>();
		origin_STRHTTPPRE = Arrays.asList(org_STRHTTPPRE.split(";"));
		// add controller_url from temporary config
		origin_STRHTTPPRE.addAll(extra_STRHTTPPRE);
		
		//for(String controller_url: origin_STRHTTPPRE) System.out.println("Floodlight++++++++++++++++++++++++++++"+controller_url);
		
		int controller_num = origin_STRHTTPPRE.size();
		
		if(isFirstPortStats){
			isFirstPortStatss = new boolean[controller_num];
			Arrays.fill(isFirstPortStatss, true);
			isFirstPortStats = false;
		}
		int i=0;
		for (String controller_url:origin_STRHTTPPRE) {
			//floodlight controller id start from 50
			SetArguments(controller_url,50+i);		
			if (1 == Init()) {//note that the below order can not be adjusted, since the table dependency in db
				//insert controller info 
				InsertCtrlInfo(controller_url,50+i);
				//get floodlight related switch info, write into db
				InsertSwitchInfo();
				//get flow table info, write into db
				InsertFlowInfo();
				//get switch port info, write into db
				InsertPortInfo();
				//get port statistics info, write into db
				InsertPortStats(i);
				//get link info, write into db
				InsertLinkInfo();
				// TODO
				InsertHostInfo();
				
			}
			i++;
			System.out.println(lastPortStatss.get(0).toString());
		}
	}

	@Override
	public int Init() {
		// initializing swsDpids[],nSwsNumber
		nSwsNumber = 0;

		// resolve the switch controlled by floodlight
		String strSwsDpid;
		try {
			strSwsDpid = HttpRequest.readContentFromGet(STRHTTPSWSIDS);
			/**
			 * modifyswsDpids[], put all DPIDS filed of switches into swsDpids[]
			 */
			int i = 0;
			int nIndexFor = strSwsDpid.indexOf('\"');
			int nIndexBack = strSwsDpid.indexOf('\"', nIndexFor + 1);
			while (nIndexFor > 0) {
				swsDpid[i++] = strSwsDpid.substring(nIndexFor + 1, nIndexBack);
				nIndexFor = strSwsDpid.indexOf('\"', nIndexBack + 1);
				nIndexBack = strSwsDpid.indexOf('\"', nIndexFor + 1);
				nSwsNumber++;
			}
		} catch (IOException e) {
			System.out.println("Getting Floodlight's switches id error!");
			return 0;
		}
		return 1;
	}

}
