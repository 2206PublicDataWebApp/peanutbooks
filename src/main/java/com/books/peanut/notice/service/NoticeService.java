package com.books.peanut.notice.service;

import java.util.List;

import com.books.peanut.notice.domain.Notice;

public interface NoticeService {
	//공지사항 입력
	public int registeNotice(Notice notice);
	//공지사항 전체갯수 출력
	public int getTotalCount(String searchCondition, String searchValue);
	//공지사항 전체리스트 출력
	public List<Notice> printAllNotice(int currentPage, int noticeLimit);
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
			, int noticeLimit);
	//공지사항 카테고리별 검색 페이지수
	public int getTotalCount(String noticeCategory);
	//공지사항 카테고리별 검색
	public List<Notice> printAllByCategory(
			String noticeCategory
			, int currentPage
			, int categoryLimit);


}
