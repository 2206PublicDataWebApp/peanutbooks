package com.books.peanut.book.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.HomeController;
import com.books.peanut.book.domain.WriterProfile;
import com.books.peanut.book.domain.HashTag;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
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
	public ModelAndView writerMenu(ModelAndView mv, HttpSession session) {

		if (session.getAttribute("loginMember") == null) {

			mv.addObject("msg", "로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");

		} else {
			Member member = (Member) session.getAttribute("loginMember");
			WriterProfile oneWriter = bService.getProfile(member.getMemberId());

			if (oneWriter != null) {
				mv.addObject("oneWriter", oneWriter);
			}

			mv.setViewName("/book/writermenu");
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
	 * 오리지널 시리즈 등록
	 * 
	 * @param mv
	 * @param Oribook
	 * @param oriSeries
	 * @param writePro
	 * @param session
	 * @return
	 */
	@RequestMapping("/book/registOriBook.do")
	public ModelAndView registOriBook(ModelAndView mv, @ModelAttribute OriginBook Oribook,
			@ModelAttribute OriginBookSeries oriSeries, @ModelAttribute WriterProfile writePro, HttpSession session) {
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
			wrtiePro.setMainPic("defaultImg");
			wrtiePro.setMainPicRename("defaultImg");

		}
		
		//프로필 헤더 사진 저장
		String headPic = headerPic.getOriginalFilename();
		if (!headPic.equals("")) {

			String headPicRename = fileSave(headPic, request, headerPic, "head");

			wrtiePro.setHeadPic(headPic);
			wrtiePro.setHeadPicRename(headPicRename);
		} else {
			wrtiePro.setHeadPic("defaultImg");
			wrtiePro.setHeadPicRename("defaultImg");

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
			oBook.setCoverRename("defaultImg");
			oBook.setCover("defaultImg");

		}

		// 삽화저장
		String subPic = subPicture.getOriginalFilename();
		if (!subPic.equals("")) {
			String subPicRename = fileSave(subPic, request, subPicture, "sub");
			oSeries.setSubPic(subPic);
			oSeries.setSubPicRename(subPicRename);
		} else {
			oSeries.setSubPic("defaultImg");
			oSeries.setSubPicRename("defaultImg");
		}
		
		Member member = (Member)session.getAttribute("loginMember");
		oBook.setMemberId(member.getMemberId());
		oSeries.setSeriesNo(1);
		oSeries.setPaidCheck("N");
		hTag.setCategory("origin");
		int result = bService.registeOriBook(oBook);
		result += bService.registeTag(hTag);
		result += bService.registOriSeries(oSeries);

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

	/* 사진삭제용 */
	private void fileDelete(HttpServletRequest request, String rename) {
		if (!rename.equals("defaultImg")) {
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
