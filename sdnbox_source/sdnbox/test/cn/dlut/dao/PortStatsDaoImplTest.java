package cn.dlut.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import cn.dlut.base.BaseTest;

public class PortStatsDaoImplTest extends BaseTest {
	private PortStatsDao portDAO;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		portDAO = (PortStatsDao) this.ctx.getBean("portStatsDao");
	}

	@Test
	public void testGetByDpId() {
		Map<String, Object> paramter = new HashMap<String, Object>();
		paramter.put("dp_id","00:00:00:00:00:00:00:02");
		paramter.put("interval", "30");
		List<?> list = portDAO.getByDpId(paramter);
		for (int i = 0; i < list.size(); i++) {
			Map<?, ?> map = (Map<?, ?>) list.get(i);
			System.out.println(map.toString());
			/*long port_no = (Long) map.get("port_no");
			Date date = (Date) map.get("time");
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			System.out.println(c1.get(Calendar.HOUR_OF_DAY) + ":"
					+ c1.get(Calendar.MINUTE));
			int bytes = (Integer) map.get("bytes");
			System.out.println("The bytes of port " + port_no + " is " + bytes);*/
		}
	}

}
