package com.books.peanut.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.admin.domain.ModifyBookSeries;
import com.books.peanut.admin.service.BookApproveService;
import com.books.peanut.book.controller.BookPageController;
import com.books.peanut.book.domain.BookPage;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.service.BookService;
import com.books.peanut.member.domain.Member;

@Controller
public class BookApproveController {
	@Autowired
	private BookApproveService BAService;
	@Autowired
	private BookService bService;
	//도서 승인리스트 출력
//	@RequestMapping(value = "/admin/writerMenu.do", method = RequestMethod.GET)
//	public ModelAndView writerMenu(ModelAndView mv, HttpSession session,
//			@RequestParam(value = "page", required = false) Integer page) {
//
//			mv.setViewName("redirect:/admin/approveYN.kh");
//		
//		return mv;
//
//	}
	//도서 승인 기능
	@RequestMapping(value="/admin/approve.kh", method=RequestMethod.GET)
	public ModelAndView bookApprove(
			@RequestParam("bookNo") Integer bookNo
			, @RequestParam("seriesNo") Integer seriesNo
			, @RequestParam(value="currentPage", required=false) Integer page
			, ModelAndView mv
			, HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		if((member.getAdminYN().charAt(0) + "").equals("N")) {
			mv.addObject("msg", "관리자만 접속가능합니다");
			mv.setViewName("/common/errorPage");
		}else {
			try {
				int result = BAService.approveBooks(bookNo,seriesNo);
				mv.addObject("page", page);
				if(result > 0) {
					mv.setViewName("redirect:/admin/writerMenu.do");
				}else {
					mv.addObject("msg", "승인 실패");
					mv.setViewName("common/errorPage");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("/common/errorPage");
			}
		}
		return mv;
	}
	
	//도서 리스트(all, 승인, 보류)
	@RequestMapping(value="/admin/approveYN.kh", method=RequestMethod.GET)
	public ModelAndView approveYNList(
			ModelAndView mv
			, @RequestParam(value="checkPermission", defaultValue = "all") String checkPermission
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam(value="step", required=false, defaultValue = "date")String step) {
		try {
			int getTotalCount = BAService.checkPermissionCount(checkPermission);
			int boardLimit = 20;
			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, getTotalCount, boardLimit);
			
			if(getTotalCount > 0) {
				List<OriginBookSeries> osList = BAService.checkPermission(bPage.getCurrentPage(), boardLimit, checkPermission, step);
				
				for (int i = 0; i < osList.size(); i++) {
					String bookTitle = bService.getBookTitle(osList.get(i).getBookNo());
					osList.get(i).setBookTitle(bookTitle);// 각 시리즈의 책 제목 가지고옴
				}
				mv.addObject("osList", osList);
			}
			mv.addObject("checkPermission", checkPermission);
			mv.addObject("bPage", bPage);
			mv.setViewName("/bookApprove/BAwritermenu");
			
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	//재승인 리스트
	@RequestMapping(value="/admin/reApproveList.kh", method=RequestMethod.GET)
	public ModelAndView reApproveList(
			ModelAndView mv
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int getTotalCount = BAService.reApproveCount();   //전체 게시물갯수
			int boardLimit = 20;  //20개씩 출력
			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, getTotalCount, boardLimit);
			
			if(getTotalCount > 0) {
				List<ModifyBookSeries> mbList = BAService.reApproveList(bPage.getCurrentPage(), boardLimit);
				
				mv.addObject("mbList", mbList);
				mv.addObject("bPage", bPage);
				
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		mv.setViewName("/bookApprove/BAreApprovemenu");
		return mv;
		
	}
	//도서 재승인 기능
	@RequestMapping(value="/admin/reApprove.kh", method=RequestMethod.GET)
	public ModelAndView bookReApprove(
			@RequestParam("bookNo") Integer bookNo
			, @RequestParam("seriesNo") Integer seriesNo
			, @RequestParam(value="currentPage", required=false) Integer page
			, ModelAndView mv
			, HttpSession session) {
//		Member member = (Member)session.getAttribute("loginMember");
//		if((member.getAdminYN().charAt(0)+"").contentEquals("N")) {
//			mv.addObject("msg", "관리자만 접속가능합니다.");
//			mv.setViewName("/common/errorPage");
//		}else {
			try {
				int result = BAService.reApproveBooks(bookNo,seriesNo);
				mv.addObject("page", page);
				if(result > 0) {
					mv.setViewName("redirect:/admin/reApproveList.kh");
				}else {
					mv.addObject("msg", "승인 실패");
					mv.setViewName("common/errorPage");
				}
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("/common/errorPage");
			}
//		}
		return mv;
	}
	
}
