package com.books.peanut.book.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.service.ReplyService;
import com.books.peanut.book.store.ReplyStore;

@Service
public class ReplyServiceLogic implements ReplyService {
	
	@Autowired
	ReplyStore rStore;
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public int registOneReply(OriginBookReply obReply) {
		int result = rStore.insertReply(session,obReply);
		return result;
	}

}
