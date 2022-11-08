package com.books.peanut.book.service;

import java.util.List;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.PeanutCess;

public interface PeanutCessService {

	/**게임한적있는지 체크*/
	int CheckGame(String memberId);

	/**닉네임 가져오기*/
	String getMemberName(String memberId);

	/**게임 삭제하기*/
	int removeGame(String memberId);

	/**내 서재 최근도서 3개 가져오기*/
	List<NormalBook> selectBookMarkt(String memberId);

	/**이름, 생일 데이터 저장*/
	int StartGameSave(PeanutCess pc);

	/**세이브 데이터 가지고오기*/
	PeanutCess getOneMemberDate(String memberId);

	/**스테이터스 저장*/
	int firstBookStatus(PeanutCess pCess);

	/**모든 스테이터스 저장*/
	int saveStatusAll(PeanutCess pCess);

	/**여왕엔딩 도달시 저장된 엔딩있는지 확인*/
	int getEnding(String memberId);

	/**엔딩저장하고 땅콩주기*/
	int addEnding(String memberId, String ending);

}
