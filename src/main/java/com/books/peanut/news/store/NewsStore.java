package com.books.peanut.news.store;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.news.domain.News;

public interface NewsStore {
   // 서재에서 도서 번호로 회원 아이디 가져오기
   List<Library> selectMemberIdByBookNo(SqlSession session, Integer bookNo);
   // 도서 번호로 도서 제목 가져오기
   OriginBook selectBookTitleByNo(SqlSession session, Integer bookNo);
   // 알림 등록
   void insertNews(SqlSession session, HashMap<String, Object> paramMap);
   // 알림 목록 조회
   List<News> selectNewsList(SqlSession session, String memberId);
   // 알림 삭제
   int deleteNews(SqlSession session, int newsNo);
   // 알림 개수 구하기
   int countNews(SqlSession session, String memberId);
   // 알림 읽음 처리
   int updateNewsStatus(SqlSession session, int newsNo);
   // 출석 테이블에 해당 멤버 있는지 검사
   int checkAttendExist(SqlSession session, String memberId);
   void insertEventNews(SqlSession session, HashMap<String, String> paramMap);
   int checkEventExist(SqlSession session, String memberId);

}