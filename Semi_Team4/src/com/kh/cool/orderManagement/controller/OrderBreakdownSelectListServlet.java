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
import com.kh.cool.member.model.vo.Member;
import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.BRANCH;
import com.kh.cool.orderManagement.model.vo.OrderDetail;
import com.kh.cool.orderManagement.model.vo.OrderResult;

/**
 * Servlet implementation class OrderBreakdownSelectListServlet
 */
@WebServlet("/selectBreak.om")
public class OrderBreakdownSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderBreakdownSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginMember");
		String writer = loginUser.getDeptCode();
		
		String bk = writer.substring(0, 3);
		if( bk.equals("BNH") ) {
			
			String kiosk = new KioskService().selectKiosk(writer);
			
			String[] kioskArr = kiosk.split(",");
			
			ArrayList<OrderResult> history = new KioskService().selectOHistory(kioskArr);
			
			ArrayList<OrderDetail> detail = new KioskService().selectODetail(kioskArr);
			
			String page = "";
			if( history != null && detail != null) {
				page = "views/orderManagement/orderBreakdown.jsp";
				
				request.setAttribute("history", history);
				request.setAttribute("detail", detail);
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "주문내역 불러오기 실패!");
			}
			
			request.getRequestDispatcher(page).forward(request, response);
			
		} else {
			ArrayList<BRANCH> branch = new KioskService().selectBranchList();
			
/*			String page = "";
			if( history != null && detail != null ) {
				page = "views/orderManagement/orderBreakdown.jsp";
				
				request.setAttribute("branch", branch);
				request.setAttribute("history", history);
				request.setAttribute("detail", detail);
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "주문내역 불러오기 실패!");
			}
			
			request.getRequestDispatcher(page).forward(request, response);*/
			String page = "";
			page = "views/orderManagement/orderBreakdown.jsp";
			request.setAttribute("branch", branch);
			request.getRequestDispatcher(page).forward(request, response);
			
			
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
