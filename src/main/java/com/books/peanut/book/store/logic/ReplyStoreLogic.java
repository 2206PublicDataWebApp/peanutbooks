package com.books.peanut.book.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
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

	/**리플숫자 가져오기*/
	@Override
	public int selectReplyCount(SqlSessionTemplate session, String bookNo) {
		int count = session.selectOne("bookReplyMapper.countOriReply", bookNo);
		return count;
	}

}
