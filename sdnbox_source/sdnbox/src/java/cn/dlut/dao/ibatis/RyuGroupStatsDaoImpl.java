package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.RyuGroupStatsDao;
import cn.dlut.entity.RyuGroupStats;

import com.plato.common.dao.ibatis.IBatisEntityDao;


@Repository("ryuGroupStatsDao")
public class RyuGroupStatsDaoImpl extends IBatisEntityDao<RyuGroupStats> implements RyuGroupStatsDao{

	static Logger logger = Logger.getLogger(RyuGroupStatsDaoImpl.class.getName());

	//TODO
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RyuGroupStats> getAll() {
		return (List<RyuGroupStats>)getSqlMapClientTemplate().queryForList("RyuGroupStats.getAll");
	}

	@Override
	public RyuGroupStats getById(int id) {
		return (RyuGroupStats)getSqlMapClientTemplate().queryForObject(
				"RyuGroupStats.getById", id);
	}

	@Override
	public int insert(RyuGroupStats g) {
		getSqlMapClientTemplate().insert("RyuGroupStats.insert", g);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("RyuGroupStats.delById", id);
	}

	@Override
	public int delByCtrl_id(int ctrl_id) {
		return getSqlMapClientTemplate().delete("RyuGroupStats.delByCtrl_id",ctrl_id);
	}

	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("RyuGroupStats.delAll");
	}

}
