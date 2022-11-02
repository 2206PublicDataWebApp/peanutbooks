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
	// 피넛 오리지널의 리스트 출력
	public List<OriginBookSeries> selectAllOriSeries(SqlSession session, int i, int boardLimit);
	//승인, 보류 갯수 파악
	public int selectCheckPermissionCount(SqlSession session, String checkPermission);
	//승인, 보류 리스트 출력
	public List<OriginBookSeries> selectCheckPermissionList(SqlSession session, int i, int boardLimit,
			String checkPermission);
	//책 승인
	public int updateApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo);
	//책 재승인
	public int updateReApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo);
	//책 재승인 갯수 구하기
	public int selectAllReAppCount(SqlSession session);
	//책 재승인 리스트 출력
	public List<ModifyBookSeries> selectAllReAppList(SqlSession session, int currentPage, int boardLimit);
	
	
	//전체 회원 개수
	public int selectAllMember(SqlSession session, String searchCondition, String searchValue);
	//전체 회원 리스트 출력
	public List<Member> selectAllMembers(SqlSession session, int i, int memberLimit);
	//회원 상세보기
	public Member selectOneById(SqlSession session, String memberId);
	//회원 삭제
	public int deleteOneById(SqlSession session, String memberId);
	//회원 상세검색
	public List<Member> selectSeachAllMember(SqlSession session, String searchCondition, String searchValue, int i, int limit);
	//회원 정보수정
	public int updateMemberModify(SqlSession session, Member member);


	




}
