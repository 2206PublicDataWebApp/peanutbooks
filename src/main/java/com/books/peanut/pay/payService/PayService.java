package com.books.peanut.pay.payService;

import java.util.List;

import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.WriterPay;

public interface PayService {

	public int orderin(Pay pay);

	public Pay orderNoOne(Pay pay);

	public int orderSuccess(String orderNo);

	public int writerReceipt(WriterPay writerP);

	public List<WriterPay> wrListPrint();

}
