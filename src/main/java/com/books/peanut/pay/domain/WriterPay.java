package com.books.peanut.pay.domain;

import java.util.Date;

public class WriterPay {
	private String memberId;
	private int seriesNo;
	private int ori_bookNo;
	private Date putDate;
	private String bankName;
	private int bankNo;
	private String payment;
	private String bankStatus;
	
	public WriterPay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WriterPay(String memberId, int seriesNo, int ori_bookNo, Date putDate, String bankName, int bankNo,
			String payment, String bankStatus) {
		super();
		this.memberId = memberId;
		this.seriesNo = seriesNo;
		this.ori_bookNo = ori_bookNo;
		this.putDate = putDate;
		this.bankName = bankName;
		this.bankNo = bankNo;
		this.payment = payment;
		this.bankStatus = bankStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getSeriesNo() {
		return seriesNo;
	}

	public void setSeriesNo(int seriesNo) {
		this.seriesNo = seriesNo;
	}

	public int getOri_bookNo() {
		return ori_bookNo;
	}

	public void setOri_bookNo(int ori_bookNo) {
		this.ori_bookNo = ori_bookNo;
	}

	public Date getPutDate() {
		return putDate;
	}

	public void setPutDate(Date putDate) {
		this.putDate = putDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getBankNo() {
		return bankNo;
	}

	public void setBankNo(int bankNo) {
		this.bankNo = bankNo;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(String bankStatus) {
		this.bankStatus = bankStatus;
	}

	@Override
	public String toString() {
		return "WriterPay [memberId=" + memberId + ", seriesNo=" + seriesNo + ", ori_bookNo=" + ori_bookNo
				+ ", putDate=" + putDate + ", bankName=" + bankName + ", bankNo=" + bankNo + ", payment=" + payment
				+ ", bankStatus=" + bankStatus + "]";
	}
	
	
	
	

}
