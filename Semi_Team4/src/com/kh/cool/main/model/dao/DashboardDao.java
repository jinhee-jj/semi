package com.kh.cool.main.model.dao;

import static com.kh.cool.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.main.model.vo.Notice;
import com.kh.cool.main.model.vo.Order;
import com.kh.cool.main.model.vo.Revenue;

public class DashboardDao {
	private Properties prop = new Properties();
	
	public DashboardDao() {
		String fileName = DashboardDao.class.getResource("/sql/main/dashboard-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Notice> selectNoticeOverview(Connection con, int row) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Notice> noticeList = null;
		
		String query = prop.getProperty("selectNiticeOverview");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, row);
			
			rset = pstmt.executeQuery();
			
			noticeList = new ArrayList<Notice>();
			
			while(rset.next()) {
				Notice nt = new Notice();
				nt.setBoardTitle(rset.getString("BOARD_TITLE"));
				nt.setCommunityCode(rset.getInt("COMMUNITY_CODE"));
				nt.setBoardWriteDate(rset.getDate("BOARD_WRITE_DATE"));
				nt.setBoardNo(rset.getInt("BOARD_NO"));
				nt.setBoardNum(rset.getInt("BOARD_NUM"));
				
				noticeList.add(nt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return noticeList;
	}

	public ArrayList<Revenue> selectRevenueNow(Connection con, String today) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Revenue> rList = null;
		
		String query = prop.getProperty("selectRevenueNow");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, today);
			
			rset = pstmt.executeQuery();
			
			rList = new ArrayList<Revenue>();

			while(rset.next()) {
				Revenue rv = new Revenue();
				rv.setBranchName(rset.getString("BRANCH_NAME"));
				rv.setGrossIncome(rset.getInt("SUM_GROSS_INCOME"));
				
				rList.add(rv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rList;
	}

	public ArrayList<Order> selectOrderNow(Connection con, String today, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Order> oList = null;
		
		String query = prop.getProperty("selectOrderNow");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			pstmt.setString(2, today);
			pstmt.setString(3, "주문완료");
			
			rset = pstmt.executeQuery();
			
			oList = new ArrayList<Order>();

			while(rset.next()) {
				Order od = new Order();
				od.setMenuName(rset.getString("MENU_NAME"));
				od.setOrderQuantity(rset.getInt("ORDER_QUANTITY"));
				od.setToGo(rset.getString("TOGO"));
				od.setOrderStatus(rset.getString("ORDER_STATUS"));
				
				oList.add(od);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return oList;
	}

	public String searchBranchName(Connection con, String deptCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String branchName = "";
		
		String query = prop.getProperty("searchBranchName");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, deptCode);
		
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				branchName = rset.getString("BRANCH_NAME");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return branchName;
	}

	public String findBranch(Connection con, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String bCode = "";
		
		String query = prop.getProperty("findBranch");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				bCode = rset.getString("MEMBER_DEPT_CODE");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return bCode;
	}
}
