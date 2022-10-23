package com.books.peanut.book.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.OriginBookReply;

public interface ReplyStore {

	/**피넛오리지널 리플등록*/
	int insertReply(SqlSessionTemplate session, OriginBookReply obReply);

	/**피넛 오리지널 리플 불러오기
	 * @param boardLimit 
	 * @param currentPage */
	List<OriginBookReply> selectAllOriBookReply(SqlSessionTemplate session, String bookNo, int currentPage, int boardLimit);

	/**닉네임 가져오기*/
	String selectOneMemberNick(SqlSessionTemplate session, String memberId);

	/**리플숫자 가져오기*/
	int selectReplyCount(SqlSessionTemplate session, String bookNo);

}
