package com.books.peanut.admin.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.admin.domain.ModifyBookSeries;
import com.books.peanut.admin.service.BookApproveService;
import com.books.peanut.admin.store.BookApproveStore;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;


@Service
public class BookApproveServiceImpl implements BookApproveService {
	@Autowired
	private BookApproveStore BAStore;
	@Autowired
	private SqlSession session;
	
	@Override
	public int allOriSeriesCount() {
		int count = BAStore.selectAllOriSeries(session);
		return count;
	}

	@Override
	public List<OriginBookSeries> allOriSeries(int i, int boardLimit) {
		List<OriginBookSeries> osList = BAStore.selectAllOriSeries(session, i, boardLimit);
		return osList;
	}

	@Override
	public int checkPermissionCount(String checkPermission) {
		int count = BAStore.selectCheckPermissionCount(session, checkPermission);
		return count;
	}
	
	@Override
	public List<OriginBookSeries> checkPermission(int i, int boardLimit, String checkPermission) {
		List<OriginBookSeries> osList = BAStore.selectCheckPermissionList(session, i, boardLimit, checkPermission);
		return osList;
	}
	
	@Override
	public int reApproveCount() {
		int count = BAStore.selectAllReAppCount(session);
		return count;
	}

	@Override
	public int approveBooks(Integer bookNo, Integer seriesNo) {
		int result = BAStore.updateApproveBooks(session, bookNo, seriesNo);
		return result;
	}
	
	@Override
	public List<ModifyBookSeries> reApproveList(int currentPage, int boardLimit) {
		List<ModifyBookSeries> mbList = BAStore.selectAllReAppList(session, currentPage, boardLimit);
		return mbList;
	}

	@Override
	public int allMemberCount() {
		int count = BAStore.selectAllMember(session);
		return count;
	}

	@Override
	public List<Member> allMembers(int i, int memberLimit) {
		List<Member> mList = BAStore.selectAllMembers(session, i, memberLimit);
		return mList;
	}

	@Override
	public Member printOneById(String memberId) {
		Member member = BAStore.selectOneById(session, memberId);
		return member;
	}

	@Override
	public int removeOneById(String memberId) {
		int result = BAStore.deleteOneById(session, memberId);
		return result;
	}

	//책이름가져오기
	@Override
	public String getBookTitle(String bookNo) {
		String bookTitle = BAStore.selectBookTitle(bookNo, session);
		return bookTitle;
	}



	


}
