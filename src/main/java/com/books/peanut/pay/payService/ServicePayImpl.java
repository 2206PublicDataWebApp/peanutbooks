package com.books.peanut.pay.payService;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.Pagemarker;
import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;
import com.books.peanut.pay.store.StorePay;

@Service
public class ServicePayImpl implements PayService {
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private StorePay pStore;
	
	@Override
	public int orderin(Pay pay) {
		int result=pStore.orderIn(session, pay);
		return result;
	}

	@Override
	public Pay orderNoOne(Pay pay) {
		Pay payOne=pStore.orderNoOne(session ,pay);
		return payOne;
	}
	//결제 api 성공
	@Override
	public int orderSuccess(Pay payApi) {
		int result=pStore.orderSuccess(session,payApi);
		return result;
	}
	//작가료 정산접수
	@Override
	public int writerReceipt(WriterPay writerP) {
		int result=pStore.writerReceipt(session,writerP);
		return result;
	}
	//peanetpoint table입력
	@Override
	public int peanutTableInput(PeanutPoint pp) {
		int p_t_input=pStore.peanutTableInput(session,pp);
		return p_t_input;
	}
	//seasonticket table입력
	@Override
	public int seasonticketInput(SeasonTicket st) {
		int p_t_input=pStore.seasonticketInput(session,st);
		return p_t_input;
	}
	//member  구독권 y/n 변경
	@Override
	public int memberStChange(String memberId) {
		int m_st_YN=pStore.memberStChange(session,memberId);
		return m_st_YN;
	}
	// 로그인시 구독권 여부 및 날짜 확인하는 부분
	@Override
	public String seasonTicketDate(String memberId) {
		String lastDate = pStore.seasonTicketDate(session,memberId);		
		return lastDate;		
	}
	//관리자 회원정보 수정-//구독권 반영  및 member 반영
	@Override
	public int modifyseasonTK(SeasonTicket st) {
		int subYN_result = pStore.modifyseasonTK(session, st);
		return subYN_result;
	}

/////////////////////////////////////땅콩관련
	
	//관리자 검색시 땅콩 포인트 합계
	@Override
	public int searchPNsum(String memberId) {
		int ppSum = pStore.searchPNsum(session, memberId);
		return ppSum;
	}
	//검색시 땅콩포인트페이징 전체 갯수
	@Override
	public int searchPNcount(String memberId, String ppDate) {
		int num=pStore.searchPNcount(session, memberId, ppDate);
		return num;
	}
	//관리자 검색시 땅콩포인트 리스트
	@Override
	public List<PeanutPoint> getPNList(String memberId, String ppDate, Pagemarker pm) {
		List<PeanutPoint> pList=pStore.getPNList(session, memberId, ppDate, pm);
		return pList;
	}
	//로그인시 id별 땅콩 포인트 합계
	@Override
	public int getPNsum(String memberId) {
		int ppSum = pStore.getPNsum(session, memberId);
		return ppSum;
	}
	//id별 땅콩포인트페이징 전체 갯수
	@Override
	public int getPNcount(String memberId, String ppDate) {
		int num=pStore.getPNcount(session, memberId, ppDate);
		return num;
	}
	//id별 땅콩포인트 리스트
		@Override
		public List<PeanutPoint> searchPNList(String memberId, String ppDate, Pagemarker pm) {
			List<PeanutPoint> pList=pStore.searchPNList(session, memberId, ppDate,pm);
			return pList;
		}
	//땅콩갯수 memberId넣기
	@Override
	public void putMemberPoint(Member member) {
		pStore.putMemberPoint(session, member);		
	}
	//관리자 땅콩 포인트 추가/ 삭제시
	@Override
	public int modifyPNpoint(PeanutPoint pp) {
		int pn_P_result=pStore.modifyseasonTK(session, pp);
		return pn_P_result;
	}
	
///////////////////////////////////작가정산관련
	
	//작가정산위한 도서 리스트 확인
	@Override
	public List<OriginBook> originListGet(String memberId) {
		List<OriginBook> obList = pStore.originListGet(session, memberId);
		return obList;
	}
	//도서번호로 시리즈 조회
	@Override
	public List<OriginBookSeries> findSeriseNo(OriginBookSeries obs) {
		List<OriginBookSeries> obsList= pStore.findSeriseNo(session, obs);
		return obsList;
	}
	//지급접수후 포인트 차감
	@Override
	public int updatePaidCount(WriterPay writerP) {
		int num = pStore.updatePaidCount(session, writerP);
		return num;
	}
	//작가 정산리스트 전체갯수 구하기
	@Override
//	public int getwritetP_Count() {
//		int count=pStore.getwritetP_Count(session);
//		return count;
//	}
	public int getwritetP_Count(String memberId, String bankStatus, String putDate) {
		int count=pStore.getwritetP_Count(session,memberId, bankStatus, putDate);
		return count;
	}
	//작가 정산리스트 출력
	@Override
	public List<WriterPay> wrListPrint(Pagemarker pm, String memberId, String bankStatus, String putDate) {
		List<WriterPay> wrList=pStore.wrListPrint(session,pm,memberId, bankStatus, putDate);
		return wrList;
	}
	//작가 정산접수 관리자 승인처리
	@Override
	public int writerPayStatusOne(String wrpayNo) {
		int num=pStore.writerPayStatusOne(session, wrpayNo);
		return num;
	}
	//관리자 결제리스트 전체페이지갯수
	@Override
	public int getPayCount(String memberId, String startDate, String endDate) {
		int num=pStore.getPayCount(session, memberId, startDate, endDate);
		return num;
	}
	//관리자 결제리스트 조회
	@Override
	public List<Pay> payListsearch(Pagemarker pm, String memberId, String startDate, String endDate) {
		List<Pay> payList = pStore.payListsearch(session, pm,  memberId, startDate, endDate);
		return payList;
	}






	

	

}
