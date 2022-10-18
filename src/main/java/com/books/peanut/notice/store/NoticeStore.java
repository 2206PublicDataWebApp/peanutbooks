package com.books.peanut.notice.store;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.notice.domain.Notice;

public interface NoticeStore {

	int insertNotice(SqlSessionTemplate session, Notice notice);

}
