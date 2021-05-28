package cn.dlut.dao;

import java.util.List;

import cn.dlut.entity.RyuMeterFeatures;

public interface RyuMeterFeaturesDao extends IBaseDao<Object>{

	public List<RyuMeterFeatures> getAll();

	public RyuMeterFeatures getById(int id);

	public int insert(RyuMeterFeatures p);

	public int delById(int id);
	
	public int delByCtrl_id(int ctrl_id);

	public int delAll();
}
