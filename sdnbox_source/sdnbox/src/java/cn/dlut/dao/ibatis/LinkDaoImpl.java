/**
 * 
 */
package cn.dlut.dao.ibatis;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.LinkDao;
import cn.dlut.entity.Link;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("linkDao")
public class LinkDaoImpl extends IBatisEntityDao<Link> implements LinkDao {
	static Logger logger = Logger.getLogger(LinkDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<Link> getAll() {
		return (List<Link>) getSqlMapClientTemplate().queryForList(
				"Link.getAll");
	}
	
	public Link getById(int id) {
		return (Link) getSqlMapClientTemplate().queryForList(
				"Link.getById", id);
	}

	public int insert(Link f) {
		getSqlMapClientTemplate().insert("Link.insert", f);
		return 0;
	}

	public int delById(int id) {
		return getSqlMapClientTemplate().delete("Link.delById", id);
	}

	public int delByCtrlId(int ctrl_id) {
		return getSqlMapClientTemplate().delete("Link.delByCtrlId", ctrl_id);
	}
	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("Link.delAll");
	}

}
