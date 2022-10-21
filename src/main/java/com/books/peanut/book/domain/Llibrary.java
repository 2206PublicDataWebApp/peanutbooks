package com.books.peanut.book.domain;

public class Llibrary {
	private String memberId;
	private String bookNo;
	private String category;
	private String libraryNo;
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
