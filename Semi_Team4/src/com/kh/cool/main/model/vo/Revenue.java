package com.kh.cool.main.model.vo;

public class Revenue implements java.io.Serializable {
	private String branchName;		//지점명
	private int grossIncome;		//매출
	
	public Revenue () {}

	public Revenue(String branchName, int grossIncome) {
		super();
		this.branchName = branchName;
		this.grossIncome = grossIncome;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(int grossIncome) {
		this.grossIncome = grossIncome;
	}

	@Override
	public String toString() {
		return "Revenue [branchName=" + branchName + ", grossIncome=" + grossIncome + "]";
	}
}
