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
 * Servlet implementation class InvenListDetailServlet
 */
@WebServlet("/listDetail.in")
public class InvenListDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvenListDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("date");
		//System.out.println(data);
		String[] detailData = data.split("\\?");
		
		String date = detailData[0];
		String branchCode = detailData[1];
		String igCode = detailData[2];
		/*
		java.sql.Date day = null;
		
		if(date != null) {
			day = java.sql.Date.valueOf(date);
		}else {
			day = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		}*/
		
		Inven detailInven = new Inven();
		//detailInven.setInsertDate(day);
		detailInven.setBranchCode(branchCode);
		detailInven.setIgretCode(igCode);
		
		ArrayList<Inven> list = new InvenService().branchDetail(detailInven);
		
		
		String path = "";
		
		if(list  != null) {
			path = "views/inven/InvenDetail.jsp";
			request.setAttribute("list", list);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "재고상세 조회 실패");
		}
		//System.out.println(list);
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
