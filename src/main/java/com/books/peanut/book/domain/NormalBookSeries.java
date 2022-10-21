package com.books.peanut.book.domain;

import java.sql.Date;

public class NormalBookSeries {
	
	private int seriesNo;
	private String bookNo;
	private String subPic;
	private String subpicRename;
	private String title;
	private String contents;
	private Date insertDate;
	private Date updateDate;
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
	public String getSubpicRename() {
		return subpicRename;
	}
	public void setSubpicRename(String subpicRename) {
		this.subpicRename = subpicRename;
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
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "normalBookSeries [seriesNo=" + seriesNo + ", bookNo=" + bookNo + ", subPic=" + subPic
				+ ", subpicRename=" + subpicRename + ", title=" + title + ", contents=" + contents + ", insertDate="
				+ insertDate + ", updateDate=" + updateDate + "]";
	}
	
	

}
