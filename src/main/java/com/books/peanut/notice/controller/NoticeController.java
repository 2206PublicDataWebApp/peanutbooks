package com.books.peanut.notice.controller;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.notice.domain.Notice;
import com.books.peanut.notice.service.NoticeService;
import com.google.gson.JsonObject;


@Controller
public class NoticeController {

	@Autowired
	NoticeService nService;
	
	//공지사항 작성 페이지 이동
	@RequestMapping(value="/notice/writeView.kh", method=RequestMethod.GET)
	public String reviewWriteView(HttpSession session) {
		//Member loginUser = (Member)session.getAttribute("loginUser");
		//if(loginUser ! = null) {
			return "/notice/noticeWrite";
		//}
		//return "redirect:/notice/list.kh";
	}
	
	@RequestMapping(value="/notice/register.kh", method=RequestMethod.POST)
	public ModelAndView noticeWrite(
			ModelAndView mv
			, @ModelAttribute Notice notice) {
		//공지사항 작성 후 insert
		int result = nService.registeNotice(notice);
		try {
			if(result >0 ) {
				mv.setViewName("redirect: /notice/list.kh? currentPage=1");
			}else {
				mv.addObject("msg", "공지사항 입력에 실패하였습니다.").setViewName("/common/errorPage");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.getMessage()).setViewName("/common/errorPage");
		}
				return mv;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/notice/uploadSummernoteImageFile", method = RequestMethod.POST)
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile
			, HttpServletRequest request) {
		JsonObject jsonObj = new JsonObject();
		try {
			//업로드한 파일을 MultipartFile로 받는다.
			
			//1. 파일 이름과 경로를 설정
			String originalFileName = multipartFile.getOriginalFilename();
			String root = request.getSession().getServletContext().getRealPath("resources");
			String savePath = root + "\\image\\notice\\summerImageFiles";
			
			//2.파일이름이 중복되지 않도록 재정의 해준다.
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String boardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "." + extension;
			
			//3.저장할 경로의 폴더(디렉토리)가 없으면 새로 만든다.
			File targetFile = new File(savePath);
			if (!targetFile.exists()) {
				targetFile.mkdir();
			}
			
			//4.설정한경로에 재정의한 이름으로 파일을 저장한다.
			multipartFile.transferTo(new File(savePath + "\\" + boardFileRename));
			
			//5.ajax의 success로 리턴해줄 json오브젝트에 프로퍼티를 저장해준다.
			// 1)썸머노트의 insertImage 설정값에 넣어줄 파일의 경로.
			// 2)원래 파일이름
			// 3)ajax 성공여부
			jsonObj.addProperty("url", "/resources/image/notice/summerImageFiles/" + boardFileRename);
			jsonObj.addProperty("originName", originalFileName);
			jsonObj.addProperty("responseCode", "success");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return jsonObj;
		
	}
	
}
