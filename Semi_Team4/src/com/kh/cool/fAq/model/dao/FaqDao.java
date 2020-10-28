package com.kh.cool.fAq.model.dao;

import static com.kh.cool.common.JDBCTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.fAq.model.vo.Faq;
import com.kh.cool.fAq.model.vo.PageInfo;

public class FaqDao {
	
	private Properties prop = new Properties();
	
	public FaqDao() {
		String fileName = FaqDao.class.getResource("/sql/board/faq/faq-query.properties").getPath();
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		return listCount;
	}

	public ArrayList<Faq> selectListWithPaging(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = null;
		
		String query = prop.getProperty("faqPaging");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Faq>();
			
			while (rset.next()) {
				Faq f = new Faq();
				
				f.setfId(rset.getInt(""));
				f.setqId(rset.getInt(""));
				f.setMemberId(rset.getString(""));
				f.setMemberName(rset.getString(""));
				f.setFaqTitle(rset.getString(""));
				f.setfContents(rset.getString(""));
				f.setaContents(rset.getString(""));
				f.setFaqCode(rset.getString(""));
				f.setFaqName(rset.getString(""));
				f.setFaqDate(rset.getDate(""));
				f.setSeeNum(rset.getInt(""));
				f.setFaqStatus(rset.getString(""));
	
				list.add(f);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		close(rset);
		close(pstmt);
	}
		return list;
	}

	public ArrayList<Faq> selectList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = null;
		
		String query = prop.getProperty("selectList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Faq>();

			while (rset.next()) {
				Faq f = new Faq();
				
				f.setfId(rset.getInt("BOARD_NUM"));
				//f.setqId(rset.getInt(""));
				f.setMemberId(rset.getString("BOARD_WRITER_ID"));
				//f.setMemberName(rset.getString(""));
				f.setFaqTitle(rset.getString("BOARD_TITLE"));
				f.setfContents(rset.getString("BOARD_CONTENT"));
				//f.setaContents(rset.getString("COMMENT_CONTENT"));
				f.setFaqCode(rset.getString("BOARD_NO"));
				//f.setFaqName(rset.getString(""));
				f.setFaqDate(rset.getDate("BOARD_WRITE_DATE"));
				f.setSeeNum(rset.getInt("BOARD_COUNT"));
				f.setFaqStatus(rset.getString("STATUS"));
			
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
			return list;
	}

	public int updataCount(Connection con, int num) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("countUp");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, num);
			pstmt.setInt(2, num);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public Faq selectOne(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Faq faq = null;
		
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				faq = new Faq();
				
				faq.setFaqTitle(rset.getString("BOARD_TITLE"));
				faq.setMemberId(rset.getString("BOARD_WRITER_ID"));
				faq.setfContents(rset.getString("BOARD_CONTENT"));
				faq.setaContents(rset.getString("COMMENT_CONTENT"));
				faq.setfId(rset.getInt("BOARD_NO"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
			return faq;
	}

	public int insertFaq(Connection con, Faq faq) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFaQ1");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getMemberId());
			pstmt.setString(3, faq.getfContents());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertFaq2(Connection con, Faq faq) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertFaQ2");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, faq.getaContents());
			pstmt.setInt(2, faq.getqId());
			pstmt.setString(3, faq.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectNum(Connection con, Faq faq) {
		Statement stmt = null;
		ResultSet rset = null;
		int num = 0;
		
		String query = prop.getProperty("selectNum");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);

			while(rset.next()) {
				
				num = rset.getInt("NUM");
			}
			
			//System.out.println("selectBno dao"+num);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
			return num;
	}

	public int updateFaq1(Connection con, Faq faq) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateFaQ1");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, faq.getFaqTitle());
			pstmt.setString(2, faq.getfContents());
			pstmt.setString(3, faq.getMemberId());
			pstmt.setInt(4, faq.getfId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateFaq2(Connection con, Faq faq) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateFaQ2");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, faq.getaContents());
			pstmt.setString(2, faq.getMemberId());
			pstmt.setInt(3, faq.getfId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int delFaq(Connection con, int delnum) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("delFaq");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, delnum);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Faq> selectListSee(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = null;
		
		String query = prop.getProperty("selectListSee");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Faq>();

			while (rset.next()) {
				Faq f = new Faq();
				
				f.setfId(rset.getInt("BOARD_NUM"));
				f.setMemberId(rset.getString("BOARD_WRITER_ID"));
				f.setFaqTitle(rset.getString("BOARD_TITLE"));
				f.setfContents(rset.getString("BOARD_CONTENT"));
				f.setFaqCode(rset.getString("BOARD_NO"));
				f.setFaqDate(rset.getDate("BOARD_WRITE_DATE"));
				f.setSeeNum(rset.getInt("BOARD_COUNT"));
				f.setFaqStatus(rset.getString("STATUS"));
			
				list.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
			return list;
	}

	public ArrayList<Faq> searchList(Connection con, Faq faq) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = null;
		
		String query = prop.getProperty("stitle");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, faq.getMemberName());
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while (rset.next()) {
				Faq f = new Faq();
				
				f.setfId(rset.getInt("BOARD_NUM"));
				f.setMemberId(rset.getString("BOARD_WRITER_ID"));
				f.setFaqTitle(rset.getString("BOARD_TITLE"));
				f.setfContents(rset.getString("BOARD_CONTENT"));
				f.setFaqCode(rset.getString("BOARD_NO"));
				f.setFaqDate(rset.getDate("BOARD_WRITE_DATE"));
				f.setSeeNum(rset.getInt("BOARD_COUNT"));
				f.setFaqStatus(rset.getString("STATUS"));
				
				list.add(f);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
			return list;
	}

	public ArrayList<Faq> searchList2(Connection con, Faq faq) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Faq> list = null;
		
		String query = prop.getProperty("scontent");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, faq.getMemberName());
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			
			while (rset.next()) {
				Faq f = new Faq();
				
				f.setfId(rset.getInt("BOARD_NUM"));
				f.setMemberId(rset.getString("BOARD_WRITER_ID"));
				f.setFaqTitle(rset.getString("BOARD_TITLE"));
				f.setfContents(rset.getString("BOARD_CONTENT"));
				f.setFaqCode(rset.getString("BOARD_NO"));
				f.setFaqDate(rset.getDate("BOARD_WRITE_DATE"));
				f.setSeeNum(rset.getInt("BOARD_COUNT"));
				f.setFaqStatus(rset.getString("STATUS"));
				
				list.add(f);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
			return list;
	}
	
	
	
	

}
