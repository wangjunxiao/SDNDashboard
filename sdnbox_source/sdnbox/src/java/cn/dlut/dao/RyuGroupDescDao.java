package cn.dlut.dao;

import java.util.List;

import cn.dlut.entity.RyuGroupDesc;

public interface RyuGroupDescDao extends IBaseDao<Object>{
	
	public List<RyuGroupDesc> getAll();

	public RyuGroupDesc getById(int id);

	public int insert(RyuGroupDesc p);

	public int delById(int id);
	
	public int delByCtrl_id(int ctrl_id);

	public int delAll();

}
