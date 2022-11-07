package com.books.peanut.news.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.news.service.NewsService;
import com.books.peanut.news.store.NewsStore;
import com.books.peanut.notice.domain.Notice;

@Service
public class NewsServiceImpl implements NewsService {
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private NewsStore nStore;
	
	// 공지 알림 목록
	@Override
	public List<Notice> showNoticeList() {
		List<Notice> nList = nStore.selectNoticeList(session);
		return nList;
	}

}
