package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.cool.member.model.vo.Member;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.PurchaseListBR;

/**
 * Servlet implementation class PurchaseHistorySelectOneServlet
 */
@WebServlet("/selectOneHistoryBR.pu")
public class PurchaseHistorySelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseHistorySelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");
		
		String branchCode = member.getDeptCode();
		
		
		//발주번호가 주문 건당이 아닌 품목별로 부여돼서 시간으로 조회해옴
		//상세보기 조회할 주문의 발주일자
		String date = request.getParameter("date");
		String hour = request.getParameter("hour");
		String min = request.getParameter("min");
		String sec = request.getParameter("sec");
		
//		System.out.println(date);
//		System.out.println(hour);
//		System.out.println(min);
//		System.out.println(sec);
		

		//조회일자
		
		//1초 뒤 (between and)로 범위 설정하여 조회하기 위해
		int oneSecLater1 = Integer.parseInt(sec) + 1;
		String dateTime = date + " " + hour + ":" + min+ ":" +sec;
		//System.out.println(dateTime);
		
		String oneSecLater;
		int min1 = 0;
		
		//초가 10 이하면 앞에 0붙여주기 
		if(oneSecLater1 < 10){
			oneSecLater = date + " " + hour + ":" + min + ":" + "0" + oneSecLater1;
		} else {
			
			//60이 되면 00으로 바꿔주고 1분 더하기
			if(oneSecLater1 == 60) {
				min1 += Integer.parseInt(min) + 1;
				oneSecLater = date + " " + hour + ":" + min1 + ":00" ;
			} else {
				oneSecLater = date + " " + hour + ":" + min + ":" + oneSecLater1;
			}
			
		}
//		System.out.println(oneSecLater);
		ArrayList<PurchaseListBR> list = new PurchaseService().selectpListWithDate(branchCode, dateTime, oneSecLater);
		
		//System.out.println("list 확인 : " + list);
	
		
		response.setContentType("application/json; charset=UTF-8");
			
		new Gson().toJson(list, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
