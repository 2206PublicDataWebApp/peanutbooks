package com.books.peanut.member.store;

import org.apache.ibatis.session.SqlSession;

import com.books.peanut.member.domain.Member;

public interface MemberStore {
	// 회원가입
	public int insertMember(SqlSession session, Member member);
	// 별명 유효성 검사
	public int checkNickname(SqlSession session, String mNickname);
	// 아이디 유효성 검사
	public int checkId(SqlSession session, String memberId);
	// 이메일 유효성 검사
	public int checkEmail(SqlSession session, String mEmail);
	// 로그인
	public Member selectLoginMember(SqlSession session, Member member);
	// 회원 정보 수정
	public int updateInfo(SqlSession session, Member member);
	// 회원탈퇴
	public int deleteMember(SqlSession session, Member member);
	// 내 서재 - 저장된 도서 수 가져오기
//	public int selectSavedBooks(SqlSession session, String memberId);
	// 로그인한 회원이 등록한 작품 수 가져오기
	public int selectWrittenBooks(SqlSession session, String memberId);

}
