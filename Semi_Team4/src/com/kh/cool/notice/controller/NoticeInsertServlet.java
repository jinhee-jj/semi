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
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/insertNotice.no")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인유저 아이디(작성자), 작성한 글 제목과 내용 가져오기
/*		String writer = ((Member) request.getSession().getAttribute("loginMember")).getMemberId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");*/
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1024 * 1024 * 10;
			
			String root = request.getSession().getServletContext().getRealPath("/");
			System.out.println(root);
			
			String filePath = root + "resources/board_uploadFiles/";
			
			
			MultipartRequest multiRequest = 
					new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			ArrayList<String> saveFiles = new ArrayList<>();
			
			ArrayList<String> originFiles = new ArrayList<>();
			
			Enumeration<String> files = multiRequest.getFileNames();
			
			
			while(files.hasMoreElements()) {
				String name = files.nextElement();
				
				System.out.println("name : " + name);
				
				saveFiles.add(multiRequest.getFilesystemName(name));
				originFiles.add(multiRequest.getOriginalFileName(name));
				
				System.out.println("fileSystem name : " + saveFiles);
				System.out.println("originFile name : " + originFiles);
				
				String title = multiRequest.getParameter("title");
				String content = multiRequest.getParameter("content");
				
				System.out.println("title : " + title);
				System.out.println("content : " + content);
				
				String writer = ((Member) request.getSession().getAttribute("loginMember")).getMemberId();
				
				Notice notice = new Notice();
				notice.setnTitle(title);
				notice.setnContent(content);
				notice.setnWriter(writer);
				
				ArrayList<Attachment> fileList = new ArrayList<Attachment>();
				
				for(int i = originFiles.size() - 1; i>=0; i--) {
					Attachment at = new Attachment();
					
					at.setFilePath(filePath);
					at.setOriginName(originFiles.get(i));
					at.setChangeName(saveFiles.get(i));
					
					fileList.add(at);
				}
				
				System.out.println("upload fileList : " + fileList);
				
				Map<String, Object> requestData = new HashMap<String, Object>();
				
				requestData.put("notice", notice);
				requestData.put("fileList", fileList);
				
				System.out.println("requstData : " + requestData);
				
				int result = new NoticeService().insertNotice(requestData);
				
				if( result > 0 ) {
					response.sendRedirect(request.getContextPath() + "/selectList.no");
				} else {
					for(int i = 0; i < saveFiles.size(); i++) {
						File failedFile = new File(filePath + saveFiles.get(i));
						
						failedFile.delete();
					}
					request.setAttribute("message", "공지사항 등록 실패");
					request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
				}
				
				
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
