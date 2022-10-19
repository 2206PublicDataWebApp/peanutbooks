package com.books.peanut.ejmember.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.ejmember.domain.Member;
import com.books.peanut.ejmember.store.MemberStore;
@Service
public class ServiceImpl implements MemberService{
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private MemberStore mStore;
	
	@Override
	public int registerMember(Member member) {
		int result=mStore.insertMember(session, member);
		return result;
	}

	@Override
	public Member loginMember(Member member) {
		Member mOne=mStore.selectLoginMember(session, member);
		return mOne;
	}

}
