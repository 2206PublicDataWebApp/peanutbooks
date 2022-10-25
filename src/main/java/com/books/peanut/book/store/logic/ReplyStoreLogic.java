package com.books.peanut.book.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.domain.Star;
import com.books.peanut.book.store.ReplyStore;

@Repository
public class ReplyStoreLogic implements ReplyStore{

	/**피넛 오리지널 리플등록*/
	@Override
	public int insertReply(SqlSessionTemplate session, OriginBookReply obReply) {
		int result = session.insert("bookReplyMapper.insertOriBookReply",obReply);
		return result;
	}

	/**피넛 오리지널 리플 불러오기*/
	@Override
	public List<OriginBookReply> selectAllOriBookReply(SqlSessionTemplate session, String bookNo,int currentPage, int boardLimit) {
		
		int offset = (currentPage-1)*boardLimit;
		RowBounds rowBounds= new RowBounds(offset,boardLimit);
		List<OriginBookReply>  obReply = session.selectList("bookReplyMapper.selectAllOriReply", bookNo,rowBounds);
		return obReply;
	}

	/**닉네임 가져오기*/
	@Override
	public String selectOneMemberNick(SqlSessionTemplate session, String memberId) {
		String mNick = session.selectOne("wirterMapper.oneMemberNick",memberId);
		return mNick;
	}

	/**피넛 오리지널 리플숫자 가져오기*/
	@Override
	public int selectReplyCount(SqlSessionTemplate session, String bookNo) {
		int count = session.selectOne("bookReplyMapper.countOriReply", bookNo);
		return count;
	}

	/**피넛 오리지널 리플 내용 가져오기*/
	@Override
	public String selectOneOroBookReply(SqlSessionTemplate session, String rNo) {
		String rContents = session.selectOne("bookReplyMapper.selectOneOriBookReply",rNo);
		return rContents;
	}

	/**피넛 오리지널 리플 쓴 사람 체크*/
	@Override
	public String selectOneNenberId(SqlSessionTemplate session, int rNo) {
		String checkMember = session.selectOne("bookReplyMapper.selectOneReplyMember",rNo);
		return checkMember;
	}

	/***피넛 오리지널 리플 수정*/
	@Override
	public int updateOriReply(SqlSessionTemplate session, OriginBookReply obReply) {
		int result = session.update("bookReplyMapper.updateOriReply",obReply);
		return result;
	}

	/**피넛 오리지널 리플 삭제*/
	@Override
	public int deleteOriReply(SqlSessionTemplate session, Integer rNo) {
		int result = session.delete("bookReplyMapper.deleteOriReply",rNo);
		return result;
	}

	/**별점주기*/
	@Override
	public int insertScore(SqlSessionTemplate session, Star star) {
		int result = session.insert("bookReplyMapper.insertScore",star);
		
		if(star.getCategory().equals("origin")) {
			result += session.update("bookReplyMapper.updateOriBook",star);
		}
		return result;
	}

	/**별점취소*/
	@Override
	public int deleteScore(SqlSessionTemplate session, Star star) {
		star.setScore(session.selectOne("bookReplyMapper.selectOneScore",star));
		
		int result = session.insert("bookReplyMapper.deleteScore",star);
		
		if(star.getCategory().equals("origin")) {
			result += session.update("bookReplyMapper.UpdateOriBookScoreMinus",star);
		}
		
		return result;
	}

	
	/**피넛 오리지널 책 한권 가져오기*/
	@Override
	public OriginBook selectOneBook(SqlSessionTemplate session, String bookNo) {
		OriginBook oBook = session.selectOne("wirterMapper.selectOneBook",bookNo);
		return oBook;
	}

}
