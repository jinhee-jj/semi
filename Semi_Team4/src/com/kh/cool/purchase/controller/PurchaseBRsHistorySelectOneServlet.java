package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.PurchaseListBR;

/**
 * Servlet implementation class PurchaseBRsHistorySelectOneServlet
 */
@WebServlet("/selectOneHistoryfromBR.pu")
public class PurchaseBRsHistorySelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseBRsHistorySelectOneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//지점발주내역에서 상세보기 누를 때
		
		//상세보기 조회할 주문 발주일자 받아옴
		String date = request.getParameter("date");
		String hour = request.getParameter("hour");
		String min = request.getParameter("min");
		String bCode = request.getParameter("bCode");
		
		/*System.out.println("date : " + date);
		System.out.println("hour : " + hour);
		System.out.println("min : " + min);
		System.out.println("bCode : " + bCode);*/
		
		//조회일자
		String dateTime = date + " " + hour + ":" + min;
		//1분 뒤
		int oneMinLater1 = Integer.parseInt(min) + 1;
		//between and~ 로 조회하기 위해 만들어줌
		String oneMinLater = date + " " + hour + ":" + (oneMinLater1);
		
		ArrayList<PurchaseListBR> list = new PurchaseService().selectpListWithDate(bCode, dateTime, oneMinLater);
		
		System.out.println("전체 발주 내역 list 확인 : " + list);
	
		
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
