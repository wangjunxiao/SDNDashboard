package cn.dlut.service;


/**
 * ConfigControllerService: response to add new extra controller to original config file
 *
 */
public interface ConfigControllerService {
	
	public String addController(String controllertype, String controllerurl);
	public String removeController(String controllertype, String controllerurl);
}