package com.kh.cool.revenue.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.revenue.model.service.RevenueService;
import com.kh.cool.revenue.model.vo.RevenueStatistics;

/**
 * Servlet implementation class RevenueSelectDayIncomeTable
 */
@WebServlet("/hoIncomeListDay.rv")
public class RevenueSelectHoIncomeListDayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevenueSelectHoIncomeListDayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<RevenueStatistics> rsList = new RevenueService().selectHoIncomeListDay();
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(rsList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
