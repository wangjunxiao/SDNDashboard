package cn.dlut.dao;

import cn.dlut.base.BaseTest;
import cn.dlut.dao.RyuMeterStatsDao;
import cn.dlut.entity.RyuMeterStats;

public class MyTestRyuMeterStatsDao extends BaseTest{

	private RyuMeterStatsDao dao;
	
	protected void setUp() throws Exception {
		super.setUp();
		dao=(RyuMeterStatsDao)this.ctx.getBean("ryuMeterStatsDao");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		dao=null;
	}
	
	public void testGet()
	{
		RyuMeterStats p=new RyuMeterStats();
		p.setSwitch_id(1);
		p.setMeter_id(3);
		p.setCtrl_id(3);
		p.setLen(10);
		p.setFlow_count(14);
		p.setPacket_in_count(0);
		p.setByte_in_count(0);
		p.setDuration_sec(1);
		p.setDuration_nsec("1000000000");
		p.setBandStats("[{\"packet_band_count\": 1,\"byte_band_count\": 2}]");
		p.setCtime(new java.util.Date());
		try{
			dao.insert(p);
		}catch(Exception e){
			e.printStackTrace();
			return ;
		}
	}
}
