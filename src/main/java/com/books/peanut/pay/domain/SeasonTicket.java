package com.books.peanut.pay.domain;

import java.util.Date;

public class SeasonTicket{
	private String memberId;
	private String orderNo;
	private Date orderDate;
	private Date lastDate;
	private String expiry_yn;
	

	
	public SeasonTicket() {
		super();		
	}



	public SeasonTicket(String memberId, String orderNo, Date orderDate, Date lastDate, String expiry_yn) {
		super();
		this.memberId = memberId;
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.lastDate = lastDate;
		this.expiry_yn = expiry_yn;
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



	public Date getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}



	public Date getLastDate() {
		return lastDate;
	}



	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}



	public String getExpiry_yn() {
		return expiry_yn;
	}



	public void setExpiry_yn(String expiry_yn) {
		this.expiry_yn = expiry_yn;
	}



	@Override
	public String toString() {
		return "SeasonTicket [memberId=" + memberId + ", orderNo=" + orderNo + ", orderDate=" + orderDate
				+ ", lastDate=" + lastDate + ", expiry_yn=" + expiry_yn + "]";
	}
	
	


	

	
		
	
	
}