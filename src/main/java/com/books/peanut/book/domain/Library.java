package com.books.peanut.book.domain;

public class Library {
	private String memberId;
	private String bookNo;
	private String category;
	private String libraryNo;
	private String bookTitle;
	private String picName;
	private String step;
	
	
	
	
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getLibraryNo() {
		return libraryNo;
	}
	public void setLibraryNo(String libraryNo) {
		this.libraryNo = libraryNo;
	}
	@Override
	public String toString() {
		return "library [memberId=" + memberId + ", bookNo=" + bookNo + ", category=" + category + ", libraryNo="
				+ libraryNo + "]";
	}
	

}
