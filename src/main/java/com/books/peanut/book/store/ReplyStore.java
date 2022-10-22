package com.books.peanut.book.store;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.OriginBookReply;

public interface ReplyStore {

	/**피넛오리지널 리플등록*/
	int insertReply(SqlSessionTemplate session, OriginBookReply obReply);

}
