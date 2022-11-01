package com.books.peanut.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.member.domain.Member;
import com.books.peanut.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{
	// 회원가입
	@Override
	public int insertMember(SqlSession session, Member member){
		int result = session.insert("MemberMapper.insertMember", member);
		return result;
	}
	// 별명 유효성 검사
	@Override
	public int checkNickname(SqlSession session, String mNickname) {
		int result = session.selectOne("MemberMapper.checkNickname", mNickname);
		return result;
	}
	// 아이디 유효성 검사
	@Override
	public int checkId(SqlSession session, String memberId) {
		int result = session.selectOne("MemberMapper.checkId", memberId);
		return result;
	}
	// 이메일 유효성 검사
	@Override
	public int checkEmail(SqlSession session, String mEmail) {
		int result = session.selectOne("MemberMapper.checkEmail", mEmail);
		return result;
	}
	// 로그인
	@Override
	public Member selectLoginMember(SqlSession session, Member member) {
		Member mResult = session.selectOne("MemberMapper.selectLoginMember", member);
		return mResult;
	}
	// 회원 정보 수정
	@Override
	public int updateInfo(SqlSession session, Member member) {
		int result = session.update("MemberMapper.updateInfo", member);
		return result;
	}
	// 회원탈퇴
	@Override
	public int deleteMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.deleteMember", member);
		return result;
	}
	// 내 서재 - 저장된 도서 개수 가져오기
//	@Override
//	public int selectSavedBooks(SqlSession session, String memberId) {
//		int result = session.selectOne("MemberMapper.selectSavedBooks", memberId);
//		return result;
//	}
	// 로그인한 회원이 등록한 작품 수 가져오기
	@Override
	public int selectWrittenBooks(SqlSession session, String memberId) {
		int result = session.selectOne("MemberMapper.selectWrittenBooks", memberId);
		return result;
	}
	// 이메일 인증 키 확인
//	@Override
//	public Member emailAuth(SqlSession session, String authKey) {
//		Member mResult = session.selectOne("MemberMapper.emailAuth", authKey);
//		return mResult;
//	}
	// 이메일 인증 성공 후 계정 활성화
//	@Override
//	public void successAuth(SqlSession session, Member member) throws Exception {
//		session.update("MemberMapper.successAuth", member);
//	}
}
