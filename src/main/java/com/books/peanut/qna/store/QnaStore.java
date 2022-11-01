package com.books.peanut.qna.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.qna.domain.Qna;

public interface QnaStore {

	public int insertQna(SqlSessionTemplate session, Qna qna);

	public List<Qna> selectMemberQna(SqlSessionTemplate session, String memberId, int currentPage, int qnaLimit);

	public int selectMemberQnaCount(SqlSessionTemplate session, String memberId, String searchCondition, String searchValue);

	public Qna selectOneByNo(SqlSessionTemplate session, Integer qnaNo);

	public int deleteOneByNo(SqlSessionTemplate session, Integer qnaNo);

	public int updateQna(SqlSessionTemplate session, Qna qna);

	public List<Qna> selectMemberByValue(SqlSessionTemplate session, String memberId, String searchCondition, String searchValue,
			int currentPage, int qnaLimit);

	public int selectAllCount(SqlSessionTemplate session, String searchCondition, String searchValue);

	public List<Qna> selectAllQna(SqlSessionTemplate session, int currentPage, int aqnaLimit);

	public int answerQna(SqlSessionTemplate session, Qna qna);

	public List<Qna> selectAllByValue(SqlSessionTemplate session, String searchCondition, String searchValue,
			int currentPage, int aqnaLimit);

	public List<Qna> selectAllByCategory(SqlSessionTemplate session, String qnaCategory, int currentPage,
			int categoryLimit);





}
