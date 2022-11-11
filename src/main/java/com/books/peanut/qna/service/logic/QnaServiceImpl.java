package com.books.peanut.qna.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.qna.domain.Qna;
import com.books.peanut.qna.service.QnaService;
import com.books.peanut.qna.store.QnaStore;
@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	private QnaStore qStore;
	@Autowired
	private SqlSessionTemplate session;
	//게시물 입력
	@Override
	public int registerQna(Qna qna) {
		int result = qStore.insertQna(session, qna);
		return result;
	}
	//회원별 게시물 상세검색 출력갯수
	@Override
	public int getTotalCount(String memberId, String searchCondition, String searchValue, String qnaStatus) {
		int totalCount = qStore.selectMemberQnaCount(session, memberId, searchCondition, searchValue, qnaStatus);
		return totalCount;
	}
	//관리자 게시물 검색 출력갯수
	/*
	 * @Override public int getTotalCount(String searchCondition, String
	 * searchValue, String qnaStatus) { int totalCount =
	 * qStore.selectAdminQnaCount(session, searchCondition, searchValue, qnaStatus);
	 * return totalCount; }
	 */
	//회원별 게시물 출력
	@Override
	public List<Qna> printMemberQna(int currentPage, int qnaLimit, String memberId, String qnaStatus) {
		List<Qna> qList = qStore.selectMemberQna(session, memberId, currentPage, qnaLimit, qnaStatus);
		return qList;
	}
	//게시물 상세보기
	@Override
	public Qna printOneByNo(Integer qnaNo) {
		Qna qna = qStore.selectOneByNo(session, qnaNo);
		return qna;
	}
	//게시물 삭제
	@Override
	public int removeOneByNo(Integer qnaNo) {
		int result = qStore.deleteOneByNo(session, qnaNo);
		return result;
	}
	//게시물 수정
	@Override
	public int modifyQna(Qna qna) {
		int result = qStore.updateQna(session, qna);
		return result;
	}
	//회원별 게시물 출력
	@Override
	public List<Qna> printMemberByValue(String memberId, String searchCondition, String searchValue, int currentPage, int qnaLimit) {
		List<Qna> qList = qStore.selectMemberByValue(session, memberId, searchCondition, searchValue, currentPage, qnaLimit);
		return qList;
	}
	//게시물 전체 갯수
	@Override
	public int getTotalCount(String searchCondition, String searchValue, String qnaStatus) {
		int totalCount = qStore.selectAllCount(session, searchCondition, searchValue, qnaStatus);
		return totalCount;
	}
	//게시물 전체 출력
	@Override
	public List<Qna> printAllQna(int currentPage, int aqnaLimit, String qnaStatus) {
		List<Qna> aList = qStore.selectAllQna(session, currentPage, aqnaLimit, qnaStatus);
		return aList;
	}
	//관리자 회원문의글 답변
	@Override
	public int answerQna(Qna qna) {
		int result = qStore.answerQna(session, qna);
		return result;
	}
	//상세검색
	@Override
	public List<Qna> printAllByValue(String searchCondition, String searchValue, int currentPage, int aqnaLimit) {
		List<Qna> aList = qStore.selectAllByValue(
				session, searchCondition, searchValue, currentPage, aqnaLimit);
		return aList;
	}
	//카테고리별 리스트
	@Override
	public List<Qna> printAllByCategory(
			String qnaCategory
			, int currentPage
			, int categoryLimit) {
		List<Qna> aList = qStore.selectAllByCategory(session
				, qnaCategory, currentPage, categoryLimit);
		return aList;
	}
	//카테고리별 리스트 갯수
	@Override
	public int getTotalCount(String qnaCategory) {
		int result = qStore.selectCategoryCount(session, qnaCategory);
		return result;
	}
	//회원 답변상황에 따른 리스트 카운트
	@Override
	public int totalQna(String memberId) {
		int result = qStore.selectTotalQna(session, memberId);
		return result;
	}
	@Override
	public int totalAnswer(String memberId) {
		int result = qStore.selectTotalAnswer(session,memberId);
		return result;
	}
	@Override
	public int totalNoAnswer(String memberId) {
		int result = qStore.selectTotalNoAnswer(session, memberId);
		return result;
	}
	//관리자 답변상황에 따른 리스트 카운트
	@Override
	public int totalQna() {
		int result = qStore.selectTotalQna(session);
		return result;
	}
	@Override
	public int totalAnswer() {
		int result = qStore.selectTotalAnswer(session);
		return result;
	}
	@Override
	public int totalNoAnswer() {
		int result = qStore.selectTotalNoAnswer(session);
		return result;
	}


}
