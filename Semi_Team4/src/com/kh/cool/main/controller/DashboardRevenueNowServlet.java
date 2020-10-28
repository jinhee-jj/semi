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
import com.kh.cool.main.model.vo.Revenue;

/**
 * Servlet implementation class DashboardRevenueNowServlet
 */
@WebServlet("/revenueNow.db")
public class DashboardRevenueNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardRevenueNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String today = request.getParameter("today");
		today = today.replaceAll("-", "");

		ArrayList<Revenue> rList = new DashboardService().selectRevenueNow(today);

		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(rList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
