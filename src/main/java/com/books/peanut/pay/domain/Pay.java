package com.books.peanut.pay.domain;

public class Pay {
	private String orderNo;
	private int pay;
	private String orderStatus;
	private String memberId;	
	private String orderContents;	
	
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pay(String orderNo, int pay, String orderStatus, String memberId, String orderContents) {
		super();
		this.orderNo = orderNo;
		this.pay = pay;
		this.orderStatus = orderStatus;
		this.memberId = memberId;
		this.orderContents = orderContents;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getOrderContents() {
		return orderContents;
	}

	public void setOrderContents(String orderContents) {
		this.orderContents = orderContents;
	}

	@Override
	public String toString() {
		return "Pay [orderNo=" + orderNo + ", pay=" + pay + ", orderStatus=" + orderStatus + ", memberId=" + memberId
				+ ", orderContents=" + orderContents + "]";
	}
	
	
}
