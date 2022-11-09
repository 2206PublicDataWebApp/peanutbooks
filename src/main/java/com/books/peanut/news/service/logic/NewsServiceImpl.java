package com.books.peanut.news.service.logic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.peanut.book.domain.Library;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.news.domain.News;
import com.books.peanut.news.service.NewsService;
import com.books.peanut.news.store.NewsStore;

@Service
public class NewsServiceImpl implements NewsService {
   @Autowired
   private SqlSessionTemplate session;
   @Autowired
   private NewsStore nStore;
   
   // 알림 목록 조회
   @Override
   public List<News> showNewsList(String memberId) {
      List<News> nList = nStore.selectNewsList(session, memberId);
      return nList;
   }
   // 오리지널 도서 승인 시 사용된 도서 번호로 해당 도서가 담긴 서재의 회원 아이디 값 가져오기
   @Override
   public List<Library> selectMemberIdByBookNo(Integer bookNo) {
      List<Library> lList = nStore.selectMemberIdByBookNo(session, bookNo);
      return lList;
   }
   // 오리지널 도서 승인 시 사용된 도서 번호로 알림 내용에 쓰일 도서 제목 가져오기
   @Override
   public OriginBook selectBookTitleByNo(Integer bookNo) {
      OriginBook bookTitle = nStore.selectBookTitleByNo(session, bookNo);
      return bookTitle;
   }
   // 알림 등록
   @Override
   public void insertNews(HashMap<String, Object> paramMap) {
      nStore.insertNews(session, paramMap);
   }
   // 알림 삭제
	@Override
	public int deleteNewsByNo(int newsNo) {
		int result = nStore.deleteNews(session, newsNo);
		return result;
	}

}