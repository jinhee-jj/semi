package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Inven;

/**
 * Servlet implementation class InvenStatusServlet
 */
@WebServlet({ "/InvenStatusServlet", "/status.in" })
public class InvenStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvenStatusServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branchCode = request.getParameter("invenStatus");
		/*String date = request.getParameter("StatusDate");
		
		java.sql.Date day = null;
		
		if(date != "") {
			day = java.sql.Date.valueOf(date);
		}else {
			day = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		}*/
		
		Inven statusInven = new Inven();
		//statusInven.setInsertDate(day);
		statusInven.setBranchCode(branchCode);
		
		//System.out.println("servlet : "+statusInven);
		
		ArrayList<Inven> list = new InvenService().statusInven(statusInven);
		
		String path = "";
		
		if(list != null) {
			path = "views/inven/InvenStatus.jsp";
			request.setAttribute("list", list);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "재고현황 조회 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
