package cn.dlut.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dlut.service.FloodInfoToDBService;

@Component
public class HandleFloodJob {
	
	@Autowired
	private FloodInfoToDBService flservice;

	/**
	 * this is the core function of floodlight timed task
	 */
	
	public void HandleFloodlight()
	{
		//update floodlight related dataset in db
		flservice.FloodInfoRmAndInsert();
	}
}
