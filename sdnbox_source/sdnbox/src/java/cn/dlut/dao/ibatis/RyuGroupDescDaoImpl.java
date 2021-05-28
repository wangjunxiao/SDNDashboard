package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.RyuGroupDescDao;
import cn.dlut.entity.RyuGroupDesc;

import com.plato.common.dao.ibatis.IBatisEntityDao;


@Repository("ryuGroupDescDao")
public class RyuGroupDescDaoImpl extends IBatisEntityDao<RyuGroupDesc> implements RyuGroupDescDao{

	
	static Logger logger = Logger.getLogger(RyuGroupDescDaoImpl.class.getName());
 
	//TODO
	
	@Override
	public RyuGroupDesc getById(int id) {
		return (RyuGroupDesc)getSqlMapClientTemplate().queryForObject(
				"RyuGroupDesc.getById", id);
	}

	@Override
	public int insert(RyuGroupDesc p) {
		getSqlMapClientTemplate().insert("RyuGroupDesc.insert", p);
		return 0;
	}

	@Override
	public int delById(int id) {
		return getSqlMapClientTemplate().delete("RyuGroupDesc.delById", id);
	}

	@Override
	public int delByCtrl_id(int ctrl_id) {
		return getSqlMapClientTemplate().delete("RyuGroupDesc.delByCtrl_id",ctrl_id);
	}

	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("RyuGroupDesc.delAll");
	}

	@SuppressWarnings("unchecked")
	public List<RyuGroupDesc> getAll()
	{
		return (List<RyuGroupDesc>)getSqlMapClientTemplate().queryForList("RyuGroupDesc.getAll");
	}
	
}
