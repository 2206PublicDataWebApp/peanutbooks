package com.books.peanut.news.domain;

public class News {
	private int newsNo; // 알림 번호
	private String memberId; // 회원 아이디
	private String newsContents; // 알림 내용
	private int refNoticeNo; // 참고 공지 번호
	private int refBookNo; // 참고 도서 번호
	private String readYN; // 알림 읽음 여부
	private String newsType; // 알림 종류
	
	public int getNewsNo() {
		return newsNo;
	}
	public void setNewsNo(int newsNo) {
		this.newsNo = newsNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getNewsContents() {
		return newsContents;
	}
	public void setNewsContents(String newsContents) {
		this.newsContents = newsContents;
	}
	public int getRefNoticeNo() {
		return refNoticeNo;
	}
	public void setRefNoticeNo(int refNoticeNo) {
		this.refNoticeNo = refNoticeNo;
	}
	public int getRefBookNo() {
		return refBookNo;
	}
	public void setRefBookNo(int refBookNo) {
		this.refBookNo = refBookNo;
	}
	public String getReadYN() {
		return readYN;
	}
	public void setReadYN(String readYN) {
		this.readYN = readYN;
	}
	public String getNewsType() {
		return newsType;
	}
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	
	@Override
	public String toString() {
		return "News [newsNo=" + newsNo + ", memberId=" + memberId + ", newsContents=" + newsContents + ", refNoticeNo="
				+ refNoticeNo + ", refBookNo=" + refBookNo + ", readYN=" + readYN + ", newsType=" + newsType + "]";
	}
	
}
