/**
 * 
 */
package cn.dlut.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.ControllerDao;
import cn.dlut.dao.FlowDao;
import cn.dlut.dao.LinkDao;
import cn.dlut.dao.PortDao;
import cn.dlut.dao.PortStatsDao;
import cn.dlut.dao.SwitchDao;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.DelByCtrlIdService;


@Service("delByCtrlIdService")
public class DelByCtrlIdServiceImpl extends AbstractBaseService implements DelByCtrlIdService {
	
	@Autowired
	private SwitchDao switchdao;
	@Autowired
	private FlowDao flowdao;
	@Autowired
	private PortDao portdao;
	@Autowired
	private PortStatsDao portStatsDao;
	@Autowired
	private LinkDao linkdao;
	@Autowired
	private ControllerDao controllerdao;
	 
	public void delByCtrlId(int ctrl_id) {
		this.linkdao.delByCtrlId(ctrl_id);
		this.flowdao.delByCtrlId(ctrl_id);
		this.portdao.delByCtrlId(ctrl_id);
		this.portStatsDao.delByCtrlId(ctrl_id);
		this.switchdao.delByCtrlId(ctrl_id);
		this.controllerdao.delById(ctrl_id);
		
	}

	

}
