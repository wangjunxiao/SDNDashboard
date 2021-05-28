package cn.dlut.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.AclRuleService;
import cn.dlut.util.JsonUtil;
import cn.dlut.entity.Rule;

@Service("AclRuleService")
public class AclRuleServiceImpl extends AbstractBaseService implements AclRuleService {
	
	//add ACL rules;
	public String AddRule(Rule rule,int controllerType,String switch_id,int controller_id){
		if (controllerType == 1)
		{
			//add IP mask, only support 32-bit mask
			if(!rule.getSrc_ip().equals(""))
				rule.setSrc_ip(rule.getSrc_ip()+"/32");
			if(!rule.getDst_ip().equals(""))
				rule.setDst_ip(rule.getDst_ip()+"/32");
			
			
			ModifyFloodlightAclServiceImpl floodlight = new ModifyFloodlightAclServiceImpl();
			return floodlight.writeRule(rule,controller_id);
		}
		else if (controllerType == 0)
		{
			//set priority of ryu controller rule and dl_type
			rule.setPriority(65533);
			rule.setDl_type("IPv4");
			ModifyRyuFireWallRuleServiceImpl Ryu = new ModifyRyuFireWallRuleServiceImpl();
			return Ryu.writeRule(rule,switch_id, controller_id);
		}
		else{
			return "";
		}
	}
	
	//delete ACL rules;
	public String DeleteRule(int ruleid,int controllerType,String switch_id, int controller_id){
		if (controllerType == 1)
		{
			ModifyFloodlightAclServiceImpl floodlight = new ModifyFloodlightAclServiceImpl();
			return floodlight.delRule(ruleid, controller_id);
		}
		else if (controllerType == 0)
		{
			ModifyRyuFireWallRuleServiceImpl Ryu = new ModifyRyuFireWallRuleServiceImpl();
			return Ryu.delRule(ruleid,switch_id,controller_id);
		}
		else{
			return "";
		}
	}
	//enable ryu firewall
	public String AddSpecialRule(String switch_id, int controller_id){
		Rule rule=new Rule();
		rule.setPriority(1);
		rule.setDl_type("IPv4");
		rule.setAction("ALLOW");
		ModifyRyuFireWallRuleServiceImpl Ryu = new ModifyRyuFireWallRuleServiceImpl();
		return Ryu.writeRule(rule,switch_id,controller_id);
	}
	
	//query ACL rules;
	public List<Rule> QueryRule(String switch_id,int controllerType, int controller_id){
		if (controllerType == 1)
		{
			ModifyFloodlightAclServiceImpl floodlight = new ModifyFloodlightAclServiceImpl();
			
			return this.FlwriteRule(floodlight.queryRule(controller_id));
		}
		else if (controllerType == 0)
		{
			ModifyRyuFireWallRuleServiceImpl Ryu = new ModifyRyuFireWallRuleServiceImpl();
			
			return  this.RyuwriteRule(Ryu.queryRule(switch_id, controller_id));
		}
		else{
			return null;
		}
	}
	
	//Ryu change json to object
    public List<Rule> RyuwriteRule(String ruleString){
		List<Rule> ruleList=new ArrayList<Rule>();
		
		if(ruleString.equals(null))
		{
			return null;
		}
		Map<String,Object> ruleMap=new HashMap<String, Object>();
		//resolve rule first layer
		Object[] objects;
		objects= JsonUtil.getObjectArray4Json(ruleString);
		Map<String,Object> map1=new HashMap<String, Object>();
		map1=JsonUtil.getMap4Json(objects[0].toString());
		
		//resolve rule second layer of access_control_list
		Object[] objects2;
		if(map1.get("access_control_list")==null)
		{
			System.out.println("rules null");
			return null;
		}
		objects2=JsonUtil.getObjectArray4Json(map1.get("access_control_list").toString());
		Map<String,Object> map2=new HashMap<String, Object>();
		if(objects2.length==0)
		{
			System.out.println("rules null");
			return null;
		}
		map2=JsonUtil.getMap4Json(objects2[0].toString());
		
		//resolve rule third layer
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
    
    
  //Floodlight change json to object
    public List<Rule> FlwriteRule(String ruleString){
		List<Rule> ruleList=new ArrayList<Rule>();
		
		if(ruleString.equals(null))
		{
			return null;
		}
		
		Map<String,Object> ruleMap=new HashMap<String, Object>();
		//resolve rule first layer
		Object[] objects;
		objects= JsonUtil.getObjectArray4Json(ruleString);
	
		
		
		
		int j;
		for(j=0;j<objects.length;j++)
		{
			ruleMap=JsonUtil.getMap4Json(objects[j].toString());
			Rule rule =new Rule();
			rule.setRuleid(Integer.parseInt(ruleMap.get("id").toString()));
			rule.setAction(ruleMap.get("action").toString());
			if(ruleMap.get("nw_proto")!=null)
			{
				System.out.println(Integer.parseInt(ruleMap.get("nw_proto").toString()));
				if(Integer.parseInt(ruleMap.get("nw_proto").toString())==6)
				{
					rule.setNw_proto("TCP");
				}
				else if(Integer.parseInt(ruleMap.get("nw_proto").toString())==11)
				{
					rule.setNw_proto("UDP");
				}
				else
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
