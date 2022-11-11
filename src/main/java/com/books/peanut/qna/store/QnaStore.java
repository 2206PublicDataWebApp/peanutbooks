package com.books.peanut.qna.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.qna.domain.Qna;

public interface QnaStore {
	//회원 게시물 입력
	public int insertQna(SqlSessionTemplate session, Qna qna);
	//회원 게시물 전체리스트
	public List<Qna> selectMemberQna(SqlSessionTemplate session, String memberId, int currentPage, int qnaLimit, String qnaStatus);
	//회원 게시물 전체갯수
	public int selectMemberQnaCount(SqlSessionTemplate session, String memberId, String searchCondition, String searchValue, String qnaStatus);
	//회원 게시물상세보기
	public Qna selectOneByNo(SqlSessionTemplate session, Integer qnaNo);
	//회원 게시물삭제
	public int deleteOneByNo(SqlSessionTemplate session, Integer qnaNo);
	//회원 게시물업데이트
	public int updateQna(SqlSessionTemplate session, Qna qna);
	//회원 문의게시판 상세검색
	public List<Qna> selectMemberByValue(SqlSessionTemplate session, String memberId, String searchCondition, String searchValue,
			int currentPage, int qnaLimit);

	//관리자 전체페이지 리스트갯수
	public int selectAllCount(SqlSessionTemplate session, String searchCondition, String searchValue, String qnaStatus);
	//관리자 전체페이지 리스트
	public List<Qna> selectAllQna(SqlSessionTemplate session, int currentPage, int aqnaLimit, String qnaStatus);
	//관리자 회원문의글 답변
	public int answerQna(SqlSessionTemplate session, Qna qna);
	//상세검색
	public List<Qna> selectAllByValue(SqlSessionTemplate session, String searchCondition, String searchValue,
			int currentPage, int aqnaLimit);
	//카테고리별 리스트
	public List<Qna> selectAllByCategory(SqlSessionTemplate session, String qnaCategory, int currentPage,
			int categoryLimit);
	//카테고리별 리스트 갯수
	public int selectCategoryCount(SqlSessionTemplate session, String qnaCategory);
	//회원 답변상황에 따른 리스트 카운트
	public int selectTotalQna(SqlSessionTemplate session, String memberId);
	public int selectTotalAnswer(SqlSessionTemplate session, String memberId);
	public int selectTotalNoAnswer(SqlSessionTemplate session, String memberId);
	//관리자 답변상황에 따른 리스트 카운트
	public int selectTotalQna(SqlSessionTemplate session);
	public int selectTotalAnswer(SqlSessionTemplate session);
	public int selectTotalNoAnswer(SqlSessionTemplate session);
	





}
