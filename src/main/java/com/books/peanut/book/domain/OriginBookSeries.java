package com.books.peanut.book.domain;

public class OriginBookSeries {
	
	private int seriesNo;
	private String bookNo;
	private String subPic;
	private String subPicRename;
	private String title;
	private String contents;
	private String modifyContents;
	private int paidCount;
	private String paidCheck;
	private int viewCount;
	
	
	
	@Override
	public String toString() {
		return "originBookSeries [seriesNo=" + seriesNo + ", bookNo=" + bookNo + ", subPic=" + subPic
				+ ", subPicRename=" + subPicRename + ", title=" + title + ", contents=" + contents + ", modifyContents="
				+ modifyContents + ", paidCount=" + paidCount + ", paidCheck=" + paidCheck + ", viewCount=" + viewCount
				+ "]";
	}
	
	
	public int getSeriesNo() {
		return seriesNo;
	}
	public void setSeriesNo(int seriesNo) {
		this.seriesNo = seriesNo;
	}
	public String getBookNo() {
		return bookNo;
	}
	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}
	public String getSubPic() {
		return subPic;
	}
	public void setSubPic(String subPic) {
		this.subPic = subPic;
	}
	public String getSubPicRename() {
		return subPicRename;
	}
	public void setSubPicRename(String subPicRename) {
		this.subPicRename = subPicRename;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getModifyContents() {
		return modifyContents;
	}
	public void setModifyContents(String modifyContents) {
		this.modifyContents = modifyContents;
	}
	public int getPaidCount() {
		return paidCount;
	}
	public void setPaidCount(int paidCount) {
		this.paidCount = paidCount;
	}
	public String getPaidCheck() {
		return paidCheck;
	}
	public void setPaidCheck(String paidCheck) {
		this.paidCheck = paidCheck;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	

}
