package com.books.peanut.consult.store;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.consult.domain.Consult;
import com.books.peanut.consult.domain.ConsultServer;
import com.books.peanut.pay.domain.Pagemarker;
@Repository
public class LogicStore implements ConsultStore{
	//고객채팅 리스트 받아오기
	@Override
	public List<ConsultServer> printAllChat(SqlSessionTemplate session) {
		List<ConsultServer> chatList=session.selectList("ConsultMapper.chatList");
		return chatList;
	}
////////////////////////////////////////////////////
	
	//고객 채팅 접수
		@Override
		public int reciptChat(SqlSessionTemplate session, ConsultServer conServer) {
			int num=session.insert("ConsultMapper.reseciptChat",conServer);
			return num;
		}

		// 채팅 상답접수 후에 번호 얻어오기
		@Override
		public int serchNoTitle(SqlSessionTemplate session, ConsultServer conServer) {
			int titleNo = session.selectOne("ConsultMapper.serchtitleNo", conServer);
			return titleNo;
		}

		//고객DB 채팅저장
		@Override
		public int inputChat(SqlSessionTemplate session, Consult consult) {
			int num=session.insert("ConsultMapper.inClientChat",consult);			
			return num;
		}
	//실시간 채팅 리스트 가져오기.
	@Override
	public List<Consult> nowList(SqlSessionTemplate session, int titleNo) {
			List<Consult> conList=session.selectList("ConsultMapper.nowSelectChat", titleNo);
		return conList;
	}
	//관리자 채팅종료
	@Override
	public int finishChat(SqlSessionTemplate session, ConsultServer conServer) {
		int num=session.update("ConsultMapper.reportUpdate",conServer);
		return num;
	}
////on/off으로 변경
	@Override
	public int changebutten(SqlSessionTemplate session,String on_off) {	
		int num=session.update("ConsultMapper.switchUpdate",on_off);
				return num;
	}
//버튼값 받기
	@Override
	public String selechbtn(SqlSessionTemplate session) {
		String btnresult=session.selectOne("ConsultMapper.switchbtncheck");
		return btnresult;
	}
	//채팅상담종료건 조회
	@Override
//	public List<ConsultServer> printEndListChat(SqlSessionTemplate session, String csMemberId, String csDate) {
//		int offset=(pm.getCurrentPage()-1)*pm.getLimit();		
//		RowBounds rowBounds = new RowBounds(offset,pm.getLimit());
//		//null, rowBounds 같이 진행해줘야 자동으로 처리된다.
//		HashMap<String, String > paramMap=new HashMap<String, String>();
//		
//		
//		List<ConsultServer> chatList=session.selectList("ConsultMapper.chatEndList",cs,rowBounds);
//		return chatList;
//	}
	public List<ConsultServer> printEndListChat(SqlSessionTemplate session, Pagemarker pm,String csMemberId, String csDate) {
		int offset=(pm.getCurrentPage()-1)*pm.getLimit();		
		RowBounds rowBounds = new RowBounds(offset,pm.getLimit());
		//null, rowBounds 같이 진행해줘야 자동으로 처리된다.
		HashMap<String, String > paramMap=new HashMap<String, String>();
		paramMap.put("csMemberId", csMemberId);
		paramMap.put("csDate", csDate);
		
		List<ConsultServer> chatList=session.selectList("ConsultMapper.chatEndList",paramMap,rowBounds);
		return chatList;
	}
	
	//채팅상담종료건 조회 전체 카운트
	@Override
	public int getTotalCount(SqlSessionTemplate session,String csMemberId, String csDate) {
		HashMap<String, String> hmap=new HashMap<String, String>();
		hmap.put("csMemberId", csMemberId);
		hmap.put("csDate", csDate);
		int count=session.selectOne("ConsultMapper.chatListcount", hmap);
		return count;
	}
	
//	public int getTotalCount(SqlSessionTemplate session, ConsultServer cs) {
//	int count=session.selectOne("ConsultMapper.chatListcount", cs);
//	return count;
//}
	//id,titleNo  상세보기
	@Override
	public List<Consult> chatDetailList(SqlSessionTemplate session,Pagemarker pm, Consult consult) {
		int offset=(pm.getCurrentPage()-1)*pm.getLimit();		
		RowBounds rowBounds = new RowBounds(offset,pm.getLimit());

		List<Consult> cList=session.selectList("ConsultMapper.detailList",consult,rowBounds);
		return cList;
	}
	//id,titleNo 전체 카운트
	@Override
	public int getConsultCount(SqlSessionTemplate session, Consult consult) {
		int num=session.selectOne("ConsultMapper.detailcount", consult);
		return num;
	}	

}
