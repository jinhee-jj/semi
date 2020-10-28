package com.kh.cool.fAq.model.vo;

import java.sql.Date;

public class Faq implements java.io.Serializable{

	private int fId; //글1번호
	private int qId; //글2번호
	private String memberId; //작성자 아이디
	private String memberName; //작성자 이름
	private String faqTitle; //게시글 제목
	private String fContents; //질문 내용
	private String aContents; //답변 내용
	private String faqCode; //게시판 코드
	private String faqName; //게시판 이름
	private Date faqDate; //작성일
	private int seeNum; //조회수
	private String faqStatus; //상태
	
	public Faq() {}

	public Faq(int fId, int qId, String memberId, String memberName, String faqTitle, String fContents,
			String aContents, String faqCode, String faqName, Date faqDate, int seeNum, String faqStatus) {
		super();
		this.fId = fId;
		this.qId = qId;
		this.memberId = memberId;
		this.memberName = memberName;
		this.faqTitle = faqTitle;
		this.fContents = fContents;
		this.aContents = aContents;
		this.faqCode = faqCode;
		this.faqName = faqName;
		this.faqDate = faqDate;
		this.seeNum = seeNum;
		this.faqStatus = faqStatus;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
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

	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public String getfContents() {
		return fContents;
	}

	public void setfContents(String fContents) {
		this.fContents = fContents;
	}

	public String getaContents() {
		return aContents;
	}

	public void setaContents(String aContents) {
		this.aContents = aContents;
	}

	public String getFaqCode() {
		return faqCode;
	}

	public void setFaqCode(String faqCode) {
		this.faqCode = faqCode;
	}

	public String getFaqName() {
		return faqName;
	}

	public void setFaqName(String faqName) {
		this.faqName = faqName;
	}

	public Date getFaqDate() {
		return faqDate;
	}

	public void setFaqDate(Date faqDate) {
		this.faqDate = faqDate;
	}

	public int getSeeNum() {
		return seeNum;
	}

	public void setSeeNum(int seeNum) {
		this.seeNum = seeNum;
	}

	public String getFaqStatus() {
		return faqStatus;
	}

	public void setFaqStatus(String faqStatus) {
		this.faqStatus = faqStatus;
	}

	@Override
	public String toString() {
		return "Faq [fId=" + fId + ", qId=" + qId + ", memberId=" + memberId + ", memberName=" + memberName
				+ ", faqTitle=" + faqTitle + ", fContents=" + fContents + ", aContents=" + aContents + ", faqCode="
				+ faqCode + ", faqName=" + faqName + ", faqDate=" + faqDate + ", seeNum=" + seeNum + ", faqStatus="
				+ faqStatus + "]";
	}
	
	
}
