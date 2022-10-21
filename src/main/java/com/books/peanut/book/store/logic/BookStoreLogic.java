package com.books.peanut.book.store.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.WriterProfile;
import com.books.peanut.book.store.BookStore;

@Repository
public class BookStoreLogic implements BookStore{

	/**
	 * 작가 프로필 등록
	 */
	@Override
	public int insertProfile(WriterProfile wrtiePro, SqlSessionTemplate session) {
		int result = session.insert("wirterMapper.insertProfile",wrtiePro);
		return result;
	}
	/**
	 * 작가 프로필 불러오기
	 */
	@Override
	public WriterProfile selectProfile(SqlSessionTemplate session, String memberId) {
		WriterProfile oneProfile = session.selectOne("wirterMapper.selectOneMember",memberId);
		return oneProfile;
	}

	/***
	 * 작가 프로필 수정
	 */
	@Override
	public int updateProfile(SqlSessionTemplate session, WriterProfile wrtiePro) {
		int result = session.update("wirterMapper.updateProfile",wrtiePro);
		return result;

	}
	
	/**
	 * 피넛 오리지널 메인 도서 등록
	 */
	@Override
	public int insertOribook(SqlSessionTemplate session, OriginBook oBook) {
		int result = session.insert("wirterMapper.insertBook",oBook);
		return result;
	}
	
	/***
	 * 태그등록
	 */
	@Override
	public int insertTag(SqlSessionTemplate session, HashTag hTag) {
		int result = session.insert("wirterMapper.insertSeries",hTag);
		return result;
	}
	
	/**
	 * 피넛 오리지널 시리즈 등록
	 */
	@Override
	public int insertOriSeries(SqlSessionTemplate session, OriginBookSeries oSeries) {
		int result = session.insert("wirterMapper.insertTag",oSeries);
		return result;
	}

	

}
