package com.books.peanut.qna.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.qna.service.QnaService;
import com.books.peanut.qna.store.QnaStore;
@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	private QnaStore qStore;
	@Autowired
	private SqlSessionTemplate session;
	
}
