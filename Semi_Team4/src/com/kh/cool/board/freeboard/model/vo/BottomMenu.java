package com.kh.cool.board.freeboard.model.vo;

public class BottomMenu {
	private String menuName;
	private int count;
	private int menuPrice;
	private int allCount;
	
	public BottomMenu() {
		
	}

	public BottomMenu(String menuName, int count, int menuPrice, int allCount) {
		super();
		this.menuName = menuName;
		this.count = count;
		this.menuPrice = menuPrice;
		this.allCount = allCount;
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

	public int getAllCount() {
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	@Override
	public String toString() {
		return "BottomMenu [menuName=" + menuName + ", count=" + count + ", menuPrice=" + menuPrice + ", allCount="
				+ allCount + "]";
	}
	
	
	
}


