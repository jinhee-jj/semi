package com.kh.cool.fAq.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.fAq.model.service.FaqService;
import com.kh.cool.fAq.model.vo.Faq;

/**
 * Servlet implementation class FaQUpdateInputServlet
 */
@WebServlet("/faqUpdateInput.faq")
public class FaQUpdateInputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaQUpdateInputServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ftitle = request.getParameter("fTitle");
		String fwriter = request.getParameter("fWriter");
		String insertF = request.getParameter("inputF");
		String insertQ = request.getParameter("inputQ");
		int faqNum = Integer.parseInt(request.getParameter("num"));
		
		
		Faq faq = new Faq();
		
		faq.setFaqTitle(ftitle);
		faq.setMemberId(fwriter);
		faq.setfContents(insertF);
		faq.setaContents(insertQ);
		faq.setfId(faqNum);
		
		int result = new FaqService().updateFaqInput(faq);
		
		String page = "";
		if(result >= 2 ) {
			response.sendRedirect(request.getContextPath() + "/faqList.faq");
		}else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 수정 실패");
			
			RequestDispatcher view = request.getRequestDispatcher(page);
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
