package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.RyuMeterFeaturesDao;
import cn.dlut.entity.RyuMeterFeatures;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("ryuMeterFeaturesDao")
public class RyuMeterFeaturesDaoImpl extends IBatisEntityDao<RyuMeterFeatures> implements RyuMeterFeaturesDao{

	
	static Logger logger = Logger.getLogger(RyuMeterFeaturesDaoImpl.class.getName());
	
	//TODO
	
	@Override
	public RyuMeterFeatures getById(int id) {
		return (RyuMeterFeatures)getSqlMapClientTemplate().queryForObject("RyuMeterFeatures.getById", id);
	}

	@Override
	public int insert(RyuMeterFeatures p) {
		getSqlMapClientTemplate().insert("RyuMeterFeatures.insert", p);
		return 0;
	}

	@Override
	public int delById(int id) {
		getSqlMapClientTemplate().delete("RyuMeterFeatures.delById",id);
		return 0;
	}

	@Override
	public int delAll() {
		getSqlMapClientTemplate().delete("RyuMeterFeatures.delAll");
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<RyuMeterFeatures> getAll(){
		return (List<RyuMeterFeatures>)getSqlMapClientTemplate().queryForList("RyuMeterFeatures.getAll");
	}

	@Override
	public int delByCtrl_id(int ctrl_id) {
		getSqlMapClientTemplate().delete("RyuMeterFeatures.delByCtrl_id", ctrl_id);
		return 0;
	}
}
