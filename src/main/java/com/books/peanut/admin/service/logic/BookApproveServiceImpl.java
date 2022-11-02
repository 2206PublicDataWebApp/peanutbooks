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
	
	// 전체 회원 갯수
	@Override
	public int allOriSeriesCount() {
		int count = BAStore.selectAllOriSeries(session);
		return count;
	}
	// 전체 회원 리스트 출력
	@Override
	public List<OriginBookSeries> allOriSeries(int i, int boardLimit) {
		List<OriginBookSeries> osList = BAStore.selectAllOriSeries(session, i, boardLimit);
		return osList;
	}

	//승인된 게시물 갯수
	@Override
	public int checkPermissionCount(String checkPermission) {
		int count = BAStore.selectCheckPermissionCount(session, checkPermission);
		return count;
	}
	//승인된 게시물 리스트 출력
	@Override
	public List<OriginBookSeries> checkPermission(int i, int boardLimit, String checkPermission) {
		List<OriginBookSeries> osList = BAStore.selectCheckPermissionList(session, i, boardLimit, checkPermission);
		return osList;
	}
	//재승인된 게시물 갯수
	@Override
	public int reApproveCount() {
		int count = BAStore.selectAllReAppCount(session);
		return count;
	}
	//재승인 리스트 출력
	@Override
	public List<ModifyBookSeries> reApproveList(int currentPage, int boardLimit) {
		List<ModifyBookSeries> mbList = BAStore.selectAllReAppList(session, currentPage, boardLimit);
		return mbList;
	}
	//책 승인하기
	@Override
	public int approveBooks(Integer bookNo, Integer seriesNo) {
		int result = BAStore.updateApproveBooks(session, bookNo, seriesNo);
		return result;
	}
	//책 재승인하기
	@Override
	public int reApproveBooks(Integer bookNo, Integer seriesNo) {
		int result = BAStore.updateReApproveBooks(session, bookNo, seriesNo);
		return result;
	}

	//전체 회원 갯수
	@Override
	public int allMemberCount(String searchCondition, String searchValue) {
		int count = BAStore.selectAllMember(session, searchCondition, searchValue);
		return count;
	}
	//전체 회원 리스트 출력
	@Override
	public List<Member> allMembers(int i, int memberLimit) {
		List<Member> mList = BAStore.selectAllMembers(session, i, memberLimit);
		return mList;
	}
	//회원상세 페이지
	@Override
	public Member printOneById(String memberId) {
		Member member = BAStore.selectOneById(session, memberId);
		return member;
	}
	//회원삭제
	@Override
	public int removeOneById(String memberId) {
		int result = BAStore.deleteOneById(session, memberId);
		return result;
	}
	//회원 상세검색
	@Override
	public List<Member> searchMembers(String searchCondition, String searchValue, int i, int limit) {
		List<Member> mList = BAStore.selectSeachAllMember(session, searchCondition, searchValue, i, limit);
		return mList;
	}
	//회원정보 수정
	@Override
	public int modifyOnById(Member member) {
		int result = BAStore.updateMemberModify(session, member);
		return result;
	}






	


}
