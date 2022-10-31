package com.books.peanut.consult.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.consult.domain.Consult;
import com.books.peanut.consult.domain.ConsultServer;
import com.books.peanut.pay.domain.Pagemarker;

public interface ConsultStore {

	// 관리자 지금까지의 전체 리스트 조회
	public List<ConsultServer> printAllChat(SqlSessionTemplate session);

//////////////////////////////////////////////////////////////////////////////	
	
	//고객 채팅접수
	public int reciptChat(SqlSessionTemplate session, ConsultServer conServer);
	// 채팅 상답접수 후에 번호 얻어오기 	
	public int serchNoTitle(SqlSessionTemplate session, ConsultServer conServer);
	//고객채팅 DB저장
	public int inputChat(SqlSessionTemplate session, Consult consult);
	//고객 채팅내역 화면 전달받기
	public List<Consult> nowList(SqlSessionTemplate session, int titleNo);


	public int finishChat(SqlSessionTemplate session, ConsultServer conServer);
	//on/off으로 변경
	public int changebutten(SqlSessionTemplate session, String chatbtn);

	public String selechbtn(SqlSessionTemplate session);
	//채팅상담종료건 조회
	public List<ConsultServer> printEndListChat(SqlSessionTemplate session, Pagemarker pm, ConsultServer cs);
	//채팅상담종료건 조회 전체 카운트
	public int getTotalCount(SqlSessionTemplate session, ConsultServer cs);
	//종료채팅 id별로  상세보기
	public List<Consult> printDetail(SqlSessionTemplate session,String memberId);




}
