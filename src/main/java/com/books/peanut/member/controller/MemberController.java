package com.books.peanut.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import com.books.peanut.news.service.NewsService;
import com.books.peanut.pay.payService.PayService;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class MemberController {
	@Autowired
	private MemberService mService;
	@Autowired
	private PayService pService;
	@Autowired
	private NewsService nService;
	@Autowired
	private JavaMailSender mailSender; // mailSender Bean 의존성 주입
	// 네이버 로그인
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	// 카카오 로그인
	@Autowired
	private KakaoLoginBO kakaoLoginBO;
	
	/**
	 * 인증 메일 전송
	 * @param mEmail
	 * @return
	 * @throws Exception
	 */
	public String sendEmail(String mEmail) throws Exception{
		// 인증번호(난수) 생성
		Random random = new Random();
		int authKey = random.nextInt(88888) + 11111;
		
		String subject = "[땅콩북스] 인증번호";
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

	/**
	 * 회원가입 화면
	 * @return
	 */
	@RequestMapping(value="/member/joinView.pb", method= {RequestMethod.GET, RequestMethod.POST})
	public String memberJoinView(Model model, HttpSession session) {
		
		// 네아로 인증 url을 생성하기 위하여 NaverLoginBO 클래스의 getAuthorizationUrl 메소드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		System.out.println("네이버 url: " + naverAuthUrl);
		model.addAttribute("urlNaver", naverAuthUrl); // 객체 바인딩
		
		// 카카오 url
		String kakaoAuthUrl = kakaoLoginBO.getAuthorizationUrl(session);
		System.out.println("카카오 url: " + kakaoAuthUrl);
		model.addAttribute("urlKakao", kakaoAuthUrl);
		
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
			// 비밀번호 암호화 시작
			System.out.println("암호화 전 비밀번호: " + member.getMemberPw());
			String encryptedPw = Sha256.encrypt(member.getMemberPw()); // 회원가입 jsp에서 입력된 비밀번호를 암호화해서 encryptedPw에 넣기
			member.setMemberPw(encryptedPw); // 암호화된 비밀번호를 멤버 객체의 비밀번호로 넣음
			System.out.println("암호화 후 비밀번호: " + member.getMemberPw());
			// 비밀번호 암호화 끝
			
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
	 * sns 회원가입 화면
	 * @return
	 */
	@RequestMapping(value="/member/snsjoinView.pb", method=RequestMethod.GET)
	public String snsJoinView() {
		return "member/snsJoin";
	}
	
	/**
	 * sns 회원가입 기능
	 * @param member
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/member/snsJoin.pb", method=RequestMethod.POST)
	public ModelAndView snsJoin(
			@ModelAttribute Member member,
			ModelAndView mv) {
		try {
			
			int result = mService.snsJoin(member); // 회원가입
			
			String authKey = this.sendEmail(member.getmEmail()); // 메일 발송 및 인증 키 저장
			String mEmail = member.getmEmail();
			
			mService.saveAuthKey(authKey, mEmail); // 메일 발송 후 해당 메일의 인증 키만 업데이트로 저장
			
			if(result > 0) {
				mv.addObject("msg", "입력하신 이메일 주소로 인증번호를 발송했습니다."); // 회원가입 성공 시 alert 창 띄운 후
				mv.addObject("url", "/member/confirmEmailView.pb?memberId="+member.getMemberId()); // 인증번호 입력 페이지로 이동
				mv.setViewName("common/alert");
			} else {
				mv.setViewName("redirect:/member/snsjoinView.pb"); // 회원가입 실패 시 회원가입 페이지로 이동(임시)
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage"); // 에러 확인용
		}
		return mv;
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
	 * 아이디 찾기 화면
	 * @return
	 */
	@RequestMapping(value="/member/forgotId.pb", method=RequestMethod.GET)
	public String forgotIdView() {
		return "member/forgotId";
	}

	/**
	 * 이메일로 아이디 찾기
	 * @param mEmail
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member/getIdByEmail.pb", produces="application/json;charset=utf-8", method=RequestMethod.GET)
	public String findIdAuth(@RequestParam("mEmail") String mEmail) {
		try {
			mService.resetAuthKey(mEmail); // 기존 인증 키 삭제
			String authKey = sendEmail(mEmail); // 인증 메일 발송
			mService.saveAuthKey(authKey, mEmail); // 인증키 db에 저장
			String memberId = mService.getIdByEmail(mEmail); // 이메일로 아이디 찾기
			HashMap<String, String> authData = new HashMap<String, String>();
			authData.put("memberId", memberId);
			authData.put("authKey", authKey);
			List<Member> result = mService.getMemberInfo(authData);
			if(!result.isEmpty()) {
				Gson gson = new GsonBuilder().create();
				return gson.toJson(result);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 이메일로 회원 여부 확인
	 * @param mEmail
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member/checkMemberByEmail", method=RequestMethod.GET)
	public String checkMemberByEmail(@RequestParam("mEmail") String mEmail) {
		int result = mService.checkMemberByEmail(mEmail);
		return String.valueOf(result);
	}
	
	/**
	 * 인증 키 검사
	 * @param authKey
	 * @param memberId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/member/checkAuthKey.pb", method=RequestMethod.GET)
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
	 * 이메일 인증(이메일 인증 여부 컬럼 'Y'로 업데이트)
	 * @param memberId
	 * @param authKey
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/member/authEmail.pb", method=RequestMethod.POST)
	public ModelAndView authEmail(
			@RequestParam("memberId") String memberId,
			@RequestParam("authKey") String authKey,
			ModelAndView mv) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("authKey", authKey);
		paramMap.put("memberId", memberId);
		int result = mService.authEmail(paramMap);
		if(result > 0) {
			mv.setViewName("redirect:/"); // 성공 시 로그인 전 메인 페이지로
		}
		return mv;
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
	@RequestMapping(value="/member/loginView.pb", method = { RequestMethod.GET, RequestMethod.POST })
	public String memberLoginView(Model model, HttpSession session) {
		
		// 네아로 인증 url을 생성하기 위하여 NaverLoginBO 클래스의 getAuthorizationUrl 메소드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		System.out.println("네이버 url: " + naverAuthUrl);
		model.addAttribute("urlNaver", naverAuthUrl); // 객체 바인딩
		
		// 카카오 url
		String kakaoAuthUrl = kakaoLoginBO.getAuthorizationUrl(session);
		System.out.println("카카오 url: " + kakaoAuthUrl);
		model.addAttribute("urlKakao", kakaoAuthUrl);
		
		return "member/login";
	}
	
	// 네이버 로그인 성공 시 callback 호출 메소드
	@RequestMapping(value = "/callbackNaver.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackNaver(
			Model model,
			@RequestParam String code,
			@RequestParam String state,
			HttpSession session,
			HttpServletRequest request)
			throws Exception {
		OAuth2AccessToken oauthToken;
        oauthToken = naverLoginBO.getAccessToken(session, code, state);
        // 로그인 사용자 정보를 읽어옴
	    apiResult = naverLoginBO.getUserProfile(oauthToken);
	    
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("response"); // 데이터 가져오기
		// 프로필 조회
		String id = (String) response_obj.get("id");
		String email = (String) response_obj.get("email");
		String nickname = (String) response_obj.get("nickname");

		int result = mService.selectMemberById(id);
		if(result > 0) {
			Member loginMember = mService.snsLogin(id);
			if(loginMember != null) {
				session = request.getSession();
				session.setAttribute("loginMember", loginMember); // session에 로그인한 회원의 모든 정보(loginMember) 저장
				// 구독권 가져오는 부분 시작
				String lastDate = pService.seasonTicketDate(loginMember.getMemberId());			
				session.setAttribute("lastDate", lastDate);
				//구독권 가져오는 부분 종료
				// 로그인한 회원이 저장한 도서 수 가져오기
				int savedBooks = mService.countSavedBooks(loginMember.getMemberId());
				session.setAttribute("savedBooks", savedBooks);
				// 로그인한 회원이 등록한 작품 수 가져오기
				int writtenBooks = mService.countWrittenBooks(loginMember.getMemberId());
				session.setAttribute("writtenBooks", writtenBooks);
				// 알림 개수 가져오기
				int countNews = nService.countNews(loginMember.getMemberId());
				session.setAttribute("countNews", countNews);
				return "redirect:/main"; // 로그인 성공 시 로그인 후 메인 페이지로 이동
			}
		}else {
			session.setAttribute("msg", "해당 SNS로 등록된 회원정보가 없습니다. 계정이 없는 경우 회원가입이 필요합니다.");
			session.setAttribute("url", "/member/snsjoinView.pb?snsId="+id+"&mEmail="+email+"&mNickname="+nickname+"&accType=naver");
			return "redirect:/alertView.pb";
		}

		return null;
	}
	
	// 카카오 로그인 성공 시 callback
	@RequestMapping(value = "/callbackKakao.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String callbackKakao(
			Model model,
			@RequestParam String code,
			@RequestParam String state,
			HttpSession session,
			HttpServletRequest request) 
			throws Exception {
		OAuth2AccessToken oauthToken;
		oauthToken = kakaoLoginBO.getAccessToken(session, code, state);	
		// 로그인 사용자 정보를 읽어옴.
		apiResult = kakaoLoginBO.getUserProfile(oauthToken);
		System.out.println(apiResult);
		
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObj;
		
		jsonObj = (JSONObject) jsonParser.parse(apiResult);
		JSONObject response_obj = (JSONObject) jsonObj.get("kakao_account");	
		JSONObject response_obj2 = (JSONObject) response_obj.get("profile");
		// 프로필 조회
		String email = (String) response_obj.get("email");
		String nickname = (String) response_obj2.get("nickname");
		String id = jsonObj.get("id")+"";

		int result = mService.selectMemberById(id);
		if(result > 0) {
			Member loginMember = mService.snsLogin(id);
			if(loginMember != null) {
				session = request.getSession();
				session.setAttribute("loginMember", loginMember); // session에 로그인한 회원의 모든 정보(loginMember) 저장
				// 구독권 가져오는 부분 시작
				String lastDate = pService.seasonTicketDate(loginMember.getMemberId());			
				session.setAttribute("lastDate", lastDate);
				//구독권 가져오는 부분 종료
				// 로그인한 회원이 저장한 도서 수 가져오기
				int savedBooks = mService.countSavedBooks(loginMember.getMemberId());
				session.setAttribute("savedBooks", savedBooks);
				// 로그인한 회원이 등록한 작품 수 가져오기
				int writtenBooks = mService.countWrittenBooks(loginMember.getMemberId());
				session.setAttribute("writtenBooks", writtenBooks);
				// 알림 개수 가져오기
				int countNews = nService.countNews(loginMember.getMemberId());
				session.setAttribute("countNews", countNews);
				return "redirect:/main"; // 로그인 성공 시 로그인 후 메인 페이지로 이동
			}
		}else {
			session.setAttribute("msg", "해당 SNS로 등록된 회원정보가 없습니다. 계정이 없는 경우 회원가입이 필요합니다.");
			session.setAttribute("url", "/member/snsjoinView.pb?snsId="+id+"&mEmail="+email+"&mNickname="+nickname+"&accType=kakao");
			return "redirect:/alertView.pb";
		}

		return null;
	}
	
	/**
	 * alert만 뜨는 화면
	 * @return
	 */
	@RequestMapping(value="/alertView.pb", method=RequestMethod.GET)
	public String alertView() {
		return "common/alert";
	}
	
	/**
	 * 이메일 인증 안 된 회원 로그인 전 인증 유도 화면
	 * @return
	 */
	@RequestMapping(value="/member/reAuthView.pb", method=RequestMethod.GET)
	public String reAuthView(Model model, @RequestParam("mEmail") String mEmail, @RequestParam("memberId") String memberId) {
		model.addAttribute("mEmail", mEmail);
		model.addAttribute("memberId", memberId);
		return "member/reAuthEmail";
	}
	
	/**
	 * 로그인 기능(일반)
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
			// 비밀번호 암호화 시작
			System.out.println("로그인-암호화 전 비밀번호: " + member.getMemberPw());
			String encryptedPw = Sha256.encrypt(member.getMemberPw()); // 회원가입 jsp에서 입력된 비밀번호를 암호화해서 encryptedPw에 넣기
			member.setMemberPw(encryptedPw); // 암호화된 비밀번호를 멤버 객체의 비밀번호로 넣음
			System.out.println("로그인-암호화 후 비밀번호: " + member.getMemberPw());
			// 비밀번호 암호화 끝
			Member loginMember = mService.loginMember(member);
			if(loginMember.getEmailYN().equals("N")) {
				HttpSession session = request.getSession();
				session.setAttribute("msg", "회원가입이 완료되지 않았습니다. 이메일 인증을 완료해 주세요.");
				session.setAttribute("url", "/member/reAuthView.pb?mEmail="+loginMember.getmEmail()+"&memberId="+loginMember.getMemberId()); // 인증번호 입력 페이지로 이동
				mv.setViewName("redirect:/alertView.pb");
			}else {
				if(loginMember != null) {
					HttpSession session = request.getSession();
					session.setAttribute("loginMember", loginMember); // session에 로그인한 회원의 모든 정보(loginMember) 저장
					// 구독권 가져오는 부분 시작
					String lastDate = pService.seasonTicketDate(loginMember.getMemberId());			
					session.setAttribute("lastDate", lastDate);
					//구독권 가져오는 부분 종료
					// 로그인한 회원이 저장한 도서 수 가져오기
					int savedBooks = mService.countSavedBooks(loginMember.getMemberId());
					session.setAttribute("savedBooks", savedBooks);
					// 로그인한 회원이 등록한 작품 수 가져오기
					int writtenBooks = mService.countWrittenBooks(loginMember.getMemberId());
					session.setAttribute("writtenBooks", writtenBooks);
					// 알림 개수 가져오기
					int countNews = nService.countNews(loginMember.getMemberId());
					session.setAttribute("countNews", countNews);
					mv.setViewName("redirect:/main"); // 로그인 성공 시 로그인 후 메인 페이지로 이동
				}
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage"); // 에러 확인용
		}
		return mv;
	}
	
	// 회원 정보 삭제
	@RequestMapping(value="/member/deleteMember.pb", method=RequestMethod.GET)
	public String deleteMemberInfo(@RequestParam("memberId") String memberId) {
		int result = mService.deleteMemberInfo(memberId);
		if(result > 0) {
			return "redirect:/";
		}
		return null;
	}
	
	// 로그인 되는지 검사
	@ResponseBody
	@RequestMapping(value="/member/loginCheck.pb", produces="application/json;charset=utf-8", method=RequestMethod.POST)
	public String loginCheck(
			@RequestParam("memberId") String memberId,
			@RequestParam("memberPw") String memberPw) {
		// 비밀번호 암호화 시작
		System.out.println("로그인-암호화 전 비밀번호: " + memberPw);
		String encryptedPw = Sha256.encrypt(memberPw); // 회원가입 jsp에서 입력된 비밀번호를 암호화해서 encryptedPw에 넣기
		memberPw = encryptedPw; // 암호화된 비밀번호를 멤버 객체의 비밀번호로 넣음
		System.out.println("로그인-암호화 후 비밀번호: " + memberPw);
		// 비밀번호 암호화 끝
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memberId", memberId);
		paramMap.put("memberPw", memberPw);
		int result = mService.loginCheck(paramMap);
		return String.valueOf(result);
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
	@RequestMapping(value="/member/resetPwView.pb", method=RequestMethod.GET)
	public String resetPwView() {
		return "member/resetPw";
	}
	
	// 비밀번호 재설정 시 필요한 데이터 넘기는 용
	@RequestMapping(value="/member/sendMemberId", method=RequestMethod.POST)
	public String sendMemberId(@RequestParam("memberId") String memberId, Model model) {
		model.addAttribute("memberId", memberId);
		return "member/resetPw";
	}
	
	// 비밀번호 재설정(수정) 기능
	@RequestMapping(value="/member/resetPw.pb", method=RequestMethod.POST)
	public ModelAndView resetPw(
			@RequestParam("memberId") String memberId, 
			@RequestParam("memberPw") String memberPw,
			ModelAndView mv) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memberId", memberId);
		paramMap.put("memberPw", memberPw);
		int result = mService.resetMemberPw(paramMap);
		if(result > 0) {
			mv.setViewName("redirect:/member/loginView.pb");
		}
		return mv;
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
	
	// 정보 수정-비밀번호 확인-기존 비밀번호와 비교 전 입력 값 암호화
	@ResponseBody
	@RequestMapping(value="/member/encryptPw.pb", method=RequestMethod.GET)
	public String encryptPw(@RequestParam("inputPw") String inputPw) {
		System.out.println("정보 수정-암호화 전 비밀번호: " + inputPw);
		String encryptedPw = Sha256.encrypt(inputPw); // 회원가입 jsp에서 입력된 비밀번호를 암호화해서 encryPw에 넣기
		System.out.println("정보 수정-암호화 후 비밀번호: " + encryptedPw);
		return encryptedPw;
	}
	
	/**
	 * 정보 관리 - 회원 정보 수정 화면
	 * @return
	 */
	@RequestMapping(value="/member/modifyView.pb", method=RequestMethod.GET)
	public String modifyView() {
		return "member/modifyInfo";
	}
	
	// 정보 수정 submit 전 데이터 검사용 비밀번호 암호화
	@ResponseBody
	@RequestMapping(value="/member/encryptPws.pb", method=RequestMethod.GET)
	public String encryptPws(
			@RequestParam("memberPwChk") String memberPwChk,
			@RequestParam("newPw") String newPw) {
		// 입력된 기존비번확인 암호화
		System.out.println("기존비번확인-암호화 전 비밀번호: " + memberPwChk);
		String e_memberPwChk = Sha256.encrypt(memberPwChk);
		System.out.println("기존비번확인-암호화 후 비밀번호: " + e_memberPwChk);
		// 입력된 새비번 암호화
		System.out.println("새비번-암호화 전 비밀번호: " + newPw);
		String e_newPw = Sha256.encrypt(newPw);
		System.out.println("새비번-암호화 후 비밀번호: " + e_newPw);
		
		ArrayList<String> pwDataList = new ArrayList<String>();
		pwDataList.add(e_memberPwChk);
		pwDataList.add(e_newPw);
		
		return pwDataList.toString();
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
			ModelAndView mv,
			@RequestParam("originPw") String originPw,
			HttpServletRequest request) {
		try {
			String memberPw = member.getMemberPw();
			if(memberPw == "") { // jsp에서 전달 받은 비밀번호 값이 null일 경우(별명만 수정하거나 아무것도 수정하지 않는 경우)
				member.setMemberPw(originPw); // 로그인 정보에서 가져온 기존 비밀번호 값을 넣어줌(안 넣으면 null 값이 그대로 업데이트 되므로)
			}
			int result = mService.modifyInfo(member);
			if(result > 0) { // 회원 정보 수정 성공
				mv.setViewName("redirect:/member/memberInfo.pb");
				Member loginMember = mService.loginMember(member);
				HttpSession session = request.getSession();
				session.setAttribute("loginMember", loginMember);
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
