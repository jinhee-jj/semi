package com.kh.cool.member.model.service;

import static com.kh.cool.common.JDBCTemplate.*;
import static com.kh.cool.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.management.model.vo.Paper;
import com.kh.cool.member.model.dao.MemberDao;
import com.kh.cool.member.model.vo.Member;

public class MemberService { //로그인 체크용

	
	public Member loginCheck(String deptCode, String memberId, String memberPwd) {
		
		Connection con = getConnection();
		
		Member loginMember = new MemberDao().loginCheck(con,deptCode, memberId, memberPwd);
		
		close(con);
		
		return loginMember;
		
	}


	public Member PasswordSearch(String memberId, String deptCode, String memberName, String memberEmail) {
		//비밀번호찾기
		
		Connection con =getConnection();

		Member passSearch = new MemberDao().passwordSearch(con, memberId, deptCode, memberName, memberEmail);
		close(con);
		
		return passSearch;
	}


	public ArrayList<Member> deptListAll(String deptCode) {
		//passwordsearch select option 
		
		Connection con = getConnection();
		
		ArrayList<Member> list = new MemberDao().deptListAll(con, deptCode);
		
		close(con);
		
		return list;
	}


	public int updatePwd(String memberId, String pwd) {
		
		Connection con = getConnection();
		
		int result = new MemberDao().updatePwd(con, memberId, pwd);
		if(result>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result; 
	}

}
