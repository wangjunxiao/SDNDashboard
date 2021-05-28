package cn.dlut.service;


import cn.dlut.base.BaseTest;


public class DelByCtrlIdServiceTest extends BaseTest {
	private DelByCtrlIdService service;
    protected void setUp() throws Exception {
        super.setUp();
        service = (DelByCtrlIdService)this.ctx.getBean("delByCtrlIdService");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        service = null ;
    }
    
    public void testDelByCtrlId(){
    	 this.service.delByCtrlId(50);
	}
}//~;
