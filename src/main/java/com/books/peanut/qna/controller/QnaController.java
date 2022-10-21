package com.books.peanut.qna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.books.peanut.qna.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qService;

	//qna등록 화면
	@RequestMapping(value="", method=RequestMethod.GET)
	public String showQnaWriter() {
		return "";
	}
}
