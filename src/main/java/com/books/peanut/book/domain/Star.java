package com.books.peanut.book.domain;

public class Star {

	private String memberId;
	private String bookNo;
	private String category;
	private int score;
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
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "star [memberId=" + memberId + ", bookNo=" + bookNo + ", category=" + category + ", score=" + score
				+ "]";
	}
	
	
}
