package com.kh.cool.qna.model.dao;

import static com.kh.cool.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.qna.model.vo.Comment;

public class CommentDao {
	private Properties prop = new Properties();
	
	public CommentDao() {
		String fileName = CommentDao.class.getResource("/sql/board/qna/qnaComment-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertComment(Connection con, Comment comment) {
		PreparedStatement pstmt = null;
		int result = 0;
		ArrayList<Comment> commentList = null;
		
		String query = prop.getProperty("insertComment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, comment.getCommunityCode());
			pstmt.setString(2, comment.getCommentContent());
			pstmt.setInt(3, comment.getBoardNo());
			pstmt.setString(4, comment.getCommentWriterId());
			
			result = pstmt.executeUpdate(); 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Comment> selectCommentOne(Connection con, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Comment>	commentList = null;
		String query = prop.getProperty("selectCommentOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			commentList = new ArrayList<Comment>();
			while(rset.next()) {
				Comment com = new Comment();
				
				com.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				com.setCommentContent(rset.getString("COMMENT_CONTENT"));
				com.setCommentWriteDate(rset.getDate("COMMENT_WRITE_DATE"));
				com.setBoardNo(rset.getInt("BOARD_NO"));
				com.setCommentNo(rset.getInt("COMMENT_NO"));
				com.setCommentWriterId(rset.getString("COMMENT_WRITER_ID"));

				commentList.add(com);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return commentList;
	}

	public ArrayList<Comment> selectCommentAll(Connection con, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Comment> commentList = null;
		
		String query = prop.getProperty("selectCommentAll");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			
			commentList = new ArrayList<Comment>();
			
			while(rset.next()) {
				Comment com = new Comment();
				
				com.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				com.setCommentContent(rset.getString("COMMENT_CONTENT"));
				com.setCommentWriteDate(rset.getDate("COMMENT_WRITE_DATE"));
				com.setBoardNo(rset.getInt("BOARD_NO"));
				com.setCommentNo(rset.getInt("COMMENT_NO"));
				com.setCommentWriterId(rset.getString("COMMENT_WRITER_ID"));

				commentList.add(com);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commentList;
	}

}
