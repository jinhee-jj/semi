package com.kh.cool.revenue.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.revenue.model.service.RevenueService;
import com.kh.cool.revenue.model.vo.BranchRevenue;

/**
 * Servlet implementation class RevenueSelectInquiry
 */
@WebServlet("/brIncomeYesterday.rv")
public class RevenueSelectBrIncomeYesterdayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevenueSelectBrIncomeYesterdayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter는 문자열로 값 전달됨
		String selBranch = request.getParameter("selBranch");
		String selDate = request.getParameter("selDate");
		//2020-09-14 -> 20200914 변환
		selDate = selDate.replaceAll("-", "");
		
		BranchRevenue br = new RevenueService().selectBrIncomeYesterday(selDate, selBranch);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(br, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
