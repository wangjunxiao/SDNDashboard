package cn.dlut.dao;

import java.util.List;

import cn.dlut.entity.RyuGroupStats;

public interface RyuGroupStatsDao extends IBaseDao<Object>{
	
   public List<RyuGroupStats> getAll();
	
	public RyuGroupStats getById(int id);
	
	public int insert(RyuGroupStats g);
	
	public int delById(int id);
	
	public int delByCtrl_id(int ctrl_id);
	
	public int delAll();

}
