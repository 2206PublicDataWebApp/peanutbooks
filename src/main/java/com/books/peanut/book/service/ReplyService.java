package com.books.peanut.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.OriginBookReply;


public interface ReplyService {

	/**피넛 오리지널 리플등록*/
	int registOneReply(OriginBookReply obReply);

	/**피넛오리지널 리플 불러오기
	 * @param boardLimit 
	 * @param currentPage */
	List<OriginBookReply> OriBookReply(String bookNo, int currentPage, int boardLimit);

	/**닉네임 가져오기*/
	String getMemberNickName(String memberId);

	/**리플수 가져오기
	 * @param bookNo */
	int getTotalCount(String bookNo);

	/**리플 내용 가져오기*/
	String getOriOneReply(String rNo);

	/** 피넛 오리지널 리플 쓴 사람 체크*/
	String checkOriReplyMember(int rNo);

	
	int modifyReply(OriginBookReply obReply);

}
