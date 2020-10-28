package com.kh.cool.purchase.model.vo;

public class CartIngredient implements java.io.Serializable{
	private String iCode;
	private int num;
	
	public CartIngredient() {}

	public CartIngredient(String iCode, int num) {
		super();
		this.iCode = iCode;
		this.num = num;
	}

	public String getiCode() {
		return iCode;
	}

	public void setiCode(String iCode) {
		this.iCode = iCode;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "CartIngredient [iCode=" + iCode + ", num=" + num + "]";
	}
	
	
}
