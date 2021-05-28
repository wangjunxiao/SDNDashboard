package cn.dlut.dao;

import java.util.List;

import cn.dlut.base.BaseTest;
import cn.dlut.entity.Host;

public class HostDaoTest extends BaseTest {
	private HostDao dao;
	int i;
	
	protected void setUp() throws Exception {
		super.setUp();
		dao = (HostDao) this.ctx.getBean("hostDao");
		i++;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao = null;
	}

	public void testGet() {
		/**
		 * select
		 */
		List<Host> list = this.dao.getAll();
		//List<Flow> list = this.dao.getById(1);		
		for (Host info : list) {
			log.debug(info.toString());
		}
//		//this.dao.delById(2);
//		Flow s = new Flow();
//		s.setCtime(new java.util.Date());
//		s.setSwitch_id(1);
//		s.setByte_count(88888);
//		this.dao.insert(s);
		
		/**
		 * insert
		 */
		Host h = new Host();
		h.setPort_id(1);
		h.setSwitch_id(1);
		h.setIp_addr("192.168.1.222");
		h.setMac_addr("00.00.00.00.00.32");
		h.setCtime(new java.util.Date());
		h.setMtime(new java.util.Date());
		this.dao.insert(h);
		
		/**
		 * delete
		 */
		this.dao.delById(2);
		i++;
	}

}
