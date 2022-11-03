package com.books.peanut.pay.payService;

import java.util.Date;
import java.util.List;

import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
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

	public List<WriterPay> wrListPrint(Pagemarker pm);

	public int peanutTableInput(PeanutPoint pp);

	public int seasonticketInput(SeasonTicket st);
	//member  구독권 y/n 변경
	public int memberStChange(String memberId);
	// 로그인시 구독권 여부 및 날짜 확인하는 부분
	public String seasonTicketDate(String memberId);
	
/////////////////////땅콩
	//관리자 검색시 땅콩 포인트 합계
	public int searchPNsum(String memberId);
	//관리자 검색시 페이징 전체 갯수
	public int searchPNcount(String memberId, String ppDate);
	//관리자 검색시 땅콩포인트 리스트
	public List<PeanutPoint> searchPNList(String memberId,String ppDate,Pagemarker pm);
	
	//로그인시 id별 땅콩 포인트 합계
	public int getPNsum(String memberId);
	//id별 페이징 전체 갯수
	public int getPNcount(String memberId, String ppDate);
	//id별 땅콩포인트 리스트
	public List<PeanutPoint> getPNList(String memberId,String ppDate,Pagemarker pm);
	//땅콩갯수 memberId넣기
	public void putMemberPoint(Member member);
	
//////////////////////////////////작가료 지급
	//작가정산위한 도서 리스트 확인
	public List<OriginBook> originListGet(String memberId);
	//도서번호로 시리즈 조회
	public List<OriginBookSeries> findSeriseNo(OriginBookSeries obs);
	//지급접수후 포인트 차감
	public int updatePaidCount(WriterPay writerP);
	//작가 정산리스트 전체갯수 구하기
	public int getwritetP_Count();
	//작가 정산접수 관리자 승인처리
	public int writerPayStatusOne(String wrpayNo1);




}
