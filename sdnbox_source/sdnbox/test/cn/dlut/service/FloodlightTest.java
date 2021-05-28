package cn.dlut.service;

import cn.dlut.base.BaseTest;
import cn.dlut.entity.Rule;

public class FloodlightTest extends BaseTest {
	private AclRuleService  service; 
	protected void setUp() throws Exception {
        super.setUp();
        service = (AclRuleService)this.ctx.getBean("AclRuleService");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        service = null ;
    }
  /*  public void testQuery(){
    	List<Rule> rules;
   	 	rules=this.service.QueryRule("b44a486e7302024b", 1);
   	 	for(Rule r:rules)
   	 	{
   	 		System.out.println(r.getRuleid());
   	 		System.out.println(r.getAction());
   	 		System.out.println(r.getSrc_ip());
   	 		System.out.println(r.getDst_ip());
   	 		System.out.println(r.getTp_dst());
   	 		System.out.println(r.getNw_proto());
   	 	}
	}*/
   public void testAdd(){
    	Rule rule=new Rule();
    	rule.setSrc_ip("10.0.0.44");
    	rule.setDst_ip("10.0.0.37");
    	rule.setNw_proto("TCP");
    	rule.setAction("deny");
    	rule.setTp_dst(7900);
    	String answer;
    	
    	answer=this.service.AddRule(rule,1,"0000000000000001",50);
   	 	System.out.println(answer);
   	 	
   	 	//{"src-ip":"10.0.0.4/32","dst-ip":"10.0.0.24/32","action":"deny"}
   }
   /* public void testDel(){
    	Rule rule=new Rule();
    	rule.setRuleid(12);
    	String answer=this.service.DeleteRule(rule.getRuleid(),1,"0000000000000001");
    	System.out.println(answer);
    }*/
}
