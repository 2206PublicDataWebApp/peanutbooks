package com.books.peanut.book.store.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.NormalBook;
import com.books.peanut.book.domain.PeanutCess;
import com.books.peanut.book.store.PeanutCessStore;

@Repository
public class PeanutCessStoreLogic implements PeanutCessStore{

	/**게임한적있는지 체크*/
	@Override
	public int selectMemberCheck(SqlSessionTemplate session, String memberId) {
		int result = session.selectOne("peanutcessMapper.selectOneMemberCount",memberId);
		return result;
	}

	/**닉네임 가져오기*/
	@Override
	public String selectMemberNickName(SqlSessionTemplate session, String memberId) {
		String nickName = session.selectOne("peanutcessMapper.selectMemberNickName",memberId);
		return nickName;
	}

	/**게임 데이터 지우기*/
	@Override
	public int deleteMemberGameDate(SqlSessionTemplate session, String memberId) {
		int result = session.delete("peanutcessMapper.deleteMemberGameDate",memberId);
		return result;
	}

	/**내 서재 최근도서 3개 가져오기*/
	@Override
	public List<NormalBook> selectBookMark(SqlSessionTemplate session, String memberId) {
		List<NormalBook> bList = session.selectList("adminWirteMapper.selectBookMark",memberId);
		return bList;
	}

	/**이름 생일 첫 저장*/
	@Override
	public int insertGameDate(PeanutCess pc, SqlSessionTemplate session) {
		int result = session.insert("peanutcessMapper.insertGameDate",pc);
		return result;
	}

	/**세이브 데이터 가지고 오기*/
	@Override
	public PeanutCess selectOneMemberDate(String memberId, SqlSessionTemplate session) {
		PeanutCess pCess =session.selectOne("peanutcessMapper.selectOneMemberDate",memberId);
		return pCess;
	}

	/**스테이터스 저장*/
	@Override
	public int updateBookStatus(PeanutCess pCess,SqlSessionTemplate session) {
		int result = session.update("peanutcessMapper.updateStatus", pCess);
		return result;
	}

}
