package com.books.peanut.book.domain;

import java.sql.Date;

public class NormalBook {
	private String bookNo;
	private String bookTitle;
	private String writer;
	private String bookInfo;
	private String category;
	private Date inserDate;
	private String cover;
	private String coverRename;
	private String status;
	private int viewCount;
	private int score;
	private String language;
	private String adminId;
	
	
	
	
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public Date getInserDate() {
		return inserDate;
	}
	public void setInserDate(Date inserDate) {
		this.inserDate = inserDate;
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
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "normalBook [bookNo=" + bookNo + ", bookTitle=" + bookTitle + ", writer=" + writer + ", bookInfo="
				+ bookInfo + ", category=" + category + ", inserDate=" + inserDate + ", cover=" + cover
				+ ", coverRename=" + coverRename + ", status=" + status + ", viewCount=" + viewCount + ", score="
				+ score + ", language=" + language + "]";
	}
	
	
}
