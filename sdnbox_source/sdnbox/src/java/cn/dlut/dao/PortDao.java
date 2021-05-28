/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import java.util.Map;

import cn.dlut.entity.Port;

public interface PortDao extends IBaseDao<Object> {

	public List<Port> getAll();
	
	public List<Port> getBySwitchId(int switch_id);
	
	public Port getById(int id);
	
	public int getId(Map<?, ?> maptmp);
	
	public int insert(Port p);
	
	public int delById(int id);
	
	public int delByCtrlId(int ctrl_id);
	
	public int delAll();
	
}
