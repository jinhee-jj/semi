package com.kh.cool.fAq.model.service;

import static com.kh.cool.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.fAq.model.dao.FaqDao;
import com.kh.cool.fAq.model.vo.Faq;
import com.kh.cool.fAq.model.vo.PageInfo;

public class FaqService {

	public int getListCount() {
		Connection con = getConnection();
		
		int listCount = new FaqDao().getListCount(con);
		close(con);
		
		return listCount;
	}

	public ArrayList<Faq> selectListWithPaging(PageInfo pi) {
		Connection con = getConnection();
		
		ArrayList<Faq> list = new FaqDao().selectListWithPaging(con, pi);
		
		close(con);
		
		return list;
	}

	public ArrayList<Faq> selectList() {
		Connection con = getConnection();
		
		ArrayList<Faq> list = new FaqDao().selectList(con);
		
		close(con);
		
		return list;
	}

	public Faq selectOne(int num) {
		Connection con = getConnection();
		
		Faq faq = null;
		
		int result = new FaqDao().updataCount(con, num);
		
		if(result > 0) {
			faq = new FaqDao().selectOne(con, num);
			if(faq != null) {
				commit(con);
			}else {
				rollback(con);
			}
		}else {
			rollback(con);
		}
		
		close(con);
		
		return faq;
	}

	public int insertFaq(Faq faq) {
		Connection con = getConnection();
		int result = 0;
		
		int result1 = new FaqDao().insertFaq(con, faq);
		
		int num = new FaqDao().selectNum(con, faq);
		//System.out.println(faq);
		faq.setqId(num);
		//System.out.println(num);
		
		int result2 = new FaqDao().insertFaq2(con, faq);
		
		//System.out.println(faq);
		
		result = result1 + result2;
		
		//System.out.println(result);
		
		if(result >= 2) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public static Faq updatefaq(int num) {
		Connection con = getConnection();
		
		FaqDao fd = new FaqDao();
		
		Faq faq = null;
		
		
		int result = fd.updataCount(con, num);
		
		if(result > 0) {
			faq = new FaqDao().selectOne(con, num);
			if(faq != null) {
				commit(con);
			}else {
				rollback(con);
			}
		}else {
			rollback(con);
		}
		
		close(con);
		
		return faq;
	}

	public int updateFaqInput(Faq faq) {
		Connection con = getConnection();
		int result = 0;
		
		int result1 = new FaqDao().updateFaq1(con, faq);
		
		int result2 = new FaqDao().updateFaq2(con, faq);
		
		
		result = result1 + result2;
		
		
		if(result >= 2) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
		
	}

	public int delPage(int delnum) {
		Connection con = getConnection();
		int result = 0;
		
		result = new FaqDao().delFaq(con, delnum);
		
		if(result > 0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<Faq> selectListSee() {
		Connection con = getConnection();
		
		ArrayList<Faq> list = new FaqDao().selectListSee(con);
		
		close(con);
		
		return list;
	}

	public ArrayList<Faq> searchList(Faq faq) {
		Connection con = getConnection();
		ArrayList<Faq> list = null;
		
		if((faq.getFaqCode()).equals("title")) {
			list = new FaqDao().searchList(con, faq);
			
		}else if((faq.getFaqCode()).equals("contents")) {
			list = new FaqDao().searchList2(con, faq);
			
		}
		
		
		close(con);
		
		return list;
	}

}
