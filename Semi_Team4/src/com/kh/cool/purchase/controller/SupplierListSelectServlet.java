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
import com.kh.cool.purchase.model.vo.Supplier;

/**
 * Servlet implementation class SupplierListSelectServlet
 */
@WebServlet("/selectSupList.pu")
public class SupplierListSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierListSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajax = request.getParameter("ajax");
		
		//System.out.println("ajax" + ajax);
		if(ajax != null) {
			ArrayList<Supplier> list = new PurchaseService().selectSupListAll();
			
			//System.out.println("suplist 확인1 : " + list);
			
			String page="";
			
			if(list != null) {
				response.setContentType("application/json; charset=UTF-8");
				new Gson().toJson(list, response.getWriter());
				
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("message", "거래처 조회 실패!");
			}
			
			
		} else {
			
			ArrayList<Supplier> list = new PurchaseService().selectSupListAll();
			
		//	System.out.println("suplist 확인 : " + list);
			
			String page="";
			
			if(list != null) {
				page = "views/purchase/headOffice/supplierManagementListHO.jsp";
				request.setAttribute("list", list);
				
			} else {
				page = "views/common/errorPage.jsp";
				request.setAttribute("message", "거래처 조회 실패!");
			}
			
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
