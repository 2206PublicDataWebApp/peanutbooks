package com.books.peanut.book.domain;

public class WriterProfile {
	private String memberId;
	private String mainPic;
	private String headPic;
	private String mainPicRename;
	private String headPicRename;
	private String info;
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getMainPic() {
		return mainPic;
	}
	public void setMainPic(String mainPic) {
		this.mainPic = mainPic;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getMainPicRename() {
		return mainPicRename;
	}
	public void setMainPicRename(String mainPicRename) {
		this.mainPicRename = mainPicRename;
	}
	public String getHeadPicRename() {
		return headPicRename;
	}
	public void setHeadPicRename(String headPicRename) {
		this.headPicRename = headPicRename;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "writerProfile [MemberId=" + memberId + ", mainPic=" + mainPic + ", headPic=" + headPic
				+ ", mainPicRename=" + mainPicRename + ", headPicRename=" + headPicRename + ", info=" + info + "]";
	}
	
	
	
}
