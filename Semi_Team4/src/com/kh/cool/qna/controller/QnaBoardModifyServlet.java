package com.kh.cool.qna.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.qna.model.service.QnaBoardService;
import com.kh.cool.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardModifyServlet
 */
@WebServlet("/modify.qa")
public class QnaBoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String bTitle = request.getParameter("title");
		String bContent = request.getParameter("content");

		QnaBoard qnaBoard = new QnaBoard();
		qnaBoard.setBoardNo(bNo);
		qnaBoard.setBoardTitle(bTitle);
		qnaBoard.setBoardContent(bContent);
		
		int result = new QnaBoardService().modifyOne(qnaBoard);
		
		String path="";
		if(result > 0) {
			path = "/selectOne.qa?bNo=" + bNo;

			response.sendRedirect(request.getContextPath() + path);
		} else {
			path = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "QnA 게시물 수정 실패!");
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
