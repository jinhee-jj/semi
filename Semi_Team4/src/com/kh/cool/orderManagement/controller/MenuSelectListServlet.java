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
import com.kh.cool.orderManagement.model.vo.BRANCH;
import com.kh.cool.orderManagement.model.vo.MenuM;

/**
 * Servlet implementation class MenuSelectListServlet
 */
@WebServlet("/selectList.mm")
public class MenuSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuSelectListServlet() {
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
		
		String what = writer.substring(0,3);
		
		if( what.equals("BNH") ) {
			
			ArrayList<MenuM> menu = new KioskService().selectMenuList(writer);
			
			String page = "";
			if(menu != null) {
				page = "views/orderManagement/menuManagement.jsp";
				
				request.setAttribute("menu", menu);
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "메뉴 불러오기 실패!!");
			}
			
			request.getRequestDispatcher(page).forward(request, response);
			
		} else {
			
			ArrayList<BRANCH> branch = new KioskService().selectBranchList();
			
			
			String page = "";
			if(branch != null) {
				page = "views/orderManagement/menuManagement.jsp";
				
				request.setAttribute("branch", branch);
			} else {
				page = "views/common/errorPage.jsp";
				
				request.setAttribute("message", "메뉴 불러오기 실패!!");
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
