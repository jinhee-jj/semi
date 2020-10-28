package com.kh.cool.notice.model.vo;

public class Attachment {
	private int attNo;     //첨부파일번호
	private int boardNo;   //게시글번호
	private String originName;   //파일명
	private String changeName;   //변경된파일명
	private String filePath;     //경로
	private String fileSize;     //용량
	private String status;       //상태
	
	public Attachment() {}

	public Attachment(int attNo, int boardNo, String originName, String changeName, String filePath, String fileSize,
			String status) {
		super();
		this.attNo = attNo;
		this.boardNo = boardNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.status = status;
	}

	public int getAttNo() {
		return attNo;
	}

	public void setAttNo(int attNo) {
		this.attNo = attNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Attachment [attNo=" + attNo + ", boardNo=" + boardNo + ", originName=" + originName + ", changeName="
				+ changeName + ", filePath=" + filePath + ", fileSize=" + fileSize + ", status=" + status + "]";
	}
	
	
}
