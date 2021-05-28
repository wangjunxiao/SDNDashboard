package cn.dlut.dao;

import java.util.Map;

import cn.dlut.base.BaseTest;
import cn.dlut.dao.RyuGroupStatsDao;
import cn.dlut.entity.RyuGroupStats;
import cn.dlut.util.AppProperties;
import cn.dlut.util.HttpRequest;
import cn.dlut.util.JsonUtil;

public class MyTestRyuGroupStatsDao extends BaseTest{

	private RyuGroupStatsDao dao;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		dao=(RyuGroupStatsDao)this.ctx.getBean("ryuGroupStatsDao");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		dao=null;
	}
	
	public void testGet()
	{
		String str;
		try{
		str= HttpRequest.readContentFromGet(AppProperties.Ryu_preurl()+AppProperties.RyuGroup()+1);
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
/*		String str="{\"1\":[{\"length\": 56,\"group_id\": 1,\"ref_count\": 1,\"packet_count\": 0,
 * \"byte_count\": 0,\"duration_sec\": 161,\"duration_nsec\": 3.03e+08,\"bucket_stats\": [{\"packet_count\": 0,\"byte_count\": 0}]}]}";
 */
		Map<?, ?> map1=JsonUtil.getMap4Json(str);
		Object[] ob=JsonUtil.getObjectArray4Json(map1.get(1+"").toString());
		for(int i=0;i<ob.length;i++)
		{
			Map<?, ?> map2=JsonUtil.getMap4Json(ob[i].toString());
			RyuGroupStats p=new RyuGroupStats();
			p.setSwitch_id(1);
			p.setCtrl_id(3);
			p.setLength(Integer.parseInt(map2.get("length").toString()));
			p.setRef_count(Integer.parseInt(map2.get("ref_count").toString()));
			p.setPacket_count(Integer.parseInt(map2.get("packet_count").toString()));
			p.setByte_count(Integer.parseInt(map2.get("byte_count").toString()));
			p.setDuration_sec(Integer.parseInt(map2.get("duration_sec").toString()));
			p.setDuration_nsec(map2.get("duration_nsec").toString());
			p.setGroup_id(Integer.parseInt(map2.get("group_id").toString()));
			p.setBucket_stats(map2.get("bucket_stats").toString());
			p.setCtime(new java.util.Date());
			
			try{
				dao.insert(p);
			}catch(Exception e){
				System.out.println("insert ryugroupstats into DB error!");
				return;
			}
		}
	}
	
}
