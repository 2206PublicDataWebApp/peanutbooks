package com.books.peanut.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.books.peanut.notice.domain.Notice;
import com.books.peanut.notice.service.NoticeService;


@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;
	
	//공지사항 작성 페이지 이동
	@RequestMapping(value="/notice/writeView.kh", method=RequestMethod.GET)
	public String reviewWriteView(HttpSession session) {
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {
			return "/notice/noticeWriteForm";
		}
		return "member/login";
	}

	//공지사항 게시물 등록
	@RequestMapping(value="/notice/register.kh", method=RequestMethod.POST)
	public ModelAndView registerNotice(
			ModelAndView mv
			, @ModelAttribute Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			,HttpServletRequest request) {
		
		try {
			String noticeFilename = uploadFile.getOriginalFilename();
			if(!noticeFilename.equals("")) {
				// 파일 업로드 
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\nuploadFiles";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String noticeFileRename 
				= sdf.format(new Date(System.currentTimeMillis()))+"."
						+noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				// 파일이름 설정
				File file = new File(savePath);
				if(!file.exists()) {
					file.mkdir();
				}
				
				uploadFile.transferTo(new File(savePath+"\\"+noticeFileRename)); 
				// 파일을 buploadFiles 경로에 저장하는 메소드
				String noticeFilepath = savePath+"\\"+noticeFileRename;
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath);
				
			}
			int result = nService.registeNotice(notice);
			mv.setViewName("redirect:/notice/list.kh");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/common/errorPage");
		}
		return mv;
		
	}

	//공지사항 전체 리스트
	@RequestMapping(value="/notice/list.kh", method = RequestMethod.GET)
	public ModelAndView noticeListView(
			ModelAndView mv
			, @RequestParam(value="page", required = false) Integer page){
		try {
			//페이징
			int totalCount = nService.getTotalCount("", "");
			int noticeLimit = 10;
			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, totalCount, noticeLimit);
			
			if(totalCount>0) {
				List<Notice> nList = nService.printAllNotice(bPage.getCurrentPage(), noticeLimit);
				mv.addObject("nList", nList);
			}
			mv.addObject("bPage", bPage);
			mv.addObject("page", page);
			mv.setViewName("notice/noticeListView");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("/common/errorPage");
		}
		return mv;
	}
	
	//공지사항 상세보기
	@RequestMapping(value="/notice/noticeDetailView.kh", method=RequestMethod.GET)
	public ModelAndView noticeDetailView(
			ModelAndView mv
			, @RequestParam("noticeNo") Integer noticeNo
			, @RequestParam("page") int page
			, HttpSession session) {
		Member member = (Member)session.getAttribute("loginMember");
		try {
			Notice notice = nService.printOneByNo(noticeNo);
			mv.addObject("notice", notice);
			mv.addObject("page", page);
			mv.setViewName("notice/noticeDetailView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	//공지사항 게시글 삭제
	@RequestMapping(value="/notice/remove.kh", method=RequestMethod.GET)
	public String noticeRemove(
			HttpSession session
			, @RequestParam("page") Integer page
			, @RequestParam("noticeNo") Integer noticeNo) {
		try {
			int result = nService.removeOneByNo(noticeNo);
			if(result > 0) {
				return "redirect:/notice/list.kh?page="+page;
			}
		} catch (Exception e) {
			return "common/errorPage";
		}
		return "redirect:/notice/list.kh?page="+page;
	}
	
	//공지사항 수정화면
	@RequestMapping(value="/notice/modifyView.kh", method=RequestMethod.GET)
	public ModelAndView noticeModifyView(
			ModelAndView mv
			, @RequestParam("page") int page
			, @RequestParam("noticeNo") Integer noticeNo) {
		try {
			Notice notice = nService.printOneByNo(noticeNo);
			mv.addObject("notice", notice);
			mv.addObject("page", page);
			mv.setViewName("/notice/noticeModifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	//공지사항 수정
	@RequestMapping(value="/notice/modify.kh", method=RequestMethod.POST)
	public ModelAndView boardModify(
			@ModelAttribute Notice notice
			, ModelAndView mv
			,@RequestParam(value="reloadFile", required=false) MultipartFile reloadFile
			,@RequestParam("page") Integer page
			,HttpServletRequest request) {
		try {
			String noticeFilename = reloadFile.getOriginalFilename();
			if(reloadFile != null && !noticeFilename.equals("")) {
				// 수정, 1. 대체(replace) / 2. 삭제 후 저장
				// 파일삭제
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\nUploadFiles";
				File file = new File(savedPath + "\\" + notice.getNoticeFileRename());
				if(file.exists()) {
					file.delete();
				}
				// 파일 다시 저장
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String noticeFileRename = sdf.format(new Date(System.currentTimeMillis()))
						+ "." + noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				String noticeFilepath = savedPath + "\\" + noticeFileRename;
				reloadFile.transferTo(new File(noticeFilepath));
				notice.setNoticeFilename(noticeFilename);
				notice.setNoticeFileRename(noticeFileRename);
				notice.setNoticeFilepath(noticeFilepath);
			}
			int result = nService.modifyNotice(notice);
			mv.setViewName("redirect:/notice/list.kh?page="+page);
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	//조건 검색
	@RequestMapping(value="/notice/search.kh", method=RequestMethod.GET)
	public ModelAndView noticeSearchList(
			ModelAndView mv
			, @RequestParam("searchCondition") String searchCondition
			, @RequestParam("searchValue") String searchValue
			, @RequestParam(value="page", required=false) Integer page) {
				
		try {
			int totalCount = nService.getTotalCount(searchCondition, searchValue);
			int noticeLimit = 10;
			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, totalCount, noticeLimit);
			
			if(totalCount>0) {
				List<Notice> nList = nService.printAllByValue(
						searchCondition, searchValue, bPage.getCurrentPage(), noticeLimit);
				mv.addObject("nList", nList);
				
			}
			mv.addObject("bPage", bPage);
			mv.addObject("page", page);
			mv.addObject("searchCondition", searchCondition);
			mv.addObject("searchValue", searchValue);
			mv.setViewName("notice/noticeListView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
		
	}
	//공지사항 카테고리별 리스트
	@RequestMapping(value="/notice/categoryCount.kh", method=RequestMethod.GET)
	public ModelAndView noticeCategoryList(
			ModelAndView mv
			, @RequestParam("noticeCategory") String noticeCategory
			, @RequestParam(value="page", required=false) Integer page) {
		try {
			int totalCount = nService.getTotalCount(noticeCategory);
			int categoryLimit = 10;

			BookPageController bpCont = new BookPageController();
			BookPage bPage = bpCont.boardList(page, totalCount, categoryLimit);
			if(totalCount>0) {
				List<Notice> nList = nService.printAllByCategory(noticeCategory, bPage.getCurrentPage(), categoryLimit);
				mv.addObject("nList", nList);
			}
			mv.addObject("bPage", bPage);
			mv.addObject("page", page);
			mv.addObject("noticeCategory", noticeCategory);
			mv.setViewName("notice/noticeListView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
		
	}
	
	
	
}
