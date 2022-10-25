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

	@Override
	public int selectTotalCount(SqlSessionTemplate session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("QnaMapper.selectTotalCount", paramMap);
		return totalCount;
	}

	@Override
	public List<Qna> selectAllQna(SqlSessionTemplate session, String memberId, int currentPage, int qnaLimit) {
		int offset = (currentPage - 1) * qnaLimit;
		RowBounds rowBounds = new RowBounds(offset, qnaLimit);
		List<Qna> qList = session.selectList("QnaMapper.selectAllQna", memberId, rowBounds);
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

}
