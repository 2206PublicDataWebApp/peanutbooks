package com.books.peanut.book.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookReply;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.domain.ReReply;
import com.books.peanut.book.domain.Star;
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

	/**피넛 오리지널 리플 수정*/
	@Override
	public int modifyReply(OriginBookReply obReply) {
		int result = rStore.updateOriReply(session,obReply);
		return result;
	}

	/**피넛 오리지널 리플 삭제*/
	@Override
	public int removeOriReply(Integer rNo) {
		int result = rStore.deleteOriReply(session,rNo);
		return result;
	}

	/**별점주기*/
	@Override
	public int getStarScoreOrigin(Star star) {
		int result = rStore.insertScore(session, star);
		return result;
	}

	/**별점취소*/
	@Override
	public int removeScore(Star star) {
		int result = rStore.deleteScore(session, star);
		return result;
	}

	
	/**피넛 오리지널 책 한권가져오기*/
	@Override
	public OriginBook showOnebook(String bookNo) {
		OriginBook oBook = rStore.selectOneBook(session, bookNo);
		return oBook;
	}

	/**일반 도서 한권 가져오기*/
	@Override
	public NormalBook showOneNorBook(String bookNo) {
		NormalBook nBook = rStore.selectOneNorBook(session, bookNo);
		return nBook;
	}

	/**일반도서 리플갯수 가져오기*/
	@Override
	public int getTotalNorReplyCount(String bookNo) {
		int result = rStore.selectAllCountNorReply(session, bookNo);
		return result;
	}

	/**일반도서 리플목록가져오기*/
	@Override
	public List<NormalBookReply> norBookReply(String bookNo, int currentPage, int boardLimit) {
		List<NormalBookReply> nrList = rStore.selectAllNorReply(session, bookNo, currentPage, boardLimit);
		return nrList;
	}

	/**일반도서 리플 등록하기*/
	@Override
	public int registNorOneReply(NormalBookReply nbReply) {
		int result = rStore.insertNorReply(session,nbReply);
		return result;
	}

	/**일반도서 리플쓴 사람 체크*/
	@Override
	public String checkNorReplyMember(int replyNo) {
		String checkMember = rStore.selectOneNorMemberId(session,replyNo);
		return checkMember;
	}

	/**일반도서 리플 수정하기*/
	@Override
	public int modifyNorReply(NormalBookReply nbReply) {
		int result = rStore.updateNorReply(session,nbReply);
		return result;
	}

	/**일반도서 리플 내용 가져오기*/
	@Override
	public String getNorOneReply(String rNo) {
		String rContents = rStore.selectNorOroBookReply(session, rNo);
		return rContents;
	}
	
	/**일반도서 리플삭제*/
	@Override
	public int removeNorReply(Integer rNo) {
		int replyNo = (int)rNo;
		int result = rStore.deleteNorReply(session,replyNo);
		return result;
	}

	/**리리플 등록*/
	@Override
	public int registOneReReply(ReReply rReply) {
		int result = rStore.insertReReply(session,rReply);
		return result;
	}

	@Override
	public List<ReReply> BookReReply(ReReply r) {
		List<ReReply> rRreply = rStore.selectOneBookReReply(session,r);
		return rRreply;
	}
	


	
}
