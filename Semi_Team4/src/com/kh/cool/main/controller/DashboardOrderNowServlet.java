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
import com.kh.cool.main.model.vo.Order;

/**
 * Servlet implementation class DashboardOrderNowServlet
 */
@WebServlet("/orderNow.db")
public class DashboardOrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardOrderNowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String today = request.getParameter("today");
		today = today.replaceAll("-", "");
		String branchCode = request.getParameter("branchCode");
		
		ArrayList<Order> oList = new DashboardService().selectOrderNow(today, branchCode);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(oList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
