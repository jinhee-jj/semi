package com.kh.cool.management.model.vo;

public class PaperPurchase implements java.io.Serializable{

	private int rNum;              //테이블상에는 없지만 지점별 주문순번 알려주기 위해 부여
	private String branchCode;       //지점코드H
	private String branchName;       //지점이름B
	private String branchAddress;    //지점주소B
	private String branchPhone;   	 //지점연락처B
	private int purchaseCode;		 //지점발주번호D
	private String branchDate;		//지점발주날짜D	
	private String supplierCode;	//거래처코드D
	private String orderStatue;		//발주상태D
	private int purchaseQuantity; //지점발주수량
	private String purchaseUnit;	//지점발주단위
	
	//거래처
	private String customName;//거래처명
	private String supplierRep;//거래처대표자
	private String supplierPhone;//거래처연락처
	private String supplierAddress;//거래처주소
	private String supplierNumber;//거래처사업자번호
	private String supplierEnrollDate;//거래처등록일
	private String supplierEmail;//거래처이메일주소
	private String supplierAccountBank;//거래처은행명
	private String supplierNum;//거래처계좌번호
	private String supplierFax;//거래처팩스번호
	
	//cool
	private String hoInfo;//본사코드
	private String hoName;//회사명
	private String hoRepName;//대표자명
	private String hoPhone;//대표연락처
	private String hoAddress;//사업장주소
	private String hoNo;//사업자번호
	
	//ingredient_info
	private String ingredientCode;//원재료코드
	private String ingredientName;//원재료명
	
	//ingredient_classification
	private String ingredientClassName; //원재료분류명
	
	
	
	
	public PaperPurchase() {}




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




	public String getCustomName() {
		return customName;
	}




	public void setCustomName(String customName) {
		this.customName = customName;
	}




	public String getSupplierRep() {
		return supplierRep;
	}




	public void setSupplierRep(String supplierRep) {
		this.supplierRep = supplierRep;
	}




	public String getSupplierPhone() {
		return supplierPhone;
	}




	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = supplierPhone;
	}




	public String getSupplierAddress() {
		return supplierAddress;
	}




	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}




	public String getSupplierNumber() {
		return supplierNumber;
	}




	public void setSupplierNumber(String supplierNumber) {
		this.supplierNumber = supplierNumber;
	}




	public String getSupplierEnrollDate() {
		return supplierEnrollDate;
	}




	public void setSupplierEnrollDate(String supplierEnrollDate) {
		this.supplierEnrollDate = supplierEnrollDate;
	}




	public String getSupplierEmail() {
		return supplierEmail;
	}




	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}




	public String getSupplierAccountBank() {
		return supplierAccountBank;
	}




	public void setSupplierAccountBank(String supplierAccountBank) {
		this.supplierAccountBank = supplierAccountBank;
	}




	public String getSupplierNum() {
		return supplierNum;
	}




	public void setSupplierNum(String supplierNum) {
		this.supplierNum = supplierNum;
	}




	public String getSupplierFax() {
		return supplierFax;
	}




	public void setSupplierFax(String supplierFax) {
		this.supplierFax = supplierFax;
	}




	public String getHoInfo() {
		return hoInfo;
	}




	public void setHoInfo(String hoInfo) {
		this.hoInfo = hoInfo;
	}




	public String getHoName() {
		return hoName;
	}




	public void setHoName(String hoName) {
		this.hoName = hoName;
	}




	public String getHoRepName() {
		return hoRepName;
	}




	public void setHoRepName(String hoRepName) {
		this.hoRepName = hoRepName;
	}




	public String getHoPhone() {
		return hoPhone;
	}




	public void setHoPhone(String hoPhone) {
		this.hoPhone = hoPhone;
	}




	public String getHoAddress() {
		return hoAddress;
	}




	public void setHoAddress(String hoAddress) {
		this.hoAddress = hoAddress;
	}




	public String getHoNo() {
		return hoNo;
	}




	public void setHoNo(String hoNo) {
		this.hoNo = hoNo;
	}




	public String getIngredientCode() {
		return ingredientCode;
	}




	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}




	public String getIngredientName() {
		return ingredientName;
	}




	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}




	public String getIngredientClassName() {
		return ingredientClassName;
	}




	public void setIngredientClassName(String ingredientClassName) {
		this.ingredientClassName = ingredientClassName;
	}




	@Override
	public String toString() {
		return "PaperPurchase [rNum=" + rNum + ", branchCode=" + branchCode + ", branchName=" + branchName
				+ ", branchAddress=" + branchAddress + ", branchPhone=" + branchPhone + ", purchaseCode=" + purchaseCode
				+ ", branchDate=" + branchDate + ", supplierCode=" + supplierCode + ", orderStatue=" + orderStatue
				+ ", purchaseQuantity=" + purchaseQuantity + ", purchaseUnit=" + purchaseUnit + ", customName="
				+ customName + ", supplierRep=" + supplierRep + ", supplierPhone=" + supplierPhone
				+ ", supplierAddress=" + supplierAddress + ", supplierNumber=" + supplierNumber
				+ ", supplierEnrollDate=" + supplierEnrollDate + ", supplierEmail=" + supplierEmail
				+ ", supplierAccountBank=" + supplierAccountBank + ", supplierNum=" + supplierNum + ", supplierFax="
				+ supplierFax + ", hoInfo=" + hoInfo + ", hoName=" + hoName + ", hoRepName=" + hoRepName + ", hoPhone="
				+ hoPhone + ", hoAddress=" + hoAddress + ", hoNo=" + hoNo + ", ingredientCode=" + ingredientCode
				+ ", ingredientName=" + ingredientName + ", ingredientClassName=" + ingredientClassName + "]";
	}







	
}
