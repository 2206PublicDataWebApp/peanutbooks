package com.books.peanut.pay.payService;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.pay.domain.Pay;
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

	@Override
	public int orderSuccess(String orderNo) {
		int result=pStore.orderSuccess(session,orderNo);
		return result;
	}

	@Override
	public int writerReceipt(WriterPay writerP) {
		int result=pStore.writerReceipt(session,writerP);
		return result;
	}

	@Override
	public List<WriterPay> wrListPrint() {
		List<WriterPay> wrList=pStore.wrListPrint(session);
		return wrList;
	}
	

	

}
