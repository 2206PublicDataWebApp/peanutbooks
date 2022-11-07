package com.books.peanut.book.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.PeanutCess;
import com.books.peanut.book.service.PeanutCessService;
import com.books.peanut.member.domain.Member;
import com.google.gson.Gson;

@Controller
public class PeanutCessController {
	@Autowired
	PeanutCessService pService;
	
	private static final Logger logger = LoggerFactory.getLogger(PeanutCessController.class);

	/** 게임 시작창 */
	@RequestMapping(value = "/book/Peanutcess.do", method = RequestMethod.GET)
	public ModelAndView StartGame(ModelAndView mv, HttpSession session) {
		if (session.getAttribute("loginMember") != null) {
			mv.setViewName("/peanutcess/peanutcess");
		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}

	/** 새게임하거나 불러오기창 */
	@RequestMapping(value = "/book/Peanutcess-1.do", method = RequestMethod.GET)
	public ModelAndView StartCheck(ModelAndView mv, HttpSession session) {

		if (session.getAttribute("loginMember") != null) {
			Member member = (Member) session.getAttribute("loginMember");
			int result = pService.CheckGame(member.getMemberId()); // 게임한적 있는지 확인하기
			String nickName = pService.getMemberName(member.getMemberId());// 멤버 닉네임 가져오기

			mv.addObject("result", result);
			mv.addObject("nickName", nickName);
			mv.setViewName("/peanutcess/peanutcess-1");

		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}

	/**
	 * 게임데이터 삭제
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/book/peanutcessRemove.do", produces = " text/plain;charset=utf-8", method = RequestMethod.GET)
	public String gameRemove(HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		int result = pService.removeGame(member.getMemberId());
		return "";
	}

	/**
	 * 북마크 가져오기
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/peanutcess/checkBookMark.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String checkBookMark(HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		List<NormalBook> bList = pService.selectBookMarkt(member.getMemberId());
		Gson gson = new Gson();
		return gson.toJson(bList);
	}

	/**
	 * 게임시작 정보 저장하기 하기
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/peanutcess/startGamesave.do", produces = " text/plain;charset=utf-8", method = RequestMethod.GET)
	public String startGame(HttpSession session, String pName, int bMonth, int bDay) {

		Member member = (Member) session.getAttribute("loginMember");
		PeanutCess pc = new PeanutCess();
		pc.setName(pName);
		pc.setBirthDay(bDay);
		pc.setBirthMonth(bMonth);
		pc.setMemberId(member.getMemberId());
		int result = pService.StartGameSave(pc);// 게임 데이터 저장
		return result + "";
	}

	/** 게임 시작창 */
	@RequestMapping(value = "/book/gameMain.do", method = RequestMethod.GET)
	public ModelAndView gameMain(ModelAndView mv, HttpSession session) {
		if (session.getAttribute("loginMember") != null) {
			Member member = (Member) session.getAttribute("loginMember");
			PeanutCess pCess = pService.getOneMemberDate(member.getMemberId());//저장한 분량 가져오기
			String nickName = pService.getMemberName(member.getMemberId());// 멤버 닉네임 가져오기
			
			mv.addObject("nickName", nickName);
			mv.addObject("bList", null);
			
			
			if (pCess.getTurn() == 0) { //만약 게임 시작이 처음이라 턴이 없다면

				List<NormalBook> bList = pService.selectBookMarkt(member.getMemberId());
				for (int i = 0; i < bList.size(); i++) {

					if (bList.get(i).getHashTag3().equals("horror")) {
						pCess.setStrong(pCess.getStrong()+5);

					} else if (bList.get(i).getHashTag3().equals("gag")) {
						pCess.setStudy(pCess.getStudy()+5);

					} else if (bList.get(i).getHashTag3().equals("move")) {
						pCess.setManner(pCess.getManner()+5);

					} else if (bList.get(i).getHashTag3().equals("heart")) {
						pCess.setPower(pCess.getPower()+5);

					} else if (bList.get(i).getHashTag3().equals("tear")) {
						pCess.setArt(pCess.getArt()+5);

					} else if (bList.get(i).getHashTag3().equals("popcorn")) {
						pCess.setCook(pCess.getCook()+5);

					} else if (bList.get(i).getHashTag3().equals("cider")) {
						pCess.setMusic(pCess.getMusic()+5);
						
					} else if (bList.get(i).getHashTag3().equals("none")) {
						pCess.setMoney(pCess.getMoney()+5);
						
					}

				

				}
				
				logger.info(pCess.toString());
				pCess.setTurn(0);
				pCess.setMemberId(member.getMemberId());
				int result = pService.firstBookStatus(pCess); //스테이터스 저장
				mv.addObject("bList", bList);
			}else {
				mv.setViewName("redirect:/book/gameStarting");

			}
			String age = pCess.getAge().substring(0,2);
			String mon = pCess.getAge().substring(2,4);
			mv.addObject("pCess", pCess);
			mv.addObject("age", age);
			mv.addObject("mon", mon);
			mv.setViewName("/peanutcess/peanutcess-firstmain");
		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}
	
	
	
	/** 스케쥴 시작 */
	@RequestMapping(value = "/book/gameStarting", method = RequestMethod.GET)
	public ModelAndView gameStarting(ModelAndView mv, HttpSession session) {

		if (session.getAttribute("loginMember") != null) {
			Member member = (Member) session.getAttribute("loginMember");
			PeanutCess pCess = pService.getOneMemberDate(member.getMemberId()); //저장한 분량 가져오기
			String nickName = pService.getMemberName(member.getMemberId());// 멤버 닉네임 가져오기

			//나이와 현재달 가져오기
			String age = pCess.getAge().substring(0,2);
			String mon = pCess.getAge().substring(2,4);
			mv.addObject("pCess", pCess);
			mv.addObject("age", age);
			mv.addObject("mon", mon);
			
			mv.addObject("nickName", nickName);
			mv.addObject("pCess", pCess);
			mv.setViewName("/peanutcess/peanutcess-main");

		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}

}
