package com.books.peanut.book.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.HashTag;
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

	

}
