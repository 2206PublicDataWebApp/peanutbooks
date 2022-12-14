package com.books.peanut.book.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.books.peanut.book.service.BookService;
import com.books.peanut.book.store.BookStore;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.PeanutPoint;

import oracle.net.aso.s;

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
	public List<NormalBookSeries> allAdminBooks(int i, int boardLimit) {
		List<NormalBookSeries> nsList = bStore.selectAllNorSeries(session,i,boardLimit);
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
	public List<Library> getOneMemberLibrary(String memberId, String category, String step, String searchValue, int page, int limit) {
		List<Library> lList = bStore.selectOneMemberLibrary(session, memberId,category,step,searchValue,page,limit);
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

	/**내 서재 피넛 오리지널 불러오기*/
	@Override
	public List<Library> getOneMemberOriLibrary(String memberId) {
		 List<Library> lList= bStore.selectOneMemberOriLibrary(session, memberId);
			return lList;
		}

	/**내 서재 일반도서 불러오기*/
	@Override
	public List<Library> getOneMemberNorLibrary(String memberId) {
		List<Library> lList= bStore.selectOneMemberNorLibrary(session, memberId);
		return lList;
	}

	/**내 구입도서 가져오기*/
	@Override
	public List<peanutPaidSeries> getOneMemberPaid(String memberId, String step, String searchValue, int currentPage, int Limit) {
		 List<peanutPaidSeries> pList = bStore.selectAllOneMemberPaid(session, memberId,step,searchValue,currentPage,Limit);
		return pList;
	}

	/**내가 구입한 모든 시리즈 허가되고 삭제안된*/
	@Override
	public OriginBookSeries getOneBookSeriesStatus(String bookNo, String seriesNo) {
		OriginBookSeries oSeries = bStore.selectOneBookSeriesStatus(session, bookNo, seriesNo);
		return oSeries;
	}

	/**페이징용 내서재 총 갯수*/
	@Override
	public int countOneMemberLibrary(String memberId, String category, String step, String searchValue) {
		int result = bStore.selectCountOneMemberLibrary(session, memberId,category,step,searchValue);
		return result;
	}

	/**페이징용 내 구입시리즈 총 갯수 가져오기*/
	@Override
	public int getOneMemberPaidCount(String memberId, String step, String searchValue) {
		int result = bStore.selectOneBookSeriesStatusCount(session, memberId,step,searchValue);
		return result;
	}

	/**피넛 오리지널 검색숫자*/
	@Override
	public int OriBookSearchValueCount(String tag, String step, String searchValue, String category) {
		int result = bStore.selectOriBookSearchCount(session,tag,step,searchValue,category);
		return result;
	}

	/**일반도서 검색숫자*/
	@Override
	public int NorBookSearchValueCount(String tag, String step, String searchValue, String category) {
		int result = bStore.selectNorBookSearchCount(session,tag,step,searchValue, category);
		return result;
	}

	/**피넛 오리지널 도서 검색*/
	@Override
	public List<OriginBook> allBookSearchValue(String tag, String step, String searchValue, Integer page,
			 int limit, String category) {
		List<OriginBook> oList =bStore.selectBookSearchValue(session, tag,step,searchValue,page,limit,category);
		return oList;
	}

	/**일반도서 검색*/
	@Override
	public List<NormalBook> allBookSearchValueNor(String tag, String step, String searchValue, Integer page,
		 int limit, String category) {
		List<NormalBook> nList =bStore.selectBookSearchValueNor(session, tag,step,searchValue,page,limit,category);
		return nList;	}

	/**조회수 높은 동화그림*/
	@Override
	public String getNorImgName() {
		String img = bStore.selectNorImgName(session);
		return img;
	}

	/**조회수 높은 시 그림*/
	@Override
	public String getNorImgName2() {
		String img = bStore.selectNorImgName2(session);
		return img;
	}
	/**조회수 높은 소설그림*/
	@Override
	public String getOriImgName() {
		String img = bStore.selectOriImgName(session);
		return img;
	}
	/**조회수 높은 동화그림*/
	@Override
	public String getOriImgName2() {
		String img = bStore.selectOriImgName1(session);
		return img;
	}

	/**피넛 오리지널 탑 6개 카테고리별 가져오기*/
	@Override
	public List<OriginBook> getRankOriBook(String category) {
		List<OriginBook> oList = bStore.selectRankOriBook(session,category);
		return oList;
	}
	
	/**피넛 오리지널 카테고리별 총 갯수*/
	@Override
	public int countAllOriginCategory(String category) {
		int result = bStore.selectCountAllOriginCategory(session,category);
		return result;
	}

	/**피넛 오리지널 카테고리별 모든도서*/
	@Override
	public List<OriginBook> getAllOriginCategory(String category, int currentPage, int bookLimit, String step) {
		List<OriginBook> oList = bStore.selectAllOriginCategory(session,category,currentPage,bookLimit,step);
		return oList;
	}

	/**일반도서 카테고리별 총 갯수*/
	@Override
	public int countAllNormalCategory(String category) {
		int result = bStore.selectCountAllNormalCategory(session,category);
		return result;
	}

	/**일반도서 카테고리별 모든도서*/
	@Override
	public List<NormalBook> getAllNormalCategory(String category, int currentPage, int bookLimit, String step) {
		List<NormalBook> nList = bStore.selectAllNormalCategory(session,category,currentPage,bookLimit,step);
		return nList;
	}

	
	/**일반도서 탑 6개 카테고리별 가져오기*/
	@Override
	public List<NormalBook> getRankNorBook(String category) {
		List<NormalBook> nList = bStore.selectRankNorBook(session,category);
		return nList;
	}

	/**피넛 오리지널 시리즈 조회수 올리기*/
	@Override
	public int registViewCount(Member member, int seriesNo, int bookNo,String pCheck) {
		int viewCount = bStore.insertViewCount(session,member,seriesNo,bookNo,pCheck);
		return viewCount;
	}
	
	/**일반도서 조회수 추가하기*/
	@Override
	public int plusCountOne(String memberId, int seriesNo, int bookNo) {
		int viewCount = bStore.insertViewCount(session,memberId,seriesNo,bookNo);
		return viewCount;
	}
	
	
	
/**토탈 상위 도서 3권 가져오기*/
	@Override
	public List<NormalBook> getRankTopBook() {
	List<NormalBook> nList = bStore.selectRankTopBook(session);
		return nList;
	}

	/**가장 별점 많은 추리도서 가져오기*/
@Override
public NormalBook getTopScore(String string) {
	NormalBook nBook = bStore.selectTopScore(session,string);
	return nBook;
}

/**별점많은 카테고리 가져오기*/
	@Override
	public List<NormalBook> getTopScore4(String category) {
		List<NormalBook> nList = bStore.selectTop4(session,category);
		return nList;
	}

	/**별점 가장많은 오리지널 북 카테고리 1권*/
	@Override
	public OriginBook getTopScoreOri(String category) {
		OriginBook oBook = bStore.selectOneTop(session,category);
		return oBook;
	}
	
	/**별점 가장많은 오리지널 북 카테고리 4권*/
	@Override
	public List<OriginBook> getTopScore4Ori(String category) {
		List<OriginBook> oList = bStore.selectTop4Ori(session, category);
		return oList;
	}

	/**오리지널 도서 인포 수정*/
	@Override
	public int modifyOriBookInfo(OriginBook oBook) {
		int result = bStore.updateOriBookInfo(session,oBook);
		return result;
	}

	/**해시태그 수정*/
	@Override
	public int modifyOriBookTag(HashTag hTag) {
		int result = bStore.updateOriHashTag(session,hTag);
		return result;
	}

	/**일반도서 인포 수정하기*/
	@Override
	public int modifyNorBooksInfo(NormalBook nBook) {
		int result = bStore.updateNorBookInfo(session,nBook);
		return result;
	}

	/**수정테이블의 시리즈 한편가져오기*/
	@Override
	public OriginBookSeries getOneModifySeries(int modifyNo) {
		OriginBookSeries oSeries = bStore.selectOneModifySeries(session,modifyNo);
		return oSeries;
	}

	/**피넛 오리지널 수정 승인*/
	@Override
	public int modifyOriSeriesProve(OriginBookSeries oModifyS, int modifyNo) {
		int result = bStore.updateModifyOne(session,oModifyS,modifyNo);
		return result;
	}

	
	/**시리즈 하나 삭제하기*/
	@Override
	public int removeOneORiBookSeries(Integer seriesNo, Integer bookNo) {
		int result = bStore.deleteOneSeries(session,seriesNo,bookNo);
		return result;
	}

	/**수정 테이블에 존재여부 확인*/
	@Override
	public int modifyCheck(OriginBookSeries obSeries) {
		int result = bStore.SelectmodifyCheck(session,obSeries);
		return result;
	}

	/**책 영구삭제*/
	@Override
	public int removeOriBookMember(String bookNo) {
		int result = bStore.deleteOneObook(session,bookNo);
		return result;
	}







	
	
	

}
