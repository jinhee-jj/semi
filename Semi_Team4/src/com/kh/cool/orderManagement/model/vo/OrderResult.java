package com.kh.cool.orderManagement.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class OrderResult implements Serializable{
	private int orderNo;
	private Date orderDate;
	private String kioskCode;
	private String togo;
	private int grossIncome;
	private int discountIncome;
	private int totalIncome;
	
	public OrderResult() {
		
	}

	public OrderResult(int orderNo, Date orderDate, String kioskCode, String togo, int grossIncome, int discountIncome,
			int totalIncome) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.kioskCode = kioskCode;
		this.togo = togo;
		this.grossIncome = grossIncome;
		this.discountIncome = discountIncome;
		this.totalIncome = totalIncome;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getKioskCode() {
		return kioskCode;
	}

	public void setKioskCode(String kioskCode) {
		this.kioskCode = kioskCode;
	}

	public String getTogo() {
		return togo;
	}

	public void setTogo(String togo) {
		this.togo = togo;
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

	@Override
	public String toString() {
		return "OrderResult [orderNo=" + orderNo + ", orderDate=" + orderDate + ", kioskCode=" + kioskCode + ", togo="
				+ togo + ", grossIncome=" + grossIncome + ", discountIncome=" + discountIncome + ", totalIncome="
				+ totalIncome + "]";
	}
	
	
	
}
