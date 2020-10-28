package com.kh.cool.qna.model.vo;

public class Attachment implements java.io.Serializable {
	private int attNo;
	private int boardNo;
	private String attFileOriginName;
	private String attFileChangeName;
	private String attFileAddress;
	private int attFileSize;
	private String attStatus;
	
	
	public Attachment () {}


	public Attachment(int attNo, int boardNo, String attFileOriginName, String attFileChangeName, String attFileAddress,
			int attFileSize, String attStatus) {
		super();
		this.attNo = attNo;
		this.boardNo = boardNo;
		this.attFileOriginName = attFileOriginName;
		this.attFileChangeName = attFileChangeName;
		this.attFileAddress = attFileAddress;
		this.attFileSize = attFileSize;
		this.attStatus = attStatus;
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


	public String getAttFileOriginName() {
		return attFileOriginName;
	}


	public void setAttFileOriginName(String attFileOriginName) {
		this.attFileOriginName = attFileOriginName;
	}


	public String getAttFileChangeName() {
		return attFileChangeName;
	}


	public void setAttFileChangeName(String attFileChangeName) {
		this.attFileChangeName = attFileChangeName;
	}


	public String getAttFileAddress() {
		return attFileAddress;
	}


	public void setAttFileAddress(String attFileAddress) {
		this.attFileAddress = attFileAddress;
	}


	public int getAttFileSize() {
		return attFileSize;
	}


	public void setAttFileSize(int attFileSize) {
		this.attFileSize = attFileSize;
	}


	public String getAttStatus() {
		return attStatus;
	}


	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}


	@Override
	public String toString() {
		return "Attachment [attNo=" + attNo + ", boardNo=" + boardNo + ", attFileOriginName=" + attFileOriginName
				+ ", attFileChangeName=" + attFileChangeName + ", attFileAddress=" + attFileAddress + ", attFileSize="
				+ attFileSize + ", attStatus=" + attStatus + "]";
	}

	
}
