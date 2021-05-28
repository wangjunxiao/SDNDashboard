package cn.dlut.service;

import java.util.List;

import cn.dlut.entity.Flow;
import cn.dlut.entity.FlowEntry;

public interface ModifyFloodFlowEntryService {

	public String writeFlow(FlowEntry flowEntry, int controller_id);

	public String delFlowEntry(String name, int controller_id);
	
	public List<Flow> queryFlowEntry(String dp_ID, int controller_id);

}
