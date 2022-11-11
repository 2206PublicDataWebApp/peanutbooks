package com.books.peanut.notice.store;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.books.peanut.notice.domain.Notice;

public interface NoticeStore {
	//공지사항 작성
	public int insertNotice(SqlSession session, Notice notice);
	//페이징을 위한 총게시물 카운트
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue, String nStatus);
	//전체게시물 출력
	public List<Notice> selectAllNotice(SqlSession session, int currentPage, int noticeLimit, String nStatus);
	//공지사항 상세페이지보기
	public Notice selectOneByNo(SqlSession session, Integer noticeNo);
	//공지사항 조회수 올리기
	public int updateNoticeCount(SqlSession session, int noticeNo);
	//공지사항 삭제
	public int deleteOneByNo(SqlSession session, int noticeNo);
	//공지사항 수정
	public int updateNotice(SqlSession session, Notice notice);
	//공지사항 조건검색
	public List<Notice> selectAllByValue(SqlSession session, 
			String searchCondition, String searchValue,
			int currentPage, int noticeLimit, String nStatus);
	public List<Notice> selectAllByCategory(SqlSession session, 
			String noticeCategory, int currentPage,
			int categoryLimit);
	//공지사항 조건검색 페이지 갯수
	public int selectTotalCount(SqlSession session, String noticeCategory);
	//공지사항 노출 선택
	public int updateStatus(SqlSession session, String noticeNo, String nStatus);
	//유저 공지사항 리스트뷰
	public List<Notice> selectNoticeUser(SqlSession session, String noticeCategory);
	//공지항 상황별 카운트
	public int selectAllBoard(SqlSession session);
	public int selectShowBoard(SqlSession session);
	public int selectHideBoard(SqlSession session);

}
