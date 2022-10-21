package com.books.peanut.pay.store;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

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
//	public int peanutTableInput(SqlSessionTemplate session,String orderNo, String memberId, Integer peanet_point) {
//		HashMap<String, String> hMap=new HashMap<String, String>();
//		hMap.put("orderNo",orderNo);
//		hMap.put("memberId",memberId);
//		hMap.put("peanet_point",String.valueOf(peanet_point));
		int p_t_input=session.insert("payPoint_Mapper.insertPeanut",pp);
		
		return p_t_input;
	}

	//seasomticket table입력
	@Override
	public int seasonticketInput(SqlSessionTemplate session, SeasonTicket st) {
//		HashMap<String, String> hMap=new HashMap<String, String>();
//		hMap.put("orderNo",orderNo);
//		hMap.put("memberId",memberId);
		int p_t_input=session.insert("payPoint_Mapper.insertSSticket",st);
		return p_t_input;
	}
	

}
