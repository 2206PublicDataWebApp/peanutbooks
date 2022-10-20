package com.books.peanut.pay.payController;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.books.peanut.pay.domain.Pay;
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
		//mv.addObject("member", session.getAttribute("loginUser"));
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
	@RequestMapping(value="/peanut/listStart.kh", method=RequestMethod.GET)
	public ModelAndView peanetListGo( ModelAndView mv){
		mv.setViewName("/peanetPay/peanutList");
		return mv;
		
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
		List<WriterPay> wrList=pService.wrListPrint();
		if(wrList.isEmpty()) {
			JSONObject json=new JSONObject();
			json.put("error", "error");
			return json.toJSONString();
		}else {
			Gson gson=new Gson();
			return gson.toJson(wrList);		
		}
  }
	
	@ExceptionHandler({NullPointerException.class, SQLException.class})
	public String errorHandler() {
		return "redirect:/er.kh";		
	}
	
}
