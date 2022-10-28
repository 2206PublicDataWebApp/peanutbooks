package com.books.peanut.admin.bookApprove.service;

import java.awt.print.Book;
import java.util.List;

public interface BookApproveService {
	
	//전체 도서물 개수 기능
	public int getBooksTotalCount(String searchCondition, String searchValue);
	//전체 도서물 출력 기능

}
