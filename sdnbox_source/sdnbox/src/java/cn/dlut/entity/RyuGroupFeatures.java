package cn.dlut.entity;

import java.util.Date;

public class RyuGroupFeatures {
	
	private int id;
	private int switch_id;
	private int ctrl_id;
	private String types;
	private String capabilities;
	private String max_groups;
	private String actions;
	private Date ctime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCtrl_id(){
		return ctrl_id;
	}
	
	public void setCtrl_id(int ctrl_id){
		this.ctrl_id=ctrl_id;
	}
	public String getTypes() {
		return types;
	}
	public int getSwitch_id(){
		return switch_id;
	}
	public void setSwitch_id(int switch_id){
		this.switch_id=switch_id;
	}
	public void setTypes(String typs) {
		this.types = typs;
	}
	public String getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(String capabilities) {
		this.capabilities = capabilities;
	}
	public String getMax_groups() {
		return max_groups;
	}
	public void setMax_groups(String max_groups) {
		this.max_groups = max_groups;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
	
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	@Override
	public String toString() {
		return "GroupFeatures [id=" + id + ", switch_id="+switch_id+", ctrl_id"+ctrl_id+", types=" + types
				+ ", capabilities=" + capabilities + ", max_groups="
				+ max_groups + ", actions=" + actions + ", ctime=" + ctime
				+ "]";
	}
	

}
