package cn.dlut.entity;

import java.util.Date;


public class RyuMeterStats {

	private int id;
	private int switch_id;
	private int ctrl_id;
	private int meter_id;
	private int len;
	private int flow_count;
	private int packet_in_count;
	private int byte_in_count;
	private long duration_sec;
	private String duration_nsec;
	private String band_stats;
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
	
	public int getLen(){
		return len;
	}
	
	public int getFlow_count(){
		return flow_count;
	}
	
	public int getPacket_in_count(){
		return packet_in_count;
	}
	
	public int getByte_in_count(){
		return byte_in_count;
	}
	
	public long getDuration_sec(){
		return duration_sec;
	}
	
	public String getDuration_nsec(){
		return duration_nsec;
	}
	
	public String getBandstats(){
		return band_stats;
	}
	
	public void setMeter_id(int meter_id){
		this.meter_id=meter_id;
	}
	
	public void setLen(int len){
		this.len=len;
	}
	
	public void setFlow_count(int flow_count){
		this.flow_count=flow_count;
	}
	
	public void setPacket_in_count(int packet_in_count){
		this.packet_in_count=packet_in_count;
	}
	
	public void setByte_in_count(int byte_in_count){
		this.byte_in_count=byte_in_count;
	}
	
	public void setDuration_sec(long duration_sec){
		this.duration_sec=duration_sec;
	}
	
	public void setDuration_nsec(String duration_nsec){
		this.duration_nsec=duration_nsec;
	}
	
	public void setBandStats(String band_stats){
		this.band_stats=band_stats;
	}
	public Date getCtime(){
		return ctime;
	}
	public void setCtime(Date ctime)
	{
		this.ctime=ctime;
	}
	
	public String toString(){
		return "Meter: [id="+id+", switch_id="+switch_id+", ctrl_id="+ctrl_id+", meter_id="+meter_id+", len="+len+", flow_count="+flow_count+", packet_in_count="+packet_in_count
				+", byte_in_count="+byte_in_count+", duration_sec="+duration_sec+", duration_nsec="+duration_nsec
				+", band_stats="+band_stats+", ctime="+ctime+"]";
	}
}
