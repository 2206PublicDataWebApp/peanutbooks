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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;
import com.books.peanut.pay.domain.Pagemarker;
import com.books.peanut.pay.domain.Pay;
import com.books.peanut.pay.domain.PeanutPoint;
import com.books.peanut.pay.domain.SeasonTicket;
import com.books.peanut.pay.domain.WriterPay;
import com.books.peanut.pay.payService.PayService;
import com.google.gson.Gson;

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
				//결제성공후 포인트 넣어주고 멤버테이블에 반영하기
				Member member=new Member();
				int ppSum = pService.getPPsum(memberId);
				member.setMemberId(memberId);
				member.setmPoint(ppSum);
				pService.putMemberPoint(member);
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
		pm.setTotalCount(pService.getTotalCount(memberId));
		pm.setCurrentPage((page != null) ? page : 1);
		pm.pageInfo(pm.getCurrentPage(), pm.getTotalCount());
		mv.addObject("pm", pm);
		
		List<PeanutPoint> pList=pService.peanutList(memberId,pm);
		for(int i=0;i<pList.size();i++) {
			PeanutPoint pp = pList.get(i);
			if(!(pp.getBookName()==null)) {
				if(pp.getBookName().length()>10) {
					pp.setBookName(pp.getBookName().substring(0,10)+"...");
					pList.set(i, pp);			
				
				}
				
			}
		}
		mv.addObject("ppSum", ppSum);
		mv.addObject("pList", pList);
		mv.setViewName("/peanetPay/peanutList");		
		return mv;		
	}
	
	//관리자 땅콩리스트 확인
		@RequestMapping(value="/peanut/admin_list.kh", method=RequestMethod.GET)
		public ModelAndView adminListGo( 
				ModelAndView mv , String memberId
				, @RequestParam(value= "page", required = false) Integer page		
				){
			int ppSum = pService.getPPsum(memberId);
			Pagemarker pm=new Pagemarker();
			pm.setTotalCount(pService.getTotalCount(memberId));
			pm.setCurrentPage((page != null) ? page : 1);
			pm.pageInfo(pm.getCurrentPage(), pm.getTotalCount());
			mv.addObject("pm", pm);
			
			List<PeanutPoint> pList=pService.peanutList(memberId,pm);
			for(int i=0;i<pList.size();i++) {
				PeanutPoint pp = pList.get(i);
				if(!(pp.getBookName()==null)) {
					if(pp.getBookName().length()>10) {
						pp.setBookName(pp.getBookName().substring(0,10)+"...");
						pList.set(i, pp);			
					
					}
					
				}
			}
			mv.addObject("ppSum", ppSum);
			mv.addObject("pList", pList);
			mv.setViewName("/peanetPay/adminPNList");		
			return mv;		
		}
	// 헤더에서 포인트조회하는 부분
	@ResponseBody
	@RequestMapping(value="/ppoint/pointsum.kh", method=RequestMethod.GET)
	public String pointSum(String memberId) {
		int ppSum = pService.getPPsum(memberId);
		return String.valueOf(ppSum);
	}
	
	//작가 정산요청 화면 이동
	@RequestMapping(value="/writer/writerStart.kh", method=RequestMethod.GET)
	public ModelAndView writerPutGo( ModelAndView mv,String memberId){
			List<OriginBook> o_bookList= pService.originListGet(memberId);
			for(int i=0;i<o_bookList.size();i++) {
				OriginBook oB = o_bookList.get(i);			
				if(oB.getBookTitle().length()>10) {
					oB.setBookTitle(oB.getBookTitle().substring(0,10)+"...");
					o_bookList.set(i, oB);			
				
				}				
			}
			
			mv.addObject("o_bookList", o_bookList);
			mv.setViewName("/peanetPay/WriterPay");
			return mv;		
	}
	//도서번호로 시리즈 조회
	@ResponseBody
	@RequestMapping(value="/writer/bookNo.kh", method=RequestMethod.POST)
	public String findSeriseNo(@ModelAttribute OriginBookSeries obs) {
		List<OriginBookSeries>  obsList = pService.findSeriseNo(obs);
		Gson gson=new Gson();
		return gson.toJson(obsList);
		
		
	}
	
	//작가료 정산접수
	@ResponseBody
	@RequestMapping(value="/writer/receipt.kh", method=RequestMethod.POST)
	public String writerPayReceipt(@ModelAttribute WriterPay writerP) {
		int num =pService.updatePaidCount(writerP);
		if(num>0) {
			int result = pService.writerReceipt(writerP);
			if(result>0) {
				return "success";
			}else {
				return "point failure";
			}
			
		}else {
			return "failure";
		}		
	}
		
	//관리자가 작가 정산리스트 화면으로가기
	@ResponseBody
	@RequestMapping(value="/writer/admin_list.kh", method=RequestMethod.GET)
	public ModelAndView writerList(
			ModelAndView mv,
			@RequestParam(value= "page", required = false) Integer page ){
		
		Pagemarker pm=new Pagemarker();
		int count=pService.getwritetP_Count();
		if(count==0) {			
			mv.addObject("count", "no");
		}else {
			pm.setTotalCount(count);
			pm.setCurrentPage((page != null) ? page : 1);
			pm.pageInfo(pm.getCurrentPage(), pm.getTotalCount());
			mv.addObject("pm", pm);	
			
			List<WriterPay> wrList = pService.wrListPrint(pm);			
			mv.addObject("wrList", wrList);
			mv.setViewName("/peanetPay/writerPayList");
		}	
		return mv;		

	}
	//관리자 정산 접수 승인
	@ResponseBody
	@RequestMapping(value="/writer/payStatus.kh", method=RequestMethod.POST)
	public String writerPayStatus(String wrpayNo) {
		//int wrpayNo1=Integer.valueOf(wrpayNo1);
		int result=pService.writerPayStatusOne(wrpayNo);
		if(result>0) {
			return "success";
		}else {
			return "fail";			
		}
	}

	@ExceptionHandler({NullPointerException.class, SQLException.class, IllegalAccessException.class})
	public ModelAndView errorHandler(ModelAndView mv, Exception e) {
		mv.addObject("msg", e.getMessage());
		mv.setViewName("/common/errorPage");

		return mv;		
	}



}
