package com.books.peanut.consult.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
public class ConsultController {
	@Autowired
	private ConsultService cService;
	
	//채팅상담 진행시 on/of확인부분.	
	@ResponseBody
	@RequestMapping(value = "/client/chatCheck.kh", method = RequestMethod.GET)
	public String checkChat() {
		SwitchChat switchChat = new SwitchChat();
		String switchbtn =cService.selechbtn();
		switchChat.setOn_off(switchbtn);
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
	public ModelAndView showChat(ModelAndView mv
			, HttpSession session, HttpServletRequest request) {	

			Member member = (Member) session.getAttribute("loginUser");
			try {				
				SwitchChat switchChat=new SwitchChat();
				if(member == null){
					mv.setViewName("redirect:/home.kh");
				}
				String memberId = "일용자";
				String memberEmail = member.getMemberEmail();
				String swichbtn = switchChat.getOn_off();

				mv.addObject("swichbtn", swichbtn);
				mv.addObject("memberId", memberId);
				mv.addObject("memberEmail", memberEmail);
				mv.setViewName("/consult/userChat");
			
			} catch (Exception e) {
			
				mv.addObject("msg", e.getMessage());
			}
	
		return mv;
	}

	// 고객채팅 시작

	
	// 채팅 상담접수
	@ResponseBody
	@RequestMapping(value = "/client/afterChat.kh", method = RequestMethod.POST)
	public String clientChat(String cNickName, String csTitle, String cEmail, @ModelAttribute ConsultServer conServer) {
		conServer.setCsNickName(cNickName);
		conServer.setCsTitle(csTitle);
		conServer.setCsMail(cEmail);
//		System.out.println("고객상담접수:"+conServer.toString());
		int result = cService.receiptChat(conServer);//상담접수하고
		int titleNo= cService.serchTitleNo(conServer); //접수번호 가져오기
//		System.out.println("titleNo:"+titleNo);
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
	public String clientChat(
			String cNickName, String cContexts, String cEmail, int titleNo) {
//		System.out.println("채팅내용 DB로 저장 cContext :" + cContexts);
		Consult consult = new Consult();
		consult.setcNickName(cNickName);
		consult.setcContexts(cContexts);
		consult.setcEmail(cEmail);
		consult.setTitleNo(titleNo);

		int result = cService.inputChat(consult);
		JSONObject jsonObj = new JSONObject();
		
		//JSONObject jsonObj = new JSONObject();
		if(result >= 0) {
			//jsonObj.put("resultMsg", "success");			
			jsonObj.put("status", "success");
		} else {
			//jsonObj.put("resultMsg", "error");			
			jsonObj.put("status", "error");

		}
		// 제이슨으로 다시 화면에 전송함...
		return jsonObj.toJSONString();
	}

	// 채팅내역 실시간 화면으로전송해주기

	@ResponseBody
	@RequestMapping(value = "/client/listprint.kh", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	public String clientChat(@RequestParam("titleNo") int titleNo) {
	
		List<Consult> conList = cService.nowChatList(titleNo);		
		JSONArray jsonArr = new JSONArray();
		//JSONObject jsonObj = new JSONObject();
		System.out.println("리스트 전달 1번");
		// 리스트 자체를 array이로 넘김
		if (!(conList.isEmpty())) {
			for( int i=0; i<conList.size(); i++) { //for Each문, conList에 있는 객체를 하나씩 거내서 객체
				Consult consult = conList.get(i);				
				JSONObject jsonObj = new JSONObject();
				jsonObj.put("cNickName",consult.getcNickName()); 
				System.out.println("리스트 전달 2번 : "+consult.getcNickName());
				jsonObj.put("cContexts", consult.getcContexts());
				//데이트형 문자열로 바꾸기
				SimpleDateFormat format1=new SimpleDateFormat("HH:mm:ss");
				String cDate="";					 
				cDate=format1.format(consult.getcDate());		
				
				jsonObj.put("cDate",cDate); // 데이트형 문자열로 바꿔서 가져오기!
				System.out.println("리스트 전달 2번 : "+cDate);
				jsonArr.add(jsonObj);				
			}		
	
		}
		System.out.println("리스트 전달 마지막");
		
		return jsonArr.toJSONString();
	}

//서버자리///////////////////////////////////////////////////


  //관리자가 리스트 페이지 들어와서 관리페이지로 올때. 
	@RequestMapping(value = "/consult/move.kh", method = RequestMethod.GET)
	public ModelAndView move(ModelAndView mv, HttpSession session) {

		Member member = (Member) session.getAttribute("loginUser");
		
		if(!(member.getMemberEmail().equals("manager"))) {
			
			mv.addObject("msg","관리자가 아닙니다");
			mv.setViewName("/error");
		} else {		
			mv.setViewName("/consult/consultingList");
		}
		return mv;
	}


//관리자 전체 리스트 가져오는 것
	@ResponseBody
	@RequestMapping(value = "/consult/chatSession.kh", produces="application/json;charset=UTF-8", method = RequestMethod.GET)
	public String managerList() {		

		List<ConsultServer> chatList  = cService.printAllChat();	
		JSONObject jsonObj = new JSONObject();
		JSONArray jsonArr = new JSONArray();		

		//on버튼으로 변경
		String chatbtn="Y";
		int chatbutton=cService.changebutten(chatbtn);
		
		if (!(chatList.isEmpty())) {
			for( int i=0; i<chatList.size(); i++) { //for Each문, conList에 있는 객체를 하나씩 거내서 객체
				ConsultServer consultServer = chatList.get(i);	

				jsonObj.put("titleNo",consultServer.getTitleNo());
				jsonObj.put("csNickName",consultServer.getCsNickName());
				jsonObj.put("csMail",consultServer.getCsMail());
				jsonObj.put("csTitle",consultServer.getCsTitle());
				//데이트형 문자열로 바꾸기
				SimpleDateFormat format1=new SimpleDateFormat("yy.MM.dd HH:mm:ss");
				String csDate="";					 
				csDate=format1.format(consultServer.getCsDate());				
				jsonObj.put("csDate",csDate);
				///////////
				jsonObj.put("csResult",consultServer.getCsResult());
				jsonObj.put("csFileName",consultServer.getCsFileName());
				jsonObj.put("csFileRename",consultServer.getCsFileRename());
				jsonObj.put("csFilePath",consultServer.getCsFilePath());

				jsonArr.add(jsonObj);			
				jsonObj = new JSONObject();
			}
			
		}else {
		  //jsonObj.put("resultMsg", "error");
		}
//		System.out.println("리스트 전달 마지막");
		
		return jsonArr.toJSONString();	
		
	}
	
	// 관리자 상담 시작하기
		@RequestMapping(value = "serverchat/start.kh", method = RequestMethod.GET)
		public ModelAndView serverChat(ModelAndView mv,
				HttpServletRequest request,
				@RequestParam("csNickName") String csNickName,
				@RequestParam("csTitle") String csTitle,
				@RequestParam("titleNo") Integer titleNo) {
			try {				
				mv.addObject("csTitle", csTitle);
				mv.addObject("cNickName", csNickName);
				mv.addObject("titleNo", titleNo);
				mv.setViewName("/consult/managerChat");
			}catch (Exception e) {
				mv.addObject("msg",e.toString());
				mv.setViewName("/error");		
			}

			return mv;
		}

	//관리자 상담종료 한기
	@ResponseBody
	@RequestMapping(value = "/consult/finish.kh", produces="application/json;charset=UTF-8", method = RequestMethod.POST)
	public String managerList(			
		    @RequestParam("csResult") String csResult,
		    @RequestParam("titleNo") Integer titleNo		    
			) {
			ConsultServer conServer=new ConsultServer();
			JSONObject jsonObj = new JSONObject();
				
				conServer.setTitleNo(titleNo);
				conServer.setCsResult(csResult);		
			
			int result=cService.chatFinish(conServer );	
				if(result>0) {
					jsonObj.put("result", result);
				}else {
					jsonObj.put("result",result);
				}
			return jsonObj.toJSONString();
			}	
	//관리자 체크리스트에서 종료로 변경할때 채팅 on/off 변경
	@ResponseBody
	@RequestMapping(value = "/manager/chatbtn.kh", method = RequestMethod.GET)
	public String chatbtn(@RequestParam("on_off") String on_off) {										
			JSONObject jsonObj = new JSONObject();
			System.out.println(on_off);
			
			int result=cService.changebutten(on_off);
			System.out.println(result);
			if(result>0) {
				jsonObj.put("result", "성공");						
			}else {
				jsonObj.put("result", "실패");
			}
			return jsonObj.toJSONString();
	}
//편의점 조회하러 가기	
	@RequestMapping(value = "/peon/peon.kh", method = RequestMethod.GET)
	public ModelAndView movepeon(ModelAndView mv) {
		mv.setViewName("consult/pyeonFind");
		return mv;
	}
	
	

	}

