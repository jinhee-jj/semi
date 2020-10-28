package com.kh.cool.purchase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.Supplier;

/**
 * Servlet implementation class SupplierInsertServlet
 */
@WebServlet("/insertSupplier.pu")
public class SupplierInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String supplierCode = request.getParameter("supplierCode");
		String supplierName = request.getParameter("supplierName");
		String supplierRep = request.getParameter("supplierRep");
		String supplierPhone = request.getParameter("supplierPhone");
		String supplierAddress = request.getParameter("supplierAddress");
		String supplierNumber = request.getParameter("supplierNumber");
		String supplierEmail = request.getParameter("supplierEmail");
		String supplierBank = request.getParameter("supplierBank");
		String supplierBankNum = request.getParameter("supplierBankNum");
		String supplierFax = request.getParameter("supplierFax");
	
		
		Supplier supplier = new Supplier();
		
		supplier.setsCode(supplierCode);
		supplier.setcName(supplierName);
		supplier.setrName(supplierRep);
		supplier.setsPhone(supplierPhone);
		supplier.setsAddress(supplierAddress);
		supplier.setsNum(supplierNumber);
		supplier.setsEmail(supplierEmail);
		supplier.setsBank(supplierBank);
		supplier.setsAccount(supplierBankNum);
		supplier.setsFax(supplierFax);
		
	//	System.out.println("supplier check : " + supplier);
		
		int result = new PurchaseService().insertSupplier(supplier);
		
		
		String page = "";
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/selectSupList.pu");
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "거래서 등록 실패");
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
