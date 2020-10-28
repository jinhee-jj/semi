package com.kh.cool.qna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.qna.model.service.QnaBoardService;
import com.kh.cool.qna.model.vo.PageInfo;
import com.kh.cool.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaSelectListServlet
 */
@WebServlet("/selectList.qa")
public class QnaBoardSelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardSelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String sortCondition = request.getParameter("sortCondition");
		String searchCondition = request.getParameter("searchCondition");
		String searchText = request.getParameter("searchText");
    	
    	ArrayList<QnaBoard> getList = null;
    	getList = (ArrayList<QnaBoard>) request.getAttribute("qnaList");
		
// request.getParameter("qnaList");
// 위 파라미터를 통해 문자열 형식의 ArrayList는 불러왔으나
// 사용불가 ㅠ
    	
		ArrayList<QnaBoard> list = new ArrayList<QnaBoard>();
		
		int currentPage;	// 현재 페이지
		int limit;			// 한 페이지에 보여지는 페이지 수
		int maxPage;		// 전체 중 마지막 페이지
		int startPage;		// 전체 페이지 중 보여지는 묶음의 첫 번째 페이지
		int endPage;		// 전체 페이지 중 보여지는 묶음의 마지막 페이지
		
		currentPage = 1;	// 현재 페이지 초기화
		
		// 현재 페이지 값을 전달받을 경우 해당 값으로 초기화
		if(getList != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		// 한 번에 보여지는 페이지 수 
		limit = 10;
		
		QnaBoardService qs = new QnaBoardService();
		
		
		int listCount = 0;			// 전체 게시물 개수
		if(getList != null) {
			listCount = getList.size();
		} else {
			listCount = qs.getListCount();
		}
		
		// 페이지 나눔 + 가중치 -> limit를 넘는 게시물이 최소 1개라도 있으면 새로운 페이지 생성
		maxPage = (int) ((double) listCount / limit + (1- ((double)1/limit)));
		
		// 보여지는 묶음 첫번째 페이지 
		startPage = ((int)((double) currentPage / limit + (1 - ((double)1/limit))) - 1) * 10 + 1;
		
		// 묶음 마지막 페이지
		endPage = startPage + limit -1;
		
		// 묶음 페이지 개수 모자라는 경우
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		// 페이징 정보 변수
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);

		if(getList != null) {
//			list = getList;
			list = qs.selectSearchedListWithPaging(getList, pi);
		
		} else {
			list = qs.selectListWithPaging(pi);
		}

		String path="";
		if(list != null) {
			path = "views/board/qna/qnaList.jsp";
			request.setAttribute("qnaList", list);
			request.setAttribute("pi", pi);
			request.setAttribute("sortCondition", sortCondition);
			request.setAttribute("searchCondition", searchCondition);
			request.setAttribute("searchText", searchText);
		} else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "QnA 게시판 조회 실패!");
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
