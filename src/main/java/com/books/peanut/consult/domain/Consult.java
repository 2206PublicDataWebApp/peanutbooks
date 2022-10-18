package com.books.peanut.consult.domain;

import java.util.Date;


public class Consult {
	private int consultNo;
	private int titleNo;
	private String cNickName;
	private String cEmail;
	private String cContexts;
	private Date cDate;
	
	public int getTitleNo() {
		return titleNo;
	}
	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}
	public int getConsultNo() {
		return consultNo;
	}
	public void setConsultNo(int consultNo) {
		this.consultNo = consultNo;
	}
	public String getcNickName() {
		return cNickName;
	}
	public void setcNickName(String cNickName) {
		this.cNickName = cNickName;
	}

	public String getcEmail() {
		return cEmail;
	}
	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
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
	@Override
	public String toString() {
		return "Consult [consultNo=" + consultNo + ", cNickName=" + cNickName + ",cEmail=" + cEmail+", cContexts="
				+ cContexts + "titleNo="+ titleNo+", cDate=" + cDate + "]";
	}
	
	

}