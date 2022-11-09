package com.books.peanut.member.store;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.books.peanut.member.domain.Member;

public interface MemberStore {
	// 회원가입
	public int insertMember(SqlSession session, Member member);
	// 인증 키 저장
	public int updateAuthKey(SqlSession session, String authKey, String mEmail);
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
	public int selectSavedBooks(SqlSession session, String memberId);
	// 로그인한 회원이 등록한 작품 수 가져오기
	public int selectWrittenBooks(SqlSession session, String memberId);
	// 이메일 인증 키 검사
	public int checkAuthKey(SqlSession session, HashMap<String, String> paramMap);
	// 이메일 인증 여부 업데이트
	public int updateEmailYN(SqlSession session, HashMap<String, String> paramMap);
	// 기존 인증 삭제
	public void resetAuthKey(SqlSession session, String mEmail);
	// 이메일로 아이디 찾기
	public String getIdByEmail(SqlSession session, String mEmail);
	// 이메일로 회원 여부 확인
	public int checkMemberByEmail(SqlSession session, String mEmail);
	public List<Member> getMemberInfo(SqlSession session, HashMap<String, String> authData);

}
