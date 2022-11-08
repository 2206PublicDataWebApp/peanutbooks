package com.books.peanut.qna.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.qna.domain.Qna;
import com.books.peanut.qna.store.QnaStore;
@Repository
public class QnaStoreLogic implements QnaStore {

	@Override
	public int insertQna(SqlSessionTemplate session, Qna qna) {
		int result = session.insert("QnaMapper.insertQna", qna);
		return result;
	}
	//회원별 qna게시판 총게시물 구하기
	@Override
	public int selectMemberQnaCount(SqlSessionTemplate session, String memberId, String searchCondition, String searchValue, String qnaStatus) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memberId", memberId);
		paramMap.put("qnaStatus", qnaStatus);
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("QnaMapper.selectMemberQnaCount", paramMap);
		return totalCount;
	}
	//회원별 qna게시판 리스트 페이징
	@Override
	public List<Qna> selectMemberQna(SqlSessionTemplate session, String memberId, int currentPage, int qnaLimit, String qnaStatus) {
		int offset = (currentPage - 1) * qnaLimit;
		RowBounds rowBounds = new RowBounds(offset, qnaLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memberId", memberId);
		paramMap.put("qnaStatus", qnaStatus);
		List<Qna> qList = session.selectList("QnaMapper.selectMemberQna", paramMap, rowBounds);
		return qList;
	}
	//회원 게시물 상세보기
	@Override
	public Qna selectOneByNo(SqlSessionTemplate session, Integer qnaNo) {
		Qna qna = session.selectOne("QnaMapper.selectOneByNo", qnaNo);
		return qna;
	}
	//회원 게시물 삭제
	@Override
	public int deleteOneByNo(SqlSessionTemplate session, Integer qnaNo) {
		int result = session.delete("QnaMapper.deleteOneByNo", qnaNo);
		return result;
	}
	//회원 게시물 업데이트
	@Override
	public int updateQna(SqlSessionTemplate session, Qna qna) {
		int result = session.update("QnaMapper.updateOneQna", qna);
		return result;
	}
	//회원별 상세검색 페이지 출력
	@Override
	public List<Qna> selectMemberByValue(SqlSessionTemplate session, String memberId, String searchCondition, String searchValue,
			int currentPage, int qnaLimit) {
		int offset = (currentPage-1)*qnaLimit;
		RowBounds rowBounds = new RowBounds(offset, qnaLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memberId", memberId);
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		List<Qna> qList = session.selectList("QnaMapper.selectMemberByValue", paramMap, rowBounds);
		return qList;
	}
	//관리자 qna게시판 총게시물 구하기
	@Override
	public int selectAllCount(SqlSessionTemplate session, String searchCondition, String searchValue, String qnaStatus) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		paramMap.put("qnaStatus", qnaStatus);
		int totalCount = session.selectOne("QnaMapper.selectAllQnaCount", paramMap);
		return totalCount;
	}

	//관리자 Qna게시판 리스트 페이징 
	@Override
	public List<Qna> selectAllQna(SqlSessionTemplate session, int currentPage, int aqnaLimit, String qnaStatus) {
		int offset = (currentPage-1)*aqnaLimit;
		RowBounds rowBounds = new RowBounds(offset, aqnaLimit);
		List<Qna> aList 
		= session.selectList("QnaMapper.selectAllQna"
				, qnaStatus, rowBounds);
		return aList;
	}
	//관리자 회원문의글 답변
	@Override
	public int answerQna(SqlSessionTemplate session, Qna qna) {
		int  result = session.update("QnaMapper.insertAnswer", qna);
		return result;
	}
	//관리자 상세검색
	@Override
	public List<Qna> selectAllByValue(SqlSessionTemplate session, String searchCondition, String searchValue,
			int currentPage, int aqnaLimit) {
		int offset = (currentPage-1)*aqnaLimit;
		RowBounds rowBounds = new RowBounds(offset, aqnaLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		List<Qna> aList = session.selectList("QnaMapper.selectAllByValue",paramMap, rowBounds);
		return aList;
	}
	//관리자 카테고리 리스트 출력
	@Override
	public List<Qna> selectAllByCategory(
			SqlSessionTemplate session
			, String qnaCategory
			, int currentPage
			, int categoryLimit) {
		int offset = (currentPage-1)*categoryLimit;
		RowBounds rowBounds = new RowBounds(offset, categoryLimit);
		List<Qna> aList = session.selectList("QnaMapper.selectAllCategory", qnaCategory, rowBounds);
		return aList;
	}
	//관리자 카테고리 리스트별 갯수
	@Override
	public int selectCategoryCount(SqlSessionTemplate session, String qnaCategory) {
		int totalCount = session.selectOne("QnaMapper.selectCategoryList", qnaCategory);
		return totalCount;
	}
	//회원 답변상황에 따른 리스트 카운트
	@Override
	public int selectTotalQna(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("QnaMapper.selectTotalQna", memberId);
		return result;
	}
	@Override
	public int selectTotalAnswer(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("QnaMapper.selectTotalAnswer", memberId);
		return result;
	}
	@Override
	public int selectTotalNoAnswer(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("QnaMapper.selectTotalNoAnswer", memberId);
		return result;
	}
	//관리자 답변상황에 따른 리스트 카운트
	@Override
	public int selectTotalQna(SqlSessionTemplate session) {
		int result = session.selectOne("QnaMapper.selectTotalQnaAdmin");
		return result;
	}
	@Override
	public int selectTotalAnswer(SqlSessionTemplate session) {
		int result = session.selectOne("QnaMapper.selectTotalAnswerAdmin");
		return result;
	}
	@Override
	public int selectTotalNoAnswer(SqlSessionTemplate session) {
		int result = session.selectOne("QnaMapper.selectTotalNoAnswerAdmin");
		return result;
	}
}
