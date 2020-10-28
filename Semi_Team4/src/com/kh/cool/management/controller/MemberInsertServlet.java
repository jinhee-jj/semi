package com.kh.cool.management.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.management.model.service.MemberService;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/memberInsert.me")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//멤버 추가(계정생성)
		
		String memberId = request.getParameter("memberId");
		String memberName = request.getParameter("memberName");
		String deptCode = request.getParameter("deptCode");
		String memberPwd = request.getParameter("memberPwd");
		String memberEmail=request.getParameter("memberEmail");
		String memberPhone=request.getParameter("memberPhone");
		String memberDeptCode = request.getParameter("memberDeptCode");
		
		
		Member newMember = new Member();
		
		newMember.setMemberId(memberId);
		newMember.setMemberName(memberName);
		newMember.setDeptCode(deptCode);
		newMember.setMemberPwd(memberPwd);
		newMember.setMemberEmail(memberEmail);
		newMember.setMemberPhone(memberPhone);
		newMember.setMemberDeptCode(memberDeptCode);
		
		
		int result = new MemberService().memberInsert(newMember);
		
		
		String page="";
		if(result>0) {
			page="memberSelectAll.me";
			response.sendRedirect(page);
			
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("message", "계정권한관리 : 계정생성 실패");
			request.getRequestDispatcher(page).forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
