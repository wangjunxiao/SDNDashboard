package cn.dlut.service;

import java.util.List;

import cn.dlut.entity.Flow;
import cn.dlut.entity.FlowEntry;


public interface FlowEntryService {

	public String createFlowEntry(FlowEntry f, int controllerType, int controller_id);
	public String deleteFlowEntry(String flowName, int controllerType, int controller_id);
	public List<Flow> queryFlowEntry(int controllerType,String dp_id, int controller_id);

}