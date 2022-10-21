package com.books.peanut.qna.controller;

import java.io.File;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.qna.domain.Qna;
import com.books.peanut.qna.service.QnaService;

@Controller
public class QnaController {

	@Autowired
	private QnaService qService;

	//qna등록 화면
	@RequestMapping(value="/qna/writeView", method=RequestMethod.GET)
	public String showQnaWriter() {
		return "/qna/qnaWriteForm";
	}
	
	@ResponseBody
	@RequestMapping(value="/qna/register.kh", method=RequestMethod.POST)
	public ModelAndView registQna(
			ModelAndView mv
			, @ModelAttribute Qna qna
			, @RequestParam("article_file") List<MultipartFile> multipartFile	
			, HttpServletRequest request) {
		
		String strResult = "{ \"result\":\"FAIL\" }";
		String root = request.getSession().getServletContext().getRealPath("resources\\uploadFiles");
		String fileRoot;
		try {
			// 파일이 있을때 탄다.
			if(multipartFile.size() > 0 && !multipartFile.get(0).getOriginalFilename().equals("")) {
				int i=1;
				for(MultipartFile file:multipartFile) {
					fileRoot = root;
					
					System.out.println(fileRoot+"0"+i);
					
					String originalFileName = file.getOriginalFilename();	//오리지날 파일명
					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					//String savedFileName = sdf + extension;	//저장될 파일 명
					String savedFileName 
					= sdf.format(new Date(System.currentTimeMillis()))+extension;
					System.out.println(savedFileName+"0"+i);
					System.out.println(originalFileName+"0"+i);
					File targetFile = new File(fileRoot + savedFileName);
					i++;
					qna.setQnaFilename01(originalFileName+"0"+i);
					qna.setQnaFileRename01(savedFileName+"0"+i);
					qna.setQnaFilepath01(fileRoot+"0"+i);
					try {
						InputStream fileStream = file.getInputStream();
						FileUtils.copyInputStreamToFile(fileStream, targetFile); //파일 저장
						
					} catch (Exception e) {
						//파일삭제
						FileUtils.deleteQuietly(targetFile);	//저장된 현재 파일 삭제
						e.printStackTrace();
						break;
					}
				}
				strResult = "{ \"result\":\"OK\" }";
			}
			// 파일 아무것도 첨부 안했을때 탄다.(게시판일때, 업로드 없이 글을 등록하는경우)
			else
				strResult = "{ \"result\":\"OK\" }";
		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
}
