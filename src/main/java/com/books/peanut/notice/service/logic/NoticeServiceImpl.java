package com.books.peanut.notice.service.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.notice.domain.Notice;
import com.books.peanut.notice.service.NoticeService;
import com.books.peanut.notice.store.NoticeStore;



@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	NoticeStore nStore;
	@Autowired
	SqlSessionTemplate session;

	@Override
	public int registeNotice(Notice notice) {
		int result = nStore.insertNotice(session, notice);
		return result;
	}

}
