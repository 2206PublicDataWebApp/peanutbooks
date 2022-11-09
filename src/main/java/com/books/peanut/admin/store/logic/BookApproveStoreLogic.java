package com.books.peanut.admin.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.admin.domain.ModifyBookSeries;
import com.books.peanut.admin.store.BookApproveStore;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.WriterPay;
import com.books.peanut.qna.domain.Qna;
@Repository
public class BookApproveStoreLogic implements BookApproveStore{

	//전체 도서 갯수
	@Override
	public int selectAllOriSeries(SqlSession session) {
		int count = session.selectOne("BookApproveMapper.selectAllOriSeries");
		return count;
	}
	//전체 도서 리스트 출력
	@Override
	public List<OriginBookSeries> selectAllOriSeries(SqlSession session, int i, int limit) {
		int offset = (i-1)*limit;
		RowBounds rowBounds= new RowBounds(offset,limit);
		
		List<OriginBookSeries> osList = session.selectList("BookApproveMapper.selectAllMemberSeriese", null, rowBounds);
		return osList;
	}
	//승인 도서 갯수
	@Override
	public int selectCheckPermissionCount(SqlSession session, String checkPermission) {
		int count = session.selectOne("BookApproveMapper.selectCheckPermission", checkPermission);
		return count;
	}
	//승인 도서 리스트
	@Override
	public List<OriginBookSeries> selectCheckPermissionList(SqlSession session, int i, int limit,
			String checkPermission, String step) {
		int offset = (i-1)*limit;
		RowBounds rowBounds= new RowBounds(offset,limit);
		HashMap hMap = new HashMap<String, String>();
		hMap.put("checkPermission", checkPermission);
		hMap.put("step", step);
		List<OriginBookSeries> osList = session.selectList("BookApproveMapper.selectCheckPermissionList",hMap, rowBounds);
		return osList;
	}
	//보류 도서 갯수
	@Override
	public int selectAllReAppCount(SqlSession session) {
		int count = session.selectOne("BookApproveMapper.selectAllReAppCount");
		return count;
	}
	//보류 도서 리스트
	@Override
	public List<ModifyBookSeries> selectAllReAppList(SqlSession session, int i, int limit) {
		int offset = (i-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<ModifyBookSeries> mbList = session.selectList("BookApproveMapper.selectAllReAppList", null, rowBounds);
		return mbList;
	}
	//책 승인
	@Override
	public int updateApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo) {
		OriginBookSeries os = new OriginBookSeries();
		os.setBookNo(bookNo+"");
		os.setSeriesNo(seriesNo);
		
		int result = session.update("BookApproveMapper.updateApproveBooks", os);
		result += session.update("BookApproveMapper.updateOriBookApprove",os);
		result += session.update("BookApproveMapper.updateOriBookSeiresApprove",os);
		return result;
	}
	//책 재승인
	@Override
	public int updateReApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo) {
		OriginBookSeries os = new OriginBookSeries();
		os.setBookNo(bookNo+"");
		os.setSeriesNo(seriesNo);
		
		int result = session.update("BookApproveMapper.updateREAppInPermission", os);
		result += session.update("BookApproveMapper.updateReAppUpSeries",os);
		result += session.update("BookApproveMapper.updateReAppDelModify",os);
		return result;
	}
	
	
	//전체 회원 갯수
	@Override
	public int selectAllMember(SqlSession session, String searchCondition, String searchValue, String code) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		paramMap.put("code", code);
		int count = session.selectOne("BookApproveMapper.selectAllMemberCount", paramMap);
		return count;
	}
	//전체 회원 리스트
	@Override
	public List<Member> selectAllMembers(SqlSession session, int i, int memberLimit, String code) {
		int offset = (i-1)*memberLimit;
		RowBounds rowBounds= new RowBounds(offset,memberLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("code", code);
		List<Member> mList = session.selectList("BookApproveMapper.selectAllMembers", paramMap, rowBounds);
		return mList;
	}
	//회원 상세보기
	@Override
	public Member selectOneById(SqlSession session, String memberId) {
		Member member = session.selectOne("BookApproveMapper.selectOneById", memberId);
		return member;
	}
	//회원 삭제
	@Override
	public int deleteOneById(SqlSession session, String memberId) {
		int result = session.delete("BookApproveMapper.deleteOneById", memberId);
		return result;
	}
	//회원 리스트 출력
	@Override
	public List<Member> selectSeachAllMember(
			SqlSession session
			, String searchCondition
			, String searchValue
			, int i
			, int limit) {
		int offset = (i-1)*limit;
		RowBounds rowBounds= new RowBounds(offset,limit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		List<Member> mList = session.selectList("BookApproveMapper.selectSearchAllMember", paramMap, rowBounds);
		return mList;
	}
	//회원정보수정
	@Override
	public int updateMemberModify(SqlSession session, Member member) {
		int result = session.update("BookApproveMapper.updateMemberModify", member);
		return result;
	}
	//회원 상태별로 카운트
	@Override
	public int selectTodayCount(SqlSession session) {
		int result = session.selectOne("BookApproveMapper.selectTodayCount");
		return result;
	}
	@Override
	public int selectDeleteMemberCount(SqlSession session) {
		int result = session.selectOne("BookApproveMapper.selectDeleteMemberCount");
		return result;
	}
	@Override
	public int selectTotalCount(SqlSession session) {
		int result = session.selectOne("BookApproveMapper.selectTotalCount");
		return result;
	}
	//도서 상태별로 카운트
	@Override
	public int selectAllBooks(SqlSession session) {
		int result = session.selectOne("BookApproveMapper.selectAllBoos");
		return result;
	}
	@Override
	public int selectApproveYes(SqlSession session) {
		int result = session.selectOne("BookApproveMapper.selectApproveYes");
		return result;
	}
	@Override
	public int selectApproveNo(SqlSession session) {
		int result = session.selectOne("BookApproveMapper.selectApproveNo");
		return result;
	}
	//관리자 메인페이지
	@Override
	public List<Qna> selectPrintNewQna(SqlSession session) {
		List<Qna> qList = session.selectList("BookApproveMapper.selectPrintNewQna");
		return qList;
	}
	@Override
	public List<OriginBookSeries> selectPrintNewBooks(SqlSession session) {
		List<OriginBookSeries> osList = session.selectList("BookApproveMapper.selectPrintNewBooks");
				return osList;
	}
	@Override
	public List<Member> selectPrintNewMembers(SqlSession session) {
		List<Member> mList = session.selectList("BookApproveMapper.selectPrintNewMembers");
				return mList;
	}
	@Override
	public List<WriterPay> selectPrintNewPay(SqlSession session) {
		List<WriterPay> wrList = session.selectList("BookApproveMapper.selectPrintNewPay");
				return wrList;
	}




	



	

	

}
