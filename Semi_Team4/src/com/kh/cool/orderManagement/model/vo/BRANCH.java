package com.kh.cool.orderManagement.model.vo;

public class BRANCH {
	private String branchCode;
	private String branchName;
	
	public BRANCH() {
		
	}

	public BRANCH(String branchCode, String branchName) {
		super();
		this.branchCode = branchCode;
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Override
	public String toString() {
		return "BRANCH [branchCode=" + branchCode + ", branchName=" + branchName + "]";
	}
	
	
	
}
