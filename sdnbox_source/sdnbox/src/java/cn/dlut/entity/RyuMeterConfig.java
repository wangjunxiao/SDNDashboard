package cn.dlut.entity;

import java.util.Date;

public class RyuMeterConfig {

	private int id;
	private int switch_id;
	private int ctrl_id;
	private String flags;
	private int meter_id;
	private String bands;
	private Date ctime;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	
	public int getSwitch_id(){
		return switch_id;
	}
	
	public void setSwitch_id(int switch_id){
		this.switch_id=switch_id;
	}
	
	public int getCtrl_id(){
		return ctrl_id;
	}
	
	public void setCtrl_id(int ctrl_id){
		this.ctrl_id=ctrl_id;
	}
	
	public int getMeter_id(){
		return meter_id;
	}
	
	public void setMeter_id(int meter_id){
		this.meter_id=meter_id;
	}
	
	public String getFlags(){
		return flags;
	}
	
	public void setFlags(String flags){
		this.flags=flags;
	}
	
	public String getBands(){
		
		return bands;
	}
	
	public void setBands(String bands){
		this.bands=bands;
	}
	
	public Date getCtime(){
		return ctime;
	}
	
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	public String toString()
	{
		return "RyuMeterConfig:[id="+id+", switch_id="+switch_id+", ctrl_id="+ctrl_id+", meter_id="+meter_id+", flags="+flags+", bands"+bands+", ctime="+ctime+"]";
	}
	
}
