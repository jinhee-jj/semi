package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Inven;

/**
 * Servlet implementation class InvenInsertLastServlet
 */
@WebServlet("/insertInvenLast.in")
public class InvenInsertLastServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvenInsertLastServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String igretName = request.getParameter("igName");
		int addQuantity = Integer.parseInt(request.getParameter("igQ"));
		String barcode = request.getParameter("insertBarcode");
		String igretCode = request.getParameter("insertIgCode");
		String branchCode = request.getParameter("insertBranchCode");
		String supCode = request.getParameter("insertSupCode");
		
		Inven lastInsertInven = new Inven();
		
		lastInsertInven.setIgretName(igretName);
		lastInsertInven.setAddQuantity(addQuantity);
		lastInsertInven.setBarcode(barcode);
		lastInsertInven.setIgretCode(igretCode);
		lastInsertInven.setSup_code(supCode);
		lastInsertInven.setBranchCode(branchCode);
		
		int result = new InvenService().lastInsertInven(lastInsertInven);
		
		String path = "";
		if(result >= 2) {
			response.sendRedirect(request.getHeader("referer"));
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "원재료 입고 실패!");
			request.getRequestDispatcher(path).forward(request, response);
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
