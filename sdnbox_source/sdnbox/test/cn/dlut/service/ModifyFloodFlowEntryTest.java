package cn.dlut.service;

import java.util.List;

import cn.dlut.base.BaseTest;
import cn.dlut.entity.Flow;
import cn.dlut.entity.FlowEntry;

public class ModifyFloodFlowEntryTest extends BaseTest {

	private ModifyFloodFlowEntryService wffs;

	protected void setUp() throws Exception {
		super.setUp();

		wffs = (ModifyFloodFlowEntryService)this.ctx.getBean("ModifyFloodFlowEntryService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		wffs = null;

	}
	
	public void testQuery(){
		List<Flow> list = wffs.queryFlowEntry("ce:72:48:6e:73:02:02:4b",50);
		for (Flow flow : list) {
			System.out.println(flow.getCookie());
		}
	}

	public void testGet() {
		/**
		 * test if success to add an flow entry 
		 */
		FlowEntry f = new FlowEntry();
		f.setDpid("00:00:00:00:00:01");
		f.setName("flow_fen");
		f.setCookie("1");
		f.setAction("output");
		f.setPriority("0");
		f.setIn_port("2");
		f.setOutput_port("1");
		f.setDl_type("IPv4");
		f.setNw_proto("TCP");
		f.setNw_src("128.2.2.1");
		f.setNw_src_masklen("17");
		System.out.println(wffs.writeFlow(f,50));
		
		/**
		 * test if success to delete an flow entry
		 */
//		System.out.println(wffs.delFlowEntry("flow_fen"));
}
}
