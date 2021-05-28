package cn.dlut.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dlut.dao.ControllerDao;
import cn.dlut.dao.FlowDao;
import cn.dlut.dao.HostDao;
import cn.dlut.dao.LinkDao;
import cn.dlut.dao.PortDao;
import cn.dlut.dao.PortStatsDao;
import cn.dlut.dao.SwitchDao;
import cn.dlut.entity.Controller;
import cn.dlut.entity.Flow;
import cn.dlut.entity.Host;
import cn.dlut.entity.Link;
import cn.dlut.entity.Port;
import cn.dlut.entity.PortStats;
import cn.dlut.entity.Switch;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.DBInfoService;

@Service("DBInfoService")
public class DBInfoServiceImpl extends AbstractBaseService implements DBInfoService {
	@SuppressWarnings("unused")
	private static final int SWSMAXNUM = 40;
	@SuppressWarnings("unused")
	private static String STRCTRLID;
	@SuppressWarnings("unused")
	private SwitchDao daoSwitch;
	
	@Autowired
	private ControllerDao controllerDao;
	
	@Autowired
	private SwitchDao switchDao;
	
	@Autowired
	private FlowDao flowDao;
	
	@Autowired
	private PortDao portDao;
	
	@Autowired
	private HostDao hostDao;
	
	@Autowired
	private LinkDao linkDao;
	
	@Autowired
	private PortStatsDao portStatsDao;
	
	
	public ControllerDao getControllerDao() {
		return controllerDao;
	}
	
	public void setControllerDao(ControllerDao controllerDao){
		this.controllerDao=controllerDao;
	}
	
	public SwitchDao getSwitchDao() {
		return switchDao;
	}
	
	public void setSwitchDao(SwitchDao switchDao){
		this.switchDao=switchDao;
	}
	
	public FlowDao getFlowDao() {
		return flowDao;
	}
	
	public void setFlowDao(FlowDao flowDao){
		this.flowDao=flowDao;
	}
	
	public PortDao getPortDao() {
		return portDao;
	}
	
	public void setPortDao(PortDao portDao){
		this.portDao=portDao;
	}
	
	public void setPortStatsDao(PortStatsDao portStatsDao) {
		this.portStatsDao = portStatsDao;
	}

	public PortStatsDao getPortStatsDao() {
		return portStatsDao;
	}
	
	public HostDao getHostDao() {
		return hostDao;
	}
	
	public void setHostDao(HostDao hostDao){
		this.hostDao=hostDao;
	}
	
	public LinkDao getLinkDao() {
		return linkDao;
	}
	
	public void setLinkDao(LinkDao linkDao){
		this.linkDao=linkDao;
	}
	
	@Override
	public List<Controller> controller_getAll() {
		return controllerDao.getAll();
	}
	
	@Override
	public Controller controller_getById(int id) {
		return controllerDao.getById(id);
	}
	
	@Override
	public int controller_insert (Controller c) {
		return controllerDao.insert(c);
	}
	
	@Override
	public int controller_delById(int id) {
		return controllerDao.delById(id);
	}
	
	@Override
	public List<Switch> switch_getAll() {
		return switchDao.getAll();
	}
	
	@Override
	public Switch switch_getById(int id) {
		return switchDao.getById(id);
	}
	
	@Override
	public int switch_getId(@SuppressWarnings("rawtypes") Map para){
		return switchDao.getId(para);
	}

	@Override
	public List<Switch> getByControllerId(int ctrl_id) {
		return (List<Switch>) switchDao.getByControllerId(ctrl_id);
	}
	
	@Override
	public int switch_insert (Switch c) {
		return switchDao.insert(c);
	}
	
	@Override
	public int switch_delById(int id) {
		return switchDao.delById(id);
	}
	
	@Override
	public int switch_delAll() {
		return switchDao.delAll();
	}
	
	@Override
	public List<Flow> flow_getAll() {
		return flowDao.getAll();
	}
	
	@Override
	public Flow flow_getById(int id) {
		return flowDao.getById(id);
	}
	
	@Override
	public int flow_insert (Flow c) {
		return flowDao.insert(c);
	}
	
	@Override
	public int flow_delById(int id) {
		return flowDao.delById(id);
	}
	
	@Override
	public int flow_delAll() {
		return portDao.delAll();
	}
	
	@Override
	public List<Port> port_getAll() {
		return portDao.getAll();
	}
	
	@Override
	public Port port_getById(int id) {
		return portDao.getById(id);
	}
	
	@Override
	public List<Port> getBySwitchId(int id) {
		return (List<Port>) portDao.getBySwitchId(id);
	}
	
	@Override
	public int port_getId(@SuppressWarnings("rawtypes") Map para){
		return portDao.getId(para);
	}
	
	@Override
	public int port_insert(Port c) {
		return portDao.insert(c);
	}
	
	@Override
	public int port_delById(int id) {
		return portDao.delById(id);
	}
	
	@Override
	public int port_delAll() {
		return portDao.delAll();
	}
	
	@Override
	public List<Host> host_getAll() {
		return hostDao.getAll();
	}
	
	@Override
	public Host host_getById(int id) {
		return hostDao.getById(id);
	}
	
	@Override
	public int host_insert (Host c) {
		return hostDao.insert(c);
	}
	
	@Override
	public int host_delById(int id) {
		return hostDao.delById(id);
	}
	
	@Override
	public int host_delAll() {
		return hostDao.delAll();
	}
	
	@Override
	public List<Link> link_getAll() {
		return linkDao.getAll();
	}
	
	@Override
	public Link link_getById(int id) {
		return linkDao.getById(id);
	}
	
	@Override
	public int link_insert (Link c) {
		return linkDao.insert(c);
	}
	
	@Override
	public int link_delById(int id) {
		return linkDao.delById(id);
	}
	
	@Override
	public int link_delAll() {
		return linkDao.delAll();
	}

	@Override
	public List<PortStats> portStats_getAll() {
		return portStatsDao.getAll();
	}

	@Override
	public List<?> getPortStatsByDpID(String dp_id,int interval) {
		// requiring every underlay switch have unique dp_id
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("dp_id", dp_id);
		temp.put("interval", interval);
		return portStatsDao.getByDpId(temp);
	}

}
