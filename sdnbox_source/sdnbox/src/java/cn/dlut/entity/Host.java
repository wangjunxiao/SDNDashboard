/**
 * 
 */
package cn.dlut.entity;

import java.util.Date;

public class Host {

	private Integer id;
	private Integer switch_id;
	private Integer port_id;
	private String mac_addr;
	private String ip_addr;
	private Date ctime;
	private Date mtime;

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

	public Integer getPort_id() {
		return port_id;
	}

	public void setPort_id(Integer port_id) {
		this.port_id = port_id;
	}

	public String getMac_addr() {
		return mac_addr;
	}

	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}

	public String getIp_addr() {
		return ip_addr;
	}

	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	@Override
	public String toString() {
		return "Switch [id=" + id + ", switch_id=" + switch_id + ", port_id=" + port_id
				+ ", mac_addr=" + mac_addr + ", ip_addr=" + ip_addr
				+ ", ctime=" + ctime + ", mtime=" + mtime+"]";
	}

}
