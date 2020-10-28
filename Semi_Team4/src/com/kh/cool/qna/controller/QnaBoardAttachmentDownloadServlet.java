package com.kh.cool.qna.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.qna.model.service.QnaBoardService;
import com.kh.cool.qna.model.vo.Attachment;

/**
 * Servlet implementation class QnaBoardAttachmentDownloadServlet
 */
@WebServlet("/downloadAttachment.qa")
public class QnaBoardAttachmentDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardAttachmentDownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		Attachment file = new QnaBoardService().selectOneAttachment(num);
		
		//파일을 읽어올 입력 스트림 생성
		BufferedInputStream buf = null;
		
		//클라이언트로 내보낼 출력 스트림 생성
		ServletOutputStream downOut = response.getOutputStream();
		
		//전송할 파일 객체 생성
		File downFile = new File(file.getAttFileAddress() + file.getAttFileChangeName());
		
		//응답 헤더 설정
		response.setContentType("text/plain; charset=UTF-8");
		response.setContentLength( (int)downFile.length());
		response.setHeader("Content-Disposition", "attachment; filename=\""
							+ new String(file.getAttFileOriginName().getBytes("UTF-8"), "ISO-8859-1") + "\"");
		
		//내보내기
		FileInputStream fin = new FileInputStream(downFile);
		
		buf = new BufferedInputStream(fin);
		
		int readBytes = 0;
		while((readBytes = buf.read()) != -1) {
			downOut.write(readBytes);
		}
		
		downOut.close();
		buf.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
