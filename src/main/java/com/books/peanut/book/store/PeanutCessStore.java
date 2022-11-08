package com.books.peanut.book.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.PeanutCess;

public interface PeanutCessStore {
	
	/**게임한적있는지 체크*/
	int selectMemberCheck(SqlSessionTemplate session, String memberId);

	/**멤버 닉네임 가져오기*/
	String selectMemberNickName(SqlSessionTemplate session, String memberId);

	/**게임 삭제하기*/
	int deleteMemberGameDate(SqlSessionTemplate session, String memberId);

	/**내 서재 최근도서 3개 가져오기*/
	List<NormalBook> selectBookMark(SqlSessionTemplate session, String memberId);

	/**이름, 생일 데이터 첫 세이브
	 * @param session */
	int insertGameDate(PeanutCess pc, SqlSessionTemplate session);

	/**세이브 데이터 가지고 오기
	 * @param session */
	PeanutCess selectOneMemberDate(String memberId, SqlSessionTemplate session);

	/**스테이터스 저장*/
	int updateBookStatus(PeanutCess pCess,SqlSessionTemplate session);

	/**모든 스테이터스 저장*/
	int updateStatusAll(PeanutCess pCess, SqlSessionTemplate session);

	/**여왕엔딩 저장여부 확인*/
	int countQeenEnding(String memberId, SqlSessionTemplate session);

	/**엔딩저장하고 땅콩주기*/
	int UpdateaddEnding(String memberId, String ending, SqlSessionTemplate session);

}
