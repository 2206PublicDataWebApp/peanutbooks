//package com.books.peanut.admin.common;
//
//public class Paging {
//	//생성자에 매개변수를 넣어주면 필요한 값들을 getter로 얻을 수 있다.
//	//1. 게시물 개수, 2.현재페이지, 3.페이지당 게시물수, 4.페이징네비사이즈
//	
//	private int totalCount;
//	private int currentPage;
//	private int pageLimit;   //10 한페이지당 출력될 게시물 수
//	private int naviLimit;	//5 한 화면에 출력할 게시판 페이지 수
//	private int maxPage; 	//총게시물수
//	private int startNavi;  //한 화면에 출력되는 게시판 페이지의 처음 수
//	private int endNavi;  	//한 화면에 출력되는 게시판 페이지의 마지막 수
//	private int offset;
//	
//	public Paging() {}
//	
//	public Paging(int totalCount, int currentPage, int pageLimit, int naviLimit) {
//		super();
//		this.totalCount = totalCount;
//		this.currentPage = currentPage;
//		this.pageLimit = pageLimit;
//		this.naviLimit = naviLimit;
//
//		startNavi = 1;
//		maxPage = (int) ((double) totalCount / pageLimit + 0.9);
//		startNavi = ((int)((double)currentPage / naviLimit+0.9)-1) * naviLimit + 1;
//		endNavi = startNavi + naviLimit - 1;
//		if (maxPage < endNavi) {
//			endNavi = maxPage;
//		}
//		if(currentPage < 1) {
//			currentPage = 1;
//		}
//		if(currentPage > endNavi) {
//			currentPage = endNavi;
//		}
//		
//		offset = (currentPage - 1) * pageLimit;
//	}
//
//	public int getTotalCount() {
//		return totalCount;
//	}
//
//	public void setTotalCount(int totalCount) {
//		this.totalCount = totalCount;
//	}
//
//	public int getPage() {
//		return currentPage;
//	}
//
//	public void setPage(int currentPage) {
//		this.currentPage = currentPage;
//	}
//
//	public int getPageLimit() {
//		return pageLimit;
//	}
//
//	public void setPageLimit(int pageLimit) {
//		this.pageLimit = pageLimit;
//	}
//
//	public int getNaviLimit() {
//		return naviLimit;
//	}
//
//	public void setNaviLimit(int naviLimit) {
//		this.naviLimit = naviLimit;
//	}
//
//	public int getMaxPage() {
//		return maxPage;
//	}
//
//	public void setMaxPage(int maxPage) {
//		this.maxPage = maxPage;
//	}
//
//	public int getStartNavi() {
//		return startNavi;
//	}
//
//	public void setStartNavi(int startNavi) {
//		this.startNavi = startNavi;
//	}
//
//	public int getEndNavi() {
//		return endNavi;
//	}
//
//	public void setEndNavi(int endNavi) {
//		this.endNavi = endNavi;
//	}
//
//	public int getOffset() {
//		return offset;
//	}
//
//	public void setOffset(int offset) {
//		this.offset = offset;
//	}
//
//	@Override
//	public String toString() {
//		return "Paging [totalCount=" + totalCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit + ", naviLimit="
//				+ naviLimit + ", maxPage=" + maxPage + ", startNavi=" + startNavi + ", endNavi=" + endNavi + ", offset="
//				+ offset + "]";
//	}
//
//	
//
//}