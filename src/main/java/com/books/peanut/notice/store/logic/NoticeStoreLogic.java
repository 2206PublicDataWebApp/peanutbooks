package com.books.peanut.notice.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.notice.domain.Notice;
import com.books.peanut.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {
	//공지사항 입력
	@Override
	public int insertNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}
	//공지사항 총 개시물 갯수
	@Override
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue, String nStatus) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		paramMap.put("nStatus", nStatus);
		int totalCount = session.selectOne("NoticeMapper.selectTotalCount", paramMap);
		return totalCount;
	}
	//공지사항 총 개시물 리스트
	@Override
	public List<Notice> selectAllNotice(SqlSession session, int currentPage, int noticeLimit, String nStatus) {
		int offset = (currentPage-1)*noticeLimit;
		RowBounds rowBounds = new RowBounds(offset, noticeLimit);
		List<Notice> nList 
		= session.selectList("NoticeMapper.selectAllNotice"
				, nStatus, rowBounds);
		return nList;
	}
	//공지사항 상세보기
	@Override
	public Notice selectOneByNo(SqlSession session, Integer noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectOneByNo", noticeNo);
		return notice;
	}
	//공지사항 조회수 ======================
	@Override
	public int updateNoticeCount(SqlSession session, int noticeNo) {
		int result = session.update("NoticeMapper.updateCount", noticeNo);
		return result;
	}
	//공지사항 삭제
	@Override
	public int deleteOneByNo(SqlSession session, int noticeNo) {
		int result = session.delete("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}
	//공지사항 수정
	@Override
	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.update("NoticeMapper.updateNotice", notice);
		return result;
	}
	//공지사항 검색
	@Override
	public List<Notice> selectAllByValue(
			SqlSession session
			, String searchCondition
			, String searchValue
			, int currentPage, int noticeLimit) {
		int offset = (currentPage-1)*noticeLimit;
		RowBounds rowBounds 
		= new RowBounds(offset, noticeLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		List<Notice> nList 
		= session.selectList("NoticeMapper.selectAllByValue"
				, paramMap, rowBounds);
		return nList;
	}
	//공지사항 상세검색
	@Override
	public List<Notice> selectAllByCategory(
			SqlSession session
			, String noticeCategory
			, int currentPage, int categoryLimit) {
		int offset = (currentPage-1)*categoryLimit;
		RowBounds rowBounds = new RowBounds(offset, categoryLimit);
		List<Notice> nList 
		= session.selectList("NoticeMapper.selectAllCategory"
				, noticeCategory, rowBounds);
		return nList;
	}
	//공지사항 카테고리별 검색 페이지갯수
	@Override
	public int selectTotalCount(SqlSession session, String noticeCategory) {
		int totalCount = session.selectOne("NoticeMapper.selectTotalCategoryList", noticeCategory);
		return totalCount;
	}
	//공지사항 노출 선택
	@Override
	public int updateStatus(SqlSession session, String noticeNo, String nStatus) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("noticeNo", noticeNo);
		paramMap.put("nStatus", nStatus);
		int result = session.update("NoticeMapper.updateStatus", paramMap);
		return result;
	}
	//유저 공지사항 리스트뷰
	@Override
	public List<Notice> selectNoticeUser(SqlSession session, String noticeCategory) {
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeUser", noticeCategory);
		return nList;
	}

}

