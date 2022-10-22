package com.books.peanut.book.store.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.store.ReplyStore;

@Repository
public class ReplyStoreLogic implements ReplyStore{

	/**피넛 오리지널 리플등록*/
	@Override
	public int insertReply(SqlSessionTemplate session, OriginBookReply obReply) {
		int result = session.insert("bookReplyMapper.insertOriBookReply",obReply);
		return 0;
	}

}
