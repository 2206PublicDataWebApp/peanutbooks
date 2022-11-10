package com.books.peanut.member.store.logic;

import java.util.HashMap;
import java.util.List;

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
	// 인증 키 저장
	@Override
	public int updateAuthKey(SqlSession session, String authKey, String mEmail) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("authKey", authKey);
		paramMap.put("mEmail", mEmail);
		int result = session.update("MemberMapper.updateAuthKey", paramMap);
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
	@Override
	public int selectSavedBooks(SqlSession session, String memberId) {
		int result = session.selectOne("MemberMapper.selectSavedBooks", memberId);
		return result;
	}
	// 로그인한 회원이 등록한 작품 수 가져오기
	@Override
	public int selectWrittenBooks(SqlSession session, String memberId) {
		int result = session.selectOne("MemberMapper.selectWrittenBooks", memberId);
		return result;
	}
	// 이메일 인증 키 검사
	@Override
	public int checkAuthKey(SqlSession session, HashMap<String, String> paramMap) {
		int result = session.selectOne("MemberMapper.checkAuthKey", paramMap);
		return result;
	}
	// 이메일 인증 여부 업데이트
	@Override
	public int updateEmailYN(SqlSession session, HashMap<String, String> paramMap) {
		int result = session.update("MemberMapper.updateEmailYN", paramMap);
		return result;
	}
	// 기존 인증 키 삭제
	@Override
	public void resetAuthKey(SqlSession session, String mEmail) {
		session.update("MemberMapper.resetAuthKey", mEmail);
	}
	// 이메일로 아이디 찾기
	@Override
	public String getIdByEmail(SqlSession session, String mEmail) {
		String memberId = session.selectOne("MemberMapper.getIdByEmail", mEmail);
		return memberId;
	}
	@Override
	public int checkMemberByEmail(SqlSession session, String mEmail) {
		int result = session.selectOne("MemberMapper.checkMemberByEmail", mEmail);
		return result;
	}
	@Override
	public List<Member> getMemberInfo(SqlSession session, HashMap<String, String> authData) {
		List<Member> result = session.selectList("MemberMapper.getMemberInfo", authData);
		return result;
	}
	@Override
	public int loginCheck(SqlSession session, HashMap<String, String> paramMap) {
		int result = session.selectOne("MemberMapper.loginCheck", paramMap);
		return result;
	}
	// 비밀번호 재설정
	@Override
	public int updateMemberPw(SqlSession session, HashMap<String, String> paramMap) {
		int result = session.update("MemberMapper.updateMemberPw", paramMap);
		return result;
	}
	
}
