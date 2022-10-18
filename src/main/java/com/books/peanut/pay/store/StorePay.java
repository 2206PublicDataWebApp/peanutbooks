package com.books.peanut.pay.store;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.pay.domain.Pay;

public interface StorePay {

	public int orderIn(SqlSessionTemplate session, Pay pay);

	public Pay orderNoOne(SqlSessionTemplate session, Pay pay);

	public int orderSuccess(SqlSessionTemplate session,String orderNo);

}
