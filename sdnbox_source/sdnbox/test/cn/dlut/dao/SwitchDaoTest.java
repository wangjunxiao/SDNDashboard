package cn.dlut.dao;

import java.util.Date;

import cn.dlut.base.BaseTest;
import cn.dlut.entity.Switch;

public class SwitchDaoTest extends BaseTest {
	private SwitchDao dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = (SwitchDao) this.ctx.getBean("switchDao");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao = null;
	}

	public void testGet() {
		//List<Switch> list = this.dao.getAll();
//		List<Switch> list = this.dao.getById(1);		
//		for (Switch info : list) {
//			log.debug(info.toString());
//		}
//		
//		Map para = new HashMap();
//		para.put("dp_id", "00-00-00-00-00-02");
//		para.put("ctrl_id", "1");
//		
//		int id = this.dao.getId(para);
//		
//		System.out.println(id);
		Switch s=new Switch();
		s.setCtrl_id(3);
		s.setDp_desc("dvfdsg");
		s.setDp_id(4+"");
		s.setHw_desc("sdvgdfg");
		s.setMfr_desc("fdewgfreg");
		s.setN_tables(12);
		s.setSerial_num("11335");
		s.setSw_desc("ferh");
	    s.setCtime(new Date());
		dao.insert(s);
		//Switch s = new Switch();
		//s.setCtime(new java.util.Date());
		//s.setCtrl_id(1);
		//this.dao.insert(s);
		
//		this.dao.delById(1);
//		this.dao.delById(5);
//		this.dao.delById(40);
//		this.dao.delById(41);
//		this.dao.delById(42);
//		this.dao.delById(43);
//		this.dao.delById(44);
//		this.dao.delById(45);
//		this.dao.delById(46);
//		this.dao.delById(47);
		
	}

}
