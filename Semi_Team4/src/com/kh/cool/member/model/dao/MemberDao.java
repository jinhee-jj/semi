package com.kh.cool.member.model.dao;

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
import com.kh.cool.member.model.vo.Member;

public class MemberDao {

	public Properties prop = new Properties();
	
	public MemberDao() {
											
													//properties 작성하고 경로넣기.
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public Member loginCheck(Connection con, String deptCode, String memberId, String memberPwd) {
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		Member loginMember = null; 
	
		String query = prop.getProperty("loginCheck");
		
		
		try {
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPwd);
			pstmt.setString(3, deptCode);
			
			rset = pstmt.executeQuery();

			if(rset.next()) { 
				loginMember = new Member();

				loginMember.setMemberId(rset.getString("MEMBER_ID"));
				loginMember.setMemberName(rset.getString("MEMBER_NAME"));
				loginMember.setDeptCode(rset.getString("DEPT_CODE"));
				loginMember.setMemberPwd(rset.getString("MEMBER_PWD"));
				loginMember.setMemberEmail(rset.getString("MEMBER_EMAIL"));
				loginMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
				loginMember.setMemberDeptCode(rset.getString("MEMBER_DEPT_CODE"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return loginMember;
	}


	public Member passwordSearch(Connection con, String memberId, String deptCode, String memberName, String memberEmail) {
		//비밀번호 찾기
		PreparedStatement pstmt = null; 
		ResultSet rset = null; 
		Member passSearch = null; 
	
		String query = prop.getProperty("passSearch");
		
		//System.out.println("dao query 1 : " + query );
		
		try {
			pstmt = con.prepareStatement(query);
		
			pstmt.setString(1, memberId);
			pstmt.setString(2, deptCode);
			pstmt.setString(3, memberName);
			pstmt.setString(4, memberEmail);
			
			rset =pstmt.executeQuery();
			
			if(rset.next()) {
				passSearch=new Member();
				
				passSearch.setMemberId(rset.getString("MEMBER_ID"));
				passSearch.setDeptCode(rset.getString("DEPT_CODE"));
				passSearch.setMemberName(rset.getString("MEMBER_NAME"));
				passSearch.setMemberEmail(rset.getString("MEMBER_EMAIL"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(rset);
		}
		
		
		return passSearch;
	}


	public ArrayList<Member> deptListAll(Connection con, String deptCode) {
		Statement stmt = null;
		ResultSet rset = null;
		ArrayList<Member> list =null;
		
		String query = prop.getProperty("deptListAll");
		
		try {
			stmt = con.createStatement();
			rset=stmt.executeQuery(query);
			
			list = new ArrayList<Member>();
			
			while(rset.next()) {
				Member deptListAll = new Member();
				
				deptListAll.setDeptCode(rset.getString("DEPT_CODE"));
				
				list.add(deptListAll);
			
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


	public int updatePwd(Connection con, String memberId, String pwd) {
		PreparedStatement pstmt = null;
		int result= 0;
		
		String query = prop.getProperty("updatePwd");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}		
		return result;
	}

}

