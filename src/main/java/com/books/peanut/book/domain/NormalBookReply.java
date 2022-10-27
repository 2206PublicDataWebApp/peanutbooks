package com.books.peanut.book.domain;

import java.sql.Date;

public class NormalBookReply {
	
	private int replyNo;
	private String bookNo;
	private String memberId;
	private String contents;
	private Date insertDate;
	private String mNickName;
	private int endNavi;
	private int startNavi;
	private int maxPage;
	private int totalCount;
	private int currentPage;
	
	
	
	
	
	public String getmNickName() {
		return mNickName;
	}
	public void setmNickName(String mNickName) {
		this.mNickName = mNickName;
	}
	public int getEndNavi() {
		return endNavi;
	}
	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}
	public int getStartNavi() {
		return startNavi;
	}
	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
