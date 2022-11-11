package com.books.peanut.notice.service;

import java.util.List;

import com.books.peanut.notice.domain.Notice;

public interface NoticeService {
	//공지사항 입력
	public int registeNotice(Notice notice);
	//공지사항 전체갯수 출력
	public int getTotalCount(String searchCondition, String searchValue, String nStatus);
	//공지사항 전체리스트 출력
	public List<Notice> printAllNotice(int currentPage, int noticeLimit, String nStatus);
	//공지사항 상세페이지
	public Notice printOneByNo(Integer noticeNo);
	//공지사항 삭제
	public int removeOneByNo(int noticeNo);
	//공지사항 수정
	public int modifyNotice(Notice notice);
	//공지사항 조건검색
	public List<Notice> printAllByValue(
			String searchCondition
			, String searchValue
			, int currentPage
			, int noticeLimit
			, String nStatus);
	//공지사항 카테고리별 검색 페이지수
	public int getTotalCount(String noticeCategory);
	//공지사항 카테고리별 검색
	public List<Notice> printAllByCategory(
			String noticeCategory
			, int currentPage
			, int categoryLimit);
	//공지사항 노출 선택
	public int chooseStatus(String noticeNo, String nStatus);
	//회원 공지사항 리스트뷰
	public List<Notice> noticeUserList(String noticeCategory);
	
	//공지사항 상황별 카운트
	public int totalBoard();
	public int showBoard();
	public int hideBoard();


}
