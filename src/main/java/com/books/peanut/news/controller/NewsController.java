package com.books.peanut.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.books.peanut.news.service.NewsService;

@Controller
public class NewsController {
	@Autowired
	private NewsService nService;
}
