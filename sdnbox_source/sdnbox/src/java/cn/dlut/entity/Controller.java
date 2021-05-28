/**
 * 
 */
package cn.dlut.entity;

import java.util.Date;

public class Controller {

	private Integer id;
	private String ip;
	private String port;
	private String type;
	private String rest_url;
	private Date ctime;
	
	public Controller(){	
	}

	public Controller(String ip,String port){
		this.ip = ip;
		this.port = port;		
	}
	
	public Integer getId() {
		return id;
	}

	public String toString() {
		return "Controller [id=" + id + ", type=" + type + ", ip=" + ip + ", port=" + port + ", rest_url="
				+ rest_url + ", ctime=" + ctime + "]";
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRest_url() {
		return rest_url;
	}

	public void setRest_url(String rest_url) {
		this.rest_url = rest_url;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getPort() {
		return port;
	}
	
	public void setPort(String port) {
		this.port = port;
	}

}
