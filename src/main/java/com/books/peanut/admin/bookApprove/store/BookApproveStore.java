package com.books.peanut.admin.bookApprove.store;

import org.apache.ibatis.session.SqlSession;

public interface BookApproveStore {

	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);

}
