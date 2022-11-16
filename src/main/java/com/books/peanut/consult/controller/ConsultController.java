package com.books.peanut.consult.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.consult.domain.Consult;
import com.books.peanut.consult.domain.ConsultServer;
import com.books.peanut.consult.domain.SwitchChat;
import com.books.peanut.consult.service.ConsultService;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.Pagemarker;

@Controller
public class ConsultController {
	@Autowired
	private ConsultService cService;

	// 채팅상담 진행시 on/of확인부분.
	@ResponseBody
	@RequestMapping(value = "/client/chatCheck.kh", method = RequestMethod.GET)
	public String checkChat() {
		SwitchChat switchChat = new SwitchChat();
		String switchbtn = cService.selechbtn();
		switchChat.setOn_off(switchbtn.trim());
		JSONObject jsonObj = new JSONObject();
		if (!(switchbtn.isEmpty())) {
			jsonObj.put("switchbtn", switchbtn);
		} else {
			jsonObj.put("resultMsg", "error");
		}
		return jsonObj.toJSONString();
	}

	// chat을 위한 한명의 정보 받아오기
	@RequestMapping(value = "/consult/chatbefore.kh", method = RequestMethod.GET)
	public ModelAndView showChat(ModelAndView mv, HttpSession session, HttpServletRequest request) {

		Member member = (Member) session.getAttribute("loginMember");
		try {
			SwitchChat switchChat = new SwitchChat();
			if (member == null) {
				mv.setViewName("redirect:/ej.kh");
			}

			mv.addObject("swichbtn", switchChat.getOn_off());
			mv.addObject("memberId", member.getMemberId());
			mv.addObject("mEmail", member.getmEmail());
			mv.setViewName("/consult/userChat");

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
		}

		return mv;
	}

	// 채팅 상담접수
	@ResponseBody
	@RequestMapping(value = "/client/afterChat.kh", method = RequestMethod.POST)
	public String clientChat(String cMemberId, String csTitle, String cEmail, @ModelAttribute ConsultServer conServer) {
		conServer.setCsMemberId(cMemberId);
		conServer.setCsTitle(csTitle);
		conServer.setCsMail(cEmail);

		int result = cService.receiptChat(conServer);
		int titleNo = cService.serchTitleNo(conServer);

		JSONObject jsonObj = new JSONObject();
		if (result >= 0) {
			jsonObj.put("resultMsg", "success");
			jsonObj.put("titleNo", titleNo);
		} else {
			jsonObj.put("resultMsg", "error");
		}
		return jsonObj.toJSONString();
	}

	// 채팅DB 보내기
	@ResponseBody
	@RequestMapping(value = "/client/start.kh", method = RequestMethod.POST)
	public String clientChat(String cMemberId, String cContexts, String cEmail, int titleNo) {

		Consult consult = new Consult();
		consult.setcMemberId(cMemberId);
		consult.setcContexts(cContexts);
		consult.setcEmail(cEmail);
		consult.setTitleNo(titleNo);

		int result = cService.inputChat(consult);
		JSONObject jsonObj = new JSONObject();

		if (result >= 0) {
			jsonObj.put("status", "success");
		} else {
			jsonObj.put("status", "error");

		}
		return jsonObj.toJSONString();
	}

	// 채팅내역 실시간 화면으로전송해주기
	@ResponseBody
	@RequestMapping(value = "/client/listprint.kh", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String clientChat(@RequestParam("titleNo") int titleNo) {

		List<Consult> conList = cService.nowChatList(titleNo);
		JSONArray jsonArr = new JSONArray();

		if (!(conList.isEmpty())) {
			for (int i = 0; i < conList.size(); i++) {
				Consult consult = conList.get(i);
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("consultNo", consult.getConsultNo());
				jsonObj.put("cMemberId", consult.getcMemberId());
				jsonObj.put("cContexts", consult.getcContexts());
				// 데이트형 문자열로 바꾸기
				SimpleDateFormat format1 = new SimpleDateFormat("HH:mm:ss");
				String cDate = "";
				cDate = format1.format(consult.getcDate());
				jsonObj.put("cDate", cDate);
				jsonArr.add(jsonObj);
			}
		}

		return jsonArr.toJSONString();
	}

//서버자리///////////////////////////////////////////////////

	// 관리자가 리스트 페이지 들어와서 관리페이지로 올때.
	@RequestMapping(value = "/chat/move.kh", method = RequestMethod.GET)
	public ModelAndView move(ModelAndView mv, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");
		mv.setViewName("/consult/consultingList");

		return mv;
	}

//관리자 전체 리스트 가져오는 것
	@ResponseBody
	@RequestMapping(value = "/consult/chatSession.kh", produces = "application/json;charset=UTF-8", method = RequestMethod.GET)
	public String managerList() {

		List<ConsultServer> chatList = cService.printAllChat();
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();

		// on버튼으로 변경
		String on_off = "Y";
		int chatbutton = cService.changebutten(on_off);

		if (!(chatList.isEmpty()) && chatbutton > 0) {
			for (int i = 0; i < chatList.size(); i++) {
				ConsultServer consultServer = chatList.get(i);

				jsonObj.put("titleNo", consultServer.getTitleNo());
				jsonObj.put("csMemberId", consultServer.getCsMemberId());
				jsonObj.put("csMail", consultServer.getCsMail());
				jsonObj.put("csTitle", consultServer.getCsTitle());
				// 데이트형 문자열로 바꾸기
				SimpleDateFormat format1 = new SimpleDateFormat("yy.MM.dd HH:mm:ss");
				String csDate = format1.format(consultServer.getCsDate());
				jsonObj.put("csDate", csDate);
				///////////
				jsonObj.put("csResult", consultServer.getCsResult());
				jsonArr.add(jsonObj);
				jsonObj = new JSONObject();
			}

			return jsonArr.toJSONString();
		} else {
			jsonObj.put("resultMsg", "error");
			return jsonObj.toJSONString();
		}
	}

	// 관리자 상담 시작하기
	@RequestMapping(value = "serverchat/start.kh", method = RequestMethod.GET)
	public ModelAndView serverChat(ModelAndView mv, HttpServletRequest request,
			@RequestParam("csMemberId") String csMemberId, @RequestParam("csTitle") String csTitle,
			@RequestParam("titleNo") Integer titleNo) {
		try {
			mv.addObject("csTitle", csTitle);
			mv.addObject("cMemberId", csMemberId);
			mv.addObject("titleNo", titleNo);
			mv.setViewName("/consult/managerChat");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("/error");
		}

		return mv;
	}

	// 관리자 상담종료 한기
	@ResponseBody
	@RequestMapping(value = "/consult/finish.kh", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
	public String managerList(@RequestParam("csResult") String csResult, @RequestParam("titleNo") Integer titleNo) {
		ConsultServer conServer = new ConsultServer();
		JSONObject jsonObj = new JSONObject();

		conServer.setTitleNo(titleNo);
		conServer.setCsResult(csResult);

		int result = cService.chatFinish(conServer);
		if (result > 0) {
			jsonObj.put("result", result);
		} else {
			jsonObj.put("result", result);
		}
		return jsonObj.toJSONString();
	}

	// 관리자 체크리스트에서 종료로 변경할때 채팅 on/off 변경
	@ResponseBody
	@RequestMapping(value = "/manager/chatbtn.kh", method = RequestMethod.GET)
	public String chatbtn(@RequestParam("on_off") String on_off) {
		JSONObject jsonObj = new JSONObject();
		System.out.println(on_off);

		int result = cService.changebutten(on_off);
		System.out.println(result);
		if (result > 0) {
			jsonObj.put("result", "성공");
		} else {
			jsonObj.put("result", "실패");
		}
		return jsonObj.toJSONString();
	}

	// 관리자가 종료건 검색 화면으로 이동
	
	
/*			@RequestParam(value = "searchDate", required = false) String csDate,
	    	@RequestParam(value = "page", required = false) Integer page,
	    	@ModelAttribute ConsultServer cs) throws ParseException {
		Pagemarker pm = new Pagemarker();
		SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
		if(csDate != null && !csDate.equals("")) {
			cs.setCsDate(smf.parse(csDate));
		}

		int totalCount = cService.getTotalCount(cs);
		int currentPage = (page != null) ? page : 1;		
		
		pm.pageInfo(currentPage, totalCount);
		mv.addObject("pm", pm);
		
		List<ConsultServer> chatList = cService.printEndListChat(pm, cs);
		mv.addObject("chatList", chatList);
		mv.addObject("csMemberId", cs.getCsMemberId());
		mv.addObject("csDate", csDate);		
		mv.setViewName("/consult/chatEndList");
		return mv;
	}*/
	//csDate값을 내가 원하는 형식을 표시할수 없어서 그냥 String값으로 가져가 봅니다.
	@RequestMapping(value = "/consult/endList.kh", method = RequestMethod.GET)
	public ModelAndView endListSearch(ModelAndView mv,
			@RequestParam(value = "searchDate", required = false) String csDate,
	    	@RequestParam(value = "page", required = false) Integer page,
	    	@RequestParam(value = "csMemberId", required = false) String csMemberId) {
		cService.changebutten("N");
		Pagemarker pm = new Pagemarker();
		int totalCount = cService.getTotalCount(csMemberId,csDate);
		int currentPage = (page != null) ? page : 1;
		pm.pageInfo(currentPage, totalCount);
		mv.addObject("pm", pm);
		
		List<ConsultServer> chatList = cService.printEndListChat(pm, csMemberId,csDate);
		mv.addObject("chatList", chatList);
		mv.addObject("csMemberId", csMemberId);
		mv.addObject("csDate", csDate);		
		mv.setViewName("/consult/chatEndList");
		return mv;		
	}
			

	// id,titleNo 상담내용 상세보기
	@RequestMapping(value = "/consult/chatDetail.kh", method = RequestMethod.GET)
	public ModelAndView detailSearch(ModelAndView mv, 
			@ModelAttribute Consult consult,
			@RequestParam(value = "page", required = false) Integer page) {
		Pagemarker pm = new Pagemarker();
		//int totalCount = cService.getConsultCount(consult);
		//int currentPage = (page != null) ? page : 1;
		//pm.pageInfo(currentPage, totalCount);
		pm.setTotalCount(cService.getConsultCount(consult));
		pm.setCurrentPage((page != null) ? page : 1);
		pm.pageInfo(pm.getCurrentPage(), pm.getTotalCount());
		mv.addObject("pm", pm);
		
		List<Consult> cList = cService.chatDetailList(pm, consult);
		mv.addObject("cList", cList);
		mv.addObject("cMemberId", consult.getcMemberId());
		mv.addObject("titleNo", consult.getTitleNo());
		mv.setViewName("/consult/chatDetail");
		return mv;
	}
}
