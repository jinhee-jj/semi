package com.kh.cool.fAq.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.fAq.model.service.FaqService;
import com.kh.cool.fAq.model.vo.Faq;
import com.kh.cool.fAq.model.vo.PageInfo;

/**
 * Servlet implementation class FaQListSelectServlet
 */
@WebServlet("/faqList.faq")
public class FaQListSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaQListSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("faq");
		
		ArrayList<Faq> list = new FaqService().selectList();
		
		String path = "";
		
		if(list != null) {
			path = "views/board/FaQ/FaQMain.jsp";
			request.setAttribute("list", list);
			//request.setAttribute("pi", pi);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 리스트 조회 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
		
	/*	  //페이징
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		limit = 10;
		
		FaqService fs = new FaqService();
		
		int listCount = fs.getListCount();
		
		maxPage = (int)((double) listCount / limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;

		endPage = startPage + 10 - 1;
		
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<Faq> list = fs.selectListWithPaging(pi);
		
		String path = "";
		
		if(list != null) {
			path = "views/board/FaQ/FaQMain.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 리스트 조회 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
