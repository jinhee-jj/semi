package com.kh.cool.purchase.model.vo;

public class Branch implements java.io.Serializable{
	private String branchCode; //지점코드
	private String branchName; //지점명
	
	public Branch() {}

	public Branch(String branchCode, String branchName) {
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
		return "Branch [branchCode=" + branchCode + ", branchName=" + branchName + "]";
	}
	
	
}
