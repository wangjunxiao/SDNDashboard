package cn.dlut.entity;

import java.util.Date;

public class PortStats {
	private int id;
	private String dp_id;
	private long port_no;
	private int rcv_packets;
	private int trsm_packets;
	private long rcv_bytes;
	private long trsm_bytes;
	private int rcv_drop;
	private int trsm_drop;
	private int rcv_err;
	private int trsm_err;
	private int rcv_frm_err;
	private int rcv_over_err;
	private int rcv_CRC_err;
	private int collisions;
	private long duration_sec;
	private String duration_nsec;
	private Date update_time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDp_id() {
		return dp_id;
	}
	public void setDp_id(String dp_id) {
		this.dp_id = dp_id;
	}
	public long getPort_no() {
		return port_no;
	}
	public void setPort_no(long port_no) {
		this.port_no = port_no;
	}
	public int getRcv_packets() {
		return rcv_packets;
	}
	public void setRcv_packets(int rcv_packets) {
		this.rcv_packets = rcv_packets;
	}
	public int getTrsm_packets() {
		return trsm_packets;
	}
	public void setTrsm_packets(int trsm_packets) {
		this.trsm_packets = trsm_packets;
	}
	public long getRcv_bytes() {
		return rcv_bytes;
	}
	public void setRcv_bytes(long rcv_bytes) {
		this.rcv_bytes = rcv_bytes;
	}
	public long getTrsm_bytes() {
		return trsm_bytes;
	}
	public void setTrsm_bytes(long trsm_bytes) {
		this.trsm_bytes = trsm_bytes;
	}
	public int getRcv_drop() {
		return rcv_drop;
	}
	public void setRcv_drop(int rcv_drop) {
		this.rcv_drop = rcv_drop;
	}
	public int getTrsm_drop() {
		return trsm_drop;
	}
	public void setTrsm_drop(int trsm_drop) {
		this.trsm_drop = trsm_drop;
	}
	public int getRcv_err() {
		return rcv_err;
	}
	public void setRcv_err(int rcv_err) {
		this.rcv_err = rcv_err;
	}
	public int getTrsm_err() {
		return trsm_err;
	}
	public void setTrsm_err(int trsm_err) {
		this.trsm_err = trsm_err;
	}
	public int getRcv_frm_err() {
		return rcv_frm_err;
	}
	public void setRcv_frm_err(int rcv_frm_err) {
		this.rcv_frm_err = rcv_frm_err;
	}
	public int getRcv_over_err() {
		return rcv_over_err;
	}
	public void setRcv_over_err(int rcv_over_err) {
		this.rcv_over_err = rcv_over_err;
	}
	public int getRcv_CRC_err() {
		return rcv_CRC_err;
	}
	public void setRcv_CRC_err(int rcv_CRC_err) {
		this.rcv_CRC_err = rcv_CRC_err;
	}
	public int getCollisions() {
		return collisions;
	}
	public void setCollisions(int collisions) {
		this.collisions = collisions;
	}
	
	public long getDuration_sec(){
		return duration_sec;
	}
	
	public void setDuration_sec(long duration_sec){
		this.duration_sec=duration_sec;
	}
	
	public String getDuration_nsec(){
		return duration_nsec;
	}
	public void setDuration_nsec(String duration_nsec){
		this.duration_nsec=duration_nsec;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	@Override
	public String toString() {
		return "Port [id=" + id + ", dp_id=" + dp_id + ", port_no="
				+ port_no + ", rcv_packets=" + rcv_packets + ", trsm_packets="
				+ trsm_packets + ", rcv_bytes=" + rcv_bytes + ", trsm_bytes="
				+ trsm_bytes + ", rcv_drop=" + rcv_drop + ", trsm_drop="
				+ trsm_drop + ", rcv_err=" + rcv_err + ", trsm_err=" + trsm_err + ", rcv_frm_err="
				+ rcv_frm_err + ", rcv_over_err=" + rcv_over_err + ", rcv_CRC_err="
				+ rcv_CRC_err + ", collisions=" + collisions + ", duration_sec="+duration_sec+", duration_nsec="+duration_nsec+", update_time=" + update_time + "]";
	}
	
}
