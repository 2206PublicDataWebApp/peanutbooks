package com.books.peanut.book.service;

import java.util.List;

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

	/**
	 * 지금까지 쓴 도서 불러오기
	 * @param memberId
	 * @return
	 */
	List<OriginBookSeries> allOriSeries(String memberId);

	/**책이름 가져오기*/
	String getBookTitle(String bookTitle);

	/**책 한권 가져오기*/
	OriginBook showOnebook(String bookNo);
	/**시리즈 타이틀 전부 가져오기*/
	List<OriginBookSeries> getSeriesTitle(String bookNo);

	/**책 한권에 해당하는 태그 가져오기
	 * @param category */
	HashTag getBookTga(String bookNo, String category);

	/**회원의 닉네임 가져오기*/
	String getMemberNickName(String memberId);

}
