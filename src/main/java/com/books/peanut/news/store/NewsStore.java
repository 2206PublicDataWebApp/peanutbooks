package com.books.peanut.news.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.books.peanut.notice.domain.Notice;

public interface NewsStore {
	// 공지 알림 목록
	List<Notice> selectNoticeList(SqlSession session);

}
