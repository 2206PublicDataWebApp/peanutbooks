package com.books.peanut.ejmember.service;

import com.books.peanut.ejmember.domain.Member;

public interface MemberService {

	public int registerMember(Member member);

	public Member loginMember(Member member);

}
