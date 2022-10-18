package com.books.peanut.pay.domain;

import java.util.Date;

public class SeasonTicket{
	private String memberId;
	private String orderName;
	private Date orderDate;
	private int orderNo;
	private String contents;
	private Date lastDate;
	
	public SeasonTicket() {
		super();		
	}

	public SeasonTicket(String memberId, String orderName, Date orderDate, int orderNo, String contents,
			Date lastDate) {
		super();
		this.memberId = memberId;
		this.orderName = orderName;
		this.orderDate = orderDate;
		this.orderNo = orderNo;
		this.contents = contents;
		this.lastDate = lastDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	@Override
	public String toString() {
		return "SeasonTicket [memberId=" + memberId + ", orderName=" + orderName + ", orderDate=" + orderDate
				+ ", orderNo=" + orderNo + ", contents=" + contents + ", lastDate=" + lastDate + "]";
	}
	
	
	
	
}