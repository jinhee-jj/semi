package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.SupplierOrder;

/**
 * Servlet implementation class SelectOneSupServlet
 */
@WebServlet("/selectOneSup.pu")
public class SelectOneSupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneSupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sCode = request.getParameter("sCode");
		//System.out.println("sCode check in servlet : " + sCode);
		
		
		ArrayList<SupplierOrder> list = new PurchaseService().selectOneSup(sCode);
		
		//System.out.println("거래처별 재료 목록 확인 " + list);
		
		String page = "";
		if(list != null) {
			page = "views/purchase/headOffice/purchaseHistoryOneSup.jsp";
			request.setAttribute("list", list);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "거래처별 상세 발주목록 조회 실패");
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
