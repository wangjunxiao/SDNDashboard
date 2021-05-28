/**
 * 
 */
package cn.dlut.dao;

import java.util.List;

import cn.dlut.entity.Link;

public interface LinkDao extends IBaseDao<Object> {

	public List<Link> getAll();

	public Link getById(int id);
	
	public int insert(Link f);
	
	public int delById(int id);
	
	public int delByCtrlId(int ctrl_id);	
	
	public int delAll();

}
