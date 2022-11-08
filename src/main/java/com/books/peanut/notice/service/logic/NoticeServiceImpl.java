package com.books.peanut.notice.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.notice.domain.Notice;
import com.books.peanut.notice.service.NoticeService;
import com.books.peanut.notice.store.NoticeStore;



@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeStore nStore;
	@Autowired
	private SqlSession session;
	//공지사항 입력하기
	@Override
	public int registeNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}
	//공지사항 전체페이지 갯수
	@Override
	public int getTotalCount(String searchCondition, String searchValue, String nStatus) {
		int totalCount = nStore.selectTotalCount(session, searchCondition, searchValue, nStatus);
		return totalCount;
	}
	//공지사항 전체리스트
	@Override
	public List<Notice> printAllNotice(int currentPage, int noticeLimit, String nStatus) {
		List<Notice> nList = nStore.selectAllNotice(session, currentPage, noticeLimit, nStatus);
		return nList;
	}
	//공지사항 상세페이지
	@Override
	public Notice printOneByNo(Integer noticeNo) {
		Notice notice = nStore.selectOneByNo(session, noticeNo);
		int result = 0;
		if(notice != null) {
			result = nStore.updateNoticeCount(session, noticeNo);
		}
		return notice;
	}
	//공지사항 삭제
	@Override
	public int removeOneByNo(int noticeNo) {
		int result = nStore.deleteOneByNo(session, noticeNo);
		return result;
	}
	//공지사항 수정
	@Override
	public int modifyNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}
	//공지사항 상세검색
	@Override
	public List<Notice> printAllByValue(
			String searchCondition
			, String searchValue
			, int currentPage
			, int noticeLimit) {
		List<Notice> nList = nStore.selectAllByValue(session
				, searchCondition, searchValue
				, currentPage, noticeLimit);
		return nList;
	}
	//카테고리별 검색
	@Override
	public List<Notice> printAllByCategory(
			String noticeCategory
			, int currentPage
			, int categoryLimit) {
		List<Notice> nList = nStore.selectAllByCategory(session
				, noticeCategory, currentPage, categoryLimit);
		return nList;
	}
	//카테고리별 검색 페이지 수
	@Override
	public int getTotalCount(String noticeCategory) {
		int totalCount = nStore.selectTotalCount(session, noticeCategory);
		return totalCount;
	}
	//공지사항 노출 선택
	@Override
	public int chooseStatus(String noticeNo, String nStatus) {
		int result = nStore.updateStatus(session, noticeNo, nStatus);
		return result;
	}
	@Override
	public List<Notice> noticeUserList(String noticeCategory) {
		List<Notice> nList = nStore.selectNoticeUser(session, noticeCategory);
		return nList;
	}
	@Override
	public int totalBoard() {
		int result = nStore.selectAllBoard(session);
		return result;
	}
	@Override
	public int showBoard() {
		int result = nStore.selectShowBoard(session);
		return result;
	}
	@Override
	public int hideBoard() {
		int result = nStore.selectHideBoard(session);
		return result;
	}



}
