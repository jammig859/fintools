package com.jam.tree.entity;

public class GrantSummary {

	private String resultCount;
	private String frrgrnlGrntCode;
	private String frrgrnlTotal;
	private String frrgrnlBudget;
	private String frrgrnlYtd;
	private String frrgrnlReservations;
	private String frrgrnlEncumbrance;
	private String agencyName;
	private String sponsorId;
	private String status;
	private String rowNumber;
	private String analystName;
	private String grantTitle;
	private String investigatorName;

	public String getResultCount() {
		return resultCount;
	}
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}
	public String getFrrgrnlGrntCode() {
		return frrgrnlGrntCode;
	}
	public void setFrrgrnlGrntCode(String frrgrnlGrntCode) {
		this.frrgrnlGrntCode = frrgrnlGrntCode;
	}
	public String getFrrgrnlTotal() {
		return frrgrnlTotal;
	}
	public void setFrrgrnlTotal(String frrgrnlTotal) {
		this.frrgrnlTotal = frrgrnlTotal;
	}
	public String getFrrgrnlBudget() {
		return frrgrnlBudget;
	}
	public void setFrrgrnlBudget(String frrgrnlBudget) {
		this.frrgrnlBudget = frrgrnlBudget;
	}
	public String getFrrgrnlYtd() {
		return frrgrnlYtd;
	}
	public void setFrrgrnlYtd(String frrgrnlYtd) {
		this.frrgrnlYtd = frrgrnlYtd;
	}
	public String getFrrgrnlReservations() {
		return frrgrnlReservations;
	}
	public void setFrrgrnlReservations(String frrgrnlReservations) {
		this.frrgrnlReservations = frrgrnlReservations;
	}
	public String getFrrgrnlEncumbrance() {
		return frrgrnlEncumbrance;
	}
	public void setFrrgrnlEncumbrance(String frrgrnlEncumbrance) {
		this.frrgrnlEncumbrance = frrgrnlEncumbrance;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	public String getSponsorId() {
		return sponsorId;
	}
	public void setSponsorId(String sponsorId) {
		this.sponsorId = sponsorId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(String rowNumber) {
		this.rowNumber = rowNumber;
	}
	public String getAnalystName() {
		return analystName;
	}
	public void setAnalystName(String analystName) {
		this.analystName = analystName;
	}
	public String getGrantTitle() {
		return grantTitle;
	}
	public void setGrantTitle(String grantTitle) {
		this.grantTitle = grantTitle;
	}
	public String getInvestigatorName() {
		return investigatorName;
	}
	public void setInvestigatorName(String investigatorName) {
		this.investigatorName = investigatorName;
	}
	@Override
	public String toString() {
		return "GrantSummary [resultCount=" + resultCount + ", frrgrnlGrntCode=" + frrgrnlGrntCode + ", frrgrnlTotal="
				+ frrgrnlTotal + ", frrgrnlBudget=" + frrgrnlBudget + ", frrgrnlYtd=" + frrgrnlYtd
				+ ", frrgrnlReservations=" + frrgrnlReservations + ", frrgrnlEncumbrance=" + frrgrnlEncumbrance
				+ ", agencyName=" + agencyName + ", sponsorId=" + sponsorId + ", status=" + status + ", rowNumber="
				+ rowNumber + ", analystName=" + analystName + ", grantTitle=" + grantTitle + ", investigatorName="
				+ investigatorName + "]";
	}

	
	
	
}
