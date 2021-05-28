package cn.dlut.service;
import java.util.List;

import cn.dlut.entity.Rule;

public interface AclRuleService {
	//add ACL rules;
	public String AddRule(Rule rule,int controllerType,String switch_id,int controller_id);
	
	//delete ACL rules;
	public String DeleteRule(int ruleid,int controllerType,String switch_id,int controller_id);
	
	//query ACL rules;
	public List<Rule> QueryRule(String switch_id,int controllerType,int controller_id);
	
	//add Special rules;
	public String AddSpecialRule(String switch_id,int controller_id);

}
