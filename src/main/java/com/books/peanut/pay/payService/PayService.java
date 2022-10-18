package com.books.peanut.pay.payService;

import com.books.peanut.pay.domain.Pay;

public interface PayService {

	public int orderin(Pay pay);

	public Pay orderNoOne(Pay pay);

	public int orderSuccess(String orderNo);

}
