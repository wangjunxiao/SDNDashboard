package cn.dlut.entity;

import java.io.Serializable;


public class MeterEntry implements Serializable {
	
	/**
	 * MeterEntry
	 */
	private static final long serialVersionUID = 1L;
	int dpid;
	String flags;
	int meter_id;
	String bands;
	public int getDpid() {
		return dpid;
	}
	public void setDpid(int dpid) {
		this.dpid = dpid;
	}
	public String getFlags() {
		return flags;
	}
	public void setFlags(String flags) {
		this.flags = flags;
	}
	public int getMeter_id() {
		return meter_id;
	}
	public void setMeter_id(int meter_id) {
		this.meter_id = meter_id;
	}
	public String getBands() {
		return bands;
	}
	public void setBands(String bands) {
		this.bands = bands;
	}
	@Override
	public String toString() {
		return "MeterEntry [dpid=" + dpid + ", flags=" + flags + ", meter_id="
				+ meter_id + ", bands=" + bands + "]";
	}
	
}