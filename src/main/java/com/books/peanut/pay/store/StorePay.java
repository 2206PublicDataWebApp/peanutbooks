package com.books.peanut.pay.store;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.Pagemarker;
import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;

public interface StorePay {

	public int orderIn(SqlSessionTemplate session, Pay pay);

	public Pay orderNoOne(SqlSessionTemplate session, Pay pay);
	//결제api성공
	public int orderSuccess(SqlSessionTemplate session,Pay payApi);
	//작가료 정산접수
	public int writerReceipt(SqlSessionTemplate session, WriterPay writerP);

	public List<WriterPay> wrListPrint(SqlSessionTemplate session);
	//peanetpoint table입력
	public int peanutTableInput(SqlSessionTemplate session,PeanutPoint pp);
	//seasonticket table입력
	public int seasonticketInput(SqlSessionTemplate session, SeasonTicket st);
	//member  구독권 y/n 변경
	public int memberStChange(SqlSessionTemplate session,String memberId);
	// 로그인시 구독권 여부 및 날짜 확인하는 부분
	public String seasonTicketDate(SqlSessionTemplate session, String memberId);
	//땅콩포인트 리스트
	public List<PeanutPoint> peanutList(SqlSessionTemplate session, String memberId,Pagemarker pm);
	//페이징 전체 갯수
	public int getTotalCount(SqlSessionTemplate session,String memberId);
	//id별 땅콩 포인트 합계
	public int getPPsum(SqlSessionTemplate session, String memberId);
	//땅콩갯수 memberId넣기
	public void putMemberPoint(SqlSessionTemplate session, Member member);
	//작가정산위한 도서 리스트 확인
	public List<OriginBook> originListGet(SqlSessionTemplate session, String memberId);
	//도서번호로 시리즈 조회
	public List<OriginBookSeries> findSeriseNo(SqlSessionTemplate session, OriginBookSeries obs);
	//지급접수후 포인트 차감
	public int updatePaidCount(SqlSessionTemplate session, WriterPay writerP);

}
