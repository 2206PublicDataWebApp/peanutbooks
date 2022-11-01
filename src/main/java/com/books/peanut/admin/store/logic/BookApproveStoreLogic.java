package com.books.peanut.admin.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.books.peanut.admin.domain.ModifyBookSeries;
import com.books.peanut.admin.store.BookApproveStore;
import com.books.peanut.book.domain.OriginBook;
import com.books.peanut.book.domain.OriginBookSeries;
import com.books.peanut.member.domain.Member;
@Repository
public class BookApproveStoreLogic implements BookApproveStore{


	@Override
	public int selectAllOriSeries(SqlSession session) {
		int count = session.selectOne("BookApproveMapper.selectAllOriSeries");
		return count;
	}

	@Override
	public List<OriginBookSeries> selectAllOriSeries(SqlSession session, int i, int limit) {
		int offset = (i-1)*limit;
		RowBounds rowBounds= new RowBounds(offset,limit);
		
		List<OriginBookSeries> osList = session.selectList("BookApproveMapper.selectAllMemberSeriese", null, rowBounds);
		return osList;
	}
	
	@Override
	public int selectCheckPermissionCount(SqlSession session, String checkPermission) {
		int count = session.selectOne("BookApproveMapper.selectCheckPermission", checkPermission);
		return count;
	}

	@Override
	public List<OriginBookSeries> selectCheckPermissionList(SqlSession session, int i, int limit,
			String checkPermission) {
		int offset = (i-1)*limit;
		RowBounds rowBounds= new RowBounds(offset,limit);
//		HashMap<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("checkPermission", checkPermission);
		List<OriginBookSeries> osList = session.selectList("BookApproveMapper.selectCheckPermissionList",checkPermission, rowBounds);
		return osList;
	}
	
	@Override
	public int selectAllReAppCount(SqlSession session) {
		int count = session.selectOne("BookApproveMapper.selectAllReAppCount");
		return count;
	}
	
	@Override
	public List<ModifyBookSeries> selectAllReAppList(SqlSession session, int i, int limit) {
		int offset = (i-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<ModifyBookSeries> mbList = session.selectList("BookApproveMapper.selectAllReAppList", null, rowBounds);
		return mbList;
	}
	//책 승인
	@Override
	public int updateApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo) {
		OriginBookSeries os = new OriginBookSeries();
		os.setBookNo(bookNo+"");
		os.setSeriesNo(seriesNo);
		
		int result = session.update("BookApproveMapper.updateApproveBooks", os);
		result += session.update("BookApproveMapper.updateOriBookApprove",os);
		result += session.update("BookApproveMapper.updateOriBookSeiresApprove",os);
		return result;
	}
	//책 재승인
	@Override
	public int updateReApproveBooks(SqlSession session, Integer bookNo, Integer seriesNo) {
		OriginBookSeries os = new OriginBookSeries();
		os.setBookNo(bookNo+"");
		os.setSeriesNo(seriesNo);
		
		int result = session.update("BookApproveMapper.updateREAppInPermission", os);
		result += session.update("BookApproveMapper.updateReAppUpSeries",os);
		result += session.update("BookApproveMapper.updateReAppDelModify",os);
		return result;
	}
	
	@Override
	public int selectAllMember(SqlSession session) {
		int count = session.selectOne("BookApproveMapper.selectAllMemberCount");
		return count;
	}

	@Override
	public List<Member> selectAllMembers(SqlSession session, int i, int memberLimit) {
		int offset = (i-1)*memberLimit;
		RowBounds rowBounds= new RowBounds(offset,memberLimit);
		
		List<Member> mList = session.selectList("BookApproveMapper.selectAllMembers", null, rowBounds);
		return mList;
	}

	@Override
	public Member selectOneById(SqlSession session, String memberId) {
		Member member = session.selectOne("BookApproveMapper.selectOneById", memberId);
		return member;
	}

	@Override
	public int deleteOneById(SqlSession session, String memberId) {
		int result = session.delete("BookApproveMapper.deleteOneById", memberId);
		return result;
	}

	



	

	

}
