package com.books.peanut;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	
	@RequestMapping(value = "/footer", method = RequestMethod.GET)
	public String footer() {

		

		
		return "/footer/footer";
	}
	
	@RequestMapping(value = "/header", method = RequestMethod.GET)
	public String header() {
	
		return "/header/header";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main() {
	
		return "/main";
	}

	@RequestMapping(value = "/bookmark", method = RequestMethod.GET)
	public String bookmark() {
		
		return "/book/bookmark";
	}
	@RequestMapping(value = "/bookserach", method = RequestMethod.GET)
	public String booksearch() {
		
		return "/book/bookSearch";
	}
	
	@RequestMapping(value = "/adminheader", method = RequestMethod.GET)
	public String adminheader() {
		
		return "/header/adminheader";
	}
	@RequestMapping(value = "/oribooklist", method = RequestMethod.GET)
	public String oribooklist() {
		
		return "/book/booklist-ori";
	}
	
	@RequestMapping(value = "/norbooklist", method = RequestMethod.GET)
	public String norbooklist() {
		
		return "/book/booklist-nor";
	}
	
	@RequestMapping(value = "/searchbooklist", method = RequestMethod.GET)
	public String searchbooklist() {
		
		return "/book/booklist-search";
	}

	@RequestMapping(value = "/ej", method = RequestMethod.GET)
	public String ejhome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "eunJeonghome";
	}
	
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "eunJeongmanagerPage";
	}
	
	@RequestMapping(value = "/bookmain", method = RequestMethod.GET)
	public String bookmain() {
		
		return "/book/bookmain";
	}
	@RequestMapping(value = "/bookstep", method = RequestMethod.GET)
	public String bookstep() {
		
		return "/book/bookstep";
	}
	
	@RequestMapping(value = "/bookdetail", method = RequestMethod.GET)
	public String bookdetail() {
		
		return "/book/bookstep-detail";
	}
	
	@RequestMapping(value = "/bookdetailen", method = RequestMethod.GET)
	public String bookdetaile() {
		
		return "/book/bookstep-detail-en";
	}
	
	@RequestMapping(value = "/writermenu", method = RequestMethod.GET)
	public String writermenu() {
		
		return "/book/writermenu";
	}
	@RequestMapping(value = "/bookregist", method = RequestMethod.GET)
	public String bookregist() {
		
		return "/book/bookregist";
	}
	@RequestMapping(value = "/bookmodify", method = RequestMethod.GET)
	public String bookmodify() {
		
		return "/book/bookmodify";
	}
	@RequestMapping(value = "/bookmodifyadmin", method = RequestMethod.GET)
	public String bookmodifyadmin() {
		
		return "/book/bookmodify-admin";
	}
	@RequestMapping(value = "/bookregistadmin", method = RequestMethod.GET)
	public String bookregistadmin() {
		
		return "/book/bookregist-admin";
	}
	@RequestMapping(value = "/bookregistnext", method = RequestMethod.GET)
	public String bookregistnext() {
		
		return "/book/bookregist-next";
	}
	@RequestMapping(value = "/booknextadmin", method = RequestMethod.GET)
	public String booknextadmin() {
		
		return "/book/bookregist-nextAdmin";
	}

	
	
}
