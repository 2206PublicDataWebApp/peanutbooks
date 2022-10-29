package com.books.peanut.book.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookSeries;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.Star;
import com.books.peanut.book.domain.WriterProfile;
import com.books.peanut.book.store.BookStore;

@Repository
public class BookStoreLogic implements BookStore{

	/**
	 * 작가 프로필 등록
	 */
	@Override
	public int insertProfile(WriterProfile wrtiePro, SqlSessionTemplate session) {
		int result = session.insert("wirterMapper.insertProfile",wrtiePro);
		return result;
	}
	/**
	 * 작가 프로필 불러오기
	 */
	@Override
	public WriterProfile selectProfile(SqlSessionTemplate session, String memberId) {
		WriterProfile oneProfile = session.selectOne("wirterMapper.selectOneMember",memberId);
		return oneProfile;
	}

	/***
	 * 작가 프로필 수정
	 */
	@Override
	public int updateProfile(SqlSessionTemplate session, WriterProfile wrtiePro) {
		int result = session.update("wirterMapper.updateProfile",wrtiePro);
		return result;

	}
	
	/**
	 * 피넛 오리지널 메인 도서 등록
	 */
	@Override
	public int insertOribook(SqlSessionTemplate session, OriginBook oBook) {
		int result = session.insert("wirterMapper.insertBook",oBook);
		return result;
	}
	
	/***
	 * 태그등록
	 */
	@Override
	public int insertTag(SqlSessionTemplate session, HashTag hTag) {
		int result = session.insert("wirterMapper.insertTag",hTag);
		return result;
	}
	
	/**
	 * 피넛 오리지널 시리즈 등록(1화)
	 */
	@Override
	public int insertOriSeries(SqlSessionTemplate session, OriginBookSeries oSeries) {
		int result = session.insert("wirterMapper.insertSeries1",oSeries);
		result += session.insert("wirterMapper.insertPermission1",oSeries);
		return result;
	}
	
	/**
	 * 사용자가 쓴 모든 오리지널 시리즈 가져오기
	 */
	@Override
	public List<OriginBookSeries> selectAllOriSeries(int i, int limit,String memberId, SqlSessionTemplate session) {
		
		int offset = (i-1)*limit;
		RowBounds rowBounds= new RowBounds(offset,limit);
		
		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectOneMemberSeriese", memberId, rowBounds);
		return osList;
	}
	
	/**
	 * 책 번호로 책 제목 가져오기(피넛 오리지널)
	 */
	@Override
	public String selectBookTitle(String bookNo, SqlSessionTemplate session) {
		String bookTitle = session.selectOne("wirterMapper.selectBookTitle",bookNo);
		return bookTitle;
	}
	
	/***
	 * 오리지널 시리즈 책 정보 가져오기
	 */
	@Override
	public OriginBook selectOneBook(SqlSessionTemplate session, String bookNo) {
		OriginBook oBook = session.selectOne("wirterMapper.selectOneBook",bookNo);
		return oBook;
	}
	
	/**
	 * 피넛 오리지널 하나의 모든 시리즈 제목 가져오기*/
	@Override
	public List<OriginBookSeries> selectSeriesTitle(SqlSessionTemplate session, String bookNo) {
		List<OriginBookSeries> obList = session.selectList("wirterMapper.selecSeriesTitle",bookNo);
		return obList;
	}
	
	/**도서의 모든 태그 가져오기*/
	@Override
	public HashTag selectOneBookTag(String bookNo, String category,SqlSessionTemplate session) {
		HashTag tag = new HashTag();
		tag.setBookNo(bookNo);
		tag.setCategory(category);
		HashTag hTag =  session.selectOne("wirterMapper.selectOneBookTag", tag);
		return hTag;
	}
	
	/**회원 한명 닉네임 가져오기*/
	@Override
	public String selectOneMemberNick(SqlSessionTemplate session, String memberId) {
		String mNick = session.selectOne("wirterMapper.oneMemberNick",memberId);
		return mNick;
	}
	
	/**한명의 별점가져오기*/
	@Override
	public Star selectOneBookStar(SqlSessionTemplate session, Star starOne) {
		Star star = session.selectOne("bookReplyMapper.selectOneStar",starOne);
		return star;
	}
	
	/**회원이 쓴 피넛 오리지널의 갯수 파악*/
	@Override
	public int selectAllMemberOriSeries(SqlSessionTemplate session, String memberId) {
		int count = session.selectOne("wirterMapper.selectAllMemberOriSeries",memberId);
		return count;
	}
	
	/**작가의 모든책 가져오기*/
	@Override
	public List<OriginBook> selectAllWritebookTitle(SqlSessionTemplate session ,String memberId) {
		List<OriginBook> obList = session.selectList("wirterMapper.selectAllWriterBookTitle",memberId);
		return obList;
	}
	
	/**피넛 오리지널 도서 1개의 모든 시리즈 가져오기*/
	@Override
	public List<OriginBookSeries> selectAlloriBookSeries(SqlSessionTemplate session, String bookNo) {
		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectAllOriBookSeries",bookNo);
		return osList;
	}
	
	
	/**유료화 여부 체크*/
	@Override
	public String selectOneCheckPaid(SqlSessionTemplate session, int seriesNo, int bookNo) {
		OriginBookSeries obSeries = new OriginBookSeries();
		obSeries.setBookNo(bookNo+"");
		obSeries.setSeriesNo(seriesNo);
		String paidCheck = session.selectOne("wirterMapper.chekedpayOneSeries",obSeries);
		return paidCheck;
	}
	
	/**피넛 오리지널 시리즈 한편 가져오기*/
	@Override
	public OriginBookSeries selectOneSeries(SqlSessionTemplate session, int seriesNo, int bookNo) {
		OriginBookSeries obSeries = new OriginBookSeries();
		obSeries.setBookNo(bookNo+"");
		obSeries.setSeriesNo(seriesNo);
		OriginBookSeries oneObs = session.selectOne("wirterMapper.selectOneObSeries",obSeries);
		return oneObs;
	}
	
	/**작성자 맞는지 체크하기*/
	@Override
	public int selectcheckWirter(SqlSessionTemplate session, int bookNo, String memberId) {
		OriginBook oBook = new OriginBook();
		oBook.setMemberId(memberId);
		oBook.setBookNo(bookNo+"");
		int result = session.selectOne("wirterMapper.selectCheckWriter",oBook);
		return result;
	}
	
	/**피넛 오리지널 다음 시리즈 등록*/
	@Override
	public int insertOriSeriesNext(SqlSessionTemplate session, OriginBookSeries obSeries) {
		int result = session.insert("wirterMapper.insertNextOseries",obSeries);
		result += session.insert("wirterMapper.insertPermission2",obSeries);
		return result;
		
	}
	
	/**사용자의 시리즈 구입여부 체크*/
	@Override
	public int selectOnebokkPurchase(SqlSessionTemplate session, String memberId, int seriesNo, int bookNo) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("seriesNo", seriesNo+"");
		hMap.put("bookNo", bookNo+"");
		
		int result = session.selectOne("wirterMapper.selectOnebokkPurchase",hMap);
		return result;
	}
	
	/**구독여부 체크*/
	@Override
	public int selectCheckSubscribe(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("wirterMapper.selectCheckSubscribe",memberId);
		return result;
	}
	
	/**피넛갯수 확인하기*/
	@Override
	public int selectCheckPoint(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("wirterMapper.selectCheckPoint",memberId);
		return result;
	}
	
	/**시리즈 하나 구입하기*/
	@Override
	public int updatebuyOneSeries(SqlSessionTemplate session, int seriesNo, int bookNo, String memberId, String bookTitle) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("seriesNo", seriesNo+"");
		hMap.put("bookNo", bookNo+"");
		hMap.put("bookTitle", bookTitle);
		int result = session.update("wirterMapper.UsePeanutOne",hMap);
		result += session.insert("wirterMapper.insertBuyOneSeries",hMap);
		return result;
	}
	
	/**피넛 오리지널 시리즈 수정*/
	@Override
	public int updateOriSeries(SqlSessionTemplate session, OriginBookSeries obSeries) {
		int result = session.insert("wirterMapper.updateOriSeries",obSeries);
		result += session.insert("wirterMapper.permissionModify",obSeries);
		return result;
	}
	
	/**모든 일반 도서 시리즈의 갯수 파악*/
	@Override
	public int countAllnorBook(SqlSessionTemplate session) {
		int result = session.selectOne("adminWirteMapper.allNorBookCount");
		return result;
	}
	
	/**모든 일반도서 시리즈 가져오기*/
	@Override
	public List<NormalBookSeries> selectAllNorSeries(SqlSessionTemplate session) {
		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.allNorBook");
		return nsList;
	}
	
	/**일반도서 제목 가져오기*/
	@Override
	public String selectNorbookTitle(SqlSessionTemplate session, String bookNo) {
		String nTitle = session.selectOne("adminWirteMapper.selectNorBookTitle",bookNo);
		return nTitle;
	}
	
	/**일반도서 등록하기*/
	@Override
	public int insertNorBook(SqlSessionTemplate session, NormalBook nBook) {
		int result = session.insert("adminWirteMapper.insertOneNorBook",nBook);
		return result;
	}
	
	/**일반도서 시리즈 1화 등록하기*/
	@Override
	public int insertNSeriesBook(SqlSessionTemplate session, NormalBookSeries nSeries) {
		int result = session.insert("adminWirteMapper.insertNSeriesBook",nSeries);
		return result;

	}
	
	/**일반도서 태그 등록하기*/
	@Override
	public int insertNBTag(SqlSessionTemplate session, HashTag hTag) {
		int result = session.insert("adminWirteMapper.insertNBTag",hTag);
		return result;
	}
	
	/**일반도서 열람하기*/
	@Override
	public NormalBook selectOneNorBook(SqlSessionTemplate session, String bookNo) {
		NormalBook nBook = session.selectOne("adminWirteMapper.selectOneNorBook",bookNo);
		return nBook;
	}
	
	/**책 번호에 해당하는 모든 일반도서 시리즈의 특정 정보 가져오기*/
	@Override
	public List<NormalBookSeries> selectOneNorSeriesTitle(SqlSessionTemplate session, String bookNo) {
		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.selectOneNorSeriesTitle",bookNo);
		return nsList;
	}
	
	/**일반도서 작가의 모든 도서 제목 가져오기*/
	@Override
	public List<NormalBook> selectNorWriterbTitle(SqlSessionTemplate session, String writer) {
		List<NormalBook> nbList = session.selectList("adminWirteMapper.selectAllNorWriterTitle",writer);
		return nbList;
	}
	
	/**일반도서 한권에 모든 시리즈 가져오기*/
	@Override
	public List<NormalBookSeries> selectAllNorBookSeries(SqlSessionTemplate session, String bookNo) {
		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.selectAllNorBookSeries",bookNo);
		return nsList;
	}
	
	/**일반도서 시리즈 1개 가져오기*/
	@Override
	public NormalBookSeries selectOneNorSeries(SqlSessionTemplate session, int bookNo, int seriesNo) {
		NormalBookSeries nSeriesOne = new NormalBookSeries();
		nSeriesOne.setBookNo(bookNo+"");
		nSeriesOne.setSeriesNo(seriesNo);
		
		NormalBookSeries nSeries = session.selectOne("adminWirteMapper.selectOneNorSeries",nSeriesOne);
		return nSeries;
	}
	
	/**일반도서 다음화 등록*/
	@Override
	public int insertNorSeriesNext(SqlSessionTemplate session, NormalBookSeries nSeries) {
		int result = session.insert("adminWirteMapper.insertNextNorSeries",nSeries);
		return result;
	}
	
	/**도서의 언어여부 확인하기*/
	@Override
	public String selectBookLanguage(SqlSessionTemplate session, String bookNo) {
		String lang = session.selectOne("adminWirteMapper.selectBookLanguage",bookNo);
		return lang;
	}
	
	/**
	 * 일반도서 시리즈 수정
	 */
	@Override
	public int updateNorBookSeries(SqlSessionTemplate session, NormalBookSeries nbSeries) {
		int result = session.update("adminWirteMapper.updateNorbookSeries",nbSeries);
		return result;
	}
	
	/**일반도서 한개의 모든 시리즈 번호 가지고 오기*/
	@Override
	public List<NormalBookSeries> selectOneNorBookSeriesNo(SqlSessionTemplate session, int bookNo) {
		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.selectOneNorBookSeriesNo",bookNo);
		return nsList;
	}
	
	/**피넛 오리지널 시리즈 1개 삭제*/
	@Override
	public int updateOriSeriesRemove(SqlSessionTemplate session, String bookNo, Integer seriesNo) {
		
		OriginBookSeries oSeries = new OriginBookSeries();
		oSeries.setBookNo(bookNo);
		oSeries.setSeriesNo(seriesNo);
		
		int result = session.update("wirterMapper.updateOriSeriesRemove",oSeries);
		return result;
	}
	
	/**피넛 오리지널 도서 삭제*/
	@Override
	public int updateOriRemove(SqlSessionTemplate session, String bookNo) {
		int result = session.update("wirterMapper.updateOriRemove",bookNo);
		result += session.update("wirterMapper.updateAllOriSeriesRemove",bookNo);
		return result;
	}
	
	/**일반도서 시리즈 하나 삭제*/
	@Override
	public int deleteNorBookSeries(SqlSessionTemplate session, String bookNo, Integer seriesNo) {
		NormalBookSeries nSeries = new NormalBookSeries();
		nSeries.setBookNo(bookNo);
		nSeries.setSeriesNo(seriesNo);
		int result = session.delete("adminWirteMapper.deleteOneNorSeries",nSeries);
		return result;
	}
	
	/**일반도서 하나 삭제*/
	@Override
	public int updateNorRemove(SqlSessionTemplate session, int bookNo) {
		int result = session.update("adminWirteMapper.updateNorRemove",bookNo);
		result += session.delete("adminWirteMapper.updateAllNorSeriesRemove",bookNo);
		return result;
	}
	
	/**피넛 오리지널 한편에 모든 삭제되지 않고 허가된 시리즈 번호 가져오기*/
	@Override
	public List<OriginBookSeries> selectOneOriBookSeriesNo(SqlSessionTemplate session, int bookNo) {
		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectOneOriBookSeriesNo",bookNo);
		return osList;
	}
	
	/**피넛 오리지널 한편에 모든 시리즈 번호 가져오기*/

	@Override
	public List<OriginBookSeries> selectOneOriBookAllSeriesNo(SqlSessionTemplate session, int bookNo) {
		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectOneOriBookAllSeriesNo",bookNo);
		return osList;
	}
	
	/**내 서재 등록여부 확인하기*/
	@Override
	public int selectMybookMember(SqlSessionTemplate session, Library library) {
		int result = session.selectOne("librarymapper.selectMybookMember",library);
		return result;
	}
	
	/**내 서재 등록*/
	@Override
	public int insertMybook(SqlSessionTemplate session, Library library) {
		int result = session.insert("librarymapper.insertMybook",library);
		return result;
	}
	
	/**내 서재 삭제*/
	@Override
	public int deleteMybook(SqlSessionTemplate session, Library library) {
		int result = session.delete("librarymapper.deleteMybook",library);
		return result;
	}
	
	/**내 서재 불러오기*/
	@Override
	public List<Library> selectOneMemberLibrary(SqlSessionTemplate session, String memberId) {
		List<Library> lList = session.selectList("librarymapper.selectOneMemberLibrary",memberId);
		return lList;
	}
	
	/**피넛 오리지널 삭제되지 않고 승인된 책 한권의 제목, 표지 가져오기*/
	@Override
	public OriginBook selectOneOriBookStatus(SqlSessionTemplate session, String bookNo) {
		OriginBook oBook = session.selectOne("wirterMapper.selectOneBookStatus", bookNo);
		return oBook;
	}
	
	/**일반 삭제되지 않고 승인된 책 한권의 제목, 표지 가져오기*/
	@Override
	public NormalBook selectOneNorBookStatus(SqlSessionTemplate session, String bookNo) {
		NormalBook nBook = session.selectOne("adminWirteMapper.selectOneBookStatus", bookNo);
		return nBook;
	}

	

}
