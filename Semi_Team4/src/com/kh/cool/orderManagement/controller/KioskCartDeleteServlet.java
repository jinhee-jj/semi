package com.kh.cool.orderManagement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class KioskCartDeleteServlet
 */
@WebServlet("/deleteCart.kk")
public class KioskCartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KioskCartDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cartNum = Integer.parseInt(request.getParameter("num"));
		String eat = request.getParameter("eat");
		String menu = request.getParameter("menu");
		
		int result = 0;
		if(0 < cartNum) {
			
			result = new KioskService().deleteCart(cartNum);
			
		} else {
			
			String numC = request.getParameter("output");
			String[] num56 = numC.split(",");
			
			for(int i = 0; i < num56.length;i++) {
				
				cartNum = Integer.parseInt(num56[i]);
				result = new KioskService().deleteCart(cartNum);
				
			}
			
		}
		
		String page = "";
		if(result > 0) {
			
			page = "cartList.kk?eat="+eat+"&menu="+menu;
			
		} else {
			page = "views/common/errorPage.jsp";
			
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
