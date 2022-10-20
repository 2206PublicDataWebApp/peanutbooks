package com.books.peanut.consult.domain;

import java.util.Date;

public class ConsultServer {
	private int titleNo;    //titleNo로 변경된다.
	private String csMemberId;
	private String csTitle;
	private Date csDate;
	private String csMail;
	private String csResult;
	public ConsultServer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ConsultServer(int titleNo, String csMemberId, String csTitle, Date csDate, String csMail, String csResult) {
		super();
		this.titleNo = titleNo;
		this.csMemberId = csMemberId;
		this.csTitle = csTitle;
		this.csDate = csDate;
		this.csMail = csMail;
		this.csResult = csResult;
	}
	public int getTitleNo() {
		return titleNo;
	}
	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}
	public String getCsMemberId() {
		return csMemberId;
	}
	public void setCsMemberId(String csMemberId) {
		this.csMemberId = csMemberId;
	}
	public String getCsTitle() {
		return csTitle;
	}
	public void setCsTitle(String csTitle) {
		this.csTitle = csTitle;
	}
	public Date getCsDate() {
		return csDate;
	}
	public void setCsDate(Date csDate) {
		this.csDate = csDate;
	}
	public String getCsMail() {
		return csMail;
	}
	public void setCsMail(String csMail) {
		this.csMail = csMail;
	}
	public String getCsResult() {
		return csResult;
	}
	public void setCsResult(String csResult) {
		this.csResult = csResult;
	}
	@Override
	public String toString() {
		return "ConsultServer [titleNo=" + titleNo + ", csMemberId=" + csMemberId + ", csTitle=" + csTitle + ", csDate="
				+ csDate + ", csMail=" + csMail + ", csResult=" + csResult + "]";
	}
	
	

	
}
