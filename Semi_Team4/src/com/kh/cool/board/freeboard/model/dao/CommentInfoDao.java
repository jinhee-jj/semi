package com.kh.cool.board.freeboard.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.board.freeboard.model.vo.CommentInfo;
import static com.kh.cool.common.JDBCTemplate.*;

public class CommentInfoDao {
	private Properties prop = new Properties();
	
	public CommentInfoDao() {
		String fileName = CommentInfoDao.class.getResource("/sql/board/freeboard/comment-query.properties").getPath();
	
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insertReply(Connection con, CommentInfo reply) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertReply");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, reply.getCommentContent());
			pstmt.setInt(2, reply.getBoardNo());
			pstmt.setString(3, reply.getCommentWriterId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList<CommentInfo> selectReplyList(Connection con, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<CommentInfo> list = null;
		
		String query = prop.getProperty("selectReplyList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<CommentInfo>();
			while(rset.next()) {
				CommentInfo c = new CommentInfo();
				
				c.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				c.setCommentContent(rset.getString("COMMENT_CONTENT"));
				c.setCommentWriteDate(rset.getDate("COMMENT_WRITE_DATE"));
				c.setBoardNo(rset.getInt("BOARD_NO"));
				c.setCommentNo(rset.getInt("COMMENT_NO"));
				c.setCommentWriterId(rset.getString("COMMENT_WRITER_ID"));
				
				list.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

}
