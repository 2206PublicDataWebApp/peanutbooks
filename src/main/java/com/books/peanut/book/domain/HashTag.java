package com.books.peanut.book.domain;

public class HashTag {
	private String bookNo;
	private String category;
	private String hashTag1;
	private String hashTag2;
	private String hashTag3;
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
	public String getHashTag1() {
		return hashTag1;
	}
	public void setHashTag1(String hashTag1) {
		this.hashTag1 = hashTag1;
	}
	public String getHashTag2() {
		return hashTag2;
	}
	public void setHashTag2(String hashTag2) {
		this.hashTag2 = hashTag2;
	}
	public String getHashTag3() {
		return hashTag3;
	}
	public void setHashTag3(String hashTag3) {
		this.hashTag3 = hashTag3;
	}
	@Override
	public String toString() {
		return "hashTag [bookNo=" + bookNo + ", category=" + category + ", hashTag1=" + hashTag1 + ", hashTag2="
				+ hashTag2 + ", hashTag3=" + hashTag3 + "]";
	}
	
	
	
	
}
