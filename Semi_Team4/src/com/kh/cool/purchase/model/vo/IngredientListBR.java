package com.kh.cool.purchase.model.vo;

//원재료 목록 vo
public class IngredientListBR implements java.io.Serializable {
	private String iClassName; //분류 이름
	private String iCode; 	   //재료 코드
	private String iName;	   //재료 이름
	private int iCapacity;     //재료 용량(1개)
	private String iUnit;      //재료 단위(1개)
	private int branchInven;   //매장 재고
	private int iNum;          //장바구니 담았을 때 수량 
	
	public IngredientListBR () {}

	public IngredientListBR(String iClassName, String iCode, String iName, int iCapacity, String iUnit, int branchInven,
			int iNum) {
		super();
		this.iClassName = iClassName;
		this.iCode = iCode;
		this.iName = iName;
		this.iCapacity = iCapacity;
		this.iUnit = iUnit;
		this.branchInven = branchInven;
		this.iNum = iNum;
	}

	public String getiClassName() {
		return iClassName;
	}

	public void setiClassName(String iClassName) {
		this.iClassName = iClassName;
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

	public int getiCapacity() {
		return iCapacity;
	}

	public void setiCapacity(int iCapacity) {
		this.iCapacity = iCapacity;
	}

	public String getiUnit() {
		return iUnit;
	}

	public void setiUnit(String iUnit) {
		this.iUnit = iUnit;
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

	@Override
	public String toString() {
		return "IngredientListBR [iClassName=" + iClassName + ", iCode=" + iCode + ", iName=" + iName + ", iCapacity="
				+ iCapacity + ", iUnit=" + iUnit + ", branchInven=" + branchInven + ", iNum=" + iNum + "]";
	}
	
	
}
