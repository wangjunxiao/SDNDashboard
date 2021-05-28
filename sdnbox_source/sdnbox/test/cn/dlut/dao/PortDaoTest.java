package cn.dlut.dao;

import cn.dlut.base.BaseTest;


public class PortDaoTest extends BaseTest {
	private PortDao dao_port;
	@SuppressWarnings("unused")
	private SwitchDao dao_switch;

	protected void setUp() throws Exception {
		super.setUp();
		dao_port = (PortDao) this.ctx.getBean("portDao");
		dao_switch = (SwitchDao) this.ctx.getBean("switchDao");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dao_port = null;
		dao_switch = null;
	}

	public void testGet() {
		// List<Port> list = this.dao_port.getAll();
//		List<Port> list = this.dao_port.getById(1);
//		for (Port info : list) {
//			log.debug(info.toString());
//		}
		 this.dao_port.delByCtrlId(2);
		// Port s = new Port();
		// s.setCtime(new java.util.Date());
		// s.setSwitch_id(1);
		// this.dao_port.insert(s);

//		String info = "{\"result\": [{\"n_tables\": 255, \"ports\": [{\"hw_addr\": \"0a:90:8a:5f:e4:5f\", \"name\": \"s1-eth1\", \"port_no\": 1}, {\"hw_addr\": \"9e:ed:01:8f:37:3c\", \"name\": \"s1-eth2\", \"port_no\": 2}, {\"hw_addr\": \"0e:96:0e:ae:b4:4e\", \"name\": \"s1\", \"port_no\": 65534}], \"dpid\": \"00-00-00-00-00-01\"}, {\"n_tables\": 255, \"ports\": [{\"hw_addr\": \"16:40:67:79:c8:51\", \"name\": \"s2-eth1\", \"port_no\": 1}, {\"hw_addr\": \"ee:57:95:39:84:64\", \"name\": \"s2-eth2\", \"port_no\": 2}, {\"hw_addr\": \"e2:3f:51:2e:98:4f\", \"name\": \"s2\", \"port_no\": 65534}], \"dpid\": \"00-00-00-00-00-02\"}], \"id\": 1}";
//		Map map = JsonUtil.getMap4Json(info);
//		String[] result = JsonUtil.getStringArray4Json(map.get("result")
//				.toString());
//		for (int i = 0; i < result.length; i++) {
//			Map map2 = JsonUtil.getMap4Json(result[i]);
//			String[] ports = JsonUtil.getStringArray4Json(map2.get("ports")
//					.toString());
//			String dpid = map2.get("dpid").toString();
//			for (int j = 0; j < ports.length; j++) {
//				Map map3 = JsonUtil.getMap4Json(ports[j]);
//				Port port = new Port();
//				port.setMac_addr(map3.get("hw_addr").toString());
//				port.setPort_name(map3.get("name").toString());
//				port.setPort_no((Integer) map3.get("port_no"));
//
//				// get corresponding switch_id
//				Map para = new HashMap();
//				para.put("dp_id", dpid);
//				para.put("ctrl_id", "1");
//				int id = this.dao_switch.getId(para);
//
//				port.setSwitch_id(id);
//				port.setCtime(new Date());
//
//				this.dao_port.insert(port);
//
//				System.out.println(port);
//			}
//		}
		

//    	this.dao_port.delById(1);
//    	this.dao_port.delById(6);
//    	
    	int i;
    	for(i = 1;i < 12;i++)
    	{
    		this.dao_port.delById(i);
    	}
//    	
//    	for(i = 41;i < 50;i++)
//    	{
//    		this.dao_port.delById(i);
//    	}

	}

}
