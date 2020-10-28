package com.kh.cool.main.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.main.model.service.DashboardService;
import com.kh.cool.main.model.vo.Notice;

/**
 * Servlet implementation class DashboardNoticeOverviewServlet
 */
@WebServlet("/overviewNotice.db")
public class DashboardNoticeOverviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardNoticeOverviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int row = Integer.parseInt(request.getParameter("row"));
		
		ArrayList<Notice> noticeList = new DashboardService().selectNoticeOverviwe(row);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(noticeList, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
