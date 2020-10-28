package com.kh.cool.management.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.management.model.service.MemberService;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/memberDelete.me")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String[] memberId = request.getParameterValues("memberId");
		//System.out.println("memberId1 : " + memberId[0]);
		//System.out.println("memberId2 : " + memberId[1]);
		
		//int result = new MemberService().deleteMember(memberId);
		String add = request.getParameter("add");
		
		
			//System.out.println("add1 : " + add);
		String[] memberId = add.split(", ");
		
		ArrayList<Member> list = new ArrayList<Member>();
		for(int i = 0; i < memberId.length;i++) {
			//System.out.println("memberId[i] : " + memberId[i]);
			
			Member deleteMember = new Member();
			
			deleteMember.setMemberId(memberId[i]);
			list.add(deleteMember);
				//System.out.println("list : " + list);
		}
		
		
		int result = new MemberService().deleteAllMember(list);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(result, response.getWriter());
		
//		//String page="";
//		if(result>0) {
//			page="memberSelectAll.me";
//			response.sendRedirect(page);
//		}else {
//			request.setAttribute("message", "권한관리 : 계정 삭제 실패");
//			page="views/common/errorPage.jsp";
//			request.getRequestDispatcher(page).forward(request, response);
//		}
		
/*		String page="";
		if(result>0) {
			
			page="memberSelectAll.me";
			response.sendRedirect(page);
			
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("message", "운영관리 : 계정삭제 실패" );
			request.getRequestDispatcher(page).forward(request, response);
		}
		
		
		*/
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
