package com.books.peanut.book.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.Star;
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

	/**해시태그 등록*/
	int insertTag(SqlSessionTemplate session, HashTag hTag);
	
	/**피넛 오리지널 시리즈 등록*/
	int insertOriSeries(SqlSessionTemplate session, OriginBookSeries oSeries);

	/**피넛 오리지널 책 한권에 해당하는 모든 시리즈 가져오기
	 * @param boardLimit 
	 * @param i */
	List<OriginBookSeries> selectAllOriSeries(int i, int boardLimit, String memberId, SqlSessionTemplate session);

	/**피넛 오리지널 책 한권 제목 가져오기*/
	String selectBookTitle(String bookTitle, SqlSessionTemplate session);

	/**피넛 오리지널 도서 한권 가져오기*/
	OriginBook selectOneBook(SqlSessionTemplate session, String bookNo);

	/**피넛 오리지널 도서 한권의 시리즈 제목 전부 가져오기*/
	List<OriginBookSeries> selectSeriesTitle(SqlSessionTemplate session, String bookNo);

	/**피넛 오리지널 도서 한권의 태그 전부 가져오기
	 * @param category 
	 * @param session */
	HashTag selectOneBookTag(String bookNo, String category, SqlSessionTemplate session);

	/**회원 닉네임 가져오기*/
	String selectOneMemberNick(SqlSessionTemplate session, String memberId);

	/**회원 별점 가져오기*/
	Star selectOneBookStar(SqlSessionTemplate session, Star starOne);

	/**회원이 쓴 피넛 오리지널의 갯수 파악*/
	int selectAllMemberOriSeries(SqlSessionTemplate session, String memberId);

	/**작가의 모든 책 가지고 오기
	 * @param session */
	List<OriginBook> selectAllWritebookTitle(SqlSessionTemplate session, String memberId);

	/**피넛 오리지널 책번호의 모든 시리즈 가져오기*/
	List<OriginBookSeries> selectAlloriBookSeries(SqlSessionTemplate session, String bookNo);

	/**유료화 여부 체크*/
	String selectOneCheckPaid(SqlSessionTemplate session, int seriesNo, int bookNo);

	/**시리즈 한편 가져오기*/
	OriginBookSeries selectOneSeries(SqlSessionTemplate session, int seriesNo, int bookNo);

	/**작성자 맞는지 체크하기*/
	int selectcheckWirter(SqlSessionTemplate session, int bookNo, String memberId);

}
