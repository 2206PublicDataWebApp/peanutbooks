package com.books.peanut.consult.service;

import java.util.Date;
import java.util.List;

import com.books.peanut.consult.domain.Consult;
import com.books.peanut.consult.domain.ConsultServer;
import com.books.peanut.pay.domain.Pagemarker;

public interface ConsultService {
	//관리자 리스트 전체 조회
		public List<ConsultServer> printAllChat();


//////////////////////////////////////////////////		
		
	//고객채팅접수
		public int receiptChat(ConsultServer conServer);
		// 채팅 상답접수 후에 번호 얻어오기 	
		public int serchTitleNo(ConsultServer conServer);
		
	//고객 채팅DB저장
		public int inputChat(Consult consult);
	//고객 채팅내역 화면 전달받기	
		public List<Consult> nowChatList(int titleNo);
	//관리자 채팅종료
		public int chatFinish(ConsultServer conServer);
		//on/off으로 변경
		public int changebutten(String on_off);
		//버튼값 받기
		public String selechbtn();

		//채팅상담종료건 조회
		public List<ConsultServer> printEndListChat(Pagemarker pm, String csMemberId, String csDate);

		//채팅상담종료건 조회 전체 카운트
//		public int getTotalCount(ConsultServer cs);
		public int getTotalCount(String csMemberId, String csDate);
		//id,titleNo 상담내용  확인
		public List<Consult> chatDetailList(Pagemarker pm,Consult consult);
		//id,titleNo 상담내용  전체 카운트
		public int getConsultCount(Consult consult);

		




		
		

}
