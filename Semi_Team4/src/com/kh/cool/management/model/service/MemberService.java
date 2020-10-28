package com.kh.cool.management.model.service;

import static com.kh.cool.common.JDBCTemplate.close;
import static com.kh.cool.common.JDBCTemplate.commit;
import static com.kh.cool.common.JDBCTemplate.getConnection;
import static com.kh.cool.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.management.model.dao.MemberDao;
import com.kh.cool.management.model.vo.Paper;
import com.kh.cool.management.model.vo.PaperPurchase;
import com.kh.cool.member.model.vo.Member;

public class MemberService { 

	public int memberInsert(Member newMember) {
		//계정생성
		Connection con = getConnection();
		int result = new MemberDao().memberInsert(con, newMember);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<Member> memberSelectAll() {
		//계정권한관리 : 전체계정 조회
		Connection con = getConnection();
		//System.out.println("memberSelectAll ㅈㅓㅂㅅㅗㄱ ");
		ArrayList<Member> list = new MemberDao().memberSelectAll(con);
		
		close(con);
		return list;
	}

	public int updateMember(Member memberUpdate) {
		Connection con = getConnection();
		
		MemberDao memberDao = new MemberDao();
		
		int updateResult = memberDao.updateMember(con, memberUpdate);
		
		
		if(updateResult > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return updateResult;
	}
	
	//업데이트(권한수정) 후 계정조회
	public int UpdateAllMembers(ArrayList<Member> list) {
		
		Connection con = getConnection();
		//controller 에 보낼 신호 0이면 실패 1 이면 성공
		int result = 0;
		int result1 = 0;
		
		//여러번 쓸거라서 객체생성
		MemberDao mb = new MemberDao();

		//System.out.println("listsize : " + list.size()); //11
		for(int i = 0; i < list.size(); i++) {

			result1 += mb.updateMemberAll(con, list.get(i));
			
		}	
		if(result1 == list.size()) {
			//System.out.println("if문들어옴");
			commit(con);

			result = 1;

		}else {
			//System.out.println("else문 들어옴");
			rollback(con);
		}
			//System.out.println("result1 : " + result1);
		return result;
	}

	public int deleteAllMember(ArrayList<Member> list) {
		Connection con  = getConnection();
		int result =0;
		int result1=0;
		
		MemberDao mb = new MemberDao();
		
		for(int i = 0; i<list.size();i++) {
			result1 += mb.deleteMemberAll(con, list.get(i));
			
		}
		if(result1==list.size()) {
			commit(con);
			result = 1;
		}else {
			rollback(con);
		}
		return result;
	}

	
	//내 계정정보 수정하기
	public Member meUpdateInfo(Member myInfo) {
		Connection con = getConnection();
		Member meUpdate = null;
		
		int result = new MemberDao().meUpdateInfo(con, myInfo);
		if(result>0) {
			meUpdate=new MemberDao().updateInfoMe(con, myInfo);
			
			if(meUpdate !=null) {
				commit(con);
			}else {
				rollback(con);
			}
		}else {
			rollback(con);
		}
		close(con);
		return meUpdate;
	}

	//계정관리 : 이름으로 검색
	public ArrayList<Member> deptNameSearch(String deptSearch2) {
		Connection con = getConnection();
		
		ArrayList<Member> list = new MemberDao().deptNameSearch(con, deptSearch2);
		
		close(con);
		return list;
	}
	
	//계정관리 : 구분으로 검색
	public ArrayList<Member> deptSortSearch(String deptSearch2) {
		Connection con = getConnection();
		
		ArrayList<Member> list = new MemberDao().deptSortSearch(con, deptSearch2);
		
		close(con);
		return list;
	}
	//계정관리 : 부서코드로 정렬
	public ArrayList<Member> deptDeptCode(String radioF) {
		Connection con = getConnection();
		
		ArrayList<Member> list = new MemberDao().deptDeptCode(con, radioF);
		
		close(con);
		return list;
	}
	
	//계정관리 : 아이디로 정렬
	public ArrayList<Member> deptId(String radioF) {
		Connection con = getConnection();
		
		ArrayList<Member> list = new MemberDao().deptId(con, radioF);
		
		close(con);
		return list;
	}
	//계정관리 : 전체 정렬
	public ArrayList<Member> deptAll(String radioF) {
		Connection con = getConnection();
		
		ArrayList<Member> list = new MemberDao().deptAll(con, radioF);
		
		close(con);
		return list;
	}

	
	public ArrayList<Paper> deptNameAll() {
		Connection con = getConnection();
			//System.out.println("memberService deptNameAll 접속");
		
		ArrayList<Paper> list = new MemberDao().deptNameAll(con);
		
		close(con);
		
		return list;
	}

	//계정관리 :지점으로 검색
	
	public ArrayList<Paper> PaperBranchSearch(String branchName) {
		Connection con = getConnection();
		
		ArrayList<Paper> list = new MemberDao().PaperBranchSearch(con, branchName);
		
		close(con);
		
		return list;
	}

	//지점명 리스트
	public ArrayList<Paper> deptNameList(String branchName) {
		
		Connection con = getConnection();
				
		ArrayList<Paper> list = new MemberDao().deptNameList(con, branchName);
		
		close(con);
				
		return list;
	}

	//문서관리 상세보기 지점정보
	public ArrayList<PaperPurchase> paperSelectBNH(String branchName, String branchDate) {
		 Connection con = getConnection();
		 ArrayList<PaperPurchase> list  = new MemberDao().paperSelectBNH(con,branchName, branchDate);
		 
		 close(con);
		 return list;
	}

	//문서관리 상세보기 본사정보
	public ArrayList<PaperPurchase> paperSelectHOF() {
		Connection con = getConnection();
		ArrayList<PaperPurchase> list = new MemberDao(). paperSelectHOF(con);
				
		close(con);
		return list;
	}
	//문서관리 상세보기 제품리스트
	public ArrayList<PaperPurchase> paperSelectOrder(String branchDate, String branchCode) {
		Connection con = getConnection();
		ArrayList<PaperPurchase> list = new MemberDao().paperSelectOrder(con, branchDate, branchCode);
		
		close(con);
		return list;
	}

	//지점명으로 검색
	public ArrayList<PaperPurchase> paperManagementSearch(String branchName) {
		Connection con = getConnection();
		
		ArrayList<PaperPurchase> list = new MemberDao().paperManagementSearch(con, branchName);
		
		close(con);
		return list;
	}

	public ArrayList<Paper> paperDeptFindAll() {
		Connection con = getConnection();
		
		ArrayList<Paper> list = new MemberDao().paperDeptFindAll(con);
		
		
		//System.out.println("paperDeptFindAll Service : " + list);
		close(con);
		return list;
	}

	


/*	//문서관리 : 지점문서 조회 (순번, 작성일자)
	public ArrayList<PurchaseListBR> paperBNHSelect() {
		Connection con = getConnection();
		
		ArrayList<PurchaseListBR> paperList = new MemberDao().paperBNHSelect(con);
		
		return paperList;
	}
*/
	
}