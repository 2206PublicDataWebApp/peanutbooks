package com.books.peanut.ejmember.store;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.ejmember.domain.Member;

public interface MemberStore {

	public int insertMember(SqlSessionTemplate session, Member member);

	public Member selectLoginMember(SqlSessionTemplate session, Member member);
}
