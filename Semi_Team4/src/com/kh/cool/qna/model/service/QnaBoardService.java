package com.kh.cool.qna.model.service;

import static com.kh.cool.common.JDBCTemplate.close;
import static com.kh.cool.common.JDBCTemplate.commit;
import static com.kh.cool.common.JDBCTemplate.getConnection;
import static com.kh.cool.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kh.cool.qna.model.dao.QnaBoardDao;
import com.kh.cool.qna.model.vo.Attachment;
import com.kh.cool.qna.model.vo.PageInfo;
import com.kh.cool.qna.model.vo.QnaBoard;

public class QnaBoardService {

	public int getListCount() {
		Connection con = getConnection();
		
		int listCount = new QnaBoardDao().getListCount(con);
		
		close(con);
		
		return listCount;
	}

	public ArrayList<QnaBoard> selectListWithPaging(PageInfo pi) {
		Connection con = getConnection();
		
		ArrayList<QnaBoard> list = new QnaBoardDao().selectListWithPaging(con, pi);
		
		close(con);
		
		return list;
	}

	public int insertQna(Map<String, Object> requestData) {
		Connection con = getConnection();
		int result = 0;
		
		QnaBoardDao qbd = new QnaBoardDao();
		QnaBoard qnaBoard = (QnaBoard) requestData.get("qnaBoard");
		ArrayList<Attachment> fileList = (ArrayList<Attachment>) requestData.get("fileList");
		
		int result1 = qbd.insertQnaContent(con, qnaBoard);
		
		if(result1 > 0) {
			int bNo = qbd.selectCurrval(con);
			
			int result2 = 0;
			
			for(int i=0; i<fileList.size(); i++) {
				Attachment att = fileList.get(i);
				att.setBoardNo(bNo);
				result2 += qbd.insertAttachment(con, att);
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
		
		return result;
	}

	public HashMap<String, Object> selectQnaBoardOne(int bNo) {
		Connection con = getConnection();
		HashMap<String, Object> hmap = null;
		QnaBoardDao qbd = new QnaBoardDao();
		
		int result = qbd.updateCount(con, bNo);
		
		if(result > 0) {
			hmap = qbd.selectQnaBoardOne(con, bNo);
			
			if(hmap != null) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return hmap;
	}

	public int deleteOne(int bNo) {
		Connection con = getConnection();
		
		int result = new QnaBoardDao().deleteOne(con, bNo);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int modifyOne(QnaBoard qnaBoard) {
		Connection con = getConnection();
		
		int result = new QnaBoardDao().modifyOne(con, qnaBoard);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<QnaBoard> searchAndSort(String searchCondition, String sortCondition, String searchValue) {
		Connection con = getConnection();
		ArrayList<QnaBoard>	list = null;
		
		QnaBoardDao qbd = new QnaBoardDao();
		
		switch(searchCondition) {
		case "title":
			list = qbd.searchQnaBoardWithTitle(con, searchValue);
			break;
		case "writer":
			list = qbd.searchQnaBoardWithWriter(con, searchValue);
			break;
		case "content":
			list = qbd.searchQnaBoardWithContent(con, searchValue);
			break;
		default:
			list = qbd.searchQnaBoardAll(con);
		}
		
		if(list != null) {
			switch(sortCondition) {
			case "lastest":
				list = qbd.sortQnaBoardListLastest(list);
				break;
			case "popular":
				list = qbd.sortQnaBoardListPopular(list);
				break;
			}
		}
		
		close(con);
		
		return list;
	}

	public ArrayList<QnaBoard> selectSearchedListWithPaging(ArrayList<QnaBoard> getList, PageInfo pi) {
		ArrayList<QnaBoard> newList = new QnaBoardDao().selectSearchedListWithPaging(getList, pi);
		
		return newList;
	}

	public Attachment selectOneAttachment(int num) {
		Connection con = getConnection();
		
		Attachment file = new QnaBoardDao().selectOneAttachment(con, num);
		
		close(con);
		
		return file;
	}


}
