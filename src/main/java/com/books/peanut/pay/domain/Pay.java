package com.books.peanut.pay.domain;

public class Pay {
	private String orderNo;
	private int pay;
	private String orderStatus;
	private String memberId;	
	private String orderContents;	
	private String imp_uid;
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pay(String orderNo, int pay, String orderStatus, String memberId, String orderContents, String imp_uid) {
		super();
		this.orderNo = orderNo;
		this.pay = pay;
		this.orderStatus = orderStatus;
		this.memberId = memberId;
		this.orderContents = orderContents;
		this.imp_uid = imp_uid;
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
	public String getImp_uid() {
		return imp_uid;
	}
	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
	}
	@Override
	public String toString() {
		return "Pay [orderNo=" + orderNo + ", pay=" + pay + ", orderStatus=" + orderStatus + ", memberId=" + memberId
				+ ", orderContents=" + orderContents + ", imp_uid=" + imp_uid + "]";
	}

	
	
}
