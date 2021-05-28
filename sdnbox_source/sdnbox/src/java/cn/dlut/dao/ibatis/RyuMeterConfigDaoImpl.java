package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.RyuMeterConfigDao;
import cn.dlut.entity.RyuMeterConfig;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("ryuMeterConfigDao")
public class RyuMeterConfigDaoImpl extends IBatisEntityDao<RyuMeterConfig> implements RyuMeterConfigDao{

	
	static Logger logger = Logger.getLogger(RyuMeterConfigDaoImpl.class.getName());
	
	//TODO
	
	@Override
	public RyuMeterConfig getById(int id) {
		return (RyuMeterConfig)getSqlMapClientTemplate().queryForObject("RyuMeterConfig.getById", id);
	}

	@Override
	public int insert(RyuMeterConfig p) {
		getSqlMapClientTemplate().insert("RyuMeterConfig.insert",p);
		return 0;
	}

	@Override
	public int delById(int id) {
		getSqlMapClientTemplate().delete("RyuMeterConfig.delById", id);
		return 0;
	}

	@Override
	public int delAll() {
		getSqlMapClientTemplate().delete("RyuMeterConfig.delAll");
		return 0;
	}
	
	@SuppressWarnings("unchecked")
	public List<RyuMeterConfig> getAll(){
		return (List<RyuMeterConfig>)getSqlMapClientTemplate().queryForList("RyuMeterConfig.getAll");
	}

	@Override
	public int delByCtrl_id(int ctrl_id) {
		getSqlMapClientTemplate().delete("RyuMeterConfig.delByCtrl_id",ctrl_id);
		return 0;
	}

}
