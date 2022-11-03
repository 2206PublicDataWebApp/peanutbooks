package com.books.peanut.book.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookReply;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.domain.ReReply;
import com.books.peanut.book.domain.Star;

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

	/**피넛 오리지널 리플 삭제*/
	int deleteOriReply(SqlSessionTemplate session, Integer rNo);

	/**별점주기*/
	int insertScore(SqlSessionTemplate session, Star star);
	
	/**별점취소*/
	int deleteScore(SqlSessionTemplate session, Star star);

	/**피넛 오리지널 도서 한권가져오기*/
	OriginBook selectOneBook(SqlSessionTemplate session, String bookNo);

	/**일반도서 한권가져오기*/
	NormalBook selectOneNorBook(SqlSessionTemplate session, String bookNo);

	/**일반도서 리플갯수 가져오기*/
	int selectAllCountNorReply(SqlSessionTemplate session, String bookNo);

	/**일반도서 리플목록 가져오기*/
	List<NormalBookReply> selectAllNorReply(SqlSessionTemplate session, String bookNo, int currentPage, int boardLimit);

	/**일반도서 리플 등록하기*/
	int insertNorReply(SqlSessionTemplate session, NormalBookReply nbReply);

	/**일반도서 리플쓴 사람 체크*/
	String selectOneNorMemberId(SqlSessionTemplate session, int replyNo);

	/**일반도서 리플 수정하기*/
	int updateNorReply(SqlSessionTemplate session, NormalBookReply nbReply);

	/**일반도서 리플내용 가져오기*/
	String selectNorOroBookReply(SqlSessionTemplate session, String rNo);

	/**일반도서 리플삭제*/
	int deleteNorReply(SqlSessionTemplate session, int replyNo);

	/**리리플등록*/
	int insertReReply(SqlSessionTemplate session, ReReply rReply);

	/**리리플 가져오기*/
	List<ReReply> selectOneBookReReply(SqlSessionTemplate session, ReReply r);


}
