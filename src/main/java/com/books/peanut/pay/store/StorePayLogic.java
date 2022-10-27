package com.books.peanut.pay.store;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.Pagemarker;
import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;


@Repository
public class StorePayLogic implements StorePay{
	
	@Override
	public int orderIn(SqlSessionTemplate session, Pay pay) {
		int result=session.insert("payPoint_Mapper.insertPay",pay);
		return result;
	}
	
	@Override
	public Pay orderNoOne(SqlSessionTemplate session, Pay pay) {
		Pay payone=session.selectOne("payPoint_Mapper.selectOdNo",pay);
		return payone;
	}
//결제 api 성공
	@Override
	public int orderSuccess(SqlSessionTemplate session, Pay payApi) {
		int result=session.update("payPoint_Mapper.updataOrder",payApi);
		return result;
	}
	//작가료 정산접수
	@Override
	public int writerReceipt(SqlSessionTemplate session, WriterPay writerP) {
		int result=session.insert("payPoint_Mapper.insertreceiptWP",writerP);
		return result;
	}

	@Override
	public List<WriterPay> wrListPrint(SqlSessionTemplate session) {
		List<WriterPay> wrList=session.selectList("payPoint_Mapper.selectwrList");
		return wrList;
	}
	//peanetpoint table입력
	@Override
	public int peanutTableInput(SqlSessionTemplate session,PeanutPoint pp) {
		int p_t_input=session.insert("payPoint_Mapper.insertPeanut",pp);		
		return p_t_input;
	}

	//seasomticket table입력
	@Override
	public int seasonticketInput(SqlSessionTemplate session, SeasonTicket st) {

		int p_t_input = session.insert("payPoint_Mapper.insertSSticket",st);
		return p_t_input;
	}
	//member  구독권 y/n 변경
	@Override
	public int memberStChange(SqlSessionTemplate session,String memberId) {
		int m_st_YN = session.update("payPoint_Mapper.updateMemberSSticket",memberId);
		return m_st_YN;
	}
	// 로그인시 구독권 여부 및 날짜 확인하는 부분
	@Override
	public String seasonTicketDate(SqlSessionTemplate session, String memberId) {
		String lastDate = session.selectOne("payPoint_Mapper.selectLastSSticket",memberId);		
		return lastDate;		
	}
	//땅콩포인트 리스트
	@Override
	public List<PeanutPoint> peanutList(SqlSessionTemplate session, String memberId,Pagemarker pm) {
		int offset=(pm.getCurrentPage()-1)*pm.getLimit();		
		RowBounds rowBounds = new RowBounds(offset,pm.getLimit());
		//null, rowBounds 같이 진행해줘야 자동으로 처리된다.
		HashMap<String, String > paramMap=new HashMap<String, String>();
		paramMap.put("memberId",memberId);
		List<PeanutPoint> pList=session.selectList("payPoint_Mapper.peanutpointLsit", paramMap ,rowBounds);
		return pList;
	}
	//페이징 전체 갯수
	@Override
	public int getTotalCount(SqlSessionTemplate session,String memberId) {
		int num=session.selectOne("payPoint_Mapper.ppListCount",memberId);
		return num;
	}
	//id별 땅콩 포인트 합계
	@Override
	public int getPPsum(SqlSessionTemplate session, String memberId) {
		Integer ppSum = session.selectOne("payPoint_Mapper.idppSum", memberId);
		if(ppSum==null) {
			ppSum=0;			
		}
		return ppSum;
	}
	//포인트 계산하기
	@Override
	public void putMemberPoint(SqlSessionTemplate session, Member member) {
		session.update("payPoint_Mapper.memberPoint",member);
	}
	//작가정산위한 도서 리스트 확인
	@Override
	public List<OriginBook> originListGet(SqlSessionTemplate session, String memberId) {
		List<OriginBook> obList=session.selectList("payPoint_Mapper.OriginBookNo",memberId);
		return obList;
	}
	//도서번호로 시리즈 조회
	@Override
	public List<OriginBookSeries> findSeriseNo(SqlSessionTemplate session, OriginBookSeries obs) {
		List<OriginBookSeries> obsList= session.selectList("payPoint_Mapper.origin_B_S_list", obs);
		return obsList;
	}
	//지급접수후 포인트 차감
	@Override
	public int updatePaidCount(SqlSessionTemplate session, WriterPay writerP) {
		int num=session.update("payPoint_Mapper.updatePaid_count",writerP);
		return num;
	}	

}
