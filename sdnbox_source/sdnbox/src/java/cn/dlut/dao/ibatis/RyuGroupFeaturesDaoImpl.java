package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.RyuGroupFeaturesDao;
import cn.dlut.entity.RyuGroupFeatures;

import com.plato.common.dao.ibatis.IBatisEntityDao;


@Repository("ryuGroupFeaturesDao")
public class RyuGroupFeaturesDaoImpl extends IBatisEntityDao<RyuGroupFeatures> implements RyuGroupFeaturesDao{

	
	static Logger logger = Logger.getLogger(RyuGroupFeaturesDaoImpl.class.getName());
	
	//TODO
	
	@Override
	public RyuGroupFeatures getById(int id) {
		return (RyuGroupFeatures)getSqlMapClientTemplate().queryForObject("RyuGroupFeatures.getById", id);
	}

	@Override
	public int insert(RyuGroupFeatures g) {
		getSqlMapClientTemplate().insert("RyuGroupFeatures.insert",g);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("RyuGroupFeatures.delById", id);
		
	}

	@Override
	public int delByCtrl_id(int ctrl_id) {
		return getSqlMapClientTemplate().delete("RyuGroupFeatures.delByCtrl_id", ctrl_id);
	}

	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("RyuGroupFeatures.delAll");
	}
	
	@SuppressWarnings("unchecked")
	public List<RyuGroupFeatures> getAll(){
		return (List<RyuGroupFeatures>)getSqlMapClientTemplate().queryForList("RyuGroupFeatures.getAll");
	}

}
