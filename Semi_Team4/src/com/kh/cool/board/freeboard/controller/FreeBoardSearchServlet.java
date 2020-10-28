package com.kh.cool.board.freeboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.board.freeboard.model.service.FreeBoardService;
import com.kh.cool.board.freeboard.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeBoardSearchServlet
 */
@WebServlet("/search.fb")
public class FreeBoardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String radio = request.getParameter("radio");
		String select = request.getParameter("select");
		String search = request.getParameter("search");
		
		ArrayList<FreeBoard> list = new FreeBoardService().FreeBoardSearch(radio,select,search);
		
		String page = "";
		if(list != null) {
			page = "views/board/freeBoard/freeBoardList.jsp";
			
			request.setAttribute("list", list);
		} else {
			page = "views/common/errorPage";
			
			request.setAttribute("message", "검색 실패!!!");
		}
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
