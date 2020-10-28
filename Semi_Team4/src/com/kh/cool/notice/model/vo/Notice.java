package com.kh.cool.notice.model.vo;

import java.sql.Date;

public class Notice {
	private String nTitle; //공지사항 제목
	private int nCode;    //게시판 코드
	private String nName; //게시판 이름
	private String nWriter; //공지사항 작성자
	private Date nDate; //공지사항 작성일
	private String nPublic; //공개여부
	private int nCount; //조회수
	private String nContent; //내용
	private int boardNo; //게시판 전체 글번호
	private int nno;     //공지사항 번호
	private String status; //상태
	
	public Notice() {}

	public Notice(String nTitle, int nCode, String nName, String nWriter, Date nDate, String nPublic, int nCount,
			String nContent, int boardNo, int nno, String status) {
		super();
		this.nTitle = nTitle;
		this.nCode = nCode;
		this.nName = nName;
		this.nWriter = nWriter;
		this.nDate = nDate;
		this.nPublic = nPublic;
		this.nCount = nCount;
		this.nContent = nContent;
		this.boardNo = boardNo;
		this.nno = nno;
		this.status = status;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public int getnCode() {
		return nCode;
	}

	public void setnCode(int nCode) {
		this.nCode = nCode;
	}

	public String getnName() {
		return nName;
	}

	public void setnName(String nName) {
		this.nName = nName;
	}

	public String getnWriter() {
		return nWriter;
	}

	public void setnWriter(String nwriter) {
		this.nWriter = nwriter;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	public String getnPublic() {
		return nPublic;
	}

	public void setnPublic(String nPublic) {
		this.nPublic = nPublic;
	}

	public int getnCount() {
		return nCount;
	}

	public void setnCount(int nCount) {
		this.nCount = nCount;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Notice [nTitle=" + nTitle + ", nCode=" + nCode + ", nName=" + nName + ", nWriter=" + nWriter
				+ ", nDate=" + nDate + ", nPublic=" + nPublic + ", nCount=" + nCount + ", nContent=" + nContent
				+ ", boardNo=" + boardNo + ", nno=" + nno + ", status=" + status + "]";
	}
	
	
 }
