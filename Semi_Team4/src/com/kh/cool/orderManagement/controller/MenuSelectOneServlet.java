package com.kh.cool.orderManagement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.member.model.vo.Member;
import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.MenuOne;

/**
 * Servlet implementation class MenuSelectOneServlet
 */
@WebServlet("/selectOneMenu.mm")
public class MenuSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginMember");
		String writer = loginUser.getDeptCode();
		
		String bk = writer.substring(0, 3);
		if( bk.equals("BNH") ) {
			ArrayList<MenuOne> menuOne = new KioskService().selectOneMenu(writer,code);
			String page = "";
			if(menuOne != null) {
				page = "views/orderManagement/menuManagementDetail.jsp";
				
				request.setAttribute("menuOne", menuOne);
				request.setAttribute("code", code);
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "메뉴 상세보기 실패!!!");
			}
			
			request.getRequestDispatcher(page).forward(request, response);
		} else {
			String branch = request.getParameter("branch");
			
			ArrayList<MenuOne> menuOne = new KioskService().selectHoOneMenu(branch,code);
			String page = "";
			if(menuOne != null) {
				page = "views/orderManagement/menuManagementDetail.jsp";
				
				request.setAttribute("menuOne", menuOne);
				request.setAttribute("code", code);
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "메뉴 상세보기 실패!!!");
			}
			
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
