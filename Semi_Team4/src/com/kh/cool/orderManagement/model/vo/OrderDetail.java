package com.kh.cool.orderManagement.model.vo;

import java.io.Serializable;

public class OrderDetail implements Serializable{
	private String menuCode;
	private String menuName;
	private int orderQuantity;
	private int orderNo;
	private int orderNumber;
	private String orderStatus;
	private int orderPrice;
	private int orderMenuPrice;
	
	public OrderDetail() {
		
	}

	public OrderDetail(String menuCode, String menuName, int orderQuantity, int orderNo, int orderNumber,
			String orderStatus, int orderPrice) {
		super();
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.orderQuantity = orderQuantity;
		this.orderNo = orderNo;
		this.orderNumber = orderNumber;
		this.orderStatus = orderStatus;
		this.orderPrice = orderPrice;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	public int getOrderMenuPrice() {
		return orderPrice * orderQuantity;
	}
	
	@Override
	public String toString() {
		return "OrderDetail [menuCode=" + menuCode + ", menuName=" + menuName + ", orderQuantity=" + orderQuantity
				+ ", orderNo=" + orderNo + ", orderNumber=" + orderNumber + ", orderStatus=" + orderStatus
				+ ", orderPrice=" + orderPrice + "]";
	}

	
	
	
	
}
