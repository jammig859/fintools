package com.jam.tree.entity;



import java.util.List;

public class DataTableObject {
	private String  iTotalRecords        = "";
	private String  iTotalDisplayRecords  = "";
	
	private List<?>   aaData ;
	
	public String getiTotalRecords() {
		return iTotalRecords;
	}
	public void setiTotalRecords(String iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}
	public String getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}
	public void setiTotalDisplayRecords(String iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}
	public List<?> getAaData() {
		return aaData;
	}
	public void setAaData(List<?> aaData) {
		this.aaData = aaData;
	}
}

