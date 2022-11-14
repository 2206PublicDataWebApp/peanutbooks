package com.books.peanut.member.service.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.member.domain.Member;
import com.books.peanut.member.service.MemberService;
import com.books.peanut.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private SqlSession session;
	@Autowired
	private MemberStore mStore;

	// 회원가입
	@Override
	public int registerMember(Member member) {
		int result = mStore.insertMember(session, member);
		return result;
	}
	// 인증 키 저장
	@Override
	public int saveAuthKey(String authKey, String mEmail) {
		int result = mStore.updateAuthKey(session, authKey, mEmail);
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
	// 이메일 유효성 검사
	@Override
	public int checkEmail(String mEmail) {
		int result = mStore.checkEmail(session, mEmail);
		return result;
	}
	// 로그인
	@Override
	public Member loginMember(Member member) {
		Member mResult = mStore.selectLoginMember(session, member);
		return mResult;
	}
	// 회원 정보 수정
	@Override
	public int modifyInfo(Member member) {
		int result = mStore.updateInfo(session, member);
		return result;
	}
	// 회원탈퇴
	@Override
	public int deleteMember(Member member) {
		int result = mStore.deleteMember(session, member);
		return result;
	}
	// 내 서재 - 저장된 도서 수 가져오기
	@Override
	public int countSavedBooks(String memberId) {
		int result = mStore.selectSavedBooks(session, memberId);
		return result;
	}
	// 로그인한 회원이 등록한 작품 수 가져오기
	@Override
	public int countWrittenBooks(String memberId) {
		int result = mStore.selectWrittenBooks(session, memberId);
		return result;
	}
	// 이메일 인증 키 저장
	@Override
	public int checkAuthKey(HashMap<String, String> paramMap) {
		int result = mStore.checkAuthKey(session, paramMap);
		return result;
	}
	// 이메일 인증 여부 업데이트
	@Override
	public int authEmail(HashMap<String, String> paramMap) {
		int result = mStore.updateEmailYN(session, paramMap);
		return result;
	}
	// 기존 인증 키 삭제
	@Override
	public void resetAuthKey(String mEmail) {
		mStore.resetAuthKey(session, mEmail);
	}
	// 이메일로 아이디 찾기
	@Override
	public String getIdByEmail(String mEmail) {
		String memberId = mStore.getIdByEmail(session, mEmail);
		return memberId;
	}
	// 이메일로 회원 여부 확인
	@Override
	public int checkMemberByEmail(String mEmail) {
		int result = mStore.checkMemberByEmail(session, mEmail);
		return result;
	}
	@Override
	public List<Member> getMemberInfo(HashMap<String, String> authData) {
		List<Member> result = mStore.getMemberInfo(session, authData);
		return result;
	}
	@Override
	public int loginCheck(HashMap<String, String> paramMap) {
		int result = mStore.loginCheck(session, paramMap);
		return result;
	}
	// 비밀번호 재설정
	@Override
	public int resetMemberPw(HashMap<String, String> paramMap) {
		int result = mStore.updateMemberPw(session, paramMap);
		return result;
	}
	// 네아로 회원 확인
	@Override
	public int selectMemberById(HashMap<String, String> paramMap) {
		int result = mStore.selectMemberById(session, paramMap);
		return result;
	}
	// sns 회원가입 기능
	@Override
	public int snsJoin(Member member) {
		int result = mStore.snsJoin(session, member);
		return result;
	}
	// 네아로
	@Override
	public Member snsLogin(HashMap<String, String> paramMap) {
		Member loginMember = mStore.snsLogin(session, paramMap);
		return loginMember;
	}

}
