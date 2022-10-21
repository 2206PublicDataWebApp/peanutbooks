package com.books.peanut.book.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.WriterProfile;
import com.books.peanut.book.service.BookService;
import com.books.peanut.book.store.BookStore;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	BookStore bStore;
	@Autowired
	SqlSessionTemplate session;
	
	/**
	 * 작가 프로필 등록
	 */
	@Override
	public int registProfile(WriterProfile wrtiePro) {
		int result = bStore.insertProfile(wrtiePro, session);
		
		return result;
	}

	/**
	 *작가 프로필 불러오기
	 */
	@Override
	public WriterProfile getProfile(String memberId) {
		WriterProfile oneWriter = bStore.selectProfile(session, memberId);
		return oneWriter;
	}

	/**
	 * 작가 프로필 수정
	 */
	@Override
	public int modifyProfile(WriterProfile wrtiePro) {
		int result = bStore.updateProfile(session, wrtiePro);
		return result;
	}

	/*피넛 오리지널 도서 등록 메인*/
	@Override
	public int registeOriBook(OriginBook oBook) {
		int result = bStore.insertOribook(session, oBook);
		return result;
	}

	/*태그등록*/
	@Override
	public int registeTag(HashTag hTag) {
		int result = bStore.insertTag(session, hTag);
		return result;
	}

	/**
	 * 피넛 오리지널 시리즈 등록
	 */
	@Override
	public int registOriSeries(OriginBookSeries oSeries) {
		int result = bStore.insertOriSeries(session, oSeries);
		return result;
	}
	

}
