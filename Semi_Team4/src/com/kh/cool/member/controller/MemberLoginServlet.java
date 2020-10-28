package com.kh.cool.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.member.model.service.MemberService;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/loginMember.me")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//System.out.println("MemberLoginServlet start : doPost");
		
		//1. 인코딩
		request.setCharacterEncoding("UTF-8");
		
		//2.전송값 꺼내서 변수에 담기"
		String deptCode = request.getParameter("deptCode"); //태그 name값
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
				
			/*//확인용
			System.out.println("deptCode: " + deptCode);
			System.out.println("memberId : " + memberId);
			System.out.println("memberPwd : " + memberPwd);
			
			//db저장 값(확인용 : 09월16일 임시비밀번호 발급 후 생성된 암호화 비밀번호)
			//11sDeJr6W9tM/PE5dlSDnWf6GL7d3QCSahKjy8YoK7VqFalCt424cmjLAv6exVZ4ACHmss7kaAUJONjPu3+rSA==
			 */		
		//3. 비지니스 로직 // 서비스 클래스의 메소드로 인자 전달(비지니스 로직 호출)
		Member loginMember = new MemberService().loginCheck(deptCode, memberId, memberPwd);
		
		/*System.out.println("MemberLogin Servlet" + loginMember);*/
		
		
		
		//4. 받은 결과에 따라 뷰 페이지 내보내기
		String page = "";
		if(loginMember != null) {
			page = "views/main/hoDashboard.jsp";

			HttpSession session = request.getSession(); 
			session.setAttribute("loginMember",loginMember);
	
			response.sendRedirect(page);
			
		}else {
			
			
			
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "로그인 에러!!!");
			
			request.getRequestDispatcher(page).forward(request, response);
		}
		
	}

}
