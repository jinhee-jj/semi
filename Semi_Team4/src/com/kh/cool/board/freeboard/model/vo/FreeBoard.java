package com.kh.cool.board.freeboard.model.vo;

import java.sql.Date;

public class FreeBoard {
	private int boardNo;
	private int boardNum;
	private String boardTitle;
	private int communityCode;
	private String communityName;
	private String boardWriterId;
	private Date boardWriteDate;
	private String boardPublic;
	private int boardCount;
	private String boardProcess;
	private String boardContent;
	private String status;
	
	public FreeBoard() {
		
	}

	public FreeBoard(int boardNo, int boardNum, String boardTitle, int communityCode, String communityName,
			String boardWriterId, Date boardWriteDate, String boardPublic, int boardCount, String boardProcess,
			String boardContent, String status) {
		super();
		this.boardNo = boardNo;
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.communityCode = communityCode;
		this.communityName = communityName;
		this.boardWriterId = boardWriterId;
		this.boardWriteDate = boardWriteDate;
		this.boardPublic = boardPublic;
		this.boardCount = boardCount;
		this.boardProcess = boardProcess;
		this.boardContent = boardContent;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FreeBoard [boardNo=" + boardNo + ", boardNum=" + boardNum + ", boardTitle=" + boardTitle
				+ ", communityCode=" + communityCode + ", communityName=" + communityName + ", boardWriterId="
				+ boardWriterId + ", boardWriteDate=" + boardWriteDate + ", boardPublic=" + boardPublic
				+ ", boardCount=" + boardCount + ", boardProcess=" + boardProcess + ", boardContent=" + boardContent
				+ ", status=" + status + "]";
	}



	
}



