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
import com.kh.cool.board.freeboard.model.vo.PageInfo;

/**
 * Servlet implementation class FreeBoardSelectListServlet
 */
@WebServlet("/selectList.fb")
public class FreeBoardSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int community = Integer.parseInt(request.getParameter("community"));
		
/*		ArrayList<FreeBoard> list = new FreeBoardService().selectList(community);
		
		String path = "";
		if(list != null) {
			path = "views/board/freeBoard/freeBoardList.jsp";
			request.setAttribute("list", list);
			
		} else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "자유게시판 조회 실패!!");
		}
		
		request.getRequestDispatcher(path).forward(request, response);*/
		
		
		
		int currentPage; //현재 페이지
		int limit; 		 //한 페이지에 게시글이 몇 개가 보여질지
		int maxPage;     //전체 페이지에서 가장 마지막 페이지
		int startPage;	 //한 번에 표시될 페이지가 시작할 페이지
		int endPage;     //한 번에 표시될 페이지가 끝나는 페이지
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		limit = 10;
		
		FreeBoardService fs = new FreeBoardService();
		
		int listCount = fs.getListCount();
		
		//총 페이지 수 계산
		//예를 들면 모록 갯수가 123개 이면, 총 13개 페이지가 필요하다.
		
		maxPage = (int) ((double)listCount / limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
		
		endPage = startPage + 10 - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<FreeBoard> list = fs.selectList(community,pi);
		
		String page = "";
		if(list != null) {
			page = "views/board/freeBoard/freeBoardList.jsp";
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "게시판 조회 실패!!!");
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
 