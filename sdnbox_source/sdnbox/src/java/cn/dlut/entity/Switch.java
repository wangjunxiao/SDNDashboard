package cn.dlut.entity;

import java.util.Date;

public class Switch {

	private Integer id;
	private Integer ctrl_id;
	private String dp_id;
	private Integer n_tables;
	private String dp_desc;
	private String sw_desc;
	private String hw_desc;
	private String serial_num;
	private String mfr_desc;
	private Date ctime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCtrl_id() {
		return ctrl_id;
	}

	public void setCtrl_id(Integer ctrl_id) {
		this.ctrl_id = ctrl_id;
	}

	public String getDp_id() {
		return dp_id;
	}

	public void setDp_id(String dp_id) {
		this.dp_id = dp_id;
	}

	public Integer getN_tables() {
		return n_tables;
	}

	public void setN_tables(Integer n_tables) {
		this.n_tables = n_tables;
	}

	public String getDp_desc() {
		return dp_desc;
	}

	public void setDp_desc(String dp_desc) {
		this.dp_desc = dp_desc;
	}

	public String getSw_desc() {
		return sw_desc;
	}

	public void setSw_desc(String sw_desc) {
		this.sw_desc = sw_desc;
	}

	public String getHw_desc() {
		return hw_desc;
	}

	public void setHw_desc(String hw_desc) {
		this.hw_desc = hw_desc;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public String getMfr_desc() {
		return mfr_desc;
	}

	public void setMfr_desc(String mfr_desc) {
		this.mfr_desc = mfr_desc;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "Switch [id=" + id + ", ctrl_id=" + ctrl_id + ", dp_id=" + dp_id
				+ ", n_tables=" + n_tables + ", dp_desc=" + dp_desc
				+ ", sw_desc=" + sw_desc + ", hw_desc=" + hw_desc
				+ ", serial_num=" + serial_num + ", mfr_desc=" + mfr_desc
				+ ", ctime=" + ctime + "]";
	}

}
