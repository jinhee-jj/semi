package com.kh.cool.inven.model.vo;

import java.sql.Date;

public class Inven implements java.io.Serializable{

	//셀렉트되어 보여져야 할 변수들 모음 
	
	private int igretWeight; //INGREDIENT_CAPACITY 중량
	private int addQuantity; //INGREDIENT_QUANTITY 입고수량
	private String igretCode; //INGREDIENT_CODE 원재료 코드.  BARCODE에서 추출한다.
	private String igretName; //INGREDIENT_NAME 원재료명
	private String igretClass; //INGREDIENT_CLASS_NAME 분류명
	private String invenType; //INGREDIENT_UNIT 사용단위
	private String storageType; //STORAGE_TYPE 보관방식 
	private String igretImg; //INGREDIENT_IMAGE 이미지 
	private Date insertDate; //UPDATE_DATE 입고일자

	private Date exDate; //BARCODE 의 마지막 부분 유통기한
	private Date mfDate; //BARCODE 의 제조일자
	private int efDate; //BARCODE 의 유효일자

	private int igretQuantity; //연산?수량
	private int allQuantity; //연산결과 총수량
	private String buyType; //따로 구현?  구매단위

	private int orderNum; //히스토리 순번으로 조회하려고 할때?쓸듯?
	private String branchCode; //지점코드
	private String Barcode; //바코드
	//여기까지가 1차로 만든 vo변수
	
	//2차
	private String userBranchCode; //사용자 구분용 지점코드(본사/지점용)
	private String sup_code;   //거래처코드
	
	//3차
	private String InvenSelectType; //'입고' OR '출구' OR'제조실수' OR '폐기' OR '기타'


	//기본
	public Inven() {}


	//3차(수정 가능성 있음)
	
	

	public int getIgretWeight() {
		return igretWeight;
	}


	public Inven(int igretWeight, int addQuantity, String igretCode, String igretName, String igretClass,
			String invenType, String storageType, String igretImg, Date insertDate, Date exDate, Date mfDate,
			int efDate, int igretQuantity, int allQuantity, String buyType, int orderNum, String branchCode,
			String barcode, String userBranchCode, String sup_code, String invenSelectType) {
		super();
		this.igretWeight = igretWeight;
		this.addQuantity = addQuantity;
		this.igretCode = igretCode;
		this.igretName = igretName;
		this.igretClass = igretClass;
		this.invenType = invenType;
		this.storageType = storageType;
		this.igretImg = igretImg;
		this.insertDate = insertDate;
		this.exDate = exDate;
		this.mfDate = mfDate;
		this.efDate = efDate;
		this.igretQuantity = igretQuantity;
		this.allQuantity = allQuantity;
		this.buyType = buyType;
		this.orderNum = orderNum;
		this.branchCode = branchCode;
		this.Barcode = barcode;
		this.userBranchCode = userBranchCode;
		this.sup_code = sup_code;
		this.InvenSelectType = invenSelectType;
	}


	public void setIgretWeight(int igretWeight) {
		this.igretWeight = igretWeight;
	}


	public int getAddQuantity() {
		return addQuantity;
	}


	public void setAddQuantity(int addQuantity) {
		this.addQuantity = addQuantity;
	}


	public String getIgretCode() {
		return igretCode;
	}


	public void setIgretCode(String igretCode) {
		this.igretCode = igretCode;
	}


	public String getIgretName() {
		return igretName;
	}


	public void setIgretName(String igretName) {
		this.igretName = igretName;
	}


	public String getIgretClass() {
		return igretClass;
	}


	public void setIgretClass(String igretClass) {
		this.igretClass = igretClass;
	}


	public String getInvenType() {
		return invenType;
	}


	public void setInvenType(String invenType) {
		this.invenType = invenType;
	}


	public String getStorageType() {
		return storageType;
	}


	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}


	public String getIgretImg() {
		return igretImg;
	}


	public void setIgretImg(String igretImg) {
		this.igretImg = igretImg;
	}


	public Date getInsertDate() {
		return insertDate;
	}


	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}


	public Date getExDate() {
		return exDate;
	}


	public void setExDate(Date exDate) {
		this.exDate = exDate;
	}


	public Date getMfDate() {
		return mfDate;
	}


	public void setMfDate(Date mfDate) {
		this.mfDate = mfDate;
	}


	public int getEfDate() {
		return efDate;
	}


	public void setEfDate(int efDate) {
		this.efDate = efDate;
	}


	public int getIgretQuantity() {
		return igretQuantity;
	}


	public void setIgretQuantity(int igretQuantity) {
		this.igretQuantity = igretQuantity;
	}


	public int getAllQuantity() {
		return allQuantity;
	}


	public void setAllQuantity(int allQuantity) {
		this.allQuantity = allQuantity;
	}


	public String getBuyType() {
		return buyType;
	}


	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}


	public int getOrderNum() {
		return orderNum;
	}


	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}


	public String getBranchCode() {
		return branchCode;
	}


	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}


	public String getBarcode() {
		return Barcode;
	}


	public void setBarcode(String barcode) {
		Barcode = barcode;
	}


	public String getUserBranchCode() {
		return userBranchCode;
	}


	public void setUserBranchCode(String userBranchCode) {
		this.userBranchCode = userBranchCode;
	}


	public String getSup_code() {
		return sup_code;
	}


	public void setSup_code(String sup_code) {
		this.sup_code = sup_code;
	}


	public String getInvenSelectType() {
		return InvenSelectType;
	}


	public void setInvenSelectType(String invenSelectType) {
		InvenSelectType = invenSelectType;
	}


	
	@Override
	public String toString() {
		return "Inven [igretWeight=" + igretWeight + ", addQuantity=" + addQuantity + ", igretCode=" + igretCode
				+ ", igretName=" + igretName + ", igretClass=" + igretClass + ", invenType=" + invenType
				+ ", storageType=" + storageType + ", igretImg=" + igretImg + ", insertDate=" + insertDate + ", exDate="
				+ exDate + ", mfDate=" + mfDate + ", efDate=" + efDate + ", igretQuantity=" + igretQuantity
				+ ", allQuantity=" + allQuantity + ", buyType=" + buyType + ", orderNum=" + orderNum + ", branchCode="
				+ branchCode + ", Barcode=" + Barcode + ", userBranchCode=" + userBranchCode + ", sup_code=" + sup_code
				+ ", InvenSelectType=" + InvenSelectType + "]";
	}



}
