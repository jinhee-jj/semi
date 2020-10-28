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
import com.kh.cool.purchase.model.service.PurchaseService;

/**
 * Servlet implementation class deptNameSearchServlet
 */
@WebServlet("/deptNameSearch.me")
public class deptNameSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deptNameSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deptSearch2 = request.getParameter("deptSearch2");
			//System.out.println("deptNameSearchServlet deptSeatch2 확인 : " + deptSearch2);
		
		ArrayList<Member> list = new MemberService().deptNameSearch(deptSearch2);
		
			//System.out.println("deptNameSearchServlet 이름으로 검색 : " + list);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
