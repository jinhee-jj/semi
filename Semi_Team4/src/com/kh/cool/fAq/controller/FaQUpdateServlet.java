package com.kh.cool.fAq.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.fAq.model.service.FaqService;
import com.kh.cool.fAq.model.vo.Faq;

/**
 * Servlet implementation class FaQUpdateServlet
 */
@WebServlet("/faqUpdate.faq")
public class FaQUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaQUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		Faq faq = FaqService.updatefaq(num);
		
		String path="";
		
		if(faq != null) {
			path = "views/board/FaQ/FaqUpdate.jsp";
			request.setAttribute("faq", faq);
			//request.setAttribute("pi", pi);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 수정 조회 실패");
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
