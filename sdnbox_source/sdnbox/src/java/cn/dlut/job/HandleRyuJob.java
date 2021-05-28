package cn.dlut.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dlut.service.RyuInfoToDBService;

@Component
public class HandleRyuJob {
	
	@Autowired
	private RyuInfoToDBService ryuservice;

	/**
	 * this is the core function of ryu timed task
	 */
	
	public void HandleRyu()
	{
		//update ryu related dataset in db
		ryuservice.RyuInfoRmAndInsert();
	}
}
