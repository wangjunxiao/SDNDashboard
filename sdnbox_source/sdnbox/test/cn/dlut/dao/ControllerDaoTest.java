package cn.dlut.dao;


import cn.dlut.base.BaseTest;
public class ControllerDaoTest extends BaseTest {
	@SuppressWarnings("unused")
	private ControllerDao dao;

	protected void setUp() throws Exception {
		super.setUp();
		dao = (ControllerDao) this.ctx.getBean("controllerDao");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao = null;
	}

	public void testGet() {
		// List<Controller> list = this.dao.getAll();
//		List<Controller> list = this.dao.getById(1);		
//		for (Controller info : list) {
//			log.debug(info.toString());
//		}
		//this.dao.delById(2);
		/*Controller c = new Controller();
		c.setCtime(new Date());
		c.setRest_url("localhost");
		c.setType("F");
		this.dao.insert(c);*/
		
		
//		this.dao.delById(25);
//		this.dao.delById(26);
//		this.dao.delById(27);
	}

}
