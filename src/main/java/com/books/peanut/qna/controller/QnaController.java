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

import com.books.peanut.book.domain.WriterProfile;
import com.books.peanut.member.domain.Member;
import com.books.peanut.qna.domain.Qna;
import com.books.peanut.qna.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qService;

	//qna등록 화면
	@RequestMapping(value="/qna/writeView", method=RequestMethod.GET)
	public ModelAndView showQnaWriter(ModelAndView mv, HttpSession session) {
//		로그인 유저용
		Member member = (Member) session.getAttribute("loginMember");
		if(member == null) {
			mv.addObject("msg", "로그인한 회원만 작성가능합니다.");
			mv.setViewName("/common/errorPage");
		}else if(member.getAdminYN().equals("Y")) {
			mv.setViewName("");  //어드민페이지 연결
		}else {
			mv.setViewName("/qna/qnaWriteForm");
		}
		return mv;
	}
	
	//qna등록 주소연결
	@RequestMapping(value="/qna/registView", method=RequestMethod.GET)
	public ModelAndView registQnaView(ModelAndView mv, HttpSession session) {
//		로그인 유저용
		if(session.getAttribute("loginMember") == null) {
			mv.addObject("msg", "로그인한 회원만 접속가능합니다.");
			mv.setViewName("/common/errorPage");
		}else {
			mv.setViewName("/qna/qnaWriteForm");
		}
		return mv;
	}
	
	
	@RequestMapping(value = "/qna/register.kh", method = RequestMethod.POST)
	public ModelAndView registQan(ModelAndView mv, @ModelAttribute Qna qna,
			@RequestParam(value = "uploadFile", required = false) List<MultipartFile> uploadFile, HttpSession session
			, HttpServletRequest request) {
		if(session.getAttribute("loginMember") == null) {
			mv.addObject("msg", "로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");
		}else {
			// 세션으로 id가져옴
			Member member = (Member) session.getAttribute("loginMember");
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
		}
		return mv;
	}
	
	//qna전체 리스트
	@RequestMapping(value="/qna/list.kh", method=RequestMethod.GET)
	public ModelAndView qnaListView(
			ModelAndView mv
			, @ModelAttribute Qna qna
			, @RequestParam(value="page", required = false) Integer page
			, @RequestParam(value="searchCondition", required = false) String searchCondition
			, @RequestParam(value="searchValue", required = false) String searchValue
			, HttpSession session) {
		if(session.getAttribute("loginMember") == null) {
			mv.addObject("msg", "로그인한 유저만 접속가능합니다");
			mv.setViewName("/common/errorPage");
		}else {
			try {
				Member member = (Member)session.getAttribute("loginMember");
				String memberId = member.getMemberId();
				//페이징 구현
				int currentPage = (page != null) ? page : 1;
				// 삼항연산자를 이용하여 페이지값 확인
				// 현재 페이지 = 넘겨받은 페이지값이 널이 아니면 페이지값, 널이면 1
				
				int totalCount = qService.getTotalCount("","");
				int qnaLimit = 10; // 한페이지당 출력한 게시물 수
				int naviLimit = 5; // 한 화면에 출력할 게시판 페이지 수
				int maxPage;	// 총게시물 수
				int startNavi; //한 화면에 출력되는 게시판 페이지의 처음 수
				int endNavi; //한 화면에 출력되는 게시판 페이지의 마지막 수
				
				maxPage = (int)((double) totalCount / qnaLimit + 0.9);
				startNavi = ((int)((double) currentPage / naviLimit + 0.9) -1) * naviLimit+1;
				endNavi = startNavi + naviLimit - 1;
				
				if(maxPage < endNavi) {
					endNavi = maxPage;
				}
				List<Qna> qList = qService.printAllQna(currentPage, qnaLimit, memberId);
				if(!qList.isEmpty()) {
					mv.addObject("memberId", memberId);
					mv.addObject("urlVal", "list");
					mv.addObject("maxPage", maxPage);
					mv.addObject("currentPage", currentPage);
					mv.addObject("startNavi", startNavi);
					mv.addObject("endNavi", endNavi);
					mv.addObject("qList", qList);
					///페이징 종료
					
					/// 검색어 전송 ///
	//				mv.addObject("pageNow", currentPage);
	//				mv.addObject("searchValue", searchValue);
	//				mv.addObject("searchCondition", searchCondition);
				}
				mv.setViewName("/qna/qnaListView");
			} catch (Exception e) {
				e.printStackTrace();
				mv.addObject("msg", e.getMessage());
				mv.setViewName("/common/errorPage");
			}
		}
		return mv;
	}
	
	//qna상세보기
	@RequestMapping(value="/qna/detailView.kh", method=RequestMethod.GET)
	public ModelAndView qnaDetailView(
			ModelAndView mv
			, @RequestParam(value="page", required=false) Integer page
			, @RequestParam("qnaNo") Integer qnaNo) {
		try {
			Qna qna = qService.printOneByNo(qnaNo);
			mv.addObject("qna", qna);
			mv.addObject("page", page);
			mv.setViewName("qna/qnaDetailView");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
		
	}
	
	//qna게시글 삭제
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
	
	//qna게시글 수정화면
	@RequestMapping(value="/qna/modifyView.kh", method=RequestMethod.GET)
	public ModelAndView qnaModifyView(
			ModelAndView mv
			, @RequestParam("page") int page
			, @RequestParam("qnaNo") Integer qnaNo) {
		try {
			Qna qna = qService.printOneByNo(qnaNo);
			mv.addObject("qna", qna);
			mv.addObject("page", page);
			mv.setViewName("/qna/qnaModifyForm");
			
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	//qna게시글 수정
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
						qnaFilepath[i] = savePath+"\\"+qnaFileRename;
						reloadFile.get(i).transferTo(new File(qnaFilepath[i]));
						// 파일을 buploadFiles 경로에 저장하는 메소드
						
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
}
