package com.kh.cool.main.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	private String boardTitle;
	private int communityCode;
	private Date boardWriteDate;
	private int boardNo;			//전체 게시물 중 번호(pk)
	private int boardNum;
	
	public Notice() {}

	public Notice(String boardTitle, int communityCode, Date boardWriteDate, int boardNo, int boardNum) {
		super();
		this.boardTitle = boardTitle;
		this.communityCode = communityCode;
		this.boardWriteDate = boardWriteDate;
		this.boardNo = boardNo;
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

	public Date getBoardWriteDate() {
		return boardWriteDate;
	}

	public void setBoardWriteDate(Date boardWriteDate) {
		this.boardWriteDate = boardWriteDate;
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

	@Override
	public String toString() {
		return "Notice [boardTitle=" + boardTitle + ", communityCode=" + communityCode + ", boardWriteDate="
				+ boardWriteDate + ", boardNo=" + boardNo + ", boardNum=" + boardNum + "]";
	}
}
