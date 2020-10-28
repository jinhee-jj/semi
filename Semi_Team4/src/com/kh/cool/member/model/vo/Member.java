package com.kh.cool.member.model.vo;

public class Member implements java.io.Serializable{

	private String memberId;  // ID
	private String memberName; //이름
	private String deptCode; //구분
	private String memberPwd; //pwd
	private String memberEmail; //이메일
	private String memberPhone; //연락처
	private String memberDeptCode;//소속코드
	
	
	public Member() {}


	public Member(String memberId, String memberName, String deptCode, String memberPwd, String memberEmail,
			String memberPhone, String memberDeptCode) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.deptCode = deptCode;
		this.memberPwd = memberPwd;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberDeptCode = memberDeptCode;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getDeptCode() {
		return deptCode;
	}


	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public String getMemberPwd() {
		return memberPwd;
	}


	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}


	public String getMemberEmail() {
		return memberEmail;
	}


	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}


	public String getMemberPhone() {
		return memberPhone;
	}


	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}


	public String getMemberDeptCode() {
		return memberDeptCode;
	}


	public void setMemberDeptCode(String memberDeptCode) {
		this.memberDeptCode = memberDeptCode;
	}


	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", deptCode=" + deptCode + ", memberPwd="
				+ memberPwd + ", memberEmail=" + memberEmail + ", memberPhone=" + memberPhone + ", memberDeptCode="
				+ memberDeptCode + "]";
	}


	
	
}