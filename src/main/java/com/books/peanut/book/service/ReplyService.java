package com.books.peanut.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookReply;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.domain.ReReply;
import com.books.peanut.book.domain.Star;


public interface ReplyService {

	/**피넛 오리지널 리플등록*/
	int registOneReply(OriginBookReply obReply);

	/**피넛오리지널 리플 불러오기
	 * @param boardLimit 
	 * @param currentPage */
	List<OriginBookReply> OriBookReply(String bookNo, int currentPage, int boardLimit);

	/**닉네임 가져오기*/
	String getMemberNickName(String memberId);

	/**피넛 오리지널 리플수 가져오기
	 * @param bookNo */
	int getTotalCount(String bookNo);

	/**리플 내용 가져오기*/
	String getOriOneReply(String rNo);

	/** 피넛 오리지널 리플 쓴 사람 체크*/
	String checkOriReplyMember(int rNo);

	/**피넛 오리지널 리플 수정*/
	int modifyReply(OriginBookReply obReply);
	
	/**피넛 오리지널 리플 삭제*/
	int removeOriReply(Integer rNo);

	/**별점주기*/
	int getStarScoreOrigin(Star star);

	/**별점취소*/
	int removeScore(Star star);

	
	/**피넛 오리지널 책 한권 가져오기*/
	OriginBook showOnebook(String bookNo);

	/**일반도서 한권가져오기*/
	NormalBook showOneNorBook(String bookNo);

	/**일반도서 리플갯수 가져오기*/
	int getTotalNorReplyCount(String bookNo);

	/**일반도서 리플 리스트 가져오기*/
	List<NormalBookReply> norBookReply(String bookNo, int currentPage, int boardLimit);

	/**일반도서 리플 등록하기*/
	int registNorOneReply(NormalBookReply nbReply);

	/**일반도서 리플쓴 사람이 맞는지 체크*/
	String checkNorReplyMember(int replyNo);

	/**일반도서 리플 수정하기*/
	int modifyNorReply(NormalBookReply nbReply);

	/**일반도서 리플 가져오기*/
	String getNorOneReply(String rNo);

	/**일반도서 리플삭제*/
	int removeNorReply(Integer rNo);

	/**리리플 등록*/
	int registOneReReply(ReReply rReply);

	/**리리플 불러오기*/
	List<ReReply> BookReReply(ReReply r);

	/**리리플 등록한 사람 찾기*/
	String checkReReplyMember(Integer rNo);

	/**리리플 삭제*/
	int removeReReply(Integer rNo);

	/**리리플 내용 가져오기*/
	String getReReply(String rNo);

	/**리리플 수정하기*/
	int modifyReReply(ReReply rReply);

	/**리리플 달렸는지 확인하기
	 * @param category */
	int checkReReply(Integer rNo, String category);

	/**피넛오리지널 댓글 삭제했다고 내용 변경하기*/
	int UpdateRemoveOriReply(Integer rNo);

	/**일반도서  댓글 삭제했다고 내용 변경하기*/
	int UpdateRemoveNorReply(Integer rNo);



}
