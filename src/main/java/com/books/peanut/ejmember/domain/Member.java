package com.books.peanut.ejmember.domain;

public class Member {
	private String memberId;
	private String mEmail;
	private String memberPw;
	
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}	

	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", mEmail=" + mEmail + ", memberPw=" + memberPw
				+ "]";
	}
	
	
}
