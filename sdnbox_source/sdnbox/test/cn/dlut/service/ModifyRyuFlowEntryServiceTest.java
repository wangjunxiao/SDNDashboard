package cn.dlut.service;

import cn.dlut.base.BaseTest;
import cn.dlut.entity.RyuGroupStats;

public class ModifyRyuFlowEntryServiceTest extends BaseTest{
	
	private ModifyRyuFlowEntryService service;
	
	protected void setUp() throws Exception {
		super.setUp();

		service = (ModifyRyuFlowEntryService)this.ctx.getBean("ModifyRyuFlowEntryService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		service = null;

	}
	
	public void testPost(){
		RyuGroupStats g=new RyuGroupStats();
		g.setSwitch_id(1);
		g.setGroup_id(1);
		g.setBucket_stats("[{\"actions\": [{\"type\": \"OUTPUT\",\"port\": 1}]}]");
		System.out.print(service.addGroup(g, 501));
	}
}
