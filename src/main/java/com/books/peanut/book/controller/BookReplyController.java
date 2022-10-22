package com.books.peanut.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.book.domain.OriginBookReply;
import com.books.peanut.book.service.ReplyService;
import com.books.peanut.member.domain.Member;

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
		String msg = "";
	
			obReply.setMemberId(member.getMemberId());
			int result = rService.registOneReply(obReply);
			msg = "등록성공";
		
		return result+"";
	}
	
	@ResponseBody
	@RequestMapping(value = "/book/originBookAllReply", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String AllReplyList(@RequestParam("bookNo") String bookNo) {
		
		return "";
		
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
