package com.books.peanut.ejmember.store;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.ejmember.domain.Member;
@Repository
public class StoreLogic implements MemberStore{
//회원가입
	@Override
	public int insertMember(SqlSessionTemplate session, Member member) {
		int result=session.insert("MemberMapper.insertMember",member);
		return result;
	}
//로그인 1명정보
	@Override
	public Member selectLoginMember(SqlSessionTemplate session, Member member) {
		Member mOne=session.selectOne("MemberMapper.selectLoginOne",member);
		return mOne;

	}
}
