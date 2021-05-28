/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import cn.dlut.dao.ControllerDao;
import cn.dlut.entity.Controller;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("controllerDao")
public class ControllerDaoImpl extends IBatisEntityDao<Controller> implements
		ControllerDao {
	static Logger logger = Logger.getLogger(ControllerDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Controller> getAll() {
		return (List<Controller>) getSqlMapClientTemplate().queryForList(
				"Controller.getAll");
	}

	public Controller getById(int id) {
		return (Controller) getSqlMapClientTemplate().queryForList(
				"Controller.getById", id);
	}
	
	public int insert(Controller c) {
		 getSqlMapClientTemplate().insert("Controller.insert", c);
		return 0;
	}

	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Controller.delById",id);
	}
	
	@Override
	public int updateRset_URL(Controller c) {
		getSqlMapClientTemplate().update("Controller.updateRset_URL", c);
		return 0;
	}
	
	public int getIdByType(String type){
		return (Integer)getSqlMapClientTemplate().queryForObject("Controller.getIdByType", type);
	}
	
	public Integer getIdByRestUrl(String rest_url){
		return (Integer)getSqlMapClientTemplate().queryForObject("Controller.getIdByRestUrl", rest_url);
	}
}
