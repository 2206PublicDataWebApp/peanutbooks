package com.books.peanut.pay.domain;

import java.util.Date;

public class PeanutPoint {
	private int peanutNo;
	private Date ppDate;
	private int peanutPoint;
	private String ppStatus;
	private String bookNo;
	private String bookName;
	private String memberId;
	private String orderNo;
	private String seriesNo;
	public PeanutPoint() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PeanutPoint(int peanutNo, Date ppDate, int peanutPoint, String ppStatus, String bookNo, String bookName,
			String memberId, String orderNo, String seriesNo) {
		super();
		this.peanutNo = peanutNo;
		this.ppDate = ppDate;
		this.peanutPoint = peanutPoint;
		this.ppStatus = ppStatus;
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.memberId = memberId;
		this.orderNo = orderNo;
		this.seriesNo = seriesNo;
	}
	public int getPeanutNo() {
		return peanutNo;
	}
	public void setPeanutNo(int peanutNo) {
		this.peanutNo = peanutNo;
	}
	public Date getPpDate() {
		return ppDate;
	}
	public void setPpDate(Date ppDate) {
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
	public String getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}
	@Override
	public String toString() {
		return "PeanutPoint [peanutNo=" + peanutNo + ", ppDate=" + ppDate + ", peanutPoint=" + peanutPoint
				+ ", ppStatus=" + ppStatus + ", bookNo=" + bookNo + ", bookName=" + bookName + ", memberId=" + memberId
				+ ", orderNo=" + orderNo + ", seriesNo=" + seriesNo + "]";
	}
	
	
	
	
	
	
	
	
	
	
	

}
