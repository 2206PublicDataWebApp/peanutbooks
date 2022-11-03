package com.books.peanut.book.store.logic;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.books.peanut.book.domain.peanutPaidSeries;
import com.books.peanut.book.store.BookStore;
import com.books.peanut.member.domain.Member;

@Repository
public class BookStoreLogic implements BookStore {

	/**
	 * 작가 프로필 등록
	 */
	@Override
	public int insertProfile(WriterProfile wrtiePro, SqlSessionTemplate session) {
		int result = session.insert("wirterMapper.insertProfile", wrtiePro);
		return result;
	}

	/**
	 * 작가 프로필 불러오기
	 */
	@Override
	public WriterProfile selectProfile(SqlSessionTemplate session, String memberId) {
		WriterProfile oneProfile = session.selectOne("wirterMapper.selectOneMember", memberId);
		return oneProfile;
	}

	/***
	 * 작가 프로필 수정
	 */
	@Override
	public int updateProfile(SqlSessionTemplate session, WriterProfile wrtiePro) {
		int result = session.update("wirterMapper.updateProfile", wrtiePro);
		return result;

	}

	/**
	 * 피넛 오리지널 메인 도서 등록
	 */
	@Override
	public int insertOribook(SqlSessionTemplate session, OriginBook oBook) {
		int result = session.insert("wirterMapper.insertBook", oBook);
		return result;
	}

	/***
	 * 태그등록
	 */
	@Override
	public int insertTag(SqlSessionTemplate session, HashTag hTag) {
		int result = session.insert("wirterMapper.insertTag", hTag);
		return result;
	}

	/**
	 * 피넛 오리지널 시리즈 등록(1화)
	 */
	@Override
	public int insertOriSeries(SqlSessionTemplate session, OriginBookSeries oSeries) {
		int result = session.insert("wirterMapper.insertSeries1", oSeries);
		result += session.insert("wirterMapper.insertPermission1", oSeries);
		return result;
	}

	/**
	 * 사용자가 쓴 모든 오리지널 시리즈 가져오기
	 */
	@Override
	public List<OriginBookSeries> selectAllOriSeries(int i, int limit, String memberId, SqlSessionTemplate session) {

		int offset = (i - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectOneMemberSeriese", memberId, rowBounds);
		return osList;
	}

	/**
	 * 책 번호로 책 제목 가져오기(피넛 오리지널)
	 */
	@Override
	public String selectBookTitle(String bookNo, SqlSessionTemplate session) {
		String bookTitle = session.selectOne("wirterMapper.selectBookTitle", bookNo);
		return bookTitle;
	}

	/***
	 * 오리지널 시리즈 책 정보 가져오기
	 */
	@Override
	public OriginBook selectOneBook(SqlSessionTemplate session, String bookNo) {
		OriginBook oBook = session.selectOne("wirterMapper.selectOneBook", bookNo);
		return oBook;
	}

	/**
	 * 피넛 오리지널 하나의 모든 시리즈 제목 가져오기
	 */
	@Override
	public List<OriginBookSeries> selectSeriesTitle(SqlSessionTemplate session, String bookNo) {
		List<OriginBookSeries> obList = session.selectList("wirterMapper.selecSeriesTitle", bookNo);
		return obList;
	}

	/** 도서의 모든 태그 가져오기 */
	@Override
	public HashTag selectOneBookTag(String bookNo, String category, SqlSessionTemplate session) {
		HashTag tag = new HashTag();
		tag.setBookNo(bookNo);
		tag.setCategory(category);
		HashTag hTag = session.selectOne("wirterMapper.selectOneBookTag", tag);
		return hTag;
	}

	/** 회원 한명 닉네임 가져오기 */
	@Override
	public String selectOneMemberNick(SqlSessionTemplate session, String memberId) {
		String mNick = session.selectOne("wirterMapper.oneMemberNick", memberId);
		return mNick;
	}

	/** 한명의 별점가져오기 */
	@Override
	public Star selectOneBookStar(SqlSessionTemplate session, Star starOne) {
		Star star = session.selectOne("bookReplyMapper.selectOneStar", starOne);
		return star;
	}

	/** 회원이 쓴 피넛 오리지널의 갯수 파악 */
	@Override
	public int selectAllMemberOriSeries(SqlSessionTemplate session, String memberId) {
		int count = session.selectOne("wirterMapper.selectAllMemberOriSeries", memberId);
		return count;
	}

	/** 작가의 모든책 가져오기 */
	@Override
	public List<OriginBook> selectAllWritebookTitle(SqlSessionTemplate session, String memberId) {
		List<OriginBook> obList = session.selectList("wirterMapper.selectAllWriterBookTitle", memberId);
		return obList;
	}

	/** 피넛 오리지널 도서 1개의 모든 시리즈 가져오기 */
	@Override
	public List<OriginBookSeries> selectAlloriBookSeries(SqlSessionTemplate session, String bookNo) {
		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectAllOriBookSeries", bookNo);
		return osList;
	}

	/** 유료화 여부 체크 */
	@Override
	public String selectOneCheckPaid(SqlSessionTemplate session, int seriesNo, int bookNo) {
		OriginBookSeries obSeries = new OriginBookSeries();
		obSeries.setBookNo(bookNo + "");
		obSeries.setSeriesNo(seriesNo);
		String paidCheck = session.selectOne("wirterMapper.chekedpayOneSeries", obSeries);
		return paidCheck;
	}

	/** 피넛 오리지널 시리즈 한편 가져오기 */
	@Override
	public OriginBookSeries selectOneSeries(SqlSessionTemplate session, int seriesNo, int bookNo) {
		OriginBookSeries obSeries = new OriginBookSeries();
		obSeries.setBookNo(bookNo + "");
		obSeries.setSeriesNo(seriesNo);
		OriginBookSeries oneObs = session.selectOne("wirterMapper.selectOneObSeries", obSeries);
		return oneObs;
	}

	/** 작성자 맞는지 체크하기 */
	@Override
	public int selectcheckWirter(SqlSessionTemplate session, int bookNo, String memberId) {
		OriginBook oBook = new OriginBook();
		oBook.setMemberId(memberId);
		oBook.setBookNo(bookNo + "");
		int result = session.selectOne("wirterMapper.selectCheckWriter", oBook);
		return result;
	}

	/** 피넛 오리지널 다음 시리즈 등록 */
	@Override
	public int insertOriSeriesNext(SqlSessionTemplate session, OriginBookSeries obSeries) {
		int result = session.insert("wirterMapper.insertNextOseries", obSeries);
		result += session.insert("wirterMapper.insertPermission2", obSeries);
		return result;

	}

	/** 사용자의 시리즈 구입여부 체크 */
	@Override
	public int selectOnebokkPurchase(SqlSessionTemplate session, String memberId, int seriesNo, int bookNo) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("seriesNo", seriesNo + "");
		hMap.put("bookNo", bookNo + "");

		int result = session.selectOne("wirterMapper.selectOnebokkPurchase", hMap);
		return result;
	}

	/** 구독여부 체크 */
	@Override
	public int selectCheckSubscribe(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("wirterMapper.selectCheckSubscribe", memberId);
		return result;
	}

	/** 피넛갯수 확인하기 */
	@Override
	public int selectCheckPoint(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("wirterMapper.selectCheckPoint", memberId);
		return result;
	}

	/** 시리즈 하나 구입하기 */
	@Override
	public int updatebuyOneSeries(SqlSessionTemplate session, int seriesNo, int bookNo, String memberId,
			String bookTitle) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("seriesNo", seriesNo + "");
		hMap.put("bookNo", bookNo + "");
		hMap.put("bookTitle", bookTitle);
		int result = session.update("wirterMapper.UsePeanutOne", hMap); //멤버 테이블에서 땅콩 사용하기
		result += session.insert("wirterMapper.insertBuyOneSeries", hMap); //땅콩테이블에서 도서 구입내역 넣기
		result += session.insert("wirterMapper.insertOenOriSeries", hMap); //조회수 테이블에 조회내역넣기
		result += session.update("wirterMapper.UpdateOneOriSeries", hMap);//시리즈 테이블에 조회 1넣기
		result += session.update("wirterMapper.UpdateOneOriBook", hMap);// 책 테이블에 조회1 넣기
		result += session.update("wirterMapper.UpdateOnePaidOriSeries", hMap); //시리즈 테이블의 유료조회 1 넣기
		return result;
	}

	/** 피넛 오리지널 시리즈 수정 */
	@Override
	public int updateOriSeries(SqlSessionTemplate session, OriginBookSeries obSeries) {
		int result = session.insert("wirterMapper.updateOriSeries", obSeries);
		result += session.insert("wirterMapper.permissionModify", obSeries);
		return result;
	}

	/** 모든 일반 도서 시리즈의 갯수 파악 */
	@Override
	public int countAllnorBook(SqlSessionTemplate session) {
		int result = session.selectOne("adminWirteMapper.allNorBookCount");
		return result;
	}

	/** 모든 일반도서 시리즈 가져오기 */
	@Override
	public List<NormalBookSeries> selectAllNorSeries(SqlSessionTemplate session, int i, int limit) {
		int offset = (i - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.allNorBook", null, rowBounds);
		return nsList;
	}

	/** 일반도서 제목 가져오기 */
	@Override
	public String selectNorbookTitle(SqlSessionTemplate session, String bookNo) {
		String nTitle = session.selectOne("adminWirteMapper.selectNorBookTitle", bookNo);
		return nTitle;
	}

	/** 일반도서 등록하기 */
	@Override
	public int insertNorBook(SqlSessionTemplate session, NormalBook nBook) {
		int result = session.insert("adminWirteMapper.insertOneNorBook", nBook);
		return result;
	}

	/** 일반도서 시리즈 1화 등록하기 */
	@Override
	public int insertNSeriesBook(SqlSessionTemplate session, NormalBookSeries nSeries) {
		int result = session.insert("adminWirteMapper.insertNSeriesBook", nSeries);
		return result;

	}

	/** 일반도서 태그 등록하기 */
	@Override
	public int insertNBTag(SqlSessionTemplate session, HashTag hTag) {
		int result = session.insert("adminWirteMapper.insertNBTag", hTag);
		return result;
	}

	/** 일반도서 열람하기 */
	@Override
	public NormalBook selectOneNorBook(SqlSessionTemplate session, String bookNo) {
		NormalBook nBook = session.selectOne("adminWirteMapper.selectOneNorBook", bookNo);
		return nBook;
	}

	/** 책 번호에 해당하는 모든 일반도서 시리즈의 특정 정보 가져오기 */
	@Override
	public List<NormalBookSeries> selectOneNorSeriesTitle(SqlSessionTemplate session, String bookNo) {
		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.selectOneNorSeriesTitle", bookNo);
		return nsList;
	}

	/** 일반도서 작가의 모든 도서 제목 가져오기 */
	@Override
	public List<NormalBook> selectNorWriterbTitle(SqlSessionTemplate session, String writer) {
		List<NormalBook> nbList = session.selectList("adminWirteMapper.selectAllNorWriterTitle", writer);
		return nbList;
	}

	/** 일반도서 한권에 모든 시리즈 가져오기 */
	@Override
	public List<NormalBookSeries> selectAllNorBookSeries(SqlSessionTemplate session, String bookNo) {
		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.selectAllNorBookSeries", bookNo);
		return nsList;
	}

	/** 일반도서 시리즈 1개 가져오기 */
	@Override
	public NormalBookSeries selectOneNorSeries(SqlSessionTemplate session, int bookNo, int seriesNo) {
		NormalBookSeries nSeriesOne = new NormalBookSeries();
		nSeriesOne.setBookNo(bookNo + "");
		nSeriesOne.setSeriesNo(seriesNo);

		NormalBookSeries nSeries = session.selectOne("adminWirteMapper.selectOneNorSeries", nSeriesOne);
		return nSeries;
	}

	/** 일반도서 다음화 등록 */
	@Override
	public int insertNorSeriesNext(SqlSessionTemplate session, NormalBookSeries nSeries) {
		int result = session.insert("adminWirteMapper.insertNextNorSeries", nSeries);
		return result;
	}

	/** 도서의 언어여부 확인하기 */
	@Override
	public String selectBookLanguage(SqlSessionTemplate session, String bookNo) {
		String lang = session.selectOne("adminWirteMapper.selectBookLanguage", bookNo);
		return lang;
	}

	/**
	 * 일반도서 시리즈 수정
	 */
	@Override
	public int updateNorBookSeries(SqlSessionTemplate session, NormalBookSeries nbSeries) {
		int result = session.update("adminWirteMapper.updateNorbookSeries", nbSeries);
		return result;
	}

	/** 일반도서 한개의 모든 시리즈 번호 가지고 오기 */
	@Override
	public List<NormalBookSeries> selectOneNorBookSeriesNo(SqlSessionTemplate session, int bookNo) {
		List<NormalBookSeries> nsList = session.selectList("adminWirteMapper.selectOneNorBookSeriesNo", bookNo);
		return nsList;
	}

	/** 피넛 오리지널 시리즈 1개 삭제 */
	@Override
	public int updateOriSeriesRemove(SqlSessionTemplate session, String bookNo, Integer seriesNo) {

		OriginBookSeries oSeries = new OriginBookSeries();
		oSeries.setBookNo(bookNo);
		oSeries.setSeriesNo(seriesNo);

		int result = session.update("wirterMapper.updateOriSeriesRemove", oSeries);
		return result;
	}

	/** 피넛 오리지널 도서 삭제 */
	@Override
	public int updateOriRemove(SqlSessionTemplate session, String bookNo) {
		int result = session.update("wirterMapper.updateOriRemove", bookNo);
		result += session.update("wirterMapper.updateAllOriSeriesRemove", bookNo);
		return result;
	}

	/** 일반도서 시리즈 하나 삭제 */
	@Override
	public int deleteNorBookSeries(SqlSessionTemplate session, String bookNo, Integer seriesNo) {
		NormalBookSeries nSeries = new NormalBookSeries();
		nSeries.setBookNo(bookNo);
		nSeries.setSeriesNo(seriesNo);
		int result = session.delete("adminWirteMapper.deleteOneNorSeries", nSeries);
		return result;
	}

	/** 일반도서 하나 삭제 */
	@Override
	public int updateNorRemove(SqlSessionTemplate session, int bookNo) {
		int result = session.update("adminWirteMapper.updateNorRemove", bookNo);
		result += session.delete("adminWirteMapper.updateAllNorSeriesRemove", bookNo);
		return result;
	}

	/** 피넛 오리지널 한편에 모든 삭제되지 않고 허가된 시리즈 번호 가져오기 */
	@Override
	public List<OriginBookSeries> selectOneOriBookSeriesNo(SqlSessionTemplate session, int bookNo) {
		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectOneOriBookSeriesNo", bookNo);
		return osList;
	}

	/** 피넛 오리지널 한편에 모든 시리즈 번호 가져오기 */

	@Override
	public List<OriginBookSeries> selectOneOriBookAllSeriesNo(SqlSessionTemplate session, int bookNo) {
		List<OriginBookSeries> osList = session.selectList("wirterMapper.selectOneOriBookAllSeriesNo", bookNo);
		return osList;
	}

	/** 내 서재 등록여부 확인하기 */
	@Override
	public int selectMybookMember(SqlSessionTemplate session, Library library) {
		int result = session.selectOne("librarymapper.selectMybookMember", library);
		return result;
	}

	/** 내 서재 등록 */
	@Override
	public int insertMybook(SqlSessionTemplate session, Library library) {
		int result = session.insert("librarymapper.insertMybook", library);
		return result;
	}

	/** 내 서재 삭제 */
	@Override
	public int deleteMybook(SqlSessionTemplate session, Library library) {
		int result = session.delete("librarymapper.deleteMybook", library);
		return result;
	}

	/** 내 서재 불러오기 */
	@Override
	public List<Library> selectOneMemberLibrary(SqlSessionTemplate session, String memberId, String category,
			String step, String searchValue, int page, int limit) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("category", category);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);
		hMap.put("memberId", memberId);

		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<Library> lList = session.selectList("librarymapper.selectOneMemberLibrary", hMap, rowBounds);
		return lList;
	}

	/** 피넛 오리지널 삭제되지 않고 승인된 책 한권의 제목, 표지 가져오기 */
	@Override
	public OriginBook selectOneOriBookStatus(SqlSessionTemplate session, String bookNo) {
		OriginBook oBook = session.selectOne("wirterMapper.selectOneBookStatus", bookNo);
		return oBook;
	}

	/** 일반 삭제되지 않고 승인된 책 한권의 제목, 표지 가져오기 */
	@Override
	public NormalBook selectOneNorBookStatus(SqlSessionTemplate session, String bookNo) {
		NormalBook nBook = session.selectOne("adminWirteMapper.selectOneBookStatus", bookNo);
		return nBook;
	}

	/** 내 서재 피넛 오리지널 목록 가져오기 */
	@Override
	public List<Library> selectOneMemberOriLibrary(SqlSessionTemplate session, String memberId) {
		List<Library> lList = session.selectList("librarymapper.selectOneMemberOriLibrary", memberId);
		return lList;
	}

	/** 내 서재 일반도서 불러오기 */
	@Override
	public List<Library> selectOneMemberNorLibrary(SqlSessionTemplate session, String memberId) {
		List<Library> lList = session.selectList("librarymapper.selectOneMemberNorLibrary", memberId);
		return lList;
	}

	/** 구입한 도서 목록 가져오기 */
	@Override
	public List<peanutPaidSeries> selectAllOneMemberPaid(SqlSessionTemplate session, String memberId, String step,
			String searchValue, int currentPage, int limit) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);

		int offset = (currentPage - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);

		List<peanutPaidSeries> pList = session.selectList("librarymapper.selectOneMemberPaid", hMap, rowBounds);
		return pList;
	}

	/** 내 구입시리즈 삭제되지 않고 허가된 */
	@Override
	public OriginBookSeries selectOneBookSeriesStatus(SqlSessionTemplate session, String bookNo, String seriesNo) {
		OriginBookSeries oS = new OriginBookSeries();
		oS.setBookNo(bookNo);
		int sNo = Integer.parseInt(seriesNo);
		oS.setSeriesNo(sNo);

		OriginBookSeries oSeries = session.selectOne("wirterMapper.SelectOneBookSeriesStatus", oS);
		return oSeries;
	}

	/** 페이징용 내서재 총 갯수 */
	@Override
	public int selectCountOneMemberLibrary(SqlSessionTemplate session, String memberId, String category, String step,
			String searchValue) {

		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("category", category);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);
		hMap.put("memberId", memberId);

		int result = session.selectOne("librarymapper.selectCountOneMemberLibrary", hMap);
		return result;
	}

	/** 페이징용 내 구입시리즈 갯수 */
	@Override
	public int selectOneBookSeriesStatusCount(SqlSessionTemplate session, String memberId, String step,
			String searchValue) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);

		int result = session.selectOne("librarymapper.selectOneMemberPaidCount", hMap);
		return result;
	}

	/** 피넛 오리지널 검색숫자 */
	@Override
	public int selectOriBookSearchCount(SqlSessionTemplate session, String tag, String step, String searchValue,
			String category) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("tag", tag);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);
		hMap.put("category", category);

		int result = session.selectOne("wirterMapper.selectCountOriSearchValue", hMap);

		return result;
	}

	/** 일반도서 검색숫자 */
	@Override
	public int selectNorBookSearchCount(SqlSessionTemplate session, String tag, String step, String searchValue,
			String category) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("tag", tag);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);
		hMap.put("category", category);

		int result = session.selectOne("adminWirteMapper.selectCountNorSearchValue", hMap);

		return result;
	}

	/** 피넛 오리지널 검색 */
	@Override
	public List<OriginBook> selectBookSearchValue(SqlSessionTemplate session, String tag, String step,
			String searchValue, Integer page, int limit, String category) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("tag", tag);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);
		hMap.put("category", category);

		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<OriginBook> oList = session.selectList("wirterMapper.selectBookSearchValue", hMap, rowBounds);

		return oList;
	}

	/** 일반도서 검색 */
	@Override
	public List<NormalBook> selectBookSearchValueNor(SqlSessionTemplate session, String tag, String step,
			String searchValue, Integer page, int limit, String category) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("tag", tag);
		hMap.put("step", step);
		hMap.put("searchValue", searchValue);
		hMap.put("category", category);

		int offset = (page - 1) * limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<NormalBook> nList = session.selectList("adminWirteMapper.selectBookSearchValueNor", hMap, rowBounds);

		return nList;
	}

	/** 조회수 높은 동화 그림 */
	@Override
	public String selectNorImgName(SqlSessionTemplate session) {
		String img = session.selectOne("adminWirteMapper.selectOneNorimg");
		return img;
	}

	/** 조회수 높은 시 그림 */
	@Override
	public String selectNorImgName2(SqlSessionTemplate session) {
		String img = session.selectOne("adminWirteMapper.selectOneNorimg2");
		return img;
	}

	/** 조회수 높은 소설 그림 */
	@Override
	public String selectOriImgName(SqlSessionTemplate session) {
		String img = session.selectOne("wirterMapper.selectOneOriimg");
		return img;
	}

	/** 조회수 높은 동화 그림 */
	@Override
	public String selectOriImgName1(SqlSessionTemplate session) {
		String img = session.selectOne("wirterMapper.selectOneOriimg2");
		return img;
	}

	/** 피넛 오리지널 랭킹 6 카테고리 별로 가져오기 */
	@Override
	public List<OriginBook> selectRankOriBook(SqlSessionTemplate session, String category) {
		List<OriginBook> oList = session.selectList("wirterMapper.selectRankOriBook", category);
		return oList;
	}

	/** 피넛 오리지널 카테고리별 총 갯수 */
	@Override
	public int selectCountAllOriginCategory(SqlSessionTemplate session, String category) {
		int result = session.selectOne("wirterMapper.countAllOriginCategory", category);
		return result;
	}

	/** 피넛 오리지널 카테고리별 모든도서 */
	@Override
	public List<OriginBook> selectAllOriginCategory(SqlSessionTemplate session, String category, int currentPage,
			int bookLimit, String step) {

		int offset = (currentPage - 1) * bookLimit;
		RowBounds rowBounds = new RowBounds(offset, bookLimit);
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("category", category);
		hMap.put("step", step);

		List<OriginBook> oList = session.selectList("wirterMapper.allOriginCategory", hMap, rowBounds);
		return oList;
	}

	/** 일반도서 카테고리별 총 갯수 */
	@Override
	public int selectCountAllNormalCategory(SqlSessionTemplate session, String category) {
		int result = session.selectOne("adminWirteMapper.CountAllNormalCategory", category);
		return result;
	}

	/** 일반도서 카테고리별 모든도서 */
	@Override
	public List<NormalBook> selectAllNormalCategory(SqlSessionTemplate session, String category, int currentPage,
			int bookLimit, String step) {

		int offset = (currentPage - 1) * bookLimit;
		RowBounds rowBounds = new RowBounds(offset, bookLimit);
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("category", category);
		hMap.put("step", step);

		List<NormalBook> nList = session.selectList("adminWirteMapper.allNormalCategory", hMap, rowBounds);
		return nList;
	}

	/** 일반도서 랭킹 6 카테고리 별로 가져오기 */
	@Override
	public List<NormalBook> selectRankNorBook(SqlSessionTemplate session, String category) {
		List<NormalBook> nList = session.selectList("adminWirteMapper.selectRankNorBook", category);
		return nList;
	}

	/** 피넛 오리지널 조회수 입력하기 */
	@Override
	public int insertViewCount(SqlSessionTemplate session, Member member, int seriesNo, int bookNo, String pCheck) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("seriesNo", seriesNo + "");
		hMap.put("bookNo", bookNo + "");
		hMap.put("category", "origin");
		hMap.put("memberId", member.getMemberId());
			int result2 = 0;

		if (pCheck.equals("N")) { // 무료도서라면 무조건 조회수 추가
			result2 = session.selectOne("wirterMapper.selectOneFreeCountMember", hMap);//무료도서를 본적있는지 체크
			if (result2 == 0) {//본적없다면
				result2 += session.insert("wirterMapper.insertOenOriSeries", hMap);//카운터 테이블에 추가
				result2 += session.update("wirterMapper.UpdateOneOriSeries", hMap);//시리즈 테이블에 카운트 1추가
				result2 += session.update("wirterMapper.UpdateOneOriBook", hMap); //오리지널북 테이블에 카운트 1추가
			}

		} else {

			int result = session.selectOne("wirterMapper.selectMemberDateCount", member);// 구독 여부 확인
			if (result > 0) {
				Date date = session.selectOne("wirterMapper.selectMemberDate", member); // 현재 구독날짜 가져오기

				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy/MM/dd");
				String subsDate = transFormat.format(date);
				hMap.put("subsDate", subsDate);

				int result1 = session.selectOne("wirterMapper.selectOneCountMember", hMap); // 구독날짜로 조회수 추가 된적있는지 확인하기

				if (result1 == 0) {
					int result3 = session.insert("wirterMapper.insertCountOne", hMap); // 추가된적없으면 추가하기
					result3 += session.update("wirterMapper.UpdateOneOriSeries", hMap); // 시리즈 테이블에 조회수 추가
					result3 += session.update("wirterMapper.UpdateOneOriBook", hMap); // 피넛오리지널북 테이블에 조회수 추가

					
					result2 += session.update("wirterMapper.UpdateOnePaidOriSeries", hMap); //유료도서 카운트 추가
					
				}
			}
		}
		return result2;
	}

	/**일반도서 조회수추가하기*/
	@Override
	public int insertViewCount(SqlSessionTemplate session, String memberId, int seriesNo, int bookNo) {
		HashMap<String, String> hMap =new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("seriesNo", seriesNo+"");
		hMap.put("bookNo", bookNo+"");
		
		int result = session.selectOne("adminWirteMapper.selectOneCheckCount",hMap);
		if(result==0) {
			result += session.update("adminWirteMapper.updateBookCount",hMap);
			result += session.insert("adminWirteMapper.insertBookCount",hMap);
		}
		return result;
	}

	/**토탈 상위 도서 3권 가져오기*/
	@Override
	public List<NormalBook> selectRankTopBook(SqlSessionTemplate session) {
		List<NormalBook> nList = session.selectList("adminWirteMapper.selectTop3Book");
		return nList;
	}

	
	/**가장 별점많은 도서 가져오기*/
	@Override
	public NormalBook selectTopScore(SqlSessionTemplate session, String category) {
		NormalBook nBook = session.selectOne("adminWirteMapper.selectTopScore",category);
		return nBook;
	}

	
	/**별점많은 카테고리별 도서 가져오기*/
	@Override
	public List<NormalBook> selectTop4(SqlSessionTemplate session, String category) {
		List<NormalBook> nList = session.selectList("adminWirteMapper.selectTop4",category);
		return nList;
	}

	/**별점많은 카테고리 오리지널 도서 1개*/
	@Override
	public OriginBook selectOneTop(SqlSessionTemplate session, String category) {
		OriginBook oBook = session.selectOne("wirterMapper.selectOneTop",category);
		return oBook;
	}

	/**별점많은 카테고리 오리지널 도서 4개*/
	@Override
	public List<OriginBook> selectTop4Ori(SqlSessionTemplate session, String category) {
		 List<OriginBook> oList = session.selectList("wirterMapper.selectTop4Ori",category);
		return oList;
	}

	/**오리지널 도서 인포 수정*/
	@Override
	public int updateOriBookInfo(SqlSessionTemplate session, OriginBook oBook) {
		int result = session.update("wirterMapper.updateOriBookInfo",oBook);
		return result;
	}

	/**해시태그 수정*/
	@Override
	public int updateOriHashTag(SqlSessionTemplate session, HashTag hTag) {
		int result = session.update("wirterMapper.updateOriHashTag",hTag);
		return result;
	}

	/**일반도서 인포 수정하기*/
	@Override
	public int updateNorBookInfo(SqlSessionTemplate session, NormalBook nBook) {
		int result = session.update("adminWirteMapper.updateNorBookInfo",nBook);
		return result;
	}

	/**수정테이블의 시리즈 한편 가져오기*/
	@Override
	public OriginBookSeries selectOneModifySeries(SqlSessionTemplate session, int seriesNo, int bookNo) {
		OriginBookSeries os = new OriginBookSeries();
		os.setBookNo(bookNo+"");
		os.setSeriesNo(seriesNo);
		OriginBookSeries oSeries = session.selectOne("wirterMapper.ModifyOneSeries",os);
		return oSeries;
	}

	/**피넛 오리지널 수정분 업로드*/
	@Override
	public int updateModifyOne(SqlSessionTemplate session, OriginBookSeries oModifyS) {
		int result = session.update("wirterMapper.updateModifyOne",oModifyS); //도서 테이블에 수정
		result += session.delete("BookApproveMapper.updateReAppDelModify",oModifyS);//수정테이블에서 삭제
		result += session.update("BookApproveMapper.updateReAppUpSeries",oModifyS);//도서 허가 테이블에서 해당도서를 Y로
		result += session.update("BookApproveMapper.updateOriBookApprove",oModifyS);//도서테이블에서 해당도서를 승인
		
		
		return result;
	}

}
