package com.kh.cool.purchase.model.dao;

import static com.kh.cool.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.kh.cool.purchase.model.vo.Branch;
import com.kh.cool.purchase.model.vo.IngredientListBR;
import com.kh.cool.purchase.model.vo.PurchaseListBR;
import com.kh.cool.purchase.model.vo.Supplier;
import com.kh.cool.purchase.model.vo.SupplierIngredient;
import com.kh.cool.purchase.model.vo.SupplierOrder;

public class PurchaseDao {
	Properties prop = new Properties();
	
	public PurchaseDao () {
		String fileName = PurchaseDao.class.getResource("/sql/purchase/purchase-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SupplierIngredient> selectIngredientList(Connection con, String branchCode) {
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		ArrayList<SupplierIngredient> list = null;
		
		String query = prop.getProperty("selectIngredientList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SupplierIngredient>();
			while(rset.next()) {
				SupplierIngredient sIngredient = new SupplierIngredient();
				
				sIngredient.setsCode(rset.getString("SUPPLIER_CODE"));
				sIngredient.setiClassName(rset.getString("INGREDIENT_CLASS_NAME"));
				sIngredient.setiCode(rset.getString("INGREDIENT_CODE"));
				sIngredient.setiName(rset.getString("INGREDIENT_NAME"));
				sIngredient.setiCapacity(rset.getInt("INGREDIENT_CAPACITY"));
				sIngredient.setiUnit(rset.getString("INGREDIENT_UNIT"));
				sIngredient.setBranchInven(rset.getInt("HOLD_QUANTITY"));
				sIngredient.setBranchCode(rset.getString("BRANCH_CODE"));
				
				list.add(sIngredient);
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
	
	
	//재료명으로 검색시 리스트 호출 메소드
	public ArrayList<IngredientListBR> selectListWithiName(Connection con, String searchValue, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<IngredientListBR> list = null;
		
		String query = prop.getProperty("selectListWithiName");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			pstmt.setString(2, searchValue);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<IngredientListBR>();
			while(rset.next()) {
				IngredientListBR ingredient = new IngredientListBR();
				
				ingredient.setiClassName(rset.getString("INGREDIENT_CLASS_NAME"));
				ingredient.setiCode(rset.getString("INGREDIENT_CODE"));
				ingredient.setiName(rset.getString("INGREDIENT_NAME"));
				ingredient.setiCapacity(rset.getInt("INGREDIENT_CAPACITY"));
				ingredient.setiUnit(rset.getString("INGREDIENT_UNIT"));
				ingredient.setBranchInven(rset.getInt("HOLD_QUANTITY"));
				
				list.add(ingredient);
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

	public ArrayList<IngredientListBR> selectListWithiCode(Connection con, String searchValue, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<IngredientListBR> list = null;
		
		String query = prop.getProperty("selectListWithiCode");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			pstmt.setString(2, searchValue);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<IngredientListBR>();
			while(rset.next()) {
				IngredientListBR ingredient = new IngredientListBR();
				
				ingredient.setiClassName(rset.getString("INGREDIENT_CLASS_NAME"));
				ingredient.setiCode(rset.getString("INGREDIENT_CODE"));
				ingredient.setiName(rset.getString("INGREDIENT_NAME"));
				ingredient.setiCapacity(rset.getInt("INGREDIENT_CAPACITY"));
				ingredient.setiUnit(rset.getString("INGREDIENT_UNIT"));
				ingredient.setBranchInven(rset.getInt("HOLD_QUANTITY"));
				
				list.add(ingredient);
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

	//지점 발주 목록 insert메소드
	public int insertPList(Connection con, PurchaseListBR purchase) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = prop.getProperty("insertPList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, purchase.getIngredientCode());
			pstmt.setInt(2, purchase.getPurchseQuantity());
			pstmt.setString(3, purchase.getPurchaseUnit());
			pstmt.setString(4, purchase.getBranchCode());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public ArrayList<HashMap<String, Object>> selectPurchaseList(Connection con, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<HashMap<String, Object>> list = null;
		
		String query = prop.getProperty("selectPurchaseList");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<HashMap<String, Object>>();
			
			while(rset.next()) {
				HashMap<String, Object> hmap = new HashMap<String, Object>();
				hmap.put("pCode", rset.getInt("BRANCH_PURCHASE_CODE"));
				hmap.put("iCode", rset.getString("INGREDIENT_CODE"));
				hmap.put("iName", rset.getString("INGREDIENT_NAME"));
				hmap.put("pNum", rset.getInt("PURCHASE_QUANTITY"));
				hmap.put("pUnit", rset.getString("PURCHASE_UNIT"));
				hmap.put("bCode", rset.getString("BRANCH_CODE"));
				
				list.add(hmap);		
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

	public List<Integer> selectPCode(Connection con, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Integer> numlist = null;
		
		String query = prop.getProperty("selectPCode");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			
			rset = pstmt.executeQuery();
			numlist = new ArrayList<>();
			while(rset.next()) {
				numlist.add(rset.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}	
		
		return numlist;
	}

	public ArrayList<PurchaseListBR> listOrderByPCode(Connection con, String branchCode, int purCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> pListing = null;
		
		String query = prop.getProperty("listOrderByPCode");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			pstmt.setInt(2, purCode);
			
			rset = pstmt.executeQuery();
			
			pListing = new ArrayList<>();
			while(rset.next()) {
				PurchaseListBR plbr = new PurchaseListBR();
				plbr.setPurchaseCode(rset.getInt("BRANCH_PURCHASE_CODE"));
				plbr.setIngredientCode(rset.getString("INGREDIENT_CODE"));
				plbr.setiClassName(rset.getString("INGREDIENT_CLASS_NAME"));
				plbr.setIngredientName(rset.getString("INGREDIENT_NAME"));
				plbr.setPurchseQuantity(rset.getInt("PURCHASE_QUANTITY"));
				plbr.setPurchaseUnit(rset.getString("PURCHASE_UNIT"));
				plbr.setBranchCode(rset.getString("BRANCH_CODE"));
				
				pListing.add(plbr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return pListing;
	}

	public String getSupList(Connection con, Object object) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String str =""; 
//		ArrayList getSupList = null;
		
		String query = prop.getProperty("getSupList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, (String) object);
			rset = pstmt.executeQuery();
			
//			getSupList = new ArrayList();
			while(rset.next()) {
				
				str = rset.getString(1);
//				getSupList.add(rset.getString(1));
			}
			
			//System.out.println("getSupList 확인 : " + getSupList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return str;
	}


	public int selectNextVal(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		
		int seq = 0;
		
		String query = prop.getProperty("selectNextval");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				seq = rset.getInt("NEXTVAL");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
	
		return seq;
	}

	public int insertIntoDoc(Connection con, Object next, int seq) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertIntoDoc");
		
		try {
			pstmt = con.prepareStatement(query);
			/*pstmt.setInt(1, seq);*/
			//pstmt.setString(2, next.toString()); 
			pstmt.setString(1, next.toString()); 
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		
		return result;
	}

	public ArrayList<Supplier> selectSupList(Connection con) {
		Statement stmt = null; 
		ResultSet rset = null;
		ArrayList<Supplier> list = null;
		
		String query = prop.getProperty("selectSupList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Supplier>();
			
			while(rset.next()) {
				Supplier supplier = new Supplier();
				supplier.setsCode(rset.getString("SUPPLIER_CODE"));
				supplier.setcName(rset.getString("CUSTOM_NAME"));
				supplier.setrName(rset.getString("SUPPLIER_REP"));
				supplier.setsAddress(rset.getString("SUPPLIER_ADDRESS"));
				supplier.setsPhone(rset.getString("SUPPLIER_PHONE"));
				supplier.setsNum(rset.getString("SUPPLIER_NUMBER"));
				supplier.setEnrollDate(rset.getString("SUPPLIER_ENROLL_DATE"));
				supplier.setsEmail(rset.getString("SUPPLIER_EMAIL"));
				supplier.setsBank(rset.getString("SUPPLIER_ACCOUNT_BANK"));
				supplier.setsAccount(rset.getString("SUPPLIER_ACCOUNT_NUM"));
				supplier.setsFax(rset.getString("SUPPLIER_FAX"));

				list.add(supplier);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return list;
		
	}

	public int insertSupplier(Connection con, Supplier supplier) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertSupList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, supplier.getsCode());
			pstmt.setString(2, supplier.getcName());
			pstmt.setString(3, supplier.getrName());
			pstmt.setString(4, supplier.getsPhone());
			pstmt.setString(5, supplier.getsAddress());
			pstmt.setString(6, supplier.getsNum());
			pstmt.setString(7, supplier.getsEmail());
			pstmt.setString(8, supplier.getsBank());
			pstmt.setString(9, supplier.getsAccount());
			pstmt.setString(10, supplier.getsFax());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}	
		
		return result;
	}

	public int updateSupplier(Connection con, Supplier editSupplier) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateSupplier");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, editSupplier.getcName());
			pstmt.setString(2, editSupplier.getrName());
			pstmt.setString(3, editSupplier.getsPhone());
			pstmt.setString(4, editSupplier.getsAddress());
			pstmt.setString(5, editSupplier.getsNum());
			pstmt.setString(6, editSupplier.getsEmail());
			pstmt.setString(7, editSupplier.getsBank());
			pstmt.setString(8, editSupplier.getsAccount());
			pstmt.setString(9, editSupplier.getsFax());
			pstmt.setString(10, editSupplier.getsCode());
			
			result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			
		}

		return result;
	}
	
	//branch_purchase_document에 insert하는 메소드
	public int insertIntoDoc(Connection con, String docCode) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = prop.getProperty("insertIntoDoc");
		
		try {
			pstmt = con.prepareStatement(query);
			 
			pstmt.setString(1, docCode); 
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		//System.out.println("docCode check in dao: " + docCode );
		
		return result;
	}

	//branch_purchase_history에 발주한 재료 insert하기 위해
	//branch_purchase_document에서 발주번호 가져오기
	public ArrayList<Integer> selectPCodeforInsert(Connection con, int isize) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Integer> pCode = null;
		
		String query = prop.getProperty("selectPCodeforInsert");
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, isize);
			
			rset = pstmt.executeQuery();
			pCode = new ArrayList<Integer>();

			while(rset.next()) {
				pCode.add(rset.getInt(1));			
			}		
			//System.out.println("pCode check in dao : " + pCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return pCode;
	}

	//branch_purchase_history에 발주한 재료 insert
	public int insertPList(Connection con, PurchaseListBR purchase, Integer pCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertPList");
		
		try {
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, pCode);
			pstmt.setString(2, purchase.getIngredientCode());
			pstmt.setInt(3, purchase.getPurchseQuantity());
			pstmt.setString(4, purchase.getPurchaseUnit());
			pstmt.setString(5, purchase.getBranchCode());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	//지점 발주내역에 띄울 대표 재료명, 발주일자 등 가져오는 메소드
	public ArrayList<PurchaseListBR> selectRepiName(Connection con, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> repList = null;
		
		String query = prop.getProperty("selectRepiName");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			pstmt.setString(2, branchCode);
			
			rset = pstmt.executeQuery();
			
			repList = new ArrayList<PurchaseListBR>();
			while(rset.next()) {
				
				//지점별 주문 순번, 주문날짜, 대표재료이름(발주내역 첫페이지에 나오는) 담을 객체 생성
				PurchaseListBR purchase = new PurchaseListBR();
				purchase.setRowNum(rset.getInt("RNUM"));
				purchase.setPurchaseDate(rset.getString("BRANCH_DATE"));
				purchase.setiNameRep(rset.getString("INGREDIENT_NAME"));
				purchase.setCnt(rset.getInt("CNT"));
				
				repList.add(purchase);
			}
			System.out.println("repList check in dao : " + repList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return repList;
	}

	public ArrayList<PurchaseListBR> selectpListWithDate(Connection con, String bCode, String dateTime,
			String oneMinLater) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> list = null;
		
		String query = prop.getProperty("selectpListWithDate");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dateTime);
			pstmt.setString(2, oneMinLater);
			pstmt.setString(3, bCode);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				PurchaseListBR purchase = new PurchaseListBR();
				purchase.setIngredientCode(rset.getString("INGREDIENT_CODE"));
				purchase.setiClassName(rset.getString("INGREDIENT_CLASS_NAME"));
				purchase.setIngredientName(rset.getString("INGREDIENT_NAME"));
				purchase.setPurchseQuantity(rset.getInt("PURCHASE_QUANTITY"));
				purchase.setBranchCode(rset.getString("BRANCH_CODE"));
				
				list.add(purchase);
			}
			System.out.println("특정 일자로 list 상세조회 확인 : " + list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;

	}

	public ArrayList<PurchaseListBR> selectAllBRpList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> list = null;
		
		String query = prop.getProperty("selectAllBRpList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
				list = new ArrayList<PurchaseListBR>();
			while(rset.next()) {
				PurchaseListBR purchase = new PurchaseListBR();
				
				purchase.setRowNum(rset.getInt("RNUM"));
				purchase.setPurchaseDate(rset.getString("BRANCH_DATE"));
				purchase.setBranchCode(rset.getString("BRANCH_CODE"));
				purchase.setBranchName(rset.getString("BRANCH_NAME"));
				purchase.setiNameRep(rset.getString("INGREDIENT_NAME"));
				purchase.setCnt(rset.getInt("CNT"));
				
				list.add(purchase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}

	public ArrayList<PurchaseListBR> selectAllpListWithDate(Connection con, String dateTime, String oneMinLater) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> list = null;
		
		String query = prop.getProperty("selectAllpListWithDate");
		
		try {
			pstmt = con.prepareStatement(query);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return list;
	}

	//거래처 삭제
	public int deleteSupplier(Connection con, String sCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteSupplier");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sCode);
		
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return result;
	}

	public ArrayList<String> selectSupListHasOrder(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<String> supList = null;
		
		String query = prop.getProperty("selectSupListHasOrder");
		
		supList = new ArrayList<String>();
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				supList.add(rset.getString(1));
			}
			System.out.println("supList check in dao : " + supList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return supList;
	}

	public ArrayList<SupplierOrder> SelectPuListSortBySupplier(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> puList = null;
		
		String query = prop.getProperty("SelectPuListSortBySupplier");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			puList = new ArrayList<>();
			while(rset.next()) {
				
				SupplierOrder sup = new SupplierOrder();
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setiName(rset.getString("INGREDIENT_NAME"));
				sup.setCnt(rset.getInt("CNT"));
				sup.setPrice(rset.getLong("ALL_PRICE"));
				System.out.println("sup: 확인 " + sup);
				puList.add(sup);
			}
			//System.out.println("puList 확인 : " + puList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}

		return puList;
	}

	public ArrayList<SupplierOrder> selectOneSup(Connection con, String sCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectOneSup");
		
			list = new ArrayList<SupplierOrder>();
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sCode);
			
			rset = pstmt.executeQuery();
			System.out.println("sCode 확인 " + sCode);
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setiCode(rset.getString("INGREDIENT_CODE"));
				sup.setiName(rset.getString("INGREDIENT_NAME"));
				sup.setCnt(rset.getInt("PURCHASE_NUM"));
				sup.setiCapacity(rset.getInt("INGREDIENT_CAPACITY"));
				sup.setiUnit(rset.getString("INGREDIENT_UNIT"));
				sup.setPrice(rset.getLong("NORMAL_PRICE"));
				sup.setSumPrice(rset.getLong("ALL_PRICE"));
				
				list.add(sup);
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

	public ArrayList<PurchaseListBR> selectOneIng(Connection con, String sCode, String iCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> list = null;
		
		String query = prop.getProperty("selectOneIng");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sCode);
			pstmt.setString(2, iCode);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<PurchaseListBR>();
			while(rset.next()) {
				PurchaseListBR purchase = new PurchaseListBR();
				purchase.setBranchCode(rset.getString("BRANCH_CODE"));
				purchase.setBranchName(rset.getString("BRANCH_NAME"));
				purchase.setPurchseQuantity(rset.getInt("ALL_AMOUNT"));
				
				list.add(purchase);			
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

	public ArrayList<PurchaseListBR> selectBPcodePrice(Connection con, String arr) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> list = null;
		
		//System.out.println("arr확인 in dao " + arr);
		String query = prop.getProperty("selectBPcodePrice");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, arr);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<PurchaseListBR>();

			while(rset.next()) {
				PurchaseListBR purchase = new PurchaseListBR();
				purchase.setPurchaseCode(rset.getInt("BRANCH_PURCHASE_CODE"));
				purchase.setPrice(rset.getLong("ALL_PRICE"));
				
			//	System.out.println("purchase 확인 : " + purchase);
				list.add(purchase);
			}
			
			//System.out.println("list 확인 in dao " + list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int insertRegisterHO(Connection con, int hoSeq, int bpCode, long price) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("insertRegisterHO");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, hoSeq);
			pstmt.setInt(2, bpCode);
			pstmt.setLong(3, price);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	
	//지점 주문 재료 주문상태 y로 바꾸기
	public int updateOrderStatus(Connection con, int bpCode) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("updateOrderStatus");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, bpCode);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectNextValforInsert(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int hoSeq = 0;
		
		String query = prop.getProperty("selectNextValforInsert");
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				hoSeq = rset.getInt("NEXTVAL");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
	
		return hoSeq;

	}

	public ArrayList<SupplierOrder> selectHOpuList(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectHOpuList");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<SupplierOrder>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
						
				list.add(sup);
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

	public ArrayList<PurchaseListBR> selectHOpuListDetail(Connection con, String pCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> list = null;
		
		String query = prop.getProperty("selectHOpuListDetail");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(pCode));
			
			rset = pstmt.executeQuery();
				list = new ArrayList<PurchaseListBR>();
			while(rset.next()) {
				PurchaseListBR plist = new PurchaseListBR();
				plist.setBranchCode(rset.getString("BRANCH_CODE"));
				plist.setBranchName(rset.getString("BRANCH_NAME"));
				plist.setIngredientName(rset.getString("INGREDIENT_NAME"));
				plist.setPurchseQuantity(rset.getInt("PURCHASE_QUANTITY"));
				
				list.add(plist);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}		
		
		return list;
	}

	public ArrayList<SupplierOrder> selectPuListWithSupName(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectPuListWithSupName");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				
				list.add(sup);
				
			}
			//System.out.println("list확인 in dao : " + list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<SupplierOrder> selectPuListWithSupCode(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectPuListWithSupCode");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				
				list.add(sup);
				
			}
			//System.out.println("list확인 in dao : " + list);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public ArrayList<Supplier> selectSupplierWithSupName(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Supplier> list = null;
		
		String query = prop.getProperty("selectSupplierWithSupName");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Supplier>();
			
			while(rset.next()) {
				Supplier supplier = new Supplier();
				supplier.setsCode(rset.getString("SUPPLIER_CODE"));
				supplier.setcName(rset.getString("CUSTOM_NAME"));
				supplier.setrName(rset.getString("SUPPLIER_REP"));
				supplier.setsAddress(rset.getString("SUPPLIER_ADDRESS"));
				supplier.setsPhone(rset.getString("SUPPLIER_PHONE"));
				supplier.setsNum(rset.getString("SUPPLIER_NUMBER"));
				supplier.setEnrollDate(rset.getString("SUPPLIER_ENROLL_DATE"));
				supplier.setsEmail(rset.getString("SUPPLIER_EMAIL"));
				supplier.setsBank(rset.getString("SUPPLIER_ACCOUNT_BANK"));
				supplier.setsAccount(rset.getString("SUPPLIER_ACCOUNT_NUM"));
				supplier.setsFax(rset.getString("SUPPLIER_FAX"));
				
				list.add(supplier);
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

	public ArrayList<Supplier> selectSupplierSupCode(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Supplier> list = null;
		
		String query = prop.getProperty("selectSupplierSupCode");
		
		
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<Supplier>();
			
			while(rset.next()) {
				Supplier supplier = new Supplier();
				supplier.setsCode(rset.getString("SUPPLIER_CODE"));
				supplier.setcName(rset.getString("CUSTOM_NAME"));
				supplier.setrName(rset.getString("SUPPLIER_REP"));
				supplier.setsAddress(rset.getString("SUPPLIER_ADDRESS"));
				supplier.setsPhone(rset.getString("SUPPLIER_PHONE"));
				supplier.setsNum(rset.getString("SUPPLIER_NUMBER"));
				supplier.setEnrollDate(rset.getString("SUPPLIER_ENROLL_DATE"));
				supplier.setsEmail(rset.getString("SUPPLIER_EMAIL"));
				supplier.setsBank(rset.getString("SUPPLIER_ACCOUNT_BANK"));
				supplier.setsAccount(rset.getString("SUPPLIER_ACCOUNT_NUM"));
				supplier.setsFax(rset.getString("SUPPLIER_FAX"));
				
				list.add(supplier);
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

	public ArrayList<Branch> selectAllBranch(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Branch> bList = null;
		
		String query = prop.getProperty("selectAllBranch");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			bList = new ArrayList<Branch>();
			
			while(rset.next()) {
				Branch branch = new Branch();
				branch.setBranchCode(rset.getString("BRANCH_CODE"));
				branch.setBranchName(rset.getString("BRANCH_NAME"));
				
				bList.add(branch);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		return bList;
	}

	public ArrayList<PurchaseListBR> selectHistoryWithbName(Connection con, String bName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> list = null;
		
		String query = prop.getProperty("selectHistoryWithbName");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bName);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<PurchaseListBR>();
			while(rset.next()) {
				PurchaseListBR purchase = new PurchaseListBR();
				
				purchase.setRowNum(rset.getInt("RNUM"));
				purchase.setPurchaseDate(rset.getString("BRANCH_DATE"));
				purchase.setBranchCode(rset.getString("BRANCH_CODE"));
				purchase.setBranchName(rset.getString("BRANCH_NAME"));
				purchase.setiNameRep(rset.getString("INGREDIENT_NAME"));
				purchase.setCnt(rset.getInt("CNT"));
				
				list.add(purchase);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public String selectbNameWithbCode(Connection con, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String branchName = null;
		
		String query = prop.getProperty("selectbNameWithbCode");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
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

	public ArrayList<PurchaseListBR> selectRepiNameWithDate(Connection con, String branchCode, String startDay,
			String endDay) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PurchaseListBR> repList = null;
		
		String query = prop.getProperty("selectRepiNameWithDate");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchCode);
			pstmt.setString(2, startDay);
			pstmt.setString(3, endDay);
			pstmt.setString(4, branchCode);
			System.out.println(branchCode);
			System.out.println(startDay);
			System.out.println(endDay);
			rset = pstmt.executeQuery();
			
			repList = new ArrayList<PurchaseListBR>();
			while(rset.next()) {
				
				//지점별 주문 순번, 주문날짜, 대표재료이름(발주내역 첫페이지에 나오는) 담을 객체 생성
				PurchaseListBR purchase = new PurchaseListBR();
				purchase.setRowNum(rset.getInt("RNUM"));
				purchase.setPurchaseDate(rset.getString("BRANCH_DATE"));
				purchase.setiNameRep(rset.getString("INGREDIENT_NAME"));
				purchase.setCnt(rset.getInt("CNT"));
				
				repList.add(purchase);
				
			}
			System.out.println("repList확인 : " + repList);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return repList;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithDate(Connection con, String startDay, String endDay) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectHOpuListWithDate");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SupplierOrder>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
						
				list.add(sup);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithDateSearch(Connection con, String startDay, String endDay,
			String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectHOpuListWithDateSearch");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setString(3, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SupplierOrder>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
						
				list.add(sup);
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

	public ArrayList<SupplierOrder> selectHOpuListWithSearch(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectHOpuListWithSearch");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SupplierOrder>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
						
				list.add(sup);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithDateSearchCode(Connection con, String startDay, String endDay,
			String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectHOpuListWithDateSearchCode");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, startDay);
			pstmt.setString(2, endDay);
			pstmt.setString(3, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SupplierOrder>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
						
				list.add(sup);
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

	public ArrayList<SupplierOrder> selectHOpuListWithSearchCode(Connection con, String searchValue) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<SupplierOrder> list = null;
		
		String query = prop.getProperty("selectHOpuListWithSearchCode");
		try {

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, searchValue);
			
			rset = pstmt.executeQuery();
			
			list = new ArrayList<SupplierOrder>();
			while(rset.next()) {
				SupplierOrder sup = new SupplierOrder();
				
				sup.setRownum(rset.getInt("ROWNUM"));
				sup.setHoPurchaseCode(rset.getInt("PURCHASE_CODE"));
				sup.setSupCode(rset.getString("SUPPLIER_CODE"));
				sup.setSupName(rset.getString("CUSTOM_NAME"));
				sup.setHoPurchaseDate(rset.getString("ORDER_DATE"));
						
				list.add(sup);
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
