package com.books.peanut.news.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.news.store.NewsStore;
import com.books.peanut.notice.domain.Notice;

@Repository
public class NewsStoreLogic implements NewsStore {
	// 공지 알림 목록
	@Override
	public List<Notice> selectNoticeList(SqlSession session) {
		List<Notice> nList = session.selectList("NewsMapper.selectNoticeList");
		return nList;
	}

}
