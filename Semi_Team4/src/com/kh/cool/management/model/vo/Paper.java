package com.kh.cool.management.model.vo;

import java.sql.Date;

public class Paper implements java.io.Serializable{

	private int rNum;              //테이블상에는 없지만 지점별 주문순번 알려주기 위해 부여
	private String branchCode;       //지점코드H
	private String branchName;       //지점이름B
	private String branchAddress;    //지점주소B
	private String branchPhone;   	 //지점연락처B
	private int purchaseCode;		 //지점발주번호D
	private String branchDate;		//지점발주날짜D	
	private String supplierCode;	//거래처코드D
	private String orderStatue;		//발주상태D
	private String IngredientCode;	//원재료코드
	private int purchaseQuantity; //지점발주수량
	private String purchaseUnit;	//지점발주단위
	
	public Paper() {}

	public Paper(int rNum, String branchCode, String branchName, String branchAddress, String branchPhone,
			int purchaseCode, String branchDate, String supplierCode, String orderStatue, String ingredientCode,
			int purchaseQuantity, String purchaseUnit) {
		super();
		this.rNum = rNum;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.branchAddress = branchAddress;
		this.branchPhone = branchPhone;
		this.purchaseCode = purchaseCode;
		this.branchDate = branchDate;
		this.supplierCode = supplierCode;
		this.orderStatue = orderStatue;
		IngredientCode = ingredientCode;
		this.purchaseQuantity = purchaseQuantity;
		this.purchaseUnit = purchaseUnit;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getBranchPhone() {
		return branchPhone;
	}

	public void setBranchPhone(String branchPhone) {
		this.branchPhone = branchPhone;
	}

	public int getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(int purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getBranchDate() {
		return branchDate;
	}

	public void setBranchDate(String branchDate) {
		this.branchDate = branchDate;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getOrderStatue() {
		return orderStatue;
	}

	public void setOrderStatue(String orderStatue) {
		this.orderStatue = orderStatue;
	}

	public String getIngredientCode() {
		return IngredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		IngredientCode = ingredientCode;
	}

	public int getPurchaseQuantity() {
		return purchaseQuantity;
	}

	public void setPurchaseQuantity(int purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}

	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
	}

	@Override
	public String toString() {
		return "Paper [rNum=" + rNum + ", branchCode=" + branchCode + ", branchName=" + branchName + ", branchAddress="
				+ branchAddress + ", branchPhone=" + branchPhone + ", purchaseCode=" + purchaseCode + ", branchDate="
				+ branchDate + ", supplierCode=" + supplierCode + ", orderStatue=" + orderStatue + ", IngredientCode="
				+ IngredientCode + ", purchaseQuantity=" + purchaseQuantity + ", purchaseUnit=" + purchaseUnit + "]";
	}

	
	
	
}
