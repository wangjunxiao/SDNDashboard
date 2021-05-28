package cn.dlut.dao;

import cn.dlut.base.BaseTest;
import cn.dlut.dao.RyuMeterConfigDao;
import cn.dlut.entity.RyuMeterConfig;

public class MyTestRyuMeterConfigDao extends BaseTest{

	private RyuMeterConfigDao dao;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		dao=(RyuMeterConfigDao)this.ctx.getBean("ryuMeterConfigDao");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		dao=null;
	}
	
	public void testGet(){
		
		
		RyuMeterConfig s=new RyuMeterConfig();
		s.setSwitch_id(1);
		s.setCtrl_id(3);
		s.setMeter_id(2);
		s.setFlags("[\"KBPS\",\"WPS\"]");
		s.setBands("[{\"type\": \"DROP\",\"rate\": 10,\"burst_size\": 12}]");
		s.setCtime(new java.util.Date());
		try{
			dao.delById(1);
			dao.delByCtrl_id(3);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
