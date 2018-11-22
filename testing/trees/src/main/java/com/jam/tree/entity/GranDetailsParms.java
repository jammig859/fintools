package com.jam.tree.entity;

public class GranDetailsParms {

	
	private String coas;
	private String startNum;
	private String endNum;
	private String userid;
	private String userName;
	private String grantNum;
	private Number firstrec;
	
	public Number getFirstrec() {
		return firstrec;
	}
	public void setFirstrec(Number firstrec2) {
		this.firstrec = firstrec2;
	}
	public Number getLastrec() {
		return lastrec;
	}
	public void setLastrec(Number lastrec2) {
		this.lastrec = lastrec2;
	}
	private Number lastrec;
	
	public String getGrantNum() {
		return grantNum;
	}
	public void setGrantNum(String grantNum) {
		this.grantNum = grantNum;
	}
	public String getCoas() {
		return coas;
	}
	public void setCoas(String coas) {
		this.coas = coas;
	}
	public String getStartNum() {
		return startNum;
	}
	public void setStartNum(String startNum) {
		this.startNum = startNum;
	}
	public String getEndNum() {
		return endNum;
	}
	public void setEndNum(String endNum) {
		this.endNum = endNum;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
