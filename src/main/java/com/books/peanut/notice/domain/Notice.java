package com.books.peanut.notice.domain;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String noticeWriter;
	private String noticeCategory;
	private String noticeTitle;
	private String noticeContents;
	private int noticeCount;
	private Date nCreateDate;
	private Date nUpdateDate;
	private String nDeliver;
	private String nStatus;
	private String nFilename01;
	private String nRename01;
	private String nFilepath01;
	private String nFilename02;
	private String nRename02;
	private String nFilepath02;
	private String nFilename03;
	private String nRename03;
	private String nFilepath03;
	
	public Notice() {}

	public Notice(int noticeNo, String noticeWriter, String noticeCategory, String noticeTitle, String noticeContents,
			int noticeCount, Date nCreateDate, Date nUpdateDate, String nDeliver, String nStatus, String nFilename01,
			String nRename01, String nFilepath01, String nFilename02, String nRename02, String nFilepath02,
			String nFilename03, String nRename03, String nFilepath03) {
		super();
		this.noticeNo = noticeNo;
		this.noticeWriter = noticeWriter;
		this.noticeCategory = noticeCategory;
		this.noticeTitle = noticeTitle;
		this.noticeContents = noticeContents;
		this.noticeCount = noticeCount;
		this.nCreateDate = nCreateDate;
		this.nUpdateDate = nUpdateDate;
		this.nDeliver = nDeliver;
		this.nStatus = nStatus;
		this.nFilename01 = nFilename01;
		this.nRename01 = nRename01;
		this.nFilepath01 = nFilepath01;
		this.nFilename02 = nFilename02;
		this.nRename02 = nRename02;
		this.nFilepath02 = nFilepath02;
		this.nFilename03 = nFilename03;
		this.nRename03 = nRename03;
		this.nFilepath03 = nFilepath03;
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

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
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

	public String getnFilename01() {
		return nFilename01;
	}

	public void setnFilename01(String nFilename01) {
		this.nFilename01 = nFilename01;
	}

	public String getnRename01() {
		return nRename01;
	}

	public void setnRename01(String nRename01) {
		this.nRename01 = nRename01;
	}

	public String getnFilepath01() {
		return nFilepath01;
	}

	public void setnFilepath01(String nFilepath01) {
		this.nFilepath01 = nFilepath01;
	}

	public String getnFilename02() {
		return nFilename02;
	}

	public void setnFilename02(String nFilename02) {
		this.nFilename02 = nFilename02;
	}

	public String getnRename02() {
		return nRename02;
	}

	public void setnRename02(String nRename02) {
		this.nRename02 = nRename02;
	}

	public String getnFilepath02() {
		return nFilepath02;
	}

	public void setnFilepath02(String nFilepath02) {
		this.nFilepath02 = nFilepath02;
	}

	public String getnFilename03() {
		return nFilename03;
	}

	public void setnFilename03(String nFilename03) {
		this.nFilename03 = nFilename03;
	}

	public String getnRename03() {
		return nRename03;
	}

	public void setnRename03(String nRename03) {
		this.nRename03 = nRename03;
	}

	public String getnFilepath03() {
		return nFilepath03;
	}

	public void setnFilepath03(String nFilepath03) {
		this.nFilepath03 = nFilepath03;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeWriter=" + noticeWriter + ", noticeCategory=" + noticeCategory
				+ ", noticeTitle=" + noticeTitle + ", noticeContents=" + noticeContents + ", noticeCount=" + noticeCount
				+ ", nCreateDate=" + nCreateDate + ", nUpdateDate=" + nUpdateDate + ", nDeliver=" + nDeliver
				+ ", nStatus=" + nStatus + ", nFilename01=" + nFilename01 + ", nRename01=" + nRename01
				+ ", nFilepath01=" + nFilepath01 + ", nFilename02=" + nFilename02 + ", nRename02=" + nRename02
				+ ", nFilepath02=" + nFilepath02 + ", nFilename03=" + nFilename03 + ", nRename03=" + nRename03
				+ ", nFilepath03=" + nFilepath03 + "]";
	}
	
	
}
