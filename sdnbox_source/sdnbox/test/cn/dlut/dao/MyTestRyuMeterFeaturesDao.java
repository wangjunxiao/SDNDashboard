package cn.dlut.dao;

import cn.dlut.base.BaseTest;
import cn.dlut.dao.RyuMeterFeaturesDao;
import cn.dlut.entity.RyuMeterFeatures;

public class MyTestRyuMeterFeaturesDao extends BaseTest{

	private RyuMeterFeaturesDao dao;
	
	protected void setUp() throws Exception {
		super.setUp();
		dao=(RyuMeterFeaturesDao)this.ctx.getBean("ryuMeterFeaturesDao");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		dao=null;
	}
	
	public void testGet()
	{
		RyuMeterFeatures p=new RyuMeterFeatures();
		p.setSwitch_id(1);
		p.setCtrl_id(3);
		p.setMax_Color(13);
		p.setMax_Bands(23);
		p.setMax_Meter(0);
		p.setCapabilities("[\"KBPS\",\"BURST\",\"STATS\"]");
		p.setBand_Types("[\"DROP\"]");
		p.setCtime(new java.util.Date());
		try{
			dao.delById(5);
			dao.delByCtrl_id(3);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
