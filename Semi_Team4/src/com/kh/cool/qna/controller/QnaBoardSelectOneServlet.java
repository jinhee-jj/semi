package com.kh.cool.qna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.qna.model.service.QnaBoardService;
import com.kh.cool.qna.model.vo.Attachment;
import com.kh.cool.qna.model.vo.QnaBoard;

/**
 * Servlet implementation class QnaBoardSelectOneServlet
 */
@WebServlet("/selectOne.qa")
public class QnaBoardSelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardSelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		
		HashMap<String, Object> hmap = null;
		hmap = new QnaBoardService().selectQnaBoardOne(bNo);

		String path="";
		if(hmap != null) {
			QnaBoard qnaBoard = (QnaBoard) hmap.get("qnaBoard");
			
			ArrayList<Attachment> fileList = (ArrayList<Attachment>) hmap.get("attachment");
			
			request.setAttribute("qnaBoard", qnaBoard);
			request.setAttribute("fileList", fileList);
	
			path="views/board/qna/qnaDetail.jsp";
		} else {
			request.setAttribute("message", "질의응답 게시판 상세보기 실패!");
			
			path="views/common/errorPage.jsp";
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
