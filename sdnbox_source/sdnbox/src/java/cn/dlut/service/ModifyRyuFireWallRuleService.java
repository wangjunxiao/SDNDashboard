package cn.dlut.service;

import cn.dlut.entity.Rule;

public interface ModifyRyuFireWallRuleService {
	
	public String writeRule(Rule rule,String switch_id, int controller_id);

	public String delRule(int Rule_id,String switch_id, int controller_id);
	
	public String queryRule(String switch_id, int controller_id);
}
