package com.kh.cool.orderManagement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.cool.member.model.vo.Member;
import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.OrderDetail;
import com.kh.cool.orderManagement.model.vo.OrderResult;

/**
 * Servlet implementation class OrderBreakdownDateServlet
 */
@WebServlet("/search.om")
public class OrderBreakdownSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderBreakdownSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginMember");
		String writer = loginUser.getDeptCode();
		
		String bk = writer.substring(0, 3);
		if( bk.equals("BNH") ) {
			
			String kiosk = new KioskService().selectKiosk(writer);
			
			String[] kioskArr = kiosk.split(",");
			
			ArrayList<OrderResult> history = new KioskService().searchOHistory(kioskArr,date);
			
			ArrayList<OrderDetail> detail = new KioskService().searchODetail(kioskArr,date);
			
/*			String page = "";
			if( history != null && detail != null) {
				page = "views/orderManagement/orderBreakdown.jsp";
				
				request.setAttribute("history", history);
				request.setAttribute("detail", detail);
				
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "주문내역 불러오기 실패!");
			}
			
			request.getRequestDispatcher(page).forward(request, response);*/
			
			response.setContentType("application/json; charset=UTF-8");
			Gson gson = new Gson();
			gson.toJson(history, response.getWriter());
			
		} else {
			String zizum = request.getParameter("zizum");
			
			String kiosk = new KioskService().selectKiosk(zizum);
			
			String[] kioskArr = kiosk.split(",");
			
			ArrayList<OrderResult> history = new KioskService().searchOHistory(kioskArr,date);
			
			response.setContentType("application/json; charset=UTF-8");
			Gson gson = new Gson();
			gson.toJson(history, response.getWriter());
			
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
