package com.kh.cool.member.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.Wrapper.LoginWrapper;
import com.kh.cool.member.model.service.UpdateWrapper;
import com.kh.cool.member.model.service.MemberService;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class PasswordSearchServlet
 */
@WebServlet("/passwordSearch.me")
public class PasswordSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PasswordSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
		
		String memberId = request.getParameter("memberId");
		String deptCode = request.getParameter("deptCode");
		String memberName = request.getParameter("memberName");
		String memberEmail = request.getParameter("memberEmail");
		
			/*System.out.println("memberId servlet : " + memberId);
			System.out.println("deptCode servlet : " + deptCode);
			System.out.println("memberName servlet : " + memberName);
			System.out.println("memberEmail servlet : " + memberEmail);*/
		
		
		String page="";
		
		Member passEmail = new MemberService().PasswordSearch(memberId, deptCode, memberName, memberEmail);
		
		if(passEmail == null || !passEmail.getMemberEmail().equals(memberEmail)) {
			request.setAttribute("message", "비밀번호 찾기 : 아이디 혹은 이메일 정보가 맞지 않습니다.");
			page="views/common/errorPage.jsp";
			request.getRequestDispatcher(page).forward(request, response);
		}
		
		//Email server 설정
		
		String host = "smtp.naver.com"; //smtp 서버명
		final String user = "ju_hyen";   //네이버 아이디
		final String password = "T1XZG9M9DF37"; //네이버 비밀번호(애플리케이션 보안 비밀번호)
		int port=465; // 포트번호
		
		//Email받을 주소
		String toEmail = passEmail.getMemberEmail();
		
		
		
		//SMTP서버 정보 설정(객체생성)
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smpt.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		//임시비밀번호 랜덤 생성
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		
		for(int i =0; i<10; i++) {
			int rIndex = rnd.nextInt(3);
			switch(rIndex) {
			case 0:
				//a-z
				temp.append((char) ((int)(rnd.nextInt(26))+97)); break;		
			case 1:
                // A-Z
                temp.append((char) ((int)(rnd.nextInt(26)) + 65)); break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10))); break;
			
			}
		}
		
		String AuthenticationKey = temp.toString();
		
			//System.out.println("passwordServlet AuthenticationKey 임시비밀번호 : " + AuthenticationKey ); //임시비밀번호
		
		//-------------------------->비밀번호암호화 시작
		
		String pwd = new UpdateWrapper().updatePassword(AuthenticationKey);
		
			//System.out.println("임시비밀번호 pwup 암호화 : " + pwd);
		
		int result = new MemberService().updatePwd(memberId, pwd);
	
		//<--------------------------- 비밀번호암호화 끝
		
		
		
		
		
		
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String admin = user;
			String adminPwd = password;
			
			
			
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(admin, adminPwd);
			}
		});
		
		session.setDebug(true);
	
		try {

			Message mimeMessage = new MimeMessage(session); //MimeMessage 생성
			
			//발신자 메일주소
			mimeMessage.setFrom(new InternetAddress("ju_hyen@naver.com"));//보내는사람 전체이메일주소
			
			//수신자 메일주소
			//.CC(참조) .BCC(숨은참조)
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			
			//전송할 메일 제목
			mimeMessage.setSubject("COOL 임시비밀번호 안내메일입니다.");
			
			
			//메일 내용
			mimeMessage.setText("COOL 임시비밀번호 : " + temp + " \n 임시비밀번호로 로그인 후 비밀번호를 변경하세요.");
			
			//전송
			Transport.send(mimeMessage);
			//System.out.println("passwordServlet 임시비밀번호 메일 발송됨!!!!");
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
         page="/views/member/passwordSearch.jsp";
         //패스워드 바꿀때 뭘 바꿀지 조건에 들어가는 id
         request.setAttribute("loginMember", memberId);
         request.getRequestDispatcher(page).forward(request, response);
         //response.sendRedirect(page);
		
		
		
         
         
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
