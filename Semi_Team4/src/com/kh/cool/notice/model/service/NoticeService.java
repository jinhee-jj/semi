package com.kh.cool.notice.model.service;

import static com.kh.cool.common.JDBCTemplate.close;
import static com.kh.cool.common.JDBCTemplate.commit;
import static com.kh.cool.common.JDBCTemplate.getConnection;
import static com.kh.cool.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

import com.kh.cool.notice.model.dao.NoticeDao;
import com.kh.cool.notice.model.vo.Attachment;
import com.kh.cool.notice.model.vo.Notice;
import com.kh.cool.notice.model.vo.PageInfo;

public class NoticeService {

	//공지사항 리스트 조회
	public ArrayList<Notice> selectList() {
		Connection con = getConnection();	
		
		ArrayList<Notice> list = new NoticeDao().selectList(con);
		
		close(con);
		
		return list;
	}

	//공지사항 게시글 하나 선택 조회
	public Notice selectOneNoticeByNno(int nno) {
		Connection con = getConnection();
		Notice notice = null;
		
		//조회수 증가
		int result = new NoticeDao().updateCount(con, nno);
		
		if(result > 0) {
			
			notice = new NoticeDao().selectOneNoticeByNno(con, nno);
			
			if(notice != null) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} else {
			rollback(con);
		}
		
		close(con);	
		
		return notice;
	}

	public int insertNotice(Map<String, Object> requestData) {
		Connection con = getConnection();
		int result = 0;
		
		NoticeDao nd = new NoticeDao();
		Notice notice = (Notice) requestData.get("notice");
		ArrayList<Attachment> fileList = (ArrayList<Attachment>) requestData.get("fileList");
		
		int result1 = nd.insertNoticeContent(con, notice);
		
		//첨부파일 없을 때
		if(fileList.get(0).getOriginName() == null) {
			
			result = result1;
			
			if(result > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} else {
			
			if(result1 > 0) {
				//게시글번호 시퀀스값 조회
				int bno = nd.selectCurrval(con);
				
				int result2 = 0;
				for(int i = 0; i < fileList.size(); i++) {
					Attachment at = fileList.get(i);
					at.setBoardNo(bno);
					result2 += nd.insertAttachment(con, at);
					
				}
				if(result2 == fileList.size()) {
					commit(con);
					result = 1;
				} else {
					rollback(con);
				}
				
			} else {
				rollback(con);
			}
			
		}
		
		
		
		return result;
	}

	public ArrayList<Attachment> checkHasAttachment(int bno) {
		Connection con = getConnection();
		
		ArrayList<Attachment> atList = new NoticeDao().checkHasAttachment(con, bno);
		
		close(con);
		
		return atList;
	}

	public Attachment selectOneAttachment(int num) {
		Connection con = getConnection();
		
		Attachment file = new NoticeDao().selectOneAttachment(con, num);
		
		close(con);
	
		return file;
	}

	public int updateNotice(Notice updateNotice) {
		Connection con = getConnection();
		int result = new NoticeDao().updateNotice(con, updateNotice);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		return result;
	}

	public int getListCount() {
		Connection con = getConnection();
		
		int listCount = new NoticeDao().getListCount(con);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<Notice> selectListWithPaging(PageInfo pi) {
		Connection con = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectListWithPaging(con, pi);
		
		close(con);
		
		
		return list;
	}

	public ArrayList<Notice> selectWithSearchValueTitleLatest(PageInfo pi, String searchValue) {
		Connection con = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectWithSearchValueTitleLatest(con, pi, searchValue);
		System.out.println("service 작동중");
		close(con);
		
		return list;
	}

	public ArrayList<Notice> selectWithSearchValueContentLatest(PageInfo pi, String searchValue) {
		Connection con = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectWithSearchValueContentLatest(con, pi, searchValue);
		
		close(con);
		
		return list;
	}

	public ArrayList<Notice> selectWithSearchValueTitleViewd(PageInfo pi, String searchValue) {
		Connection con = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectWithSearchValueTitleViewd(con, pi, searchValue);
		
		close(con);
		
		return list;
	}

	public java.util.ArrayList<Notice> selectWithSearchValueContentViewd(PageInfo pi, String searchValue) {
		Connection con = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectWithSearchValueContentViewd(con, pi, searchValue);
		
		close(con);
		
		return list;
	}

	public int getListCountWithTitle(String searchValue) {
		Connection con = getConnection();
		
		int listCount = new NoticeDao().getListCountWithTitle(con, searchValue);
		
		close(con);
		
		return listCount;
	}

	public int getListCountWithContent(String searchValue) {
		Connection con = getConnection();
		
		int listCount = new NoticeDao().getListCountWithContent(con, searchValue);
		
		close(con);
		
		return listCount;
	}

	public int deleteNotice(int nno) {
		Connection con = getConnection();
		
		int result = new NoticeDao().deleteNotice(con, nno);
		System.out.println("nno확인 : " + nno);
		close(con);
		
		return result;
	}

	public ArrayList<Notice> selectListWithPagingMostViewed(PageInfo pi) {
		Connection con = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectListWithPagingMostViewed(con, pi);
		
		close(con);
		
		
		return list;
	}


	
	
}
