/**
 * 
 */
package cn.dlut.entity;

import java.util.Date;

public class Flow {

	private Integer id;
	private Integer switch_id;
	private Integer table_id;
	private Long byte_count;
	private Double packet_count;
	private Integer idle_timeout;
	private Integer hard_timeout;
	private Integer duration_sec;
	private Integer duration_nsec;
	private Integer priority;
	private String cookie;
	private String dl_type;
	private String dl_src;
	private String dl_dst;
	private Integer dl_vlan;
	private Integer nw_proto;
	private Integer nw_tos;
	private String nw_src;
	private String nw_dst;
	private Integer nw_src_masklen;
	private Integer nw_dst_masklen;
	private Integer tp_src;
	private Integer tp_dst;
	private Integer in_port;
	private String action;
	private Date ctime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSwitch_id() {
		return switch_id;
	}

	public void setSwitch_id(Integer switch_id) {
		this.switch_id = switch_id;
	}

	public Integer getTable_id() {
		return table_id;
	}

	public void setTable_id(Integer table_id) {
		this.table_id = table_id;
	}

	public Long getByte_count() {
		return byte_count;
	}

	public void setByte_count(long byte_count) {
		this.byte_count = byte_count;
	}

	public Double getPacket_count() {
		return packet_count;
	}

	public void setPacket_count(Double packet_count) {
		this.packet_count = packet_count;
	}

	public Integer getIdle_timeout() {
		return idle_timeout;
	}

	public void setIdle_timeout(Integer idle_timeout) {
		this.idle_timeout = idle_timeout;
	}

	public Integer getHard_timeout() {
		return hard_timeout;
	}

	public void setHard_timeout(Integer hard_timeout) {
		this.hard_timeout = hard_timeout;
	}

	public Integer getDuration_sec() {
		return duration_sec;
	}

	public void setDuration_sec(Integer duration_sec) {
		this.duration_sec = duration_sec;
	}

	public Integer getDuration_nsec() {
		return duration_nsec;
	}

	public void setDuration_nsec(Integer duration_nsec) {
		this.duration_nsec = duration_nsec;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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

	public Integer getDl_vlan() {
		return dl_vlan;
	}

	public void setDl_vlan(Integer dl_vlan) {
		this.dl_vlan = dl_vlan;
	}

	public Integer getNw_proto() {
		return nw_proto;
	}

	public void setNw_proto(Integer nw_proto) {
		this.nw_proto = nw_proto;
	}

	public Integer getNw_tos() {
		return nw_tos;
	}

	public void setNw_tos(Integer nw_tos) {
		this.nw_tos = nw_tos;
	}

	public String getNw_src() {
		return nw_src;
	}

	public void setNw_src(String nw_src) {
		this.nw_src = nw_src;
	}

	public String getNw_dst() {
		return nw_dst;
	}

	public void setNw_dst(String nw_dst) {
		this.nw_dst = nw_dst;
	}

	public Integer getNw_src_masklen() {
		return nw_src_masklen;
	}

	public void setNw_src_masklen(Integer nw_src_masklen) {
		this.nw_src_masklen = nw_src_masklen;
	}

	public Integer getNw_dst_masklen() {
		return nw_dst_masklen;
	}

	public void setNw_dst_masklen(Integer nw_dst_masklen) {
		this.nw_dst_masklen = nw_dst_masklen;
	}

	public Integer getTp_src() {
		return tp_src;
	}

	public void setTp_src(Integer tp_src) {
		this.tp_src = tp_src;
	}

	public Integer getTp_dst() {
		return tp_dst;
	}

	public void setTp_dst(Integer tp_dst) {
		this.tp_dst = tp_dst;
	}

	public Integer getIn_port() {
		return in_port;
	}

	public void setIn_port(Integer in_port) {
		this.in_port = in_port;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "Flow [id=" + id + ", switch_id=" + switch_id + ", table_id="
				+ table_id + ", byte_count=" + byte_count + ", packet_count="
				+ packet_count + ", idle_timeout=" + idle_timeout
				+ ", hard_timeout=" + hard_timeout + ", duration_sec="
				+ duration_sec + ", duration_nsec=" + duration_nsec
				+ ", priority=" + priority + ", cookie=" + cookie
				+ ", dl_type=" + dl_type + ", dl_src=" + dl_src + ", dl_dst="
				+ dl_dst + ", dl_vlan=" + dl_vlan + ", nw_proto=" + nw_proto
				+ ", nw_tos=" + nw_tos + ", nw_src=" + nw_src + ", nw_dst="
				+ nw_dst + ", nw_src_masklen=" + nw_src_masklen
				+ ", nw_dst_masklen=" + nw_dst_masklen + ", tp_src=" + tp_src
				+ ", tp_dst=" + tp_dst + ", in_port=" + in_port + ", action="
				+ action + ", ctime=" + ctime + "]";
	}

}
