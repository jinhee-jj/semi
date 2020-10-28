package com.kh.cool.revenue.model.dao;

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
import java.util.Properties;

import com.kh.cool.revenue.model.vo.Branch;
import com.kh.cool.revenue.model.vo.BranchRevenue;
import com.kh.cool.revenue.model.vo.RevenueStatistics;

public class RevenueDao {
	private Properties prop = new Properties();
	
	public RevenueDao() {
		String fileName = RevenueDao.class.getResource("/sql/revenue/revenue-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String branchCheck(Connection con, String deptCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String branchName = null;
		
		String query = prop.getProperty("branchCheck");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, deptCode);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				branchName = rset.getString("BRANCH_NAME");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return branchName;
	}

	public ArrayList<Branch> selectBranchAll(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Branch> deptList = null;
		
		String query = prop.getProperty("selectBranchAll");
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			deptList = new ArrayList<Branch>();
			while(rset.next()) {
				Branch br = new Branch();
				
				br.setBranchCode(rset.getString("BRANCH_CODE"));
				br.setBranchName(rset.getString("BRANCH_NAME"));

				deptList.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return deptList;
	}

	public BranchRevenue selectBrIncomeYesterday(Connection con, String selDate, String selBranch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue br = null;
		
		String query = prop.getProperty("selectBrIncomeYesterday");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selBranch);
			pstmt.setString(2, selDate);
			
			rset = pstmt.executeQuery();

			br = new BranchRevenue();
			//한 번에 1개 지점에 대한 정보만 필요
			if(rset.next()) {
				br.setBranchCode(rset.getString("BRANCH_CODE"));
				//br.setOrderDate(rset.getDate("ORDER_DATE"));
				br.setGrossIncome(rset.getInt(3));
				br.setDiscountIncome(rset.getInt(4));
				br.setTotalIncome(rset.getInt(5));
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return br;
	}

	public BranchRevenue selectBrIncomeMonth(Connection con, String selDate, String selBranch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue br = null;
		
		String query = prop.getProperty("selectBrIncomeMonth");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selBranch);
			pstmt.setString(2, selDate);
			
			rset = pstmt.executeQuery();
		
			br = new BranchRevenue();
			//한 번에 1개 지점에 대한 정보만 필요
			if(rset.next()) {
				br.setBranchCode(rset.getString("BRANCH_CODE"));
				//br.setOrderDate(rset.getDate(2));
				br.setGrossIncome(rset.getInt(3));
				br.setDiscountIncome(rset.getInt(4));
				br.setTotalIncome(rset.getInt(5));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return br;
	}

	public BranchRevenue selectBrIncomeYear(Connection con, String selDate, String selBranch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue br = null;
		
		String query = prop.getProperty("selectBrIncomeYear");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selBranch);
			pstmt.setString(2, selDate);
			
			rset = pstmt.executeQuery();
		
			br = new BranchRevenue();
			//한 번에 1개 지점에 대한 정보만 필요
			if(rset.next()) {
				br.setBranchCode(rset.getString("BRANCH_CODE"));
				//br.setOrderDate(rset.getDate(2));
				br.setGrossIncome(rset.getInt(3));
				br.setDiscountIncome(rset.getInt(4));
				br.setTotalIncome(rset.getInt(5));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return br;
	}
// 지난달
	
	public BranchRevenue selectHoIncomeYesterday(Connection con, String selDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue ho = null;
		
		String query = prop.getProperty("selectHoIncomeYesterday");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selDate);
			
			rset = pstmt.executeQuery();
			
			ho = new BranchRevenue();
			if(rset.next()) {
				//ho.setOrderDate(rset.getDate("ORDER_DATE"));
				ho.setGrossIncome(rset.getInt(2));
				ho.setDiscountIncome(rset.getInt(3));
				ho.setTotalIncome(rset.getInt(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ho;
	}

	public BranchRevenue selectHoIncomeMonth(Connection con, String selDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue ho = null;
		
		String query = prop.getProperty("selectHoIncomeMonth");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selDate);
			
			rset = pstmt.executeQuery();
			
			ho = new BranchRevenue();
			if(rset.next()) {
				//ho.setOrderDate(rset.getDate("ORDER_DATE"));
				ho.setGrossIncome(rset.getInt(2));
				ho.setDiscountIncome(rset.getInt(3));
				ho.setTotalIncome(rset.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ho;
	}

	public BranchRevenue selectHoIncomeYear(Connection con, String selDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue ho = null;
		
		String query = prop.getProperty("selectHoIncomeYear");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selDate);
			
			rset = pstmt.executeQuery();
			
			ho = new BranchRevenue();
			if(rset.next()) {
				//ho.setOrderDate(rset.getDate("ORDER_DATE"));
				ho.setGrossIncome(rset.getInt(2));
				ho.setDiscountIncome(rset.getInt(3));
				ho.setTotalIncome(rset.getInt(4));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ho;
	}

	public BranchRevenue selectHoMarginYesterday(Connection con, String selDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue ho = null;
		
		String query = prop.getProperty("selectHoMarginYesterday");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selDate);
			
			rset = pstmt.executeQuery();

			ho = new BranchRevenue();
			if(rset.next()) {
				ho.setMargin(rset.getInt("ORDER_MARGIN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ho;
	}

	public BranchRevenue selectHoMarginMonth(Connection con, String selDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue ho = null;
		
		String query = prop.getProperty("selectHoMarginMonth");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selDate);
			
			rset = pstmt.executeQuery();
			
			ho = new BranchRevenue();
			if(rset.next()) {
				ho.setMargin(rset.getInt("ORDER_MARGIN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ho;
	}

	public BranchRevenue selectHoMarginYear(Connection con, String selDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BranchRevenue ho = null;
		
		String query = prop.getProperty("selectHoMarginYear");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selDate);
			
			rset = pstmt.executeQuery();
			
			ho = new BranchRevenue();
			if(rset.next()) {
				ho.setMargin(rset.getInt("ORDER_MARGIN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return ho;
	}

	public ArrayList<RevenueStatistics> selectHoIncomeListDay(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<RevenueStatistics> rsList = null;
		
		String query = prop.getProperty("selectHoIncomeListDay");
		
		try {
			stmt = con.createStatement();

			rset = stmt.executeQuery(query);
			
			rsList = new ArrayList<RevenueStatistics>();
			
			while(rset.next()) {
				RevenueStatistics rs = new RevenueStatistics();

				// 출력형식에 맞게 조정
				String tempDate = rset.getString("CHAR_DATE");
				tempDate = tempDate.replaceAll("/", ".");
				rs.setDate(tempDate);
				// 본사: 전체 통합 -> 지점코드 branchCode 없음
				rs.setGrossIncome(rset.getInt("SUM_GROSS_INCOME"));
				rs.setManufactureCost(rset.getInt("SUM_MANUFACTURE_COST"));
				rs.setMargin(rset.getInt("SUM_MARGIN"));
				rs.setMarginRate(rset.getInt("SUM_MARGIN_RATE"));
				
				rsList.add(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rsList;
	}

	public ArrayList<RevenueStatistics> selectHoIncomeListMonth(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		
		ArrayList<RevenueStatistics> rsList = null;
		
		String query = prop.getProperty("selectHoIncomeListMonth");
		
		try {
			stmt = con.createStatement();

			rset = stmt.executeQuery(query);
			
			rsList = new ArrayList<RevenueStatistics>();
			
			while(rset.next()) {
				RevenueStatistics rs = new RevenueStatistics();

				// 출력형식에 맞게 조정
				String tempDate = rset.getString("CHAR_DATE");
				tempDate = tempDate.replaceAll("/", ".");
				rs.setDate(tempDate);
				// 본사: 전체 통합 -> 지점코드 branchCode 없음
				rs.setGrossIncome(rset.getInt("SUM_GROSS_INCOME"));
				rs.setManufactureCost(rset.getInt("SUM_MANUFACTURE_COST"));
				rs.setMargin(rset.getInt("SUM_MARGIN"));
				rs.setMarginRate(rset.getInt("SUM_MARGIN_RATE"));
				
				rsList.add(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return rsList;
	}

	public ArrayList<RevenueStatistics> selectBrIncomeListDay(Connection con, String selBranch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<RevenueStatistics> rsList = null;
		
		String query = prop.getProperty("selectBrIncomeListDay");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selBranch);

			rset = pstmt.executeQuery();
			
			rsList = new ArrayList<RevenueStatistics>();
			
			while(rset.next()) {
				RevenueStatistics rs = new RevenueStatistics();

				// 출력형식에 맞게 조정
				String tempDate = rset.getString("CHAR_DATE");
				tempDate = tempDate.replaceAll("/", ".");
				rs.setDate(tempDate);
				rs.setBranchCode(rset.getString("BRANCH_CODE"));
				rs.setGrossIncome(rset.getInt("SUM_GROSS_INCOME"));
				rs.setManufactureCost(rset.getInt("SUM_MANUFACTURE_COST"));
				rs.setMargin(rset.getInt("SUM_MARGIN"));
				rs.setMarginRate(rset.getInt("SUM_MARGIN_RATE"));
				
				rsList.add(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rsList;
	}

	public ArrayList<RevenueStatistics> selectBrIncomeListMonth(Connection con, String selBranch) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<RevenueStatistics> rsList = null;
		
		String query = prop.getProperty("selectBrIncomeListMonth");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, selBranch);

			rset = pstmt.executeQuery();
			
			rsList = new ArrayList<RevenueStatistics>();
			
			while(rset.next()) {
				RevenueStatistics rs = new RevenueStatistics();

				// 출력형식에 맞게 조정
				String tempDate = rset.getString("CHAR_DATE");
				tempDate = tempDate.replaceAll("/", ".");
				rs.setDate(tempDate);
				rs.setBranchCode(rset.getString("BRANCH_CODE"));
				rs.setGrossIncome(rset.getInt("SUM_GROSS_INCOME"));
				rs.setManufactureCost(rset.getInt("SUM_MANUFACTURE_COST"));
				rs.setMargin(rset.getInt("SUM_MARGIN"));
				rs.setMarginRate(rset.getInt("SUM_MARGIN_RATE"));
				
				rsList.add(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return rsList;
	}

}
