package com.books.peanut.news.service.logic;

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
	private NewsStore nsStore;
	

}
