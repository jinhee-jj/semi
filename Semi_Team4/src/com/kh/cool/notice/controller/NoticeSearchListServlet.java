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
 * Servlet implementation class NoticeSearchListServlet
 */
@WebServlet("/selectSearchList.no")
public class NoticeSearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSearchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String selectSearch = request.getParameter("selectSearch");
		String searchCondition = request.getParameter("searchCondition");
		String searchValue = request.getParameter("searchValue");
		
		
		System.out.println("selectSearch : " + selectSearch);
		System.out.println("searchCondition : " + searchCondition);
		System.out.println("searchValue : " + searchValue);

	
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
		ArrayList<Notice> list = null;
		PageInfo pi = null;
		if(searchCondition != null) {
			
				
				if(searchCondition.equals("title")) {
					
					listCount = ns.getListCountWithTitle(searchValue);
				} else {
					listCount = ns.getListCountWithContent(searchValue);
				}
			
			
			System.out.println("전체 게시물 수 :  " + listCount);
			
			maxPage = (int)((double) listCount / limit + 0.9);
			
			startPage = (((int)((double) currentPage / limit + 0.9)) - 1) * 10 + 1;
			
			endPage = startPage + 10 - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			
			
			//최신순 일 때
			if(selectSearch.equals("latest")) {
				//제목으로 검색 시
				if(searchCondition.equals("title")) {
					
					list = ns.selectWithSearchValueTitleLatest(pi, searchValue);
				//내용으로 검색 시
				} else {
					list = ns.selectWithSearchValueContentLatest(pi, searchValue);
				}
				
			//조회수순 일 때
			} else {
				
				if(searchCondition.equals("title")) {
					list = ns.selectWithSearchValueTitleViewd(pi, searchValue);
				} else {
					list = ns.selectWithSearchValueContentViewd(pi, searchValue);
				}
			}
		}
		
		String path = "";
		if(list != null) {
			path = "views/board/notice/noticeList.jsp";
			request.setAttribute("listS", list);
			request.setAttribute("piS", pi);
		} else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "게시판 조회 실패!!");
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
