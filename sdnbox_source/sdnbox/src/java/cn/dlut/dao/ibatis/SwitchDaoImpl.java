/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.SwitchDao;
import cn.dlut.entity.Switch;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("switchDao")
public class SwitchDaoImpl extends IBatisEntityDao<Switch> implements SwitchDao {
	static Logger logger = Logger.getLogger(SwitchDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Switch> getAll() {
		return (List<Switch>) getSqlMapClientTemplate().queryForList(
				"Switch.getAll");
	}

	@SuppressWarnings("unchecked")
	public List<Switch> getByControllerId(int ctrl_id) {
		return (List<Switch>) getSqlMapClientTemplate().queryForList("Switch.getByControllerId",ctrl_id);
	
	}

	public Switch getById(int id) {
		return (Switch) getSqlMapClientTemplate().queryForList(
				"Switch.getById", id);
	}

	public int getId(Map<?, ?> para){
		return (Integer) getSqlMapClientTemplate().queryForObject("Switch.getId", para);
	}
	
	public int insert(Switch c) {
		getSqlMapClientTemplate().insert("Switch.insert", c);
		return 0;
	}

	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Switch.delById", id);
	}

	public int delByCtrlId(int ctrl_id) {
		return getSqlMapClientTemplate().delete("Switch.delByCtrlId", ctrl_id);
	}
	
	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("Switch.delAll");
	}

}
