package com.books.peanut.pay.payService;

import java.util.Date;
import java.util.List;

import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.Pagemarker;
import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;

public interface PayService {	

	public int orderin(Pay pay);

	public Pay orderNoOne(Pay pay);
//결제api성공
	public int orderSuccess(Pay payApi);

	public int writerReceipt(WriterPay writerP);

	public List<WriterPay> wrListPrint();

	public int peanutTableInput(PeanutPoint pp);

	public int seasonticketInput(SeasonTicket st);
	//member  구독권 y/n 변경
	public int memberStChange(String memberId);
	// 로그인시 구독권 여부 및 날짜 확인하는 부분
	public String seasonTicketDate(String memberId);
	//땅콩포인트 리스트
	public List<PeanutPoint> peanutList(String memberId,Pagemarker pm);
	//페이징 전체 갯수
	public int getTotalCount();
	//id별 땅콩 포인트 합계
	public int getPPsum(String memberId);
	//땅콩갯수 memberId넣기
	public void putMemberPoint(Member member);

}
