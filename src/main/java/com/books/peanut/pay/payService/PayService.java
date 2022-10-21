package com.books.peanut.pay.payService;

import java.util.List;

import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;

public interface PayService {

	public int orderin(Pay pay);

	public Pay orderNoOne(Pay pay);
//결제api성공
	public int orderSuccess(Pay payApi);

	public int writerReceipt(WriterPay writerP);

	public List<WriterPay> wrListPrint();

	public int peanutTableInput(PeanutPoint pp);

	public int seasonticketInput(SeasonTicket st);

}
