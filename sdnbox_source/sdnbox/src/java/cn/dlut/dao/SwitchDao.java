/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import java.util.Map;

import cn.dlut.entity.Switch;

public interface SwitchDao extends IBaseDao<Object> {

	public List<Switch> getAll();

	public Switch getById(int id);
	
	public List<Switch> getByControllerId(int ctrl_id);

	public int getId(Map<?, ?> para);

	public int insert(Switch s);

	public int delById(int id);
	
	public int delByCtrlId(int ctrl_id);
	
	public int delAll();

}
