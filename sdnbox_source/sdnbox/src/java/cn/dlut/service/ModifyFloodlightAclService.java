package cn.dlut.service;

import cn.dlut.entity.Rule;

public interface ModifyFloodlightAclService {
	
	public String writeRule(Rule rule, int controller_id);

	public String delRule(int Rule_id, int controller_id);
	
	public String queryRule(int controller_id);
}
