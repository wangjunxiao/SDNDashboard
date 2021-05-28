/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.FlowDao;
import cn.dlut.entity.Flow;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("flowDao")
public class FlowDaoImpl extends IBatisEntityDao<Flow> implements FlowDao {
	static Logger logger = Logger.getLogger(FlowDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Flow> getAll() {
		return (List<Flow>) getSqlMapClientTemplate().queryForList(
				"Flow.getAll");
	}

	public Flow getById(int id) {
		return (Flow) getSqlMapClientTemplate().queryForList(
				"Flow.getById", id);
	}

	public int insert(Flow f) {
		getSqlMapClientTemplate().insert("Flow.insert", f);
		return 0;
	}

	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Flow.delById", id);
	}

	public int delByCtrlId(int ctrl_id) {
		return getSqlMapClientTemplate().delete("Flow.delByCtrlId", ctrl_id);
	}
	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("Flow.delAll");
	}

}
