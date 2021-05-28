package cn.dlut.service.impl;

import org.springframework.stereotype.Service;
import cn.dlut.service.AbstractBaseService;
import cn.dlut.service.ConfigControllerService;

/**
 * ConfigControllerService: response to add new extra controller to original config file
 *
 */

@Service("ConfigControllerService")
public class ConfigControllerServiceImpl extends AbstractBaseService implements ConfigControllerService {
	
	public String addController(String controllertype, String controllerurl) {
		// TODO ip:port formulation check
		if(controllertype.equals("Ryu")) {
			//http://0-255.0-255.0-255.0-255:0-65535
			RyuInfoToDBServiceImpl.extra_STRHTTPPRE.add(controllerurl);
			return "controller add success";
		}
		else if(controllertype.equals("Floodlight")) {
			FloodInfoToDBServiceImpl.extra_STRHTTPPRE.add(controllerurl);
			return "controller add success";
		}
		else {
			return "controller type incorrect";
		}
	}

	@Override
	public String removeController(String controllertype, String controllerurl) {
		// TODO ip:port formulation check
		if(controllertype.equals("Ryu")) {
			//http://0-255.0-255.0-255.0-255:0-65535
			RyuInfoToDBServiceImpl.extra_STRHTTPPRE.remove(controllerurl);
			return "controller remove success";
		}
		else if(controllertype.equals("Floodlight")) {
			FloodInfoToDBServiceImpl.extra_STRHTTPPRE.remove(controllerurl);
			return "controller remove success";
		}
		else {
			return "controller type incorrect";
		}
	}
	
}
