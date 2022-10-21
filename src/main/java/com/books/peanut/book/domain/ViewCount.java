package com.books.peanut.book.domain;

import java.sql.Date;

public class ViewCount {
private String bookNo;
private int seriesNo;
private String memberId;
private String seasonCheck;
private Date orderDate;
private String category;
public String getBookNo() {
	return bookNo;
}
public void setBookNo(String bookNo) {
	this.bookNo = bookNo;
}
public int getSeriesNo() {
	return seriesNo;
}
public void setSeriesNo(int seriesNo) {
	this.seriesNo = seriesNo;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public String getSeasonCheck() {
	return seasonCheck;
}
public void setSeasonCheck(String seasonCheck) {
	this.seasonCheck = seasonCheck;
}
public Date getOrderDate() {
	return orderDate;
}
public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
@Override
public String toString() {
	return "viewCount [bookNo=" + bookNo + ", seriesNo=" + seriesNo + ", memberId=" + memberId + ", seasonCheck="
			+ seasonCheck + ", orderDate=" + orderDate + ", category=" + category + "]";
}



}
