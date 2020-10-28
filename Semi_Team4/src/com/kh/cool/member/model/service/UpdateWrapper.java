package com.kh.cool.member.model.service;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class UpdateWrapper {

	public UpdateWrapper() {}
	
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
	
	
	//임시비밀번호 암호화
	public String updatePassword(String password) {
		String updatePassword = "";
		
		updatePassword = getSha512(password);
		
		
		return updatePassword;
	}
	
}


