package cn.dlut.service;


import cn.dlut.base.BaseTest;
import cn.dlut.service.FloodInfoToDBService;

public class FloodlightInfoToDBTest extends BaseTest {
	
	private FloodInfoToDBService fidservice;

	protected void setUp() throws Exception {
		super.setUp();

		fidservice = (FloodInfoToDBService)this.ctx.getBean("floodInfoToDBService");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		
		fidservice = null;

	}

	public void testGet() {
		//update floodlight related dataset into db
		fidservice.FloodInfoRmAndInsert();
	}
}
