package com.kh.cool.revenue.model.service;

import static com.kh.cool.common.JDBCTemplate.close;
import static com.kh.cool.common.JDBCTemplate.commit;
import static com.kh.cool.common.JDBCTemplate.getConnection;
import static com.kh.cool.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.main.model.dao.DashboardDao;
import com.kh.cool.revenue.model.dao.RevenueDao;
import com.kh.cool.revenue.model.vo.Branch;
import com.kh.cool.revenue.model.vo.BranchRevenue;
import com.kh.cool.revenue.model.vo.RevenueStatistics;

public class RevenueService {

	public String branchCheck(String deptCode) {
		Connection con = getConnection();
		
		String branchName = new RevenueDao().branchCheck(con, deptCode);

		close(con);

		return branchName;
	}

	public ArrayList<Branch> selectBranchAll() {
		Connection con = getConnection();
		ArrayList<Branch> deptList = null;
		
		deptList = new RevenueDao().selectBranchAll(con);
		
		return deptList;
	}

	public BranchRevenue selectBrIncomeYesterday(String selDate, String selBranch) {
		Connection con = getConnection();
		
		BranchRevenue br = new RevenueDao().selectBrIncomeYesterday(con, selDate, selBranch);
		
		close(con);
		
		return br;
	}

	public BranchRevenue selectBrIncomeMonth(String selDate, String selBranch) {
		Connection con = getConnection();
		
		BranchRevenue br = new RevenueDao().selectBrIncomeMonth(con, selDate, selBranch);
		
		close(con);
		
		return br;
	}

	public BranchRevenue selectBrIncomeYear(String selDate, String selBranch) {
		Connection con = getConnection();
		
		BranchRevenue br = new RevenueDao().selectBrIncomeYear(con, selDate, selBranch);
		
		close(con);
		
		return br;
	}

	public BranchRevenue selectHoIncomeYesterday(String selDate) {
		Connection con = getConnection();
		
		BranchRevenue ho = new RevenueDao().selectHoIncomeYesterday(con, selDate);
		
		close(con);
		
		return ho;
	}

	public BranchRevenue selectHoIncomeMonth(String selDate) {
		Connection con = getConnection();
		
		BranchRevenue ho = new RevenueDao().selectHoIncomeMonth(con, selDate);
		
		close(con);
		
		return ho;
	}

	public BranchRevenue selectHoIncomeYear(String selDate) {
		Connection con = getConnection();
		
		BranchRevenue ho = new RevenueDao().selectHoIncomeYear(con, selDate);
		
		close(con);
		
		return ho;
	}

	public BranchRevenue selectHoMarginYesterday(String selDate) {
		Connection con = getConnection();
		
		BranchRevenue ho = new RevenueDao().selectHoMarginYesterday(con, selDate);
		
		close(con);
		
		return ho;
	}

	public BranchRevenue selectHoMarginMonth(String selDate) {
		Connection con = getConnection();
		
		BranchRevenue ho = new RevenueDao().selectHoMarginMonth(con, selDate);
		
		close(con);
		
		return ho;
	}

	public BranchRevenue selectHoMarginYear(String selDate) {
		Connection con = getConnection();
		
		BranchRevenue ho = new RevenueDao().selectHoMarginYear(con, selDate);
		
		close(con);
		
		return ho;
	}

	public ArrayList<RevenueStatistics> selectHoIncomeListDay() {
		Connection con = getConnection();
		
		ArrayList<RevenueStatistics> rsList = new RevenueDao().selectHoIncomeListDay(con);
		
		close(con);
		
		return rsList;
	}

	public ArrayList<RevenueStatistics> selectHoIncomeListMonth() {
		Connection con = getConnection();
		
		ArrayList<RevenueStatistics> rsList = new RevenueDao().selectHoIncomeListMonth(con);
		
		close(con);
		
		return rsList;
	}

	public ArrayList<RevenueStatistics> selectBrIncomeListDay(String selBranch) {
		Connection con = getConnection();
		
		if(selBranch.substring(0,3).equals("KOK")) {
			selBranch = new DashboardDao().findBranch(con, selBranch);
		}
		
		ArrayList<RevenueStatistics> rsList = new RevenueDao().selectBrIncomeListDay(con, selBranch);
		
		close(con);
		
		return rsList;
	}

	public ArrayList<RevenueStatistics> selectBrIncomeListMonth(String selBranch) {
		Connection con = getConnection();
		
		ArrayList<RevenueStatistics> rsList = new RevenueDao().selectBrIncomeListMonth(con, selBranch);
		
		close(con);
		
		return rsList;
	}



}
