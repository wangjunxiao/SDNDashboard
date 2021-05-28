package cn.dlut.dao;

import java.util.Map;

import cn.dlut.base.BaseTest;
import cn.dlut.dao.RyuGroupDescDao;
import cn.dlut.entity.RyuGroupDesc;
import cn.dlut.util.JsonUtil;

public class MyTestRyuGroupDescDao extends BaseTest{
	
	private RyuGroupDescDao dao;
	protected void setUp() throws Exception {
		super.setUp();
		dao=(RyuGroupDescDao)this.ctx.getBean("ryuGroupDescDao");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		dao=null;
	}
	
	public void testGet(){
		String str="{\"1\": [{\"type\": \"ALL\",\"group_id\": 1,\"buckets\": [{\"weight\": " +
				"0,\"watch_port\": 4294967295,\"watch_group\": 4294967295,\"actions\": [\"OUTPUT:1\"]}]}]}";
		Map<?, ?> map1=JsonUtil.getMap4Json(str);
		Object[] ob=JsonUtil.getObjectArray4Json(map1.get(1+"").toString());
		for(int i=0;i<ob.length;i++)
		{
			
			Map<?, ?> map2=JsonUtil.getMap4Json(ob[i].toString());
			RyuGroupDesc p=new RyuGroupDesc();
			p.setSwitch_id(1);
			p.setGroup_id(Integer.parseInt(map2.get("group_id").toString()));
			p.setType(map2.get("type").toString());
			p.setCtrl_id(3);
			p.setBuckets(map2.get("buckets").toString());
			p.setCtime(new java.util.Date());
			
			try{
				dao.insert(p);
			}catch(Exception e){
				System.out.print("insert ryugroupdesc into DB error\n");
				return;
			}
		}
	}

}
