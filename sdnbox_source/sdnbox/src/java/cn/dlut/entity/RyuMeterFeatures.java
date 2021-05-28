package cn.dlut.entity;

import java.util.Date;

public class RyuMeterFeatures {

	private int id;
	private int switch_id;
	private int ctrl_id;
	private int max_meter;
	private String band_types;
	private String capabilities;
	private int max_bands;
	private int max_color;
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
	
	public int getMax_Meter(){
		return max_meter;
	}
	
	public void setMax_Meter(int max_meter){
		this.max_meter=max_meter;
	}
	
	public int getMax_Bands(){
		return max_bands;
	}
	
	
	public void setMax_Bands(int max_bands){
		this.max_bands=max_bands;
	}
	
	public int getMax_Color(){
		return max_color;
	}
	
	public void setMax_Color(int max_color){
		this.max_color=max_color;
	}
	
	public String getBand_Types(){
		return band_types;
	}
	
	public void setBand_Types(String band_types){
		this.band_types=band_types;
	}
	
	public String getCapabilities(){
		return capabilities;
	}
	
	public void setCapabilities(String capabilities){
		this.capabilities=capabilities;
	}
	
	public Date getCtime(){
		return ctime;
	}
	
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	
	public String toString(){
		return "RyuMeterFeatures: [id="+id+", switch_id="+switch_id+", ctrl_id="+ctrl_id+",max_meter="+max_meter+",max_bands="+max_bands+",max_color="+max_color
				+",band_types="+band_types+",capabilities="+capabilities+", ctime="+ctime+"]";
	}
}
