/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.PortDao;
import cn.dlut.entity.Port;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("portDao")
public class PortDaoImpl extends IBatisEntityDao<Port> implements PortDao {
	static Logger logger = Logger.getLogger(PortDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Port> getAll() {
		return (List<Port>) getSqlMapClientTemplate().queryForList(
				"Port.getAll");
	}
	
	@SuppressWarnings("unchecked")
	public List<Port> getBySwitchId(int switch_id) {
		return (List<Port>) getSqlMapClientTemplate().queryForList(
				"Port.getBySwitchId",switch_id);
	}
	
	
	public Port getById(int id) {
		return (Port) getSqlMapClientTemplate().queryForObject(
				"Port.getById", id);
	}

	public int insert(Port p) {
		getSqlMapClientTemplate().insert("Port.insert", p);
		return 0;
	}

	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Port.delById", id);
	}
	
	public int delByCtrlId(int ctrl_id) {
		return getSqlMapClientTemplate().delete("Port.delByCtrlId", ctrl_id);
	}

	public int getId(Map<?, ?> maptmp) {
		return (Integer)getSqlMapClientTemplate().queryForObject("Port.getId", maptmp);
	}

	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("Port.delAll");
	}
	

}
