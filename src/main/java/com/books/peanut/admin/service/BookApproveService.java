package com.books.peanut.admin.service;

import java.util.List;

import com.books.peanut.admin.domain.ModifyBookSeries;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;

public interface BookApproveService {
	
	//전체 도서물 갯수
	public int allOriSeriesCount();
	//전체 도서물 리스트 출력
	public List<OriginBookSeries> allOriSeries(int i, int boardLimit);
	//승인보류 게시물 갯수
	public int checkPermissionCount(String checkPermission);
	//승인보류 게시물 리스트 출력
	public List<OriginBookSeries> checkPermission(int currentPage, int boardLimit, String checkPermission);
	//재승인 게시물 갯수
	public int reApproveCount();
	//재승인 게시물 리스트 출력
	public List<ModifyBookSeries> reApproveList(int currentPage, int boardLimit);
	
	//도서 승인하기
	public int approveBooks(Integer bookNo, Integer seriesNo);
	//도서 재승인하기
	public int reApproveBooks(Integer bookNo, Integer seriesNo);
	
	
	//전체 회원 갯수
	public int allMemberCount(String searchCondition, String searchValue);
	//전체 회원 리스트 출력
	public List<Member> allMembers(int currentPage, int memberLimit);
	//회원 상세 보기
	public Member printOneById(String memberId);
	//회원 삭제
	public int removeOneById(String memberId);
	//회원 상세검색
	public List<Member> searchMembers(String searchCondition, String searchValue, int currentPage, int memberLimit);
	//회원 정보 수정
	public int modifyOnById(Member member);

	
	


	
	
	
	

}