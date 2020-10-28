package com.kh.cool.main.model.vo;

public class Order implements java.io.Serializable {
	private String branchCode;
	private String menuName;
	private int orderQuantity;
	private String toGo;
	private String orderStatus;
	
	public Order() {}

	public Order(String branchCode, String menuName, int orderQuantity, String toGo, String orderStatus) {
		super();
		this.branchCode = branchCode;
		this.menuName = menuName;
		this.orderQuantity = orderQuantity;
		this.toGo = toGo;
		this.orderStatus = orderStatus;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public String getToGo() {
		return toGo;
	}

	public void setToGo(String toGo) {
		this.toGo = toGo;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [branchCode=" + branchCode + ", menuName=" + menuName + ", orderQuantity=" + orderQuantity
				+ ", toGo=" + toGo + ", orderStatus=" + orderStatus + "]";
	}
}
