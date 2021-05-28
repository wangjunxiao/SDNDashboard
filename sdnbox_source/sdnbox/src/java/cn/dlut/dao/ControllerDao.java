/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.entity.Controller;

public interface ControllerDao extends IBaseDao<Object> {

	public List<Controller> getAll();

	public Controller getById(int id);
	
	public int insert(Controller c);
	
	public int delById(int id);
	
	public int updateRset_URL(Controller c);
	
	public int getIdByType(String type);
	
	public Integer getIdByRestUrl(String rest_url);
	
}
