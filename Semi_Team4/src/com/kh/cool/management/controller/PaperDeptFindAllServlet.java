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
import com.kh.cool.management.model.vo.Paper;

/**
 * Servlet implementation class PaperDeptFindAllServlet
 */
@WebServlet("/PaperDeptFindAll.me")
public class PaperDeptFindAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaperDeptFindAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branchName = request.getParameter("branchName");
		
		//System.out.println("paperDeptFind Servlet branchName 확인 : " + branchName);
			
		
			ArrayList<Paper> list = new MemberService().paperDeptFindAll();
		
			//System.out.println("paperDEPTfIND Servlet 전체지점 : " + list);
	
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
