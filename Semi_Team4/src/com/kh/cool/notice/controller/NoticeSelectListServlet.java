package com.kh.cool.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.notice.model.service.NoticeService;
import com.kh.cool.notice.model.vo.Notice;
import com.kh.cool.notice.model.vo.PageInfo;

/**
 * Servlet implementation class NoticeSelectListServlet
 */
@WebServlet("/selectList.no")
public class NoticeSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int currentPage;			//현재 페이지
		int limit;				    //한 페이지에 게시글이 몇개(블럭)
		int maxPage;			    //가장 마지막 페이지
		int startPage;              //블럭의 시작페이지
		int endPage;		        //블럭의 마지막 페이지
		int listCount;				//전체 게시물 수
		
		
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		
		limit = 10;
		
		NoticeService ns = new NoticeService();
		
		listCount = ns.getListCount();
		
		System.out.println("전체 게시물 수 :  " + listCount);
		
		maxPage = (int)((double) listCount / limit + 0.9);
		
		startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
		
		endPage = startPage + 10 - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		ArrayList<Notice> list = null;
		
		String selectSearch = request.getParameter("selectSearch");
		
		if(request.getParameter("selectSearch") == null) {
			list = ns.selectListWithPaging(pi);
		
		} else if(request.getParameter("selectSearch") != null) {
			/*selectSearch = request.getParameter("selectSearch");*/
			
			System.out.println("selectSearch : " + selectSearch);
			if(selectSearch.equals("latest")) {
				
				list = ns.selectListWithPaging(pi);
			
				
			} else {
				list = ns.selectListWithPagingMostViewed(pi);
				
			}

		
		} 	
				
		
		String path = "";
		if(list != null) {
			path = "views/board/notice/noticeList.jsp";
			request.setAttribute("selectSearch", selectSearch);
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
		} else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "게시판 조회 실패!!");
		}
		request.getRequestDispatcher(path).forward(request, response);
		System.out.println("selectSearch2 :  " + selectSearch );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
