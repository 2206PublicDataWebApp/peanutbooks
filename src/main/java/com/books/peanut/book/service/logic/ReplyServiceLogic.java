package com.books.peanut.book.service.logic;

import java.util.List;

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
	
	/**피넛 오리지널 리플등록하기*/
	@Override
	public int registOneReply(OriginBookReply obReply) {
		int result = rStore.insertReply(session,obReply);
		return result;
	}

	/**피넛오리지널 리플불러오기*/
	@Override
	public List<OriginBookReply> OriBookReply(String bookNo, int currentPage, int boardLimit) {
		List<OriginBookReply> obReply = rStore.selectAllOriBookReply(session,bookNo,currentPage,boardLimit);
		return obReply;
	}

	/**닉네임 가져오기*/
	@Override
	public String getMemberNickName(String memberId) {
		String mNick = rStore.selectOneMemberNick(session,memberId);
		return mNick;
	}

	/**피넛오리지널 리플 수 가져오기*/
	@Override
	public int getTotalCount(String bookNo) {
		int count = rStore.selectReplyCount(session, bookNo);
		
		return count;
	}

	
	/**피넛 오리지널 리플 1개 내용 가져오기*/
	@Override
	public String getOriOneReply(String rNo) {
		String rContents = rStore.selectOneOroBookReply(session, rNo);
		return rContents;
	}

	
	/**피넛 오리지널 리플 쓴 사람 체크*/
	@Override
	public String checkOriReplyMember(int rNo) {
		String checkMember = rStore.selectOneNenberId(session,rNo);
		return checkMember;
	}

	@Override
	public int modifyReply(OriginBookReply obReply) {
		int result = rStore.updateOriReply(session,obReply);
		return result;
	}

	
}
