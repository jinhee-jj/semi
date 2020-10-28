package com.kh.cool.orderManagement.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.orderManagement.model.vo.BRANCH;
import com.kh.cool.orderManagement.model.vo.Kiosk;
import com.kh.cool.orderManagement.model.vo.KioskCart;
import com.kh.cool.orderManagement.model.vo.MenuM;
import com.kh.cool.orderManagement.model.vo.MenuOne;
import com.kh.cool.orderManagement.model.vo.OrderDetail;
import com.kh.cool.orderManagement.model.vo.OrderResult;

import static com.kh.cool.common.JDBCTemplate.*;

public class KioskDao {
	Properties prop = new Properties();
	
	public KioskDao() {
		String fileName = KioskDao.class.getResource("/sql/kiosk/kiosk-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	public ArrayList<Kiosk> selectList(Connection con, String eat, String writer, String menu) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Kiosk> list = null;
		
		String query = "";
		if(menu.equals("all")) {
			query = prop.getProperty("selectList1");
		} else if(menu.equals("setMenu")) {
			query = prop.getProperty("selectList2");
		} else if(menu.equals("coffee")) {
			query = prop.getProperty("selectList3");
		} else if(menu.equals("juice")) {
			query = prop.getProperty("selectList4");
		} else if(menu.equals("bread")) {
			query = prop.getProperty("selectList5");
		} else if(menu.equals("tea")) {
			query = prop.getProperty("selectList6");
		} else {
			System.out.println("으어어억!! 심각한 버그!!!");
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "판매중");
			pstmt.setString(2, writer);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Kiosk>();
			while(rset.next()) {
				Kiosk k = new Kiosk();
				
				k.setMenuName(rset.getString("MENU_NAME"));
				k.setMenuCode(rset.getString("MENU_CODE"));
				k.setMenuPrice(rset.getInt("MENU_PRICE"));
				k.setMenuImage(rset.getString("MENU_IMAGE"));
				k.setMenuClassCode(rset.getString("MENU_CLASSIFICATION_CODE"));
				k.setMenuStatus(rset.getString("MENU_STATUS"));
				k.setMenu_cost(rset.getInt("MANUFACTURE_COST"));
				k.setBranchCode(rset.getString("BRANCH_CODE"));
				k.setEat(eat);
				
				list.add(k);
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

	public int insertCart(Connection con, KioskCart cart, String eat, String writer) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertCart");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, cart.getMenuCode());
			pstmt.setString(2, cart.getMenuName());
			pstmt.setInt(3, cart.getCount());
			pstmt.setString(4, cart.getImage());
			pstmt.setString(5, cart.getEat());
			pstmt.setInt(6, cart.getMenuPrice());
			pstmt.setString(7, cart.getBranchCode());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<KioskCart> selectListCart(Connection con, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<KioskCart> cart = null;
		
		String query = prop.getProperty("selectListCart");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			
			cart = new ArrayList<KioskCart>();
			while(rset.next()) {
				KioskCart k = new KioskCart();
				
				k.setBasketNum(rset.getInt("BASKET_NUM"));
				k.setMenuCode(rset.getString("MENU_CODE"));
				k.setMenuName(rset.getString("MENU_NAME"));
				k.setCount(rset.getInt("MENU_COUNT"));
				k.setImage(rset.getString("MENU_IMAGE"));
				k.setEat(rset.getString("MENU_EAT"));
				k.setMenuPrice(rset.getInt("MENU_PRICE"));
				k.setBranchCode(rset.getString("BRANCH_CODE"));
				
				cart.add(k);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		
		return cart;
	}

	public int deleteCart(Connection con, int cartNum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteCart");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, cartNum);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int allCountCart(Connection con, String writer) {
		
		PreparedStatement pstmt = null;
		int allCount = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("cartallCount");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				allCount = rset.getInt(1);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return allCount;
	}

	public int allPriceCart(Connection con, String writer) {
		PreparedStatement pstmt = null;
		int allPrice = 0;
		ResultSet rset = null;
		
		String query = prop.getProperty("allPriceCart");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				allPrice = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return allPrice;
	}

	public int deleteAllCart(Connection con, String writer) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteAllCart");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertOrResult(Connection con, OrderResult or) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrResult");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, or.getKioskCode());
			pstmt.setString(2, or.getTogo());
			pstmt.setInt(3, or.getGrossIncome());
			pstmt.setInt(4, or.getDiscountIncome());
			pstmt.setInt(5, or.getTotalIncome());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertOrResult2(Connection con, OrderDetail od) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertOrResult2");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, od.getMenuCode());
			pstmt.setInt(2, od.getOrderQuantity());
			pstmt.setInt(3, od.getOrderNo());
			pstmt.setInt(4, od.getOrderPrice());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public OrderResult selectOrder(Connection con, OrderResult or) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		OrderResult result = null;
		
		String query = prop.getProperty("selectOrder");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, or.getKioskCode());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = new OrderResult();
				
				result.setOrderNo(rset.getInt("ORDER_NO"));
				result.setOrderDate(rset.getDate("ORDER_DATE"));
				result.setKioskCode(rset.getString("KIOSK_CODE"));
				result.setTogo(rset.getString("TOGO"));
				result.setGrossIncome(rset.getInt("GROSS_INCOME"));
				result.setDiscountIncome(rset.getInt("DISCOUNT_INCOME"));
				result.setTotalIncome(rset.getInt("TOTAL_INCOME"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public String selectBranch(Connection con, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = "";
		
		String query = prop.getProperty("selectBranch");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				result = rset.getString("MEMBER_DEPT_CODE");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public ArrayList<OrderResult> selectOrderList(Connection con, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderResult> result = null;
		
		String query = prop.getProperty("selectOrderList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			pstmt.setString(2, "주문완료");
			
			rset = pstmt.executeQuery();
			
			result = new ArrayList<OrderResult>();
			while(rset.next()) {
				OrderResult or = new OrderResult();
				or.setOrderNo(rset.getInt("ORDER_NO"));
				or.setOrderDate(rset.getDate("ORDER_DATE"));
				or.setTogo(rset.getString("TOGO"));
				
				result.add(or);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public int updateOrder(Connection con, String writer, OrderResult or) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateOrder");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			pstmt.setString(2, "주문완료");
			pstmt.setString(3, "제조완료");
			pstmt.setInt(4, or.getOrderNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<OrderResult> selectOrderList2(Connection con, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderResult> result = null;
		
		String query = prop.getProperty("selectOrderList2");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			pstmt.setString(2, "제조완료");
			
			rset = pstmt.executeQuery();
			
			result = new ArrayList<OrderResult>();
			while(rset.next()) {
				OrderResult or = new OrderResult();
				or.setOrderNo(rset.getInt("ORDER_NO"));
				or.setOrderDate(rset.getDate("ORDER_DATE"));
				or.setTogo(rset.getString("TOGO"));
				
				result.add(or);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public int updateOrder2(Connection con, String writer, OrderResult resultUp) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateOrder2");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			pstmt.setString(2, "제조완료");
			pstmt.setString(3, "전달완료");
			pstmt.setInt(4, resultUp.getOrderNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public String selectKiosk(Connection con, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String result = "";
		
		String query = prop.getProperty("selectKiosk");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			
			int i = 0;
			while(rset.next()) {
				
				if(i == 0) {
					result = rset.getString("KIOSK_CODE");
				} else {
					result += "," + rset.getString("KIOSK_CODE");
				}
				i = 1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public ArrayList<OrderResult> selectOHistory(Connection con, String[] kioskArr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderResult> hisList = null;
		
		String query = prop.getProperty("selectOHistory");
		
		try {
			
			hisList = new ArrayList<OrderResult>();
			
			for(int i = 0; i < kioskArr.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, kioskArr[i]);
				
				rset = pstmt.executeQuery();
				
				while( rset.next() ) {
					OrderResult o = new OrderResult();
					o.setOrderDate(rset.getDate("ORDER_DATE"));
					o.setOrderNo(rset.getInt("ORDER_NO"));
					o.setTogo(rset.getString("TOGO"));
					o.setGrossIncome(rset.getInt("GROSS_INCOME"));
					o.setDiscountIncome(rset.getInt("DISCOUNT_INCOME"));
					o.setTotalIncome(rset.getInt("TOTAL_INCOME"));
					
					hisList.add(o);
				}
				
			}


			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return hisList;
	}

	public ArrayList<OrderDetail> selectODetail(Connection con, String[] kioskArr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderDetail> detailList = null;
		
		String query = prop.getProperty("selectODetail");
		
		try {
			detailList = new ArrayList<OrderDetail>();
			
			for(int i = 0; i < kioskArr.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, kioskArr[i]);
				
				rset = pstmt.executeQuery();
				
				while( rset.next() ) {
					OrderDetail o = new OrderDetail();
					o.setOrderNumber(rset.getInt("ORDER_NUMBER"));
					o.setMenuName(rset.getString("MENU_NAME"));
					o.setOrderQuantity(rset.getInt("ORDER_QUANTITY"));
					o.setOrderPrice(rset.getInt("ORDER_PRICE"));
					o.setOrderStatus(rset.getString("ORDER_STATUS"));
					
					detailList.add(o);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		
		return detailList;
	}

	public ArrayList<OrderResult> searchOHistory(Connection con, String[] kioskArr, String date) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderResult> result = null;
		
		String query = prop.getProperty("searchOHistory");
		
		try {
			result = new ArrayList<OrderResult>();
			
			for(int i = 0; i < kioskArr.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, kioskArr[i]);
				pstmt.setString(2, date);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					OrderResult o = new OrderResult();
					o.setOrderDate(rset.getDate("ORDER_DATE"));
					o.setOrderNo(rset.getInt("ORDER_NO"));
					o.setTogo(rset.getString("TOGO"));
					o.setGrossIncome(rset.getInt("GROSS_INCOME"));
					o.setDiscountIncome(rset.getInt("DISCOUNT_INCOME"));
					o.setTotalIncome(rset.getInt("TOTAL_INCOME"));
					
					result.add(o);
				}
				
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public ArrayList<OrderDetail> searchODtail(Connection con, String[] kioskArr, String date) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrderDetail> result = null;
		
		String query = prop.getProperty("searchODtail");
		
		try {
			result = new ArrayList<OrderDetail>();
			
			for(int i = 0; i < kioskArr.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, kioskArr[i]);
				pstmt.setString(2, date);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					OrderDetail o = new OrderDetail();
					o.setOrderNumber(rset.getInt("ORDER_NUMBER"));
					o.setMenuName(rset.getString("MENU_NAME"));
					o.setOrderQuantity(rset.getInt("ORDER_QUANTITY"));
					o.setOrderPrice(rset.getInt("ORDER_PRICE"));
					o.setOrderStatus(rset.getString("ORDER_STATUS"));
					
					result.add(o);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		
		return result;
	}

	public ArrayList<BRANCH> selectBranchList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<BRANCH> result = null;
		
		String query = prop.getProperty("selectBranchList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			result = new ArrayList<BRANCH>();
			while(rset.next()) {
				BRANCH b = new BRANCH();
				b.setBranchCode(rset.getString("BRANCH_CODE"));
				b.setBranchName(rset.getString("BRANCH_NAME"));
				
				result.add(b);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		
		return result;
	}

	public ArrayList<MenuM> selectMenuList(Connection con, String writer) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MenuM> menu = null;
		
		String query = prop.getProperty("selectMenuList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			
			rset = pstmt.executeQuery();
			
			menu = new ArrayList<MenuM>();
			while(rset.next()) {
				MenuM m = new MenuM();
				m.setMenuClass(rset.getString("MENU_CLASSIFICATION_NAME"));
				m.setMenuImage(rset.getString("MENU_IMAGE"));
				m.setMenuCode(rset.getString("MENU_CODE"));
				m.setMenuName(rset.getString("MENU_NAME"));
				m.setMenuPrice(rset.getInt("MENU_PRICE"));
				m.setMenuStatus(rset.getString("STATUS"));
				
				menu.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return menu;
	}

	public ArrayList<MenuM> searchMenu(Connection con, String writer, String search, String value) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MenuM> menu = null;
		
		String query = "";
		if( search.equals("menuName") ) {
			query = prop.getProperty("searchMenu1");
		} else if( search.equals("type") ) {
			query = prop.getProperty("searchMenu2");
		} else if( search.equals("state") ) {
			query = prop.getProperty("searchMenu3");
		} else {
			
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			pstmt.setString(2, "%"+value+"%");
			
			rset = pstmt.executeQuery();
			
			menu = new ArrayList<MenuM>();
			while(rset.next()) {
			MenuM m = new MenuM();
			m.setMenuClass(rset.getString("MENU_CLASSIFICATION_NAME"));
			m.setMenuImage(rset.getString("MENU_IMAGE"));
			m.setMenuCode(rset.getString("MENU_CODE"));
			m.setMenuName(rset.getString("MENU_NAME"));
			m.setMenuPrice(rset.getInt("MENU_PRICE"));
			m.setMenuStatus(rset.getString("STATUS"));
			
			menu.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return menu;
	}

	public int menuUpdate(Connection con, String writer, String state, String[] menuArr) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String stt = "";
		if(state.equals("0")) {
			stt = "단종";
		} else if(state.equals("1")) {
			stt = "매진";
		} else {
			stt = "판매중";
		}
		
		String query = prop.getProperty("danUpdate");
		
		try {
			
			for(int i = 0; i < menuArr.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, writer);
				pstmt.setString(2, stt);
				pstmt.setString(3, menuArr[i]);
				
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public ArrayList<MenuOne> selectOneMenu(Connection con, String writer, String code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MenuOne> result = null;
		
		String query = prop.getProperty("selectOneMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);
			pstmt.setString(2, writer);
			
			rset = pstmt.executeQuery();
			result = new ArrayList<MenuOne>();
			while(rset.next()) {
				MenuOne m = new MenuOne();
				m.setMenuImage(rset.getString("MENU_IMAGE"));
				m.setMenuName(rset.getString("MENU_NAME"));
				m.setStatus(rset.getString("STATUS"));
				m.setIdenCode(rset.getString("INGREDIENT_CODE"));
				m.setIdenName(rset.getString("INGREDIENT_NAME"));
				m.setIdenCapacity(rset.getInt("MANUFACTURE_CAPACITY"));
				m.setIdenUnit(rset.getString("INGREDIENT_UNIT"));
				
				result.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public int deleteMenu(Connection con, String code, String writer) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMenu");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, writer);
			pstmt.setString(2, code);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<MenuM> searchHOMenu(Connection con, String branchName, String search, String value) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MenuM> menu = null;
		
		String query = "";
		if( search.equals("menuName") ) {
			query = prop.getProperty("searchHOMenu1");
		} else if( search.equals("type") ) {
			query = prop.getProperty("searchHOMenu2");
		} else if( search.equals("state") ) {
			query = prop.getProperty("searchHOMenu3");
		} else {
			
		}
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchName);
			pstmt.setString(2, "%"+value+"%");
			
			rset = pstmt.executeQuery();
			
			menu = new ArrayList<MenuM>();
			while(rset.next()) {
			MenuM m = new MenuM();
			m.setMenuClass(rset.getString("MENU_CLASSIFICATION_NAME"));
			m.setMenuImage(rset.getString("MENU_IMAGE"));
			m.setMenuCode(rset.getString("MENU_CODE"));
			m.setMenuName(rset.getString("MENU_NAME"));
			m.setMenuPrice(rset.getInt("MENU_PRICE"));
			m.setMenuStatus(rset.getString("STATUS"));
			
			menu.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return menu;
	}

	public ArrayList<MenuOne> selectHoOneMenu(Connection con, String branch, String code) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MenuOne> result = null;
		
		String query = prop.getProperty("selectHoOneMenu");
		
		String bh = branch.replaceAll("점", "");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);
			pstmt.setString(2, bh);
			
			rset = pstmt.executeQuery();
			result = new ArrayList<MenuOne>();
			while(rset.next()) {
				MenuOne o = new MenuOne();
				o.setMenuImage(rset.getString("MENU_IMAGE"));
				o.setMenuName(rset.getString("MENU_NAME"));
				o.setStatus(rset.getString("STATUS"));
				o.setIdenCode(rset.getString("INGREDIENT_CODE"));
				o.setIdenName(rset.getString("INGREDIENT_NAME"));
				o.setIdenCapacity(rset.getInt("MANUFACTURE_CAPACITY"));
				o.setIdenUnit(rset.getString("INGREDIENT_UNIT"));
				
				result.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return result;
	}

	public int menuHoUpdate(Connection con, String branch, String state, String[] menuArr) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String stt = "";
		if(state.equals("0")) {
			stt = "단종";
		} else if(state.equals("1")) {
			stt = "매진";
		} else {
			stt = "판매중";
		}
		
		String query = prop.getProperty("menuHoUpdate");
		
		String branchName = branch.replaceAll("점", "");
		
		try {
			
			for(int i = 0; i < menuArr.length; i++) {
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, branchName);
				pstmt.setString(2, stt);
				pstmt.setString(3, menuArr[i]);
				
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

}
 