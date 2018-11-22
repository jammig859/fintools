package com.jam.tree.entity;

public class Account {
	private String accountCode;
	private String title;
	private String pred;
	private String level;

	@Override
	public String toString() {
		return "Account [accountCode=" + accountCode + ", title=" + title + ", pred=" + pred + ", level=" + level + "]";
	}

	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPred() {
		return pred;
	}

	public void setPred(String pred) {
		this.pred = pred;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
