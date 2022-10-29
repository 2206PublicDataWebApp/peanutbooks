package com.books.peanut.book.domain;

public class peanutPaidSeries {
	private String memberId;
	private String bookNo;
	private String seriesNo;

	private String title;
	private String bookTitle;
	private String picName;
	private String step;
	private String peanutNo;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(String seriesNo) {
		this.seriesNo = seriesNo;
	}
	
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	public String getPeanutNo() {
		return peanutNo;
	}
	public void setPeanutNo(String peanutNo) {
		this.peanutNo = peanutNo;
	}
	@Override
	public String toString() {
		return "peanutPaidSeries [memberId=" + memberId + ", bookNo=" + bookNo + ", seriesNo=" + seriesNo
				+ ", bookTitle=" + bookTitle + ", picName=" + picName + ", step=" + step
				+ ", peanutNo=" + peanutNo + "]";
	}
	
	
	

}
