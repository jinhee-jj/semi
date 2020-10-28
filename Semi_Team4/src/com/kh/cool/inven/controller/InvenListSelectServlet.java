package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Inven;

/**
 * Servlet implementation class InvenListSelectServlet
 */
@WebServlet("/invenListDate.in")
public class InvenListSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvenListSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String branchCode = request.getParameter("branchCode");
		String startDay = request.getParameter("startDay");
		String endDay = request.getParameter("endDay");
		String igCode = request.getParameter("igCode");
		String invenType = request.getParameter("invenType");
		
		//System.out.println(branchCode + " => bco " + startDay + " => sd " + endDay + " => ed " + igCode + "=> igcode");
	
		java.sql.Date startDate = null;
		java.sql.Date endDate = null;
		
		if(startDay != "") {
			startDate =  java.sql.Date.valueOf(startDay);
		}else {
			startDate = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		}
		
		if(endDay != "") {
			endDate =  java.sql.Date.valueOf(endDay);
		}else {
			endDate = new java.sql.Date(new GregorianCalendar().getTimeInMillis());
		}
		
		
		Inven selectInvenList = new Inven();
		
		selectInvenList.setBranchCode(branchCode);
		selectInvenList.setMfDate(startDate);
		selectInvenList.setExDate(endDate);
		selectInvenList.setIgretCode(igCode);
		selectInvenList.setInvenSelectType(invenType);
		
		ArrayList<Inven> list = new InvenService().invenInputList(selectInvenList);
	
		//System.out.println("servet : "+list);
		
		String path = "";
		
		if(list != null) {
			path = "views/inven/InvenList.jsp";
			request.setAttribute("list", list);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "해당 재고 입고리스트 조회 실패");
		}
		//System.out.println(list);
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
