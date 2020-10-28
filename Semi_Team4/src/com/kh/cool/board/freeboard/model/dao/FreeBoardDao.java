package com.kh.cool.board.freeboard.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.board.freeboard.model.vo.FreeBoard;
import com.kh.cool.board.freeboard.model.vo.PageInfo;

import static com.kh.cool.common.JDBCTemplate.*;

public class FreeBoardDao {

	Properties prop = new Properties();
	
	public FreeBoardDao() {
		
		String fileName = FreeBoardDao.class.getResource("/sql/board/freeboard/freeboard-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<FreeBoard> selectList(Connection con, int community, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeBoard> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1)  * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, community);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<FreeBoard>();
			
			while(rset.next()) {
				FreeBoard b = new FreeBoard();
				
				b.setBoardNo(rset.getInt("BOARD_NO"));
				b.setBoardNum(rset.getInt("BOARD_NUM"));
				b.setBoardTitle(rset.getString("BOARD_TITLE"));
				b.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				b.setCommunityName(rset.getString("COMMUNITY_NAME"));
				b.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				b.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				b.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				b.setBoardCount(rset.getInt("BOARD_COUNT"));
				b.setBoardProcess(rset.getString("BOARD_PROCESS"));
				b.setBoardContent(rset.getString("BOARD_CONTENT"));
				b.setStatus(rset.getString("STATUS"));
				
				list.add(b);
				
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

	public int insertFreeBoard(Connection con, FreeBoard newFreeBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newFreeBoard.getBoardTitle());
			pstmt.setInt(2, newFreeBoard.getCommunityCode());
			pstmt.setString(3, newFreeBoard.getCommunityName());
			pstmt.setString(4, newFreeBoard.getBoardWriterId());
			pstmt.setString(5, newFreeBoard.getBoardContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public FreeBoard selectListOne(Connection con, int num) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FreeBoard board = null;
		
		String query = prop.getProperty("selectListOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new FreeBoard();
				
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				board.setCommunityName(rset.getString("COMMUNITY_NAME"));
				board.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				board.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				board.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				board.setBoardCount(rset.getInt("BOARD_COUNT"));
				board.setBoardProcess(rset.getString("BOARD_PROCESS"));
				board.setBoardContent(rset.getString("BOARD_CONTENT"));
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardNum(rset.getInt("BOARD_NUM"));
				board.setStatus(rset.getString("STATUS"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return board;
	}

	public int updateCount(Connection con, int num) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.setInt(2, num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<FreeBoard> FreeBoardSearch(Connection con, String radio, String select, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<FreeBoard> list = null;
		String query = "";
		
		if(radio.equals("news")) {
			
			if(select.equals("title")) {
				query = prop.getProperty("searchList1");
			} else if(select.equals("writer")) {
				query = prop.getProperty("searchList2");
			} else if(select.equals("content")) {
				 query = prop.getProperty("searchList3");
			}
			
		} else if (radio.equals("count")) {
			
			if(select.equals("title")) {
				query = prop.getProperty("searchList4");
			} else if(select.equals("writer")) {
				query = prop.getProperty("searchList5");
			} else if(select.equals("content")) {
				query = prop.getProperty("searchList6");
			}
			
		} else {
			query = prop.getProperty("searchAll");
		}
		
		list = new ArrayList<FreeBoard>();
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+search+"%");
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				FreeBoard board = new FreeBoard();
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				board.setCommunityName(rset.getString("COMMUNITY_NAME"));
				board.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				board.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				board.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				board.setBoardCount(rset.getInt("BOARD_COUNT"));
				board.setBoardProcess(rset.getString("BOARD_PROCESS"));
				board.setBoardContent(rset.getString("BOARD_CONTENT"));
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardNum(rset.getInt("BOARD_NUM"));
				board.setStatus(rset.getString("STATUS"));
				
				list.add(board);
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

	public int boardDelete(Connection con, int num) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public int updateBoard(Connection con, int num, FreeBoard updateBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, updateBoard.getBoardTitle());
			pstmt.setString(2, updateBoard.getBoardContent());
			pstmt.setInt(3, num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public FreeBoard changeBoard(Connection con, int num, FreeBoard updateBoard) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FreeBoard board = null;
		
		String query = prop.getProperty("changeBoard");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new FreeBoard();
				
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				board.setCommunityName(rset.getString("COMMUNITY_NAME"));
				board.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				board.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				board.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				board.setBoardCount(rset.getInt("BOARD_COUNT"));
				board.setBoardProcess(rset.getString("BOARD_PROCESS"));
				board.setBoardContent(rset.getString("BOARD_CONTENT"));
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardNum(rset.getInt("BOARD_NUM"));
				board.setStatus(rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return board;
	}

	public FreeBoard updateOneBoard(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		FreeBoard board = null;
		
		String query = prop.getProperty("selectListOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new FreeBoard();
				
				board.setBoardTitle(rset.getString("BOARD_TITLE"));
				board.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				board.setCommunityName(rset.getString("COMMUNITY_NAME"));
				board.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				board.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				board.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				board.setBoardCount(rset.getInt("BOARD_COUNT"));
				board.setBoardProcess(rset.getString("BOARD_PROCESS"));
				board.setBoardContent(rset.getString("BOARD_CONTENT"));
				board.setBoardNo(rset.getInt("BOARD_NO"));
				board.setBoardNum(rset.getInt("BOARD_NUM"));
				board.setStatus(rset.getString("STATUS"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return board;
	}

	public int getListCount(Connection con) {
		Statement stmt = null;
		int listCount = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("listCount");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				listCount = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		
		return listCount;
	}

}
