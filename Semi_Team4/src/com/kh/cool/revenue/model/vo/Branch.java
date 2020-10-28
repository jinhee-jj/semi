package com.kh.cool.revenue.model.vo;

public class Branch implements java.io.Serializable {
	private String branchCode;
	private String branchName;
	private String branchAddress;
	private String branchPhone;
	
	public Branch() {}

	public Branch(String branchCode, String branchName, String branchAddress, String branchPhone) {
		super();
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.branchPhone = branchPhone;
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

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchPhone() {
		return branchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

	@Override
	public String toString() {
		return "Branch [branchCode=" + branchCode + ", branchName=" + branchName + ", branchAddress=" + branchAddress
				+ ", branchPhone=" + branchPhone + "]";
	}
}
