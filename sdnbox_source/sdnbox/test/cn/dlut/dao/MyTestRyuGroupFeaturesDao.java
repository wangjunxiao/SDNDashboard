package cn.dlut.dao;

import java.util.Map;

import cn.dlut.base.BaseTest;
import cn.dlut.dao.RyuGroupFeaturesDao;
import cn.dlut.entity.RyuGroupFeatures;
import cn.dlut.util.JsonUtil;
public class MyTestRyuGroupFeaturesDao extends BaseTest{

	
private RyuGroupFeaturesDao dao;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		dao=(RyuGroupFeaturesDao)this.ctx.getBean("ryuGroupFeaturesDao");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		dao=null;
	}
	
	public void testGet()
	{
		String str="{\"1\": [{\"types\": [],\"capabilities\": [\"SELECT_WEIGHT\",\"SELECT_LIVENESS\",\"CHAINING\"]," +
				"\"max_groups\": [{\"ALL\": 4294967040},{\"SELECT\": 4294967040},{\"INDIRECT\": 4294967040},{\"FF\": 4294967040}],\"actions\": [{\"ALL\": [\"OUTPUT\",\"COPY_TTL_OUT\",\"COPY_TTL_IN\",\"SET_MPLS_TTL\",\"DEC_MPLS_TTL\",\"PUSH_VLAN\",\"POP_VLAN\",\"PUSH_MPLS\",\"POP_MPLS\",\"SET_QUEUE\",\"GROUP\",\"SET_NW_TTL\",\"DEC_NW_TTL\",\"SET_FIELD\"]},{\"SELECT\": []},{\"INDIRECT\": []},{\"FF\": []}]}]}";
		
		Map<?, ?> map1=JsonUtil.getMap4Json(str);
		Object[] ob=JsonUtil.getObjectArray4Json(map1.get(1+"").toString());
		for(int i=0;i<ob.length;i++)
		{
			Map<?, ?> map2=JsonUtil.getMap4Json(ob[i].toString());
			RyuGroupFeatures p=new RyuGroupFeatures();
			p.setSwitch_id(1);
			p.setCtrl_id(3);
			p.setTypes(map2.get("types").toString());
			p.setActions(map2.get("actions").toString());
			p.setCapabilities(map2.get("capabilities").toString());
			p.setMax_groups(map2.get("max_groups").toString());
			p.setCtime(new java.util.Date());
			
			try{
				dao.insert(p);
			}catch(Exception e){
				System.out.print("insert ryugroupfeatures into DB error！\n");
				return;
			}
		}
	}
	
}
