package com.books.peanut.qna.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.book.controller.BookPageController;
import com.books.peanut.book.domain.BookPage;
import com.books.peanut.member.domain.Member;
import com.books.peanut.qna.domain.Qna;
import com.books.peanut.qna.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qService;

	//qna등록 화면
	@RequestMapping(value="/qna/writeView", method=RequestMethod.GET)
	public String showQnaWriter(HttpSession session) {
//		로그인 유저용
		Member member = (Member) session.getAttribute("loginMember");
		if(member != null) {
			return "/qna/qnaWriteForm";
		}
		return "member/login";
	}
	
	/**
	 * 게시글 등록
	 * @param mv
	 * @param qna
	 * @param uploadFile
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/qna/register.kh", method = RequestMethod.POST)
	public ModelAndView registQan(ModelAndView mv, @ModelAttribute Qna qna,
			@RequestParam(value = "uploadFile", required = false) List<MultipartFile> uploadFile
			, HttpServletRequest request) {
		
		try {

			// 파일등록시작//
			String[] qnaFilename = new String[3];
			String[] qnaFileRename = new String[3];
			String[] qnaFilepath = new String[3];
			for (int i = 0; i < uploadFile.size(); i++) {
				qnaFilename[i] = "";
				qnaFilename[i] = uploadFile.get(i).getOriginalFilename();
				qnaFileRename[i] = "";
				qnaFilepath[i]="";

				if (!qnaFilename[i].equals("")) { // uploadFile이 있을때

					String root = request.getSession().getServletContext().getRealPath("resources");
					String savePath = root + "\\qnaUploadFiles"; // 내가 저장할 폴더
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

					qnaFileRename[i] = sdf.format(new Date(System.currentTimeMillis())) +"."
							+ qnaFilename[i].substring(qnaFilename[i].lastIndexOf(".") + 1);// 동시에여러사진이올라가기에 이름에 순서추가해줌

					File file = new File(savePath); // 파일 객체 만들기
					if (!file.exists()) { // 경로에 savePath가 없을땐
						file.mkdir(); // 경로 만들기
					}
					uploadFile.get(i).transferTo(new File(savePath + "\\" + qnaFileRename[i]));
					// 파일을 buploadFiles 경로에 저장하는 메소드
					qnaFilepath[i] = savePath+"\\"+qnaFileRename[i];
					
				}
				///// 여기까지 사진 저장코드/////

			}

			qna.setQnaFilename01(qnaFilename[0]);
			qna.setQnaFilename02(qnaFilename[1]);
			qna.setQnaFilename03(qnaFilename[2]);

			qna.setQnaFileRename01(qnaFileRename[0]);
			qna.setQnaFileRename02(qnaFileRename[1]);
			qna.setQnaFileRename03(qnaFileRename[2]);
			
			qna.setQnaFilepath01(qnaFilepath[0]);
			qna.setQnaFilepath02(qnaFilepath[1]);
			qna.setQnaFilepath03(qnaFilepath[2]);
			

			int result = qService.registerQna(qna);
			mv.setViewName("redirect:/qna/list.kh");

		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	/**
	 * 게시글 회원별 리스트 출력
	 * @param mv
	 * @param qna
	 * @param page
	 * @param searchCondition
	 * @param searchValue
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/qna/list.kh", method=RequestMethod.GET)
	public ModelAndView qnaListView(
			ModelAndView mv
			, @ModelAttribute Qna qna
			, @RequestParam(value="page", required = false) Integer page
			, @RequestParam(value="searchCondition", required = false) String searchCondition
			, @RequestParam(value="searchValue", required = false) String searchValue
			, @RequestParam(value="qnaStatus", required = false, defaultValue="all") String qnaStatus
			, HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		if(member == null) {
			mv.setViewName("member/login");
		}else { 
			try {
				String memberId = member.getMemberId();
				int totalQna = qService.totalQna(memberId);
				int totalAnswer = qService.totalAnswer(memberId);
				int totalNoAnswer = qService.totalNoAnswer(memberId);	
				
				int totalCount = qService.getTotalCount(memberId,"","",qnaStatus);
				int qnaLimit = 10; // 한페이지당 출력한 게시물 수
				BookPageController bpCont = new BookPageController();
				BookPage bPage = bpCont.boardList(page, totalCount, qnaLimit);
				
				if(totalCount>0) {
					List<Qna> qList = qService.printMemberQna(bPage.getCurrentPage(), qnaLimit, memberId, qnaStatus);
					mv.addObject("qList", qList);
				}
					mv.addObject("memberId", memberId);
					mv.addObject("bPage", bPage);
					mv.addObject("page", page);
					mv.addObject("searchValue", searchValue);
					mv.addObject("searchCondition", searchCondition);
					mv.addObject("totalQna", totalQna);
					mv.addObject("totalAnswer", totalAnswer);
					mv.addObject("totalNoAnswer", totalNoAnswer);
					mv.setViewName("/qna/qnaListView");
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("/common/errorPage");
			}
		}
		return mv;
	}
	
	/**
	 * 게시글 상세보기
	 * @param mv
	 * @param page
	 * @param qnaNo
	 * @return
	 */
	@RequestMapping(value="/qna/detailView.kh", method=RequestMethod.GET)
	public ModelAndView qnaDetailView(
			ModelAndView mv
			, @RequestParam(value="searchCondition", required=false) String searchCondition
			, @RequestParam(value="searchValue", required=false) String searchValue
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam("qnaNo") Integer qnaNo
			, HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		if(member == null) {
			mv.setViewName("member/login");
		}else { 
			try {
				String memberId = member.getMemberId();
				int totalQna = qService.totalQna(memberId);
				int totalAnswer = qService.totalAnswer(memberId);
				int totalNoAnswer = qService.totalNoAnswer(memberId);	
			
				Qna qna = qService.printOneByNo(qnaNo);
				mv.addObject("qna", qna);
				mv.addObject("page", page);
				mv.addObject("searchCondition", searchCondition);
				mv.addObject("searchValue", searchValue);
				mv.addObject("totalQna", totalQna);
				mv.addObject("totalAnswer", totalAnswer);
				mv.addObject("totalNoAnswer", totalNoAnswer);
				mv.setViewName("qna/qnaDetailView");
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}
		}
		return mv;
		
	}
	
	/**
	 * 게시글 삭제
	 * @param session
	 * @param page
	 * @param qnaNo
	 * @return
	 */
	@RequestMapping(value="/qna/remove.kh", method=RequestMethod.GET)
	public String qnaRemove(
			HttpSession session
			, @RequestParam("page") Integer page
			, @RequestParam("qnaNo") Integer qnaNo) {
		try {
			int result = qService.removeOneByNo(qnaNo);
			if(result > 0) {
				return "redirect:/qna/list.kh?page="+page;
			}
		} catch (Exception e) {
			return "common/errorPage";
		}
		return "redirect:/qna/list.kh?page="+page;
		
	}
	
	/**
	 * 게시글 수정화면 
	 * @param mv
	 * @param page
	 * @param qnaNo
	 * @return
	 */
	@RequestMapping(value="/qna/modifyView.kh", method=RequestMethod.GET)
	public ModelAndView qnaModifyView(
			ModelAndView mv
			, @RequestParam("page") int page
			, @RequestParam("qnaNo") Integer qnaNo
			, HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		if(member == null) {
			mv.setViewName("member/login");
		}else { 
			try {
				String memberId = member.getMemberId();
				int totalQna = qService.totalQna();
				int totalAnswer = qService.totalAnswer();
				int totalNoAnswer = qService.totalNoAnswer();
				
				Qna qna = qService.printOneByNo(qnaNo);
				mv.addObject("qna", qna);
				mv.addObject("page", page);
				mv.addObject("totalQna", totalQna);
				mv.addObject("totalAnswer", totalAnswer);
				mv.addObject("totalNoAnswer", totalNoAnswer);
				mv.setViewName("/qna/qnaModifyForm");
				
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}
		}
		return mv;
	}
	
	/**
	 * 게시글 수정
	 * @param qna
	 * @param mv
	 * @param reloadFile
	 * @param page
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/qna/modify.kh", method=RequestMethod.POST)
	public ModelAndView qnaModify(
			@ModelAttribute Qna qna
			, ModelAndView mv
			, @RequestParam(value="reloadFile", required=false) List<MultipartFile> reloadFile
			, @RequestParam("page") Integer page
			, HttpServletRequest request
			, HttpSession session) {
		if(session.getAttribute("loginMember") == null) {
			mv.addObject("msg", "작성자만 수정할 수 있습니다.");
			mv.setViewName("/common/errorPage");
		}else {
			// 세션으로 id가져옴
			Member member = (Member) session.getAttribute("loginMember");
			try {
				
				// 파일교체시작//
				String qnaFilename[] = new String[3];
				String qnaFileRename[] = new String[3];
				String qnasetName[] = {qna.getQnaFileRename01(), qna.getQnaFileRename02(), qna.getQnaFileRename03()};
				String qnaFilepath[] = new String[3];
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\qnaUploadFiles"; // 내가 저장할 폴더
				//반복문으로 삭제 저장
				for (int i = 0; i < reloadFile.size(); i++) {
					qnaFilename[i] = reloadFile.get(i).getOriginalFilename();
	
					if (reloadFile.get(i) != null && !qnaFilename[i].equals("")) { // uploadFile이 있을때
	
						File file = new File(savePath+"\\"+qnasetName[i]); // 파일 객체 만들기
						if (!file.exists()) { // 경로에 savePath가 없을땐
							file.mkdir(); // 경로 만들기
						}
						//파일 다시 저장
						// 파일 이름으로 업로드하면 파일이름이 겹치면 파일이 겹쳐서 사라진다.
						// 고유한 rename으로 변경해주어야 한다.
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
						qnaFileRename[i] = sdf.format(new Date(System.currentTimeMillis())) +"."
								+ qnaFilename[i].substring(qnaFilename[i].lastIndexOf(".") + 1);// 동시에여러사진이올라가기에 이름에 순서추가해줌
						reloadFile.get(i).transferTo(new File(savePath + "\\" + qnaFileRename[i]));
						// 파일을 buploadFiles 경로에 저장하는 메소드
						qnaFilepath[i] = savePath+"\\"+qnaFileRename[i];
						
					}
					///// 여기까지 사진 저장코드/////
	
				}
				if(qnaFileRename[0] != null) {
					qna.setQnaFilename01(qnaFilename[0]);
					qna.setQnaFileRename01(qnaFileRename[0]);
					qna.setQnaFilepath01(qnaFilepath[0]);
				}
				if(qnaFileRename[1] != null) {
					qna.setQnaFilename02(qnaFilename[1]);
					qna.setQnaFileRename02(qnaFileRename[1]);
					qna.setQnaFilepath02(qnaFilepath[1]);
				}
				if(qnaFileRename[2] != null) {
					qna.setQnaFilename03(qnaFilename[2]);
					qna.setQnaFileRename03(qnaFileRename[2]);
					qna.setQnaFilepath03(qnaFilepath[2]);
				}
				
				int result = qService.modifyQna(qna);
				mv.setViewName("redirect:/qna/list.kh?page="+page);
				
	
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}
		}	
		return mv;
		
	}
	
	/**
	 * 게시글 조건 검색
	 * @param mv
	 * @param searchCondition
	 * @param searchValue
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/qna/search.kh", method=RequestMethod.GET)
	public ModelAndView qnaSearchList(
			ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page
			, HttpSession session){
		if(session.getAttribute("loginMember") == null) {
			mv.addObject("msg","로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");
		}else {
			try {
				int totalQna = qService.totalQna();
				int totalAnswer = qService.totalAnswer();
				int totalNoAnswer = qService.totalNoAnswer();
				
				Member member = (Member)session.getAttribute("loginMember");
				String memberId = member.getMemberId();
				int totalCount = qService.getTotalCount(memberId, searchCondition, searchValue,"");
				int qnaLimit = 10;
				BookPageController bpCont = new BookPageController();
				BookPage bPage = bpCont.boardList(page, totalCount, qnaLimit);
				
				if(totalCount > 0) {
					List<Qna> qList = qService.printMemberByValue(memberId
							, searchCondition, searchValue, bPage.getCurrentPage(), qnaLimit);
					mv.addObject("qList", qList);
				}
				mv.addObject("bPage", bPage);
				mv.addObject("page", page);
				mv.addObject("searchCondition", searchCondition);
				mv.addObject("searchValue", searchValue);
				mv.addObject("totalQna", totalQna);
				mv.addObject("totalAnswer", totalAnswer);
				mv.addObject("totalNoAnswer", totalNoAnswer);
				mv.setViewName("qna/qnaListView");
				
			} catch (Exception e) {
				mv.addObject("msg", e.toString()).setViewName("common/errorPage");
			}		
		}
		return mv;
	}
	
	//////////////////////////관리자 part//////////////////////////////
	/**
	 * 관리자 전체QNA 게시물리스트 출력
	 * @param mv
	 * @param qna
	 * @param currentPage
	 * @param searchCondition
	 * @param searchValue
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/admin/qnaList.kh", method=RequestMethod.GET)
	public ModelAndView adminQnaListView(
			ModelAndView mv
			, @ModelAttribute Qna qna
			, @RequestParam(value="page", required = false) Integer page
			, @RequestParam(value="searchCondition", required = false) String searchCondition
			, @RequestParam(value="searchValue", required = false) String searchValue
			, @RequestParam(value="qnaStatus", required = false, defaultValue="all") String qnaStatus
			, HttpSession session) {
		Member member = (Member) session.getAttribute("loginMember");
		if((member.getAdminYN().charAt(0) + "").equals("N")) {
			mv.addObject("msg", "관리자만 접속가능합니다");
			mv.setViewName("/common/errorPage");
		}else {
			try {
				int totalQna = qService.totalQna();
				int totalAnswer = qService.totalAnswer();
				int totalNoAnswer = qService.totalNoAnswer();
				
				int totalCount = qService.getTotalCount("","",qnaStatus);   // 총게시물 구하기(페이징위해)
				int aqnaLimit = 10; // 한페이지당 출력한 게시물 수
				BookPageController bpCont = new BookPageController();
				BookPage bPage = bpCont.boardList(page, totalCount, aqnaLimit);

				if(totalCount > 0) {
					List<Qna> aList = qService.printAllQna(bPage.getCurrentPage(), aqnaLimit, qnaStatus);
					mv.addObject("aList", aList);
				}
				mv.addObject("bPage", bPage);
				mv.addObject("page", page);
				mv.addObject("totalQna", totalQna);
				mv.addObject("totalAnswer", totalAnswer);
				mv.addObject("totalNoAnswer", totalNoAnswer);
				mv.setViewName("/admin/aqnaListView");
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("/common/errorPage");
			}
		}
		return mv;
	}
		
	/**
	 * 관리자 문의내역 상세보기
	 * @param mv
	 * @param searchCondition
	 * @param searchValue
	 * @param page
	 * @param qnaNo
	 * @return
	 */
	@RequestMapping(value="admin/aqnaDetailView.kh", method=RequestMethod.GET)
	public ModelAndView adminQnaDetail(
			ModelAndView mv
			, @RequestParam(value="searchCondition", required=false) String searchCondition
			, @RequestParam(value="searchValue", required=false) String searchValue
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam("qnaNo") Integer qnaNo)  {
		
		try {
			int totalQna = qService.totalQna();
			int totalAnswer = qService.totalAnswer();
			int totalNoAnswer = qService.totalNoAnswer();
			
			Qna qna = qService.printOneByNo(qnaNo);
			mv.addObject("qna", qna);
			mv.addObject("page", page);
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.addObject("totalQna", totalQna);
			mv.addObject("totalAnswer", totalAnswer);
			mv.addObject("totalNoAnswer", totalNoAnswer);
			mv.setViewName("admin/aqnaDetailView");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	//관리자 문의게시글 답변작성
	@RequestMapping(value="/admin/answer.kh", method=RequestMethod.POST)
	public ModelAndView adminQnaAnswer(
			ModelAndView mv
			, @ModelAttribute Qna qna
			, @RequestParam(value= "page", required=false) Integer page
			, HttpServletRequest Request
			, HttpSession session) {
		if(session.getAttribute("loginMember") == null) {
			mv.addObject("msg", "관리자만 답변을 작성할 수 있습니다.");
			mv.setViewName("/common/errorPage");
		}else {
			try {
				System.out.println(qna);
				int result = qService.answerQna(qna);
				mv.setViewName("redirect:/admin/qnaList.kh?page="+page);
			} catch (Exception e) {
				mv.addObject("msg", e.getMessage());
				mv.setViewName("common/errorPage");
			}

		}
		return mv;
	}

	//관리자 게시글 조건 검색
	@RequestMapping(value="/admin/search.kh", method=RequestMethod.GET)
	public ModelAndView adminQnaSearch(
			ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam(value="qnaStatus", required = false, defaultValue="all") String qnaStatus
			, HttpSession session) {

		try {
			int totalQna = qService.totalQna();
			int totalAnswer = qService.totalAnswer();
			int totalNoAnswer = qService.totalNoAnswer();
			int totalCount = qService.getTotalCount(searchCondition, searchValue, qnaStatus);
			int aqnaLimit = 10;
			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, totalCount, aqnaLimit);
			
				if(totalCount>0) {
					List<Qna> aList = qService.printAllByValue(searchCondition, searchValue, bPage.getCurrentPage(), aqnaLimit);
					mv.addObject("aList", aList);
				}
					mv.addObject("bPage", bPage);
					mv.addObject("page", page);
					mv.addObject("searchCondition", searchCondition);
					mv.addObject("searchValue", searchValue);
					mv.addObject("totalQna", totalQna);
					mv.addObject("totalAnswer", totalAnswer);
					mv.addObject("totalNoAnswer", totalNoAnswer);
					mv.setViewName("admin/aqnaListView");
				
			} catch (Exception e) {
				mv.addObject("msg", e.toString()).setViewName("common/errorPage");
			}		
		return mv;
	}
	//문의게시판 카테고리별 리스트
	@RequestMapping(value="/admin/categoryCount.kh", method=RequestMethod.GET)
	public ModelAndView adminQnaCategoryList(
			ModelAndView mv
			, @RequestParam("qnaCategory") String qnaCategory
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int totalQna = qService.totalQna();
			int totalAnswer = qService.totalAnswer();
			int totalNoAnswer = qService.totalNoAnswer();
			
			int currentPage = (page != null) ? page : 1;
			int totalCount = qService.getTotalCount(qnaCategory);
			int categoryLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;
			maxPage = (int)((double)totalCount/categoryLimit + 0.9);
			startNavi = ((int)((double)currentPage/naviLimit+0.9)-1)*naviLimit+1;
			endNavi = startNavi + naviLimit - 1;
			if(maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<Qna> aList = qService.printAllByCategory(qnaCategory, currentPage, categoryLimit);
		
			if(!aList.isEmpty()) {
				mv.addObject("aList", aList);
			}else {
				mv.addObject("aList", null);
			}
			mv.addObject("urlVal", "categoryCount");
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("totalQna", totalQna);
			mv.addObject("totalAnswer", totalAnswer);
			mv.addObject("totalNoAnswer", totalNoAnswer);
			mv.addObject("page", page);
			mv.addObject("qnaCategory", qnaCategory);
			mv.setViewName("admin/aqnaCategoryView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
}
