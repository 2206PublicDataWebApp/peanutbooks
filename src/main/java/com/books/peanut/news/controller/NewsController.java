package com.books.peanut.news.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.member.domain.Member;
import com.books.peanut.news.domain.News;
import com.books.peanut.news.service.NewsService;

@Controller
public class NewsController {
	@Autowired
	private NewsService nService;

	/**
	 * 알림 목록 조회
	 * @param mv
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/news/newsList.pb", method=RequestMethod.GET)
	public ModelAndView newsList(
			ModelAndView mv,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("loginMember"); // 로그인한 회원 정보를 member 객체에 담음
		String memberId = member.getMemberId(); // member 객체에서 id 값 가져와서 memberid 변수에 넣음
		List<News> nList = nService.showNewsList(memberId); // 로그인한 회원의 id 사용해서 해당 회원의 알림 목록 가져오기
		if(!nList.isEmpty()) { // 가져온 알림 목록이 비어있지 않으면
			mv.addObject("nList", nList); // 아래의 jsp로 알림 정보가 담긴 nlist 값을 넘김
		}
		mv.setViewName("news/newsView");
		return mv;
	}
	
	/**
	 * 알림 삭제
	 * @param model
	 * @param newsNo
	 * @return
	 */
	@RequestMapping(value="/news/deleteNews.pb", method=RequestMethod.GET)
	public String deleteNews(
			Model model,
			@RequestParam("newsNo") int newsNo) {
		try {
			nService.deleteNewsByNo(newsNo); // newsno(기본키)로 알림 선택해 삭제
			return "redirect:/news/newsList.pb"; // 삭제 후 알림 목록 화면으로 이동
		} catch (Exception e) {
			// 에러 확인용
			model.addAttribute("msg", e.toString());
			return "common/errorPage";
		}
	}
	
	/**
	 * 알림 읽음 처리
	 * @param newsNo
	 */
	@RequestMapping(value="/news/readNews.pb", method=RequestMethod.GET)
	public ModelAndView readNews(
			@RequestParam("newsNo") int newsNo,
			@RequestParam(value="newsType", defaultValue = "null") String newsType,
			@RequestParam(value="bookNo", defaultValue = "0") int bookNo,
			ModelAndView mv) {
		int result = nService.readNews(newsNo);
		if(result > 0) {
			if(newsType.equals("event")) {
				mv.setViewName("redirect:/book/attendaceEvent.do?newsNo="+newsNo);
			}else {
				mv.setViewName("redirect:/book/oriBookInfo?bookNo="+bookNo+"&newsNo="+newsNo);
			}
		}
		
		return mv;
	}
	
	// header - mypage tooltip으로 알림 개수 보내기
	@ResponseBody
	@RequestMapping(value="/news/sendNewsCount.pb", method=RequestMethod.GET)
	public String sendNewsCount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		int countNews = nService.countNews(loginMember.getMemberId());
		session.setAttribute("countNews", countNews);
		return String.valueOf(countNews);
	}

	// 출석체크 알림
	@ResponseBody
	@RequestMapping(value="/news/addEventNews.pb", method=RequestMethod.GET)
	public String addEventNews(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		String mNickname = loginMember.getmNickname();
		int result = nService.checkAttendExist(memberId);
		if(result <= 0) {
			int result2 = nService.checkEventExist(memberId);
			if(result2 <= 0) {
				String newsContents = mNickname+"님 오늘 출석체크를 안 하셨네요! 출석체크하고 땅콩 받아가세요♥";
				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("memberId", memberId);
				paramMap.put("newsContents", newsContents);
				nService.insertEventNews(paramMap);
			}
		}
		return null;
	}
}
