package com.books.peanut.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PeanutCessController {
	
	@RequestMapping(value="/book/Peanutcess.do")
	public ModelAndView StartGame(ModelAndView mv, HttpSession session) {
		return mv;
	}

}
