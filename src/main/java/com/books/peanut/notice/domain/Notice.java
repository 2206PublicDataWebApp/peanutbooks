package com.books.peanut.notice.domain;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeWriter;
	private String noticeCategory;
	private String noticeTitle;
	private String noticeContents;
	private Date nCreateDate;
	private Date nUpdateDate;
	private String nDeliver;
	private String nStatus;
	private String noticeFilename;
	private String noticeFileRename;
	private String noticeFilepath;
	private int nCount;

	public Notice() {}

	public Notice(int noticeNo, String noticeWriter, String noticeCategory, String noticeTitle, String noticeContents,
			Date nCreateDate, Date nUpdateDate, String nDeliver, String nStatus, String noticeFilename,
			String noticeFileRename, String noticeFilepath, int nCount) {
		super();
		this.noticeNo = noticeNo;
		this.noticeWriter = noticeWriter;
		this.noticeCategory = noticeCategory;
		this.noticeTitle = noticeTitle;
		this.noticeContents = noticeContents;
		this.nCreateDate = nCreateDate;
		this.nUpdateDate = nUpdateDate;
		this.nDeliver = nDeliver;
		this.nStatus = nStatus;
		this.noticeFilename = noticeFilename;
		this.noticeFileRename = noticeFileRename;
		this.noticeFilepath = noticeFilepath;
		this.nCount = nCount;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public String getNoticeCategory() {
		return noticeCategory;
	}

	public void setNoticeCategory(String noticeCategory) {
		this.noticeCategory = noticeCategory;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContents() {
		return noticeContents;
	}

	public void setNoticeContents(String noticeContents) {
		this.noticeContents = noticeContents;
	}

	public Date getnCreateDate() {
		return nCreateDate;
	}

	public void setnCreateDate(Date nCreateDate) {
		this.nCreateDate = nCreateDate;
	}

	public Date getnUpdateDate() {
		return nUpdateDate;
	}

	public void setnUpdateDate(Date nUpdateDate) {
		this.nUpdateDate = nUpdateDate;
	}

	public String getnDeliver() {
		return nDeliver;
	}

	public void setnDeliver(String nDeliver) {
		this.nDeliver = nDeliver;
	}

	public String getnStatus() {
		return nStatus;
	}

	public void setnStatus(String nStatus) {
		this.nStatus = nStatus;
	}

	public String getNoticeFilename() {
		return noticeFilename;
	}

	public void setNoticeFilename(String noticeFilename) {
		this.noticeFilename = noticeFilename;
	}

	public String getNoticeFileRename() {
		return noticeFileRename;
	}

	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}

	public String getNoticeFilepath() {
		return noticeFilepath;
	}

	public void setNoticeFilepath(String noticeFilepath) {
		this.noticeFilepath = noticeFilepath;
	}

	public int getnCount() {
		return nCount;
	}

	public void setnCount(int nCount) {
		this.nCount = nCount;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeWriter=" + noticeWriter + ", noticeCategory=" + noticeCategory
				+ ", noticeTitle=" + noticeTitle + ", noticeContents=" + noticeContents + ", nCreateDate=" + nCreateDate
				+ ", nUpdateDate=" + nUpdateDate + ", nDeliver=" + nDeliver + ", nStatus=" + nStatus
				+ ", noticeFilename=" + noticeFilename + ", noticeFileRename=" + noticeFileRename + ", noticeFilepath="
				+ noticeFilepath + ", nCount=" + nCount + "]";
	}

	

}
