package com.books.peanut.book.domain;

import java.sql.Date;

public class OriginBookReply {
	
	private int replyNo;
	private String bookNo;
	private String memberId;
	private String reContents;
	private Date insertDate;
	private String mNickName;
	private int endNavi;
	private int startNavi;
	private int maxPage;
	private int totalCount;
	private int currentPage;
	
	
	
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	public String getmNickName() {
		return mNickName;
	}
	public void setmNickName(String mNickName) {
		this.mNickName = mNickName;
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
