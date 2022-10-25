package com.books.peanut.qna.domain;

import java.sql.Date;


public class Qna {
	private int qnaNo;
	private String memberId;
	private String qnaCategory;
	private String qnaTitle;
	private String qnaContents;
	private Date qCreateDate;
	private Date qUpdateDate;
	private String qnaStatus;
	private String qnaFilename01;
	private String qnaFileRename01;
	private String qnaFilepath01;
	private String qnaFilename02;
	private String qnaFileRename02;
	private String qnaFilepath02;
	private String qnaFilename03;
	private String qnaFileRename03;
	private String qnaFilepath03;
	private String answerWriter;
	private String answerContents;
	private Date aCreateDate;
	private Date aUpdateDate;
	
	public Qna() {}

	public Qna(int qnaNo, String memberId, String qnaCategory, String qnaTitle, String qnaContents, Date qCreateDate,
			Date qUpdateDate, String qnaStatus, String qnaFilename01, String qnaFileRename01, String qnaFilepath01,
			String qnaFilename02, String qnaFileRename02, String qnaFilepath02, String qnaFilename03,
			String qnaFileRename03, String qnaFilepath03, String answerWriter, String answerContents, Date aCreateDate,
			Date aUpdateDate) {
		super();
		this.qnaNo = qnaNo;
		this.memberId = memberId;
		this.qnaCategory = qnaCategory;
		this.qnaTitle = qnaTitle;
		this.qnaContents = qnaContents;
		this.qCreateDate = qCreateDate;
		this.qUpdateDate = qUpdateDate;
		this.qnaStatus = qnaStatus;
		this.qnaFilename01 = qnaFilename01;
		this.qnaFileRename01 = qnaFileRename01;
		this.qnaFilepath01 = qnaFilepath01;
		this.qnaFilename02 = qnaFilename02;
		this.qnaFileRename02 = qnaFileRename02;
		this.qnaFilepath02 = qnaFilepath02;
		this.qnaFilename03 = qnaFilename03;
		this.qnaFileRename03 = qnaFileRename03;
		this.qnaFilepath03 = qnaFilepath03;
		this.answerWriter = answerWriter;
		this.answerContents = answerContents;
		this.aCreateDate = aCreateDate;
		this.aUpdateDate = aUpdateDate;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getQnaCategory() {
		return qnaCategory;
	}

	public void setQnaCategory(String qnaCategory) {
		this.qnaCategory = qnaCategory;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContents() {
		return qnaContents;
	}

	public void setQnaContents(String qnaContents) {
		this.qnaContents = qnaContents;
	}

	public Date getqCreateDate() {
		return qCreateDate;
	}

	public void setqCreateDate(Date qCreateDate) {
		this.qCreateDate = qCreateDate;
	}

	public Date getqUpdateDate() {
		return qUpdateDate;
	}

	public void setqUpdateDate(Date qUpdateDate) {
		this.qUpdateDate = qUpdateDate;
	}

	public String getQnaStatus() {
		return qnaStatus;
	}

	public void setQnaStatus(String qnaStatus) {
		this.qnaStatus = qnaStatus;
	}

	public String getQnaFilename01() {
		return qnaFilename01;
	}

	public void setQnaFilename01(String qnaFilename01) {
		this.qnaFilename01 = qnaFilename01;
	}

	public String getQnaFileRename01() {
		return qnaFileRename01;
	}

	public void setQnaFileRename01(String qnaFileRename01) {
		this.qnaFileRename01 = qnaFileRename01;
	}

	public String getQnaFilepath01() {
		return qnaFilepath01;
	}

	public void setQnaFilepath01(String qnaFilepath01) {
		this.qnaFilepath01 = qnaFilepath01;
	}

	public String getQnaFilename02() {
		return qnaFilename02;
	}

	public void setQnaFilename02(String qnaFilename02) {
		this.qnaFilename02 = qnaFilename02;
	}

	public String getQnaFileRename02() {
		return qnaFileRename02;
	}

	public void setQnaFileRename02(String qnaFileRename02) {
		this.qnaFileRename02 = qnaFileRename02;
	}

	public String getQnaFilepath02() {
		return qnaFilepath02;
	}

	public void setQnaFilepath02(String qnaFilepath02) {
		this.qnaFilepath02 = qnaFilepath02;
	}

	public String getQnaFilename03() {
		return qnaFilename03;
	}

	public void setQnaFilename03(String qnaFilename03) {
		this.qnaFilename03 = qnaFilename03;
	}

	public String getQnaFileRename03() {
		return qnaFileRename03;
	}

	public void setQnaFileRename03(String qnaFileRename03) {
		this.qnaFileRename03 = qnaFileRename03;
	}

	public String getQnaFilepath03() {
		return qnaFilepath03;
	}

	public void setQnaFilepath03(String qnaFilepath03) {
		this.qnaFilepath03 = qnaFilepath03;
	}

	public String getAnswerWriter() {
		return answerWriter;
	}

	public void setAnswerWriter(String answerWriter) {
		this.answerWriter = answerWriter;
	}

	public String getAnswerContents() {
		return answerContents;
	}

	public void setAnswerContents(String answerContents) {
		this.answerContents = answerContents;
	}

	public Date getaCreateDate() {
		return aCreateDate;
	}

	public void setaCreateDate(Date aCreateDate) {
		this.aCreateDate = aCreateDate;
	}

	public Date getaUpdateDate() {
		return aUpdateDate;
	}

	public void setaUpdateDate(Date aUpdateDate) {
		this.aUpdateDate = aUpdateDate;
	}

	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", memberId=" + memberId + ", qnaCategory=" + qnaCategory + ", qnaTitle="
				+ qnaTitle + ", qnaContents=" + qnaContents + ", qCreateDate=" + qCreateDate + ", qUpdateDate="
				+ qUpdateDate + ", qnaStatus=" + qnaStatus + ", qnaFilename01=" + qnaFilename01 + ", qnaFileRename01="
				+ qnaFileRename01 + ", qnaFilepath01=" + qnaFilepath01 + ", qnaFilename02=" + qnaFilename02
				+ ", qnaFileRename02=" + qnaFileRename02 + ", qnaFilepath02=" + qnaFilepath02 + ", qnaFilename03="
				+ qnaFilename03 + ", qnaFileRename03=" + qnaFileRename03 + ", qnaFilepath03=" + qnaFilepath03
				+ ", answerWriter=" + answerWriter + ", answerContents=" + answerContents + ", aCreateDate="
				+ aCreateDate + ", aUpdateDate=" + aUpdateDate + "]";
	}
	
}
