package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.SupplierOrder;

/**
 * Servlet implementation class SupplierSearchServlet
 */
@WebServlet("/searchSupplier.pu")
public class SupplierSearchServletInPList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierSearchServletInPList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchCondition = request.getParameter("searchCondition");
		String searchValue =  request.getParameter("searchValue");
		
		//System.out.println("searchCondition 확인 : " + searchCondition);
		//System.out.println("searchValue 확인 : " + searchValue);
		
		ArrayList<SupplierOrder> list = null;
		
		
		//검색조건 거래처명일 때
		if(searchCondition.equals("거래처명")) {
			list = new PurchaseService().selectPuListWithSupName(searchValue);
			//System.out.println("list 확인 in servlet" + list);
			
		//검색조건 거래처 코드일 때
		} else {
			list = new PurchaseService().selectPuListWithSupCode(searchValue);
		}
		
		if(list != null) {
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(list, response.getWriter());
		} else {
			String page = "views/common/errorPage.jsp";
			request.setAttribute("message", "거래처 검색 실패");
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
