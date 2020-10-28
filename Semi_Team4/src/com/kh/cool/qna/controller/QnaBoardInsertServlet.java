package com.kh.cool.qna.controller;

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
import com.kh.cool.qna.model.service.QnaBoardService;
import com.kh.cool.qna.model.vo.Attachment;
import com.kh.cool.qna.model.vo.QnaBoard;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class QnaBoardInsertServlet
 */
@WebServlet("/insert.qa")
public class QnaBoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaBoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(ServletFileUpload.isMultipartContent(request)) {
			
			
			//최대용량 [Byte]
			int maxSize = 1024 * 1024 * 10;
			
			String root = request.getSession().getServletContext().getRealPath("/");

			//확인완료
			//System.out.println("root: " + root);
			
			String filePath = root + "resources/board_uploadFiles/";
			
			MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			//저장된 파일 이름 보관
			ArrayList<String> saveFiles = new ArrayList<>();
			
			//원본 파일 이름 보관
			ArrayList<String> originalFiles = new ArrayList<>();
			
			//파일이 전송된 이름 반환
			Enumeration<String> files = multiRequest.getFileNames();
			
			//다음 파일이 있을 시 추가 저장
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
				saveFiles.add(multiRequest.getFilesystemName(name));
				originalFiles.add(multiRequest.getOriginalFileName(name));
			}
			
			String multiTitle = multiRequest.getParameter("title");
			String multiContent = multiRequest.getParameter("content");
			
			String boardWriter = ((Member) request.getSession().getAttribute("loginMember")).getMemberId();
			
			QnaBoard qnaBoard = new QnaBoard();
			qnaBoard.setBoardTitle(multiTitle);
			qnaBoard.setBoardContent(multiContent);
			qnaBoard.setBoardWriterId(boardWriter);
			
			ArrayList<Attachment> fileList = new ArrayList<Attachment>();
			
			for(int i=originalFiles.size()-1; i>=0; i--) {
				Attachment attachment = new Attachment();
				
				attachment.setAttFileAddress(filePath);
				attachment.setAttFileOriginName(originalFiles.get(i));
				attachment.setAttFileChangeName(saveFiles.get(i));
				
				fileList.add(attachment);
			}
			
			Map<String, Object> requestData = new HashMap<String, Object>();
			requestData.put("qnaBoard", qnaBoard);
			requestData.put("fileList", fileList);
			
			int result = new QnaBoardService().insertQna(requestData);
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/selectList.qa");
			
				// 실패할 경우 저장된 사진 삭제
			} else {
				
				for(int i=0; i<saveFiles.size(); i++) {
					File faildFile = new File(filePath + saveFiles.get(i));
					
					faildFile.delete();
				}
				
				request.setAttribute("message", "사진 게시판 등록 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}

			
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
