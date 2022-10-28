package com.books.peanut.admin.bookApprove.store.logic;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.admin.bookApprove.store.BookApproveStore;
@Repository
public class BookApproveStoreLogic implements BookApproveStore{

	@Override
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("", paramMap);
		return totalCount;
	}

}
