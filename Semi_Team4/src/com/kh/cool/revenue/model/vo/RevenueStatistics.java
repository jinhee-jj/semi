package com.kh.cool.revenue.model.vo;

public class RevenueStatistics implements java.io.Serializable {
	private String date;				//일자
	private String branchCode;			//지점코드
	private int grossIncome;			//매출가
	private int manufactureCost;		//제조원가
	private int margin;					//순이익
	private int marginRate;				//마진율
	
	public RevenueStatistics() {}

	public RevenueStatistics(String date, String branchCode, int grossIncome, int manufactureCost, int margin,
			int marginRate) {
		super();
		this.date = date;
		this.branchCode = branchCode;
		this.grossIncome = grossIncome;
		this.manufactureCost = manufactureCost;
		this.margin = margin;
		this.marginRate = marginRate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public int getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(int grossIncome) {
		this.grossIncome = grossIncome;
	}

	public int getManufactureCost() {
		return manufactureCost;
	}

	public void setManufactureCost(int manufactureCost) {
		this.manufactureCost = manufactureCost;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public int getMarginRate() {
		return marginRate;
	}

	public void setMarginRate(int marginRate) {
		this.marginRate = marginRate;
	}

	@Override
	public String toString() {
		return "RevenueStatistics [date=" + date + ", branchCode=" + branchCode + ", grossIncome=" + grossIncome
				+ ", manufactureCost=" + manufactureCost + ", margin=" + margin + ", marginRate=" + marginRate + "]";
	}
}