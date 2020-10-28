package com.kh.cool.management.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class MemberInfoServlet
 */
@WebServlet("/memberInfo.me")
public class MemberInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//업데이트(정보수정)
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String deptCode = request.getParameter("deptCode");
		String memberPwd = request.getParameter("memberPwd");
		String memberEmail=request.getParameter("memberEmail");
		String memberPhone=request.getParameter("memberPhone");
		String memberDeptCode = request.getParameter("memberDeptCode");
		
		Member memberInfo = new Member();
		memberInfo.setMemberId(memberId);
		memberInfo.setMemberPwd(memberPwd);
		memberInfo.setDeptCode(memberDeptCode);
		memberInfo.setMemberPwd(memberPwd);
		memberInfo.setMemberEmail(memberEmail);
		memberInfo.setMemberPhone(memberPhone);
		memberInfo.setDeptCode(memberDeptCode);
		

	}
}










