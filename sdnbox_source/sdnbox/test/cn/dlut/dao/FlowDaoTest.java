package cn.dlut.dao;


import cn.dlut.base.BaseTest;

public class FlowDaoTest extends BaseTest {
	private FlowDao dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = (FlowDao) this.ctx.getBean("flowDao");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao = null;
	}

	public void testGet() {
//		List<Flow> list = this.dao.getAll();
//		//List<Flow> list = this.dao.getById(1);		
//		for (Flow info : list) {
//			log.debug(info.toString());
//		}
//		//this.dao.delById(2);
//		Flow s = new Flow();
//		s.setCtime(new java.util.Date());
//		s.setSwitch_id(1);
//		s.setByte_count(88888);
//		s.setAction("100,DROP,1,200,OUTPUT,2,");
//		this.dao.insert(s);
		

    	this.dao.delByCtrlId(2);
//    	this.dao.delById(38);
//    	this.dao.delById(39);
//    	this.dao.delById(40);
//    	this.dao.delById(41);
	}

}
