package com.books.peanut.book.service;

import java.util.List;


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
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.PeanutPoint;

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
	 * @param boardLimit 
	 * @param i 
	 * @param memberId
	 * @return
	 */
	List<OriginBookSeries> allOriSeries(int i, int boardLimit, String memberId);

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

	/**회원별점가져오기
	 * @param starOne */
	Star getOneBookStar(Star starOne);

	/**회원이 쓴 피넛 오리지널의 갯수 파악*/
	int allOriSeriesCount(String memberId);

	/**작가의 모든 책 제목 가져오기*/
	List<OriginBook> allWirterbookTitle(String memberId);

	/**피넛 오리지널 해당 책 번호의 모든 시리즈 가져오기*/
	List<OriginBookSeries> allOriBookSeries(String bookNo);

	
	/**유료여부 확인*/
	String checkPaid(int seriesNo, int bookNo);

	/**시리즈 한편가져오기*/
	OriginBookSeries getOneSeries(int seriesNo, int bookNo);

	/**작성자 맞는지 체크하기*/
	int checkWriter(int bookNo, String memberId);

	/**피넛 오리지널 다음화 데이터베이스에 등록하기*/
	int registOriSeriesNext(OriginBookSeries obSeries);

	/**사용자의 시리즈 구입여부 체크*/
	int checkPurchase(String memberId, int seriesNo, int bookNo);

	/**구독권 사용자인지 체크*/
	int checkSUbcribe(String memberId);

	/**피넛갯수 확인하기*/
	int checkNowPoint(String memberId);
	
	/**땅콩으로 시리즈 구입
	 * @param bookTitle */
	int buyOneSeries(int seriesNo, int bookNo, String memberId, String bookTitle);

	/**피넛 오리지널 시리즈 수정*/
	int modifyOriSeries(OriginBookSeries obSeries);

	/**모든 일반도서의 책의 갯수 파악*/
	int allNorSeriesCount();

	/**모든 일반도서 시리즈 가져오기
	 * @param boardLimit 
	 * @param i */
	List<NormalBookSeries> allAdminBooks(int i, int boardLimit);

	/**일반도서 제목 가져오기*/
	String getNorBookTitle(String bookNo);

	/**일반도서 등록하기*/
	int registenorBook(NormalBook nBook);

	/**일반도서 시리즈 등록하기*/
	int registNoriSeries(NormalBookSeries nSeries);

	/**일반도서 태그 등록하기*/
	int registeNorTag(HashTag hTag);

	/**일반 도서 열람하기*/
	NormalBook showOneNorbook(String bookNo);

	/**일반도서 시리즈의 정보가져오기*/
	List<NormalBookSeries> getNorSeriesTitle(String bookNo);

	/**일반도서 작가가 등록한 도서 목록 가져오기*/
	List<NormalBook> allNorWirterbookTitle(String writer);

	/**일반도서 한권에 모든 시리즈 가져오기*/
	List<NormalBookSeries> allNorBookSeries(String bookNo);

	/**일반도서 시리즈 1개 가져오기*/
	NormalBookSeries getOneNorBookSeries(int bookNo, int seriesNo);

	/**일반도서 시리즈 다음화 등록*/
	int registNorSeriesNext(NormalBookSeries nSeries);

	/**도서 언어여부 확인하기*/
	String getlanguege(String string);

	/**일반도서 시리즈 수정하기*/
	int modifyNorSeries(NormalBookSeries nbSeries);

	/**현재 한 도서의 모든 시리즈 번호 가지고 오기
	 * @param bookNo */
	List<NormalBookSeries> getNorSeriesNo(int bookNo);

	/**오리지널 시리즈 삭제*/
	int removeOriBookSeries(String bookNo, Integer seriesNo);

	/**피넛 오리지널 도서 삭제*/
	int removeOriBook(String bookNo);

	/**일반도서 시리즈 하나 삭제*/
	int removeNorBookSeries(String bookNo, Integer seriesNo);


	/**일반도서 삭제*/
	int removeNorBook(int bookNo);

	/**피넛 오리지널 한권의 삭제되지 않고 허가된 모든 시리즈 번호 가져오기*/
	List<OriginBookSeries> getOriSeriesNo(int bookNo);

	/**피넛 오리지널 한권의 모든 시리즈 번호 가져오기*/
	List<OriginBookSeries> getOneOriSeriesAllNo(int bookNo);

	/**내 서재에 등록됐는지 확인하기*/
	int checkMybookMember(Library library);

	/**내서재 등록**/
	int addMybook(Library library);

	/**내 서재 삭제*/
	int removeMybook(Library library);

	/**내 서재 목록 가져오기
	 * @param searchValue 
	 * @param step 
	 * @param category 
	 * @param bPage */
	List<Library> getOneMemberLibrary(String memberId, String category, String step, String searchValue,int page, int limit);

	/**피넛 오리지널 삭제되지 않고 승인된 책의 제목, 표지 가져오기*/
	OriginBook getOneBookStatus(String bookNo);

	/**일반도서 삭제되지 않고 승인된 책의 제목, 표지 가져오기*/
	NormalBook getNorBookStatus(String bookNo);

	/**내 서재 피넛 오리지널 목록 가져오기*/
	List<Library> getOneMemberOriLibrary(String memberId);

	/**내 서재 일반도서 목록가져오기*/
	List<Library> getOneMemberNorLibrary(String memberId);

	/**내 구입도서 가져오기
	 * @param searchValue 
	 * @param step 
	 * @param boardLimit 
	 * @param i */
	List<peanutPaidSeries> getOneMemberPaid(String memberId, String step, String searchValue, int i, int boardLimit);

	/**내가 구입한 모든 도서 가져오기*/
	OriginBookSeries getOneBookSeriesStatus(String bookNo, String seriesNo);

	/**페이징용 내 서재 갯수가져오기*/
	int countOneMemberLibrary(String memberId, String category, String step, String searchValue);

	/**페이징용 내 구입도서 시리즈 숫자 가져오기*/
	int getOneMemberPaidCount(String memberId, String step, String searchValue);

	/**피넛 오리지널 검색 숫자
	 * @param searchValue2 */
	int OriBookSearchValueCount(String tag, String step, String searchValue, String category);

	/**일반도서 검색숫자
	 * @param searchValue2 */
	int NorBookSearchValueCount(String tag, String step, String searchValue, String category);

	/**피넛 오리지널 도서 검색
	 * @param category */
	List<OriginBook> allBookSearchValue(String tag, String step, String searchValue, Integer page, int getTotalCountOri, String category);
	
	/**일반 도서 검색
	 * @param category */
	List<NormalBook> allBookSearchValueNor(String tag, String step, String searchValue, Integer page,
			int getTotalCountNor, String category);

	/**조회수 높은 동화그림*/
	String getNorImgName();
	/**회수 높은 시 그림*/
	String getNorImgName2();

	/**조회수 높은 소설그림*/
	String getOriImgName();
	/**조회수 높은 동화그림*/
	String getOriImgName2();

	/**피넛 오리지널 탑 6개 카테고리별로 가져오기*/
	List<OriginBook> getRankOriBook(String category);

	/**피넛 오리지널 카테고리별 총 갯수*/
	int countAllOriginCategory(String category);
	/**피넛 오리지널 카테고리별 모든도서
	 * @param step */
	List<OriginBook> getAllOriginCategory(String category, int currentPage, int bookLimit, String step);
	/**일반도서 카테고리별 총 갯수*/
	int countAllNormalCategory(String category);
	/**일반도서 카테고리별 모든도서
	 * @param bookLimit 
	 * @param getTotalCount 
	 * @param step */
	List<NormalBook> getAllNormalCategory(String category, int getTotalCount, int bookLimit, String step);

	/**일반도서 탑 6개 카테고리별로 가져오기*/
	List<NormalBook> getRankNorBook(String string);
	
	
	/**피넛 오리지널 시리즈 조회수 올리기
	 * @param pCheck */
	int registViewCount(Member member, int seriesNo, int bookNo, String pCheck);
	
	/**일반도서 조회수 추가하기*/
	int plusCountOne(String memberId, int seriesNo, int bookNo);

	/**토탈 상위 도서 3권 가져오기*/
	List<NormalBook> getRankTopBook();

	/**가장 별점많은 추리도서 가져오기*/
	NormalBook getTopScore(String string);

	/**별점많은 카테고리 가져오기*/
	List<NormalBook> getTopScore4(String category);

	/**별점 가장 많은 카테고리 1권 --*/
	OriginBook getTopScoreOri(String string);
	
	/**별점 가장많은 카테고리 4권*/
	List<OriginBook> getTopScore4Ori(String string);

	/**오리지널 도서 북 인포 수정하기*/
	int modifyOriBookInfo(OriginBook oBook);

	/**해시태그 수정하기*/
	int modifyOriBookTag(HashTag hTag);

	/**일반도서 인포 수정하기*/
	int modifyNorBooksInfo(NormalBook nBook);

	/**수정테이블의 시리즈 한편가져오기*/
	OriginBookSeries getOneModifySeries(int seriesNo);

	/**피넛 오리지널 수정 승인
	 * @param modifyNo */
	int modifyOriSeriesProve(OriginBookSeries oModifyS, int modifyNo);

	/**시리즈 삭제하기*/
	int removeOneORiBookSeries(Integer seriesNo, Integer bookNo);

	/**수정테이블에 있는지 확인*/
	int modifyCheck(OriginBookSeries obSeries);

	/**책 영구삭제*/
	int removeOriBookMember(String bookNo);



}
