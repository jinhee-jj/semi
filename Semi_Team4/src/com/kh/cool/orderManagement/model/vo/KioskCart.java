package com.kh.cool.orderManagement.model.vo;

import java.io.Serializable;

public class KioskCart implements Serializable{
	private String eat;
	private int basketNum;
	private String menuCode;
	private String image;
	private String menuName;
	private int count;
	private int menuPrice;
	private int menuClass;
	private String branchCode;
	private int allCount;
	
	public KioskCart() {
		
	}

	public KioskCart(String eat, int basketNum, String menuCode, String image, String menuName, int count,
			int menuPrice, String branchCode, int allCount) {
		super();
		this.eat = eat;
		this.basketNum = basketNum;
		this.menuCode = menuCode;
		this.image = image;
		this.menuName = menuName;
		this.count = count;
		this.menuPrice = menuPrice;
		this.branchCode = branchCode;
		this.allCount = allCount;
	}

	public String getEat() {
		return eat;
	}

	public void setEat(String eat) {
		this.eat = eat;
	}

	public int getBasketNum() {
		return basketNum;
	}

	public void setBasketNum(int basketNum) {
		this.basketNum = basketNum;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public int getMenuClass() {
		menuClass = this.menuPrice * this.count;
		
		return menuClass;
	}
	
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	@Override
	public String toString() {
		return "KioskCart [eat=" + eat + ", basketNum=" + basketNum + ", menuCode=" + menuCode + ", image=" + image
				+ ", menuName=" + menuName + ", count=" + count + ", menuPrice=" + menuPrice + ", branchCode="
				+ branchCode + ", allCount=" + allCount + "]";
	}


	
	
}
