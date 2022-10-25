package com.books.peanut.admin.bookApprove.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.admin.bookApprove.service.BookApproveService;

@Controller
public class BookApproveController {
	@Autowired
	private BookApproveService bService;
	
	@RequestMapping(value="/admin/bookApprove/bookApproveView", method=RequestMethod.GET)
	public ModelAndView showQnaWriter(ModelAndView mv, HttpSession session) {
		if(session.getAttribute("loginMember")==null) {
			mv.addObject("msg", "관리자만 사용가능 합니다.");
			mv.setViewName("common/error");
			return mv;
			
		}

		mv.setViewName("/bookApprove/bookApproveList");
		return mv;
	}
}
