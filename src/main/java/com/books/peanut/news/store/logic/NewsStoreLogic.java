package com.books.peanut.news.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.news.domain.News;
import com.books.peanut.news.store.NewsStore;

@Repository
public class NewsStoreLogic implements NewsStore {
   // 서재에서 도서 번호로 회원 아이디 가져오기
   @Override
   public List<Library> selectMemberIdByBookNo(SqlSession session, Integer bookNo) {
      List<Library> mList = session.selectList("NewsMapper.selectMemberIdByBookNo", bookNo);
      return mList;
   }
   // 알림 목록 조회
   @Override
   public List<News> selectNewsList(SqlSession session, String memberId) {
      List<News> nList = session.selectList("NewsMapper.selectNewsList", memberId);
      return nList;
   }
   // 도서 번호로 도서 제목 가져오기
   @Override
   public OriginBook selectBookTitleByNo(SqlSession session, Integer bookNo) {
      OriginBook bookTitle = session.selectOne("NewsMapper.selectBookTitleByNo", bookNo);
      return bookTitle;
   }
   // 알림 등록
   @Override
   public void insertNews(SqlSession session, HashMap<String, Object> paramMap) {
      List<Library> lList = (List<Library>) paramMap.get("lList");
      int bookNo = (int) paramMap.get("refBookNo");
      String NewsContents = (String) paramMap.get("newsContents");
      
      for(int i=0; i<lList.size(); i++) {
         HashMap<String, Object> hMap = new HashMap<String, Object>();
         hMap.put("memberId", lList.get(i).getMemberId());
         hMap.put("refBookNo", bookNo);
         hMap.put("newsContents", NewsContents);
         session.insert("NewsMapper.insertNews", hMap);
      }
   }
   // 알림 삭제
	@Override
	public int deleteNews(SqlSession session, int newsNo) {
		int result = session.delete("NewsMapper.deleteNews", newsNo);
		return result;
	}
	// 알림 개수 구하기
	@Override
	public int countNews(SqlSession session, String memberId) {
		int countNews = session.selectOne("NewsMapper.countNews", memberId);
		return countNews;
	}
	// 알림 읽음 처리
	@Override
	public int updateNewsStatus(SqlSession session, int newsNo) {
		int result = session.update("NewsMapper.updateNewsStatus", newsNo);
		return result;
	}
	// 출석 테이블에 해당 멤버 있는지 검사
	@Override
	public int checkAttendExist(SqlSession session, String memberId) {
		int result = session.selectOne("NewsMapper.checkAttendExist", memberId);
		return result;
	}
	@Override
	public void insertEventNews(SqlSession session, HashMap<String, String> paramMap) {
		session.insert("NewsMapper.insertEventNews", paramMap);
		
	}

}