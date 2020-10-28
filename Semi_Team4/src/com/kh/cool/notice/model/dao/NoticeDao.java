package com.kh.cool.notice.model.dao;

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

import com.kh.cool.notice.model.vo.Attachment;
import com.kh.cool.notice.model.vo.Notice;
import com.kh.cool.notice.model.vo.PageInfo;

public class NoticeDao {
	Properties prop = new Properties();
	
	public NoticeDao() {
		
		String fileName = NoticeDao.class.getResource("/sql/board/notice/notice-query.properties").getPath();
	
		try {
			prop.load(new FileReader(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Notice> selectList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectNoticeList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setNno(rset.getInt("BOARD_NUM"));
				n.setnTitle(rset.getString("BOARD_TITLE"));
				n.setnWriter(rset.getString("BOARD_WRITER_ID"));
				n.setnDate(rset.getDate("BOARD_WRITE_DATE"));
				n.setnCount(rset.getInt("BOARD_COUNT"));
				
				list.add(n);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
	
		return list;
	}

	public Notice selectOneNoticeByNno(Connection con, int nno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		
		String query = prop.getProperty("selectOneNoticeByNno");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nno);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				notice = new Notice();
				notice.setnTitle(rset.getString("BOARD_TITLE"));
				notice.setnWriter(rset.getString("BOARD_WRITER_ID"));
				notice.setnContent(rset.getString("BOARD_CONTENT"));
				notice.setBoardNo(rset.getInt("BOARD_NO"));
				notice.setNno(rset.getInt("BOARD_NUM"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return notice;
	}

	public int updateCount(Connection con, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nno);
			pstmt.setInt(2, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertNoticeContent(Connection con, Notice notice) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, notice.getnTitle());
			pstmt.setString(2, notice.getnWriter());
			pstmt.setString(3, notice.getnContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int selectCurrval(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		
		int bno = 0;
		
		String query = prop.getProperty("selectCurrval");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				bno = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return bno;
	}

	public int insertAttachment(Connection con, Attachment at) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, at.getBoardNo());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Attachment> checkHasAttachment(Connection con, int bno) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Attachment> atList = null;
		
		String query = prop.getProperty("checkHasAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bno);
		
			rset = pstmt.executeQuery();
			atList = new ArrayList<>();
			
			while(rset.next()) {
				Attachment at = new Attachment();
				at.setAttNo(rset.getInt("ATT_NO"));
				at.setBoardNo(rset.getInt("BOARD_NO"));
				at.setOriginName(rset.getString("ATT_FILE_ORIGIN_NAME"));
				at.setChangeName(rset.getString("ATT_FILE_CHANGE_NAME"));
				at.setFilePath(rset.getString("ATT_FILE_ADDRESS"));
				at.setFileSize(rset.getString("ATT_FILE_SIZE"));
				at.setStatus(rset.getString("ATT_STATUS"));
			
				atList.add(at);
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		return atList;
	}

	public Attachment selectOneAttachment(Connection con, int num) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Attachment file = null;
		
		String query = prop.getProperty("selectOneAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				file = new Attachment();
				file.setAttNo(rset.getInt("ATT_NO"));
				file.setBoardNo(rset.getInt("BOARD_NO"));
				file.setOriginName(rset.getString("ATT_FILE_ORIGIN_NAME"));
				file.setChangeName(rset.getString("ATT_FILE_CHANGE_NAME"));
				file.setFilePath(rset.getString("ATT_FILE_ADDRESS"));
				file.setFileSize(rset.getString("ATT_FILE_SIZE"));
				file.setStatus(rset.getString("ATT_STATUS"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return file;

	}

	public int updateNotice(Connection con, Notice updateNotice) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, updateNotice.getnTitle());
			pstmt.setString(2, updateNotice.getnContent());
			pstmt.setInt(3, updateNotice.getNno());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//공지사항 전체 게시물 조회
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

	public ArrayList<Notice> selectListWithPaging(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectListWithPaging");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setnTitle(rset.getString("BOARD_TITLE"));
				n.setnCode(rset.getInt("COMMUNITY_CODE"));
				n.setnName(rset.getString("COMMUNITY_NAME"));
				n.setnWriter(rset.getString("BOARD_WRITER_ID"));
				n.setnDate(rset.getDate("BOARD_WRITE_DATE"));
				n.setnPublic(rset.getString("BOARD_PUBLIC"));
				n.setnCount(rset.getInt("BOARD_COUNT"));
				n.setnContent(rset.getString("BOARD_CONTENT"));
				n.setBoardNo(rset.getInt("BOARD_NO"));
				n.setNno(rset.getInt("BOARD_NUM"));
				n.setStatus(rset.getString("STATUS"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return list;
	}

	public ArrayList<Notice> selectWithSearchValueTitleLatest(Connection con, PageInfo pi, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		System.out.println("dao 작동 중");
		String query = prop.getProperty("selectWithSearchValueTitleLatest");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setnTitle(rset.getString("BOARD_TITLE"));
				n.setnCode(rset.getInt("COMMUNITY_CODE"));
				n.setnName(rset.getString("COMMUNITY_NAME"));
				n.setnWriter(rset.getString("BOARD_WRITER_ID"));
				n.setnDate(rset.getDate("BOARD_WRITE_DATE"));
				n.setnPublic(rset.getString("BOARD_PUBLIC"));
				n.setnCount(rset.getInt("BOARD_COUNT"));
				n.setnContent(rset.getString("BOARD_CONTENT"));
				n.setBoardNo(rset.getInt("BOARD_NO"));
				n.setNno(rset.getInt("BOARD_NUM"));
				n.setStatus(rset.getString("STATUS"));
				
				list.add(n);
			}
			
			System.out.println("list확인 " + list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return list;

	}

	public ArrayList<Notice> selectWithSearchValueContentLatest(Connection con, PageInfo pi, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectWithSearchValueContentLatest");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setnTitle(rset.getString("BOARD_TITLE"));
				n.setnCode(rset.getInt("COMMUNITY_CODE"));
				n.setnName(rset.getString("COMMUNITY_NAME"));
				n.setnWriter(rset.getString("BOARD_WRITER_ID"));
				n.setnDate(rset.getDate("BOARD_WRITE_DATE"));
				n.setnPublic(rset.getString("BOARD_PUBLIC"));
				n.setnCount(rset.getInt("BOARD_COUNT"));
				n.setnContent(rset.getString("BOARD_CONTENT"));
				n.setBoardNo(rset.getInt("BOARD_NO"));
				n.setNno(rset.getInt("BOARD_NUM"));
				n.setStatus(rset.getString("STATUS"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return list;
	}

	public ArrayList<Notice> selectWithSearchValueTitleViewd(Connection con, PageInfo pi, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectWithSearchValueTitleViewd");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setnTitle(rset.getString("BOARD_TITLE"));
				n.setnCode(rset.getInt("COMMUNITY_CODE"));
				n.setnName(rset.getString("COMMUNITY_NAME"));
				n.setnWriter(rset.getString("BOARD_WRITER_ID"));
				n.setnDate(rset.getDate("BOARD_WRITE_DATE"));
				n.setnPublic(rset.getString("BOARD_PUBLIC"));
				n.setnCount(rset.getInt("BOARD_COUNT"));
				n.setnContent(rset.getString("BOARD_CONTENT"));
				n.setBoardNo(rset.getInt("BOARD_NO"));
				n.setNno(rset.getInt("BOARD_NUM"));
				n.setStatus(rset.getString("STATUS"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return list;
	}

	public ArrayList<Notice> selectWithSearchValueContentViewd(Connection con, PageInfo pi, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectWithSearchValueContentViewd");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			pstmt.setString(3, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setnTitle(rset.getString("BOARD_TITLE"));
				n.setnCode(rset.getInt("COMMUNITY_CODE"));
				n.setnName(rset.getString("COMMUNITY_NAME"));
				n.setnWriter(rset.getString("BOARD_WRITER_ID"));
				n.setnDate(rset.getDate("BOARD_WRITE_DATE"));
				n.setnPublic(rset.getString("BOARD_PUBLIC"));
				n.setnCount(rset.getInt("BOARD_COUNT"));
				n.setnContent(rset.getString("BOARD_CONTENT"));
				n.setBoardNo(rset.getInt("BOARD_NO"));
				n.setNno(rset.getInt("BOARD_NUM"));
				n.setStatus(rset.getString("STATUS"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return list;
	}

	public int getListCountWithTitle(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("getListCountWithTitle");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
		if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}	
		return listCount;
	}

	public int getListCountWithContent(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		int listCount = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("getListCountWithContent");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
		if(rset.next()) {
				listCount = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}	
		return listCount;
	}

	public int deleteNotice(Connection con, int nno) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteNotice");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, nno);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
	
		return result;
	}

	public ArrayList<Notice> selectListWithPagingMostViewed(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Notice> list = null;
		
		String query = prop.getProperty("selectListWithPagingMostViewed");
		
		try {
			pstmt = con.prepareStatement(query);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
			int endRow = startRow + pi.getLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice n = new Notice();
				
				n.setnTitle(rset.getString("BOARD_TITLE"));
				n.setnCode(rset.getInt("COMMUNITY_CODE"));
				n.setnName(rset.getString("COMMUNITY_NAME"));
				n.setnWriter(rset.getString("BOARD_WRITER_ID"));
				n.setnDate(rset.getDate("BOARD_WRITE_DATE"));
				n.setnPublic(rset.getString("BOARD_PUBLIC"));
				n.setnCount(rset.getInt("BOARD_COUNT"));
				n.setnContent(rset.getString("BOARD_CONTENT"));
				n.setBoardNo(rset.getInt("BOARD_NO"));
				n.setNno(rset.getInt("BOARD_NUM"));
				n.setStatus(rset.getString("STATUS"));
				
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}

		return list;
	}




}
