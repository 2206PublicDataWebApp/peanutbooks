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

import com.books.peanut.notice.domain.Notice;
import com.books.peanut.notice.service.NoticeService;


@Controller
public class NoticeController {

	@Autowired
	NoticeService nService;
	
	//공지사항 작성 페이지 이동
	@RequestMapping(value="/notice/writeView.kh", method=RequestMethod.GET)
	public String reviewWriteView(HttpSession session) {
		//Member loginUser = (Member)session.getAttribute("loginUser");
		//if(loginUser ! = null) {
			return "/notice/noticeWriteForm";
		//}
		//return "redirect:/notice/list.kh";
	}
	//공지사항 게시물 등록
	@RequestMapping(value="/notice/register.kh", method=RequestMethod.POST)
	public ModelAndView registerNotice(
			ModelAndView mv
			, @ModelAttribute Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request){
		//공지사항 작성 후 insert
		try {
			System.out.println(uploadFile);
			String noticeFilename = uploadFile.getOriginalFilename();
			System.out.println(noticeFilename);
			if(!noticeFilename.equals("")) {
				
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\nUploadFiles";   // 파일 저장경로 설정
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String noticeFileRename = sdf.format(new Date(System.currentTimeMillis()))+"."
						+noticeFilename.substring(noticeFilename.lastIndexOf(".")+1);
				
				File file = new File(savePath);
				if(!file.exists()) {
					file.mkdir();
				}
				uploadFile.transferTo(new File(savePath+"\\"+noticeFileRename));
				// 파일을 nUploadFiles 경로에 저장하는 메소드
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
			//, @ModelAttribute Notice notce
			, @RequestParam(value="page", required = false) Integer page)
//			, HttpServletRequest request) 
	{
		try {
			//페이징
			int currentPage = (page != null) ? page : 1;
			int totalCount = nService.getTotalCount("", "");
			int noticeLimit = 10;
			int naviLimit = 5;
			int maxPage;
			int startNavi;
			int endNavi;

			maxPage = (int) ((double) totalCount / noticeLimit + 0.9);
			startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
			endNavi = startNavi + naviLimit - 1;
			if (maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<Notice> nList = nService.printAllNotice(currentPage, noticeLimit);
			if(!nList.isEmpty()) {
				mv.addObject("urlVal", "list");
				mv.addObject("maxPage", maxPage);
				mv.addObject("currentPage", currentPage);
				mv.addObject("startNavi", startNavi);
				mv.addObject("endNavi", endNavi);
				mv.addObject("nList", nList);
	
			}
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
			, @RequestParam("page") int page) {
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
				//Board bOne = bService.printOneByNo(board.getBoardNo());
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
}
