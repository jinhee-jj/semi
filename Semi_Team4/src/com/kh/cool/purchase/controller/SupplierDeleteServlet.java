package com.kh.cool.purchase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.purchase.model.service.PurchaseService;

/**
 * Servlet implementation class SupplierDeleteServlet
 */
@WebServlet("/deleteSup.pu")
public class SupplierDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] sCodes = request.getParameterValues("sCodes");
		int size = sCodes.length;
		
/*		for(int i = 0; i < sCodes.length; i++) {
			System.out.println("scode 배열확인 " +sCodes[i]);
		}*/
	//	System.out.println("sCode check : " + sCode);
		
		
		int result = new PurchaseService().deleteSupplier(sCodes);
		
	//	System.out.println("result check in servlet: " + result);
		String page = "";
		if(result > 0) {
			response.getWriter().print("Y");
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "거래처 삭제 실패");
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
