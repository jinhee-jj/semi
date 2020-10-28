package com.kh.cool.qna.model.vo;

import java.sql.Date;

public class Comment implements java.io.Serializable {
	private int communityCode;
	private String commentContent;
	private Date commentWriteDate;
	private int boardNo;
	private int commentNo;
	private String commentWriterId;
	
	public Comment() {}

	public Comment(int communityCode, String commentContent, Date commentWriteDate, int boardNo, int commentNo,
			String commentWriterId) {
		super();
		this.communityCode = communityCode;
		this.commentContent = commentContent;
		this.commentWriteDate = commentWriteDate;
		this.boardNo = boardNo;
		this.commentNo = commentNo;
		this.commentWriterId = commentWriterId;
	}

	public int getCommunityCode() {
		return communityCode;
	}

	public void setCommunityCode(int communityCode) {
		this.communityCode = communityCode;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCommentWriteDate() {
		return commentWriteDate;
	}

	public void setCommentWriteDate(Date commentWriteDate) {
		this.commentWriteDate = commentWriteDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentWriterId() {
		return commentWriterId;
	}

	public void setCommentWriterId(String commentWriterId) {
		this.commentWriterId = commentWriterId;
	}

	@Override
	public String toString() {
		return "Comment [communityCode=" + communityCode + ", commentContent=" + commentContent + ", commentWriteDate="
				+ commentWriteDate + ", boardNo=" + boardNo + ", commentNo=" + commentNo + ", commentWriterId="
				+ commentWriterId + "]";
	}
	
}
