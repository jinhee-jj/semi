package com.kh.cool.inven.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.inven.model.vo.Ingredient;
import com.kh.cool.inven.model.vo.Inven;
import static com.kh.cool.common.JDBCTemplate.*;

public class InvenDao {
	
	private static Properties prop = new Properties();
	
	public InvenDao() {
		String fileName = InvenDao.class.getResource("/sql/inven/inven-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Inven> branchInvenList(Connection con, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inven> list = null;
		
		String query = prop.getProperty("branchList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, branchCode);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Inven>();
			
			while (rset.next()) {
				Inven i = new Inven();
				
				i.setIgretCode(rset.getString("INGREDIENT_CODE"));
				i.setIgretName(rset.getString("INGREDIENT_NAME"));
				i.setIgretClass(rset.getString("INGREDIENT_CLASS_NAME"));
				i.setAddQuantity(rset.getInt("INGREDIENT_QUANTITY"));
				i.setIgretWeight(rset.getInt("INGREDIENT_CAPACITY"));
				i.setExDate(rset.getDate("EX_DATE"));
				
				
				list.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}



	public ArrayList<Inven> invenStatue(Connection con, Inven statusInven) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inven> list = null;
		
		String query = prop.getProperty("StatusList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, statusInven.getBranchCode());
			//pstmt.setDate(2, statusInven.getInsertDate());
			//and bi.inven_date like to_date(?)
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Inven>();
			
			while (rset.next()) {
				Inven i = new Inven();
				
				i.setIgretCode(rset.getString("INGREDIENT_CODE"));
				i.setIgretName(rset.getString("INGREDIENT_NAME"));
				i.setIgretClass(rset.getString("INGREDIENT_CLASS_NAME"));
				i.setIgretQuantity(rset.getInt("HOLD_QUANTITY"));
				i.setIgretWeight(rset.getInt("INGREDIENT_CAPACITY"));
				i.setBuyType(rset.getString("INGREDIENT_UNIT"));
				i.setBranchCode(statusInven.getBranchCode());
				i.setInsertDate(statusInven.getInsertDate());
				
				list.add(i);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Inven> branchInvenDetail(Connection con, Inven detailInven) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inven> list = null;
		
		String query = prop.getProperty("detailInvenSelect");
		
		try {
			pstmt = con.prepareStatement(query);
			
			//pstmt.setDate(1, detailInven.getInsertDate());
			//a.UPDATE_DATE like to_date(?) AND
			pstmt.setString(1, detailInven.getBranchCode());
			pstmt.setString(2, detailInven.getIgretCode());
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Inven>();
			
			while (rset.next()) {
				Inven i = new Inven();
				
				i.setIgretCode(rset.getString("INGREDIENT_CODE"));
				i.setIgretName(rset.getString("INGREDIENT_NAME"));
				i.setExDate(rset.getDate("DETAIL_DATE"));
				i.setInsertDate(rset.getDate("UPDATE_DATE"));
				i.setIgretQuantity(rset.getInt("INGREDIENT_QUANTITY"));
				i.setInvenType(rset.getString("INVEN_TYPE"));
				
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<Inven> insertSelectInven(Connection con, Inven newSelectInven) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inven> list = null;
		
		String query = prop.getProperty("newSelectInven");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newSelectInven.getIgretCode());
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Inven>();
			
			while(rset.next()) {
			
				
				newSelectInven.setSup_code(rset.getString("SUPPLIER_CODE"));
				newSelectInven.setIgretName(rset.getString("INGREDIENT_NAME"));
				
				list.add(newSelectInven);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public int invenLastInsert(Connection con, Inven lastInsertInven) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("lastInsertInven");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, lastInsertInven.getAddQuantity());
			pstmt.setString(2, lastInsertInven.getBarcode());
			pstmt.setString(3, lastInsertInven.getIgretCode());
			pstmt.setString(4, lastInsertInven.getBranchCode());
			pstmt.setString(5, lastInsertInven.getSup_code());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateInvenQ(Connection con, Inven lastInsertInven) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateBranchInven");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, lastInsertInven.getIgretCode());
			pstmt.setString(2, lastInsertInven.getBranchCode());
			pstmt.setInt(3, lastInsertInven.getAddQuantity());
			pstmt.setString(4, lastInsertInven.getSup_code());
			pstmt.setString(5, lastInsertInven.getIgretCode());
			pstmt.setString(6, lastInsertInven.getBranchCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Inven> SelectBoxList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Inven> list = null;
		
		String query = prop.getProperty("selectBoxIf");
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Inven>();
			
			while(rset.next()) {
				Inven i = new Inven();
				i.setIgretCode(rset.getString("INGREDIENT_CODE"));
				i.setIgretName(rset.getString("INGREDIENT_NAME"));
				
				list.add(i);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		
		return list;
	} 

	public ArrayList<Inven> inputListSelect(Connection con, Inven selectInvenList) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inven> list = null;
		
		String query = prop.getProperty("inputList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, selectInvenList.getBranchCode());
			pstmt.setString(2, selectInvenList.getIgretCode());
			pstmt.setDate(3, selectInvenList.getMfDate());
			pstmt.setDate(4, selectInvenList.getExDate());
			pstmt.setString(5, selectInvenList.getInvenSelectType());
			pstmt.setString(6, selectInvenList.getBranchCode());
			pstmt.setString(7, selectInvenList.getIgretCode());
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Inven>();
			
			while(rset.next()) {
			Inven i = new Inven();
			i.setIgretCode(rset.getString("INGREDIENT_CODE"));
			i.setIgretName(rset.getString("INGREDIENT_NAME"));
			i.setIgretClass(rset.getString("INGREDIENT_CLASS_NAME"));
			i.setAddQuantity(rset.getInt("INGREDIENT_QUANTITY"));
			i.setIgretQuantity(rset.getInt("ingredient_capacity"));
			i.setAllQuantity(rset.getInt("HOLD_QUANTITY"));
			i.setInvenType(rset.getString("INGREDIENT_UNIT"));
			i.setInsertDate(rset.getDate("UPDATE_DATE"));
			i.setInvenSelectType(rset.getString("INVEN_TYPE"));
			
			list.add(i);
			}
			
			
			//System.out.println("dao1 : "+list);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		//System.out.println("Dao : "+list);
		return list;
	}

	public int insertIgImg(Connection con, Ingredient newingredient) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("inputIngredient");
		//System.out.println(newingredient);
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, newingredient.getIgInClass());
			pstmt.setString(2, newingredient.getIgCode());
			pstmt.setString(3, newingredient.getIgName());
			pstmt.setString(4, newingredient.getIgImageFilePath());
			pstmt.setString(5, newingredient.getOriginName());
			pstmt.setString(6, newingredient.getSaveName());
			pstmt.setString(7, newingredient.getIgUnit());
			pstmt.setString(8, newingredient.getIgSType());
			pstmt.setInt(9, newingredient.getIgCapacity());
			pstmt.setString(10, newingredient.getIgClass());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Ingredient> ingredientList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Ingredient> list = null;
		
		String query = prop.getProperty("ingredientList");
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Ingredient>();
			
			while(rset.next()) {
				Ingredient i = new Ingredient();
				i.setIgCode(rset.getString("INGREDIENT_CODE"));
				i.setIgName(rset.getString("INGREDIENT_NAME"));
				i.setIgClass(rset.getString("INGREDIENT_CLASS_NAME"));
				i.setIgUnit(rset.getString("INGREDIENT_UNIT"));
				i.setIgCapacity(rset.getInt("INGREDIENT_CAPACITY"));
				i.setIgSType(rset.getString("STORAGE_TYPE"));
				i.setIgImageFilePath(rset.getString("INGREDIENT_IMAGE"));
				i.setSaveName(rset.getString("IMAGE_CHA"));
				
				list.add(i);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
		}
		
		return list;
	}

	public int updateIngredient(Connection con, Ingredient updateIngredient) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateIngredient");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, updateIngredient.getIgName());
			pstmt.setString(2, updateIngredient.getIgImageFilePath());
			pstmt.setString(3, updateIngredient.getOriginName());
			pstmt.setString(4, updateIngredient.getSaveName());
			pstmt.setString(5, updateIngredient.getIgUnit());
			pstmt.setString(6, updateIngredient.getIgSType());
			pstmt.setInt(7, updateIngredient.getIgCapacity());
			pstmt.setString(8, updateIngredient.getIgClass());
			pstmt.setString(9, updateIngredient.getIgCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateIngredientNull(Connection con, Ingredient updateIngredient) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateIngredientNull");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, updateIngredient.getIgName());
			pstmt.setString(2, updateIngredient.getIgUnit());
			pstmt.setString(3, updateIngredient.getIgSType());
			pstmt.setInt(4, updateIngredient.getIgCapacity());
			pstmt.setString(5, updateIngredient.getIgClass());
			pstmt.setString(6, updateIngredient.getIgCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int ingredientDel(Connection con, Ingredient delIngredient) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("delIngredient");
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, delIngredient.getIgCode());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Inven> inputListAll(Connection con, Inven selectInvenList) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Inven> list = null;
		
		String query = prop.getProperty("inputList2");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, selectInvenList.getBranchCode());
			//pstmt.setString(2, selectInvenList.getIgretCode());
			//AND a.INGREDIENT_CODE = ?
			pstmt.setDate(2, selectInvenList.getMfDate());
			pstmt.setDate(3, selectInvenList.getExDate());
			pstmt.setString(4, selectInvenList.getInvenSelectType());
			pstmt.setString(5, selectInvenList.getBranchCode());
			//pstmt.setString(7, selectInvenList.getIgretCode());
			//AND a.INGREDIENT_CODE = ?
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Inven>();
			
			while(rset.next()) {
			Inven i = new Inven();
			i.setIgretCode(rset.getString("INGREDIENT_CODE"));
			i.setIgretName(rset.getString("INGREDIENT_NAME"));
			i.setIgretClass(rset.getString("INGREDIENT_CLASS_NAME"));
			i.setAddQuantity(rset.getInt("INGREDIENT_QUANTITY"));
			i.setIgretQuantity(rset.getInt("ingredient_capacity"));
			i.setAllQuantity(rset.getInt("HOLD_QUANTITY"));
			i.setInvenType(rset.getString("INGREDIENT_UNIT"));
			i.setInsertDate(rset.getDate("UPDATE_DATE"));
			i.setInvenSelectType(rset.getString("INVEN_TYPE"));
			
			list.add(i);
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
