package com.kh.cool.main.model.service;

import static com.kh.cool.common.JDBCTemplate.close;
import static com.kh.cool.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.main.model.dao.DashboardDao;
import com.kh.cool.main.model.vo.Notice;
import com.kh.cool.main.model.vo.Order;
import com.kh.cool.main.model.vo.Revenue;

public class DashboardService {

	public ArrayList<Notice> selectNoticeOverviwe(int row) {
		Connection con = getConnection();
		
		ArrayList<Notice> noticeList = new DashboardDao().selectNoticeOverview(con, row);
		
		close(con);
		
		return noticeList;
	}

	public ArrayList<Revenue> selectRevenueNow(String today) {
		Connection con = getConnection();
		
		ArrayList<Revenue> rList = new DashboardDao().selectRevenueNow(con, today);
	
		close(con);
		
		return rList;
	}

	public ArrayList<Order> selectOrderNow(String today, String branchCode) {
		Connection con = getConnection();
		
		if(branchCode.substring(0,3).equals("KOK")) {
			branchCode = new DashboardDao().findBranch(con, branchCode);
		}
		
		ArrayList<Order> oList = new DashboardDao().selectOrderNow(con, today, branchCode);
	
		close(con);
		
		return oList;
	}

	public String searchBranchName(String deptCode) {
		Connection con = getConnection();
		
		String branchName = new DashboardDao().searchBranchName(con, deptCode);

		close(con);
		
		return branchName;
	}

}
