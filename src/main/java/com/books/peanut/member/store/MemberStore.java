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
	// 로그인
	public Member selectLoginMember(SqlSession session, Member member);

}
