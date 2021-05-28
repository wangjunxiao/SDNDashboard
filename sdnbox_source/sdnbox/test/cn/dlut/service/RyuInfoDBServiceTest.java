package cn.dlut.service;

import cn.dlut.base.BaseTest;

public class RyuInfoDBServiceTest extends BaseTest{

	
	private RyuInfoToDBService service;
	
	protected void setUp() throws Exception {
		super.setUp();

		service = (RyuInfoToDBService)this.ctx.getBean("RyuService");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
		service = null;

	}
	
	public void testGet()
	{
		service.RyuInfoRmAndInsert();
	}
}
