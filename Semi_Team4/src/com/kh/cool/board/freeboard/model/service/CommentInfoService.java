package com.kh.cool.board.freeboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.board.freeboard.model.dao.CommentInfoDao;
import com.kh.cool.board.freeboard.model.vo.CommentInfo;
import static com.kh.cool.common.JDBCTemplate.*;

public class CommentInfoService {

	public ArrayList<CommentInfo> insertReply(CommentInfo reply) {
		Connection con = getConnection();
		
		CommentInfoDao cf = new CommentInfoDao();
		
		int result = cf.insertReply(con, reply);
		
		ArrayList<CommentInfo> replyList = null;
		if(result > 0) {
			replyList = cf.selectReplyList(con, reply.getBoardNo());
			
			if(replyList != null) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return replyList;
	}

	public ArrayList<CommentInfo> replySelectAllList(int bno) {
		Connection con = getConnection();
		
		ArrayList<CommentInfo> replyList = new CommentInfoDao().selectReplyList(con, bno);
		
		close(con);
		
		return replyList;
	}

}
