package com.books.peanut.book.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.books.peanut.book.domain.Attendance;
import com.books.peanut.book.service.ReplyService;
import com.books.peanut.member.domain.Member;

@Controller
public class EventController {

	@Autowired
	ReplyService rService;

	@RequestMapping(value = "/book/attendaceEvent.do")
	public ModelAndView attendEventView(ModelAndView mv, HttpSession session) {

		Member member = (Member) session.getAttribute("loginMember");
		String memberId = member.getMemberId();
		if (session.getAttribute("loginMember") != null) {// 로그인 했을때
			int result = rService.checkTodayAttend(memberId); // 오늘 이벤트 한적이 있는지 확인
			int checkFirstAttned = rService.checkFirst(memberId); // 이벤트를 참여한적 있는지 확인
			int two = rService.checkAttendEvent(memberId, "two"); // 두번째 참여여부확인
			int three = rService.checkAttendEvent(memberId, "three");// 세번째 참여여부확인
			int four = rService.checkAttendEvent(memberId, "four");// 네번째 참여여부확인
			int five = rService.checkAttendEvent(memberId, "five");// 다섯번째 참여여부 확인
			
			if (result == 0 && five ==0) {// 오늘 이벤트를 한적 없다면
				mv.addObject("today", false);
				if (two == 0 && three == 0 && four == 0 && five == 0) {// 두번쨰에 참여안했다면
					int updateTwo = rService.addSecondEvent(memberId);// 두번째참여
					mv.addObject("check", "two");

				} else if (three == 0 && four == 0 && five == 0) {// 세번째참여
					mv.addObject("check", "three");
					int updateThree = rService.addThirdEvent(memberId);

				} else if (four == 0 && five == 0) {// 네번째참여
					mv.addObject("check", "four");
					int updateFour = rService.addFourthEvent(memberId);

				} else {// 다섯번째참여
					mv.addObject("check", "fine");
					int updateFie = rService.addFifthEvent(memberId);
					int updatePeanut = rService.addUpdateEventPeanut(memberId);//5번 완료하면 피넛 5개 줌

				}

			} else {// 이벤트를 오늘 했다면
				mv.addObject("today", true);
				if (two == 0 && three == 0 && four == 0 && five == 0) {// 두번쨰에 참여안했다면
					mv.addObject("check", "one");

				} else if (three == 0 && four == 0 && five == 0) {// 세번째참여
					mv.addObject("check", "two");	
				} else if (four == 0 && five == 0) {// 네번째참여
					mv.addObject("check", "three");

				} else if(five == 0){// 다섯번째참여
					mv.addObject("check", "four");

				}else {
					
					mv.addObject("check", "five");
				}
			}

			
			if (checkFirstAttned == 0) { // 이벤트를 참여한 적이 없다면
				int addFirstAttend = rService.addFirstEvent(memberId);// 첫 이벤트 참여
				mv.addObject("check", "one");
			} else {
			
				

			}

			mv.setViewName("/book/event");
		} else {// 안했을때 홈으로
			mv.setViewName("redirect:/");
		}

		return mv;

	}

}
