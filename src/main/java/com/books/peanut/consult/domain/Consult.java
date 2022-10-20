package com.books.peanut.consult.domain;

import java.util.Date;


public class Consult {
	private int consultNo;
	private String cMemberId;
	private String cContexts;
	private Date cDate;
	private String cEmail;
	private int titleNo;
	
	public Consult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consult(int consultNo, String cMemberId, String cContexts, Date cDate, String cEmail, int titleNo) {
		super();
		this.consultNo = consultNo;
		this.cMemberId = cMemberId;
		this.cContexts = cContexts;
		this.cDate = cDate;
		this.cEmail = cEmail;
		this.titleNo = titleNo;
	}

	public int getConsultNo() {
		return consultNo;
	}

	public void setConsultNo(int consultNo) {
		this.consultNo = consultNo;
	}

	public String getcMemberId() {
		return cMemberId;
	}

	public void setcMemberId(String cMemberId) {
		this.cMemberId = cMemberId;
	}

	public String getcContexts() {
		return cContexts;
	}

	public void setcContexts(String cContexts) {
		this.cContexts = cContexts;
	}

	public Date getcDate() {
		return cDate;
	}

	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public int getTitleNo() {
		return titleNo;
	}

	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}

	@Override
	public String toString() {
		return "Consult [consultNo=" + consultNo + ", cMemberId=" + cMemberId + ", cContexts=" + cContexts + ", cDate="
				+ cDate + ", cEmail=" + cEmail + ", titleNo=" + titleNo + "]";
	}

	

	

}