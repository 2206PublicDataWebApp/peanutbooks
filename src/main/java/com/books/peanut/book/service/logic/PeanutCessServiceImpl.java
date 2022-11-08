package com.books.peanut.book.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.PeanutCess;
import com.books.peanut.book.service.PeanutCessService;
import com.books.peanut.book.store.PeanutCessStore;

@Service
public class PeanutCessServiceImpl implements PeanutCessService {

	@Autowired
	PeanutCessStore pStore;
	
	@Autowired
	SqlSessionTemplate session;
	
	/**
	 * 게임한적있는지 체크하기
	 */
	@Override
	public int CheckGame(String memberId) {
		int result = pStore.selectMemberCheck(session,memberId);
		return result;
	}

	/**닉네임가져오기*/
	@Override
	public String getMemberName(String memberId) {
		String nickName = pStore.selectMemberNickName(session,memberId);
		return nickName;
	}

	/**게임 삭제하기*/
	@Override
	public int removeGame(String memberId) {
		int result = pStore.deleteMemberGameDate(session, memberId); 
		return result;
	}

	/**내 서재 최근도서 3개 가져오기*/
	@Override
	public List<NormalBook> selectBookMarkt(String memberId) {
		List<NormalBook> bList = pStore.selectBookMark(session, memberId);
		return bList;
	}

	/**이름 생일 데이터 저장*/
	@Override
	public int StartGameSave(PeanutCess pc) {
		int result = pStore.insertGameDate(pc,session);
		return result;
	}

	/**세이브 데이터 가지고오기*/
	@Override
	public PeanutCess getOneMemberDate(String memberId) {
		PeanutCess pCess = pStore.selectOneMemberDate(memberId,session);
		return pCess;
	}

	/**스테이터스 저장*/
	@Override
	public int firstBookStatus(PeanutCess pCess) {
		int result =  pStore.updateBookStatus(pCess,session);
		return result;
	}

	/**모든 스테이터스 저장*/
	@Override
	public int saveStatusAll(PeanutCess pCess) {
		int result =  pStore.updateStatusAll(pCess,session);
		return result;
	}

	/**여왕엔딩 저장 여부 확인*/
	@Override
	public int getEnding(String memberId) {
		int result = pStore.countQeenEnding(memberId,session);
		return result;
	}

	/**엔딩저장하고 땅콩주기*/
	@Override
	public int addEnding(String memberId, String ending) {
		int result = pStore.UpdateaddEnding(memberId,ending,session);
		return result;
	}

}
