package com.kh.cool.fAq.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.fAq.model.service.FaqService;
import com.kh.cool.fAq.model.vo.Faq;

/**
 * Servlet implementation class FaQInsertServlet
 */
@WebServlet("/fAqInsert.faq")
public class FaQInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaQInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fTitle = request.getParameter("fTitle");
		String fWriter = request.getParameter("fWriter");
		String inputF = request.getParameter("inputF");
		String inputQ = request.getParameter("inputQ");
		
		Faq faq = new Faq();
		faq.setFaqTitle(fTitle);
		faq.setMemberId(fWriter);
		faq.setfContents(inputF);
		faq.setaContents(inputQ);
		
		int result = new FaqService().insertFaq(faq);
		
		String path = "";
		if(result >= 2) {
			response.sendRedirect(request.getContextPath() + "/faqList.faq");
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "FaQ게시판 등록 실패");
			request.getRequestDispatcher(path).forward(request, response);
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
