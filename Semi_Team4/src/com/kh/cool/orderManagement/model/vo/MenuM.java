package com.kh.cool.orderManagement.model.vo;

public class MenuM {
	private String menuClass;
	private String menuImage;
	private String menuCode;
	private String menuName;
	private int menuPrice;
	private String menuStatus;
	
	public MenuM() {
		
	}

	public MenuM(String menuClass, String menuImage, String menuCode, String menuName, int menuPrice,
			String menuStatus) {
		super();
		this.menuClass = menuClass;
		this.menuImage = menuImage;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuStatus = menuStatus;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public String getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
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

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	@Override
	public String toString() {
		return "MenuM [menuClass=" + menuClass + ", menuImage=" + menuImage + ", menuCode=" + menuCode + ", menuName="
				+ menuName + ", menuPrice=" + menuPrice + ", menuStatus=" + menuStatus + "]";
	}
	
	
}
