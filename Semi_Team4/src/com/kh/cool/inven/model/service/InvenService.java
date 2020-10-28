package com.kh.cool.inven.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.inven.model.dao.InvenDao;
import com.kh.cool.inven.model.vo.Ingredient;
import com.kh.cool.inven.model.vo.Inven;
import static com.kh.cool.common.JDBCTemplate.*;

public class InvenService {

	public ArrayList<Inven> branchInven(String branchCode) {
		Connection con = getConnection();
		
		ArrayList<Inven> list = new InvenDao().branchInvenList(con, branchCode);
		
		//System.out.println("dao:"+list);
		
		close(con);
		
		return list;
	}


	public ArrayList<Inven> statusInven(Inven statusInven) {
		Connection con = getConnection();
		
		
		ArrayList<Inven> list = new InvenDao().invenStatue(con, statusInven);
		
		//System.out.println("servise :"+list);
		
		close(con);
		
		
		return list;
	}

	public ArrayList<Inven> branchDetail(Inven detailInven) {
		Connection con = getConnection();
		
		ArrayList<Inven> list = new InvenDao().branchInvenDetail(con, detailInven);
		
		close(con);
		
		return list;
	}


	public ArrayList<Inven> selectInsertBranchInven(Inven newSelectInven) {
		Connection con = getConnection();
		
		ArrayList<Inven> list = new InvenDao().insertSelectInven(con, newSelectInven);
		//System.out.println("daolist1:"+list);
		
		close(con);
		
		return list;
	}


	public int lastInsertInven(Inven lastInsertInven) {
		Connection con = getConnection();
		
		int num1 = new InvenDao().invenLastInsert(con, lastInsertInven);
		
		
		int num2 = new InvenDao().updateInvenQ(con, lastInsertInven);
		
		int result = 0;
		
		result = num1 + num2;
		
		if(result >= 2) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}


	public ArrayList<Inven> invenListSelectBox() {
		Connection con = getConnection();
		
		ArrayList<Inven> list = new InvenDao().SelectBoxList(con);
		
		close(con);
		
		return list;
	}


	public ArrayList<Inven> invenInputList(Inven selectInvenList) {
		Connection con = getConnection();
		ArrayList<Inven> list = null;
		//System.out.println("service : "+selectInvenList);
		if((selectInvenList.getIgretCode()).equals("all")){
			list = new InvenDao().inputListAll(con, selectInvenList);
			//System.out.println("serviceAll : "+ list);
			
		}else {
			
			list = new InvenDao().inputListSelect(con, selectInvenList);
			//System.out.println("serviceone : "+ list);
		}
		
		//System.out.println("service : "+ list);
		
		close(con);
		
		return list;
	}


	public int imgIgAdd(Ingredient newingredient) {
		Connection con = getConnection();
		int result = 0;
		
		//System.out.println("service"+newingredient);
		
		InvenDao idao = new InvenDao();

		result = idao.insertIgImg(con, newingredient);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return result;
	}


	public ArrayList<Ingredient> ingredientList() {
		Connection con = getConnection();
		
		ArrayList<Ingredient> list = new InvenDao().ingredientList(con);
		
		close(con);
		
		return list;
	}


	public static int ingredientUpdate(Ingredient updateIngredient) {
		Connection con = getConnection();
		int result = 0;
		String file = updateIngredient.getSaveName();
		
		if(file != null) {
			result = new InvenDao().updateIngredient(con, updateIngredient);
			
			if(result > 0) {
				commit(con);
			}else {
				rollback(con);
			}
			
		}else {
			result = new InvenDao().updateIngredientNull(con, updateIngredient);
			if(result > 0) {
				commit(con);
			}else {
				rollback(con);
			}
		}
		
		return result;
	}


	public int dellIngredient(Ingredient delIngredient) {
		Connection con = getConnection();
		int result = 0;

		result = new InvenDao().ingredientDel(con, delIngredient);

		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}

		return result;
	}

	


}
