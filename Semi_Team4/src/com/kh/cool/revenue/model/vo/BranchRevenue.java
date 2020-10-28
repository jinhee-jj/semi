package com.kh.cool.revenue.model.vo;

import java.sql.Date;

public class BranchRevenue implements java.io.Serializable{
	private String branchCode;	//지점코드
	private Date orderDate;			//주문일
	private int grossIncome;		//총매출
	private int discountIncome;		//할인금액
	private int totalIncome;		//최종결제금액
	private int margin;				//마진
	
	public BranchRevenue() {}

	public BranchRevenue(String branchCode, Date orderDate, int grossIncome, int discountIncome, int totalIncome,
			int margin) {
		super();
		this.branchCode = branchCode;
		this.orderDate = orderDate;
		this.grossIncome = grossIncome;
		this.discountIncome = discountIncome;
		this.totalIncome = totalIncome;
		this.margin = margin;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(int grossIncome) {
		this.grossIncome = grossIncome;
	}

	public int getDiscountIncome() {
		return discountIncome;
	}

	public void setDiscountIncome(int discountIncome) {
		this.discountIncome = discountIncome;
	}

	public int getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(int totalIncome) {
		this.totalIncome = totalIncome;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	@Override
	public String toString() {
		return "BranchRevenue [branchCode=" + branchCode + ", orderDate=" + orderDate + ", grossIncome=" + grossIncome
				+ ", discountIncome=" + discountIncome + ", totalIncome=" + totalIncome + ", margin=" + margin + "]";
	}
}
