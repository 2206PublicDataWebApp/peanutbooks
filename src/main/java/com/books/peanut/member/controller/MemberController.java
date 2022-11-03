package com.books.peanut.member.controller;

import java.util.HashMap;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.member.domain.Member;
import com.books.peanut.member.service.MemberService;
import com.books.peanut.pay.payService.PayService;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
	@Autowired
	private PayService pService;
	@Autowired
	private JavaMailSender mailSender; // mailSender Bean 의존성 주입
	
	/**
	 * 회원가입 화면
	 * @return
	 */
	@RequestMapping(value="/member/joinView.pb", method=RequestMethod.GET)
	public String memberJoinView() {
		return "member/join";
	}
	
	/**
	 * 회원가입 기능
	 * @param member
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/member/join.pb", method=RequestMethod.POST)
	public ModelAndView memberJoin(
			@ModelAttribute Member member,
			ModelAndView mv) {
		try {
			int result = mService.registerMember(member); // 회원가입
			
			String authKey = this.sendEmail(member.getmEmail()); // 메일 발송 및 인증 키 저장
			String mEmail = member.getmEmail();
			
			mService.saveAuthKey(authKey, mEmail); // 메일 발송 후 해당 메일의 인증 키만 업데이트로 저장
			
			if(result > 0) {
				mv.addObject("msg", "입력하신 이메일 주소로 인증번호를 발송했습니다."); // 회원가입 성공 시 alert 창 띄운 후
				mv.addObject("url", "/member/confirmEmailView.pb?memberId="+member.getMemberId()); // 인증번호 입력 페이지로 이동
				mv.setViewName("common/alert");
			} else {
				mv.setViewName("redirect:/member/joinView.pb"); // 회원가입 실패 시 회원가입 페이지로 이동(임시)
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage"); // 에러 확인용
		}
		return mv;
	}
	
	/**
	 * 인증 메일 전송
	 * @param mEmail
	 * @return
	 * @throws Exception
	 */
//	@ResponseBody
//	@RequestMapping(value="/member/sendEmail.pb", method=RequestMethod.GET)
	public String sendEmail(String mEmail) throws Exception{
		// 인증번호(난수) 생성
		Random random = new Random();
		int authKey = random.nextInt(88888) + 11111;
		
		String subject = "[땅콩북스] 회원가입 인증번호";
		String content = "아래의 인증번호를 인증번호 입력창에 입력하세요.<br>인증번호는 " + authKey + " 입니다."; // 이미지 첨부 -> "내용" + "<img src=\"이미지 경로\">"
		String from = "땅콩북스 <realpeanutbooks@gmail.com>"; // 이메일만 혹은 이름 + <이메일> 가능
		String to = mEmail;
		
		try {
			MimeMessage msg = mailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8"); // true는 multipart 메시지(이미지 파일 등) 사용 명시
			
			msgHelper.setFrom(from); // 메일 발신자
			msgHelper.setTo(to); // 메일 수신자
			msgHelper.setSubject(subject); // 메일 제목
			msgHelper.setText(content, true); // 메일 내용 // true는 html 사용 명시, 사용하지 않을 시 생략 가능
			mailSender.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String authNum = Integer.toString(authKey); // ajax로 반환하는 값은 String 타입만 가능하므로 형변환 처리
		
		return authNum;
	}
	
	// 인증 키 검사
	@RequestMapping(value="/membe/checkAuthKey.pb", method=RequestMethod.GET)
	public String checkAuthKey(
			@RequestParam("authKey") String authKey,
			@RequestParam("memberId") String memberId) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("authKey", authKey);
		paramMap.put("memberId", memberId);
		int result = mService.checkAuthKey(paramMap);
		return String.valueOf(result);
	}
	
	/**
	 * 이메일 인증 확인 화면
	 * @return
	 */
	@RequestMapping(value="/member/confirmEmailView.pb", method=RequestMethod.GET)
	public String confirmEmailView(
			@RequestParam("memberId") String memberId,
			Model model) {
		model.addAttribute("memberId", memberId);
		return "member/confirmEmail";
	}
	
	/**
	 * 별명 유효성 검사
	 * @param mNickname
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member/checkNickname.pb", method=RequestMethod.GET)
	public String checkNickname(
			@RequestParam("mNickname") String mNickname) {
		int result = mService.checkNickname(mNickname);
		return String.valueOf(result);
	}
	
	/**
	 * 아이디 유효성 검사
	 * @param memberId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member/checkId.pb", method=RequestMethod.GET)
	public String checkId(
			@RequestParam("memberId") String memberId) {
		int result = mService.checkId(memberId);
		return String.valueOf(result);
	}
	
	/**
	 * 이메일 유효성 검사
	 * @param mEmail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member/checkEmail.pb", method=RequestMethod.GET)
	public String checkEmail(
			@RequestParam("mEmail") String mEmail) {
		int result = mService.checkEmail(mEmail);
		return String.valueOf(result);
	}
	
	/**
	 * 로그인 화면
	 * @return
	 */
	@RequestMapping(value="/member/loginView.pb", method=RequestMethod.GET)
	public String memberLoginView() {
		return "member/login";
	}
	
	/**
	 * 로그인 기능
	 * @param member
	 * @param mv
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/login.pb", method=RequestMethod.POST)
	public ModelAndView memberLogin(
			@ModelAttribute Member member,
			ModelAndView mv,
			HttpServletRequest request) {
		try {
			Member loginMember = mService.loginMember(member);
			if(loginMember != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember); // session에 로그인한 회원의 모든 정보(loginMember) 저장
				// 구독권 가져오는 부분
				String lastDate = pService.seasonTicketDate(loginMember.getMemberId());			
				session.setAttribute("lastDate", lastDate);
				//구독권 가져오는부분 종료
				// 로그인한 회원이 저장한 도서 수 가져오기
//				int savedBooks = mService.countSavedBooks(loginMember.getMemberId());
//				session.setAttribute("savedBooks", savedBooks);
				// 로그인한 회원이 등록한 작품 수 가져오기
				int writtenBooks = mService.countWrittenBooks(loginMember.getMemberId());
				session.setAttribute("writtenBooks", writtenBooks);
				mv.setViewName("redirect:/main"); // 로그인 성공 시 로그인 후 메인 페이지로 이동
			} else {
				mv.setViewName("redirect:/member/loginView.pb"); // 로그인 실패 시 로그인 페이지로 이동(임시)
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage"); // 에러 확인용
		}
		return mv;
	}
	
	/**
	 * 로그아웃 기능
	 * @param mv
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/logout.pb", method=RequestMethod.GET)
	public ModelAndView memberLogout(
			ModelAndView mv,
			HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			if(session != null) {
				session.invalidate();
				mv.setViewName("redirect:/"); // 로그아웃 성공 시 로그인 전 메인 페이지로 이동
			}else {
				mv.setViewName("redirect:/main"); // 로그아웃 실패 시 로그인 후 메인 페이지로 이동(임시)
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage"); // 에러 확인용
		}
		return mv;
	}
	
	/**
	 * 아이디 찾기 화면
	 * @return
	 */
	@RequestMapping(value="/member/forgotId.pb", method=RequestMethod.GET)
	public String forgotIdView() {
		return "member/forgotId";
	}
	
	/**
	 * 아이디 찾기 - 결과 화면
	 * @return
	 */
	@RequestMapping(value="/member/idResult.pb", method=RequestMethod.GET)
	public String idResultView() {
		return "member/idResult";
	}
	
	/**
	 * 비밀번호 재설정 화면
	 * @return
	 */
	@RequestMapping(value="/member/forgotPw.pb", method=RequestMethod.GET)
	public String forgotPwView() {
		return "member/forgotPw";
	}
	
	/**
	 * 비밀번호 재설정 - 새 비밀번호 입력 화면
	 * @return
	 */
	@RequestMapping(value="/member/resetPw.pb", method=RequestMethod.GET)
	public String resetPwView() {
		return "member/resetPw";
	}
	
	/**
	 * 정보 관리 화면
	 * @return
	 */
	@RequestMapping(value="/member/memberInfo.pb", method=RequestMethod.GET)
	public String memberInfoView() {
		return "member/memberInfo";
	}
	
	/**
	 * 정보 관리 - 비밀번호 확인 화면
	 * @return
	 */
	@RequestMapping(value="/member/pwCheck.pb", method=RequestMethod.GET)
	public String pwCheckView() {
		return "member/pwCheck";
	}
	
	/**
	 * 정보 관리 - 회원 정보 수정 화면
	 * @return
	 */
	@RequestMapping(value="/member/modifyView.pb", method=RequestMethod.GET)
	public String modifyView() {
		return "member/modifyInfo";
	}
	
	/**
	 * 회원 정보 수정 기능
	 * @param member
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/member/modify.pb", method=RequestMethod.POST)
	public ModelAndView ModifyMemberInfo(
			@ModelAttribute Member member,
			ModelAndView mv) {
		try {
			int result = mService.modifyInfo(member);
			if(result > 0) {
				mv.setViewName("redirect:/member/memberInfo.pb");
			}else{
				mv.setViewName("redirect:/member/modifyView.pb");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	/**
	 * 회원탈퇴 화면
	 * @return
	 */
	@RequestMapping(value="/member/deleteView.pb", method=RequestMethod.GET)
	public String deleteView() {
		return "member/deleteMember";
	}
	
	/**
	 * 회원탈퇴 기능
	 * @param member
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/member/delete.pb", method=RequestMethod.POST)
	public ModelAndView deleteMember(
			@ModelAttribute Member member,
			ModelAndView mv,
			HttpServletRequest request) {
		try {
			int result = mService.deleteMember(member);
			if(result > 0) {
				HttpSession session = request.getSession();
				if(session != null) {
					session.invalidate();
				}
				mv.setViewName("redirect:/");
			}else{
				mv.setViewName("redirect:/member/deleteView.pb");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}
	
	/**
	 * 알림 - 내 알림 화면
	 * @return
	 */
	@RequestMapping(value="/news/myNews.pb", method=RequestMethod.GET)
	public String myNewsView() {
		return "news/news-my";
	}

	//은정이가 잠깐 사용할 예정
	@RequestMapping(value="/member/login.kh", method=RequestMethod.POST)
	public ModelAndView memberLogin2(
			@ModelAttribute Member member,
			ModelAndView mv,
			HttpServletRequest request) {
		try {
			Member loginMember = mService.loginMember(member);
			if(loginMember != null) {
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember); // session에 로그인한 회원의 모든 정보(loginMember) 저장
				mv.addObject("loginMember", loginMember);  //추가함//
				mv.setViewName("redirect:/ej.kh");
			} else {
				mv.setViewName("error");
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("error"); // 에러 확인용
		}
		return mv;

		
	}
	
}
