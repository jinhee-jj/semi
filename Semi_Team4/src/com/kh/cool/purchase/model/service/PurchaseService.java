package com.kh.cool.purchase.model.service;

import static com.kh.cool.common.JDBCTemplate.close;
import static com.kh.cool.common.JDBCTemplate.commit;
import static com.kh.cool.common.JDBCTemplate.getConnection;
import static com.kh.cool.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.kh.cool.purchase.model.dao.PurchaseDao;
import com.kh.cool.purchase.model.vo.Branch;
import com.kh.cool.purchase.model.vo.IngredientListBR;
import com.kh.cool.purchase.model.vo.PurchaseListBR;
import com.kh.cool.purchase.model.vo.Supplier;
import com.kh.cool.purchase.model.vo.SupplierIngredient;
import com.kh.cool.purchase.model.vo.SupplierOrder;

public class PurchaseService {

	public ArrayList<SupplierIngredient> selectIngredientList(String branchCode) {
		Connection con = getConnection();
		
		ArrayList<SupplierIngredient> list1 = new PurchaseDao().selectIngredientList(con, branchCode);
		
		close(con);
		
		return list1;
	}

	//재료명으로 검색시 리스트 호출 메소드
	public ArrayList<IngredientListBR> selectListhWithiName(String searchValue, String branchCode) {
		Connection con = getConnection();
		
		ArrayList<IngredientListBR> list = new PurchaseDao().selectListWithiName(con, searchValue, branchCode);
		
		close(con);
		
		return list;
	}

	//재료코드로 검색시 리스트 호출 메소드
	public ArrayList<IngredientListBR> selectListhWithiCode(String searchValue, String branchCode) {
		Connection con = getConnection();
		
		ArrayList<IngredientListBR> list = new PurchaseDao().selectListWithiCode(con, searchValue, branchCode);
		
		close(con);
		
		return list;
	}


	//지점 발주 목록 insert메소드
	public int insertPList(ArrayList<PurchaseListBR> pList, int isize) {
		Connection con = getConnection();
		int result = 0;
		
		PurchaseDao pd = new PurchaseDao();
		
		//insert전에 document 테이블에서 발주번호 순서 가져오기 (재료품목 갯수만큼)
		ArrayList<Integer> pCode = pd.selectPCodeforInsert(con, isize);
		
		for(int i = 0; i < pCode.size(); i++) {
			PurchaseListBR purchase = pList.get(i);
			
			int result1 = pd.insertPList(con, purchase, pCode.get(i));
			result += result1;			
		}
			
		if(result == pList.size()) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<HashMap<String, Object>> selectPurchaseListBR(String branchCode) {
		Connection con = getConnection();
		
		ArrayList<HashMap<String, Object>> list = new PurchaseDao().selectPurchaseList(con, branchCode);
		
		close(con);
		
		return list;
	}

	public List<Integer> selectPCode(String branchCode) {
		Connection con = getConnection();
		
		List<Integer> numList = new PurchaseDao().selectPCode(con, branchCode);
		
		close(con);
		
		return numList;
	}

	public ArrayList<PurchaseListBR> listOrderByPCode(String branchCode, int purCode) {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> pListing = new PurchaseDao().listOrderByPCode(con, branchCode, purCode);
		
		close(con);
		
		return pListing;
	}

	public String[] getSupList(ArrayList ilist) {
		Connection con = getConnection();

		String[] strr = new String[ilist.size()];
		
		for(int i = 0; i < ilist.size(); i++) {
		
			strr[i] = new PurchaseDao().getSupList(con, ilist.get(i));
		}
		close(con);

		return strr;
	}

	//지점 발주내역 등록 전에 supplier_document에 insert하는 메소드
	public int insertIntoDoc(ArrayList<String> docList) {
		Connection con = getConnection();
		int result = 0;

		int result1 = 0;
		ArrayList list = new ArrayList();
		
		for(int i = 0; i < docList.size(); i++) {
			result1 += new PurchaseDao().insertIntoDoc(con, docList.get(i));
			//System.out.println("service에서 docList.get(i) " + docList.get(i));
		}
			
		if(result1 == docList.size()) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<Supplier> selectSupListAll() {
		Connection con = getConnection();
		
		ArrayList<Supplier> list = new PurchaseDao().selectSupList(con);
		
		close(con);
		
		return list;
	}

	public int insertSupplier(Supplier supplier) {
		Connection con = getConnection();
		
		int result = new PurchaseDao().insertSupplier(con, supplier);
		
		if(result > 0) {
			commit(con); 
		}else {
			rollback(con);
		}
		
		return result;
	}

	public int updateSupplier(Supplier editSupplier) {
		Connection con = getConnection();
		Supplier updateSupplier = null;
		
		//System.out.println("service editSpplier	: " + editSupplier);
		int result = new PurchaseDao().updateSupplier(con, editSupplier);
		
		
		if (result > 0) {
			commit(con);

		} else {
			rollback(con);
		}
		close(con);
		
		return result;
	}
	
	//발주내역 첫페이지에 띄울 재료명과 재료갯수 가져오기
	public ArrayList<PurchaseListBR> selectRepiName(String branchCode) {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> repList = new PurchaseDao().selectRepiName(con, branchCode);
		
		close(con);
		
		return repList;
	}

	//발주일자로 발주내역 상세조회 가져오기
	public ArrayList<PurchaseListBR> selectpListWithDate(String bCode, String dateTime, String oneMinLater) {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> list = new PurchaseDao().selectpListWithDate(con, bCode, dateTime, oneMinLater);
		
		close(con);
		
		return list;
	}

	public ArrayList<PurchaseListBR> selectAllBRpList() {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> list = new PurchaseDao().selectAllBRpList(con);
		
		close(con);
		
		return list;
	}

	//거래처 삭제 메소드
	public int deleteSupplier(String[] sCodes) {
		Connection con = getConnection();
		
		int result = 0;
		for(int i = 0; i < sCodes.length; i++) {
			result += new PurchaseDao().deleteSupplier(con, sCodes[i]);
			
		}
		
	//	System.out.println("sCode check in service : " + sCode);
		
		if(result == sCodes.length ) {
			commit(con);
			result = 1;
		} else {
			rollback(con);
		}

		return result;
	}

	public ArrayList<String> selectSuplistHasOrder() {
		Connection con = getConnection();
		
		ArrayList<String> supList = new PurchaseDao().selectSupListHasOrder(con);
		
		close(con);
				
		return supList;
	}

	//본사 발주 등록에 뜨는 거래처별 발주해야할 목록 조회
	public ArrayList<SupplierOrder> SelectPuListSortBySupplier() {
		Connection con = getConnection();
		ArrayList<SupplierOrder> puList = new PurchaseDao().SelectPuListSortBySupplier(con);
		close(con);
		
		
		return puList;
	}

	//거래처별 발주해야할 목록에서 거래처 선택하여 상세조회 
	public ArrayList<SupplierOrder> selectOneSup(String sCode) {
		Connection con = getConnection();
		ArrayList<SupplierOrder> list = new PurchaseDao().selectOneSup(con, sCode);
		
		close(con);
		
		return list;
	}

	//거래처 선택한 상세조회에서 재료 선택하여 지점별 수량 조회
	public ArrayList<PurchaseListBR> selectOneIng(String sCode, String iCode) {
		Connection con = getConnection();
		ArrayList<PurchaseListBR> list = new PurchaseDao().selectOneIng(con, sCode, iCode);
		
		close(con);
		
		return list;
	}

	//본사 발주 등록
	public int insertRegisterHO(String[] arr) {
		Connection con = getConnection();
		
		PurchaseDao pd = new PurchaseDao();
		int result = 0;
		//여러 주문 넣을 본사의 다음 시퀀스번호 구하기
		int hoSeq = pd.selectNextValforInsert(con);
	
		for(int i = 0; i < arr.length; i++) {
			//System.out.println("arr확인 in service " + arr[i]);
			ArrayList<PurchaseListBR> list = pd.selectBPcodePrice(con, arr[i]);
			int result1 = 0;
			
			//주문상태가 'N'이고 지점코드가 jsp에서 받은 배열값 안에 있는 지점발주목록(지점발주번호, 가격) 가져와서 이 시퀀스 번호로 넣기
			for(int j = 0; j < list.size(); j++) {
				int bpCode = list.get(j).getPurchaseCode();
				long price = list.get(j).getPrice();
				
			//	System.out.println("bpCode 와 price 와 hoSeq : " + bpCode +"와"+ price + "hoSeq");
				result1 += pd.insertRegisterHO(con, hoSeq, bpCode, price);
			}
		
		
			if(result1 == list.size()) {
				
				//지점 발주번호에 해당하는 제품 발주상태 'y' 를 'n'으로 update 
				int result2 = 0;
				for(int k = 0; k < list.size(); k++) {
					int bpCode = list.get(k).getPurchaseCode();
					
					result2 += pd.updateOrderStatus(con, bpCode); 
				}
				
				if(result2 == list.size()) {
					commit(con);
					result = 1;
				} else {
					rollback(con);
				}
				
			} else {
				rollback(con);
			}
		}
	
		
		return result;
	}
	
	//본사 발주내역 조회
	public ArrayList<SupplierOrder> selectHOpuList() {
		Connection con = getConnection();
		
		ArrayList<SupplierOrder> list = new PurchaseDao().selectHOpuList(con);
		
		close(con);
		
		
		return list;
	}

	//본사 발주내역에서 상세조회
	public ArrayList<PurchaseListBR> selectHOpuListDetail(String pCode) {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> list = new PurchaseDao().selectHOpuListDetail(con, pCode);
		
		close(con);
			
		return list;
	}

	//거래처명으로 검색시(본사-발주내역에서)
	public ArrayList<SupplierOrder> selectPuListWithSupName(String searchValue) {
		Connection con = getConnection();
		
		ArrayList<SupplierOrder> list = new PurchaseDao().selectPuListWithSupName(con, searchValue);
		
		close(con);
		
		return list;
	}

	//거래처코드로 검색시(본사-발주내역에서)
	public ArrayList<SupplierOrder> selectPuListWithSupCode(String searchValue) {
	
		Connection con = getConnection();
	
		ArrayList<SupplierOrder> list = new PurchaseDao().selectPuListWithSupCode(con, searchValue);

		close(con);
		
		return list;
	}

	public ArrayList<Supplier> selectSupplierWithSupName(String searchValue) {
		Connection con = getConnection();
		
		ArrayList<Supplier> list = new PurchaseDao().selectSupplierWithSupName(con, searchValue);
		
		close(con);
		
		return list;
	}

	public ArrayList<Supplier> selectSupplierSupCode(String searchValue) {
		Connection con = getConnection();
		
		ArrayList<Supplier> list = new PurchaseDao().selectSupplierSupCode(con, searchValue);
		
		close(con);
		
		return list;
	}

	public ArrayList<Branch> selectAllBranch() {
		Connection con = getConnection();
		
		ArrayList<Branch> bList = new PurchaseDao().selectAllBranch(con);
		
		close(con);

		return bList;
	}

	public ArrayList<PurchaseListBR> selectHistoryWithbName(String bName) {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> list = new PurchaseDao().selectHistoryWithbName(con, bName);
		
		close(con);

		return list;
	}

	public String selectbNameWithbCode(String branchCode) {
		Connection con = getConnection();
		
		String branchName = new PurchaseDao().selectbNameWithbCode(con, branchCode);
		close(con);

		return branchName;
	}

	public ArrayList<PurchaseListBR> selectRepiNameWithDate(String branchCode, String startDay, String endDay) {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> repList = new PurchaseDao().selectRepiNameWithDate(con, branchCode, startDay, endDay);

		close(con);
		
		return repList;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithDate(String startDay, String endDay) {
		Connection con = getConnection();
		
		ArrayList<SupplierOrder> list = new PurchaseDao().selectHOpuListWithDate(con, startDay, endDay);
		
		close(con);
		return list;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithDateSearch(String startDay, String endDay, String searchValue) {
		Connection con = getConnection();
		
		ArrayList<SupplierOrder> list = new PurchaseDao().selectHOpuListWithDateSearch(con, startDay, endDay, searchValue);
		
		close(con);
		return list;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithSearch(String searchValue) {
		Connection con = getConnection();
		
		ArrayList<SupplierOrder> list = new PurchaseDao().selectHOpuListWithSearch(con, searchValue);
		
		close(con);
		return list;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithDateSearchCode(String startDay, String endDay,
			String searchValue) {
		Connection con = getConnection();
		
		ArrayList<SupplierOrder> list = new PurchaseDao().selectHOpuListWithDateSearchCode(con, startDay, endDay, searchValue);
		
		close(con);
		return list;
	}

	public ArrayList<SupplierOrder> selectHOpuListWithSearchCode(String searchValue) {
		Connection con = getConnection();
		
		ArrayList<SupplierOrder> list = new PurchaseDao().selectHOpuListWithSearchCode(con, searchValue);
		
		close(con);
		return list;
	}




}
