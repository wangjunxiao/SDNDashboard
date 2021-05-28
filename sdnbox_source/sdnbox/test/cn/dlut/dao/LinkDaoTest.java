package cn.dlut.dao;

import cn.dlut.base.BaseTest;

public class LinkDaoTest extends BaseTest {
	private LinkDao dao;
    protected void setUp() throws Exception {
        super.setUp();
        dao = (LinkDao)this.ctx.getBean("linkDao");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        dao = null ;
    }
    
    public void testGet(){
//    	List<Link> list = this.dao.getById(1);
// 		for(Link info :list){
//   		 log.debug(info.toString());
// 		}
    	this.dao.delByCtrlId(2);
// 		Link s = new Link();
// 		s.setCtime(new java.util.Date());
// 		s.setSrc_switch_id(1);
// 		s.setSrc_port_id(1);
// 		s.setDst_switch_id(5);
// 		s.setDst_port_id(6);
// 	
// 		this.dao.insert(s);
    	
//    	this.dao.delById(12);
//    	this.dao.delById(13);
 		
	}
  
}
