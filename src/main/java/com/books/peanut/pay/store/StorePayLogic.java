package com.books.peanut.pay.store;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.pay.domain.Pay;

@Repository
public class StorePayLogic implements StorePay{
	
	@Override
	public int orderIn(SqlSessionTemplate session, Pay pay) {
		int result=session.insert("payPoint_Mapper.insertPay",pay);
		return result;
	}
	
	@Override
	public Pay orderNoOne(SqlSessionTemplate session, Pay pay) {
		Pay payone=session.selectOne("payPoint_Mapper.selectOrderNo",pay);
		return payone;
	}

	@Override
	public int orderSuccess(SqlSessionTemplate session, String orderNo) {
		int result=session.update("payPoint_Mapper.updataOrder",orderNo);
		return result;
	}

}
