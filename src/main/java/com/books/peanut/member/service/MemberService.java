package com.books.peanut.member.service;

import com.books.peanut.member.domain.Member;

public interface MemberService {
	// 회원가입
	int registerMember(Member member);
	// 별명 유효성 검사
	int checkNickname(String mNickname);
	// 아이디 유효성 검사
	int checkId(String memberId);
	// 로그인
	Member loginMember(Member member);
	

}
