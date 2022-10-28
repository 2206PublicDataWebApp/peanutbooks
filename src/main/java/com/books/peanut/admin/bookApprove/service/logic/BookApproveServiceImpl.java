package com.books.peanut.admin.bookApprove.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.admin.bookApprove.service.BookApproveService;
import com.books.peanut.admin.bookApprove.store.BookApproveStore;

@Service
public class BookApproveServiceImpl implements BookApproveService {
	@Autowired
	private BookApproveStore bStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int getBooksTotalCount(String searchCondition, String searchValue) {
		int totalCount = bStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}


}
