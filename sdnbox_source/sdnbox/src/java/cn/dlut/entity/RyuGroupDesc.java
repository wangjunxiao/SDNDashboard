package cn.dlut.entity;

import java.util.Date;

public class RyuGroupDesc {

	
	private int id;
	private int switch_id;
	private int ctrl_id;
	private String type;
	private int group_id;
	private Date ctime;
	/*
	 * buckets is array element, contains weight,weight_port,
	 * watch_group,actions element;actions is also array element
	 */
	private String buckets;
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id=id;
	}
	public int getSwitch_id() {
		return switch_id;
	}

	public void setSwitch_id(int switch_id) {
		this.switch_id = switch_id;
	}

	public int getCtrl_id(){
		return ctrl_id;
	}
	
	public void setCtrl_id(int ctrl_id){
		this.ctrl_id=ctrl_id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getBuckets() {
		return buckets;
	}

	public void setBuckets(String buckets) {
		this.buckets = buckets;
	}
	

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "GroupDesc [id=" + id + ", switch_id="+switch_id+", ctrl_id="+ctrl_id+", type=" + type + ", group_id="
				+ group_id + ", ctime=" + ctime + ", buckets=" + buckets + "]";
	}
	
}
