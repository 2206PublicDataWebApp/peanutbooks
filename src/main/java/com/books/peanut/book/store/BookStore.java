package com.books.peanut.book.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookSeries;
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

	/**피넛오리지널 다음화 등록하기*/
	int insertOriSeriesNext(SqlSessionTemplate session, OriginBookSeries obSeries);

	/**사용자의 시리즈 구입여부 체크*/
	int selectOnebokkPurchase(SqlSessionTemplate session, String memberId, int seriesNo, int bookNo);
	
	/**구독권 소지여부 체크*/
	int selectCheckSubscribe(SqlSessionTemplate session, String memberId);

	/**피넛 갯수 확인하기*/
	int selectCheckPoint(SqlSessionTemplate session, String memberId);

	/**시리즈 한편 구입하기
	 * @param bookTitle */
	int updatebuyOneSeries(SqlSessionTemplate session, int seriesNo, int bookNo, String memberId, String bookTitle);

	/**피넛 오리지널 시리즈 수정*/
	int updateOriSeries(SqlSessionTemplate session, OriginBookSeries obSeries);

	/**모든 일반도서의 갯수파악*/
	int countAllnorBook(SqlSessionTemplate session);

	/**모든 일반도서 시리즈 가져오기
	 * @param session */
	List<NormalBookSeries> selectAllNorSeries(SqlSessionTemplate session);

	/**일반도서 제목 가져오기*/
	String selectNorbookTitle(SqlSessionTemplate session, String bookNo);

	/**일반도서 등록하기*/
	int insertNorBook(SqlSessionTemplate session, NormalBook nBook);

	/**일반도서 시리즈 등록하기*/
	int insertNSeriesBook(SqlSessionTemplate session, NormalBookSeries nSeries);

	/**일반 도서 태그 등록하기*/
	int insertNBTag(SqlSessionTemplate session, HashTag hTag);

	/**일반 도서 열람하기*/
	NormalBook selectOneNorBook(SqlSessionTemplate session, String bookNo);

	/**일반도서 시리즈의 특정정보 가져오기*/
	List<NormalBookSeries> selectOneNorSeriesTitle(SqlSessionTemplate session, String bookNo);

}
