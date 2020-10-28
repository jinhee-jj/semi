package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.SupplierOrder;

/**
 * Servlet implementation class HOselectHOpurchaseList
 */
@WebServlet("/selectHOpuList.pu")
public class HOselectHOpurchaseList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HOselectHOpurchaseList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<SupplierOrder> list = null;
		
		//메뉴바에서 버튼 클릭한 경우(검색조건 값 없음)
		if(request.getParameter("searchCondition") == null && request.getParameter("searchValue") == null) {
			list = new PurchaseService().selectHOpuList();
			
		//발주내역 안에서 검색버튼이나 조회버튼 클릭시
		} else {
			
			String searchCondition = request.getParameter("searchCondition");
			String searchValue = request.getParameter("searchValue");
			System.out.println("searchCondition" + searchCondition);
			System.out.println("searchValue" + searchValue);
			
			//검색키 ""일때 -> 아무검색 하지 x
			if(searchValue.equals("")) {
				//날짜 들어왔을 때
				if(!request.getParameter("startDay").equals("") && !request.getParameter("endDay").equals("")) {
					String startDay = request.getParameter("startDay");
					String endDay = request.getParameter("endDay");
					System.out.println("startDay : " + startDay);
					System.out.println("endDay : " + endDay);
					
					list = new PurchaseService().selectHOpuListWithDate(startDay, endDay);
					
				//날짜도 x 일 때
				} else {
					list = new PurchaseService().selectHOpuList();
				}
			//검색키 있을 때
			} else {
				
				//거래처명으로 검색
				if(searchCondition.equals("거래처명")) {
					
					//날짜 들어왔을 때
					if(!request.getParameter("startDay").equals("") && !request.getParameter("endDay").equals("")) {
						String startDay = request.getParameter("startDay");
						String endDay = request.getParameter("endDay");
						System.out.println("startDay : " + startDay);
						System.out.println("endDay : " + endDay);
						
						list = new PurchaseService().selectHOpuListWithDateSearch(startDay, endDay, searchValue);
					
						
					//날짜 x 일 때
					} else {
						
						list = new PurchaseService().selectHOpuListWithSearch(searchValue);
					}
				
				//거래처코드로 검색
				} else {
					
					//날짜 들어왔을 때
					if(!request.getParameter("startDay").equals("") && !request.getParameter("endDay").equals("")) {
						String startDay = request.getParameter("startDay");
						String endDay = request.getParameter("endDay");
						System.out.println("startDay1 : " + startDay);
						System.out.println("endDay1 : " + endDay);
						
						list = new PurchaseService().selectHOpuListWithDateSearchCode(startDay, endDay, searchValue);
						
					//날짜 x 일 때
					} else {
						
						list = new PurchaseService().selectHOpuListWithSearchCode(searchValue);
					}
				}
			}
		}
		
	/*	ArrayList<SupplierOrder> list = new PurchaseService().selectHOpuList();*/
		
		String page = "";
		if(list != null) {
			page = "views/purchase/headOffice/purchaseHistoryListHO.jsp";
			request.setAttribute("list", list);
		
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "본사 발주 내역 조회 실패");
			
		}
		request.getRequestDispatcher(page).forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
