/**
 * 
 */
package cn.dlut.entity;

import java.util.Date;

public class Port {

	private int id;
	private int switch_id;
	private long port_no;
	private String mac_addr;
	private String port_name;
	private int config;
	private int state;
	private int curr;
	private int advertised;
	private int supported;
	private int peer;
	private String curr_speed;
	private int max_speed;
	private Date ctime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSwitch_id() {
		return switch_id;
	}

	public void setSwitch_id(int switch_id) {
		this.switch_id = switch_id;
	}

	public long getPort_no() {
		return port_no;
	}

	public void setPort_no(long port_no) {
		this.port_no = port_no;
	}

	public String getMac_addr() {
		return mac_addr;
	}

	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}

	public String getPort_name() {
		return port_name;
	}

	public void setPort_name(String port_name) {
		this.port_name = port_name;
	}
	public int getConfig(){
		return config;
	}
	public void setConfig(int config){
		this.config=config;
	}
	public int getState(){
		return state;
	}
	public void setState(int state){
		this.state=state;
	}
	public int getCurr(){
		return curr;
	}
	public void setCurr(int curr){
		this.curr=curr;
	}
	public int getAdvertised(){
		return advertised;
	}
	public void setAdvertised(int advertised){
		this.advertised=advertised;
	}
	public int getSupported(){
		return supported;
	}
	public void setSupported(int supported){
		this.supported=supported;
	}
	public int getPeer(){
		return peer;
	}
	public void setPeer(int peer){
		this.peer=peer;
	}
	public String getCurr_speed(){
		return curr_speed;
	}
	public void setCurr_speed(String curr_speed){
		this.curr_speed=curr_speed;
	}
	public int getMax_speed(){
		return max_speed;
	}
	public void setMax_speed(int max_speed){
		this.max_speed=max_speed;
	}
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "Port [id=" + id + ", switch_id=" + switch_id + ", port_no="
				+ port_no + ", mac_addr=" + mac_addr + ", port_name="
				+ port_name + ", config="+config+", state="+state+", curr="+curr+", advertised="+advertised+", supported="+supported+", peer="+peer+", curr_speed="+curr_speed+", max_speed="+max_speed+", ctime=" + ctime + "]";
	}

}

