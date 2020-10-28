package com.kh.cool.qna.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.qna.model.service.CommentService;
import com.kh.cool.qna.model.vo.Comment;

/**
 * Servlet implementation class CommentInsertServlet
 */
@WebServlet("/insertComment.qa")
public class CommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentWriterId = request.getParameter("commentWriterId");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String commentContent = request.getParameter("commentContent");
		int communityCode = Integer.parseInt(request.getParameter("communityCode"));
		
		Comment comment = new Comment();
		comment.setCommentWriterId(commentWriterId);
		comment.setBoardNo(boardNo);
		comment.setCommentContent(commentContent);
		comment.setCommunityCode(communityCode);
		
		//댓글 목록 반환
		ArrayList<Comment> commentList = new CommentService().insertCommentOne(comment);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(commentList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
