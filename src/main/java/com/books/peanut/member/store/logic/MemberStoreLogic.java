package com.books.peanut.member.store.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.member.domain.Member;
import com.books.peanut.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{
	// 회원가입
	@Override
	public int insertMember(SqlSession session, Member member) {
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
}
