package com.kh.cool.qna.model.dao;

import static com.kh.cool.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;

import com.kh.cool.qna.model.vo.Attachment;
import com.kh.cool.qna.model.vo.PageInfo;
import com.kh.cool.qna.model.vo.QnaBoard;

public class QnaBoardDao {
	private Properties prop = new Properties();
	
	public QnaBoardDao() {
		String fileName = QnaBoard.class.getResource("/sql/board/qna/qna-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int listCount = 0;
		
		// 전체 게시글 중 commnunityCode = 4 (qna) 인 게시물의 개수 조회
		String query = prop.getProperty("listCount");

		try {
			stmt = con.createStatement();
		
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				listCount = rset.getInt(1);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return listCount;
	}

	public ArrayList<QnaBoard> selectListWithPaging(Connection con, PageInfo pi) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaBoard> list = null;
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		String query = prop.getProperty("selectListWithPaging");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<QnaBoard>();
			
			while(rset.next()) {
				QnaBoard qnaBoard = new QnaBoard();
				qnaBoard.setBoardTitle(rset.getString("BOARD_TITLE"));
				qnaBoard.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				qnaBoard.setCommunityName(rset.getString("COMMUNITY_NAME"));
				qnaBoard.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				qnaBoard.setMemberName(rset.getString("MEMBER_NAME"));
				
				qnaBoard.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				qnaBoard.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				qnaBoard.setBoardCount(rset.getInt("BOARD_COUNT"));
				qnaBoard.setBoardProcess(rset.getString("BOARD_PROCESS"));
				qnaBoard.setBoardContent(rset.getString("BOARD_CONTENT"));
				
				qnaBoard.setBoardNo(rset.getInt("BOARD_NO"));
				qnaBoard.setBoardNum(rset.getInt("BOARD_NUM"));
				qnaBoard.setStatus(rset.getString("STATUS"));
				
				list.add(qnaBoard);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int insertQnaContent(Connection con, QnaBoard qnaBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertQnaContent");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, qnaBoard.getBoardTitle());
			pstmt.setString(2, "질의응답");
			pstmt.setString(3, qnaBoard.getBoardWriterId());
			pstmt.setString(4, qnaBoard.getBoardContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectCurrval(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int bNo = 0;
		
		String query = prop.getProperty("selectCurrval");
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				bNo = rset.getInt("CURRVAL");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return bNo;
	}

	public int insertAttachment(Connection con, Attachment att) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertAttachment");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, att.getBoardNo());
			pstmt.setString(2, att.getAttFileOriginName());
			pstmt.setString(3, att.getAttFileChangeName());
			pstmt.setString(4, att.getAttFileAddress());
			pstmt.setInt(5, att.getAttFileSize());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateCount(Connection con, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bNo);
			pstmt.setInt(2, bNo);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);	
		}
		
		return result;
	}

	public HashMap<String, Object> selectQnaBoardOne(Connection con, int bNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		HashMap<String, Object> hmap = null;
		
		String query = prop.getProperty("selectQnaBoardOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			rset = pstmt.executeQuery();
			
			hmap = new HashMap<String, Object>();
			
			ArrayList<Attachment> list = new ArrayList<Attachment>();
			
			QnaBoard qnaBoard = new QnaBoard();
			
			while(rset.next()) {
				qnaBoard.setBoardNo(rset.getInt("BOARD_NO"));
				qnaBoard.setBoardNum(rset.getInt("BOARD_NUM"));		//카테고리별 번호
				qnaBoard.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				qnaBoard.setBoardTitle(rset.getString("BOARD_TITLE"));
				qnaBoard.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				qnaBoard.setMemberName(rset.getString("MEMBER_NAME"));
				qnaBoard.setBoardCount(rset.getInt("BOARD_COUNT"));
				qnaBoard.setBoardContent(rset.getString("BOARD_CONTENT"));
				qnaBoard.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				
				Attachment att = new Attachment();
				
				att.setAttNo(rset.getInt("ATT_NO"));
				att.setAttFileOriginName(rset.getString("ATT_FILE_ORIGIN_NAME"));
				att.setAttFileChangeName(rset.getString("ATT_FILE_CHANGE_NAME"));
				att.setAttFileAddress(rset.getString("ATT_FILE_ADDRESS"));
				att.setAttFileSize(rset.getInt("ATT_FILE_SIZE"));				
				
				list.add(att);
				
			}
			
			hmap.put("qnaBoard", qnaBoard);
			hmap.put("attachment", list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return hmap;
	}

	public int deleteOne(Connection con, int bNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		//BOARD_STATUS를 N으로 바꾸어
		//실제 데이터는 존재하지만 데이터값은 보이지 않도록 조정한다.
		String query = prop.getProperty("deleteOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int modifyOne(Connection con, QnaBoard qnaBoard) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("modifyOne");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, qnaBoard.getBoardTitle());
			pstmt.setString(2, qnaBoard.getBoardContent());
			pstmt.setInt(3, qnaBoard.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<QnaBoard> searchQnaBoardWithTitle(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaBoard> list = null;
		
		String query = prop.getProperty("searchQnaBoardWithTitle");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<QnaBoard>();
			
			while(rset.next()) {
				QnaBoard qboard = new QnaBoard();
				qboard.setBoardTitle(rset.getString("BOARD_TITLE"));
				qboard.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				qboard.setCommunityName(rset.getString("COMMUNITY_NAME"));
				qboard.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				qboard.setMemberName(rset.getString("MEMBER_NAME"));
				qboard.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				qboard.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				qboard.setBoardCount(rset.getInt("BOARD_COUNT"));
				qboard.setBoardProcess(rset.getString("BOARD_PROCESS"));
				qboard.setBoardContent(rset.getString("BOARD_CONTENT"));
				qboard.setBoardNo(rset.getInt("BOARD_NO"));
				qboard.setBoardNum(rset.getInt("BOARD_NUM"));
				qboard.setStatus(rset.getString("STATUS"));
				
				list.add(qboard);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<QnaBoard> searchQnaBoardWithWriter(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaBoard> list = null;
		
		String query = prop.getProperty("searchQnaBoardWithWriter");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<QnaBoard>();
			
			while(rset.next()) {
				QnaBoard qboard = new QnaBoard();
				
				qboard.setBoardTitle(rset.getString("BOARD_TITLE"));
				qboard.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				qboard.setCommunityName(rset.getString("COMMUNITY_NAME"));
				qboard.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				qboard.setMemberName(rset.getString("MEMBER_NAME"));
				qboard.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				qboard.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				qboard.setBoardCount(rset.getInt("BOARD_COUNT"));
				qboard.setBoardProcess(rset.getString("BOARD_PROCESS"));
				qboard.setBoardContent(rset.getString("BOARD_CONTENT"));
				qboard.setBoardNo(rset.getInt("BOARD_NO"));
				qboard.setBoardNum(rset.getInt("BOARD_NUM"));
				qboard.setStatus(rset.getString("STATUS"));
				
				list.add(qboard);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public ArrayList<QnaBoard> searchQnaBoardWithContent(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<QnaBoard> list = null;
		
		String query = prop.getProperty("searchQnaBoardWithContent");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<QnaBoard>();
			
			while(rset.next()) {
				QnaBoard qboard = new QnaBoard();
				qboard.setBoardTitle(rset.getString("BOARD_TITLE"));
				qboard.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				qboard.setCommunityName(rset.getString("COMMUNITY_NAME"));
				qboard.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				qboard.setMemberName(rset.getString("MEMBER_NAME"));
				qboard.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				qboard.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				qboard.setBoardCount(rset.getInt("BOARD_COUNT"));
				qboard.setBoardProcess(rset.getString("BOARD_PROCESS"));
				qboard.setBoardContent(rset.getString("BOARD_CONTENT"));
				qboard.setBoardNo(rset.getInt("BOARD_NO"));
				qboard.setBoardNum(rset.getInt("BOARD_NUM"));
				qboard.setStatus(rset.getString("STATUS"));
				
				list.add(qboard);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<QnaBoard> searchQnaBoardAll(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<QnaBoard> list = null;
		
		String query = prop.getProperty("searchQnaBoardAll");
		
		try {
			stmt = con.createStatement();

			rset = stmt.executeQuery(query);
			
			list = new ArrayList<QnaBoard>();
			
			while(rset.next()) {
				QnaBoard qboard = new QnaBoard();
				qboard.setBoardTitle(rset.getString("BOARD_TITLE"));
				qboard.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				qboard.setCommunityName(rset.getString("COMMUNITY_NAME"));
				qboard.setBoardWriterId(rset.getString("BOARD_WRITER_ID"));
				qboard.setMemberName(rset.getString("MEMBER_NAME"));
				qboard.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				qboard.setBoardPublic(rset.getString("BOARD_PUBLIC"));
				qboard.setBoardCount(rset.getInt("BOARD_COUNT"));
				qboard.setBoardProcess(rset.getString("BOARD_PROCESS"));
				qboard.setBoardContent(rset.getString("BOARD_CONTENT"));
				qboard.setBoardNo(rset.getInt("BOARD_NO"));
				qboard.setBoardNum(rset.getInt("BOARD_NUM"));
				qboard.setStatus(rset.getString("STATUS"));
				
				list.add(qboard);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public ArrayList<QnaBoard> sortQnaBoardListLastest(ArrayList<QnaBoard> list) {

		list.sort(new Comparator<QnaBoard>() {
			@Override
			public int compare(QnaBoard qb1, QnaBoard qb2) {
				Date date1 = qb1.getBoardWriteDate();
				Date date2 = qb2.getBoardWriteDate();
				
				return date2.compareTo(date1);
			}
		});
		
		return list;
	}

	public ArrayList<QnaBoard> sortQnaBoardListPopular(ArrayList<QnaBoard> list) {
		
		list.sort(new Comparator<QnaBoard>() {
			@Override
			public int compare(QnaBoard qb1, QnaBoard qb2) {
				int cnt1 = qb1.getBoardCount();
				int cnt2 = qb2.getBoardCount();
				
				if(cnt1 == cnt2) return 0;
				else if(cnt1 < cnt2) return 1;
				else return -1;
			}
		});
		
		return list;
	}

	//검색된 리스트를 pi에 맞춰 출력
	public ArrayList<QnaBoard> selectSearchedListWithPaging(ArrayList<QnaBoard> getList, PageInfo pi) {
		
		ArrayList<QnaBoard> newList = new ArrayList<QnaBoard>();
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getLimit() + 1;
		int endRow = startRow + pi.getLimit() - 1;
		
		if(startRow < 1) {
			startRow = 1;
		}
		
		if(endRow > pi.getListCount()) {
			endRow = pi.getListCount();
		}
		
		//0번 인덱스부터 시작하므로 -1
		for(int i=startRow-1; i<endRow; i++) {
			newList.add(getList.get(i));
		};
		
		return newList;
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
			
			file = new Attachment();
			if(rset.next()){
				file.setAttNo(rset.getInt("ATT_NO"));
				file.setBoardNo(rset.getInt("BOARD_NO"));
				file.setAttFileOriginName(rset.getString("ATT_FILE_ORIGIN_NAME"));
				file.setAttFileChangeName(rset.getString("ATT_FILE_CHANGE_NAME"));
				file.setAttFileAddress(rset.getString("ATT_FILE_ADDRESS"));
				file.setAttFileSize(rset.getInt("ATT_FILE_SIZE"));
				file.setAttStatus("ATT_STATUS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return file;
	}

}









