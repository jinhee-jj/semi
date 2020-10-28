package com.kh.cool.qna.model.service;

import static com.kh.cool.common.JDBCTemplate.close;
import static com.kh.cool.common.JDBCTemplate.commit;
import static com.kh.cool.common.JDBCTemplate.getConnection;
import static com.kh.cool.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.qna.model.dao.CommentDao;
import com.kh.cool.qna.model.vo.Comment;

public class CommentService {

	public ArrayList<Comment> insertCommentOne(Comment comment) {
		Connection con = getConnection();
		CommentDao com = new CommentDao();
		ArrayList<Comment> commentList = null;
		
		int result = com.insertComment(con, comment);
		
		if(result > 0) {
			commentList = new CommentDao().selectCommentOne(con, comment.getBoardNo());

			if(commentList != null) {
				commit(con);
			} else {
				rollback(con);
			}
		} else {
			rollback(con);
		}
		
		close(con);
		
		return commentList;
	}

	public ArrayList<Comment> selectCommentAll(int bNo) {
		Connection con = getConnection();
		ArrayList<Comment> commentList = null;
		
		commentList = new CommentDao().selectCommentAll(con, bNo);
		
		if(commentList != null) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return commentList;
	}

}
