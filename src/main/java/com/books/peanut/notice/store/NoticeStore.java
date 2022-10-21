package com.books.peanut.notice.store;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.notice.domain.Notice;

public interface NoticeStore {
	//공지사항 작성
	public int insertNotice(SqlSession session, Notice notice);
	//페이징을 위한 총게시물 카운트
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);
	//전체게시물 출력
	public List<Notice> selectAllNotice(SqlSession session, int currentPage, int noticeLimit);
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
			int currentPage, int noticeLimit);
	public List<Notice> selectAllByCategory(SqlSession session, 
			String noticeCategory, int currentPage,
			int categoryLimit);

}
