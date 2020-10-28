package com.kh.cool.purchase.model.vo;

import java.sql.Date;

public class PurchaseListBR implements java.io.Serializable{
	private int rowNum;              //테이블상에는 없지만 지점별 주문순번 알려주기 위해 부여
	private int purchaseCode;		 //발주번호
	private String purchaseDate;     //발주일자
	private String ingredientCode;   //재료코드
	private String iClassName;       //분류이름
	private String ingredientName;   //재료이름
	private int purchseQuantity;     //구매수량
	private String purchaseUnit;     //구매단위 
	private String branchCode;       //지점코드
	private String branchName;       //지점이름
	private String iNameRep;         //대표재료이름(발주내역 첫페이지에 뜨는 이름)
	private int cnt;                 //한 주문당 전체 재료수
	private long price;              //구매 갯수에 따른 가격
	
	public PurchaseListBR () {}

	public PurchaseListBR(int rowNum, int purchaseCode, String purchaseDate, String ingredientCode, String iClassName,
			String ingredientName, int purchseQuantity, String purchaseUnit, String branchCode, String branchName,
			String iNameRep, int cnt, long price) {
		super();
		this.rowNum = rowNum;
		this.purchaseCode = purchaseCode;
		this.purchaseDate = purchaseDate;
		this.ingredientCode = ingredientCode;
		this.iClassName = iClassName;
		this.ingredientName = ingredientName;
		this.purchseQuantity = purchseQuantity;
		this.purchaseUnit = purchaseUnit;
		this.branchCode = branchCode;
		this.branchName = branchName;
		this.iNameRep = iNameRep;
		this.cnt = cnt;
		this.price = price;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public int getPurchaseCode() {
		return purchaseCode;
	}

	public void setPurchaseCode(int purchaseCode) {
		this.purchaseCode = purchaseCode;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getiClassName() {
		return iClassName;
	}

	public void setiClassName(String iClassName) {
		this.iClassName = iClassName;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public int getPurchseQuantity() {
		return purchseQuantity;
	}

	public void setPurchseQuantity(int purchseQuantity) {
		this.purchseQuantity = purchseQuantity;
	}

	public String getPurchaseUnit() {
		return purchaseUnit;
	}

	public void setPurchaseUnit(String purchaseUnit) {
		this.purchaseUnit = purchaseUnit;
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

	public String getiNameRep() {
		return iNameRep;
	}

	public void setiNameRep(String iNameRep) {
		this.iNameRep = iNameRep;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PurchaseListBR [rowNum=" + rowNum + ", purchaseCode=" + purchaseCode + ", purchaseDate=" + purchaseDate
				+ ", ingredientCode=" + ingredientCode + ", iClassName=" + iClassName + ", ingredientName="
				+ ingredientName + ", purchseQuantity=" + purchseQuantity + ", purchaseUnit=" + purchaseUnit
				+ ", branchCode=" + branchCode + ", branchName=" + branchName + ", iNameRep=" + iNameRep + ", cnt="
				+ cnt + ", price=" + price + "]";
	}

	
	
}