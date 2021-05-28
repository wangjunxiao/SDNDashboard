package cn.dlut.service;

public interface RyuInfoToDBService {

	public int InsertCtrlInfo(String controller_url, int controller_id);
	public int InsertFlow(String controller_url);
	public int InsertPort(String controller_url);
	public int InsertPortDesc(String controller_url);
	public int InsertQueue();
	public int InsertGroup();
	public int InsertGroupDesc();
	public int InsertGroupFeatures();
	public int InsertMeter();
	public int InsertMeterConfig();
	public int InsertMeterFeatures();
	public int InsertSwitchDesc(String controller_url);
	public int InsertLink(String controller_url);
	public void RyuInfoRmAndInsert();
	
}