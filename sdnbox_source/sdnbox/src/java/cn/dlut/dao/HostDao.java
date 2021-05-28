/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import cn.dlut.entity.Host;

public interface HostDao extends IBaseDao<Object> {

	public List<Host> getAll();

	public Host getById(int id);
	
	public int insert(Host h);
	
	public int delById(int id);
	
	public int delAll();

}
