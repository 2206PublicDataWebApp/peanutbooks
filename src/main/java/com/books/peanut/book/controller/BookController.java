package com.books.peanut.book.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebParam.Mode;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
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
import com.books.peanut.book.domain.peanutPaidSeries;
import com.books.peanut.book.domain.BookPage;
import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.NormalBookSeries;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.domain.Star;
import com.books.peanut.book.service.BookService;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.PeanutPoint;
import com.google.gson.Gson;

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
			mv.setViewName("/");
		} else if (member.getAdminYN().equals("Y")) {
			mv.setViewName("/bookadmin/bookregist-admin");

		} else {
			mv.setViewName("/book/bookregist");

		}

		return mv;

	}
	/**
	 * 피넛 오리지널 도서 수정 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/OriBookModifyView.do", method = RequestMethod.GET)
	public ModelAndView OriBookModifyView(ModelAndView mv, HttpSession session, String bookNo) {
		
		Member member = (Member) session.getAttribute("loginMember");
		if (member == null) {
			mv.setViewName("/");
		} else if (member.getAdminYN().equals("Y")) {
			OriginBook oBook = bService.showOnebook(bookNo);
			HashTag hTag = bService.getBookTga(bookNo, "origin");
			mv.addObject("oBook",oBook);
			mv.addObject("hTag",hTag);
			mv.setViewName("/book/modifyBook");
			
		} else {
			mv.setViewName("/");
			
		}
		
		return mv;
		
	}
	
	/**
	 * 일반 도서 수정 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/NorBookModifyView.do", method = RequestMethod.GET)
	public ModelAndView NorBookModifyView(ModelAndView mv, HttpSession session, String bookNo) {
		
		Member member = (Member) session.getAttribute("loginMember");
		if (member == null) {
			mv.setViewName("/");
		} else if (member.getAdminYN().equals("Y")) {
			NormalBook nBook = bService.showOneNorbook(bookNo);
			HashTag hTag = bService.getBookTga(bookNo, "normal");
			mv.addObject("nBook",nBook);
			mv.addObject("hTag",hTag);
			mv.setViewName("/bookadmin/modifyBook");
			
		} else {
			mv.setViewName("/");
			
		}
		
		return mv;
		
	}

	/**
	 * 검색 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/bookSearch.do", method = RequestMethod.GET)
	public ModelAndView bookSearchView(ModelAndView mv, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member == null) {
			mv.setViewName("redirect:/");

		} else {
			String img1 = bService.getNorImgName(); // 동화에서 가장 조회수 높은 그림가져오기
			String img2 = bService.getNorImgName2(); // 시에서 가장 조회수 높은 그림 가져오기
			mv.addObject("img1", img1);
			mv.addObject("img2", img2);

			String img3 = bService.getOriImgName(); // 소설에서 가장 조회수 높은 그림가져오기
			String img4 = bService.getOriImgName2(); // 동화에서 가장 조회수 높은 그림 가져오기
			mv.addObject("img3", img3);
			mv.addObject("img4", img4);

			mv.setViewName("/book/bookSearch");

		}

		return mv;

	}

	/**
	 * 피넛 오리지널 목록 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/bookOriList.do", method = RequestMethod.GET)
	public ModelAndView bookOriListView(ModelAndView mv, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member == null) {

			mv.setViewName("redirect:/");

		} else {

			List<OriginBook> oNList = bService.getRankOriBook("novel");
			List<OriginBook> oEList = bService.getRankOriBook("essay");
			List<OriginBook> oTList = bService.getRankOriBook("tale");
			List<OriginBook> oPList = bService.getRankOriBook("poem");
			List<OriginBook> oOList = bService.getRankOriBook("other");

			mv.addObject("oNList", oNList);
			mv.addObject("oEList", oEList);
			mv.addObject("oTList", oTList);
			mv.addObject("oPList", oPList);
			mv.addObject("oOList", oOList);

			mv.setViewName("/book/booklist-ori");

		}

		return mv;

	}

	/**
	 * 일반도서 목록 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/bookNorList.do", method = RequestMethod.GET)
	public ModelAndView bookNorListView(ModelAndView mv, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member == null) {

			mv.setViewName("redirect:/");

		} else {

			List<NormalBook> nDList = bService.getRankNorBook("detective");// 추리
			List<NormalBook> nHList = bService.getRankNorBook("history");// 역사
			List<NormalBook> nRList = bService.getRankNorBook("romance");// 로맨스
			List<NormalBook> nTList = bService.getRankNorBook("tale");// 동화
			List<NormalBook> nPList = bService.getRankNorBook("poem");// 시
			List<NormalBook> nNList = bService.getRankNorBook("novel");// 소설
			List<NormalBook> nOList = bService.getRankNorBook("other");// 기타

			mv.addObject("nDList", nDList);
			mv.addObject("nHList", nHList);
			mv.addObject("nRList", nRList);
			mv.addObject("nTList", nTList);
			mv.addObject("nPList", nPList);
			mv.addObject("nNList", nNList);
			mv.addObject("nOList", nOList);

			mv.setViewName("/book/booklist-nor");

		}

		return mv;

	}

	/**
	 * 도서 카테고리 목록 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/bookCatogoryList.do", method = RequestMethod.GET)
	public ModelAndView bookCatogoryListView(ModelAndView mv, HttpSession session,
			@RequestParam(value = "bookCate") String bookCate, @RequestParam(value = "category") String category,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "step", required = false, defaultValue = "all") String step) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member == null) {

			mv.setViewName("redirect:/");

		} else {
			if (bookCate.equals("origin")) { // 피넛 오리지널 이라면
				int getTotalCount = bService.countAllOriginCategory(category);// 페이징용 갯수 가져오기
				int bookLimit = 20;
				BookPageController bpCon = new BookPageController();
				BookPage bPage = bpCon.boardList(page, getTotalCount, bookLimit);

				List<OriginBook> bList = bService.getAllOriginCategory(category, bPage.getCurrentPage(), bookLimit,
						step);
				mv.addObject("bList", bList);
				mv.addObject("TotalCount", getTotalCount);
				mv.addObject("startNavi", bPage.getStartNavi());
				mv.addObject("endNavi", bPage.getEndNavi());
				mv.addObject("maxPage", bPage.getMaxPage());
				mv.addObject("currentPage", bPage.getCurrentPage());

			} else if (bookCate.equals("normal")) {// 일반도서 라면

				int getTotalCount = bService.countAllNormalCategory(category);// 페이징용 갯수 가져오기
				int bookLimit = 20;
				BookPageController bpCon = new BookPageController();
				BookPage bPage = bpCon.boardList(page, getTotalCount, bookLimit);

				List<NormalBook> bList = bService.getAllNormalCategory(category, bPage.getCurrentPage(), bookLimit,
						step);
				mv.addObject("bList", bList);
				mv.addObject("TotalCount", getTotalCount);
				mv.addObject("startNavi", bPage.getStartNavi());
				mv.addObject("endNavi", bPage.getEndNavi());
				mv.addObject("maxPage", bPage.getMaxPage());
				mv.addObject("currentPage", bPage.getCurrentPage());

			}
			// 오리지널/일반도서 한글로 번역하기
			String bookCateStr = "";
			if (bookCate.equals("origin")) {
				bookCateStr = "피넛 오리지널";
			} else {
				bookCateStr = "일반도서";
			}
			String cateStr = categroyChange(category);// 카테고리 번역하기
			mv.addObject("bookCate", bookCate);
			mv.addObject("bookCateStr", bookCateStr);
			mv.addObject("category", category);
			mv.addObject("cateStr", cateStr);
			mv.addObject("step", step);
			mv.setViewName("/book/booklist-category");

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
	 * 일반도서 시리즈 다음화 등록 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/norBookNextSeires.do", method = RequestMethod.GET)
	public ModelAndView registNextNorBookView(ModelAndView mv, HttpSession session, int bookNo, int seriesNo) {

		Member member = (Member) session.getAttribute("loginMember");

		if (member.getAdminYN().equals("Y")) {
			NormalBook nBook = bService.showOneNorbook(bookNo + ""); // 해당시리즈의 상위도서 가져오기
			String category = categroyChange(nBook.getCategory());// 카테고리 번역하기
			String language = languageChange(nBook.getLanguage());// 해당언어 한글로 바꾸기
			nBook.setCategory(category);// 번역한 카테고리 등록하기
			nBook.setLanguage(language);// 바꾼 언어값 저장
			mv.addObject("nBook", nBook);
			mv.addObject("seriesNo", seriesNo);
			mv.setViewName("/bookadmin/bookregist-nextAdmin");

		} else {
			mv.addObject("msg", "관리자만 등록할수 있습니다.");
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

	/**
	 * 일반 도서 시리즈 수정 등록 창 연결
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/NorSeriesModifyView.do", method = RequestMethod.GET)
	public ModelAndView NorSeriesModifyView(ModelAndView mv, HttpSession session, int bookNo, int seriesNo) {

		Member member = (Member) session.getAttribute("loginMember");

		if (member.getAdminYN().equals("Y")) { // 관리자가 맞는지 체크
			NormalBook nBook = bService.showOneNorbook(bookNo + "");// 해당 시리즈의 상위 도서 가져오기
			String category = categroyChange(nBook.getCategory()); // 카테고리 번역하기
			String language = bService.getlanguege(bookNo + "");// 언어 가져오기
			nBook.setCategory(category);// 번역한 카테고리 등록하고
			String laung = languageChange(language);// 가져온 언어 한글로 바꾸기
			nBook.setLanguage(laung);// 한글로 언어 등록
			NormalBookSeries nSeries = bService.getOneNorBookSeries(bookNo, seriesNo);
			mv.addObject("nBook", nBook);
			mv.addObject("nSeries", nSeries);
			mv.setViewName("/bookadmin/bookmodify-admin");

		} else {
			mv.addObject("msg", "관리자만 수정할수 있습니다.");
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

				if (getTotlaCount > 0) {
					List<OriginBookSeries> osList = bService.allOriSeries(bPage.getCurrentPage(), boardLimit,
							member.getMemberId());

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
					List<NormalBookSeries> nsList = bService.allAdminBooks(bPage.getCurrentPage(), boardLimit); // 모든
																												// 일반도서
																												// 시리즈
																												// 리스트로
																												// 가져오기

					mv.addObject("nsList", nsList);
				}

				mv.addObject("bPage", bPage);

			}

			mv.setViewName("/bookadmin/writermenu-admin");
		}
		return mv;

	}

	/**
	 * 내 서재 열기
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/mybooks.do", method = RequestMethod.GET)
	public ModelAndView showAllMybooks(ModelAndView mv, HttpSession session,
			@RequestParam(value = "step", required = false, defaultValue = "all") String step,
			@RequestParam(value = "category", required = false, defaultValue = "all") String category,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "page", required = false) Integer page) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member != null) {
			List<Library> lList = new ArrayList<Library>();

			// 페이징 하기
			int getTotalCount = bService.countOneMemberLibrary(member.getMemberId(), category, step, searchValue);// 총갯수
																													// 가져오기
			int boardLimit = 20;
			BookPageController bpCont = new BookPageController();// 페이징 해주는 클래스
			BookPage bPage = bpCont.boardList(page, getTotalCount, boardLimit); // 클래스에서 페이징해온 숫자를 가지고옴
			// 페이징용코드종료

			lList = bService.getOneMemberLibrary(member.getMemberId(), category, step, searchValue,
					bPage.getCurrentPage(), boardLimit);

			mv.addObject("step", step);
			mv.addObject("searchValue", searchValue);
			mv.addObject("category", category);
			mv.addObject("lList", lList);

			mv.addObject("TotalCount", getTotalCount);
			mv.addObject("startNavi", bPage.getStartNavi());
			mv.addObject("endNavi", bPage.getEndNavi());
			mv.addObject("maxPage", bPage.getMaxPage());
			mv.addObject("currentPage", bPage.getCurrentPage());

			mv.setViewName("/book/bookmark");
		} else {// 로그인안하면 메인페이지로감
			mv.setViewName("redirect:/");
		}

		return mv;

	}

	/**
	 * 내 구입도서 목록 열기
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/myPaid.do", method = RequestMethod.GET)
	public ModelAndView showAllMyPaid(ModelAndView mv, HttpSession session,
			@RequestParam(value = "step", required = false, defaultValue = "all") String step,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "page", required = false) Integer page) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member != null) {
			List<peanutPaidSeries> pList = new ArrayList<peanutPaidSeries>();
			// 페이징시작
			int getTotalCount = bService.getOneMemberPaidCount(member.getMemberId(), step, searchValue); // 가져올 목록의 총 갯수
																											// 파악하기
			int boardLimit = 20;
			BookPageController bpCont = new BookPageController();// 페이징 해주는 클래스
			BookPage bPage = bpCont.boardList(page, getTotalCount, boardLimit); // 클래스에서 페이징해온 숫자를 가지고옴

			pList = bService.getOneMemberPaid(member.getMemberId(), step, searchValue, bPage.getCurrentPage(),
					boardLimit); // 현재 로그인한 아이디와 정렬 검색값을 모두 적용해 구입모록 가져옴

			mv.addObject("pList", pList); // 목록넘기기
			mv.addObject("step", step); // 정렬명
			mv.addObject("searchValue", searchValue); // 검색명

			// 페이징용
			mv.addObject("TotalCount", getTotalCount);
			mv.addObject("startNavi", bPage.getStartNavi());
			mv.addObject("endNavi", bPage.getEndNavi());
			mv.addObject("maxPage", bPage.getMaxPage());
			mv.addObject("currentPage", bPage.getCurrentPage());

			mv.setViewName("/book/bookPaid");
		} else {// 로그인안하면 메인페이지로감
			mv.setViewName("redirect:/");
		}

		return mv;

	}

	/**
	 * 검색 도서 목록 열기
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/bookSearchList.do", method = RequestMethod.GET)
	public ModelAndView bookSearchList(ModelAndView mv, HttpSession session,
			@RequestParam(value = "step", required = false, defaultValue = "all") String step,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "tag", required = false, defaultValue = "empty") String tag,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "bookCate", required = false, defaultValue = "origin") String bookCate) {

		Member member = (Member) session.getAttribute("loginMember");
		if (member != null) {
			List<OriginBook> oList = new ArrayList<OriginBook>();
			List<NormalBook> nList = new ArrayList<NormalBook>();

			// 페이징시작
			int getTotalCountOri = bService.OriBookSearchValueCount(tag, step, searchValue, category); // 가져올 목록의 총 갯수
																										// 파악하기
			int getTotalCountNor = bService.NorBookSearchValueCount(tag, step, searchValue, category); // 가져올 목록의 총 갯수
																										// 파악하기

			int boardLimit = 20;
			BookPageController bpContOri = new BookPageController();// 페이징 해주는 클래스
			BookPageController bpContNor = new BookPageController();// 페이징 해주는 클래스

			if (bookCate.equals("origin")) {// 오리지널 북을 선택했다면

				BookPage bPage = bpContOri.boardList(page, getTotalCountOri, boardLimit); // 클래스에서 페이징해온 숫자를 가지고옴
				oList = bService.allBookSearchValue(tag, step, searchValue, bPage.getCurrentPage(), boardLimit,
						category);

				mv.addObject("bList", oList); // 목록넘기기
				// 페이징용

				mv.addObject("TotalCount", getTotalCountOri);
				mv.addObject("startNavi", bPage.getStartNavi());
				mv.addObject("endNavi", bPage.getEndNavi());
				mv.addObject("maxPage", bPage.getMaxPage());
				mv.addObject("currentPage", bPage.getCurrentPage());
			} else {

				BookPage nPage = bpContNor.boardList(page, getTotalCountOri, boardLimit); // 클래스에서 페이징해온 숫자를 가지고옴
				nList = bService.allBookSearchValueNor(tag, step, searchValue, nPage.getCurrentPage(), boardLimit,
						category);
				mv.addObject("bList", nList); // 목록넘기기
				// 페이징용

				mv.addObject("TotalCount", getTotalCountNor);
				mv.addObject("startNavi", nPage.getStartNavi());
				mv.addObject("endNavi", nPage.getEndNavi());
				mv.addObject("maxPage", nPage.getMaxPage());
				mv.addObject("currentPage", nPage.getCurrentPage());
			}
			// 검색한 태그 전송하기
			if (!tag.equals("empty")) {
				HashTag tagOne = new HashTag();
				tagOne.setHashTag1(tag);
				tagOne.setHashTag2(tag);
				tagOne.setHashTag3(tag);
				tagOne = changeKo(tagOne);

				String hTag = "";
				if (!tagOne.getHashTag1().equals("none")) {
					hTag = tagOne.getHashTag1();
				} else if (!tagOne.getHashTag2().equals("none")) {
					hTag = tagOne.getHashTag2();
				} else {
					hTag = tagOne.getHashTag3();
				}
				mv.addObject("tag", hTag); // 검색한 태그값 보내기
			} else {
				mv.addObject("tag", "");
			}
			mv.addObject("Hahstag", tag); // 검색한 태그값 보내기
			mv.addObject("step", step); // 정렬명
			mv.addObject("searchValue", searchValue); // 검색명
			mv.addObject("category", category); // 검색명
			mv.addObject("bookCate", bookCate); // 검색명

			mv.setViewName("/book/booklist-search");
		} else {// 로그인안하면 메인페이지로감
			mv.setViewName("redirect:/");
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
	 * 일반 도서 등록
	 * 
	 * @param mv
	 * @param wrtiePro
	 * @param headerPic
	 * @param proPic
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/book/norbookRegist.do", method = RequestMethod.POST)
	public ModelAndView norbookRegist(ModelAndView mv, @ModelAttribute HashTag hTag, @ModelAttribute NormalBook nBook,
			@ModelAttribute NormalBookSeries nSeries,
			@RequestParam(value = "coverpic", required = false) MultipartFile coverpic,
			@RequestParam(value = "subPicture", required = false) MultipartFile subPicture, HttpServletRequest request,
			HttpSession session) {

		// 표지사진 저장
		String mainPic = coverpic.getOriginalFilename();
		if (!mainPic.equals("")) {
			String coverPicRename = fileSave(mainPic, request, coverpic, "cover");
			nBook.setCoverRename(coverPicRename);
			nBook.setCover(mainPic);
		} else {
			nBook.setCoverRename("defaultImg.jpg");
			nBook.setCover("defaultImg.jpg");

		}

		// 삽화저장
		String subPic = subPicture.getOriginalFilename();
		if (!subPic.equals("")) {
			String subPicRename = fileSave(subPic, request, subPicture, "sub");
			nSeries.setSubPic(subPic);
			nSeries.setSubpicRename(subPicRename);
		} else {
			nSeries.setSubPic("defaultImg.jpg");
			nSeries.setSubpicRename("defaultImg.jpg");
		}

		Member member = (Member) session.getAttribute("loginMember");
		nBook.setAdminId(member.getMemberId()); // 도서에 관리자 아이디 등록
		nSeries.setSeriesNo(1); // 첫화기때문에 무조건 1화등록
		hTag.setCategory("normal");// 일반도서라고 넣어준다
		int result = bService.registenorBook(nBook);
		result += bService.registeNorTag(hTag);
		result += bService.registNoriSeries(nSeries);

		mv.setViewName("redirect:/book/writerMenu-admin.do");
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
			String status = oBook.getStatus().charAt(0) + "";
			if (permission.equals("N") || status.equals("N")) {
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
			starOne.setMemberId(member.getMemberId());
			Star star = bService.getOneBookStar(starOne);// 내가 준 별점 가져오기

			int osListSize = osList.size(); // 다음화가될 시리즈 번호 계산
			int nextSeriesNo = osList.get(osListSize - 1).getSeriesNo() + 1;// 다음화가될 시리즈 번호 계산

			// 내 서재 등록여부 체크하기
			Library library = new Library();
			library.setMemberId(member.getMemberId());
			library.setBookNo(bookNo);
			library.setCategory("origin");
			int checkLibrary = bService.checkMybookMember(library);
			if (checkLibrary > 0) {
				mv.addObject("library", "in");
			}

			mv.addObject("hTag", OneTag);
			mv.addObject("star", star);
			mv.addObject("oBook", oBook);
			mv.addObject("osList", osList);
			mv.addObject("nextSeriesNo", nextSeriesNo);

			mv.setViewName("/book/bookmain");
		}
		return mv;

	}

	/**
	 * 일반도서 책 소개 페이지로 연결
	 * 
	 * @param mv
	 * @param session
	 * @param bookNo
	 * @return
	 */
	@RequestMapping(value = "/book/norBookInfo", method = RequestMethod.GET)
	public ModelAndView showOneNorBook(ModelAndView mv, HttpSession session, String bookNo) {
		Member member = (Member) session.getAttribute("loginMember");

		// 로그인 안하면 메인페이지로 보냄
		if (session.getAttribute("loginMember") == null) {
			mv.setViewName("redirect:/");
		} else {
			// 해당 책 가져오기
			NormalBook nBook = bService.showOneNorbook(bookNo); // 도서 메인테이블
			String status = nBook.getStatus().charAt(0) + "";
			if (status.equals("N")) {
				if (member.getAdminYN().equals("N")) {
					mv.addObject("msg", "이책은 삭제되었습니다");
					mv.setViewName("/common/errorPage");
					return mv;
				}
			}

			List<NormalBookSeries> nsList = bService.getNorSeriesTitle(bookNo); // 도서 시리즈 이름만 가져오기
			String category = "normal";
			HashTag hTag = bService.getBookTga(bookNo, category); // 도서 태그가져오기
			HashTag OneTag = changeKo(hTag);// 태그 한글로 변경

			// 별점 평균 계산하기
			int score = nBook.getScore();
			int count = nBook.getScoreCount();
			int scoreSet = 0;

			if (score == 0 || count == 0) {
				scoreSet = 0;
			} else {
				scoreSet = (int) Math.round(score / count);
			}
			nBook.setScore(scoreSet);

			Star starOne = new Star();
			starOne.setBookNo(bookNo);
			starOne.setCategory(category);
			starOne.setMemberId(member.getMemberId());
			Star star = bService.getOneBookStar(starOne);// 내가 준 별점 가져오기
			if (!status.equals("N")) {
				int nslistSize = nsList.size(); // 다음화 누르면 등록될 시리즈 번호 계산
				int nextSeriesNo = nsList.get(nslistSize - 1).getSeriesNo() + 1;// 다음화 누르면 등록될 시리즈 번호 계산
				mv.addObject("nextSeriesNo", nextSeriesNo);
			}

			// 내 서재 등록여부 체크하기
			Library library = new Library();
			library.setMemberId(member.getMemberId());
			library.setBookNo(bookNo);
			library.setCategory("normal");
			int checkLibrary = bService.checkMybookMember(library);
			if (checkLibrary > 0) {
				mv.addObject("library", "in");
			}

			mv.addObject("hTag", OneTag);
			mv.addObject("star", star);
			mv.addObject("nBook", nBook);
			mv.addObject("nsList", nsList);

			mv.setViewName("/bookadmin/bookmain-admin");
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

			} else if (category.equals("normal")) { // 일반 도서일때

				NormalBook nBook = bService.showOneNorbook(bookNo); // 책 번호로 한권 가지고오기
				WriterProfile oneWriter = bService.getProfile(nBook.getAdminId());// 책에 자가 아이디로 작가 프로필 전부 가져오기
				List<NormalBook> nbList = bService.allNorWirterbookTitle(nBook.getWriter()); // 작가의 모든 책 제목 가져오기
				List<NormalBookSeries> nsList = bService.allNorBookSeries(bookNo); // 해당 도서의 모든 시리즈 가져오기

				for (NormalBookSeries nbSeries : nsList) { // 미리보기 내용 만들기

					String contents = nbSeries.getContents().replaceAll("<[^>]*>", ""); // 태그 삭제하는 정규표현식
					if (contents.length() > 300) {
						contents = contents.substring(0, 300);
					}

					nbSeries.setContents(contents);
					mv.addObject("oneWriter", oneWriter);// 작가는 아니고 담당한 관리자의 헤더와 사진이 출력됨
					mv.addObject("nBook", nBook); // 책전송
					mv.addObject("nbList", nbList); // 모든 책 제목 전송
					mv.addObject("nsList", nsList); // 모든 시리즈 전송
					mv.setViewName("/bookadmin/bookstep");

				}
			} else {
				mv.setViewName("redirect:/");
			}

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
	 * 내 서재 등록하기
	 */
	@ResponseBody
	@RequestMapping(value = "/book/addMybooks.do", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String addMybooks(@ModelAttribute Library library, HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		library.setMemberId(memberId);
		int result = bService.checkMybookMember(library);// 내 서재에 등록됐는지 확인하기
		String msg = "";
		if (result < 1) {// 등록되어있지 않다면 등록
			result = bService.addMybook(library);
			msg = "ok";
		} else {// 등록되어있다면 삭제
			result = bService.removeMybook(library);
			msg = "remove";

		}

		return msg;
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
			String pCheck = paidCheck.charAt(0) + "";
			if (pCheck.equals("N") || result > 0 || wirterMember > 0 || member.getAdminYN().equals("Y")) {// 유료화가 아니거나,
																											// 구매했거나
				String bookTitle = bService.getBookTitle(bookNo + ""); // 책 이름 가져옴
				OriginBookSeries obSeries = bService.getOneSeries(seriesNo, bookNo); // 시리즈 한편가져오기

				// 해당책이 만약 허가되지 않았다면 작가와 관리자에는 열람 불가
				String permission = obSeries.getCheckPermission().charAt(0) + "";
				String status = obSeries.getStatus().charAt(0) + "";
				if (permission.equals("N") || status.equals("N")) {
					if ((wirterMember < 1) && member.getAdminYN().equals("N")) {

						mv.addObject("msg", "이책은 승인되지 않은 책입니다");
						mv.setViewName("/common/errorPage");

						return mv;

					}

				}
		
					// 조회수 영역
					int viewCount = bService.registViewCount(member, seriesNo, bookNo, pCheck);

					mv.addObject("bookTitle", bookTitle);
					mv.addObject("obSeries", obSeries);
					mv.setViewName("/book/bookstep-detail");
				

			} else {

				logger.info("유료화 인데 구입하지 않음");
				mv.setViewName("redirect:/");
			}
		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}

	/**
	 * 일반도서 한 편 열람
	 * 
	 * @param mv
	 * @param session
	 * @param seriesNo
	 * @param bookNo
	 * @return
	 */
	@RequestMapping(value = "/book/NordetailSeries.do", method = RequestMethod.GET)
	public ModelAndView detailNorbookSeries(ModelAndView mv, HttpSession session, int seriesNo, int bookNo) {
		if (session.getAttribute("loginMember") != null) {// 로그인 여부 체크

			String bookTitle = bService.getNorBookTitle(bookNo + ""); // 책 이름 가져옴
			String lang = bService.getlanguege(bookNo + "");
			NormalBookSeries nbSeries = bService.getOneNorBookSeries(bookNo, seriesNo); // 시리즈 한편가져오기

			mv.addObject("bookTitle", bookTitle);
			mv.addObject("nbSeries", nbSeries);
			mv.addObject("language", lang);

			if (lang.equals("ko")) {
				mv.setViewName("/bookadmin/bookstep-detail-admin");
			} else {
				mv.setViewName("/bookadmin/bookstep-detail-en");
			}
			Member member = (Member) session.getAttribute("loginMember");
			int result = bService.plusCountOne(member.getMemberId(), seriesNo, bookNo); // 조회수 추가하기

		} else {

			mv.setViewName("redirect:/");
		}

		return mv;
	}

	/**
	 * 일반도서 다음편 열람
	 * 
	 * @param mv
	 * @param session
	 * @param seriesNo
	 * @param bookNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/NornextSeires.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String NornextSeires(HttpSession session, int seriesNo, int bookNo) {
		if (session.getAttribute("loginMember") != null) {// 로그인 여부 체크

			List<NormalBookSeries> nsList = bService.getNorSeriesNo(bookNo);// 일반도서 한개의 모든 시리즈 번호 가지고옴
			int nextSeriesNo = 0;

			for (int i = 0; i < nsList.size() - 1; i++) { // 다음화 시리즈 번호 찾기
				if (nsList.get(i).getSeriesNo() == seriesNo) {
					nextSeriesNo = nsList.get(i + 1).getSeriesNo();
				}
			}

			String bookTitle = bService.getNorBookTitle(bookNo + ""); // 책 이름 가져옴
			if (nextSeriesNo != 0) {
				NormalBookSeries nbSeries = bService.getOneNorBookSeries(bookNo, nextSeriesNo); // 다음 시리즈 한편가져오기
				nbSeries.setBookTitle(bookTitle);

				Gson gson = new Gson();
				return gson.toJson(nbSeries).toString();
			} else {
				Gson gson = new Gson();
				return gson.toJson("no:end").toString();
			}

		} else {

			return "";
		}

	}

	/**
	 * 일반도서 이전편 열람
	 * 
	 * @param mv
	 * @param session
	 * @param seriesNo
	 * @param bookNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/NorprevSeires.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String NorprevSeires(HttpSession session, int seriesNo, int bookNo) {
		if (session.getAttribute("loginMember") != null) {// 로그인 여부 체크

			List<NormalBookSeries> nsList = bService.getNorSeriesNo(bookNo);// 일반도서 한개의 모든 시리즈 번호 가지고옴
			int prevSeriesNo = 0;

			if (seriesNo > 1) {
				for (int i = 0; i < nsList.size(); i++) { // 다음화 시리즈 번호 찾기
					if (i > 0 & nsList.get(i).getSeriesNo() == seriesNo) {
						prevSeriesNo = nsList.get(i - 1).getSeriesNo();
					}
				}
			}

			String bookTitle = bService.getNorBookTitle(bookNo + ""); // 책 이름 가져옴
			if (prevSeriesNo != 0) {
				NormalBookSeries nbSeries = bService.getOneNorBookSeries(bookNo, prevSeriesNo); // 다음 시리즈 한편가져오기
				nbSeries.setBookTitle(bookTitle);

				Gson gson = new Gson();
				return gson.toJson(nbSeries).toString();
			} else {
				Gson gson = new Gson();
				return gson.toJson("no:one").toString();
			}

		} else {

			return "";
		}

	}

	/**
	 * 피넛오리지널 다음편 열람
	 * 
	 * @param mv
	 * @param session
	 * @param seriesNo
	 * @param bookNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/OrinextSeires.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String OrinextSeires(HttpSession session, int seriesNo, int bookNo) {
		if (session.getAttribute("loginMember") != null) {// 로그인 여부 체크

			Member member = (Member) session.getAttribute("loginMember");
			String memberId = member.getMemberId();
			int result = bService.checkWriter(bookNo, memberId);
			int nextSeriesNo = 0;
			List<OriginBookSeries> osList;
			if (result > 0 || member.getAdminYN().equals("Y")) {// 작가이거나 관리자일때는 모든 책 열람가능
				osList = bService.getOneOriSeriesAllNo(bookNo);
			} else {
				osList = bService.getOriSeriesNo(bookNo);// 일반회원이면 삭제되지 않았으면서 허가된 책만 열람가능
			}

			for (int i = 0; i < osList.size() - 1; i++) { // 다음화 시리즈 번호 찾기
				if (osList.get(i).getSeriesNo() == seriesNo) {
					nextSeriesNo = osList.get(i + 1).getSeriesNo();
				}
			}

			String bookTitle = bService.getBookTitle(bookNo + ""); // 책 이름 가져옴

			if (nextSeriesNo != 0) {
				String paidChek = checkPaidbookSeries(nextSeriesNo, bookNo, session);
				String payCh = paidChek.charAt(0) + "";
				if (payCh.equals("Y")) { // 유료화되었다면
					String resultStr = checkPurchase(nextSeriesNo, bookNo, session); // 구입했거나 구독자인지 체크
					int resultPay = Integer.parseInt(resultStr);
					if (result > 0 || resultPay > 0 || member.getAdminYN().equals("Y")) {// 구입했거나 관리자라면
						OriginBookSeries obSeries = bService.getOneSeries(nextSeriesNo, bookNo); // 다음 시리즈 한편가져오기

						obSeries.setBookTitle(bookTitle);

						Gson gson = new Gson();
						return gson.toJson(obSeries).toString();
					} else {
						OriginBookSeries obSeries = bService.getOneSeries(nextSeriesNo, bookNo);
						JSONObject json = new JSONObject();
						json.put("paid", "no");
						json.put("nextSeriesNo", nextSeriesNo);
						return json.toString();
					}

				} else {// 유료화가 아니라면

					OriginBookSeries obSeries = bService.getOneSeries(nextSeriesNo, bookNo); // 다음 시리즈 한편가져오기

					obSeries.setBookTitle(bookTitle);

					Gson gson = new Gson();
					return gson.toJson(obSeries).toString();
				}
			} else {
				Gson gson = new Gson();
				return gson.toJson("no:end").toString();
			}

		} else {

			return "";
		}

	}

	/**
	 * 피넛오리지널 이전편 열람
	 * 
	 * @param mv
	 * @param session
	 * @param seriesNo
	 * @param bookNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/OriprevSeires.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String OriprevSeires(HttpSession session, int seriesNo, int bookNo) {
		if (session.getAttribute("loginMember") != null) {// 로그인 여부 체크

			List<OriginBookSeries> osList;
			Member member = (Member) session.getAttribute("loginMember");
			String memberId = member.getMemberId();
			int result = bService.checkWriter(bookNo, memberId);
			int prevSeriesNo = 0;

			if (result > 0 || member.getAdminYN().equals("Y")) { // 작성자이거나 관리자라면 모든책 열람가능
				osList = bService.getOneOriSeriesAllNo(bookNo);
			} else {
				osList = bService.getOriSeriesNo(bookNo);// 일반회원은 허가됐으며 삭제되지 않은책만 열람가능
			}

			if (seriesNo > 1) {
				for (int i = 0; i < osList.size(); i++) { // 다음화 시리즈 번호 찾기
					if (i > 0 & osList.get(i).getSeriesNo() == seriesNo) {
						prevSeriesNo = osList.get(i - 1).getSeriesNo();
					}
				}
			}

			String bookTitle = bService.getBookTitle(bookNo + ""); // 책 이름 가져옴
			if (prevSeriesNo != 0) { // 이전시리즈가 있다면
				OriginBookSeries obSeries = bService.getOneSeries(prevSeriesNo, bookNo); // 이전 시리즈 한편가져오기
				obSeries.setBookTitle(bookTitle);

				String paidChek = checkPaidbookSeries(prevSeriesNo, bookNo, session); // 이전 시리즈 유료화 여부 체크
				String payCh = paidChek.charAt(0) + ""; // 체크한 유료화 한자로 잘라서 비교하기 쉽게함

				if (payCh.equals("Y")) { // 유료화되었다면
					String resultStr = checkPurchase(prevSeriesNo, bookNo, session); // 구입했거나 구독자인지 체크
					int resultPay = Integer.parseInt(resultStr);
					if (resultPay > 0 || result > 0 || member.getAdminYN().equals("Y")) { // 구입했거나 작성자 이거나 관리자이라면
						logger.info(obSeries.getBookTitle() + "유료화시");
						Gson gson = new Gson();
						return gson.toJson(obSeries).toString();

					} else {// 구입하지 않앗다면
						JSONObject json = new JSONObject();
						json.put("paid", "no");
						json.put("prevSeriesNo", prevSeriesNo);
						return json.toString();
					}

				} else {// 유료화가 아니라면
					logger.info(obSeries.getBookTitle() + "무료");
					Gson gson = new Gson();
					return gson.toJson(obSeries).toString();
				}
			} else { // 이전 시리즈가 없다명
				Gson gson = new Gson();
				return gson.toJson("no:one").toString();
			}

		} else {

			return "";
		}

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
	 * 일반도서 다음 시리즈 등록
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/norSeriesRegist.do", method = RequestMethod.POST)
	public ModelAndView norSeriesRegist(ModelAndView mv, HttpSession session, @ModelAttribute NormalBookSeries nSeries,
			@RequestParam(value = "subPicture", required = false) MultipartFile subPicture,
			HttpServletRequest request) {

		// 삽화 저장
		String picName = subPicture.getOriginalFilename(); // 파일 이름 가져오기
		if (!picName.equals("")) {

			String subPicRename = fileSave(picName, request, subPicture, "sub");

			nSeries.setSubPic(picName);
			nSeries.setSubpicRename(subPicRename);
		} else {
			nSeries.setSubPic("defaultImg.jpg");
			nSeries.setSubpicRename("defaultImg.jpg");

		}

		int result = bService.registNorSeriesNext(nSeries); // 다음화 시리즈만 데이터베이스에 전송하기
		mv.addObject("bookNo", nSeries.getBookNo());
		mv.setViewName("redirect:/book/norBookInfo");

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

	/**
	 * 일반도서 시리즈 수정
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/norbookModify.do", method = RequestMethod.POST)
	public ModelAndView norSeriesModify(ModelAndView mv, HttpSession session, @ModelAttribute NormalBookSeries nbSeries,
			@RequestParam(value = "subPicture", required = false) MultipartFile subPicture,
			HttpServletRequest request) {

		// 삽화 수정
		String picName = subPicture.getOriginalFilename(); // 전송한 사진 이름 가져오기
		if (subPicture != null && !picName.equals("")) { // 만약 전송한 사진이 있거나 사진이름이 없는게 아니라면
			
			fileDelete(request,nbSeries.getSubpicRename());
			String subPicRename = fileSave(picName, request, subPicture, "sub");// 파일을 저장한다, 파일이름,리퀘스트,저장된파일, 추가할이름
			
			nbSeries.setSubPic(picName);
			nbSeries.setSubpicRename(subPicRename);

		}

		int result = bService.modifyNorSeries(nbSeries); // 다음화 시리즈만 데이터베이스에 전송하기
		mv.addObject("bookNo", nbSeries.getBookNo());
		mv.setViewName("redirect:/book/norBookInfo");

		return mv;

	}
	/**
	 * 피넛 오리지널 도서 인포 수정
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/ModifyOriBook.do", method = RequestMethod.POST)
	public ModelAndView oriModify(ModelAndView mv, HttpSession session,
			@ModelAttribute OriginBook oBook,@ModelAttribute HashTag hTag,
			@RequestParam(value = "coverpic", required = false) MultipartFile coverpic,
			HttpServletRequest request) {
		
		// 삽화 수정
		String picName = coverpic.getOriginalFilename(); // 전송한 사진 이름 가져오기
		if (coverpic != null && !picName.equals("")) { // 만약 전송한 사진이 있거나 사진이름이 없는게 아니라면
			
			fileDelete(request, oBook.getCoverRename());
			String coverPicRename = fileSave(picName, request, coverpic, "sub");// 파일을 저장한다, 파일이름,리퀘스트,저장된파일, 추가할이름
			
			oBook.setCover(picName);
			oBook.setCoverRename(coverPicRename);
			
		}
		
		int result = bService.modifyOriBookInfo(oBook); // 인포 수정 전송
		hTag.setCategory("origin");
		result += bService.modifyOriBookTag(hTag); // 인포 수정 전송
		
		
		mv.addObject("bookNo", oBook.getBookNo());
		mv.setViewName("redirect:/book/oriBookInfo");
		
		return mv;
		
	}
	/**
	 * 일반 도서 인포 수정
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/ModifyNorBook.do", method = RequestMethod.POST)
	public ModelAndView norModify(ModelAndView mv, HttpSession session,
			@ModelAttribute NormalBook nBook,@ModelAttribute HashTag hTag,
			@RequestParam(value = "coverpic", required = false) MultipartFile coverpic,
			HttpServletRequest request) {
		
		// 삽화 수정
		String picName = coverpic.getOriginalFilename(); // 전송한 사진 이름 가져오기
		if (coverpic != null && !picName.equals("")) { // 만약 전송한 사진이 있거나 사진이름이 없는게 아니라면
			
			fileDelete(request, nBook.getCoverRename());
			String coverPicRename = fileSave(picName, request, coverpic, "sub");// 파일을 저장한다, 파일이름,리퀘스트,저장된파일, 추가할이름
			
			nBook.setCover(picName);
			nBook.setCoverRename(coverPicRename);
			
		}
		
		int result = bService.modifyNorBooksInfo(nBook); // 인포 수정 전송
		hTag.setCategory("normal");
		result += bService.modifyOriBookTag(hTag); // 인포 수정 전송
		
		
		mv.addObject("bookNo", nBook.getBookNo());
		mv.setViewName("redirect:/book/norBookInfo");
		
		return mv;
		
	}

	/**
	 * 피넛 오리지널 시리즈 삭제
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/removeOriSeries.do", method = RequestMethod.GET)
	public ModelAndView oriSeriesRemove(ModelAndView mv, HttpSession session,
			@RequestParam(value = "bookNo") String bookNo, @RequestParam(value = "seriesNo") Integer seriesNo) {
		Member member = (Member) session.getAttribute("loginMember");

		if (member.getAdminYN().equals("N")) {

			return mv;
		} else {

			int result = bService.removeOriBookSeries(bookNo, seriesNo);
			mv.addObject("bookNo", bookNo);
		}

		mv.setViewName("redirect:/book/oriBookInfo");

		return mv;

	}

	/**
	 * 일반 도서 시리즈 삭제
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/removeNorBookSeries.do", method = RequestMethod.GET)
	public ModelAndView norSeriesRemove(ModelAndView mv, HttpSession session,
			@RequestParam(value = "bookNo") String bookNo, @RequestParam(value = "seriesNo") Integer seriesNo) {
		Member member = (Member) session.getAttribute("loginMember");

		if (member.getAdminYN().equals("N")) {

		} else {

			int result = bService.removeNorBookSeries(bookNo, seriesNo);
			mv.addObject("bookNo", bookNo);
		}

		mv.setViewName("redirect:/book/norBookInfo");

		return mv;

	}

	/**
	 * 피넛 오리지널 도서 하나 삭제
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/removeOribook.do", method = RequestMethod.GET)
	public ModelAndView removeOribook(ModelAndView mv, HttpSession session,
			@RequestParam(value = "bookNo") String bookNo) {
		Member member = (Member) session.getAttribute("loginMember");

		if (member.getAdminYN().equals("N")) {

		} else {

			int result = bService.removeOriBook(bookNo);
			mv.addObject("bookNo", bookNo);
		}

		mv.setViewName("redirect:/book/oriBookInfo");

		return mv;

	}

	/**
	 * 일반 도서 하나 삭제
	 * 
	 * @param mv
	 * @param session
	 * @param obSeries
	 * @param subPicture
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/book/removeNorBook.do", method = RequestMethod.GET)
	public ModelAndView removeNorbook(ModelAndView mv, HttpSession session,
			@RequestParam(value = "bookNo") int bookNo) {
		Member member = (Member) session.getAttribute("loginMember");

		if (member.getAdminYN().equals("N")) {

		} else {

			int result = bService.removeNorBook(bookNo);
			mv.addObject("bookNo", bookNo);
		}

		mv.setViewName("redirect:/book/norBookInfo");

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

	/** 언어 한글로 재저장 */
	private String languageChange(String language) {
		String lngChage = "";
		switch (language) {
		case "ko":
			lngChage = "한국어";
			break;
		case "en":
			lngChage = "영어";
			break;
		case "jp":
			lngChage = "일본어";
			break;

		}
		return lngChage;
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
		case "detective":
			category = "추리";
			break;
		case "romance":
			category = "로맨스";
			break;
		case "other":
			category = "기타";
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

	/**
	 * 메인페이지연결
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(ModelAndView mv, HttpSession session) {

		if (session.getAttribute("loginMember") == null) {
			mv.setViewName("/index");
		} else {

			List<NormalBook> nList = bService.getRankTopBook(); // 조회수 상위 3개 도서
			NormalBook topScoreDe = bService.getTopScore("detective"); //추리에서 가장 별점많은 1개 가져오기(평균아님)
			List<NormalBook> topScore4tale = bService.getTopScore4("tale"); //추리에서 가장 별점많은 1개 가져오기(평균아님)
			List<NormalBook> topScore4history = bService.getTopScore4("history"); //추리에서 가장 별점많은 1개 가져오기(평균아님)

			OriginBook topScoreNovel = bService.getTopScoreOri("novel"); //가장 별점많은 소설 가져오기
			List<OriginBook> topScore4taleOri = bService.getTopScore4Ori("tale");//오리지널 별점많은 동화 4개
			List<OriginBook> topScore4talePoem = bService.getTopScore4Ori("poem");//오리지널 별점많은 시 4개
					
			
			mv.addObject("nList", nList);
			mv.addObject("topScoreDe", topScoreDe);
			mv.addObject("topTale", topScore4tale);
			mv.addObject("topTOri", topScore4taleOri);
			mv.addObject("topPOri", topScore4talePoem);
			mv.addObject("topHistory", topScore4history);
			mv.addObject("topScoreNovel", topScoreNovel);
			mv.setViewName("/main");

		}
		return mv;
	}

}
