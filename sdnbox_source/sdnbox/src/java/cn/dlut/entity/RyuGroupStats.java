package cn.dlut.entity;

import java.util.Date;

public class RyuGroupStats {

	private int id;
	private int switch_id;
	private int ctrl_id;
	private int length;
	private int group_id;
	private int ref_count;
	private int packet_count;
	private int byte_count;
	private int duration_sec;
	private String duration_nsec;
	private String bucket_stats;
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
	
	public int getLength(){
		return length;
	}
	
	public void setLength(int length){
		this.length=length;
	}
	
	public int getGroup_id(){
		return group_id;
	}
	
	public void setGroup_id(int group_id){
		this.group_id=group_id;
	}
	
	public int getRef_count(){
		return ref_count;
	}
	
	public void setRef_count(int ref_count){
		this.ref_count=ref_count;
	}
	
	public int getPacket_count(){
		return packet_count;
	}
	
	public void setPacket_count(int packet_count){
		this.packet_count=packet_count;
	}
	
	public int getByte_count(){
		return byte_count;
	}
	
	public void setByte_count(int byte_count){
		this.byte_count=byte_count;
	}
	
	public int getDuration_sec(){
		return duration_sec;
	}
	
	public void setDuration_sec(int duration_sec){
		this.duration_sec=duration_sec;
	}
	
	public String getDuration_nsec(){
		return duration_nsec;
	}
	
	public void setDuration_nsec(String duration_nsec){
		this.duration_nsec=duration_nsec;
	}
	
	public String getBucket_stats(){
		return bucket_stats;
	}
	
	public void setBucket_stats(String bucket_stats){
		this.bucket_stats=bucket_stats;
	}
	
	public Date getCtime(){
		return ctime;
	}
	
	public void setCtime(Date ctime){
		this.ctime=ctime;
	}
	
	public String toString() {
		return "GroupStats [id=" + id + ", switch_id="+switch_id+", ctrl_id="+ctrl_id+", length=" + length + ", group_id="
				+ group_id + ", ref_count=" + ref_count + ", packet_count="
				+ packet_count + ", byte_count=" + byte_count
				+ ", duration_sec=" + duration_sec + ", duration_nsec="
				+ duration_nsec + ", bucket_stats=" + bucket_stats + ", ctime="
				+ ctime + "]";
	}
}
