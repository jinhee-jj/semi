package com.kh.cool.management.model.dao;

import static com.kh.cool.common.JDBCTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.cool.management.model.vo.Paper;
import com.kh.cool.management.model.vo.PaperPurchase;
import com.kh.cool.member.model.vo.Member;

public class MemberDao {

	public Properties prop = new Properties();
	
	public MemberDao() {
											
													//properties 작성하고 경로넣기.
		String fileName = MemberDao.class.getResource("/sql/management/management-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public int memberInsert(Connection con, Member newMember) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("memberInsert");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newMember.getMemberId());
			pstmt.setString(2, newMember.getMemberName());
			pstmt.setString(3, newMember.getDeptCode());
			pstmt.setString(4, newMember.getMemberPwd());
			pstmt.setString(5, newMember.getMemberEmail());
			pstmt.setString(6, newMember.getMemberPhone());
			pstmt.setString(7, newMember.getMemberDeptCode());
			
			result = pstmt.executeUpdate();
			
			
			/*System.out.println("MEMBERDAO query : " + query);
			System.out.println("MEMBERDAO pstmt : " + pstmt);
			System.out.println("MEMBERDAO result(성공1) : " + result);*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public ArrayList<Member> memberSelectAll(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		//System.out.println("memberSelectAllDao");
		String query = prop.getProperty("memberSelectAll");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member memberSelectList = new Member();
				
				memberSelectList.setMemberId(rset.getString("MEMBER_ID"));
				memberSelectList.setMemberName(rset.getString("MEMBER_NAME"));
				memberSelectList.setMemberPhone(rset.getString("MEMBER_PHONE"));
				memberSelectList.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				memberSelectList.setDeptCode(rset.getString("DEPT_CODE"));
				memberSelectList.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
				list.add(memberSelectList);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}


	public int updateMember(Connection con, Member memberUpdate) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("memberUpdate");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberUpdate.getDeptCode());
			pstmt.setString(2, memberUpdate.getMemberDeptCode());
			pstmt.setString(3, memberUpdate.getMemberId());


			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	public ArrayList<Member> selectChangedMemberInformation(Connection con, Member memberUpdate) {

		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = prop.getProperty("memberSelectAll");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<Member>();
			while(rset.next()) {
				Member memberSelectList = new Member();
				
				memberSelectList.setMemberId(rset.getString("MEMBER_ID"));
				memberSelectList.setMemberName(rset.getString("MEMBER_NAME"));
				memberSelectList.setMemberPhone(rset.getString("MEMBER_PHONE"));
				memberSelectList.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				memberSelectList.setDeptCode(rset.getString("DEPT_CODE"));
				memberSelectList.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
				list.add(memberSelectList);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}


	public int updateMemberAll(Connection con, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("memberUpdate");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getDeptCode());
			pstmt.setString(2, member.getMemberDeptCode());
			pstmt.setString(3, member.getMemberId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}




/*	public int deleteMember(Connection con, String memberId) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}*/


	public int deleteMemberAll(Connection con, Member member) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, member.getMemberId());

			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}

		return result;
	}




	public int meUpdateInfo(Connection con, Member myInfo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		
			//System.out.println("memberDao myInfo확인용" + myInfo);
		String query = prop.getProperty("myInfo");
		//System.out.println("query : " +query);
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, myInfo.getMemberPwd());
			pstmt.setString(2, myInfo.getMemberEmail());
			pstmt.setString(3, myInfo.getMemberPhone());
			pstmt.setString(4, myInfo.getMemberId());
			
			result = pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
			//System.out.println("memberDao meUpdateInfo result : " + result);
		return result;
	}


	public Member updateInfoMe(Connection con, Member myInfo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member meUpdate  = null;
		
			//System.out.println("myInfo.getMemberId : "  + myInfo.getMemberId());
			//System.out.println("myInfo.getMemberPwd : "  + myInfo.getMemberPwd());
			
		String query = prop.getProperty("selectMeInfo");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, myInfo.getMemberId());
			pstmt.setString(2, myInfo.getMemberPwd());
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				meUpdate  = new Member();
				
				meUpdate .setMemberId(rset.getString("MEMBER_ID"));
				meUpdate .setMemberName(rset.getString("MEMBER_NAME"));
				meUpdate .setDeptCode(rset.getString("DEPT_CODE"));
				meUpdate .setMemberPwd(rset.getString("MEMBER_PWD"));
				meUpdate .setMemberEmail(rset.getString("MEMBER_EMAIL"));
				meUpdate .setMemberPhone(rset.getString("MEMBER_PHONE"));
				meUpdate .setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		
			//System.out.println("memberDao meUpdate : " + meUpdate);
		return meUpdate ;
	}


	public ArrayList<Member> deptSearch(Connection con, String radioSearch, String selectSearch, String searchText) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query="";
		if(radioSearch.equals("findId")) {
			if(selectSearch.equals("memberName")) {
				query=prop.getProperty("");
			}else if(selectSearch.equals("memberDeptCode")){
				query=prop.getProperty("");
			}
			
			
			
		}else if(radioSearch.equals("findDeptCode")) {
			if(selectSearch.equals("memberName")) {
				query=prop.getProperty("");
			}else if(selectSearch.equals("memberDeptCode")){
				query=prop.getProperty("");
			}
		}else {
			query = prop.getProperty("findAll");
		}
		list = new ArrayList<Member>();
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%"+searchText+"%");
			
			while(rset.next()) {
				
				Member searchFind = new Member();
				
				searchFind.setMemberId(rset.getString("MEMBER_ID"));
				searchFind.setMemberName(rset.getString("MEMBER_NAME"));
				searchFind.setDeptCode(rset.getString("DEPT_CODE"));
				searchFind.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				searchFind.setMemberPhone(rset.getString("MEMBER_PHONE"));
				searchFind.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				//searchFind.setMemberName(rset);
				
				
				list.add(searchFind);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
		
	
	}


	public ArrayList<Member> deptNameSearch(Connection con, String deptSearch2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = prop.getProperty("nameSearch"); 
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, deptSearch2);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member nameSearchDept = new Member();
				
				nameSearchDept.setMemberId(rset.getString("MEMBER_ID"));
				nameSearchDept.setMemberName(rset.getString("MEMBER_NAME"));
				nameSearchDept.setMemberPhone(rset.getString("MEMBER_PHONE"));
				nameSearchDept.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				nameSearchDept.setDeptCode(rset.getString("DEPT_CODE"));
				nameSearchDept.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
				list.add(nameSearchDept);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}


	public ArrayList<Member> deptSortSearch(Connection con, String deptSearch2) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = prop.getProperty("sortSearch"); 
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, deptSearch2);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member sortSearchDept = new Member();
				
				sortSearchDept.setMemberId(rset.getString("MEMBER_ID"));
				sortSearchDept.setMemberName(rset.getString("MEMBER_NAME"));
				sortSearchDept.setMemberPhone(rset.getString("MEMBER_PHONE"));
				sortSearchDept.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				sortSearchDept.setDeptCode(rset.getString("DEPT_CODE"));
				sortSearchDept.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
				list.add(sortSearchDept);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}


	public ArrayList<Member> deptDeptCode(Connection con, String radioF) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = prop.getProperty("deptDeptCodeSelect"); 
		
		try {
			stmt = con.createStatement();
			//stmt.setString(1, radioF);
			
			rset = stmt.executeQuery(query);
			list = new ArrayList<Member>();
			
			//System.out.println("query" + query );
			
			while(rset.next()) {
				Member deptDeptCode = new Member();
				
				deptDeptCode.setMemberId(rset.getString("MEMBER_ID"));
				deptDeptCode.setMemberName(rset.getString("MEMBER_NAME"));
				deptDeptCode.setMemberPhone(rset.getString("MEMBER_PHONE"));
				deptDeptCode.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				deptDeptCode.setDeptCode(rset.getString("DEPT_CODE"));
				deptDeptCode.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
				list.add(deptDeptCode);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		
		return list;
	}


	public ArrayList<Member> deptId(Connection con, String radioF) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = prop.getProperty("deptIdSelect"); 
		
		try {
			stmt = con.createStatement();
			//stmt.setString(1, radioF);
			
			rset = stmt.executeQuery(query);
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member deptId = new Member();
				
				deptId.setMemberId(rset.getString("MEMBER_ID"));
				deptId.setMemberName(rset.getString("MEMBER_NAME"));
				deptId.setMemberPhone(rset.getString("MEMBER_PHONE"));
				deptId.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				deptId.setDeptCode(rset.getString("DEPT_CODE"));
				deptId.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
				list.add(deptId);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
				
		return list;
	}


	public ArrayList<Member> deptAll(Connection con, String radioF) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list = null;
		
		String query = prop.getProperty("deptAllSelect"); 
		
		try {
			stmt = con.createStatement();
			
			rset = stmt.executeQuery(query);
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member deptAll = new Member();
				
				deptAll.setMemberId(rset.getString("MEMBER_ID"));
				deptAll.setMemberName(rset.getString("MEMBER_NAME"));
				deptAll.setMemberPhone(rset.getString("MEMBER_PHONE"));
				deptAll.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				deptAll.setDeptCode(rset.getString("DEPT_CODE"));
				deptAll.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
				
				list.add(deptAll);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
				
		return list;
	}


	//이게 SELECT하는거 같은데.. 본사에서 전체 리스트 조회하는거
	public ArrayList<Paper> deptNameAll(Connection con) {
		Statement stmt =null;
		ResultSet rset = null;
		ArrayList<Paper> list =null;
		
		String query = prop.getProperty("deptNameAllSelect");
		
		try {
			stmt = con.createStatement();
			rset=stmt.executeQuery(query);
			
			list = new ArrayList<Paper>();
			
			
			while(rset.next()) {
				Paper deptNameList = new  Paper();
				
				deptNameList.setrNum(rset.getInt(1));
				deptNameList.setBranchDate(rset.getString(2));
				deptNameList.setBranchCode(rset.getString(3));
				deptNameList.setBranchName(rset.getString(4));
				
				
				list.add(deptNameList);
				
				//System.out.println("memberDAO LIST : " + list);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}


	public ArrayList<Paper> PaperBranchSearch(Connection con, String branchName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Paper> list = null;
		
		String query = prop.getProperty("paperBranchSearch"); 
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchName);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<Paper>();
			
			while(rset.next()) {
				Paper paperBranchSearch = new Paper();
				
				paperBranchSearch.setrNum(rset.getInt("RNUM"));
				paperBranchSearch.setBranchDate(rset.getString("BRANCH_DATE"));
				paperBranchSearch.setBranchCode(rset.getString("BRANCH_CODE"));
				paperBranchSearch.setBranchName(rset.getString("BRANCH_NAME"));

				
				list.add(paperBranchSearch);
				//System.out.println("memberdao list paperbranchSearch : " + list);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}


	//지점명 select
	public ArrayList<Paper> deptNameList(Connection con, String deptName) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Paper> list =null;
		
		String query = prop.getProperty("branchNameList");
		
		try {
			stmt = con.createStatement();
			rset=stmt.executeQuery(query);
			
			list = new ArrayList<Paper>();
			
			while(rset.next()) {
				Paper deptNameList = new Paper();
				
				deptNameList.setBranchName(rset.getString("BRANCH_NAME"));
				
				list.add(deptNameList);
			
				
				
				
				
			}
			
			//System.out.println("deptListAll list : " + list);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}


	public ArrayList<PaperPurchase> paperSelectBNH(Connection con, String branchName, String branchDate) {
		PreparedStatement pstmt = null;
		ResultSet rset =null;
		ArrayList<PaperPurchase> list = null;

		String query = prop.getProperty("paperSelectBNH");
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, branchDate);
			pstmt.setString(2, branchDate);
			pstmt.setString(3, branchName);
			
			rset=pstmt.executeQuery();
			//System.out.println("RSET : " + rset);
			
			list = new ArrayList<PaperPurchase>();
			
			
			//System.out.println("while 전 list : " + list);
			
			while(rset.next()) {
				
				PaperPurchase paperSelectBNH = new PaperPurchase();
				
				paperSelectBNH.setrNum(rset.getInt("RNUM"));
				paperSelectBNH.setBranchDate(rset.getString("BRANCH_DATE"));
				paperSelectBNH.setBranchName(rset.getString("BRANCH_NAME"));
				paperSelectBNH.setBranchAddress(rset.getString("BRANCH_ADDRESS"));
				
				
				
				
				list.add(paperSelectBNH);
				
				//System.out.println("memberDAO PAPERSELECTBNH  : " + list);
			
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


	public ArrayList<PaperPurchase> paperSelectHOF(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<PaperPurchase> list = null;
		String query = prop.getProperty("paperSelectHOF");
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			
			list = new ArrayList<PaperPurchase>();
			while(rset.next()) {
				PaperPurchase paperSelectHOF = new PaperPurchase();
				
				paperSelectHOF.setHoNo(rset.getString("HO_NO")); //사업자번호
				paperSelectHOF.setHoName(rset.getString("HO_NAME"));//회사명
				paperSelectHOF.setHoRepName(rset.getString("HO_REP_NAME"));//대표자명
				paperSelectHOF.setHoAddress(rset.getString("HO_ADDRESS"));//사업장주소
				paperSelectHOF.setHoPhone(rset.getString("HO_PHONE"));//사업장연락처

				
				list.add(paperSelectHOF);
				//System.out.println("dao paperselectHOF : " + list);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}


	public ArrayList<PaperPurchase> paperSelectOrder(Connection con, String branchDate, String branchCode) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PaperPurchase> list = null;
		
		String query = prop.getProperty("paperSelectOrder"); //쿼리문 재확인 필요 아직 안넣음!
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, branchDate);
			pstmt.setString(2, branchDate);
			pstmt.setString(3, branchCode);
			
			
			rset=pstmt.executeQuery();
		
				//확인용
				//System.out.println("dao paperSelectOrder rset : " + rset);
			
			list = new ArrayList<PaperPurchase>();
			
			while(rset.next()) {
				PaperPurchase paperSelectOrder =new PaperPurchase();
				
				paperSelectOrder.setIngredientClassName(rset.getString("INGREDIENT_CLASS_NAME"));
				paperSelectOrder.setIngredientCode(rset.getString("INGREDIENT_CODE"));
				paperSelectOrder.setIngredientName(rset.getString("INGREDIENT_NAME"));
				paperSelectOrder.setPurchaseQuantity(rset.getInt("PURCHASE_QUANTITY"));	
				
				list.add(paperSelectOrder);
				
				
				//System.out.println("dao paperSelectOrder list : " + list);
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


	public ArrayList<PaperPurchase> paperManagementSearch(Connection con, String branchName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<PaperPurchase> list = null;
		
		String query = prop.getProperty("deptNameSearch"); 
		
		 //System.out.println("query 확인 : " + query);
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, branchName);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<PaperPurchase>();
			
			 //System.out.println("while 전 list : " + list);
			
			while(rset.next()) {
				PaperPurchase paperManagementSearch = new PaperPurchase();
				
				paperManagementSearch.setrNum(rset.getInt("RNUM"));
				paperManagementSearch.setBranchDate(rset.getString("BRANCH_DATE"));
				paperManagementSearch.setBranchCode(rset.getString("BRANCH_CODE"));
				paperManagementSearch.setBranchName(rset.getString("BRANCH_NAME"));
				
				list.add(paperManagementSearch);
				
				//System.out.println("list : " + list);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return list;
	}


	public ArrayList<Paper> paperDeptFindAll(Connection con) {
		Statement stmt =null;
		ResultSet rset = null;
		ArrayList<Paper> list =null;
		
		String query = prop.getProperty("paperFindAll");
		
		try {
			stmt = con.createStatement();
			rset=stmt.executeQuery(query);
			
			list = new ArrayList<Paper>();
			
			
			while(rset.next()) {
				Paper deptNameList = new  Paper();
				
				deptNameList.setrNum(rset.getInt(1));
				deptNameList.setBranchDate(rset.getString(2));
				deptNameList.setBranchCode(rset.getString(3));
				deptNameList.setBranchName(rset.getString(4));
				
				
				list.add(deptNameList);
				
				//System.out.println("memberDAO LIST : " + list);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rset);
		}
		
		return list;
	}




/*	public ArrayList<PurchaseListBR> paperBNHSelect(Connection con, String deptName) {
		// TODO Auto-generated method stub
		return null;
	}

*/

}

