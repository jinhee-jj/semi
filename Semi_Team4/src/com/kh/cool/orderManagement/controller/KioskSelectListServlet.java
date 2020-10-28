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
import com.kh.cool.board.freeboard.model.vo.BottomMenu;
import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.Kiosk;
import com.kh.cool.orderManagement.model.vo.KioskCart;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class KioskSelectListServlet
 */
@WebServlet("/select.kk")
public class KioskSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KioskSelectListServlet() {
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
		
		String branch = new KioskService().selectBranch(writer);
		
		ArrayList<Kiosk> menuList = new KioskService().selectList(eat, branch, menu);
		
		ArrayList<KioskCart> cart = new KioskService().selectListCart(writer);
		
		String page = "";
		if(menuList != null) {
			page ="views/kiosk/kioskMain.jsp";
			
			request.setAttribute("menuList", menuList);
			request.setAttribute("eat", eat);
			request.setAttribute("menu", menu);
			request.setAttribute("allC", allCountResult);
			request.setAttribute("allP", allPriceResult);
			request.setAttribute("cart", cart);
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "키오스크 불러오기 실패!!!!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
/*		if( request.getParameter("menuName") != null && request.getParameter("count") != null) {
			
			String menuName = request.getParameter("menuName").trim();
			int count = Integer.parseInt(request.getParameter("count").trim());
			int menuPrice = Integer.parseInt(request.getParameter("menuPrice").trim());
			int allCount = Integer.parseInt(request.getParameter("allCount").trim());
			
			BottomMenu bt = new BottomMenu();
			bt.setMenuName(menuName);
			bt.setCount(count);
			bt.setMenuPrice(menuPrice);
			bt.setAllCount(allCount);
			
			System.out.println("bt : " + bt);
			
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson(bt, response.getWriter());
			
		}*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
