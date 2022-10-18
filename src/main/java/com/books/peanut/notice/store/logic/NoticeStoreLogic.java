package com.books.peanut.notice.store.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.notice.domain.Notice;
import com.books.peanut.notice.store.NoticeStore;

@Repository
public class NoticeStoreLogic implements NoticeStore {

	@Override
	public int insertNotice(SqlSessionTemplate session, Notice notice) {
		int result = session.insert("NoticeMapper.insertNotice", notice);
		return result;
	}

}

