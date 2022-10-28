package com.books.peanut.admin.bookApprove.controller;

import java.awt.print.Book;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.admin.bookApprove.service.BookApproveService;
import com.books.peanut.member.domain.Member;

@Controller
public class BookApproveController {
	@Autowired
	private BookApproveService bService;
	
	@RequestMapping(value="/admin/bookApprove/bookApproveView", method=RequestMethod.GET)
	public ModelAndView showBookApproveView(ModelAndView mv, HttpSession session) {
//		로그인 유저용
		Member member = (Member) session.getAttribute("loginMember");
		if(member == null) {
			mv.setViewName("redirect:/member/loginView.pb");
		}else if (member.getAdminYN().equals("Y")) {
			mv.setViewName("admin/bookApprove/bookApproveList");
		}else {
			mv.setViewName("/main");
		}
		return mv;
	}

	
//	@RequestMapping(value="/admin/bookApprove/bookApproveList", method=RequestMethod.GET)
//	public ModelAndView showBookApproveList(
//			ModelAndView mv
//			, @RequestParam(value="page", required=false) Integer page
//			, HttpSession session
//			, HttpServletRequest request) {
//		try {
//			session = request.getSession();
//			String adminYN = (String)session.getAttribute("adminYN");
//			session.setAttribute("adminYN", adminYN);
//			int currentPage = (page != null) ? page : 1;
//			int totalCount = bService.getBooksTotalCount("","");
//			int bookALimit = 10;
//			int naviLimit = 5;
//			int maxPage;
//			int startNavi;
//			int endNavi;
//			maxPage = (int)((double)totalCount/bookALimit + 0.9);
//			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
//			endNavi = startNavi + naviLimit - 1;
//			if(maxPage < endNavi) {
//				endNavi = maxPage;
//			}
//			List<Book> baList = bService.printAllBooks(currentPage, bookALimit);
//			if(!baList.isEmpty()) {
//				mv.addObject("urlVal", "bookApproveList");
//				mv.addObject("maxPage", maxPage);
//				mv.addObject("currentPage", currentPage);
//				mv.addObject("startNavi", startNavi);
//				mv.addObject("endNavi", endNavi);
//				mv.addObject("baList", baList);
//				mv.addObject("adminYN", adminYN);
//			}
//			mv.setViewName("/bookApproveList");
//		} catch (Exception e) {
//			e.printStackTrace();
//			mv.addObject("msg", e.getMessage());
//			mv.setViewName("/common/errorPage");
//		}
//		return mv;
//	}
}
