package com.books.peanut.pay.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;

public interface StorePay {

	public int orderIn(SqlSessionTemplate session, Pay pay);

	public Pay orderNoOne(SqlSessionTemplate session, Pay pay);
//결제api성공
	public int orderSuccess(SqlSessionTemplate session,Pay payApi);

	public int writerReceipt(SqlSessionTemplate session, WriterPay writerP);

	public List<WriterPay> wrListPrint(SqlSessionTemplate session);
	//peanetpoint table입력
	public int peanutTableInput(SqlSessionTemplate session,PeanutPoint pp);
	//seasonticket table입력
	public int seasonticketInput(SqlSessionTemplate session, SeasonTicket st);

}
