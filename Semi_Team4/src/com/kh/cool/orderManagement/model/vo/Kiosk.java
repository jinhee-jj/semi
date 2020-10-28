package com.kh.cool.orderManagement.model.vo;

import javafx.scene.image.Image;

public class Kiosk {
	private String menuName;
	private String menuCode;
	private int menuPrice;
	private String menuImage;
	private String menuClassCode;
	private String menuStatus;
	private int menu_cost;
	private String branchCode;
	private String eat;
	
	public Kiosk() {
		
	}

	public Kiosk(String menuName, String menuCode, int menuPrice, String menuImage, String menuClassCode,
			String menuStatus, int menu_cost, String branchCode, String eat) {
		super();
		this.menuName = menuName;
		this.menuCode = menuCode;
		this.menuPrice = menuPrice;
		this.menuImage = menuImage;
		this.menuClassCode = menuClassCode;
		this.menuStatus = menuStatus;
		this.menu_cost = menu_cost;
		this.branchCode = branchCode;
		this.eat = eat;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}

	public String getMenuClassCode() {
		return menuClassCode;
	}

	public void setMenuClassCode(String menuClassCode) {
		this.menuClassCode = menuClassCode;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public int getMenu_cost() {
		return menu_cost;
	}

	public void setMenu_cost(int menu_cost) {
		this.menu_cost = menu_cost;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getEat() {
		return eat;
	}

	public void setEat(String eat) {
		this.eat = eat;
	}

	@Override
	public String toString() {
		return "Kiosk [menuName=" + menuName + ", menuCode=" + menuCode + ", menuPrice=" + menuPrice + ", menuImage="
				+ menuImage + ", menuClassCode=" + menuClassCode + ", menuStatus=" + menuStatus + ", menu_cost="
				+ menu_cost + ", branchCode=" + branchCode + ", eat=" + eat + "]";
	}

	
}
