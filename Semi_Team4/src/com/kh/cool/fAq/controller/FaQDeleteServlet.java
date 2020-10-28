package com.kh.cool.fAq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.fAq.model.service.FaqService;

/**
 * Servlet implementation class FaQDeleteServlet
 */
@WebServlet("/faqDel.faq")
public class FaQDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaQDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int delnum = Integer.parseInt(request.getParameter("num"));
		
		int result = new FaqService().delPage(delnum);
		
		String path = "";
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/faqList.faq");
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 삭제 실패");
			
			RequestDispatcher view = request.getRequestDispatcher(path);
			view.forward(request, response);
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
