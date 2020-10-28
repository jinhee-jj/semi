package com.kh.cool.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.notice.model.service.NoticeService;
import com.kh.cool.notice.model.vo.Attachment;
import com.kh.cool.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeSelectOneServlet
 */
@WebServlet("/selectOne.no")
public class NoticeSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		Notice notice = new NoticeService().selectOneNoticeByNno(nno);
		
		System.out.println("notice check in servlet : " + notice);
		
		//첨부파일 있는지 확인하기 위해 게시글의 게시글번호 불러옴
		int bno = notice.getBoardNo();
		
		System.out.println(bno);
		ArrayList<Attachment> atList = new NoticeService().checkHasAttachment(bno);
		System.out.println("atList: " + atList);
		
		String page = "";
		
		//첨부파일 있는 경우
		if(notice != null && atList != null) {
			page ="views/board/notice/noticeDetail.jsp";
			request.setAttribute("atList", atList);
			request.setAttribute("notice", notice);
		//없는 경우
		} else if (notice != null && atList == null) {
			page ="views/board/notice/noticeDetail.jsp";
			request.setAttribute("notice", notice);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "공지사항 상세조회 실패");
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
