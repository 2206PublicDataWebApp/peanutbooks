package com.books.peanut.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.admin.service.BookApproveService;
import com.books.peanut.book.controller.BookPageController;
import com.books.peanut.book.domain.BookPage;
import com.books.peanut.member.domain.Member;

@Controller
public class AdminController {
	@Autowired
	private BookApproveService BAService;
	
	//전체회원 리스트
	@RequestMapping(value="/admin/adminListView.kh", method = RequestMethod.GET)
	public ModelAndView adminAllList(
			ModelAndView mv
			, HttpSession session
			, @RequestParam(value="page", required=false) Integer page) {
		Member member = (Member) session.getAttribute("loginMember");
		if((member.getAdminYN().charAt(0) + "").equals("N")) {
			mv.addObject("msg", "관리자만 접속가능합니다");
			mv.setViewName("/common/errorPage");
			
		}else {
			int getTotalCount = BAService.allMemberCount("","");
			int memberLimit = 10;
			BookPageController bpCont = new BookPageController();// 페이징 해주는 클래스
			BookPage bPage = bpCont.boardList(page, getTotalCount, memberLimit); // 클래스에서 페이징해온 숫자를 가지고옴
			
			if(getTotalCount>0){
				List<Member> mList = BAService.allMembers(bPage.getCurrentPage(), memberLimit);
				mv.addObject("mList", mList);
			}
			mv.addObject("bPage", bPage);
			mv.setViewName("/admin/memberListView");

		}
		return mv;
	}
	
	//회원상세보기
	@RequestMapping(value="/admin/memberDetailView.kh", method=RequestMethod.GET)
	public ModelAndView adminMemberDetail(
			ModelAndView mv
			, @RequestParam("page") int page
			, @RequestParam("memberId") String memberId) {
		try {
			Member member = BAService.printOneById(memberId);
			mv.addObject("member", member);
			mv.addObject("page", page);
			mv.setViewName("/admin/memberDetailView");
			
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	//회원삭제
	@RequestMapping(value="/admin/memberRemove.kh", method=RequestMethod.GET)
	public String adminMemberRemove(
			HttpSession session
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam("memberId") String memberId) {
		Member member = (Member)session.getAttribute("loginMember");
		if((member.getAdminYN().charAt(0)+"").equals("N")) {
			return "common/errorPage";
		}else {
			int result = BAService.removeOneById(memberId);
			if(result >0) {
				return "redirect:/admin/adminListView.kh?page="+page;
			}
		}
		return "redirect:/admin/adminListView.kh?page="+page;
	}
	//회원 정보 수정화면
	@RequestMapping(value="/admin/memberModifyView.kh", method=RequestMethod.GET)
	public ModelAndView memberModifyView(
			ModelAndView mv
			, @RequestParam("page") int page
			, @RequestParam("memberId") String memberId) {
		try {
			Member member = BAService.printOneById(memberId);
			mv.addObject("member", member);
			mv.addObject("page", page);
			mv.setViewName("/admin/memberModifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
	return mv;
	}
	//회원 정보 수정
	@RequestMapping(value="/admin/memberModify", method=RequestMethod.POST)
	public ModelAndView memberModify(
			ModelAndView mv
			, @RequestParam("page") int page
			, @ModelAttribute Member member) {
		try {
			int result = BAService.modifyOnById(member);
			if(result > 0) {
				mv.setViewName("redirect:/admin/adminListView.kh");
			}else {
				mv.setViewName("/admin/memberModifyView");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	//회원 검색
	@RequestMapping(value="/admin/MemberSearch.kh", method=RequestMethod.GET)
	public ModelAndView memberSearch(
			ModelAndView mv
			, HttpSession session
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue) {
		Member member = (Member) session.getAttribute("loginMember");
		if((member.getAdminYN().charAt(0) + "").equals("N")) {
			mv.addObject("msg", "관리자만 접속가능합니다");
			mv.setViewName("/common/errorPage");
			
		}else {
			int getTotalCount = BAService.allMemberCount(searchCondition, searchValue);
			int memberLimit = 10;
			BookPageController bpCont = new BookPageController();// 페이징 해주는 클래스
			BookPage bPage = bpCont.boardList(page, getTotalCount, memberLimit); // 클래스에서 페이징해온 숫자를 가지고옴
			
			if(getTotalCount>0){
				List<Member> mList = BAService.searchMembers(searchCondition, searchValue, bPage.getCurrentPage(), memberLimit);
				mv.addObject("mList", mList);
			}
			mv.addObject("bPage", bPage);
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.setViewName("/admin/memberListView");

		}
		return mv;
	}
	
}
