package cn.dlut.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.dlut.entity.Flow;
import cn.dlut.entity.FlowEntry;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.ModifyFloodFlowEntryService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.HttpRequest;
import cn.dlut.util.JsonUtil;

@Service("ModifyFloodFlowEntryService")
public class ModifyFloodFlowEntryServiceImpl extends AbstractBaseService implements ModifyFloodFlowEntryService {

	private String strUrl = null;
	private String strContent;

	@Override
	public String writeFlow(FlowEntry f, int controller_id) {

		
		String[] origin_url = AppProperties.flood_preurl().split(";");
		strUrl = origin_url[controller_id-50]
			+ AppProperties.floodFlowPUT();
		

		/**
		 * generate the actions field
		 */
		String strActionType = f.getAction();
		String strActionValue = null;
		if (null == strActionType) {
			return "failed to add flow entry";
		} else if ("output".equals(strActionType)) {
			strActionValue = "output=" + f.getOutput_port();
		} else if ("flood".equals(strActionType)) {
			strActionValue = "output=flood";
		} else if ("controller".equals(strActionType)) {
			strActionValue = "output=controller";
		} else if ("loopback".equals(strActionType)) {
			strActionValue = "output=ingress-port";
		}

		else if ("vlan_id".equals(strActionType)) {
			strActionValue = "set-vlan-id=" + f.getNew_vlan_id();
		} else if ("vlan_priority".equals(strActionType)) {
			strActionValue = "set-vlan-priority=" + f.getNew_vlan_priority();
		} else if ("dl_src".equals(strActionType)) {
			strActionValue = "set-src-mac=" + f.getNew_dl_src();
		} else if ("dl_dst".equals(strActionType)) {
			strActionValue = "set-dst-mac=" + f.getNew_dl_dst();
		} else if ("nw_src".equals(strActionType)) {
			strActionValue = "set-src-ip=" + f.getNew_nw_src();
		} else if ("nw_dst".equals(strActionType)) {
			strActionValue = "set-dst-ip=" + f.getNew_nw_dst();
		} else if ("nw_tos".equals(strActionType)) {
			strActionValue = "set-tos-bits=" + f.getNew_nw_tos();
		} else if ("tp_src".equals(strActionType)) {
			strActionValue = "set-src-port=" + f.getNew_tp_src();
		} else if ("tp_dst".equals(strActionType)) {
			strActionValue = "set-dst-port=" + f.getNew_tp_dst();
		} else if ("vlan_header".equals(strActionType)) {
			strActionValue = "strip-vlan";
		} else if ("drop".equals(strActionType)) {
			strActionValue = "";
		}

		if (null == f.getDpid()) {
			f.setDpid("");
		}
		if (null == f.getName()) {
			f.setName("");
		}
		if (null == f.getPriority()) {
			f.setPriority("");
		}
		if (null == f.getIn_port()) {
			f.setIn_port("");
		}
		if (null == f.getDl_src()) {
			f.setDl_src("");
		}
		if (null == f.getDl_dst()) {
			f.setDl_dst("");
		}
		if (null == f.getDl_vlan()) {
			f.setDl_vlan("");
		}
		if (null == f.getDl_vlan_priority()) {
			f.setDl_vlan_priority("");
		}

		/**
		 * generate the Ether-type field
		 */
		if (null == f.getDl_type()) {
			f.setDl_type("");
		} else if ("IPv4".equals(f.getDl_type())) {
			f.setDl_type("0x0800");
		} else if ("ARP".equals(f.getDl_type())) {
			f.setDl_type("0x0806");
		} else if ("LLDP".equals(f.getDl_type())) {
			f.setDl_type("0x88CC");
		} else if ("VLAN".equals(f.getDl_type())) {
			f.setDl_type("0x8100");
		} else if ("MPLS_unicast".equals(f.getDl_type())) {
			f.setDl_type("0x8847");
		} else if ("MPLS_multicast".equals(f.getDl_type())) {
			f.setDl_type("0x8848");
		}

		if (null == f.getNw_tos()) {
			f.setNw_tos("");
		}

		/**
		 * generate the protocol field
		 */
		if (null == f.getNw_proto()) {
			f.setNw_proto("");
		} else if ("ICMP".equals(f.getNw_proto())) {
			f.setNw_proto("1");
		} else if ("IGMP".equals(f.getNw_proto())) {
			f.setNw_proto("2");
		} else if ("TCP".equals(f.getNw_proto())) {
			f.setNw_proto("6");
		} else if ("EGP".equals(f.getNw_proto())) {
			f.setNw_proto("8");
		} else if ("IGP".equals(f.getNw_proto())) {
			f.setNw_proto("9");
		} else if ("UDP".equals(f.getNw_proto())) {
			f.setNw_proto("17");
		} else if ("IPv6".equals(f.getNw_proto())) {
			f.setNw_proto("41");
		} else if ("OSPF".equals(f.getNw_proto())) {
			f.setNw_proto("89");
		}

		/**
		 * generate the src IP and its netmask
		 */
		if (null == f.getNw_src()) {
			f.setNw_src("");
		} else if (null != f.getNw_src_masklen()
				&& 0 != f.getNw_src_masklen().length()) {
			f.setNw_src(f.getNw_src() + "/" + f.getNw_src_masklen());
		}

		/**
		 * generate the dst IP and its netmask
		 */
		if (null == f.getNw_dst()) {
			f.setNw_dst("");
		} else if (null != f.getNw_dst_masklen()
				&& 0 != f.getNw_dst_masklen().length()) {
			f.setNw_dst(f.getNw_dst() + "/" + f.getNw_dst_masklen());
		}

		if (null == f.getTp_src() && 0 != f.getNw_src_masklen().length()) {
			f.setTp_src("");
		}
		if (null == f.getTp_dst()) {
			f.setTp_dst("");
		}
		if (null == f.getCookie()) {
			f.setCookie("");
		}

		strContent = "{\"switch\":\""
				+ f.getDpid()
				+ "\",\"name\":\""
				+ f.getName()
				+ "\",\"actions\":\""
				+ strActionValue
				+ "\",\"priority\":\""
				+ f.getPriority()
				// + "\",\"active\":\"" + active + "\",\"wildcards\":\"" +
				// wildcards
				+ "\",\"ingress-port\":\"" + f.getIn_port()
				+ "\",\"src-mac\":\"" + f.getDl_src() + "\",\"dst-mac\":\""
				+ f.getDl_dst() + "\",\"vlan-id\":\"" + f.getDl_vlan()
				+ "\",\"vlan-priority\":\"" + f.getDl_vlan_priority()
				+ "\",\"ether-type\":\"" + f.getDl_type()
				+ "\",\"tos-bits\":\"" + f.getNw_tos() + "\",\"protocol\":\""
				+ f.getNw_proto() + "\",\"src-ip\":\"" + f.getNw_src()
				+ "\",\"dst-ip\":\"" + f.getNw_dst() + "\",\"src-port\":\""
				+ f.getTp_src() + "\",\"dst-port\":\"" + f.getTp_dst()
				+ "\",\"cookie\":\"" + f.getCookie() + "\"}";

		System.out.println("JSON IS:" + strContent);

		String result = null;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);

			if (result.equals("{\"status\" : \"Entry pushed\"}")) {
				return "success to add flow entry";
			} else {
				return ("failed to add flow entry \n -->" + result);
			}
		} catch (IOException e) {
			result = "failed to connect controller";
			e.printStackTrace();
			return ("failed to add flow entry \n-->" + result);
		}
	}



	@Override
	public String delFlowEntry(final String flowName, int controller_id) {
		
		String[] origin_url = AppProperties.flood_preurl().split(";");
		strUrl = origin_url[controller_id-50]
			+ AppProperties.floodFlowPUT();
		

		String strResult = HttpRequest.MyDeleteMethod(strUrl, "{\"name\":\""
				+ flowName + "\"}");
		if (("{\"status\" : \"Entry " + flowName + " deleted\"}")
				.equals(strResult)) {
			return ("delete" + flowName + "flow entry success");
		}

		return ("delete" + flowName + "flow entry failed \n-->" + strResult);
	}


	@Override
	public List<Flow> queryFlowEntry(String dp_ID, int controller_id) {
		String[] origin_url = AppProperties.flood_preurl().split(";");
		strUrl = origin_url[controller_id-50]
					+ AppProperties.floodSwsFlow();
        System.out.println(strUrl+"+++++++++++++++++++++++");
		String strResult = null;
		try {
			strResult = HttpRequest.readContentFromGet(strUrl);
		} catch (IOException e) {
			return null;
		}
		System.out.println(strResult);
		List<Flow> list = dealResult(strResult,dp_ID);
		return list;
	}
	
	private List<Flow> dealResult(String strResult,String dp_id) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		List<Flow> list = new ArrayList<Flow>();

		map1 = JsonUtil.getMap4Json(strResult);
		
		if(map1.get(dp_id)!=null){
			Object[] objects = JsonUtil.getObjectArray4Json(map1.get(dp_id).toString());
			
			if(objects!=null && objects.length!=0){
				for(int j = 0;j < objects.length;j++)
				{
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2 = JsonUtil.getMap4Json(objects[j].toString());
					Map<String, Object> mapMatch = new HashMap<String, Object>();
					mapMatch = JsonUtil.getMap4Json(map2.get("match").toString());
				
					Flow flow = new Flow();
					flow.setTable_id(Integer.parseInt(map2.get("tableId").toString()));
			
					if(map2.get("byteCount")!=null)
					flow.setByte_count(Long.parseLong(map2.get("byteCount").toString()));
					
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
					
					list.add(flow);
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
		
		return list;
	}
}
