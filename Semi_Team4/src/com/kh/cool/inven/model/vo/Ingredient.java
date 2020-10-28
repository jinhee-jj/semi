package com.kh.cool.inven.model.vo;

public class Ingredient implements java.io.Serializable{
	
	private String igCode;	//원재료 코드
	private String igName;	//원재료 이름
	private String igClass;	//원재료 분류코드
	private String igInClass; //원재료 분류코드에 붙을 값
	private String igUnit;	//원재료 단위
	private int igCapacity;	//원재료 중량
	private String igSType;	//원재료 보관분류
	private String igImageFilePath;	//원재료 이미지 경로
	private String originName;	//이미지 원래이름
	private String saveName;	//이미지 바뀐이름 
	private String igStatus;	//원재료 상태
	
	public Ingredient() {}
	
	
	public Ingredient(String igCode, String igName, String igClass, String igInClass, String igUnit, int igCapacity,
			String igSType, String igImageFilePath, String originName, String saveName, String igStatus) {
		super();
		this.igCode = igCode;
		this.igName = igName;
		this.igClass = igClass;
		this.igInClass = igInClass;
		this.igUnit = igUnit;
		this.igCapacity = igCapacity;
		this.igSType = igSType;
		this.igImageFilePath = igImageFilePath;
		this.originName = originName;
		this.saveName = saveName;
		this.igStatus = igStatus;
	}





	public String getIgCode() {
		return igCode;
	}

	public void setIgCode(String igCode) {
		this.igCode = igCode;
	}

	public String getIgName() {
		return igName;
	}

	public void setIgName(String igName) {
		this.igName = igName;
	}

	public String getIgClass() {
		return igClass;
	}

	public void setIgClass(String igClass) {
		this.igClass = igClass;
	}


	public String getIgUnit() {
		return igUnit;
	}

	public void setIgUnit(String igUnit) {
		this.igUnit = igUnit;
	}

	public int getIgCapacity() {
		return igCapacity;
	}

	public void setIgCapacity(int igCapacity) {
		this.igCapacity = igCapacity;
	}

	public String getIgSType() {
		return igSType;
	}

	public void setIgSType(String igSType) {
		this.igSType = igSType;
	}


	public String getIgStatus() {
		return igStatus;
	}

	public void setIgStatus(String igStatus) {
		this.igStatus = igStatus;
	}

	
	
	public String getIgInClass() {
		return igInClass;
	}


	public void setIgInClass(String igInClass) {
		this.igInClass = igInClass;
	}


	public String getIgImageFilePath() {
		return igImageFilePath;
	}

	public void setIgImageFilePath(String igImageFilePath) {
		this.igImageFilePath = igImageFilePath;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}


	@Override
	public String toString() {
		return "Ingredient [igCode=" + igCode + ", igName=" + igName + ", igClass=" + igClass + ", igInClass="
				+ igInClass + ", igUnit=" + igUnit + ", igCapacity=" + igCapacity + ", igSType=" + igSType
				+ ", igImageFilePath=" + igImageFilePath + ", originName=" + originName + ", saveName=" + saveName
				+ ", igStatus=" + igStatus + "]";
	}

	
	

}
