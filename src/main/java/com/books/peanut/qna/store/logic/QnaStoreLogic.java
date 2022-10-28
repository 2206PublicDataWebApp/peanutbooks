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
	public int selectMemberQnaCount(SqlSessionTemplate session, String memberId, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memberId", memberId);
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("QnaMapper.selectMemberQnaCount", paramMap);
		return totalCount;
	}
	//회원별 qna게시판 리스트 페이징
	@Override
	public List<Qna> selectMemberQna(SqlSessionTemplate session, String memberId, int currentPage, int qnaLimit) {
		int offset = (currentPage - 1) * qnaLimit;
		RowBounds rowBounds = new RowBounds(offset, qnaLimit);
		List<Qna> qList = session.selectList("QnaMapper.selectMemberQna", memberId, rowBounds);
		return qList;
	}

	@Override
	public Qna selectOneByNo(SqlSessionTemplate session, Integer qnaNo) {
		Qna qna = session.selectOne("QnaMapper.selectOneByNo", qnaNo);
		return qna;
	}

	@Override
	public int deleteOneByNo(SqlSessionTemplate session, Integer qnaNo) {
		int result = session.delete("QnaMapper.deleteOneByNo", qnaNo);
		return result;
	}

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
	public int selectAllCount(SqlSessionTemplate session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("QnaMapper.selectAllQnaCount", paramMap);
		return totalCount;
	}

	//관리자 Qna게시판 리스트 페이징 
	@Override
	public List<Qna> selectAllQna(SqlSessionTemplate session, int currentPage, int aqnaLimit) {
		int offset = (currentPage-1)*aqnaLimit;
		RowBounds rowBounds = new RowBounds(offset, aqnaLimit);
		List<Qna> aList 
		= session.selectList("QnaMapper.selectAllQna"
				, null, rowBounds);
		return aList;
	}
	
	@Override
	public int answerQna(SqlSessionTemplate session, Qna qna) {
		int  result = session.update("QnaMapper.insertAnswer", qna);
		return result;
	}
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



}
