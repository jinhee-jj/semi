package com.kh.cool.board.freeboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.board.freeboard.model.service.FreeBoardService;
import com.kh.cool.board.freeboard.model.vo.FreeBoard;

/**
 * Servlet implementation class FreeBoardSelectOne
 */
@WebServlet("/selectOne.fb")
public class FreeBoardSelectOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardSelectOne() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		FreeBoard board = new FreeBoardService().selectListOne(num);
		
		String page = "";
		if(board != null) {
			page = "views/board/freeBoard/freeBoardOne.jsp";
			
			request.setAttribute("board", board);
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "자유게시판 상세조회 실패!!!");
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
