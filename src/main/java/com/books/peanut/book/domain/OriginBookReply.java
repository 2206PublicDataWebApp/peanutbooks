package com.books.peanut.book.domain;

import java.sql.Date;

public class OriginBookReply {
	
	private int replyNo;
	private String bookNo;
	private String memberId;
	private String reContents;
	private Date insertDate;
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getReContents() {
		return reContents;
	}
	public void setReContents(String reContents) {
		this.reContents = reContents;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	@Override
	public String toString() {
		return "originBookReply [replyNo=" + replyNo + ", bookNo=" + bookNo + ", memberId=" + memberId + ", reContents="
				+ reContents + ", insertDate=" + insertDate + "]";
	}
	
	

}
