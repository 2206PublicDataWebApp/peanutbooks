package com.books.peanut.pay.domain;

public class Pagemarker {
	private int currentPage;//현재페이지	
	private int totalCount; // 전체게시물의 갯수가 필요함. 검색할때 같은 매소드를 사용하여 빈값을 준것임
	private int limit = 10; // 한페이지당 보여주고 싶은 게시물의 갯수 //이것은 프론트에서 Param에서 받아올수 있다.
	private int naviLimit = 5; // 몇개의 페이지씩 표기할것인가.....[이전]1 2 3 4 5[다음]
	private int maxPage;    //전체 페이지
	private int startNavi; //시작
	private int endNavi;  //끝	
	
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getNaviLimit() {
		return naviLimit;
	}
	public void setNaviLimit(int naviLimit) {
		this.naviLimit = naviLimit;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartNavi() {
		return startNavi;
	}
	public void setStartNavi(int startNavi) {
		this.startNavi = startNavi;
	}
	public int getEndNavi() {
		return endNavi;
	}
	public void setEndNavi(int endNavi) {
		this.endNavi = endNavi;
	}
	
	public void pageInfo(int currentPage, int totalCount) {
		this.currentPage= currentPage;	  //현재페이지		
		this.totalCount=totalCount;     //전체 게시물의 갯수   
		
		//전체 페이지  // 23/5 = 4.8+0.9=5(.7)=5 페이지 갯수구하는 것 무조건 올림해야한다.
		this.maxPage=(int) ((double) totalCount / limit + 0.9);   // 한페이지당 보여주고 싶은개 개시물은 10개씩    
		//시작 페이지
		this.startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
		//끝 페이지
		this.endNavi = startNavi + naviLimit - 1; // 처음과 끝만 알면 나머지는 FOR문이 한다.
		//게시판 시작번호	
		
		if(this.endNavi>this.maxPage){
			this.endNavi=this.maxPage;			
		}
	}
	
	@Override
	public String toString() {
		return "Pagemarker [currentPage=" + currentPage + ", totalCount=" + totalCount + ", limit=" + limit
				+ ", naviLimit=" + naviLimit + ", maxPage=" + maxPage + ", startNavi=" + startNavi + ", endNavi="
				+ endNavi + "]";
	}
	
}


