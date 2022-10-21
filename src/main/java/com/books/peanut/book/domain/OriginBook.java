package com.books.peanut.book.domain;

import java.sql.Date;

public class OriginBook {
	
	private String bookNo;
	private String bookTitle;
	private String bookInfo;
	private String category;
	private Date insertDate;
	private String cover;
	private String coverRename;
	private String status;
	private int score;
	private int viewCount;
	private String memberId;
	private String checkPermission;
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(String bookInfo) {
		this.bookInfo = bookInfo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getCoverRename() {
		return coverRename;
	}
	public void setCoverRename(String coverRename) {
		this.coverRename = coverRename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCheckPermission() {
		return checkPermission;
	}
	public void setCheckPermission(String checkPermission) {
		this.checkPermission = checkPermission;
	}
	
	
	@Override
	public String toString() {
		return "originBook [bookNo=" + bookNo + ", bookTitle=" + bookTitle + ", bookInfo=" + bookInfo + ", category="
				+ category + ", insertDate=" + insertDate + ", cover=" + cover + ", coverRename=" + coverRename
				+ ", status=" + status + ", score=" + score + ", viewCount=" + viewCount + ", memberId=" + memberId
				+ ", checkPermission=" + checkPermission + "]";
	}
	
	
	
	
	

}
