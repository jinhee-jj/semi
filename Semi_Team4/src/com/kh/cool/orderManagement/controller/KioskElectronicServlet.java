package com.kh.cool.orderManagement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.KioskCart;
import com.kh.cool.orderManagement.model.vo.OrderResult;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class KioskElectronicServlet
 */
@WebServlet("/selectETB.kk")
public class KioskElectronicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KioskElectronicServlet() {
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
		
		ArrayList<OrderResult> order = new KioskService().selectOrderList(writer);
		
		int time = Integer.parseInt(request.getParameter("what"));
		
		if( (time % 2) == 0) {
			OrderResult or = new OrderResult();
			or.setKioskCode(writer);
			
			or = new KioskService().selectOrder(or);
			
			
			OrderResult update1 = new OrderResult();
			int updateK1 = 0;
			for(int i = 0; i < 1; i++) {
				
				if( order.size() != 0) {
					update1 = order.get(i);
					or.setOrderNo(update1.getOrderNo());
					
					updateK1 = new KioskService().orderUpdate(writer, or);
				} else {
					order = null;
					or = null;
					update1 = null;
				}
				
			}
		} else {
			
		}
		
		ArrayList<OrderResult> order2 = new KioskService().selectOrderList2(writer);
		
		if( (time % 3) == 0 ) {
			OrderResult resultUp = new OrderResult();
			resultUp.setKioskCode(writer);
			
			OrderResult or2= new OrderResult();
			OrderResult update2 = new OrderResult();
			int updateK2 = 0;
			for(int i = 0; i < 1; i++) {
				
				if(order2.size() != 0) {
					update2 = order2.get(i);
					or2.setOrderNo(update2.getOrderNo());
					
					updateK2 = new KioskService().resultUpdate(writer, or2);
				} else {
					order2 = null;
					or2 = null;
					update2 = null;
				}
				
				
			}
			
			
		} else {
			
		}
		
		
		
		
		String page = "";
		if(true ) {
			page = "views/kiosk/ElectronicBoard.jsp";
			
			request.setAttribute("orderNum", order);
			request.setAttribute("orderNUm2", order2);
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "전광판 오류");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
