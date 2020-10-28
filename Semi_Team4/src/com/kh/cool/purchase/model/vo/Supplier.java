package com.kh.cool.purchase.model.vo;

import java.sql.Date;

public class Supplier implements java.io.Serializable{
	private String sCode;
	private String cName;
	private String rName;
	private String sPhone;
	private String sAddress;
	private String sNum;
	private String enrollDate;
	private String sEmail;
	private String sBank;
	private String sAccount;
	private String sFax;
	
	public Supplier() {}

	public Supplier(String sCode, String cName, String rName, String sPhone, String sAddress, String sNum,
			String enrollDate, String sEmail, String sBank, String sAccount, String sFax) {
		super();
		this.sCode = sCode;
		this.cName = cName;
		this.rName = rName;
		this.sPhone = sPhone;
		this.sAddress = sAddress;
		this.sNum = sNum;
		this.enrollDate = enrollDate;
		this.sEmail = sEmail;
		this.sBank = sBank;
		this.sAccount = sAccount;
		this.sFax = sFax;
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getsPhone() {
		return sPhone;
	}

	public void setsPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public String getsAddress() {
		return sAddress;
	}

	public void setsAddress(String sAddress) {
		this.sAddress = sAddress;
	}

	public String getsNum() {
		return sNum;
	}

	public void setsNum(String sNum) {
		this.sNum = sNum;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getsEmail() {
		return sEmail;
	}

	public void setsEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getsBank() {
		return sBank;
	}

	public void setsBank(String sBank) {
		this.sBank = sBank;
	}

	public String getsAccount() {
		return sAccount;
	}

	public void setsAccount(String sAccount) {
		this.sAccount = sAccount;
	}

	public String getsFax() {
		return sFax;
	}

	public void setsFax(String sFax) {
		this.sFax = sFax;
	}

	@Override
	public String toString() {
		return "Supplier [sCode=" + sCode + ", cName=" + cName + ", rName=" + rName + ", sPhone=" + sPhone
				+ ", sAddress=" + sAddress + ", sNum=" + sNum + ", enrollDate=" + enrollDate + ", sEmail=" + sEmail
				+ ", sBank=" + sBank + ", sAccount=" + sAccount + ", sFax=" + sFax + "]";
	}
	
	
	
	
}
