package com.books.peanut.qna.service;

import java.util.List;

import com.books.peanut.qna.domain.Qna;

public interface QnaService {
	//qna문의 작성
	public int registerQna(Qna qna);
	//qna 총게시물수 가져오기
	public int getTotalCount(String searchCondition, String searchValue);
	//qna 총게시물 출력
	public List<Qna> printAllQna(int currentPage, int qnaLimit, String memberId);
	//qna 상세페이지
	public Qna printOneByNo(Integer qnaNo);
	//Qna 삭제
	public int removeOneByNo(Integer qnaNo);
	//Qna 수정
	public int modifyQna(Qna qna);

}
