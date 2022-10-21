package com.books.peanut.pay.domain;

public class PeanutPoint {
	private int peanutNo;
	private String ppDate;
	private int peanutPoint;
	private String ppStatus;
	private String bookNo;
	private String bookName;
	private String memberId;
	private String orderNo;
	
	
	public PeanutPoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PeanutPoint(int peanutNo, String ppDate, int peanutPoint, String ppStatus, String bookNo, String bookName,
			String memberId, String orderNo) {
		super();
		this.peanutNo = peanutNo;
		this.ppDate = ppDate;
		this.peanutPoint = peanutPoint;
		this.ppStatus = ppStatus;
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.memberId = memberId;
		this.orderNo = orderNo;
	}



	public int getPeanutNo() {
		return peanutNo;
	}
	public void setPeanutNo(int peanutNo) {
		this.peanutNo = peanutNo;
	}
	public String getPpDate() {
		return ppDate;
	}
	public void setPpDate(String ppDate) {
		this.ppDate = ppDate;
	}
	public int getPeanutPoint() {
		return peanutPoint;
	}
	public void setPeanutPoint(int peanutPoint) {
		this.peanutPoint = peanutPoint;
	}
	public String getPpStatus() {
		return ppStatus;
	}
	public void setPpStatus(String ppStatus) {
		this.ppStatus = ppStatus;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
	

}
