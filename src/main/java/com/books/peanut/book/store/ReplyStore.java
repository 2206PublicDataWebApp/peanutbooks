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

	/**피넛 오리지널 리플숫자 가져오기*/
	int selectReplyCount(SqlSessionTemplate session, String bookNo);

	/**피넛 오리지널 리플 내용 가져오기*/
	String selectOneOroBookReply(SqlSessionTemplate session, String rNo);

	/**피넛 오리지널 리플 쓴 사람 체크
	 * @param session */
	String selectOneNenberId(SqlSessionTemplate session, int rNo);

	/**피넛 오리지널 리플 수정*/
	int updateOriReply(SqlSessionTemplate session, OriginBookReply obReply);

}
