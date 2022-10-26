package com.books.peanut.book.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.HomeController;
import com.books.peanut.book.domain.WriterProfile;
import com.books.peanut.book.domain.BookPage;
import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookSeries;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.Star;
import com.books.peanut.book.service.BookService;
import com.books.peanut.member.domain.Member;

@Controller
public class BookController {

	private static final Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookService bService;

	/**
	 * 도서 등록 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/bookRegistView.do", method = RequestMethod.GET)
	public ModelAndView registBookView(ModelAndView mv, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member == null) {
			mv.addObject("msg", "로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");
		} else if (member.getAdminYN().equals("Y")) {
			mv.setViewName("/book/bookregist-admin");

		} else {
			mv.setViewName("/book/bookregist");

		}

		return mv;

	}

	/**
	 * 피넛 오리지널 도서 시리즈 다음화 등록 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/oriBookNextSeires.do", method = RequestMethod.GET)
	public ModelAndView registnextBookView(ModelAndView mv, HttpSession session, int bookNo, int seriesNo) {

		Member member = (Member) session.getAttribute("loginMember");
		int result = bService.checkWriter(bookNo, member.getMemberId());// 작가 맞는지 체크하기
		if (result > 0) {
			OriginBook oBook = bService.showOnebook(bookNo + ""); // 해당시리즈의 상위도서 가져오기
			String category = categroyChange(oBook.getCategory());// 카테고리 번역하기
			oBook.setCategory(category);// 번역한 카테고리 등록하기
			mv.addObject("oBook", oBook);
			mv.addObject("seriesNo", seriesNo);
			mv.setViewName("/book/bookRegistNext");

		} else {
			mv.addObject("msg", "작가 본인만 등록할수 있습니다.");
			mv.setViewName("/common/errorPage");
		}

		return mv;

	}

	/**
	 * 피넛 오리지널 도서 시리즈 수정 등록 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/oriSeriesModifyView.do", method = RequestMethod.GET)
	public ModelAndView oriSeriesModifyView(ModelAndView mv, HttpSession session, int bookNo, int seriesNo) {

		Member member = (Member) session.getAttribute("loginMember");
		int result = bService.checkWriter(bookNo, member.getMemberId());// 작가 맞는지 체크하기
		if (result > 0) {
			OriginBook oBook = bService.showOnebook(bookNo + "");// 해당 시리즈의 상위 도서 가져오기
			String category = categroyChange(oBook.getCategory()); // 카테고리 번역하기
			oBook.setCategory(category);// 번역한 카테고리 등록하고
			OriginBookSeries oSeries = bService.getOneSeries(seriesNo, bookNo);
			mv.addObject("oBook", oBook);
			mv.addObject("oSeries", oSeries);
			mv.setViewName("/book/bookModifySeries");

		} else {
			mv.addObject("msg", "작가 본인만 수정할수 있습니다.");
			mv.setViewName("/common/errorPage");
		}

		return mv;

	}

	/***
	 * 작가 프로필 등록 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/writerView.do", method = RequestMethod.GET)
	public ModelAndView writerProfile(ModelAndView mv, HttpSession session) {

		if (session.getAttribute("loginMember") == null) {

			mv.addObject("msg", "로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");

		} else {
			mv.setViewName("/book/writerprofile");
		}
		return mv;

	}

	/***
	 * 글 등록 메뉴 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/writerMenu.do", method = RequestMethod.GET)
	public ModelAndView writerMenu(ModelAndView mv, HttpSession session,
			@RequestParam(value = "page", required = false) Integer page) {

		if (session.getAttribute("loginMember") == null) {

			mv.addObject("msg", "로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");

		} else {
			Member member = (Member) session.getAttribute("loginMember");
			// 작가 프로필 불러오기
			WriterProfile oneWriter = bService.getProfile(member.getMemberId());
			// 작가 프로필을 등록했을때는 작가 프로필을 전송함
			if (oneWriter != null) {
				mv.addObject("oneWriter", oneWriter);

				// 지금까지 쓴 도서 리스트를 불러옴
				// 페이징 시작
				int getTotlaCount = bService.allOriSeriesCount(member.getMemberId()); // 가지고올 책의 갯수 파악
				int boardLimit = 20;
				BookPageController bpCont = new BookPageController();// 페이징 해주는 클래스
				BookPage bPage = bpCont.boardList(page, getTotlaCount, boardLimit); // 클래스에서 페이징해온 숫자를 가지고옴
				
				
				if (getTotlaCount>0) {
					List<OriginBookSeries> osList = bService.allOriSeries(bPage.getCurrentPage(), boardLimit,
							member.getMemberId());

					for (int i = 0; i < osList.size(); i++) {
						String bookTitle = bService.getBookTitle(osList.get(i).getBookNo());
						osList.get(i).setBookTitle(bookTitle);// 각 시리즈의 책 제목 가지고옴
					}
					mv.addObject("osList", osList);
				}
				mv.addObject("bPage", bPage);
				
			}

			mv.setViewName("/book/writermenu");
		}
		return mv;

	}

	/***
	 * 관리자용 글 등록 메뉴 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/writerMenu-admin.do", method = RequestMethod.GET)
	public ModelAndView adminWriterMenu(ModelAndView mv, HttpSession session,
			@RequestParam(value = "page", required = false) Integer page) {

		Member member = (Member) session.getAttribute("loginMember");
		if ((member.getAdminYN().charAt(0) + "").equals("N")) { // 세션에서 관리자 체크가 안된다면

			mv.addObject("msg", "관리자만 접속가능합니다");
			mv.setViewName("/common/errorPage");

		} else {

			WriterProfile oneWriter = bService.getProfile(member.getMemberId());
			// 작가 프로필을 등록했을때는 작가 프로필을 전송함

			if (oneWriter != null) {
				mv.addObject("oneWriter", oneWriter);

				// 관리자가 작성한 도서 리스트를 불러옴
				// 페이징 시작
				int getTotlaCount = bService.allNorSeriesCount(); // 가지고올 책 시리즈의 갯수 파악
				int boardLimit = 20;
				BookPageController bpCont = new BookPageController();// 페이징 해주는 클래스
				BookPage bPage = bpCont.boardList(page, getTotlaCount, boardLimit); // 클래스에서 페이징해온 숫자를 가지고옴
				if (getTotlaCount > 0) {
					List<NormalBookSeries> nsList = bService.allAdminBooks(); // 모든 일반도서 시리즈 리스트로 가져오기

					for (int i = 0; i < nsList.size(); i++) {
						String bookTitle = bService.getNorBookTitle(nsList.get(i).getBookNo());
						nsList.get(i).setBookTitle(bookTitle);// 각 시리즈의 책 제목 가지고옴
					}
					mv.addObject("nsList", nsList);
				}

				mv.addObject("bPage", bPage);

			}

			mv.setViewName("/bookadmin/writermenu-admin");
		}
		return mv;

	}

	/***
	 * 프로필 수정 메뉴 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/modifyView.do", method = RequestMethod.GET)
	public ModelAndView modifyView(ModelAndView mv, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {

		if (session.getAttribute("loginMember") == null) {

			mv.addObject("msg", "로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");

		} else {
			Member member = (Member) session.getAttribute("loginMember");
			WriterProfile oneWriter = bService.getProfile(member.getMemberId());
			mv.addObject("writer", oneWriter);
			mv.addObject("msg", msg);
			mv.setViewName("/book/writerprofile-modify");
		}
		return mv;

	}

	/**
	 * 작가 프로필 등록
	 * 
	 * @param mv
	 * @param wrtiePro
	 * @param headerPic
	 * @param proPic
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/profileregist.do", method = RequestMethod.POST)
	public ModelAndView profileregist(ModelAndView mv, @ModelAttribute WriterProfile wrtiePro,
			@RequestParam(value = "headerPicture", required = false) MultipartFile headerPic,
			@RequestParam(value = "profilePicture", required = false) MultipartFile proPic, HttpServletRequest request,
			HttpSession session) {

		// 프로필 사진 저장
		String mainPic = proPic.getOriginalFilename();
		if (!mainPic.equals("")) {

			String mainPicRename = fileSave(mainPic, request, proPic, "prof");

			wrtiePro.setMainPic(mainPic);
			wrtiePro.setMainPicRename(mainPicRename);
		} else {
			wrtiePro.setMainPic("defaultImg.jpg");
			wrtiePro.setMainPicRename("defaultImg.jpg");

		}

		// 프로필 헤더 사진 저장
		String headPic = headerPic.getOriginalFilename();
		if (!headPic.equals("")) {

			String headPicRename = fileSave(headPic, request, headerPic, "head");

			wrtiePro.setHeadPic(headPic);
			wrtiePro.setHeadPicRename(headPicRename);
		} else {
			wrtiePro.setHeadPic("defaultImg.jpg");
			wrtiePro.setHeadPicRename("defaultImg.jpg");

		}

		Member member = (Member) session.getAttribute("loginMember");
		wrtiePro.setMemberId(member.getMemberId());
		int result = bService.registProfile(wrtiePro);
		mv.addObject("msg", "수정성공");

		mv.setViewName("redirect:/book/modifyView.do");

		return mv;

	}

	/**
	 * 피넛 오리지널 도서 등록
	 * 
	 * @param mv
	 * @param wrtiePro
	 * @param headerPic
	 * @param proPic
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/oribookRegist.do", method = RequestMethod.POST)
	public ModelAndView oribookRegist(ModelAndView mv, @ModelAttribute HashTag hTag, @ModelAttribute OriginBook oBook,
			@ModelAttribute OriginBookSeries oSeries,
			@RequestParam(value = "coverpic", required = false) MultipartFile coverpic,
			@RequestParam(value = "subPicture", required = false) MultipartFile subPicture, HttpServletRequest request,
			HttpSession session) {

		// 표지사진 저장
		String mainPic = coverpic.getOriginalFilename();
		if (!mainPic.equals("")) {
			String coverPicRename = fileSave(mainPic, request, coverpic, "cover");
			oBook.setCoverRename(coverPicRename);
			oBook.setCover(mainPic);
		} else {
			oBook.setCoverRename("defaultImg.jpg");
			oBook.setCover("defaultImg.jpg");

		}

		// 삽화저장
		String subPic = subPicture.getOriginalFilename();
		if (!subPic.equals("")) {
			String subPicRename = fileSave(subPic, request, subPicture, "sub");
			oSeries.setSubPic(subPic);
			oSeries.setSubPicRename(subPicRename);
		} else {
			oSeries.setSubPic("defaultImg.jpg");
			oSeries.setSubPicRename("defaultImg.jpg");
		}

		Member member = (Member) session.getAttribute("loginMember");
		oBook.setMemberId(member.getMemberId());
		oSeries.setSeriesNo(1);
		oSeries.setPaidCheck("N");
		hTag.setCategory("origin");
		int result = bService.registeOriBook(oBook);
		result += bService.registeTag(hTag);
		result += bService.registOriSeries(oSeries);

		mv.setViewName("redirect:/book/writerMenu.do");
		return mv;

	}

	/**
	 * 작가프로필 수정
	 * 
	 * @param mv
	 * @param wrtiePro
	 * @param headerPic
	 * @param proPic
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/profileModify.do", method = RequestMethod.POST)
	public ModelAndView profileModify(ModelAndView mv, @ModelAttribute WriterProfile wrtiePro,
			@RequestParam(value = "headerPicture", required = false) MultipartFile headerPic,
			@RequestParam(value = "profilePicture", required = false) MultipartFile proPic, HttpServletRequest request,
			HttpSession session) {

		// 세션으로 id가져옴
		Member member = (Member) session.getAttribute("loginMember");
		WriterProfile oneWriter = bService.getProfile(member.getMemberId()); // id값으로 프로필 가져옴
		// 현재 프로필에 있는 그림의 이름 등록함
		wrtiePro.setHeadPic(oneWriter.getHeadPic());
		wrtiePro.setHeadPicRename(oneWriter.getHeadPicRename());
		wrtiePro.setMainPic(oneWriter.getMainPic());
		wrtiePro.setMainPicRename(oneWriter.getMainPicRename());

		// 사진 저장 시작

		String mainPic = proPic.getOriginalFilename();
		if (proPic != null && !mainPic.equals("")) {

			fileDelete(request, wrtiePro.getMainPicRename());
			String mainPicRename = fileSave(mainPic, request, proPic, "main");

			wrtiePro.setMainPic(mainPic);
			wrtiePro.setMainPicRename(mainPicRename);

		}

		String headPic = headerPic.getOriginalFilename();
		if (proPic != null && !headPic.equals("")) {

			fileDelete(request, wrtiePro.getHeadPicRename());
			String headPicRename = fileSave(headPic, request, headerPic, "head");

			wrtiePro.setHeadPic(headPic);
			wrtiePro.setHeadPicRename(headPicRename);
		}
		// 사진 저장됐으면 이름은 새로 저장한 사진으로 전부 변경
		// 아이디 저장함
		wrtiePro.setMemberId(member.getMemberId());

		// 새롭게 변경한 값으로 프로필 업데이트
		int result = bService.modifyProfile(wrtiePro);

		// 업데이트한 값 전송
		mv.addObject("writer", wrtiePro);
		// 모디파이 페이지로 재 연결
		mv.addObject("msg", "수정성공");
		mv.setViewName("redirect:/book/modifyView.do");

		return mv;

	}

	/**
	 * 피넛오리지널 북 책 소개 페이지로 연결
	 * 
	 * @param mv
	 * @param session
	 * @param bookNo
	 * @return
	 */
	@RequestMapping(value = "/book/oriBookInfo", method = RequestMethod.GET)
	public ModelAndView showOneOriBook(ModelAndView mv, HttpSession session, String bookNo) {
		Member member = (Member) session.getAttribute("loginMember");

		// 로그인 안하면 메인페이지로 보냄
		if (session.getAttribute("loginMember") == null) {
			mv.setViewName("redirect:/");
		} else {
			// 해당 책 가져오기
			OriginBook oBook = bService.showOnebook(bookNo); // 도서 메인테이블

			// 해당책이 만약 허가되지 않았다면 작가와 관리자에는 열람 불가
			String permission = oBook.getCheckPermission().charAt(0) + "";
			if (permission.equals("N")) {
				if ((!member.getMemberId().equals(oBook.getMemberId())) && member.getAdminYN().equals("N")) {

					mv.addObject("msg", "이책은 승인되지 않은 책입니다");
					mv.setViewName("/common/errorPage");

					return mv;

				}

			}

			List<OriginBookSeries> osList = bService.getSeriesTitle(bookNo); // 도서 시리즈 이름만 가져오기
			String category = "origin";
			HashTag hTag = bService.getBookTga(bookNo, category); // 도서 태그가져오기
			HashTag OneTag = changeKo(hTag);// 태그 한글로 변경

			String mNick = bService.getMemberNickName(oBook.getMemberId());// 작가 닉네임 가져오기
			oBook.setMemberNickName(mNick);

			// 별점 평균 계산하기
			int score = oBook.getScore();
			int count = oBook.getScoreCount();
			int scoreSet = 0;

			if (score == 0 || count == 0) {
				scoreSet = 0;
			} else {
				scoreSet = (int) Math.round(score / count);
			}
			oBook.setScore(scoreSet);

			Star starOne = new Star();
			starOne.setBookNo(bookNo);
			starOne.setCategory(category);
			starOne.setMemberId(oBook.getMemberId());
			Star star = bService.getOneBookStar(starOne);// 내가 준 별점 가져오기

			mv.addObject("hTag", OneTag);
			mv.addObject("star", star);
			mv.addObject("oBook", oBook);
			mv.addObject("osList", osList);
			mv.setViewName("/book/bookmain");
		}
		return mv;

	}

	/**
	 * 책 시리즈 순서 페이지로 연결하기
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/book/bookStep.do", method = RequestMethod.GET)
	public ModelAndView stepOriBook(ModelAndView mv, @RequestParam("bookNo") String bookNo,
			@RequestParam("category") String category, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");
		if (session.getAttribute("loginMember") != null) {
			if (category.equals("origin")) {// 피넛 오리지널의 경우
				OriginBook oBook = bService.showOnebook(bookNo); // 책 번호로 한권 가지고오기
				WriterProfile oneWriter = bService.getProfile(oBook.getMemberId());// 책에 자가 아이디로 작가 프로필 전부 가져오기
				List<OriginBook> obList = bService.allWirterbookTitle(oBook.getMemberId()); // 작가의 모든 책 제목 가져오기
				List<OriginBookSeries> osList = bService.allOriBookSeries(bookNo); // 해당 도서의 모든 시리즈 가져오기

				for (OriginBookSeries obSeries : osList) {

					String contents = obSeries.getContents().replaceAll("<[^>]*>", ""); // 태그 삭제하는 정규표현식
					if (contents.length() > 300) {
						contents = contents.substring(0, 300);
					}
					if (obSeries.getPaidCheck().equals("Y ")) {
						contents = "유료화는 미리보기 할수 없습니다";
					}

					obSeries.setContents(contents);

					logger.info(contents);
				}

				// 해당책이 만약 허가되지 않았다면 작가와 관리자에는 열람 불가
				String permission = oBook.getCheckPermission().charAt(0) + "";
				if (permission.equals("N")) {
					if ((!member.getMemberId().equals(oBook.getMemberId())) && member.getAdminYN().equals("N")) {

						mv.addObject("msg", "이책은 승인되지 않은 책입니다");
						mv.setViewName("/common/errorPage");

						return mv;

					}

				}

				String mNickName = bService.getMemberNickName(oBook.getMemberId()); // 작가 닉네임 가져오기
				oBook.setMemberNickName(mNickName);// 작가 닉 네임 넣어

				mv.addObject("oBook", oBook); // 책전송
				mv.addObject("oneWriter", oneWriter);// 작가전송
				mv.addObject("obList", obList); // 모든 책 제목 전송
				mv.addObject("osList", osList); // 모든 시리즈 전송
				mv.setViewName("/book/bookstep");

			}
		} else {
			mv.setViewName("redirect:/");
		}

		return mv;
	}

	/**
	 * 시리즈 유료화 여부 체크
	 */
	@ResponseBody
	@RequestMapping(value = "/book/checkPaidbookSeries.do", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String checkPaidbookSeries(int seriesNo, int bookNo, HttpSession session) {
		String paidCheck = bService.checkPaid(seriesNo, bookNo);

		logger.info("1" + paidCheck + "1");
		return paidCheck;
	}

	/**
	 * 시리즈 구입 여부 체크
	 */
	@ResponseBody
	@RequestMapping(value = "/book/checkPurchase.do", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String checkPurchase(int seriesNo, int bookNo, HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		int result = bService.checkPurchase(memberId, seriesNo, bookNo);// 구입여부 체크
		logger.info(result + "구입여부");
		result += bService.checkSUbcribe(memberId);// 현재 구독 여부 체크
		logger.info(result + "구독여부");

		result += bService.checkWriter(bookNo, member.getMemberId()); // 작가가 맞는지 체크하기

		return result + "";
	}

	/**
	 * 도서 구매하기
	 */
	@ResponseBody
	@RequestMapping(value = "/book/buyThisSeries.do", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String buyThisSeries(int seriesNo, int bookNo, HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		int result = bService.checkNowPoint(memberId);// 피넛갯수 확인하기
		if (result >= 1) {
			String bookTitle = bService.getBookTitle(bookNo + "");
			int result1 = bService.buyOneSeries(seriesNo, bookNo, memberId, bookTitle); // 땅콩으로 시리즈 구입
		}

		return result + "";
	}

	/**
	 * 피넛 오리지널 시리즈 한 편 열람
	 * 
	 * @param mv
	 * @param session
	 * @param seriesNo
	 * @param bookNo
	 * @return
	 */
	@RequestMapping(value = "/book/OridetailSeries.do", method = RequestMethod.GET)
	public ModelAndView detailOribookSeries(ModelAndView mv, HttpSession session, int seriesNo, int bookNo) {
		if (session.getAttribute("loginMember") != null) {// 로그인 여부 체크

			Member member = (Member) session.getAttribute("loginMember");
			String paidCheck = bService.checkPaid(seriesNo, bookNo); // 유료화 여부 다시 체크

			int result = bService.checkPurchase(member.getMemberId(), seriesNo, bookNo);// 구입여부 체크
			result += bService.checkSUbcribe(member.getMemberId());// 현재 구독 여부 체크

			int wirterMember = bService.checkWriter(bookNo, member.getMemberId()); // 작가가 맞는지 체크하기
			logger.info(wirterMember + "작가여부");

			if (paidCheck.equals("N ") || result > 0 || wirterMember > 0) {// 유료화가 아니거나, 구매했거나
				String bookTitle = bService.getBookTitle(bookNo + ""); // 책 이름 가져옴
				OriginBookSeries obSeries = bService.getOneSeries(seriesNo, bookNo); // 시리즈 한편가져오기

				// 해당책이 만약 허가되지 않았다면 작가와 관리자에는 열람 불가
				String permission = obSeries.getCheckPermission().charAt(0) + "";
				if (permission.equals("N")) {
					if ((wirterMember < 1) && member.getAdminYN().equals("N")) {

						mv.addObject("msg", "이책은 승인되지 않은 책입니다");
						mv.setViewName("/common/errorPage");

						return mv;

					}

				}

				mv.addObject("bookTitle", bookTitle);
				mv.addObject("obSeries", obSeries);
				mv.setViewName("/book/bookstep-detail");

			} else {

				mv.setViewName("redirect:/");
			}
		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}

	/**
	 * 피넛 오리지널 다음 시리즈 등록
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/oriSeriesRegist.do", method = RequestMethod.POST)
	public ModelAndView oriSeriesRegist(ModelAndView mv, HttpSession session, @ModelAttribute OriginBookSeries obSeries,
			@RequestParam(value = "subPicture", required = false) MultipartFile subPicture,
			HttpServletRequest request) {

		logger.info(obSeries.toString());
		// 삽화 저장
		String picName = subPicture.getOriginalFilename(); // 파일 이름 가져오기
		if (!picName.equals("")) {

			String subPicRename = fileSave(picName, request, subPicture, "sub");

			obSeries.setSubPic(picName);
			obSeries.setSubPicRename(subPicRename);
		} else {
			obSeries.setSubPic("defaultImg.jpg");
			obSeries.setSubPicRename("defaultImg.jpg");

		}
		String paid = "N";
		if (obSeries.getPaidCheck().equals("on")) {
			paid = "Y";
		}
		obSeries.setPaidCheck(paid);
		int result = bService.registOriSeriesNext(obSeries); // 다음화 시리즈만 데이터베이스에 전송하기
		mv.addObject("bookNo", obSeries.getBookNo());
		mv.setViewName("redirect:/book/oriBookInfo");

		return mv;

	}

	/**
	 * 피넛 오리지널 시리즈 수정
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/oriSeriesModify.do", method = RequestMethod.POST)
	public ModelAndView oriSeriesModify(ModelAndView mv, HttpSession session, @ModelAttribute OriginBookSeries obSeries,
			@RequestParam(value = "subPicture", required = false) MultipartFile subPicture,
			HttpServletRequest request) {
		logger.info(obSeries.toString());

		// 삽화 수정
		String picName = subPicture.getOriginalFilename(); // 전송한 사진 이름 가져오기
		if (subPicture != null && !picName.equals("")) { // 만약 전송한 사진이 있거나 사진이름이 없는게 아니라면

			String subPicRename = fileSave(picName, request, subPicture, "sub");// 파일을 저장한다, 파일이름,리퀘스트,저장된파일, 추가할이름

			obSeries.setSubPic(picName);
			obSeries.setSubPicRename(subPicRename);

		}

		String paid = "N";
		if (obSeries.getSeriesNo() != 1) {
			if (obSeries.getPaidCheck().equals("on")) {
				paid = "Y";
			}
		}
		obSeries.setPaidCheck(paid);

		int result = bService.modifyOriSeries(obSeries); // 다음화 시리즈만 데이터베이스에 전송하기
		mv.addObject("bookNo", obSeries.getBookNo());
		mv.setViewName("redirect:/book/oriBookInfo");

		return mv;

	}

	/** 해시태그 한글 변경 메소드 */
	private HashTag changeKo(HashTag hTag) {

		HashTag OneTag = new HashTag();
		if (!hTag.getHashTag1().isEmpty()) {
			switch (hTag.getHashTag1()) {
			case "fantasy":
				OneTag.setHashTag1("판타지");
				break;
			case "now":
				OneTag.setHashTag1("현대");
				break;
			case "daily":
				OneTag.setHashTag1("일상");
				break;
			case "history":
				OneTag.setHashTag1("역사");
				break;
			default:
				OneTag.setHashTag1("none");
				break;
			}
		}
		if (!hTag.getHashTag2().isEmpty()) {
			switch (hTag.getHashTag2()) {
			case "child":
				OneTag.setHashTag2("어린이를 위한");
				break;
			case "adult":
				OneTag.setHashTag2("어른을 위한");
				break;
			case "woman":
				OneTag.setHashTag2("여성을 위한");
				break;
			case "man":
				OneTag.setHashTag2("남성을 위한");
				break;
			case "all":
				OneTag.setHashTag2("모두를 위한");
				break;
			default:
				OneTag.setHashTag2("none");
				break;
			}
		}
		if (!hTag.getHashTag3().isEmpty()) {
			switch (hTag.getHashTag3()) {
			case "horror":
				OneTag.setHashTag3("겁쟁이 출입금지");
				break;
			case "gag":
				OneTag.setHashTag3("배꼽 빠지는");
				break;
			case "move":
				OneTag.setHashTag3("마음이 따뜻해 지는");
				break;
			case "heart":
				OneTag.setHashTag3("설레이는");
				break;
			case "tear":
				OneTag.setHashTag3("눈물이 나는");
				break;
			case "popcorn":
				OneTag.setHashTag3("팝콘각");
				break;
			case "cider":
				OneTag.setHashTag3("사이다 마시는");
				break;
			default:
				OneTag.setHashTag3("none");
				break;
			}
		}

		return OneTag;
	}

	/**
	 * 수정 사진 삭제 메소드
	 * 
	 * @param request
	 * @param rename
	 */
	private void fileDelete(HttpServletRequest request, String rename) {
		if (!rename.equals("defaultImg.jpg")) {
			String root = request.getSession().getServletContext().getRealPath("resources");// 저장된 파일의 경로를 가져온다.
			String savedPath = root + "\\bookImg"; // 가져온 경로에 업로드 파일이 들어있는 폴더의 경로까지 더해줌
			File file = new File(savedPath + "\\" + rename); // 이미 저장한 파일의 이름을 가져와야 한다.

			if (file.exists()) { // 지정한 파일이 있는지 없는지 체크
				file.delete(); // 있으면 삭제
			}
		}

	}

	/**
	 * 사진 저장용 메소드
	 * 
	 * @param mainPic
	 * @param request
	 * @param fileName
	 * @return
	 */
	public String fileSave(String mainPic, HttpServletRequest request, MultipartFile fileName, String name) {

		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\bookImg"; // 내가 저장할 폴더
		File file = new File(savePath); // 파일 객체 만들기

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String mainPicRename = name + sdf.format(new Date(System.currentTimeMillis())) + "."
				+ mainPic.substring(mainPic.lastIndexOf(".") + 1);

		if (!file.exists()) { // 경로에 savePath가 없을땐
			file.mkdir(); // 경로 만들기
		}
		try {
			fileName.transferTo(new File(savePath + "\\" + mainPicRename));// 파일을 buploadFile경로에 저장

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mainPicRename;
	}

	/* 카테고리 번역 */
	private String categroyChange(String category) {

		switch (category) {
		case "novel":
			category = "소설";
			break;
		case "essay":
			category = "에세이";
			break;
		case "tale":
			category = "동화";
			break;
		case "poem":
			category = "시";
			break;
		}

		return category;
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

}
