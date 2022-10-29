package com.books.peanut.book.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookSeries;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.Star;
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
	 * 피넛 오리지널 시리즈 1화 등록
	 */
	@Override
	public int registOriSeries(OriginBookSeries oSeries) {
		int result = bStore.insertOriSeries(session, oSeries);
		return result;
	}

	/**
	 * 사용자가 쓴 모든 오리지널 도서 시리즈 가져오기
	 */
	@Override
	public List<OriginBookSeries> allOriSeries(int i, int boardLimit,String memberId) {
		List<OriginBookSeries> osList = bStore.selectAllOriSeries(i, boardLimit,memberId, session);
		return osList;
	}

	/***
	 * 책번호로 도서 제목 가져오기
	 */
	@Override
	public String getBookTitle(String bookNo) {
		String bookTitle = bStore.selectBookTitle(bookNo, session);
		return bookTitle;
	}
	
	/**책한권가져오기*/
	@Override
	public OriginBook showOnebook(String bookNo) {
		OriginBook oBook = bStore.selectOneBook(session, bookNo);
		return oBook;
	}
	
	/**
	 * 도서 하나의 시리즈 제목만 가져오기	 */
	@Override
	public List<OriginBookSeries> getSeriesTitle(String bookNo) {
		List<OriginBookSeries> osList = bStore.selectSeriesTitle(session, bookNo);
		return osList;
	}

	/**책 한권에 해당하는 태그 가져오기*/
	@Override
	public HashTag getBookTga(String bookNo, String category) {
		HashTag hTag = bStore.selectOneBookTag(bookNo, category, session);
		return hTag;
	}

	/**회원 닉네임 가져오기*/
	@Override
	public String getMemberNickName(String memberId) {
		String mNick = bStore.selectOneMemberNick(session,memberId);
		return mNick;
	}

	/**별점가져오기*/
	@Override
	public Star getOneBookStar(Star starOne) {
		Star star = bStore.selectOneBookStar(session, starOne);
		return star;
	}

	/**회원이 쓴 피넛 오리지널의 갯수 파악*/
	@Override
	public int allOriSeriesCount(String memberId) {
		int count = bStore.selectAllMemberOriSeries(session, memberId);
		return count;
	}

	/**작가의 모든 책 제목 가져오기*/
	@Override
	public List<OriginBook> allWirterbookTitle(String memberId) {
		List<OriginBook> obList = bStore.selectAllWritebookTitle(session, memberId);
		return obList;
	}

	/**해당 피넛 오리지널의 모든 시리즈 가져오기*/
	@Override
	public List<OriginBookSeries> allOriBookSeries(String bookNo) {
		List<OriginBookSeries> osList = bStore.selectAlloriBookSeries(session, bookNo);
		return osList;
	}

	/**유료여부 체크*/
	@Override
	public String checkPaid(int seriesNo, int bookNo) {
		String paidCheck = bStore.selectOneCheckPaid(session,seriesNo,bookNo);
		return paidCheck;
	}

	/**시리즈 한편 가져오기*/
	@Override
	public OriginBookSeries getOneSeries(int seriesNo, int bookNo) {
		OriginBookSeries  obSeries = bStore.selectOneSeries(session,seriesNo,bookNo);
		return obSeries;
	}

	/**작성자 맞는지 체크하기*/
	@Override
	public int checkWriter(int bookNo, String memberId) {
		int result = bStore.selectcheckWirter(session,bookNo,memberId);
		return result;
	}

	/**피넛 오리지널 다음화 등록하기 */
	@Override
	public int registOriSeriesNext(OriginBookSeries obSeries) {
		int result =  bStore.insertOriSeriesNext(session, obSeries);
		return result;
	}

	/**사용자의 시리즈 구입여부 체크*/
	@Override
	public int checkPurchase(String memberId, int seriesNo, int bookNo) {
		int result = bStore.selectOnebokkPurchase(session,memberId,seriesNo,bookNo);
		return result;
	}

	/**구독권 사용자 여부 체크*/
	@Override
	public int checkSUbcribe(String memberId) {
		int result = bStore.selectCheckSubscribe(session,memberId);
		return result;
	}

	/**피넛 갯수 확인하기*/
	@Override
	public int checkNowPoint(String memberId) {
		int result = bStore.selectCheckPoint(session,memberId);
		return result;
	}

	/**땅콩으로 시리즈 구입*/
	@Override
	public int buyOneSeries(int seriesNo, int bookNo, String memberId, String bookTitle) {
		int result = bStore.updatebuyOneSeries(session,seriesNo,bookNo,memberId, bookTitle);
		return result;
	}

	/**피넛오리지널 시리즈 수정*/
	@Override
	public int modifyOriSeries(OriginBookSeries obSeries) {
		int result = bStore.updateOriSeries(session,obSeries);
		return result;
	}

	/**모든 일반도서 시리즈의 갯수 파악*/
	@Override
	public int allNorSeriesCount() {
		int result = bStore.countAllnorBook(session);
		return result;
	}

	/**모든 일반도서 시리즈 가져오기*/
	@Override
	public List<NormalBookSeries> allAdminBooks() {
		List<NormalBookSeries> nsList = bStore.selectAllNorSeries(session);
		return nsList;
	}

	
	/**일반도서 제목 가져오기*/
	@Override
	public String getNorBookTitle(String bookNo) {
		String ntitle = bStore.selectNorbookTitle(session, bookNo); 
		return ntitle;
	}

	/**일반도서 등록하기*/
	@Override
	public int registenorBook(NormalBook nBook) {
		int result = bStore.insertNorBook(session, nBook);
		return result;
	}

	
	/**일반도서 시리즈 등록하기*/
	@Override
	public int registNoriSeries(NormalBookSeries nSeries) {
		int result = bStore.insertNSeriesBook(session, nSeries);
		return result;
	}

	/**해시태그 등록하기*/
	@Override
	public int registeNorTag(HashTag hTag) {
		int result = bStore.insertNBTag(session, hTag);
		return result;
	}

	
	/**일반도서 열람하기*/
	@Override
	public NormalBook showOneNorbook(String bookNo) {
		NormalBook nBook = bStore.selectOneNorBook(session, bookNo);
		return nBook;
	}

	/**일반도서 시리즈의 정보 가져오기*/
	@Override
	public List<NormalBookSeries> getNorSeriesTitle(String bookNo) {
		List<NormalBookSeries> nList = bStore.selectOneNorSeriesTitle(session, bookNo);
		return nList;
	}

	/**일반도서 작가의 모든 책 제목*/
	@Override
	public List<NormalBook> allNorWirterbookTitle(String writer) {
		List<NormalBook> nbList = bStore.selectNorWriterbTitle(session,writer);
		return nbList;
	}

	/**일반도서 한권의 모든 시리즈 가져오기*/
	@Override
	public List<NormalBookSeries> allNorBookSeries(String bookNo) {
		List<NormalBookSeries> nsList = bStore.selectAllNorBookSeries(session, bookNo);
		return nsList;
	}

	/**일반도서 시리즈 한개 가져오기*/
	@Override
	public NormalBookSeries getOneNorBookSeries(int bookNo, int seriesNo) {
		NormalBookSeries nSeries = bStore.selectOneNorSeries(session, bookNo, seriesNo);
		return nSeries;
	}

	/**일반도서 시리즈 다음화 등록*/
	@Override
	public int registNorSeriesNext(NormalBookSeries nSeries) {
		int result = bStore.insertNorSeriesNext(session, nSeries);
		return result;
	}

	/**도서의 언어여부 확인하기*/
	@Override
	public String getlanguege(String string) {
		String lang = bStore.selectBookLanguage(session,string);
		return lang;
	}

	/**일반도서 시리즈 수정하기*/
	@Override
	public int modifyNorSeries(NormalBookSeries nbSeries) {
		int result = bStore.updateNorBookSeries(session,nbSeries);
		return result;
	}

	/**단 도서의 모든 시리즈 번호 가지고 오기*/
	@Override
	public List<NormalBookSeries> getNorSeriesNo(int bookNo) {
		 List<NormalBookSeries> nsList = bStore.selectOneNorBookSeriesNo(session,bookNo);
		return nsList;
	}

	/**피넛 오리지널 시리즈 한개 삭제*/
	@Override
	public int removeOriBookSeries(String bookNo, Integer seriesNo) {
		int result = bStore.updateOriSeriesRemove(session,bookNo,seriesNo);
		return result;
	}

	/**피넛 오리지널 도서 삭제*/
	@Override
	public int removeOriBook(String bookNo) {
		int result = bStore.updateOriRemove(session,bookNo);
		return result;
	}

	/**일반도서 시리즈 하나 삭제*/
	@Override
	public int removeNorBookSeries(String bookNo, Integer seriesNo) {
		int result = bStore.deleteNorBookSeries(session,bookNo,seriesNo);
		return result;
	}
	
	/**일반도서 삭제*/
	@Override
	public int removeNorBook(int bookNo) {
		int result = bStore.updateNorRemove(session,bookNo);
		return result;
	}

	/**피넛 오리지널 한편에 모든 허가되고 삭제되지 않은 시리즈 번호 가져오기*/
	@Override
	public List<OriginBookSeries> getOriSeriesNo(int bookNo) {
		List<OriginBookSeries> osList = bStore.selectOneOriBookSeriesNo(session,bookNo);
		return osList;
	}

	/**피넛 오리지널 한편에 모든 시리즈 번호 가져오기*/
	@Override
	public List<OriginBookSeries> getOneOriSeriesAllNo(int bookNo) {
		List<OriginBookSeries> osList = bStore.selectOneOriBookAllSeriesNo(session,bookNo);
		return osList;
	}

	/**내서재에 등록됐는지 확인하기*/
	@Override
	public int checkMybookMember(Library library) {
		int result = bStore.selectMybookMember(session,library);
		return result;
	}

	/**내 서재 등록*/
	@Override
	public int addMybook(Library library) {
		int result = bStore.insertMybook(session, library);
		return result;
	}

	/**내 서재 삭제*/
	@Override
	public int removeMybook(Library library) {
		int result = bStore.deleteMybook(session, library);
		return result;
	}

	/**내 서재 불러오기*/
	@Override
	public List<Library> getOneMemberLibrary(String memberId) {
		List<Library> lList = bStore.selectOneMemberLibrary(session, memberId);
		return lList;
	}

	/**피넛 오리지널 삭제되지 않고 승인된 책의 책 제목, 표지 가져오기*/
	@Override
	public OriginBook getOneBookStatus(String bookNo) {
		OriginBook oBook = bStore.selectOneOriBookStatus(session,bookNo);
		return oBook;
	}
	
	/**일반도서 삭제되지 않고 승인된 책의 책 제목, 표지 가져오기*/
	@Override
	public NormalBook getNorBookStatus(String bookNo) {
		NormalBook nBook = bStore.selectOneNorBookStatus(session,bookNo);
		return nBook;
	}


	
	
	

}
