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

/**
 * Servlet implementation class FaqSearchServlet
 */
@WebServlet("/search.faq")
public class FaqSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchType = request.getParameter("searchType");
		String searchVal = request.getParameter("textType");
		
		//System.out.println(searchType);
		//System.out.println(searchVal);
		
		Faq faq = new Faq();
		
		faq.setFaqCode(searchType);
		faq.setMemberName(searchVal);
		
		ArrayList<Faq> list = new FaqService().searchList(faq);
	
	String path = "";
		
		if(list != null) {
			path = "views/board/FaQ/FaQMain.jsp";
			request.setAttribute("list", list);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 검색 실패");
		}
		
		request.getRequestDispatcher(path).forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
