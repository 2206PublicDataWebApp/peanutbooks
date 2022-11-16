package com.books.peanut.news.service;
import java.util.HashMap;
import java.util.List;

import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.news.domain.News;

public interface NewsService {
   // 서재에서 도서 번호로 회원 아이디 가져오기
   List<Library> selectMemberIdByBookNo(Integer bookNo);
   // 도서 번호로 도서 제목 가져오기
   OriginBook selectBookTitleByNo(Integer bookNo);
   // 알림 등록
   void insertNews(HashMap<String, Object> paramMap);
   // 알림 목록 조회
   List<News> showNewsList(String memberId);
   // 알림 삭제
   int deleteNewsByNo(int newsNo);
   // 알림 개수 구하기
   int countNews(String memberId);
   // 알림 읽음 처리
   int readNews(int newsNo);
   // 출석 테이블에 해당 멤버 있는지 검사
   int checkAttendExist(String memberId);
   void insertEventNews(HashMap<String, String> paramMap);
   int checkEventExist(String memberId);

}