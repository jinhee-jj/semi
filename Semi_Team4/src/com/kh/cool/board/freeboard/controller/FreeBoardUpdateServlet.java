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
 * Servlet implementation class FreeBoardUpdateServlet
 */
@WebServlet("/update.fb")
public class FreeBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		FreeBoard updateBoard = new FreeBoard();
		updateBoard.setBoardTitle(title);
		updateBoard.setBoardContent(content);
		updateBoard.setBoardNo(num);
		
		FreeBoard changeFreeBoardInfo = new FreeBoardService().updateBoard(num, updateBoard);
		String page = "";
		if(changeFreeBoardInfo != null) {
			page = "selectList.fb?community=2";
			
			
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "게시판 수정 실패!!!!");
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
