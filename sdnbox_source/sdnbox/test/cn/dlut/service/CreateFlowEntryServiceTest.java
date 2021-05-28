package cn.dlut.service;

import cn.dlut.service.impl.FlowEntryServiceImpl;

public class CreateFlowEntryServiceTest {
	public static void main(String args[]){
		/*
		FlowEntry f = new FlowEntry();
		f.setDpid("00:00:00:00:00:00:00:01");
		f.setName("flow_sun");
		f.setCookie("1");
		f.setAction("output");
		f.setPriority("0");
		f.setIn_port("3");
		f.setOutput_port("1");
		System.out.println("---The result is:" + FlowEntryService.createFlowEntry(f, 1));
		*/
		
		FlowEntryServiceImpl fs = new FlowEntryServiceImpl();
		System.out.println("---The result is:" + fs.deleteFlowEntry("flow_sun", 1,50));
	}
}
