package com.books.peanut.member.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.member.domain.Member;
import com.books.peanut.member.service.MemberService;
import com.books.peanut.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private MemberStore mStore;

	// 회원가입
	@Override
	public int registerMember(Member member) {
		int result = mStore.insertMember(session, member);
		return result;
	}
	// 별명 유효성 검사
	@Override
	public int checkNickname(String mNickname) {
		int result = mStore.checkNickname(session, mNickname);
		return result;
	}
	// 아이디 유효성 검사
	@Override
	public int checkId(String memberId) {
		int result = mStore.checkId(session, memberId);
		return result;
	}
	// 로그인
	@Override
	public Member loginMember(Member member) {
		Member mResult = mStore.selectLoginMember(session, member);
		return mResult;
	}
	
}
