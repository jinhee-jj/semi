package com.kh.cool.purchase.model.vo;

public class SupplierOrder implements java.io.Serializable{
	private int rownum; //순번
	private String hoPurchaseDate; //본사 발주일자
	private String brPurchaseDate; //지점 발주일자
	private int hoPurchaseCode; //본사 발주번호
	private int brPurchaseCode; //지점 발주번호
	private String supCode; //거래처코드
	private String supName; //거래처명
	private String iCode; //재료코드
	private String iName; //재료이름
	private int iCapacity; //재료용량
	private String iUnit; //재료단위
	private long price; //재료가격
	private long sumPrice; //총가격
	private int cnt; //갯수
	
	public SupplierOrder() {}

	public SupplierOrder(int rownum, String hoPurchaseDate, String brPurchaseDate, int hoPurchaseCode,
			int brPurchaseCode, String supCode, String supName, String iCode, String iName, int iCapacity, String iUnit,
			long price, long sumPrice, int cnt) {
		super();
		this.rownum = rownum;
		this.hoPurchaseDate = hoPurchaseDate;
		this.brPurchaseDate = brPurchaseDate;
		this.hoPurchaseCode = hoPurchaseCode;
		this.brPurchaseCode = brPurchaseCode;
		this.supCode = supCode;
		this.supName = supName;
		this.iCode = iCode;
		this.iName = iName;
		this.iCapacity = iCapacity;
		this.iUnit = iUnit;
		this.price = price;
		this.sumPrice = sumPrice;
		this.cnt = cnt;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public String getHoPurchaseDate() {
		return hoPurchaseDate;
	}

	public void setHoPurchaseDate(String hoPurchaseDate) {
		this.hoPurchaseDate = hoPurchaseDate;
	}

	public String getBrPurchaseDate() {
		return brPurchaseDate;
	}

	public void setBrPurchaseDate(String brPurchaseDate) {
		this.brPurchaseDate = brPurchaseDate;
	}

	public int getHoPurchaseCode() {
		return hoPurchaseCode;
	}

	public void setHoPurchaseCode(int hoPurchaseCode) {
		this.hoPurchaseCode = hoPurchaseCode;
	}

	public int getBrPurchaseCode() {
		return brPurchaseCode;
	}

	public void setBrPurchaseCode(int brPurchaseCode) {
		this.brPurchaseCode = brPurchaseCode;
	}

	public String getSupCode() {
		return supCode;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	public String getSupName() {
		return supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
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

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(long sumPrice) {
		this.sumPrice = sumPrice;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "SupplierOrder [rownum=" + rownum + ", hoPurchaseDate=" + hoPurchaseDate + ", brPurchaseDate="
				+ brPurchaseDate + ", hoPurchaseCode=" + hoPurchaseCode + ", brPurchaseCode=" + brPurchaseCode
				+ ", supCode=" + supCode + ", supName=" + supName + ", iCode=" + iCode + ", iName=" + iName
				+ ", iCapacity=" + iCapacity + ", iUnit=" + iUnit + ", price=" + price + ", sumPrice=" + sumPrice
				+ ", cnt=" + cnt + "]";
	}

	

	
	
	
}
