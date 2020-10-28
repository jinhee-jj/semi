package com.kh.cool.management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.management.model.service.MemberService;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/memberUpdate.me")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
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

		
		String[] memberId = request.getParameterValues("memberId"); 
		String[] memberName = request.getParameterValues("memberName");
		String[] memberPhone = request.getParameterValues("memberPhone");
		String[] memberEmail = request.getParameterValues("memberEmail");
		String[] deptCode = request.getParameterValues("deptCode");
		String[] memberDeptCode = request.getParameterValues("memberDeptCode");
		
		
		//1. 정보 담기
		ArrayList<Member> list = new ArrayList<Member>();
		for(int i = 0 ; i < memberId.length; i++) {
			Member memberUpdate = new Member();
			
			memberUpdate.setMemberId(memberId[i]);
			memberUpdate.setMemberName(memberName[i]);
			memberUpdate.setMemberPhone(memberPhone[i]);
			memberUpdate.setMemberEmail(memberEmail[i]);
			memberUpdate.setDeptCode(deptCode[i]);
			memberUpdate.setMemberDeptCode(memberDeptCode[i]);
			list.add(memberUpdate);

		}
		
		//2. 비지니스로직

		int result  = new MemberService().UpdateAllMembers(list);
		
		String page = "";
		if(result > 0) {
			
			page="memberSelectAll.me";
			response.sendRedirect(page);
	
			
		}else {
			request.setAttribute("message", "직원 권한 수정 실패");
			page="views/common/errorPage.jsp";
			request.getRequestDispatcher(page).forward(request, response);
		}

		
	}

}
