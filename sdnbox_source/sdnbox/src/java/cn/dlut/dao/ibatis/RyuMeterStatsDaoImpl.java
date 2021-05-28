package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.RyuMeterStatsDao;
import cn.dlut.entity.RyuMeterStats;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("ryuMeterStatsDao")

public class RyuMeterStatsDaoImpl extends IBatisEntityDao<RyuMeterStats> implements RyuMeterStatsDao{

	static Logger logger = Logger.getLogger(RyuMeterStatsDaoImpl.class.getName());
	
	//TODO
	
	@Override
	public RyuMeterStats getById(int id) {
		return (RyuMeterStats)getSqlMapClientTemplate().queryForObject("RyuMeterStats.getById", id);
	}

	@Override
	public int insert(RyuMeterStats p) {
		getSqlMapClientTemplate().insert("RyuMeterStats.insert", p);
		return 0;
	}

	@Override
	public int delById(int id) {
		getSqlMapClientTemplate().delete("RyuMeterStats.delById", id);
		return 0;
	}

	@Override
	public int delAll() {
		getSqlMapClientTemplate().delete("RyuMeterStats.delAll");
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<RyuMeterStats> getAll(){
		return (List<RyuMeterStats>)getSqlMapClientTemplate().queryForList("RyuMeterStats.getAll");
	}

	@Override
	public int delByCtrl_id(int ctrl_id) {
		getSqlMapClientTemplate().delete("RyuMeterStats.delByCtrl_id", ctrl_id);
		return 0;
	}
}
