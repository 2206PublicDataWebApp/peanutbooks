package com.books.peanut.book.domain;

import java.sql.Date;

public class NormalBookReply {
	
	private int replyNo;
	private String bookNo;
	private String memberId;
	private String contents;
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
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	@Override
	public String toString() {
		return "normalBookReply [replyNo=" + replyNo + ", bookNo=" + bookNo + ", memberId=" + memberId + ", contents="
				+ contents + ", insertDate=" + insertDate + "]";
	}

	
	
}
