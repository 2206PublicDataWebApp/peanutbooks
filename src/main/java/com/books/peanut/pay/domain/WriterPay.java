package com.books.peanut.pay.domain;

import java.util.Date;

public class WriterPay {
	private String memberId;
	private String seriesNo;
	private String ori_bookNo;
	private Date putDate;
	private String bankName;
	private String bankNo;
	private String payment;
	private String bankStatus;
	private int wrpayNo;
	private int changeP;
	


	public WriterPay() {
		super();		
	}

	public WriterPay(String memberId, String seriesNo, String ori_bookNo, Date putDate, String bankName, String bankNo,
			String payment, String bankStatus,int wrpayNo, int changeP) {
		super();
		this.memberId = memberId;
		this.seriesNo = seriesNo;
		this.ori_bookNo = ori_bookNo;
		this.putDate = putDate;
		this.bankName = bankName;
		this.bankNo = bankNo;
		this.payment = payment;
		this.bankStatus = bankStatus;
		this.wrpayNo = wrpayNo;
		this.changeP = changeP;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSeriesNo() {
		return seriesNo;
	}

	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}

	public String getOri_bookNo() {
		return ori_bookNo;
	}

	public void setOri_bookNo(String ori_bookNo) {
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

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
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

	public int getWrpayNo() {
		return changeP;
	}
	
	public void setWrpayNo(int wrpayNo) {
		this.wrpayNo = wrpayNo;
	}
	
	public int getChangeP() {
		return changeP;
	}
	public void setChangeP(int changeP) {
		this.changeP = changeP;
	}
	

	@Override
	public String toString() {
		return "WriterPay [memberId=" + memberId + ", seriesNo=" + seriesNo + ", ori_bookNo=" + ori_bookNo
				+ ", putDate=" + putDate + ", bankName=" + bankName + ", bankNo=" + bankNo + ", payment=" + payment
				+ ", bankStatus=" + bankStatus + ", wrpayNo=" + wrpayNo + ", changeP" + changeP
				+"]";
	}
	
	
	
	

}
