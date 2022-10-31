package com.books.peanut.book.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.BookPage;
import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookSeries;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.Star;
import com.books.peanut.book.domain.WriterProfile;
import com.books.peanut.book.domain.peanutPaidSeries;

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
	 * @param session 
	 * @param boardLimit 
	 * @param i */
	List<NormalBookSeries> selectAllNorSeries(SqlSessionTemplate session, int i, int boardLimit);

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

	/**일반도서 작가의 모든 작성 도서 가져오기*/
	List<NormalBook> selectNorWriterbTitle(SqlSessionTemplate session, String writer);

	/**일반도서 한권의 모든 시리즈 가져오기*/
	List<NormalBookSeries> selectAllNorBookSeries(SqlSessionTemplate session, String bookNo);

	/**일반도서 시리즈 1개 가져오기*/
	NormalBookSeries selectOneNorSeries(SqlSessionTemplate session, int bookNo, int seriesNo);

	/**일반도서 다음화 등록*/
	int insertNorSeriesNext(SqlSessionTemplate session, NormalBookSeries nSeries);

	/**도서의 언어여부 확인하기*/
	String selectBookLanguage(SqlSessionTemplate session, String string);

	/**일반도서 시리즈 수정*/
	int updateNorBookSeries(SqlSessionTemplate session, NormalBookSeries nbSeries);

	/**일반도서 한개의 모든 시리즈 번호 가지고 오기*/
	List<NormalBookSeries> selectOneNorBookSeriesNo(SqlSessionTemplate session, int bookNo);

	/**오리지널 시리즈 한편 삭제*/
	int updateOriSeriesRemove(SqlSessionTemplate session, String bookNo, Integer seriesNo);
	/**피넛 오리지널 도서 삭제*/
	int updateOriRemove(SqlSessionTemplate session, String bookNo);

	/**일반 도서 시리즈 하나 삭제*/
	int deleteNorBookSeries(SqlSessionTemplate session, String bookNo, Integer seriesNo);

	/**일반 도서 삭제*/
	int updateNorRemove(SqlSessionTemplate session, int bookNo);

	/**피넛 오리지널 한편에 모든 삭제되지 않고 허가된 시리즈 번호 가져오기*/
	List<OriginBookSeries> selectOneOriBookSeriesNo(SqlSessionTemplate session, int bookNo);
	
	/**피넛 오리지널 한편에 모든 시리즈 번호 가져오기*/
	List<OriginBookSeries> selectOneOriBookAllSeriesNo(SqlSessionTemplate session, int bookNo);

	/**내 서재 등록여부 확인하기*/
	int selectMybookMember(SqlSessionTemplate session, Library library);

	/**내 서재 등록
	 * @param session */
	int insertMybook(SqlSessionTemplate session, Library library);

	/**내 서재 삭제*/
	int deleteMybook(SqlSessionTemplate session, Library library);

	/**내 서재 불러오기
	 * @param session 
	 * @param searchValue 
	 * @param step 
	 * @param category 
	 * @param bPage */
	List<Library> selectOneMemberLibrary(SqlSessionTemplate session, String memberId, String category, String step, String searchValue,int page, int limit);

	/**피넛 오리지널 삭제되지 않고 승인된 책 한권의 제목, 표지 가져오기*/
	OriginBook selectOneOriBookStatus(SqlSessionTemplate session, String bookNo);

	/**일반도서 삭제되지 않고 승인된 책 한권의 제목, 표지 가져오기*/
	NormalBook selectOneNorBookStatus(SqlSessionTemplate session, String bookNo);

	/**내 서재 피넛 오리지널 불러오기*/
	List<Library> selectOneMemberOriLibrary(SqlSessionTemplate session, String memberId);

	/**내 서재 일반도서 불러오기*/
	List<Library> selectOneMemberNorLibrary(SqlSessionTemplate session, String memberId);

	/**구입한 도서 불러오기
	 * @param searchValue 
	 * @param step 
	 * @param limit 
	 * @param currentPage */
	List<peanutPaidSeries> selectAllOneMemberPaid(SqlSessionTemplate session, String memberId, String step, String searchValue, int currentPage, int limit);

	/**내 구입 시리즈 삭제되지 않고 승인된*/
	OriginBookSeries selectOneBookSeriesStatus(SqlSessionTemplate session, String bookNo, String seriesNo);

	/**페이징용 내 서재 총 갯수*/
	int selectCountOneMemberLibrary(SqlSessionTemplate session, String memberId, String category, String step,
			String searchValue);

	/**페이징용 내 구입시리즈 갯수*/
	int selectOneBookSeriesStatusCount(SqlSessionTemplate session, String memberId, String step, String searchValue);

	/**피넛 오리지널 검색숫자
	 * @param category */
	int selectOriBookSearchCount(SqlSessionTemplate session, String tag, String step, String searchValue, String category);

	/**일반도서 검색숫자
	 * @param category */
	int selectNorBookSearchCount(SqlSessionTemplate session, String tag, String step, String searchValue, String category);

	/**피넛 오리지널 검색
	 * @param category */
	List<OriginBook> selectBookSearchValue(SqlSessionTemplate session, String tag, String step, String searchValue,
			Integer page, int limit, String category);

	/**일반도서 검색
	 * @param category */
	List<NormalBook> selectBookSearchValueNor(SqlSessionTemplate session, String tag, String step, String searchValue,
			Integer page, int limit, String category);

	/**조회수 높은 동화그림*/
	String selectNorImgName(SqlSessionTemplate session);

	/**조회수 높은 시 그림*/
	String selectNorImgName2(SqlSessionTemplate session);
	
	
	/**조회수 높은 소설그림*/
	String selectOriImgName(SqlSessionTemplate session);
	/**조회수 높은 동화그림*/
	String selectOriImgName1(SqlSessionTemplate session);

	/**피넛 오리지널 랭킹 6까지 카테고리 별로 가져오기*/
	List<OriginBook> selectRankOriBook(SqlSessionTemplate session, String category);


}
