package cn.dlut.entity;

import java.io.Serializable;

public class Rule implements Serializable {
	
	/**
	 *  Rule
	 */
	private static final long serialVersionUID = 1L;
	private int priority; //default 65535
	private String dl_type;//ipv4 or ipv6	
	private int ruleid;	
	private String nw_proto;//TCP or UDP
	private String src_ip;//source IP
	private String dst_ip;//destination IP
	private int    tp_dst;//destination port num
	private String action;//ALLOW or DENY
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getDl_type() {
		return dl_type;
	}
	public void setDl_type(String dl_type) {
		this.dl_type = dl_type;
	}
	public int getRuleid() {
		return ruleid;
	}
	public void setRuleid(int ruleid) {
		this.ruleid = ruleid;
	}
	public String getNw_proto() {
		return nw_proto;
	}
	public void setNw_proto(String nw_proto) {
		this.nw_proto = nw_proto;
	}
	public String getSrc_ip() {
		return src_ip;
	}
	public void setSrc_ip(String src_ip) {
		this.src_ip = src_ip;
	}
	public String getDst_ip() {
		return dst_ip;
	}
	public void setDst_ip(String dst_ip) {
		this.dst_ip = dst_ip;
	}
	public int getTp_dst() {
		return tp_dst;
	}
	public void setTp_dst(int tp_dst) {
		this.tp_dst = tp_dst;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
}
