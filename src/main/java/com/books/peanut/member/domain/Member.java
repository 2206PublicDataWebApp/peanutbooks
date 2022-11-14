package com.books.peanut.member.domain;

import java.sql.Date;

public class Member {
	private String memberId; // 회원 아이디
	private String memberPw; // 회원 비밀번호
	private String mNickname; // 회원 별명
	private String mEmail; // 회원 이메일
	private String emailYN; // 이메일 인증 여부
	private Date joinDate; // 회원 등록일
	private String deleteYN; // 회원 탈퇴 여부
	private int mPoint; // 회원 포인트(땅콩)
	private String subYN; // 월 구독 여부
	private String snsId; // SNS 아이디
	private String accType; // 계정 종류(일반/네이버/카카오)
	private String adminYN; // 관리자 여부
	private String authKey; // 이메일 인증 키
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getmNickname() {
		return mNickname;
	}
	public void setmNickname(String mNickname) {
		this.mNickname = mNickname;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getEmailYN() {
		return emailYN;
	}
	public void setEmailYN(String emailYN) {
		this.emailYN = emailYN;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public int getmPoint() {
		return mPoint;
	}
	public void setmPoint(int mPoint) {
		this.mPoint = mPoint;
	}
	public String getSubYN() {
		return subYN;
	}
	public void setSubYN(String subYN) {
		this.subYN = subYN;
	}
	public String getSnsId() {
		return snsId;
	}
	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAdminYN() {
		return adminYN;
	}
	public void setAdminYN(String adminYN) {
		this.adminYN = adminYN;
	}
	public String getAuthKey() {
		return authKey;
	}
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPw=" + memberPw + ", mNickname=" + mNickname + ", mEmail="
				+ mEmail + ", emailYN=" + emailYN + ", joinDate=" + joinDate + ", deleteYN=" + deleteYN + ", mPoint="
				+ mPoint + ", subYN=" + subYN + ", snsId=" + snsId + ", accType=" + accType + ", adminYN=" + adminYN
				+ ", authKey=" + authKey + "]";
	}
	
}