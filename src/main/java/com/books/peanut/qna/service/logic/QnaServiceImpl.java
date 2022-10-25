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
	
	@Override
	public int registerQna(Qna qna) {
		int result = qStore.insertQna(session, qna);
		return result;
	}

	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = qStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}

	@Override
	public List<Qna> printAllQna(int currentPage, int qnaLimit, String memberId) {
		List<Qna> qList = qStore.selectAllQna(session, memberId, currentPage, qnaLimit);
		return qList;
	}

	@Override
	public Qna printOneByNo(Integer qnaNo) {
		Qna qna = qStore.selectOneByNo(session, qnaNo);
		return qna;
	}

	@Override
	public int removeOneByNo(Integer qnaNo) {
		int result = qStore.deleteOneByNo(session, qnaNo);
		return result;
	}

	@Override
	public int modifyQna(Qna qna) {
		int result = qStore.updateQna(session, qna);
		return result;
	}
	
}
