package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.PurchaseListBR;
import com.kh.cool.purchase.model.vo.SupplierOrder;

/**
 * Servlet implementation class HOselectBRpurchaseList
 */
@WebServlet("/selectBRpuListforHo.pu")
public class HOselectBRpurchaseList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HOselectBRpurchaseList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<SupplierOrder> puList = new PurchaseService().SelectPuListSortBySupplier();
		
		//System.out.println("puList check : " + puList);
		
		String page = "";		
		if(puList != null) {
			page = "views/purchase/headOffice/purchaseRegisterHO.jsp";
			request.setAttribute("puList", puList);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "본사 발주등록창 조회 실패");
		}
		 request.getRequestDispatcher(page).forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
