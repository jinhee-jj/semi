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
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class KioskCartListServlet
 */
@WebServlet("/cartList.kk")
public class KioskCartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KioskCartListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String eat = request.getParameter("eat");
		String menu = request.getParameter("menu");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginMember");
		String writer = loginUser.getDeptCode();
		
		int allCountResult = new KioskService().allCountCart(writer);
		int allPriceResult = new KioskService().allPriceCart(writer);
		
		ArrayList<KioskCart> cart = new KioskService().selectListCart(writer);
		
		String page = "";
		if(cart != null) {
			page ="views/kiosk/kioskCart.jsp";
			
			request.setAttribute("cartList", cart);
			request.setAttribute("eat", eat);
			request.setAttribute("menu", menu);
			request.setAttribute("allCount", allCountResult);
			request.setAttribute("allPrice", allPriceResult);
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "키오스크 불러오기 실패!!!!");
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
