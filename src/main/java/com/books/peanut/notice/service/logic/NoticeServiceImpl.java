package com.books.peanut.notice.service.logic;

import java.util.List;

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

	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = nStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}

	@Override
	public List<Notice> printAllNotice(int currentPage, int noticeLimit) {
		List<Notice> nList = nStore.selectAllNotice(session, currentPage, noticeLimit);
		return nList;
	}

	@Override
	public Notice printOneByNo(Integer noticeNo) {
		Notice notice = nStore.selectOneByNo(session, noticeNo);
		int result = 0;
		if(notice != null) {
			result = nStore.updateNoticeCount(session, noticeNo);
		}
		return notice;
	}

	@Override
	public int removeOneByNo(int noticeNo) {
		int result = nStore.deleteOneByNo(session, noticeNo);
		return result;
	}

	@Override
	public int modifyNotice(Notice notice) {
		int result = nStore.updateNotice(session, notice);
		return result;
	}



}
