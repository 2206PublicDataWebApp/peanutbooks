package com.books.peanut.consult.domain;

import java.util.Date;

public class ConsultServer {
	private int titleNo;    //titleNo로 변경된다.
	private String csNickName;
	private String csMail;
	private String csTitle;
	private Date csDate;
	private String csResult;
	private String csFileName;
	private String csFileRename;
	private String csFilePath;
	

	
	public int getTitleNo() {
		return titleNo;
	}
	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}
	public String getCsNickName() {
		return csNickName;
	}
	public void setCsNickName(String csNickName) {
		this.csNickName = csNickName;
	}
	public String getCsMail() {
		return csMail;
	}
	public void setCsMail(String csMail) {
		this.csMail = csMail;
	}
	public String getCsTitle() {
		return csTitle;
	}
	public void setCsTitle(String csTitle) {
		this.csTitle = csTitle;
	}
	public Date getCsDate() {
		return csDate;
	}
	public void setCsDate(Date csDate) {
		this.csDate = csDate;
	}
	public String getCsResult() {
		return csResult;
	}
	public void setCsResult(String csResult) {
		this.csResult = csResult;
	}
	public String getCsFileName() {
		return csFileName;
	}
	public void setCsFileName(String csFileName) {
		this.csFileName = csFileName;
	}
	public String getCsFileRename() {
		return csFileRename;
	}
	public void setCsFileRename(String csFileRename) {
		this.csFileRename = csFileRename;
	}
	public String getCsFilePath() {
		return csFilePath;
	}
	public void setCsFilePath(String csFilePath) {
		this.csFilePath = csFilePath;
	}
	@Override
	public String toString() {
		return "ConsultServer [titleNo=" + titleNo + ", csNickName=" + csNickName + ", csMail=" + csMail
				+ ", csTitle=" + csTitle + ", csDate=" + csDate + ", csResult=" + csResult + ", csFileName="
				+ csFileName + ", csFileRename=" + csFileRename + ", csFilePath=" + csFilePath + "]";
	}

	
	
}
