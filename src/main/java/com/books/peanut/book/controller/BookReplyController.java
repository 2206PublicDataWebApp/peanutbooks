package com.books.peanut.book.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookReply;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.Star;
import com.books.peanut.book.service.ReplyService;
import com.books.peanut.book.service.logic.BookServiceImpl;
import com.books.peanut.member.domain.Member;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Controller
public class BookReplyController {

	@Autowired
	ReplyService rService;

	/**
	 * 피넛오리지널 리플등록
	 * 
	 * @param mv
	 * @param obReply
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/originBookReplyRegist", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String registReply(@ModelAttribute OriginBookReply obReply, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");

		obReply.setMemberId(member.getMemberId());
		int result = rService.registOneReply(obReply);

		return result + "";
	}
	/**
	 * 일반도서 리플등록
	 * 
	 * @param mv
	 * @param obReply
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/norBookReplyRegist", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String registNorReply(@ModelAttribute NormalBookReply nbReply, HttpSession session) {
		
		Member member = (Member) session.getAttribute("loginMember");
		
		nbReply.setMemberId(member.getMemberId());
		int result = rService.registNorOneReply(nbReply);
		
		return result + "";
	}

	/**
	 * 피넛 오리지널 리플 불러오기
	 * 
	 * @param bookNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/originBookAllReply", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String AllReplyList(@RequestParam("bookNo") String bookNo,
			@RequestParam(value = "rPage", required = false) Integer rPage) {

		// 리플 수계산하기
		int totalCount = rService.getTotalCount(bookNo);
		// 페이지당 출력될 변수 메소드로 가져오기
		int boardLimit = 10;// 출력될 리플수
		String navi = boardList(rPage, totalCount, boardLimit);
		int startNavi = Integer.parseInt(navi.split(",")[0]);
		int endNavi = Integer.parseInt(navi.split(",")[1]);
		int maxPage = Integer.parseInt(navi.split(",")[2]);
		int currentPage = Integer.parseInt(navi.split(",")[3]);

		// 리플목록 가져오기
		List<OriginBookReply> obReply = rService.OriBookReply(bookNo, currentPage, boardLimit);

		obReply.get(0).setEndNavi(endNavi);
		obReply.get(0).setStartNavi(startNavi);
		obReply.get(0).setMaxPage(maxPage);
		obReply.get(0).setCurrentPage(currentPage);
		obReply.get(0).setTotalCount(totalCount);

		// 가져온 리플목록에 닉네임 추가하기
		for (int i = 0; i < obReply.size(); i++) {
			String mNickName = rService.getMemberNickName(obReply.get(i).getMemberId());
			obReply.get(i).setmNickName(mNickName);

		}

		// 가져온 리플목록 반환하기
		if (!obReply.isEmpty()) {

			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			return gson.toJson(obReply);

		}

		return null;

	}
	
	/**
	 * 일반도서 리플 불러오기
	 * 
	 * @param bookNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/norBookAllReply", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String AllnorReplyList(@RequestParam("bookNo") String bookNo,
			@RequestParam(value = "rPage", required = false) Integer rPage) {
		
		// 리플 수계산하기
		int totalCount = rService.getTotalNorReplyCount(bookNo);
		// 페이지당 출력될 변수 메소드로 가져오기
		int boardLimit = 10;// 출력될 리플수
		String navi = boardList(rPage, totalCount, boardLimit);
		int startNavi = Integer.parseInt(navi.split(",")[0]);
		int endNavi = Integer.parseInt(navi.split(",")[1]);
		int maxPage = Integer.parseInt(navi.split(",")[2]);
		int currentPage = Integer.parseInt(navi.split(",")[3]);
		
		// 리플목록 가져오기
		List<NormalBookReply> nbReply = rService.norBookReply(bookNo, currentPage, boardLimit);
		
		nbReply.get(0).setEndNavi(endNavi);
		nbReply.get(0).setStartNavi(startNavi);
		nbReply.get(0).setMaxPage(maxPage);
		nbReply.get(0).setCurrentPage(currentPage);
		nbReply.get(0).setTotalCount(totalCount);
		
		// 가져온 리플목록에 닉네임 추가하기
		for (int i = 0; i < nbReply.size(); i++) {
			String mNickName = rService.getMemberNickName(nbReply.get(i).getMemberId());
			nbReply.get(i).setmNickName(mNickName);
			
		}
		
		// 가져온 리플목록 반환하기
		if (!nbReply.isEmpty()) {
			
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			
			return gson.toJson(nbReply);
			
		}
		
		return null;
		
	}

	/** 피넛 오리지널 리플 내용 가져오기 */
	@ResponseBody
	@RequestMapping(value = "/book/oribookOneReply", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String oriBookOneReply(@RequestParam(value = "rNo") String rNo) {

		String replyContents = rService.getOriOneReply(rNo);
		return replyContents;

	}

	/** 리플 수정하기 */
	@ResponseBody
	@RequestMapping(value = "/book/modifyOriReply", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String modifyOriReply(HttpSession session, @ModelAttribute OriginBookReply obReply) {

		Member member = (Member) session.getAttribute("loginMember");
		String memberId = member.getMemberId();

		// 리플 쓴 사람 체크하기
		String repleId = rService.checkOriReplyMember(obReply.getReplyNo());
		int result = 0;
		if (repleId.equals(memberId)) {
			result = rService.modifyReply(obReply);
		} else {
			result = 0;
		}

		return result + "";

	}

	/** 리플 삭제하기 */
	@ResponseBody
	@RequestMapping(value = "/book/removeOriReply", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String modifyOriReply(HttpSession session, @RequestParam(value = "rNo") Integer rNo) {

		Member member = (Member) session.getAttribute("loginMember");
		String memberId = member.getMemberId();

		// 리플 쓴 사람 체크하기
		String repleId = rService.checkOriReplyMember(rNo);
		int result = 0;
		if (repleId.equals(memberId)) {
			result = rService.removeOriReply(rNo);
		} else {
			result = 0;
		}

		return result + "";

	}

	/** 페이징하기 */
	public String boardList(Integer page, int totalCount, int boardLimit) {
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

		return startNavi + "," + endNavi + "," + maxPage + "," + currentPage;
	}

	/***
	 * 에러페이지 연결
	 * 
	 * @param mv
	 * @param e
	 * @return
	 */
	@ExceptionHandler({ NullPointerException.class, NumberFormatException.class, Exception.class })
	public ModelAndView errorPage(ModelAndView mv, Exception e) {

		mv.addObject("msg", e.getMessage());
		mv.setViewName("/common/errorPage");

		return mv;

	}

	/**
	 * 별점주기
	 * 
	 * @param star
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/StarScore.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String StarScore(@ModelAttribute Star star, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");

		star.setMemberId(member.getMemberId()); // 사용자 아이디를 star클래스에 넣기

		Gson gson = new Gson();
		int score = 0; // 기본 별점은 0
		int result = rService.getStarScoreOrigin(star); // 사용자가 준 별점을 테이블에 추가
		if (star.getCategory().equals("origin")) {// 피넛 오리지널 일때
			OriginBook oBook = rService.showOnebook(star.getBookNo()); // 도서정보 가지고 옴
			if (oBook.getScore() != 0 || oBook.getScoreCount() != 0) {
				score = oBook.getScore() / oBook.getScoreCount(); // 도서 정보안에 score를 평균내서 계산한다
			}
			oBook.setScore(score);
			return gson.toJson(oBook);
		} else if (star.getCategory().equals("normal")) {// 일반도서일때
			NormalBook nBook = rService.showOneNorBook(star.getBookNo()); // 일반도서 가져오기
			if (nBook.getScore() != 0 || nBook.getScoreCount() != 0) {
				score = nBook.getScore() / nBook.getScoreCount(); // 도서 정보안에 score를 평균내서 계산한다
			}
			nBook.setScore(score);
			return gson.toJson(nBook);
		}

		return "";
	}

	/**
	 * 별점취소
	 * 
	 * @param star
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/StarRemove.do", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String StarRemove(@ModelAttribute Star star, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");

		star.setMemberId(member.getMemberId());

		int result = rService.removeScore(star); //별점지우기
		Gson gson = new Gson();
		int score = 0;

		if (star.getCategory().equals("origin")) {
			OriginBook oBook = rService.showOnebook(star.getBookNo());

			if (oBook.getScore() != 0 || oBook.getScoreCount() != 0) {
				score = oBook.getScore() / oBook.getScoreCount();
			}
			oBook.setScore(score);
			return gson.toJson(oBook);
		}else if(star.getCategory().equals("normal")) {
			NormalBook nBook = rService.showOneNorBook(star.getBookNo()); // 일반도서 가져오기
			if (nBook.getScore() != 0 || nBook.getScoreCount() != 0) {
				score = nBook.getScore() / nBook.getScoreCount(); // 도서 정보안에 score를 평균내서 계산한다
			}
			nBook.setScore(score);
			return gson.toJson(nBook);
		}

		return "";
	}
}
