package cn.dlut.entity;

public class AclRule {
	private int ruleid;
	
	private String Nw_proto;//TCP or UDP
	private String Src_ip;//source IP
	private String Dst_ip;//destination IP
	private int    Tp_dst;//destination port num
	private String Action;//ALLOW or DENY
	
	public int getRuleid() {
		return ruleid;
	}
	public void setRuleid(int ruleid) {
		this.ruleid = ruleid;
	}
	
	public String getNw_proto(){
		return Nw_proto;
	}
	public void setNw_proto(String Nw_ptoto){
		this.Nw_proto=Nw_ptoto;
	}
	
	public String getSrc_ip(){
		return Src_ip;
	}
	public void setSrc_ip(String Src_ip){
		this.Src_ip=Src_ip;
	}
	
	public String getDst_ip(){
		return Dst_ip;
	}
	public void setDst_ip(String Dst_ip){
		this.Dst_ip=Dst_ip;
	}
	
	public int getTp_dst() {
		return this.Tp_dst;
	}
	public void setTp_dst(int tp_dst) {
		this.Tp_dst = tp_dst;
	}
	
	public String getAction(){
		return Action;
	}
	public void setAction(String Action){
		this.Action=Action;
	}
}
