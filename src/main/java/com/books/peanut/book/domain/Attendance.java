package com.books.peanut.book.domain;

import java.sql.Date;

public class Attendance {
	
	private Date first;
	private Date second;
	private Date third;
	private Date forth;
	private Date fifth;
	private String memberId;
	public Date getFirst() {
		return first;
	}
	public void setFirst(Date first) {
		this.first = first;
	}
	public Date getSecond() {
		return second;
	}
	public void setSecond(Date second) {
		this.second = second;
	}
	public Date getThird() {
		return third;
	}
	public void setThird(Date third) {
		this.third = third;
	}
	public Date getForth() {
		return forth;
	}
	public void setForth(Date forth) {
		this.forth = forth;
	}
	public Date getFifth() {
		return fifth;
	}
	public void setFifth(Date fifth) {
		this.fifth = fifth;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	
}
