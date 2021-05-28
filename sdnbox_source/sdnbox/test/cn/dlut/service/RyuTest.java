package cn.dlut.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.dlut.base.BaseTest;
import cn.dlut.entity.Rule;
import cn.dlut.service.AclRuleService;
import cn.dlut.util.JsonUtil;


public class RyuTest extends BaseTest {
	private AclRuleService  service; 
	protected void setUp() throws Exception {
        super.setUp();
        service = (AclRuleService)this.ctx.getBean("AclRuleService");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        service = null ;
    }
    public void testQuery(){
   	    List<Rule> rules;
   	 	rules=this.service.QueryRule("0000000000000002", 0,50);
   	 	if(rules.isEmpty())
   	 		System.out.println("rules null");
   	 	else
   	 	{
	   	 	for(Rule r:rules)
	   	 	{
	   	 		System.out.println(r.getRuleid());
	   	 		System.out.println(r.getAction());
	   	 		System.out.println(r.getSrc_ip());
	   	 		System.out.println(r.getDst_ip());
	   	 		System.out.println(r.getTp_dst());
	   	 		System.out.println(r.getNw_proto());
	   	 	}
   	 	}
	}
    
/*  public void testAdd(){
    	Rule rule=new Rule();
    	rule.setSrc_ip("10.0.0.1/32");
    	rule.setDst_ip("10.0.0.9/32");
    	rule.setNw_proto("TCP");
    	rule.setAction("DENY");
    	rule.setTp_dst(7900);
    	String answer;
    	answer=this.service.AddRule(rule,0,"0000000000000001");
   	 	System.out.println(answer);
    }*/
   /* public void testDel(){
    	Rule rule=new Rule();
    	rule.setRuleid(1);
    	String answer=this.service.DeleteRule(rule,0,"0000000000000001");
    	System.out.println(answer);
    }*/
    
    public void testAddSpecialRule()
    {
    	
    	
    }
    
    
    public List<Rule> writeRule(String ruleString){
		List<Rule> ruleList=new ArrayList<Rule>();
		
		Map<String,Object> ruleMap=new HashMap<String, Object>();
		//analyze the first layer of rules 
		Object[] objects;
		objects= JsonUtil.getObjectArray4Json(ruleString);
		Map<String,Object> map1=new HashMap<String, Object>();
		
		map1=JsonUtil.getMap4Json(objects[0].toString());
		
		//analyze the second layer of rules
		Object[] objects2;
		objects2=JsonUtil.getObjectArray4Json(map1.get("access_control_list").toString());
		Map<String,Object> map2=new HashMap<String, Object>();
		map2=JsonUtil.getMap4Json(objects2[0].toString());
		
		//analyze the third layer of rules
		Object[] objects3;
		if(map2.get("rules")==null)
		{
			System.out.println("rules null");
			return null;
		}
		objects3=JsonUtil.getObjectArray4Json(map2.get("rules").toString());
		int j;
		
		for(j=0;j<objects3.length;j++)
		{
			ruleMap=JsonUtil.getMap4Json(objects3[j].toString());
			Rule rule =new Rule();
			rule.setRuleid(Integer.parseInt(ruleMap.get("rule_id").toString()));
			rule.setAction(ruleMap.get("actions").toString());
			if(ruleMap.get("nw_proto")!=null)
			{
				rule.setNw_proto(ruleMap.get("nw_proto").toString());
			}
			else
				rule.setNw_proto(null);
			if(ruleMap.get("nw_src")!=null)
			{
				rule.setSrc_ip(ruleMap.get("nw_src").toString());
			}
			else
				rule.setSrc_ip(null);
			//System.out.println(rule.getSrc_ip());
			if(ruleMap.get("nw_dst")!=null)
			{
				rule.setDst_ip(ruleMap.get("nw_dst").toString());
			}
			else
				rule.setDst_ip(null);
			if(ruleMap.get("tp_dst")!=null)
			{
				rule.setTp_dst(Integer.parseInt(ruleMap.get("tp_dst").toString()));
			}
			else
				rule.setTp_dst(0);
			
			ruleList.add(rule);
		}
		
		return ruleList;
    }
}
