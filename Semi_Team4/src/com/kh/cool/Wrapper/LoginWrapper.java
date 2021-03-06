package com.kh.cool.Wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class LoginWrapper extends HttpServletRequestWrapper {

	public LoginWrapper(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String key) {
		String value="";

//MemberPwd로 바꿔야 필터 작동됨		
//20200920 17:35 수정된 내용입니다.
		if(key != null && key.equals("memberPwd")) {
			value = getSha512(super.getParameter("memberPwd"));
		} else {
			value = super.getParameter(key);
		}
		
		return value;
	}

	//Sha512 암호화 알고리즘
	private static String getSha512(String pwd) {
		String encPwd = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
			md.update(bytes);
			
			encPwd = Base64.getEncoder().encodeToString(md.digest());
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return encPwd;
	}
	
}
