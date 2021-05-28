/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.entity.Flow;

public interface FlowDao extends IBaseDao<Object> {

	public List<Flow> getAll();

	public Flow getById(int id);
	
	public int insert(Flow f);
	
	public int delById(int id);
	
	public int delByCtrlId(int ctrl_id);
	
	public int delAll();

}
