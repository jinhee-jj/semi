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
import com.kh.cool.management.model.vo.PaperPurchase;

/**
 * Servlet implementation class PaperSelectHOFServlet
 */
@WebServlet("/PaperSelectHOF.me")
public class PaperSelectHOFServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaperSelectHOFServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//발주서 모달창 본사정보
		
		//System.out.println("지점발주서 본사정보 servlet");
		ArrayList<PaperPurchase> list = new MemberService().paperSelectHOF();
		
		String page="";
		if(list !=null) {
			page="views/management/paperManagementHOF.jsp";
			request.setAttribute("list", list);
			
			
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("message", "문서관리 : 모달창 본사정보 조회 실패");
		}
	
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
