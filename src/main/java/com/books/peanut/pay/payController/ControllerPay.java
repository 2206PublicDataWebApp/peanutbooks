package com.books.peanut.pay.payController;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.Pagemarker;
import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;
import com.books.peanut.pay.payService.PayService;

@Controller
public class ControllerPay {
	
	@Autowired
	public PayService pService;


	//결제 창으로 이동
	@RequestMapping(value="/pay/start.kh", method=RequestMethod.GET)
	public ModelAndView payGo(			
			ModelAndView mv
			, HttpSession session){		
		mv.addObject("member", session.getAttribute("loginMember"));
		mv.setViewName("/peanetPay/pay");
		return mv;		
	}
	
// 주문 번호 만들어서 보내기
	@ResponseBody
	@RequestMapping(value = "/pay/orderIN.kh", produces = "application/json;charset=utf-8", method=RequestMethod.POST)
	public String orderIn(String orderNo,String payMoney,String memberId, String orderContents) {			 
		Pay pay=new Pay();
		pay.setOrderNo(orderNo);
		pay.setPay(Integer.valueOf(payMoney));
		pay.setMemberId(memberId);		
		pay.setOrderContents(orderContents);
		int result=pService.orderin(pay);
		
	
		if(result>0) {
			pay=pService.orderNoOne(pay);			
			return new Gson().toJson(pay);
			
		}else {
			JSONObject json=new JSONObject();
			json.put("error","error");
			return json.toJSONString();
		}	
		
	}
	// API결제 성공시 데이터 DB전달
	@ResponseBody
	@RequestMapping(value="/pay/success.kh", method=RequestMethod.POST)
	public String paySussess(String orderNo, String memberId, Integer pay, String imp_uid, Pay payApi) {
		String[] arr = orderNo.split("-");
		String table = arr[0];
		payApi.setImp_uid(imp_uid);
		payApi.setMemberId(memberId);
		payApi.setOrderNo(orderNo);
		int result = pService.orderSuccess(payApi);
		int p_t_input;
		int m_st_YN=1;
		if (result > 0) {
			if (table.equals("seasonticket")) {
				SeasonTicket st = new SeasonTicket();
				st.setOrderNo(orderNo);
				st.setMemberId(memberId);
				p_t_input = pService.seasonticketInput(st);
				m_st_YN = pService.memberStChange(memberId);
				
			} else {
				PeanutPoint pp = new PeanutPoint();
				pp.setMemberId(memberId);
				pp.setOrderNo(orderNo);
				pp.setPeanutPoint(pay / 100);
				p_t_input = pService.peanutTableInput(pp);
			}

		} else {
			return "failure";
		}

		if ((p_t_input > 0) && (m_st_YN > 0) ) {
			return "success";
		} else {
			return "failure";
		}
	}
	
	
///////////////////////////////////////////////////////////////////	
	//땅콩리스트
	@RequestMapping(value="/peanut/listStart.kh", method=RequestMethod.GET)
	public ModelAndView peanutListGo( 
			ModelAndView mv , String memberId
			, @RequestParam(value= "page", required = false) Integer page		
			){
		int ppSum = pService.getPPsum(memberId);
		Member member=new Member();
		member.setMemberId(memberId);
		member.setmPoint(ppSum);
		pService.putMemberPoint(member);
		
		Pagemarker pm=new Pagemarker();
		pm.setTotalCount(pService.getTotalCount());
		pm.setCurrentPage((page != null) ? page : 1);
		pm.pageInfo(pm.getCurrentPage(), pm.getTotalCount());
		mv.addObject("pm", pm);
		
		List<PeanutPoint> pList=pService.peanutList(memberId,pm);
		
		mv.addObject("ppSum", ppSum);
		mv.addObject("pList", pList);
		mv.setViewName("/peanetPay/peanutList");		
		return mv;		
	}
	// 헤더에서 포인트조회하는 부분
	@ResponseBody
	@RequestMapping(value="ppoint/pointsum.kh", method=RequestMethod.POST)
	public String pointSum(String memberId) {
		int ppSum = pService.getPPsum(memberId);
		
		return String.valueOf(ppSum);
	}
	
	//작가 정산요청 화면 이동
	@RequestMapping(value="/writer/writerStart.kh", method=RequestMethod.GET)
	public ModelAndView writerPutGo( ModelAndView mv,String memberId){
			mv.addObject("memberId", memberId);
			mv.setViewName("/peanetPay/WriterPay");
			return mv;		
	}
	//작가 정산요청 접수
	@ResponseBody
	@RequestMapping(value="/writer/receipt.kh", method=RequestMethod.POST)
	public String writerPayReceipt(@ModelAttribute WriterPay writerP) {
		int result = pService.writerReceipt(writerP);
		if(result>0) {
			return "success";
		}else {
			return "failure";
		}
		
	}
		
	//작가 정산리스트 화면으로가기
	@RequestMapping(value="/writer/list.kh", method=RequestMethod.GET)
	public ModelAndView writerListGo( ModelAndView mv){
			mv.setViewName("/peanetPay/writerPayList");
			return mv;		
	}
	
	//작가 정산리스트 요청
	@ResponseBody
	@RequestMapping(value="/writer/listprint.kh", produces="application/json;charset=UTF-8", method=RequestMethod.GET)
	public String writerList(){
		List<WriterPay> wrList = pService.wrListPrint();
		if (wrList.isEmpty()) {
			JSONObject json = new JSONObject();
			json.put("error", "error");
			return json.toJSONString();
		} else {
			Gson gson = new Gson();
			return gson.toJson(wrList);
		}
	}	
//	// 로그인시 구독권 여부 및 날짜 확인하는 부분
//	public String seasonTicketDate(String memberId) {
//		String lastDate = pService.seasonTicketDate(memberId);		
//		return lastDate;
//	}
	
	@ExceptionHandler({NullPointerException.class, SQLException.class})
	public String errorHandler() {
		return "redirect:/common/errorPage.kh";		
	}



}
