package com.kh.cool.purchase.model.vo;

public class SupplierIngredient implements java.io.Serializable{
	private String sCode; //거래처 코드
	private String iCode; //재료 코드
	private String iName; //재료 이름
	private String iUnit; //재료 단위
	private int iCapacity; //재료 용량
	private String iClassCode; // 재료 분류코드
	private String iClassName; // 재료 이름
	private int branchInven; //매장재고
	private int iNum;          //장바구니 담았을 때 수량 
	private String branchCode;
	
	public SupplierIngredient() {}

	public SupplierIngredient(String sCode, String iCode, String iName, String iUnit, int iCapacity, String iClassCode,
			String iClassName, int branchInven, int iNum, String branchCode) {
		super();
		this.sCode = sCode;
		this.iCode = iCode;
		this.iName = iName;
		this.iUnit = iUnit;
		this.iCapacity = iCapacity;
		this.iClassCode = iClassCode;
		this.iClassName = iClassName;
		this.branchInven = branchInven;
		this.iNum = iNum;
		this.branchCode = branchCode;
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getiCode() {
		return iCode;
	}

	public void setiCode(String iCode) {
		this.iCode = iCode;
	}

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getiUnit() {
		return iUnit;
	}

	public void setiUnit(String iUnit) {
		this.iUnit = iUnit;
	}

	public int getiCapacity() {
		return iCapacity;
	}

	public void setiCapacity(int iCapacity) {
		this.iCapacity = iCapacity;
	}

	public String getiClassCode() {
		return iClassCode;
	}

	public void setiClassCode(String iClassCode) {
		this.iClassCode = iClassCode;
	}

	public String getiClassName() {
		return iClassName;
	}

	public void setiClassName(String iClassName) {
		this.iClassName = iClassName;
	}

	public int getBranchInven() {
		return branchInven;
	}

	public void setBranchInven(int branchInven) {
		this.branchInven = branchInven;
	}

	public int getiNum() {
		return iNum;
	}

	public void setiNum(int iNum) {
		this.iNum = iNum;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	@Override
	public String toString() {
		return "SupplierIngredient [sCode=" + sCode + ", iCode=" + iCode + ", iName=" + iName + ", iUnit=" + iUnit
				+ ", iCapacity=" + iCapacity + ", iClassCode=" + iClassCode + ", iClassName=" + iClassName
				+ ", branchInven=" + branchInven + ", iNum=" + iNum + ", branchCode=" + branchCode + "]";
	}

}