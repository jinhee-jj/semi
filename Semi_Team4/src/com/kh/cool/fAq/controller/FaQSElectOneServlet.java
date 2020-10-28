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
 * Servlet implementation class FaQSElectOneServlet
 */
@WebServlet("/selectOne.faq")
public class FaQSElectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaQSElectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		//System.out.println(num);
		
		Faq faq = new FaqService().selectOne(num);
		
		String path = "";
		if(faq != null) {
			path = "views/board/FaQ/FaQDetail.jsp";
			request.setAttribute("faq", faq);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 상세 조회 실패");
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
