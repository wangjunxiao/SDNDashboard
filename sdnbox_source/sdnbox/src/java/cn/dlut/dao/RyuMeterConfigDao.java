package cn.dlut.dao;

import java.util.List;

import cn.dlut.entity.RyuMeterConfig;

public interface RyuMeterConfigDao extends IBaseDao<Object>{

	public List<RyuMeterConfig> getAll();

	public RyuMeterConfig getById(int id);

	public int insert(RyuMeterConfig p);

	public int delById(int id);
	
	public int delByCtrl_id(int ctrl_id);

	public int delAll();
}
