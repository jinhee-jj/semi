package com.kh.cool.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.qna.model.service.QnaBoardService;
import com.kh.cool.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardSearchServlet
 */
@WebServlet("/search.qa")
public class QnaBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sortCondition = request.getParameter("sortCondition");
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchText");
		String currentPageTemp = request.getParameter("currentPage");
		
		int currentPage=1;
		if(currentPageTemp != null) {
			currentPage = Integer.parseInt(currentPageTemp);
		}
		
		ArrayList<QnaBoard> list = new QnaBoardService().searchAndSort(searchCondition, sortCondition, searchValue);

		String path="";
		
		if(list != null) {
			//QnaBoardSelectListServlet으로 전달
			path = "/selectList.qa";

			request.setAttribute("currentPage", currentPage);
			request.setAttribute("sortcondition", sortCondition);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("searchValue", searchValue);
			
			
			request.setAttribute("qnaList", list);
		} else {
			path = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "QnA 게시물 검색/정렬 실패");
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
