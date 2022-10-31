package com.books.peanut.admin.domain;

import java.sql.Date;

public class BookApprove {
	private int approveNo;
	private String oriBookNo;
	private String approve;
	private Date approveDate;
	private Date approveUpdate;
	private String reApprove;
	private String bookSeriesNo;
	
	public BookApprove() {}

	public BookApprove(int approveNo, String oriBookNo, String approve, Date approveDate, Date approveUpdate,
			String reApprove, String bookSeriesNo) {
		super();
		this.approveNo = approveNo;
		this.oriBookNo = oriBookNo;
		this.approve = approve;
		this.approveDate = approveDate;
		this.approveUpdate = approveUpdate;
		this.reApprove = reApprove;
		this.bookSeriesNo = bookSeriesNo;
	}
	

	public int getApproveNo() {
		return approveNo;
	}

	public void setApproveNo(int approveNo) {
		this.approveNo = approveNo;
	}

	public String getOriBookNo() {
		return oriBookNo;
	}

	public void setOriBookNo(String oriBookNo) {
		this.oriBookNo = oriBookNo;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public Date getApproveUpdate() {
		return approveUpdate;
	}

	public void setApproveUpdate(Date approveUpdate) {
		this.approveUpdate = approveUpdate;
	}

	public String getReApprove() {
		return reApprove;
	}

	public void setReApprove(String reApprove) {
		this.reApprove = reApprove;
	}

	public String getBookSeriesNo() {
		return bookSeriesNo;
	}

	public void setBookSeriesNo(String bookSeriesNo) {
		this.bookSeriesNo = bookSeriesNo;
	}

	@Override
	public String toString() {
		return "BookApprove [approveNo=" + approveNo + ", oriBookNo=" + oriBookNo + ", approve=" + approve
				+ ", approveDate=" + approveDate + ", approveUpdate=" + approveUpdate + ", reApprove=" + reApprove
				+ ", bookSeriesNo=" + bookSeriesNo + "]";
	};
	
	
}
