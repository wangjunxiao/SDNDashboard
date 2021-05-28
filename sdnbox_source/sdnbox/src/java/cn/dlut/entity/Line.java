package cn.dlut.entity;

public class Line {
	int id;
	String type;
	int visible;
	String name;
	String keyname;
	String keyvalue;
	
	public String getKeyvalue() {
		return keyvalue;
	}
	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getVisible() {
		return visible;
	}
	public void setVisible(int visible) {
		this.visible = visible;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
