package com.books.peanut.qna.service;

import java.util.List;

import com.books.peanut.qna.domain.Qna;

public interface QnaService {
	//회원 문의글 문의 작성
	public int registerQna(Qna qna);
	//회원 문의글 총게시물수 가져오기
	public int getTotalCount(String memberId, String searchCondition, String searchValue, String qnaStatus);
	//회원 문의글 총게시물 출력
	public List<Qna> printMemberQna(int currentPage, int qnaLimit, String memberId, String qnaStatus);
	//회원 문의글 상세페이지
	public Qna printOneByNo(Integer qnaNo);
	//회원 문의글 삭제
	public int removeOneByNo(Integer qnaNo);
	//회원 문의글 수정
	public int modifyQna(Qna qna);
	//회원 상세검색
	public List<Qna> printMemberByValue(String memberId, String searchCondition, String searchValue, int currentPage, int qnaLimit);
	//관리자 전체갯수 페이징
	public int getTotalCount(String searchCondition, String searchValue, String qnaStatus);
	//관리자화면에서 전체 qna게시물수 가져오기
	//public int getTotalCount(String searchCondition, String searchValue);
	//관리자 전체 qna게시물 출력
	public List<Qna> printAllQna(int currentPage, int aqnaLimit, String qnaStatus);
	//관리자 문의글 답변
	public int answerQna(Qna qna);
	//관리자 게시글 상세검색
	public List<Qna> printAllByValue(String searchCondition, String searchValue, int currentPage, int aqnaLimit);
	//관리자 카테고리별 리스트
	public List<Qna> printAllByCategory(String qnaCategory, int currentPage, int categoryLimit);
	//관리자 카테리고별 페이지 갯수
	public int getTotalCount(String qnaCategory);
	//회원 답변상황에 따른 리스트 카운트
	public int totalQna(String memberId);
	public int totalAnswer(String memberId);
	public int totalNoAnswer(String memberId);
	//관리자 답변상황에 따른 리스트 카운트
	public int totalQna();
	public int totalAnswer();
	public int totalNoAnswer();

	

}
