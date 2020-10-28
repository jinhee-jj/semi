package com.kh.cool.board.freeboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.board.freeboard.model.service.CommentInfoService;
import com.kh.cool.board.freeboard.model.vo.CommentInfo;

/**
 * Servlet implementation class FreeBoardInsertReplyServlet
 */
@WebServlet("/insertReply.fb")
public class FreeBoardInsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeBoardInsertReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String writer = request.getParameter("writer");
		int bno = Integer.parseInt(request.getParameter("bno"));
		String content = request.getParameter("content");
		
		CommentInfo reply = new CommentInfo();
		reply.setCommentWriterId(writer);
		reply.setBoardNo(bno);
		reply.setCommentContent(content);
		
		ArrayList<CommentInfo> replyList = new CommentInfoService().insertReply(reply);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(replyList, response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
