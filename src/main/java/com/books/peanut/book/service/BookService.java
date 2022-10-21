package com.books.peanut.book.service;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.WriterProfile;

public interface BookService {

	/*
	 * 작가 프로필 등록
	 */
	int registProfile(WriterProfile wrtiePro);

	/**
	 * 작가 프로필 불러오기
	 * @param memberId 
	 * @return
	 */
	WriterProfile getProfile(String memberId);

	/**
	 * 작가 프로필 수정
	 * @param wrtiePro
	 * @return
	 */
	int modifyProfile(WriterProfile wrtiePro);

	/**피넛 오리지널 책등록 메인*/
	int registeOriBook(OriginBook oBook);

	/**책등록 태그*/
	int registeTag(HashTag hTag);

	/**피넛 오리지널 책등록 시리즈*/
	int registOriSeries(OriginBookSeries oSeries);

}
