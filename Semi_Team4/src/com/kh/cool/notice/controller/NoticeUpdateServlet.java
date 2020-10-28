package com.kh.cool.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.cool.common.MyFileRenamePolicy;
import com.kh.cool.member.model.vo.Member;
import com.kh.cool.notice.model.service.NoticeService;
import com.kh.cool.notice.model.vo.Attachment;
import com.kh.cool.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/update.no")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		System.out.println(title + ", " + content + ", " + nno);
		
		Notice updateNotice = new Notice();
		
		updateNotice.setnTitle(title);
		updateNotice.setnContent(content);
		updateNotice.setNno(nno);
		
		int result = new NoticeService().updateNotice(updateNotice);
		
		String page = "";
		
		if(result > 0) {
			response.sendRedirect("/st4/selectOne.no?nno=" + nno);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "공지사항 수정 실패");
			request.getRequestDispatcher(page).forward(request,response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
