package cn.dlut.entity;

import java.io.Serializable;


public class FlowEntry implements Serializable {
	/**
	 * FlowEntry
	 */
	private static final long serialVersionUID = 1L;
	
	private String dpid;
	private String name;
	
	private String in_port;
	private String priority;
	private String hard_timeout;
	private String idle_timeout;
	private String cookie;
	
	// Layer 2
	private String dl_type;
	private String dl_vlan;
	private String dl_vlan_priority; 
	private String dl_src;
	private String dl_dst;
	
	// Layer 3
	private String nw_src;
	private String nw_src_masklen;
	private String nw_dst;
	private String nw_dst_masklen;
	private String nw_tos;
	private String nw_proto;
	
	// Layer 4
	private String tp_src;
	private String tp_dst;
	
	private String action;
	
	private String output_port; // Add Output Ports
	private String new_vlan_id; // Set VLAN ID
	private String new_vlan_priority; // Set VLAN Priority
	private String new_dl_src; // Modify Datalayer Source Address
	private String new_dl_dst; // Modify Datalayer Destination Address
	private String new_nw_src; // Modify Network Source Address
	private String new_nw_dst; // Modify Network Destination Address
	private String new_nw_tos; // Modify ToS Bits
	private String new_tp_src; // Modify Transport Source Port
	private String new_tp_dst; // Modify Transport Destination Port
	
	
	
	public FlowEntry() {
		dpid = "";
		name = "";
		in_port = "";
		priority = "";
		hard_timeout = "";
		idle_timeout = "";
		cookie = "";
		dl_type = "";
		dl_vlan = "";
		dl_vlan_priority = "";
		dl_src = "";
		dl_dst = "";
		nw_src = "";
		nw_src_masklen = "";
		nw_dst = "";
		nw_dst_masklen = "";
		nw_tos = "";
		nw_proto = "";
		tp_src = "";
		tp_dst = "";
		action = "";
		output_port = "";
		new_vlan_id = "";
		new_vlan_priority = "";
		new_dl_src = "";
		new_dl_dst = "";
		new_nw_src = "";
		new_nw_dst = "";
		new_nw_tos = "";
		new_tp_src = "";
		new_tp_dst = "";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDpid() {
		return dpid;
	}
	public void setDpid(String dpid) {
		this.dpid = dpid;
	}
	public String getIn_port() {
		return in_port;
	}
	public void setIn_port(String in_port) {
		this.in_port = in_port;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getHard_timeout() {
		return hard_timeout;
	}
	public void setHard_timeout(String hard_timeout) {
		this.hard_timeout = hard_timeout;
	}
	public String getIdle_timeout() {
		return idle_timeout;
	}
	public void setIdle_timeout(String idle_timeout) {
		this.idle_timeout = idle_timeout;
	}
	public String getCookie() {
		return cookie;
	}
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	public String getDl_type() {
		return dl_type;
	}
	public void setDl_type(String dl_type) {
		this.dl_type = dl_type;
	}
	public String getDl_vlan() {
		return dl_vlan;
	}
	public void setDl_vlan(String dl_vlan) {
		this.dl_vlan = dl_vlan;
	}
	public String getDl_vlan_priority() {
		return dl_vlan_priority;
	}
	public void setDl_vlan_priority(String dl_vlan_priority) {
		this.dl_vlan_priority = dl_vlan_priority;
	}
	public String getDl_src() {
		return dl_src;
	}
	public void setDl_src(String dl_src) {
		this.dl_src = dl_src;
	}
	public String getDl_dst() {
		return dl_dst;
	}
	public void setDl_dst(String dl_dst) {
		this.dl_dst = dl_dst;
	}
	public String getNw_src() {
		return nw_src;
	}
	public void setNw_src(String nw_src) {
		this.nw_src = nw_src;
	}
	public String getNw_src_masklen() {
		return nw_src_masklen;
	}
	public void setNw_src_masklen(String nw_src_masklen) {
		this.nw_src_masklen = nw_src_masklen;
	}
	public String getNw_dst() {
		return nw_dst;
	}
	public void setNw_dst(String nw_dst) {
		this.nw_dst = nw_dst;
	}
	public String getNw_dst_masklen() {
		return nw_dst_masklen;
	}
	public void setNw_dst_masklen(String nw_dst_masklen) {
		this.nw_dst_masklen = nw_dst_masklen;
	}
	public String getNw_tos() {
		return nw_tos;
	}
	public void setNw_tos(String nw_tos) {
		this.nw_tos = nw_tos;
	}
	public String getTp_src() {
		return tp_src;
	}
	public void setTp_src(String tp_src) {
		this.tp_src = tp_src;
	}
	public String getTp_dst() {
		return tp_dst;
	}
	public void setTp_dst(String tp_dst) {
		this.tp_dst = tp_dst;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOutput_port() {
		return output_port;
	}
	public void setOutput_port(String output_port) {
		this.output_port = output_port;
	}
	public String getNew_vlan_id() {
		return new_vlan_id;
	}
	public void setNew_vlan_id(String new_vlan_id) {
		this.new_vlan_id = new_vlan_id;
	}
	public String getNew_vlan_priority() {
		return new_vlan_priority;
	}
	public void setNew_vlan_priority(String new_vlan_priority) {
		this.new_vlan_priority = new_vlan_priority;
	}
	public String getNew_dl_src() {
		return new_dl_src;
	}
	public void setNew_dl_src(String new_dl_src) {
		this.new_dl_src = new_dl_src;
	}
	public String getNew_dl_dst() {
		return new_dl_dst;
	}
	public void setNew_dl_dst(String new_dl_dst) {
		this.new_dl_dst = new_dl_dst;
	}
	public String getNew_nw_src() {
		return new_nw_src;
	}
	public void setNew_nw_src(String new_nw_src) {
		this.new_nw_src = new_nw_src;
	}
	public String getNew_nw_dst() {
		return new_nw_dst;
	}
	public void setNew_nw_dst(String new_nw_dst) {
		this.new_nw_dst = new_nw_dst;
	}
	public String getNew_nw_tos() {
		return new_nw_tos;
	}
	public void setNew_nw_tos(String new_nw_tos) {
		this.new_nw_tos = new_nw_tos;
	}
	public String getNew_tp_src() {
		return new_tp_src;
	}
	public void setNew_tp_src(String new_tp_src) {
		this.new_tp_src = new_tp_src;
	}
	public String getNew_tp_dst() {
		return new_tp_dst;
	}
	public void setNew_tp_dst(String new_tp_dst) {
		this.new_tp_dst = new_tp_dst;
	}
	public String getNw_proto() {
		return nw_proto;
	}
	public void setNw_proto(String nw_proto) {
		this.nw_proto = nw_proto;
	}
	@Override
	public String toString() {
		return "FlowEntry [dpid=" + dpid + ", name=" + name + ", in_port="
				+ in_port + ", priority=" + priority + ", hard_timeout="
				+ hard_timeout + ", idle_timeout=" + idle_timeout + ", cookie="
				+ cookie + ", dl_type=" + dl_type + ", dl_vlan=" + dl_vlan
				+ ", dl_vlan_priority=" + dl_vlan_priority + ", dl_src="
				+ dl_src + ", dl_dst=" + dl_dst + ", nw_src=" + nw_src
				+ ", nw_src_masklen=" + nw_src_masklen + ", nw_dst=" + nw_dst
				+ ", nw_dst_masklen=" + nw_dst_masklen + ", nw_tos=" + nw_tos
				+ ", nw_proto=" + nw_proto + ", tp_src=" + tp_src + ", tp_dst="
				+ tp_dst + ", action=" + action + ", output_port="
				+ output_port + ", new_vlan_id=" + new_vlan_id
				+ ", new_vlan_priority=" + new_vlan_priority + ", new_dl_src="
				+ new_dl_src + ", new_dl_dst=" + new_dl_dst + ", new_nw_src="
				+ new_nw_src + ", new_nw_dst=" + new_nw_dst + ", new_nw_tos="
				+ new_nw_tos + ", new_tp_src=" + new_tp_src + ", new_tp_dst="
				+ new_tp_dst + "]";
	}
	
}