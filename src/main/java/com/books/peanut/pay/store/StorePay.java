package com.books.peanut.pay.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.WriterPay;

public interface StorePay {

	public int orderIn(SqlSessionTemplate session, Pay pay);

	public Pay orderNoOne(SqlSessionTemplate session, Pay pay);

	public int orderSuccess(SqlSessionTemplate session,String orderNo);

	public int writerReceipt(SqlSessionTemplate session, WriterPay writerP);

	public List<WriterPay> wrListPrint(SqlSessionTemplate session);

}
