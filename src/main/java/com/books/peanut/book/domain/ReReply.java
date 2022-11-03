package com.books.peanut.book.domain;

import java.sql.Date;

public class ReReply {
	
	private int reReplyNo;
	private int replyNo;
	private String bookNo;
	private String category;
	private String memberId;
	private String reContens;
	private Date insertDate;
	private String mNickName;
	
	
	
	
	public String getmNickName() {
		return mNickName;
	}
	public void setmNickName(String mNickName) {
		this.mNickName = mNickName;
	}
	@Override
	public String toString() {
		return "ReReply [reReplyNo=" + reReplyNo + ", replyNo=" + replyNo + ", bookNo=" + bookNo + ", category="
				+ category + ", memberId=" + memberId + ", reContens=" + reContens + ", insertDate=" + insertDate + "]";
	}
	public int getReReplyNo() {
		return reReplyNo;
	}
	public void setReReplyNo(int reReplyNo) {
		this.reReplyNo = reReplyNo;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getReContens() {
		return reContens;
	}
	public void setReContens(String reContens) {
		this.reContens = reContens;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	
	
	

}
