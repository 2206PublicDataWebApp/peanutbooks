package com.books.peanut.admin.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.books.peanut.admin.domain.ModifyBookSeries;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;

public interface BookApproveStore {

	//피넛 오리지널의 갯수 파악
	public int selectAllOriSeries(SqlSession session);

	public List<OriginBookSeries> selectAllOriSeries(SqlSession session, int i, int boardLimit);
	//승인, 보류 리스트 출력
	public int selectCheckPermissionCount(SqlSession session, String checkPermission);
	
	public List<OriginBookSeries> selectCheckPermissionList(SqlSession session, int i, int boardLimit,
			String checkPermission);
	//책 승인
	public int updateApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo);
	//책 재승인
	public int updateReApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo);

	public int selectAllMember(SqlSession session);

	public List<Member> selectAllMembers(SqlSession session, int i, int memberLimit);

	public Member selectOneById(SqlSession session, String memberId);

	public int deleteOneById(SqlSession session, String memberId);

	public int selectAllReAppCount(SqlSession session);

	public List<ModifyBookSeries> selectAllReAppList(SqlSession session, int currentPage, int boardLimit);

	




}
