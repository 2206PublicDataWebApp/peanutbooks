package com.books.peanut.book.store;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.WriterProfile;

public interface BookStore {

	/***
	 * 작가 프로필 등록
	 * @param wrtiePro
	 * @param session
	 * @return
	 */
	int insertProfile(WriterProfile wrtiePro, SqlSessionTemplate session);

	/**
	 * 작가 프로필 불러오기
	 * @param session
	 * @param memberId
	 * @return
	 */
	WriterProfile selectProfile(SqlSessionTemplate session, String memberId);

	
	/**
	 * 작가프로필 수정
	 * @param session
	 * @param wrtiePro
	 * @return
	 */
	int updateProfile(SqlSessionTemplate session, WriterProfile wrtiePro);

	/**피넛오리지널 도서 등록 메인*/
	int insertOribook(SqlSessionTemplate session, OriginBook oBook);

	/*해시태그 등록*/
	int insertTag(SqlSessionTemplate session, HashTag hTag);
	
	/**피넛 오리지널 시리즈 등록*/
	int insertOriSeries(SqlSessionTemplate session, OriginBookSeries oSeries);

}
