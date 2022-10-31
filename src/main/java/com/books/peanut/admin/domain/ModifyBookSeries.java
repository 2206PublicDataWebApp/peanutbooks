package com.books.peanut.admin.domain;

public class ModifyBookSeries {
	private int seriesNo;
	private String bookNo;
	private String subPic;
	private String subPicRename;
	private String title;
	private String bookTitle;
	private String modifyContents;
	private String insertDate;
	private String paidCheck;
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
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public String getModifyContents() {
		return modifyContents;
	}
	public void setModifyContents(String modifyContents) {
		this.modifyContents = modifyContents;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getPaidCheck() {
		return paidCheck;
	}
	public void setPaidCheck(String paidCheck) {
		this.paidCheck = paidCheck;
	}
	@Override
	public String toString() {
		return "ModifyBookSeries [seriesNo=" + seriesNo + ", bookNo=" + bookNo + ", subPic=" + subPic
				+ ", subPicRename=" + subPicRename + ", title=" + title + ", bookTitle=" + bookTitle
				+ ", modifyContents=" + modifyContents + ", insertDate=" + insertDate + ", paidCheck=" + paidCheck
				+ "]";
	}
	
	
	
}
