package com.books.peanut.pay.payController;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.books.peanut.pay.domain.Pay;
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
		//mv.addObject("member", session.getAttribute("loginUser"));
		mv.setViewName("/peanetPay/pay");
		return mv;
		
	}
	
// 주문 번호 만들어서 보내기
	@ResponseBody
	@RequestMapping(value = "/pay/orderIN.kh", produces = "application/json;charset=utf-8", method=RequestMethod.POST)
	public String orderIn(String orderNo,String payPoint,String memberId, String memberEmail, String orderContents) {			 
		Pay pay=new Pay();
		pay.setOrderNo(orderNo);
		pay.setPayPoint(Integer.valueOf(payPoint));
		pay.setMemberId(memberId);
		pay.setMemberEmail(memberEmail);
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
	@ResponseBody
	@RequestMapping(value="/pay/success.kh", method=RequestMethod.GET)
	public String psySussess(String orderNo){
		int result=pService.orderSuccess(orderNo);
		if(result>0) {
			return "success";
		}else {
			return "error";
		}
	}
	//땅콩리스트
	@RequestMapping(value="/peanet/list.kh", method=RequestMethod.GET)
	public ModelAndView peanetListGo( ModelAndView mv){
		mv.setViewName("/peanetPay/peanetList");
		return mv;
		
	}
	//작가 정산리스트
	@RequestMapping(value="/writer/list.kh", method=RequestMethod.GET)
	public ModelAndView writerListGo( ModelAndView mv){
		mv.setViewName("/peanetPay/WriterPay");
		return mv;
		
	}
	@ExceptionHandler({NullPointerException.class, SQLException.class})
	public String errorHandler() {
		return "redirect:/home.kh";		
	}
	
}
