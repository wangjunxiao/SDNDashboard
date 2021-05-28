package cn.dlut.dao.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.dlut.dao.LineDao;
import cn.dlut.entity.Line;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("LineDao")
public class LineDaoImpl extends IBatisEntityDao<Line> implements LineDao{

	

	@SuppressWarnings("unchecked")
	public List<Line> getLineByVisible(int vi) {
		System.out.println("getLineByVisible===="+vi);
		return (List<Line>)getSqlMapClientTemplate().queryForList("Line.getLineByVisible",vi);
	}

	@SuppressWarnings("unchecked")
	public List<Line> getAvailableLine() {
		return (List<Line>)getSqlMapClientTemplate().queryForList("Line.getAvailableLine");
	}

	public Line getByLineName(String name) {
		return (Line)getSqlMapClientTemplate().queryForObject("Line.getByLineName",name);
	}

	public Line getByLineId(int id) {
		return (Line)getSqlMapClientTemplate().queryForObject("Line.getByLineID",id);
	}

	public Line getByLineChiName(String name) {
		return (Line)getSqlMapClientTemplate().queryForObject("Line.getByLineChiName",name);
	}

}


