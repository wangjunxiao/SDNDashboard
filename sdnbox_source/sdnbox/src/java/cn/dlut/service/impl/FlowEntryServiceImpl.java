package cn.dlut.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.dlut.entity.Flow;
import cn.dlut.entity.FlowEntry;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.FlowEntryService;

/**
 * add and delete flow entry
 * provide two kind of apis: createFlowEntry and deleteFlowEntry, can by called by flex app
 * @author alex
 */
@Service("FlowEntryService")
public class FlowEntryServiceImpl extends AbstractBaseService implements FlowEntryService{
	/**
	 * add flow entry
	 * @param f FlowEntry
	 * @param controllerType 1->Floodlight 0->Ryu
	 * @return 
	 */
	public String createFlowEntry(FlowEntry f, int controllerType, int controller_id){
		System.out.println(f.toString());
		if (controllerType == 1){
			ModifyFloodFlowEntryServiceImpl floodlight = new ModifyFloodFlowEntryServiceImpl();
			return floodlight.writeFlow(f,controller_id);
		}
		else if (controllerType == 0){
			@SuppressWarnings("unused")
			ModifyRyuFlowEntryServiceImpl ryu = new ModifyRyuFlowEntryServiceImpl();
			// TODO return ryu.createFlowEntry(f,controller_id);
			return "";
		}
		else{
			return "";
		}
	}
	
	/**
	 * query flow entry
	 * @param dp_id switch_id
	 * @param controllerType 1->Floodlight 0->Ryu
	 * @return 
	 */
	public List<Flow> queryFlowEntry(int controllerType,String dp_id,int controller_id){
		if(controllerType == 1){
			ModifyFloodFlowEntryServiceImpl floodlight = new ModifyFloodFlowEntryServiceImpl();
			return floodlight.queryFlowEntry(dp_id, controller_id);
		}
		else if (controllerType == 0){
			@SuppressWarnings("unused")
			ModifyRyuFlowEntryServiceImpl ryu = new ModifyRyuFlowEntryServiceImpl();
			// TODO return ryu.queryFlowEntry(dp_id, controller_id);
			return null;
		}
		else{
			return null;
		}
	}
	
	/**
	 * delete flow entry (only Floodlight support)
	 * @param flowName the name of added flow entry
	 * @param controllerType 1->Floodlight 0->Ryu
	 * @return 
	 */
	public String deleteFlowEntry(String flowName, int controllerType, int controller_id){
		if (controllerType == 1){
			ModifyFloodFlowEntryServiceImpl floodlight = new ModifyFloodFlowEntryServiceImpl();
			return floodlight.delFlowEntry(flowName,controller_id);
		}
		else if (controllerType == 0){
			@SuppressWarnings("unused")
			ModifyRyuFlowEntryServiceImpl ryu = new ModifyRyuFlowEntryServiceImpl();
			// TODO return ryu.delFlowEntry(flowName,controller_id);
			return "";
		}
		else{
			return "";
		}
	}
}