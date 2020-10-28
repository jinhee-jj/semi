package com.kh.cool.orderManagement.model.vo;

public class MenuOne {
	private String menuName;
	private String status;
	private String menuImage;
	private String idenCode;
	private String idenName;
	private int idenCapacity;
	private String idenUnit;
	
	public MenuOne() {
		
	}

	public MenuOne(String menuName, String status, String menuImage, String idenCode, String idenName, int idenCapacity,
			String idenUnit) {
		super();
		this.menuName = menuName;
		this.status = status;
		this.menuImage = menuImage;
		this.idenCode = idenCode;
		this.idenName = idenName;
		this.idenCapacity = idenCapacity;
		this.idenUnit = idenUnit;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}

	public String getIdenCode() {
		return idenCode;
	}

	public void setIdenCode(String idenCode) {
		this.idenCode = idenCode;
	}

	public String getIdenName() {
		return idenName;
	}

	public void setIdenName(String idenName) {
		this.idenName = idenName;
	}

	public int getIdenCapacity() {
		return idenCapacity;
	}

	public void setIdenCapacity(int idenCapacity) {
		this.idenCapacity = idenCapacity;
	}

	public String getIdenUnit() {
		return idenUnit;
	}

	public void setIdenUnit(String idenUnit) {
		this.idenUnit = idenUnit;
	}

	@Override
	public String toString() {
		return "MenuOne [menuName=" + menuName + ", status=" + status + ", menuImage=" + menuImage + ", idenCode="
				+ idenCode + ", idenName=" + idenName + ", idenCapacity=" + idenCapacity + ", idenUnit=" + idenUnit
				+ "]";
	}


	
	
}
