package com.books.peanut.news.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.news.service.NewsService;
import com.books.peanut.notice.domain.Notice;

@Controller
public class NewsController {
	@Autowired
	private NewsService nService;
	
	/**
	 * 알림 - 내 알림 화면
	 * @return
	 */
	@RequestMapping(value="/news/myNews.pb", method=RequestMethod.GET)
	public String myNewsView() {
		return "news/news-my";
	}
	
	// 공지 알림 목록
	@RequestMapping(value="/news/newsList.pb", method=RequestMethod.GET)
	public ModelAndView newsList(ModelAndView mv) {
		List<Notice> nList = nService.showNoticeList();
		if(!nList.isEmpty()) {
			mv.addObject("nList", nList);
		}
		mv.setViewName("news/news-my");
		return mv;
	}
}
