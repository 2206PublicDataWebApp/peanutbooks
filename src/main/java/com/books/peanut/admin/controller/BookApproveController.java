package com.books.peanut.admin.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.admin.domain.ModifyBookSeries;
import com.books.peanut.admin.service.BookApproveService;
import com.books.peanut.book.controller.BookPageController;
import com.books.peanut.book.domain.BookPage;
import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.book.service.BookService;
import com.books.peanut.member.domain.Member;
import com.books.peanut.news.service.NewsService;
import com.books.peanut.pay.domain.WriterPay;
import com.books.peanut.qna.domain.Qna;
import com.books.peanut.qna.service.QnaService;

@Controller
public class BookApproveController {
	@Autowired
	private BookApproveService BAService;
	@Autowired
	private BookService bService;
	@Autowired
	private NewsService nService;
	@Autowired
	private QnaService qService;

	//도서 리스트(all, 승인, 보류)
	@RequestMapping(value="/admin/approveYN.kh", method=RequestMethod.GET)
	public ModelAndView approveYNList(
			ModelAndView mv
			, @RequestParam(value="checkPermission", defaultValue = "all") String checkPermission
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam(value="step", required=false, defaultValue = "date")String step) {
		try {
			int allBooks = BAService.allBooks();
			int approveYes = BAService.approveYes();
			int approveNo = BAService.approveNo();
			int reApproveBooks = BAService.reApproveCount();
			
			int getTotalCount = BAService.checkPermissionCount(checkPermission);
			int boardLimit = 20;
			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, getTotalCount, boardLimit);
			
			if(getTotalCount > 0) {
				List<OriginBookSeries> osList = BAService.checkPermission(bPage.getCurrentPage(), boardLimit, checkPermission, step);
				
				for (int i = 0; i < osList.size(); i++) {
					String bookTitle = bService.getBookTitle(osList.get(i).getBookNo());
					osList.get(i).setBookTitle(bookTitle);// 각 시리즈의 책 제목 가지고옴
				}
				mv.addObject("osList", osList);
			}
			mv.addObject("allBooks", allBooks);
			mv.addObject("approveYes", approveYes);
			mv.addObject("approveNo", approveNo);
			mv.addObject("reApproveBooks", reApproveBooks);
			mv.addObject("checkPermission", checkPermission);
			mv.addObject("bPage", bPage);
			mv.setViewName("/bookApprove/BAwritermenu");
			
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	//도서 승인
	@RequestMapping(value="/admin/approve.kh", method=RequestMethod.GET)
	public ModelAndView bookApprove(
			@RequestParam("bookNo") Integer bookNo
			, @RequestParam("seriesNo") Integer seriesNo
			, @RequestParam(value="currentPage", required=false) Integer page
			, ModelAndView mv
			, HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		if((member.getAdminYN().charAt(0) + "").equals("N")) {
			mv.addObject("msg", "관리자만 접속가능합니다");
			mv.setViewName("/common/errorPage");
		}else {
			try {
				int result = BAService.approveBooks(bookNo,seriesNo);
				mv.addObject("page", page);
				if(result > 0) {
					mv.setViewName("redirect:/admin/approveYN.kh");
	               /* 알림 등록 시작 */
	               List<Library> lList = nService.selectMemberIdByBookNo(bookNo); // 해당 도서를 서재에 저장한 회원 아이디 리스트 가져오기
	               OriginBook originBook = nService.selectBookTitleByNo(bookNo); // 알림 내용에 들어갈 해당 도서 제목 가져오기 
	               String newsContents = "저장하신 <"+originBook.getBookTitle()+">의 새로운 회차가 등록되었어요!";
	               HashMap<String, Object> paramMap = new HashMap<String, Object>();
	               paramMap.put("lList", lList); // 회원 아이디 들어있는 library 형 리스트
	               paramMap.put("refBookNo", bookNo); // 참고 도서 번호(승인 시 사용된)
	               paramMap.put("newsContents", newsContents); // 알림 내용
	               nService.insertNews(paramMap); // 도서 등록
	               /* 알림 등록 끝 */
				}else {
					mv.addObject("msg", "승인 실패");
					mv.setViewName("common/errorPage");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("/common/errorPage");
			}
		}
		return mv;
	}
	
	
	//재승인 리스트
	@RequestMapping(value="/admin/reApproveList.kh", method=RequestMethod.GET)
	public ModelAndView reApproveList(
			ModelAndView mv
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int allBooks = BAService.allBooks();
			int approveYes = BAService.approveYes();
			int approveNo = BAService.approveNo();
			int reApproveBooks = BAService.reApproveCount();
			
			int getTotalCount = BAService.reApproveCount();   //전체 게시물갯수
			int boardLimit = 20;  //20개씩 출력
			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, getTotalCount, boardLimit);
			
//			if(getTotalCount > 0) {
				List<ModifyBookSeries> mbList = BAService.reApproveList(bPage.getCurrentPage(), boardLimit);
				
				mv.addObject("mbList", mbList);
				mv.addObject("bPage", bPage);
				mv.addObject("allBooks", allBooks);
				mv.addObject("approveYes", approveYes);
				mv.addObject("approveNo", approveNo);
				mv.addObject("reApproveBooks", reApproveBooks);
				
//			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		mv.setViewName("/bookApprove/BAreApprovemenu");
		return mv;
		
	}
	//도서 재승인 기능
	@RequestMapping(value="/admin/reApprove.kh", method=RequestMethod.GET)
	public ModelAndView bookReApprove(
			@RequestParam("bookNo") Integer bookNo
			, @RequestParam("seriesNo") Integer seriesNo
			, @RequestParam(value="currentPage", required=false) Integer page
			, ModelAndView mv
			, HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		if((member.getAdminYN().charAt(0)+"").contentEquals("N")) {
			mv.addObject("msg", "관리자만 접속가능합니다.");
			mv.setViewName("/common/errorPage");
		}else {
			try {
				int result = BAService.reApproveBooks(bookNo,seriesNo);
				mv.addObject("page", page);
				if(result > 0) {
					mv.setViewName("redirect:/admin/reApproveList.kh");
				}else {
					mv.addObject("msg", "승인 실패");
					mv.setViewName("common/errorPage");
				}
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("/common/errorPage");
			}
		}
		return mv;
	}
	
	//adminMain
	@RequestMapping(value="/adminMain.kh", method=RequestMethod.GET)
	public ModelAndView adminMain(
			ModelAndView mv) {
		try {
			
			
			
			List<Qna> qList = BAService.printNewQna();
			List<Member> mList = BAService.printNewMembers();
			List<WriterPay> wrList = BAService.printNewPays();
			if(!qList.isEmpty()|| !mList.isEmpty() || !wrList.isEmpty()) {
				mv.addObject("qList", qList);
				mv.addObject("mList", mList);
				mv.addObject("wrList", wrList);
				
			}
			//회원현황
			int todayCount = BAService.todayJoinCount();
			int deleteCount = BAService.deleteMemberCount();
			int totalCount = BAService.todalCount();
			
			mv.addObject("totalCount", totalCount);
			mv.addObject("todayCount", todayCount);
			mv.addObject("deleteCount", deleteCount);

			//도서현황
			int allBooks = BAService.allBooks();
			int approveYes = BAService.approveYes();
			int approveNo = BAService.approveNo();
			int reApproveBooks = BAService.reApproveCount();
			mv.addObject("allBooks", allBooks);
			mv.addObject("approveYes", approveYes);
			mv.addObject("approveNo", approveNo);
			mv.addObject("reApproveBooks", reApproveBooks);
			mv.setViewName("/admin/adminMain");
			
			//문의게시판 현황
			int totalQna = qService.totalQna();
			int totalAnswer = qService.totalAnswer();
			int totalNoAnswer = qService.totalNoAnswer();
			mv.addObject("totalQna", totalQna);
			mv.addObject("totalAnswer", totalAnswer);
			mv.addObject("totalNoAnswer", totalNoAnswer);
			
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
		
	}
	
}
