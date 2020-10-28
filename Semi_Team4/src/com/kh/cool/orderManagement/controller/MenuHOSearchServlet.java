package com.kh.cool.orderManagement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.BRANCH;
import com.kh.cool.orderManagement.model.vo.MenuM;

/**
 * Servlet implementation class MenuHOSearchServlet
 */
@WebServlet("/searchHOMenu.mm")
public class MenuHOSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuHOSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		String value = request.getParameter("value");
		String branchName = request.getParameter("branch");
		
		String bName = branchName.replaceAll("점", "");
		
		ArrayList<MenuM> menu = new KioskService().searchHOMenu(bName,search,value);
		
		ArrayList<BRANCH> branch = new KioskService().selectBranchList();
		
		String page = "";
		if(menu != null) {
			page = "views/orderManagement/menuManagement.jsp";
			
			request.setAttribute("menu", menu);
			request.setAttribute("branch", branch);
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "메뉴 검색 실패!!!");
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
