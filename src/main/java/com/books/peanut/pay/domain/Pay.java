package com.books.peanut.pay.domain;

public class Pay {
	private String orderNo;
	private int payPoint;
	private String orderStatus;
	private String memberId;
	private String memberEmail;
	private String orderContents;
	
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pay(String orderNo, int payPoint, String orderStatus, String memberId, String memberEmail, String orderContents) {
		super();
		this.orderNo = orderNo;
		this.payPoint = payPoint;
		this.orderStatus = orderStatus;
		this.memberId = memberId;
		this.memberEmail = memberEmail;
		this.orderContents = orderContents;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public int getPayPoint() {
		return payPoint;
	}

	public void setPayPoint(int payPoint) {
		this.payPoint = payPoint;
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

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getOrderContents() {
		return orderContents;
	}

	public void setOrderContents(String orderContents) {
		this.orderContents = orderContents;
	}

	@Override
	public String toString() {
		return "Pay [orderNo=" + orderNo + ", payPoint=" + payPoint + ", orderStatus=" + orderStatus + ", memberId=" + memberId
				+ ", memberEmail=" + memberEmail + ", orderContents=" + orderContents + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
