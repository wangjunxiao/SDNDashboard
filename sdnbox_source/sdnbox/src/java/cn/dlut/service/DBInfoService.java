package cn.dlut.service;

import java.util.List;
import java.util.Map;

import cn.dlut.entity.Controller;
import cn.dlut.entity.Flow;
import cn.dlut.entity.Host;
import cn.dlut.entity.Link;
import cn.dlut.entity.Port;
import cn.dlut.entity.PortStats;
import cn.dlut.entity.Switch;

@SuppressWarnings("all")
public interface DBInfoService {
	
	public int controller_delById(int id);
		
	public List<Switch>  switch_getAll();

	public Switch switch_getById(int id);
	
	public int switch_getId(Map para);

	public List<Switch> getByControllerId(int ctrl_id);
	
	public int switch_insert(Switch c);
	
	public int switch_delById(int id);
	
	public int switch_delAll();
	
	
	public List<Flow> flow_getAll();

	public Flow flow_getById(int id);
	
	public int flow_insert(Flow f);
	
	public int flow_delById(int id);
	
	public int flow_delAll();
	
	
	public List<Port> port_getAll();

	public Port port_getById(int id);
	
	public List<Port> getBySwitchId(int swich_id);
	
	
	public int port_getId(Map para);
	
	public int port_insert(Port f);
	
	public int port_delById(int id);
	
	public int port_delAll();
	
	
	public List<Host> host_getAll();

	public Host host_getById(int id);
	
	public int host_insert(Host f);
	
	public int host_delById(int id);
	
	public int host_delAll();
	
	
	public List<Link> link_getAll();

	public Link link_getById(int id);
	
	public int link_insert(Link f);
	
	public int link_delById(int id);
	
	public int link_delAll();
	
	
	public List<PortStats> portStats_getAll();
	
	public List getPortStatsByDpID(String dp_id,int interval);

	public List<Controller> controller_getAll();

	public Controller controller_getById(int id);

	public int controller_insert(Controller c);
	
}
