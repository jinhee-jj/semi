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
 * Servlet implementation class MemberSelectAllServlet
 */
@WebServlet("/memberSelectAll.me")
public class MemberSelectAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSelectAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//전체 계정 조회
		//System.out.println("접속됨123");
		ArrayList<Member> list = new MemberService().memberSelectAll();
		
		//System.out.println("MemberSelectAllServlet List확인용" + list);
		
		/*response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list ,response.getWriter());*/
		
		String page = "";
		if(list !=null) {
			page = "views/management/deptAuthorization.jsp";
			request.setAttribute("list",list);
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "계정권한관리 : 계정목록 조회 실패");
		}
		
		request.getRequestDispatcher(page).forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
