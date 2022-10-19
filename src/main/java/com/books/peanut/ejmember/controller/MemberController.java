package com.books.peanut.ejmember.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.consult.service.ConsultService;
import com.books.peanut.ejmember.domain.Member;
import com.books.peanut.ejmember.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
	
	@RequestMapping(value="/member/joinView.kh", method=RequestMethod.GET)
	public String memberjoinView() {
		return "member/join";
	}
//회원가입	
	@RequestMapping(value="/member/register.kh", method=RequestMethod.POST)
	public ModelAndView memberJoin(
			@ModelAttribute Member member 
			, ModelAndView mv) {
		try {
			int result=mService.registerMember(member);
			if(result>0){
				mv.setViewName("redirect:/ej.kh");
			}else {
				mv.addObject("msg", "회원가입실패!");
				mv.setViewName("error");
			}
		}catch (Exception e){
			mv.addObject("msg",e.toString()).setViewName("error");
		}
		return mv;
	}
//로그인
	@RequestMapping(value="/member/login.kh", method=RequestMethod.POST)
	public ModelAndView memberLogin(
			@ModelAttribute Member member
			,ModelAndView mv
			,HttpServletRequest request
			,HttpSession session) {
		try {
			Member loginUser=mService.loginMember(member);
			
			session.setAttribute("loginUser",loginUser);			
		
			
			if(loginUser!=null) {
				//mv.addObject("loginUser", loginUser);
				// System.out.println(loginUser.toString());
				mv.addObject("loginUser", loginUser);
				mv.setViewName("redirect:/ej.kh");
			}else {				
				mv.addObject("msg","정보 미확인");
				mv.setViewName("error");
			}
		}catch(Exception e){
			mv.addObject("msg",e.toString()).setViewName("error");
			
		}
		return mv;
	}

}
