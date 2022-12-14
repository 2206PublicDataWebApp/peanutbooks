package com.books.peanut.book.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.Attendance;
import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookReply;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.domain.ReReply;
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
		}else if(star.getCategory().equals("normal")) {
			result += session.update("bookReplyMapper.updateNorBook",star);
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
		}else if(star.getCategory().equals("normal")) {
			result += session.update("bookReplyMapper.UpdateNorBookScoreMinus",star);
		}
		
		return result;
	}

	
	/**피넛 오리지널 책 한권 가져오기*/
	@Override
	public OriginBook selectOneBook(SqlSessionTemplate session, String bookNo) {
		OriginBook oBook = session.selectOne("wirterMapper.selectOneBook",bookNo);
		return oBook;
	}

	/**일반도서 가져오기*/
	@Override
	public NormalBook selectOneNorBook(SqlSessionTemplate session, String bookNo) {
		NormalBook nBook = session.selectOne("adminWirteMapper.selectOneNorBook",bookNo);
		return nBook;
	}

	
	/**일반도서 리플갯수 가져오기*/
	@Override
	public int selectAllCountNorReply(SqlSessionTemplate session, String bookNo) {
		int rseult = session.selectOne("bookReplyMapper.countNorReply",bookNo);
		return rseult;
	}

	/**일반도서 리플목록 가져오기*/
	@Override
	public List<NormalBookReply> selectAllNorReply(SqlSessionTemplate session, String bookNo, int currentPage,
			int boardLimit) {
		int offset = (currentPage-1)*boardLimit;
		RowBounds rowBounds= new RowBounds(offset,boardLimit);
		List<NormalBookReply>  nrList = session.selectList("bookReplyMapper.selectAllnorReply", bookNo,rowBounds);
		return nrList;

	}

	/**일반도서 리플 등록하기*/
	@Override
	public int insertNorReply(SqlSessionTemplate session, NormalBookReply nbReply) {
		int result = session.insert("bookReplyMapper.insertNorBookReply",nbReply);
		return result;
	}

	/**일반도서 리플쓴 사람 체크*/
	@Override
	public String selectOneNorMemberId(SqlSessionTemplate session, int replyNo) {
		String checkMember = session.selectOne("bookReplyMapper.selectOneNorReplyMember",replyNo);
		return checkMember;
	}

	/**일반도서 리플 수정하기*/
	@Override
	public int updateNorReply(SqlSessionTemplate session, NormalBookReply nbReply) {
		int result = session.update("bookReplyMapper.updateNorReply", nbReply);
		return result;
	}

	/**일반도서 리플내용 가져오기*/
	@Override
	public String selectNorOroBookReply(SqlSessionTemplate session, String rNo) {
		String rContents = session.selectOne("bookReplyMapper.selectOneNorBookReply",rNo);
		return rContents;
	}

	/**일반도서 리플삭제*/
	@Override
	public int deleteNorReply(SqlSessionTemplate session, int rNo) {
		int result = session.delete("bookReplyMapper.deleteNorReply",rNo);
		return result;
	}

	/**리리플 등록*/
	@Override
	public int insertReReply(SqlSessionTemplate session, ReReply rReply) {
		int result = session.insert("bookReplyMapper.insertReReply",rReply);
		return result;
	}

	/**리리플 불러오기*/
	@Override
	public List<ReReply> selectOneBookReReply(SqlSessionTemplate session, ReReply r) {
		List<ReReply> rReply = session.selectList("bookReplyMapper.selectOneBookReReply",r);
		return rReply;
	}

	/**리리플 작성자 가져오기*/
	@Override
	public String selectReplymember(SqlSessionTemplate session, Integer rNo) {
		String memberId= session.selectOne("bookReplyMapper.selectReplymember",rNo);
		return memberId;
	}

	/**리리플 삭제*/
	@Override
	public int deleteReReply(SqlSessionTemplate session, Integer rNo) {
		int result = session.delete("bookReplyMapper.deleteReReply",rNo);
		return result;
	}

	/**리리플 하나 불러오기*/
	@Override
	public String selectOneReReply(SqlSessionTemplate session, String rNo) {
		String contents = session.selectOne("bookReplyMapper.selectOneReReply",rNo);
		return contents;
	}

	/**리리플 수정하기*/
	@Override
	public int updateReReply(SqlSessionTemplate session, ReReply rReply) {
		int result = session.update("bookReplyMapper.updateOneRereply",rReply);
		return result;
	}

	/**리리플달렸는지 확인하기*/
	@Override
	public int countReReply(SqlSessionTemplate session, Integer rNo, String category) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("rNo",rNo+"");
		hMap.put("category",category);
		int result = session.selectOne("bookReplyMapper.SelectcountReReply",hMap);
		return result;
	}

	/**피넛 오리지널 댓글 삭제했다고 내용 변경하기*/
	@Override
	public int UpdateRemoveOriReply(SqlSessionTemplate session, Integer rNo) {
		int result = session.update("bookReplyMapper.UpdateRemoveOriReply",rNo);
		return result;
	}

	/**피넛 오리지널 댓글 삭제했다고 내용 변경하기*/
	@Override
	public int UpdateRemoveNorReply(SqlSessionTemplate session, Integer rNo) {
		int result = session.update("bookReplyMapper.UpdateRemoveNorReply",rNo);
		return result;
		}

	/**오늘 이벤트 했는지 확인*/
	@Override
	public int selectTodayAttend(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("bookReplyMapper.selectTodayAttend",memberId);
		return result;
	}

	/**이벤트 참여한적 있는지 확인*/
	@Override
	public int selectcheckFirstAttend(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("bookReplyMapper.selectcheckFirstAttend",memberId);
		return result;
	}

	/**첫 이벤트 참여*/
	@Override
	public int insertFirstAttend(SqlSessionTemplate session, String memberId) {
		int result = session.insert("bookReplyMapper.insertFirstAttend",memberId);
		return result;
	}

	/**이벤트 몇번 참여했는지 확인*/
	@Override
	public int selectAttendEvent(SqlSessionTemplate session, String memberId,String string) {
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("memberId", memberId);
		hMap.put("string", string);
		int result = session.selectOne("bookReplyMapper.selectAttendEvent",hMap);
		return result;
	}

	/**두번째 출석 이벤트 추가*/
	@Override
	public int udateSecondEvent(SqlSessionTemplate session, String memberId) {
		int result = session.update("bookReplyMapper.udateSecondEvent",memberId);
		return result;
	}
	/**세번째 출석 이벤트 추가*/
	@Override
	public int udateThirdEvent(SqlSessionTemplate session, String memberId) {
		int result = session.update("bookReplyMapper.udateThirdEvent",memberId);
		return result;
	}

	/**네번째 출석 이벤트 추가*/
	@Override
	public int udateFourthEvent(SqlSessionTemplate session, String memberId) {
		int result = session.update("bookReplyMapper.udateFourthEvent",memberId);
		return result;
	}
	
	/**다섯번째 출석 이벤트 추가*/
	@Override
	public int udateFifthEvent(SqlSessionTemplate session, String memberId) {
		int result = session.update("bookReplyMapper.udateFifthEvent",memberId);
		return result;
	}

	/**출석이벤트로 피넛 5개줌*/
	@Override
	public int UpdateEventPeanut(SqlSessionTemplate session, String memberId) {
		int result = session.insert("bookReplyMapper.UpdateEventPeanut",memberId);
		result += session.update("bookReplyMapper.updateMemberPoint",memberId);
		return result;
	}



	

}
