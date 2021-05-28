/**
 * 
 */
package cn.dlut.dao;

import java.util.List;
import java.util.Map;

import cn.dlut.entity.PortStats;

public interface PortStatsDao extends IBaseDao<Object> {

	public List<PortStats> getAll();
	
	public List<PortStats> getByDpId(Map<?, ?> maptmp);
	
	public PortStats getById(int id);
	
	public int getId(Map<?, ?> maptmp);
	
	public int insert(PortStats p);
	
	public int delById(int id);
	
	public int delByCtrlId(int ctrl_id);
	
	public int delAll();
	
}
