package com.books.peanut.book.service;

import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.OriginBookReply;


public interface ReplyService {

	/**피넛 오리지널 리플등록*/
	int registOneReply(OriginBookReply obReply);

}
