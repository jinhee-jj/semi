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
 * Servlet implementation class PaperSelectBNHServlet
 */
@WebServlet("/PaperSelectBNH.me")
public class PaperSelectBNHServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaperSelectBNHServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//발주서 모달창 지점정보 
		
		//String branchName= request.getParameter("branchName");
		String rnum = request.getParameter("rnum");
		String branchDate = request.getParameter("branchDate");//발주일자
		String branchCode = request.getParameter("branchcode");//지점코드
		String branchName= request.getParameter("branchName"); //지점명

				
		
		//System.out.println("PAPER SELECTBNH SERVLET BRANCHname : " + branchName);
		//System.out.println("PAPER SELECTBNH SERVLET BRANCHDate : " + branchDate);
		
		
		ArrayList<PaperPurchase> list = new MemberService().paperSelectBNH(branchName, branchDate);
		
		response.setContentType("application/json; charset=UTF-8");
		
		new Gson().toJson(list, response.getWriter());
		
		
		
		String page="";
		if(list !=null) {
			page="views/management/paperManagementHOF.jsp";
			request.setAttribute("list", list);
			
			
		}else {
			page="views/common/errorPage.jsp";
			request.setAttribute("message", "문서관리 : 모달창 지점정보 조회 실패");
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
