package com.books.peanut.book.controller;

import java.util.List;
import java.util.Random;

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
	
	
	
	/** 게임 엔딩 바로보기 */
	@RequestMapping(value = "/book/restEnd.do", method = RequestMethod.GET)
	public ModelAndView restEnd(ModelAndView mv, HttpSession session) {
		if (session.getAttribute("loginMember") != null) {
			Member member = (Member) session.getAttribute("loginMember");//회원정보 가져오기
			PeanutCess pCess = pService.getOneMemberDate(member.getMemberId());//게임내역 불러오기
			pCess.setTurn(56);//턴 마지막으로 만들기
			int result = pService.saveStatusAll(pCess);
			
			mv.setViewName("redirect:/book/gameStarting");
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
	/**
	 * 복권사기
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/peanutcess/rotto.do", produces = " text/plain;charset=utf-8", method = RequestMethod.GET)
	public String rotto(HttpSession session) {
		
		Member member = (Member) session.getAttribute("loginMember");
		PeanutCess pCess = pService.getOneMemberDate(member.getMemberId());
		Random rd = new Random();
		int i = rd.nextInt(10);
		boolean rotto = false;
		pCess.setMoney(pCess.getMoney()-50);
		if(i%2==0) {
			pCess.setMoney(pCess.getMoney()+200);
			rotto= true;
		}
		
		int result = pService.saveStatusAll(pCess);// 능력치 입력
		return rotto + "";
	}
	/**
	 * 아이템사기
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/peanutcess/buyitem.do", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String buyitem(HttpSession session, String item) {
		
		Member member = (Member) session.getAttribute("loginMember");
		PeanutCess pCess = pService.getOneMemberDate(member.getMemberId());

		pCess.setMoney(pCess.getMoney()-100);
		if(item.equals("artBook")) {
			pCess.setArt(pCess.getArt()+10);
			pCess.setStudy(pCess.getStudy()+10);
			
		}else if(item.equals("musicBook")){
			pCess.setMusic(pCess.getMusic()+10);
			pCess.setManner(pCess.getManner()+10);
		}else {
			pCess.setStrong(pCess.getStrong()+10);
			pCess.setPower(pCess.getPower()+10);
		}
		
		int result = pService.saveStatusAll(pCess);// 능력치 입력
		Gson gson = new Gson();
		
		return gson.toJson(pCess);
	}

	/** 게임 시작창 */
	@RequestMapping(value = "/book/gameMain.do", method = RequestMethod.GET)
	public ModelAndView gameMain(ModelAndView mv, HttpSession session) {
		if (session.getAttribute("loginMember") != null) {
			Member member = (Member) session.getAttribute("loginMember");
			PeanutCess pCess = pService.getOneMemberDate(member.getMemberId());// 저장한 분량 가져오기
			String nickName = pService.getMemberName(member.getMemberId());// 멤버 닉네임 가져오기

			mv.addObject("nickName", nickName);
			mv.addObject("bList", null);

			if (pCess.getTurn() == 0) { // 만약 게임 시작이 처음이라 턴이 없다면

				List<NormalBook> bList = pService.selectBookMarkt(member.getMemberId());
				for (int i = 0; i < bList.size(); i++) {

					if (bList.get(i).getHashTag3().equals("horror")) {
						pCess.setStrong(pCess.getStrong() + 5);

					} else if (bList.get(i).getHashTag3().equals("gag")) {
						pCess.setStudy(pCess.getStudy() + 5);

					} else if (bList.get(i).getHashTag3().equals("move")) {
						pCess.setManner(pCess.getManner() + 5);

					} else if (bList.get(i).getHashTag3().equals("heart")) {
						pCess.setPower(pCess.getPower() + 5);

					} else if (bList.get(i).getHashTag3().equals("tear")) {
						pCess.setArt(pCess.getArt() + 5);

					} else if (bList.get(i).getHashTag3().equals("popcorn")) {
						pCess.setCook(pCess.getCook() + 5);

					} else if (bList.get(i).getHashTag3().equals("cider")) {
						pCess.setMusic(pCess.getMusic() + 5);

					} else if (bList.get(i).getHashTag3().equals("none")) {
						pCess.setMoney(pCess.getMoney() + 5);

					}

				}

				pCess.setTurn(0);
				pCess.setMemberId(member.getMemberId());
				int result = pService.firstBookStatus(pCess); // 스테이터스 저장
				
				mv.addObject("bList", bList);
				mv.setViewName("/peanutcess/peanutcess-firstmain");
				String ageInt = pCess.getAge() + "";
				String age = ageInt.substring(0, 2);
				String mon = ageInt.substring(2, 4);
				mv.addObject("pCess", pCess);
				mv.addObject("age", age);
				mv.addObject("mon", mon);
				return mv;
			} else {
				mv.setViewName("redirect:/book/gameStarting");

			}
			String ageInt = pCess.getAge() + "";
			String age = ageInt.substring(0, 2);
			String mon = ageInt.substring(2, 4);
			mv.addObject("pCess", pCess);
			mv.addObject("age", age);
			mv.addObject("mon", mon);
			mv.setViewName("redirect:/book/gameStarting");
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
			PeanutCess pCess = pService.getOneMemberDate(member.getMemberId()); // 저장한 분량 가져오기
			String nickName = pService.getMemberName(member.getMemberId());// 멤버 닉네임 가져오기

			// 나이와 현재달 가져오기
			String ageStr = pCess.getAge() + "";
			String age = ageStr.substring(0, 2);
			String mon = ageStr.substring(2, 4);
			mv.addObject("pCess", pCess);
			mv.addObject("age", age);
			mv.addObject("mon", mon);

			mv.addObject("nickName", nickName);

			if (pCess.getTurn() >= 56) {
				logger.info("게임엔딩");
				String ending = endingMethod(pCess);
				mv.setViewName("/peanutcess/peanutcess-ending");
				mv.addObject("ending", ending);
				return mv;

			}

			mv.setViewName("/peanutcess/peanutcess-main");

		} else {
			mv.setViewName("redirect:/");
		}
		return mv;
	}

	/**
	 * 엔딩 판단하기
	 * 
	 * @param pCess
	 * @return
	 */
	private String endingMethod(PeanutCess pCess) {

		String ending = "";

		if (pCess.getArt() < 10 && pCess.getCook() < 10 && pCess.getManner() < 10 && pCess.getPower() < 10
				&& pCess.getMusic() < 10 && pCess.getStrong() < 10 && pCess.getStudy() < 10) {
			ending = "neet";// 모든 능력치가 10미만일때는 백수엔딩
			int result1 = pService.addEnding(pCess.getMemberId(), ending);
		} else if (pCess.getArt() < 30 || pCess.getCook() < 30 || pCess.getManner() < 30 && pCess.getPower() < 30
				|| pCess.getMusic() < 30 || pCess.getStrong() < 30 || pCess.getStudy() < 30) {
			// 능력치가 30을 못넘는게 있을때는 알바엔딩 분기
			if (pCess.getFarm() > pCess.getCafe() && pCess.getFarm() > pCess.getChild()) { // 농장알바를 제일 많이했다면
				ending = "farmEnd";// 농부엔딩
			} else if (pCess.getCafe() > pCess.
					getChild()) {// 식당알바를 제일 많이했다면
				ending = "cafeEnd"; // 식당엔딩
				int result1 = pService.addEnding(pCess.getMemberId(), ending);
			} else if (pCess.getChild() > 0) {// 보모알바를 많이 했다면
				ending = "childEnd";
				int result1 = pService.addEnding(pCess.getMemberId(), ending);
			} else {
				ending = "freetor";// 알바한적 없다면 프리터
				int result1 = pService.addEnding(pCess.getMemberId(), ending);
			}

		} else if (pCess.getArt() < 60 || pCess.getCook() < 60 || pCess.getManner() < 60 && pCess.getPower() < 60
				|| pCess.getMusic() < 60 || pCess.getStrong() < 60 || pCess.getStudy() < 60) {
			// 능력치가 전부 30이 넘고 능력치가 60보다 작은것이 있을때는 직업엔딩분기
			if (pCess.getMaterial() > pCess.getMusicschool() && pCess.getMaterial() > pCess.getArtschool()) {
				// 무술 공부를 제일 많이했다면 장군에딩
				ending = "genal";
				int result1 = pService.addEnding(pCess.getMemberId(), ending);
			} else if (pCess.getMusicschool() > pCess.getArtschool()) {// 음악공부를 제일 많이 했다면
				ending = "musician"; // 뮤지션엔딩
				int result1 = pService.addEnding(pCess.getMemberId(), ending);
			} else if (pCess.getArtschool() > 0) {// 미술공부를 제일 많이 했다면
				ending = "artist";// 미술가 엔딩
				int result1 = pService.addEnding(pCess.getMemberId(), ending);
			} else {// 공부한적 없다면
				ending = "servent";// 공무원엔딩
				int result1 = pService.addEnding(pCess.getMemberId(), ending);
			}
		} else if (pCess.getArt() >= 60 || pCess.getCook() >= 60 || pCess.getManner() >= 60 && pCess.getPower() >= 60
				|| pCess.getMusic() >= 60 || pCess.getStrong() >= 60 || pCess.getStudy() >= 60) {
			ending = "qeen";// 모든 능력치 60이상 여왕엔딩

			int result = pService.getEnding(pCess.getMemberId());
			if (result == 0) {// 저장된 엔딩없으면 땅콩 주고 엔딩 저장
				int result1 = pService.addEnding(pCess.getMemberId(), ending);

			}
		} else {
			ending = "servent";// 공무원엔딩
			int result1 = pService.addEnding(pCess.getMemberId(), ending);
		}

		return ending;
	}

	/**
	 * 스케쥴 실행하기
	 * 
	 * @param mv
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/peanutcess/scheDo1.do", method = RequestMethod.GET)
	public ModelAndView scheDo(ModelAndView mv, HttpSession session, String sche1, String sche2, String sche3,
			String sche4) {
		Member member = (Member) session.getAttribute("loginMember");
		PeanutCess pCess = pService.getOneMemberDate(member.getMemberId()); // 저장한 분량 가져오기

		String sche1Result = "ok"; // 스케쥴 실행가능여부 체크
		String sche2Result = "ok";
		String sche3Result = "ok";
		String sche4Result = "ok";

		// 생일체크하기
		boolean sche1Birth = false;
		boolean sche2Birth = false;
		boolean sche3Birth = false;
		boolean sche4Birth = false;

		String AgeStr = pCess.getAge() + "";

		int thisMoonth = Integer.parseInt(AgeStr.substring(2, 4));// 현재 월 체크

		if (pCess.getBirthMonth() == thisMoonth) {// 생일이라면
			pCess.setMoney(pCess.getMoney() + 20);// 돈을준다.
			sche1Birth = true;
		} else if (pCess.getBirthMonth() == thisMoonth + 1) {// 생일이라면
			pCess.setMoney(pCess.getMoney() + 20);// 돈을준다.
			sche2Birth = true;
		} else if (pCess.getBirthMonth() == thisMoonth + 2) {// 생일이라면
			pCess.setMoney(pCess.getMoney() + 20);// 돈을준다.
			sche3Birth = true;
		} else if (pCess.getBirthMonth() == thisMoonth + 3) {// 생일이라면
			pCess.setMoney(pCess.getMoney() + 20);// 돈을준다.
			sche4Birth = true;
		}

		// 첫번째 스케쥴
		if (sche1.equals("material") || sche1.equals("art") || sche1.equals("music")) {// 교육을 선택했다면
			if (pCess.getMoney() < 20) {// 돈이 없을때
				sche1Result = "fail";// 스케쥴 실패
				sche1 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {
				if (pCess.getStress() > 100) {
					sche1Result = "fail";// 스케쥴 실패
					sche1 = "rest";// 휴식이 된다
					pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
				} else {

					pCess = checkStatus(sche1, pCess);
				}
			}

		} else {
			if (pCess.getStress() > 100) {
				sche1Result = "fail";// 스케쥴 실패
				sche1 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {
				pCess = checkStatus(sche1, pCess); // 능력치 조절

			}
		}

		// 두번째 스케쥴
		if (sche2.equals("material") || sche2.equals("art") || sche2.equals("music")) {// 교육을
			// 선택했다면
			if (pCess.getMoney() < 20) {// 돈이 없을때
				sche2Result = "fail";// 스케쥴 실패
				sche2 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {

				if (pCess.getStress() > 100) {
					sche1Result = "fail";// 스케쥴 실패
					sche1 = "rest";// 휴식이 된다
					pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
				} else {

					pCess = checkStatus(sche2, pCess);
				}
			}
		} else {

			if (pCess.getStress() > 100) {
				sche1Result = "fail";// 스케쥴 실패
				sche1 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {
				pCess = checkStatus(sche2, pCess);// 능력치 조절
			}
		}

		// 세번째 스케쥴
		if (sche3.equals("material") || sche3.equals("art") || sche3.equals("music")) {// 교육을
			// 선택했다면
			if (pCess.getMoney() < 20) {// 돈이 없을때
				sche1Result = "fail";// 스케쥴 실패
				sche3 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {

				if (pCess.getStress() > 100) {
					sche1Result = "fail";// 스케쥴 실패
					sche1 = "rest";// 휴식이 된다
					pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
				} else {

					pCess = checkStatus(sche3, pCess);
				}
			}
		} else {
			if (pCess.getStress() > 100) {
				sche1Result = "fail";// 스케쥴 실패
				sche1 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {
				pCess = checkStatus(sche3, pCess);
			}
		}

		// 네번째 스케쥴
		if (sche1.equals("material") || sche1.equals("art") || sche1.equals("music")) {// 교육을
			// 선택했다면
			if (pCess.getMoney() < 20) {// 돈이 없을때
				sche1Result = "fail";// 스케쥴 실패
				sche1 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {

				if (pCess.getStress() > 100) {
					sche1Result = "fail";// 스케쥴 실패
					sche1 = "rest";// 휴식이 된다
					pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
				} else {
					pCess = checkStatus(sche4, pCess);
				}
			}
		} else {
			if (pCess.getStress() > 100) {
				sche1Result = "fail";// 스케쥴 실패
				sche1 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {
				pCess = checkStatus(sche4, pCess);
			}
		}

		pCess.setTurn(pCess.getTurn() + 4); // 턴 4개 더하기

		logger.info(pCess.getAge() + "현재나이");

		pCess.setAge(pCess.getAge() + 4); // 나이 4개월 더하기
		String ageStr = pCess.getAge() + "";
		int age = Integer.parseInt(ageStr.substring(2, 4)); // 더한 달 체크

		if (age == 13) {
			pCess.setAge(pCess.getAge() - 12);
			pCess.setAge(pCess.getAge() + 100);

		}
		if (pCess.getStress() < 0) {
			if (pCess.getStress() > 100) {
				sche1Result = "fail";// 스케쥴 실패
				sche1 = "rest";// 휴식이 된다
				pCess.setStress(pCess.getStress() - 10);// 스트레스 줄기
			} else {
				pCess.setStress(0);
			}

		}

		mv.addObject("sche1", sche1);
		mv.addObject("sche2", sche2);
		mv.addObject("sche3", sche3);
		mv.addObject("sche4", sche4);
		mv.addObject("sche1Result", sche1Result);
		mv.addObject("sche2Result", sche2Result);
		mv.addObject("sche3Result", sche3Result);
		mv.addObject("sche4Result", sche4Result);
		mv.addObject("sche1Birth", sche1Birth);
		mv.addObject("sche2Birth", sche2Birth);
		mv.addObject("sche3Birth", sche3Birth);
		mv.addObject("sche4Birth", sche4Birth);
		mv.addObject("thisTurn", 1);
		mv.addObject("turn", pCess.getTurn());
		

		mv.setViewName("/peanutcess/peanutcess-farm");

		// 나이와 현재달 가져오기
		String ageStr1 = pCess.getAge() + "";
		String age1 = ageStr1.substring(0, 2);
		String mon = ageStr1.substring(2, 4);

		mv.addObject("age", age1);
		mv.addObject("mon", mon);

		int result = pService.saveStatusAll(pCess);// 능력치 입력
		String nickName = pService.getMemberName(member.getMemberId());// 멤버 닉네임 가져오기
		mv.addObject("nickName", nickName);// 닉네임 보내기

		return mv;
	}

	// 스케쥴시 능력치 변경
	private PeanutCess checkStatus(String sche1, PeanutCess pCess) {
		switch (sche1) {
		case "farm":// 농장알바일때
			pCess.setPower(pCess.getPower() + 5);
			pCess.setStress(pCess.getStress() + 5);
			pCess.setStrong(pCess.getStrong() + 2);
			pCess.setMoney(pCess.getMoney() + 10);
			pCess.setFarm(pCess.getFarm() + 1);
			break;
		case "cafe":// 식당알바일때
			pCess.setCook(pCess.getCook() + 5);
			pCess.setStress(pCess.getStress() + 5);
			pCess.setStrong(pCess.getStrong() + 2);
			pCess.setMoney(pCess.getMoney() + 10);
			pCess.setCafe(pCess.getCafe() + 1);

			break;
		case "child":// 보모알바일때
			pCess.setStress(pCess.getStress() + 5);
			pCess.setStudy(pCess.getStudy() + 5);
			pCess.setManner(pCess.getManner() + 2);
			pCess.setMoney(pCess.getMoney() + 10);
			pCess.setChild(pCess.getChild() + 1);

			break;

		case "material":// 무술수업
			pCess.setStress(pCess.getStress() + 5);
			pCess.setPower(pCess.getPower() + 5);
			pCess.setStrong(pCess.getStrong() + 5);
			pCess.setMoney(pCess.getMoney() - 20);
			pCess.setMaterial(pCess.getMaterial() + 1);
			break;

		case "art": // 미술수업
			pCess.setStress(pCess.getStress() + 5);
			pCess.setArt(pCess.getArt() + 5);
			pCess.setStudy(pCess.getStudy() + 5);
			pCess.setMoney(pCess.getMoney() - 20);
			pCess.setArtschool(pCess.getArtschool() + 1);
			break;

		case "music":
			pCess.setStress(pCess.getStress() + 5);
			pCess.setMusic(pCess.getMusic() + 5);
			pCess.setManner(pCess.getManner() + 5);
			pCess.setMoney(pCess.getMoney() - 20);
			pCess.setMusicschool(pCess.getMusicschool() + 1);
			break;

		case "rest":
			pCess.setStress(pCess.getStress() - 10);
			break;

		default:
			break;
		}

		return pCess;
	}

}
