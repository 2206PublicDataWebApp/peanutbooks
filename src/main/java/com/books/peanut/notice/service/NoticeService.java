package com.books.peanut.notice.service;

import java.util.List;

import com.books.peanut.notice.domain.Notice;

public interface NoticeService {

	public int registeNotice(Notice notice);

	public int getTotalCount(String searchCondition, String searchValue);

	public List<Notice> printAllNotice(int currentPage, int boardLimit);

	public Notice printOneByNo(Integer noticeNo);

	public int removeOneByNo(int noticeNo);

	public int modifyNotice(Notice notice);


}
