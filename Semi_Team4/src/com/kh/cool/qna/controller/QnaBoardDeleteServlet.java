package com.kh.cool.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.qna.model.service.QnaBoardService;

/**
 * Servlet implementation class QnaBoardDeleteServlet
 */
@WebServlet("/delete.qa")
public class QnaBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		
		int result =  new QnaBoardService().deleteOne(bNo);
		
		String path = "";
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/selectList.qa");
		
		} else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "QnA게시판 게시물 삭제 실패!");
			
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
