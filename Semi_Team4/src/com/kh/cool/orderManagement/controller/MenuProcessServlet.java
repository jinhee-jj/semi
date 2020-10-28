package com.kh.cool.orderManagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.member.model.vo.Member;
import com.kh.cool.orderManagement.model.service.KioskService;

/**
 * Servlet implementation class MenuProcessServlet
 */
@WebServlet("/updateMenu.mm")
public class MenuProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuProcessServlet() {
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
		
		String menus = request.getParameter("menu");
		String state = request.getParameter("state");
		
		String[] menuArr = menus.split(",");
		
		String bwhat = writer.substring(0,3);
		
		if( bwhat.equals("BNH") ) {
			int update = new KioskService().menuUpdate(writer,state,menuArr);
			
			String page = "";
			if(update > 0) {
				page = "selectList.mm";
				
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "업데이트 실패~~");
			}
			
			request.getRequestDispatcher(page).forward(request, response);
		} else {
			String branch = request.getParameter("branch");
			
			int update = new KioskService().menuHoUpdate(branch,state,menuArr);
			
			String page = "";
			if(update > 0) {
				page = "selectList.mm";
				
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "업데이트 실패~~");
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
