package cn.dlut.dao.ibatis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import cn.dlut.dao.HostDao;
import cn.dlut.entity.Host;
import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("hostDao")
public class HostDaoImpl extends IBatisEntityDao<Host> implements HostDao{
	
	static Logger logger = Logger.getLogger(HostDaoImpl.class.getName());
	
	// TODO
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Host> getAll(){
		return ((List<Host>) getSqlMapClientTemplate().queryForList("Host.getAll"));
	}
	
	@Override
	public Host getById(int id) {
		return (Host) getSqlMapClientTemplate().queryForList(
				"Host.getById", id);
	}

	@Override
	public int insert(Host h) {
		getSqlMapClientTemplate().insert("Host.insert", h);
		return 0;
	}

	@Override
	public int delById(int id) {
		getSqlMapClientTemplate().delete("Host.delById", id);
		return 0;
	}
	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("Host.delAll");
	}

}
