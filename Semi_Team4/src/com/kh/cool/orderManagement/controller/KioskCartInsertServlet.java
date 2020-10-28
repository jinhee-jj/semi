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
import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.KioskCart;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class KioskCartInsertServlet
 */
@WebServlet("/cartInsert.kk")
public class KioskCartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KioskCartInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String eat = request.getParameter("eat");
		String menuCode = request.getParameter("menuCode");
		String image = request.getParameter("menuImage");
		String menuName = request.getParameter("menuName").trim();
		int count = Integer.parseInt(request.getParameter("count").trim());
		int menuPrice = Integer.parseInt(request.getParameter("menuPrice").trim());
		int allCount = Integer.parseInt(request.getParameter("allCount").trim());
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginMember");
		String writer = loginUser.getDeptCode();
		
		KioskCart cart = new KioskCart();
		cart.setEat(eat);
		cart.setMenuCode(menuCode);
		cart.setImage(image);
		cart.setMenuName(menuName);
		cart.setCount(count);
		cart.setMenuPrice(menuPrice);
		cart.setAllCount(allCount);
		cart.setBranchCode(writer);
		
		int result = new KioskService().insertCart(cart, eat, writer);
		
		String page = "";
		if(result > 0) {
			page = "views/kiosk/kioskCart.jsp";
			
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "장바구니에 데이터 넣기 실패!");
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
