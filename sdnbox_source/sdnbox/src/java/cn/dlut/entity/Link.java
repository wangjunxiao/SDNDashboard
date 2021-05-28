/**
 * 
 */
package cn.dlut.entity;

import java.util.Date;


public class Link {

	private int id;
	private int src_switch_id;
	private int src_port_id;
	private int dst_switch_id;
	private int dst_port_id;
	private Date ctime;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getSrc_switch_id(){
			return src_switch_id;
	}
	
	public void setSrc_switch_id(int src_switch_id) {
		this.src_switch_id = src_switch_id;
	}
	
	public int getSrc_port_id() {
		return src_port_id;
	}
	
	public void setSrc_port_id(int src_port_id) {
		this.src_port_id = src_port_id;
	}
	
	public int getDst_switch_id() {
		return dst_switch_id;
	}
	
	public void setDst_switch_id(int dst_switch_id) {
		this.dst_switch_id = dst_switch_id;
	}
	
	public int getDst_port_id() {
		return dst_port_id;
	}
	
	public void setDst_port_id(int dst_port_id) {
		this.dst_port_id = dst_port_id;
	}
	
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	@Override
	public String toString() {
		return "Link [id=" + id + ", src_switch_id=" + src_switch_id + ", src_port_id="
				+ src_port_id + ", dst_switch_id=" + dst_switch_id + ", dst_port_id="
				+ dst_port_id + ", ctime=" + ctime + "]";
	}


}

