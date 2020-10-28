package com.kh.cool.orderManagement.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.orderManagement.model.dao.KioskDao;
import com.kh.cool.orderManagement.model.vo.BRANCH;
import com.kh.cool.orderManagement.model.vo.Kiosk;
import com.kh.cool.orderManagement.model.vo.KioskCart;
import com.kh.cool.orderManagement.model.vo.MenuM;
import com.kh.cool.orderManagement.model.vo.MenuOne;
import com.kh.cool.orderManagement.model.vo.OrderDetail;
import com.kh.cool.orderManagement.model.vo.OrderResult;

import static com.kh.cool.common.JDBCTemplate.*;

public class KioskService {

	public ArrayList<Kiosk> selectList(String eat, String writer, String menu) {
		Connection con = getConnection();
		
		ArrayList<Kiosk> list = new KioskDao().selectList(con,eat,writer, menu);
		
		close(con);
		
		return list;
	}

	public int insertCart(KioskCart cart, String eat, String writer) {
		Connection con = getConnection();
		
		int result = new KioskDao().insertCart(con,cart, eat, writer);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<KioskCart> selectListCart(String writer) {
		Connection con = getConnection();
		
		ArrayList<KioskCart> cart = new KioskDao().selectListCart(con, writer);
		
		close(con);
		
		return cart;
	}

	public int deleteCart(int cartNum) {
		Connection con = getConnection();
		
		int result = new KioskDao().deleteCart(con, cartNum);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int allCountCart(String writer) {
		Connection con = getConnection();
		
		int result = new KioskDao().allCountCart(con,writer);
		
		close(con);
		
		return result;
	}

	public int allPriceCart(String writer) {
		Connection con = getConnection();
		
		int result = new KioskDao().allPriceCart(con,writer);
		
		close(con);
		
		return result;
	}

	public int deleteAllCart(String writer) {
		Connection con = getConnection();
		
		int result = new KioskDao().deleteAllCart(con, writer);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int insertOrResult(OrderResult or) {
		Connection con = getConnection();
		
		int result = new KioskDao().insertOrResult(con, or);
		
		if(result > 0) {
			commit(con);
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public int insertOrResult2(OrderDetail od) {
		Connection con = getConnection();
		
		int result = new KioskDao().insertOrResult2(con, od);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public OrderResult selectOrder(OrderResult or) {
		Connection con = getConnection();
		
		OrderResult result = new KioskDao().selectOrder(con, or);
		
		close(con);
		
		return result;
	}

	public String selectBranch(String writer) {
		Connection con = getConnection();
		
		String result = new KioskDao().selectBranch(con, writer);
		
		close(con);
		
		return result;
	}

	public ArrayList<OrderResult> selectOrderList(String writer) {
		Connection con = getConnection();
		
		ArrayList<OrderResult> result = new KioskDao().selectOrderList(con, writer);
		
		close(con);
		
		return result;
	}

	public int orderUpdate(String writer, OrderResult or) {
		Connection con = getConnection();
		
		int result = new KioskDao().updateOrder(con, writer, or);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<OrderResult> selectOrderList2(String writer) {
		Connection con = getConnection();
		
		ArrayList<OrderResult> result = new KioskDao().selectOrderList2(con, writer);
		
		close(con);
		
		return result;
	}

	public int resultUpdate(String writer, OrderResult resultUp) {
		Connection con = getConnection();
		
		int result = new KioskDao().updateOrder2(con, writer, resultUp);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public String selectKiosk(String writer) {
		Connection con = getConnection();
		
		String result = new KioskDao().selectKiosk(con, writer);
		
		close(con);
		
		return result;
	}

	public ArrayList<OrderResult> selectOHistory(String[] kioskArr) {
		Connection con = getConnection();
		
		ArrayList<OrderResult> result = new KioskDao().selectOHistory(con, kioskArr);
		
		close(con);
		
		return result;
	}

	public ArrayList<OrderDetail> selectODetail(String[] kioskArr) {
		Connection con = getConnection();
		
		ArrayList<OrderDetail> result = new KioskDao().selectODetail(con, kioskArr);
		
		close(con);
		
		return result;
	}

	public ArrayList<OrderResult> searchOHistory(String[] kioskArr, String date) {
		Connection con = getConnection();
		
		ArrayList<OrderResult> result = new KioskDao().searchOHistory(con,kioskArr,date);
		
		close(con);
		
		return result;
	}

	public ArrayList<OrderDetail> searchODetail(String[] kioskArr, String date) {
		Connection con = getConnection();
		
		ArrayList<OrderDetail> result = new KioskDao().searchODtail(con,kioskArr,date);
		
		close(con);
		
		return result;
	}

	public ArrayList<BRANCH> selectBranchList() {
		Connection con = getConnection();
		
		ArrayList<BRANCH> result = new KioskDao().selectBranchList(con);
		
		close(con);
		
		return result;
	}

	public ArrayList<MenuM> selectMenuList(String writer) {
		Connection con = getConnection();
		
		ArrayList<MenuM> result = new KioskDao().selectMenuList(con,writer);
		
		close(con);
		
		return result;
	}

	public ArrayList<MenuM> searchMenu(String writer, String search, String value) {
		Connection con = getConnection();
		
		ArrayList<MenuM> result = new KioskDao().searchMenu(con,writer,search,value);
		
		close(con);
		
		return result;
	}

	public int menuUpdate(String writer, String state, String[] menuArr) {
		Connection con = getConnection();
		
		int result = new KioskDao().menuUpdate(con,writer,state,menuArr);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<MenuOne> selectOneMenu(String writer, String code) {
		Connection con = getConnection();
		
		ArrayList<MenuOne> result = new KioskDao().selectOneMenu(con,writer,code);
		
		return result;
	}

	public int deleteMenu(String code, String writer) {
		Connection con = getConnection();
		
		int result = new KioskDao().deleteMenu(con, code, writer);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<MenuM> searchHOMenu(String branchName, String search, String value) {
		Connection con = getConnection();
		
		ArrayList<MenuM> result = new KioskDao().searchHOMenu(con,branchName,search,value);
		
		close(con);
		
		return result;
	}

	public ArrayList<MenuOne> selectHoOneMenu(String branch, String code) {
		Connection con = getConnection();
		
		ArrayList<MenuOne> result = new KioskDao().selectHoOneMenu(con,branch,code);
		
		close(con);
		
		return result;
	}

	public int menuHoUpdate(String branch, String state, String[] menuArr) {
		Connection con = getConnection();
		
		int result = new KioskDao().menuHoUpdate(con,branch,state,menuArr); 
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

}
