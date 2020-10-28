package com.kh.cool.board.freeboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.board.freeboard.model.service.FreeBoardService;
import com.kh.cool.board.freeboard.model.vo.FreeBoard;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class FreeBoardInsertServlet
 */
@WebServlet("/insert.fb")
public class FreeBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int community = Integer.parseInt(request.getParameter("community"));
		String ctName = "자유";
		
		if(community == 2) {
			ctName = "자유게시판";
		} else {
			ctName = "버그";
		}
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginMember");
		String writer = loginUser.getMemberId();
		
		FreeBoard newFreeBoard = new FreeBoard();
		newFreeBoard.setBoardTitle(title);
		newFreeBoard.setBoardContent(content);
		newFreeBoard.setCommunityCode(community);
		newFreeBoard.setCommunityName(ctName);
		newFreeBoard.setBoardWriterId(writer);
	
		int result = new FreeBoardService().insertFreeBoard(newFreeBoard);
		
		String path = "";
		if(result > 0) {
			path = "selectList.fb?community=2";
			
		} else {
			path = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "게시판 작성 실패!!!!!");
			
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
