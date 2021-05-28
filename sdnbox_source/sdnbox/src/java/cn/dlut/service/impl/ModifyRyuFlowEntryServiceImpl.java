package cn.dlut.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;

import cn.dlut.entity.FlowEntry;
import cn.dlut.entity.MeterEntry;
import cn.dlut.entity.Port;
import cn.dlut.entity.RyuGroupStats;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.ModifyRyuFlowEntryService;
import cn.dlut.util.AppProperties;
import cn.dlut.util.HttpRequest;

@Service("ModifyRyuFlowEntryService")
public class ModifyRyuFlowEntryServiceImpl extends AbstractBaseService implements ModifyRyuFlowEntryService {

	private String strUrl = "";
	private String strContent = "";

	public String deleteAllMatchingFlow(FlowEntry f, int controller_id) {
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
				+ AppProperties.RyuDeleteAllMatchFlow();
		strContent = "{\"dpid\":" + f.getDpid() + ",\"cookie\":"
				+ f.getCookie() + ",\"cookie_mask\":0" + ",\"table_id\":0"   
				//there are many differences between flowentry of floodlight and flowentry of ryu, set default value
				+ ",\"idle_timeout\":" + f.getIdle_timeout()
				+ ",\"hard_timeout\":" + f.getHard_timeout() + ",\"priority\":"
				+ f.getPriority() + ",\"butter_id\":-1" + ",\"flags\":1"
				+ ",\"match\":{}" + ",\"actions\":" + f.getAction() + "}";
		String result;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "connect failed";
			e.printStackTrace();
			return "flow entry failed to wildly delete \n--->" + result;
		}
	}

	public String deleteFlowStrictly(FlowEntry f, int controller_id) {
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		                    + AppProperties.RyuDeleteFlowStrictly();
		strContent = "{\"dpid\":" + f.getDpid() + ",\"cookie\":"
				+ f.getCookie() + ",\"cookie_mask\":0" + ",\"table_id\":0"   
				//there are many differences between flowentry of floodlight and flowentry of ryu, set default value
				+ ",\"idle_timeout\":" + f.getIdle_timeout()
				+ ",\"hard_timeout\":" + f.getHard_timeout() + ",\"priority\":"
				+ f.getPriority() + ",\"butter_id\":-1" + ",\"flags\":1"
				+ ",\"match\":{}" + ",\"actions\":" + f.getAction() + "}";
		String result;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "connect failed";
			e.printStackTrace();
			return "flow entry failed to strictly delete \n--->" + result;
		}
	}

	public String addGroup(RyuGroupStats g, int controller_id) {           //Group no support
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		             + AppProperties.RyuAddGroup();

		strContent = "{\"dpid\":" + g.getSwitch_id() + ",\"length\":"      
				+ g.getLength() + ",\"group_id\":" + g.getGroup_id()
				+ ",\"ref_count\":" + g.getRef_count() + ",\"packet_count\":"
				+ g.getPacket_count() + ",\"byte_count\":" + g.getByte_count()
				+ ",\"duration_sec\":" + g.getDuration_sec()
				+ ",\"duration_nsec\":" + g.getDuration_nsec()
				+ ",\"bucket_stats\":" + g.getBucket_stats() + "}";

		String result;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "failed to connect";
			e.printStackTrace();
			return "failed to add group table \n--->" + result;
		}

	}

	public String modifyGroup(RyuGroupStats g, int controller_id) {        //Group no support
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		+ AppProperties.RyuModifyGroup();

		strContent = "{\"dpid\":" + g.getSwitch_id() + ",\"length\":"
				+ g.getLength() + ",\"group_id\":" + g.getGroup_id()
				+ ",\"ref_count\":" + g.getRef_count() + ",\"packet_count\":"
				+ g.getPacket_count() + ",\"byte_count\":" + g.getByte_count()
				+ ",\"duration_sec\":" + g.getDuration_sec()
				+ ",\"duration_nsec\":" + g.getDuration_nsec()
				+ ",\"bucket_stats\":" + g.getBucket_stats() + "}";

		String result;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "failed to connect";
			e.printStackTrace();
			return "failed to modify group table\n--->" + result;
		}
	}

	public String deleteGroup(RyuGroupStats g, int controller_id) {        //Group no support
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		+ AppProperties.RyuDeleteGroup();

		strContent = "{\"dpid\":" + g.getSwitch_id() + ",\"group_id\":"
				+ g.getGroup_id() + "}";

		String result;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "failed to connect";
			e.printStackTrace();
			return "failed to delete group table\n--->" + result;
		}
	}

	@Override
	public String ModifyFlowEntry(FlowEntry f, int controller_id) {      
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		+ AppProperties.RyuModifyFlow();
		//there are many differences between flowentry of floodlight and flowentry of ryu, set default value

		strContent = "{\"dpid\":" + f.getDpid() + ",\"cookie\":"
				+ f.getCookie() + ",\"cookie_mask\":0" + ",\"table_id\":0"
				+ ",\"idle_timeout\":" + f.getIdle_timeout()
				+ ",\"hard_timeout\":" + f.getHard_timeout() + ",\"priority\":"
				+ f.getPriority() + ",\"butter_id\":-1" + ",\"flags\":1"
				+ ",\"match\":{}" + ",\"actions\":" + f.getAction() + "}";

		System.out.println("JSON IS:" + strContent);
		String result = null;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "Fail in contacting Controller";
			e.printStackTrace();
			return ("Error:" + result);
		}
	}

	@Override
	public String ClearFlowentry(FlowEntry f, int controller_id) {
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		+ AppProperties.RyuDeleteAllFlow()
				+ f.getDpid();

		strContent = null;
		String result = "";
		try {
			result = HttpRequest.MyDeleteMethod(strUrl, strContent);
		} catch (Exception e) {
			result = "Fail in contacting Controller";
			e.printStackTrace();
			return ("Error:" + result);
		}
		return result;
	}

	@Override
	public String ModifyPortDesc(Port p, int controller_id) {
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		+ AppProperties.RyuModifyPort();

		// Warning: p.getConfig() and p.getMask() are not defined!
		strContent = "{\"dpid\":" + p.getId() + ",\"port_no\":\""
				+ p.getPort_no() + "\"," + "\"config\":0"
				+ /* p.getConfig() + */",\"mask\":0" + /* p.getMask() + */"}";

		System.out.println("JSON IS:" + strContent);
		String result = null;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "Fail in contacting Controller";
			e.printStackTrace();
			return ("Error:" + result);
		}
	}

	@Override
	public String AddMeterEntry(MeterEntry meterentry, int controller_id) {        //no support
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		+ AppProperties.RyuAddMeter();
		strContent = "{\"dpid\":" + meterentry.getDpid() + ",\"flags\":\""
				+ meterentry.getFlags() + "\"," + "\"meter_id\":"
				+ meterentry.getMeter_id() + ",\"bands\":"
				+ meterentry.getBands() + "}";
		System.out.println("JSON IS:" + strContent);
		String result = null;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "Fail in contacting Controller";
			e.printStackTrace();
			return ("Error:" + result);
		}
	}

	@Override
	public String ModifyMeterEntry(MeterEntry m, int controller_id) {          //no support
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
		+ AppProperties.RyuModifyMeter();

		strContent = "{\"dpid\":" + m.getDpid() + ",\"flags\":\""
				+ m.getFlags() + "\"," + "\"meter_id\":" + m.getMeter_id()
				+ ",\"bands\":" + m.getBands() + "}";

		System.out.println("JSON IS:" + strContent);
		String result = null;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "Fail in contacting Controller";
			e.printStackTrace();
			return ("Error:" + result);
		}
	}

	@Override
	public String DeleteMeterEntry(MeterEntry m, int controller_id) {       //no support
		String[] origin_url = AppProperties.Ryu_preurl().split(";");
		strUrl = origin_url[controller_id-500]
				+ AppProperties.RyuDeleteMeter();

		strContent = "{\"dpid\":" + m.getDpid() + ",\"meter_id\":"
				+ m.getMeter_id() + "}";

		System.out.println("JSON IS:" + strContent);
		String result = null;
		try {
			result = HttpRequest.readContentFromPost(strUrl, strContent);
			return result;
		} catch (IOException e) {
			result = "Fail in contacting Controller";
			e.printStackTrace();
			return ("Error:" + result);
		}
	}
}