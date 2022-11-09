package com.books.peanut.member.service;

import java.util.HashMap;
import java.util.List;

import com.books.peanut.member.domain.Member;

public interface MemberService {
	// 회원가입
	int registerMember(Member member);
	// 인증 키 저장
	int saveAuthKey(String authKey, String mEmail);
	// 별명 유효성 검사
	int checkNickname(String mNickname);
	// 아이디 유효성 검사
	int checkId(String memberId);
	// 이메일 유효성 검사
	int checkEmail(String mEmail);
	// 로그인
	Member loginMember(Member member);
	// 회원 정보 수정
	int modifyInfo(Member member);
	// 회원탈퇴
	int deleteMember(Member member);
	// 내 서재 - 저장된 도서 수 가져오기
	int countSavedBooks(String memberId);
	// 로그인한 회원이 등록한 작품 수 가져오기
	int countWrittenBooks(String memberId);
	// 이메일 인증 키 검사
	int checkAuthKey(HashMap<String, String> paramMap);
	// 이메일 인증 여부 업데이트
	int authEmail(HashMap<String, String> paramMap);
	// 기존 인증 키 삭제
	void resetAuthKey(String mEmail);
	// 이메일로 아이디 찾기
	String getIdByEmail(String mEmail);
	// 이메일로 회원 여부 확인
	int checkMemberByEmail(String mEmail);
	List<Member> getMemberInfo(HashMap<String, String> authData);

}
