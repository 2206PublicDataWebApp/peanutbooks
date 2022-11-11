package com.books.peanut.admin.controller;

import com.books.peanut.book.domain.BookPage;

public class PageController {
	
	
	public BookPage boardList(Integer page, int totalCount, int boardLimit) {
		// @RequestParam(value="page", required=false)의 값은 page이지만
		// required=false 필수값은 아니라는 뜻

		int currentPage = (page != null) ? page : 1;
		// 현재 페이지
		// 만약 page값이 없으면 기본형 1로 출력할것, 아니면 받아온 page의 값을 준다.
		// 삼항연상자 사용

		int naviLimit = 5; // 한 화면에 출력할 게시판 페이지 수
		int maxPage; // 게시판의 총 페이지 수
		int startNavi; // 한 화면에 출력되는 게시판 페이지의 처음 수
		int endNavi;// 한 화면에 출력되는 게시판 페이지의 마지막 수

		maxPage = (int) ((double) totalCount / boardLimit + 0.9);
		startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
		endNavi = startNavi + naviLimit - 1;

		// endNavi가 maxNavi보다 커지는 오류방지
		if (maxPage < endNavi) {
			endNavi = maxPage;
		}

		BookPage bPage = new BookPage();
		bPage.setCurrentPage(currentPage);
		bPage.setEndNavi(endNavi);;
		bPage.setMaxPage(maxPage);;
		bPage.setStartNavi(startNavi);;
		
		return bPage;
	}
}
