package cn.dlut.dao.ibatis;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.dlut.dao.PortStatsDao;
import cn.dlut.entity.PortStats;

import com.plato.common.dao.ibatis.IBatisEntityDao;

@Repository("portStatsDao")
public class PortStatsDaoImpl extends IBatisEntityDao<PortStats> implements PortStatsDao {

	static Logger logger = Logger.getLogger(PortStatsDaoImpl.class.getName());

	@SuppressWarnings("unchecked")
	public List<PortStats> getAll() {
		return (List<PortStats>) getSqlMapClientTemplate().queryForList(
				"PortStats.getAll");
	}
	
	@SuppressWarnings("all")
	public List getByDpId(Map maptmp) {
		List<Map> result  = new ArrayList<Map>();
		List list = getSqlMapClientTemplate().queryForList(
				"PortStats.getBySwitchId", maptmp);
		int portNo = 0;
		long sentinal = -1;
		for(int i=0;i<list.size();i++){
			Map middleData = (Map) list.get(i);
			long port_no = (Long) middleData.get("port_no");
			if(i==0){
				sentinal = port_no;
				portNo++;
			}
			else if(port_no!=sentinal) 	portNo++;
			else break;
		}
		
		for(int i=0;i<list.size();i++){
			Map middleData = (Map) list.get(i);
			int index = 0;
			Map bufferData = new HashMap();
			Date time = (Date) middleData.get("update_time");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);
			String time1 = calendar.get(Calendar.HOUR_OF_DAY) + ":"
			+ calendar.get(Calendar.MINUTE);
			while(index<portNo){
				int rcv_bytes = (Integer) middleData.get("rcv_bytes");
				int trsm_bytes = (Integer) middleData.get("trsm_bytes");
				long port_no = (Long) middleData.get("port_no");
				long bytes = (rcv_bytes + trsm_bytes)/(8*1024);
				bufferData.put("port"+port_no, bytes);
				if(i+1<list.size()) middleData = (Map) list.get(++i);
				else i++;
				index++;
			}
			bufferData.put("time", time1);
			result.add(bufferData);
			i--;
		}
		
		return result;
	}
	
	public PortStats getById(int id) {
		return (PortStats) getSqlMapClientTemplate().queryForObject(
				"PortStats.getById", id);
	}

	public int insert(PortStats p) {
		getSqlMapClientTemplate().insert("PortStats.insert", p);
		return 0;
	}

	public int delById(int id) {
		return getSqlMapClientTemplate().delete("PortStats.delById", id);
	}
	
	public int delByCtrlId(int ctrl_id) {
		return getSqlMapClientTemplate().delete("PortStats.delByCtrlId", ctrl_id);
	}

	public int getId(Map<?, ?> maptmp) {
		return (Integer)getSqlMapClientTemplate().queryForObject("PortStats.getId", maptmp);
	}

	@Override
	public int delAll() {
		return getSqlMapClientTemplate().delete("PortStats.delAll");
	}

}
