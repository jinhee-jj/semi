package com.kh.cool.qna.model.vo;

import java.sql.Date;

public class QnaBoard implements java.io.Serializable {
	private String boardTitle;
	private int communityCode;
	private String communityName;
	private String boardWriterId;
	private String memberName;
	private Date boardWriteDate;
	private String boardPublic;		//공개여부
	private int boardCount;
	private String boardProcess;	//관리자 처리여부
	private String boardContent; 
	private int boardNo;			//전체 게시물 중 번호(pk)
	private int boardNum;			//해당 카테고리 내 번호
	private String status;			//게시여부(Y/N)
	
	public QnaBoard() {}

	public QnaBoard(String boardTitle, int communityCode, String communityName, String boardWriterId, String memberName,
			Date boardWriteDate, String boardPublic, int boardCount, String boardProcess, String boardContent,
			int boardNo, int boardNum, String status) {
		super();
		this.boardTitle = boardTitle;
		this.communityCode = communityCode;
		this.communityName = communityName;
		this.boardWriterId = boardWriterId;
		this.memberName = memberName;
		this.boardWriteDate = boardWriteDate;
		this.boardPublic = boardPublic;
		this.boardCount = boardCount;
		this.boardProcess = boardProcess;
		this.boardContent = boardContent;
		this.boardNo = boardNo;
		this.boardNum = boardNum;
		this.status = status;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public int getCommunityCode() {
		return communityCode;
	}

	public void setCommunityCode(int communityCode) {
		this.communityCode = communityCode;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBoardWriterId() {
		return boardWriterId;
	}

	public void setBoardWriterId(String boardWriterId) {
		this.boardWriterId = boardWriterId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getBoardWriteDate() {
		return boardWriteDate;
	}

	public void setBoardWriteDate(Date boardWriteDate) {
		this.boardWriteDate = boardWriteDate;
	}

	public String getBoardPublic() {
		return boardPublic;
	}

	public void setBoardPublic(String boardPublic) {
		this.boardPublic = boardPublic;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public String getBoardProcess() {
		return boardProcess;
	}

	public void setBoardProcess(String boardProcess) {
		this.boardProcess = boardProcess;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "QnaBoard [boardTitle=" + boardTitle + ", communityCode=" + communityCode + ", communityName="
				+ communityName + ", boardWriterId=" + boardWriterId + ", memberName=" + memberName
				+ ", boardWriteDate=" + boardWriteDate + ", boardPublic=" + boardPublic + ", boardCount=" + boardCount
				+ ", boardProcess=" + boardProcess + ", boardContent=" + boardContent + ", boardNo=" + boardNo
				+ ", boardNum=" + boardNum + ", status=" + status + "]";
	}

}
