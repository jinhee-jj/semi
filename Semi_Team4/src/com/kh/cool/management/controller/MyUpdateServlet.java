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
 * Servlet implementation class MyUpdateServlet
 */
@WebServlet("/myUpdate.me")
public class MyUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId"); 
		String memberName = request.getParameter("memberName"); 
		String memberDeptCode = request.getParameter("DEPTCODE"); 
		String memberPwd = request.getParameter("memberPwd");
		String memberEmail = request.getParameter("emailCh"); 
		String memberPhone = request.getParameter("memberPhone");
		
		
		System.out.println("memberPwd : " +memberPwd);
			/*//확인용
			System.out.println("memberId : " + memberId);
			System.out.println("memberName : " +memberName);
			System.out.println("MEMBERDEPTCODE : " + memberDeptCode);
			System.out.println("memberPWD : " + memberPwd);
			System.out.println("email : " + memberEmail);
			System.out.println("phone : " +memberPhone );*/
		
		Member myInfo = new Member();
		
		myInfo.setMemberId(memberId);
		myInfo.setMemberName(memberName);
		myInfo.setMemberPhone(memberPhone);
		myInfo.setMemberDeptCode(memberDeptCode);
		myInfo.setMemberEmail(memberEmail);
		myInfo.setMemberPwd(memberPwd);
		
			//System.out.println("myMember Servlet 2 : " + myInfo);
		
		Member meInfoUpdate  = new MemberService().meUpdateInfo(myInfo);
		
		String page="";
		
		if(meInfoUpdate !=null) {
			
			page="views/management/memberInfo.jsp";			
			request.getSession().setAttribute("loginMember", meInfoUpdate);
			response.sendRedirect(page);
			
			
			
			
			//request.setAttribute("successCode", "myInfoUpdate");
			//page="views/common/successPage.jsp";
			
			
			
		}else {
			request.setAttribute("message", "계정관리 : 개인 정보 수정 실패");
			page="views/common/errorPage.jsp";
			request.getRequestDispatcher(page).forward(request, response);		
			
		}
	}
}

