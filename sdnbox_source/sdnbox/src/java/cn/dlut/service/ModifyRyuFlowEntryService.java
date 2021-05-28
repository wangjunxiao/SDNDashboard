package cn.dlut.service;

import cn.dlut.entity.FlowEntry;
import cn.dlut.entity.MeterEntry;
import cn.dlut.entity.Port;
import cn.dlut.entity.RyuGroupStats;


public interface ModifyRyuFlowEntryService {

	public String deleteAllMatchingFlow(FlowEntry f, int controller_id);
	public String deleteFlowStrictly(FlowEntry f, int controller_id);
	public String addGroup(RyuGroupStats g, int controller_id);
	public String modifyGroup(RyuGroupStats g, int controller_id);
	public String deleteGroup(RyuGroupStats g, int controller_id);
	public String ModifyFlowEntry(FlowEntry ryuflowentry, int controller_id);
	public String ClearFlowentry(FlowEntry ryuflowentry, int controller_id);
	public String ModifyPortDesc(Port port, int controller_id);
	public String AddMeterEntry(MeterEntry meterentry, int controller_id);
	public String ModifyMeterEntry(MeterEntry meterentry, int controller_id);
	public String DeleteMeterEntry(MeterEntry meterentry, int controller_id);
}
