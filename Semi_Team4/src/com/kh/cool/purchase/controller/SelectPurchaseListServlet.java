package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.member.model.vo.Member;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.PurchaseListBR;

/**
 * Servlet implementation class SelectPurchaseListServlet
 */
@WebServlet("/selectpListBR.pu")
public class SelectPurchaseListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectPurchaseListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("loginMember");

		String branchCode = member.getDeptCode();
		//System.out.println("지점코드 확인 : " + branchCode);
		
		//지점명 알아오기
		String branchName = new PurchaseService().selectbNameWithbCode(branchCode);

		ArrayList<PurchaseListBR> repList;
		if(request.getParameter("startDay") != null && request.getParameter("endDay") != null) {
			String startDay = request.getParameter("startDay");
			String endDay = request.getParameter("endDay");
			
			System.out.println("시작날짜 : " + startDay);
			System.out.println("끝날짜 : " + endDay);
			
			repList = new PurchaseService().selectRepiNameWithDate(branchCode, startDay, endDay);
		} else {
			
			// 발주 전체 내역에 불러올 대표 상품, 순번, 발주날짜, 상품 갯수 가져오기
			repList = new PurchaseService().selectRepiName(branchCode);
		}
	

		//System.out.println("repList check in servlet : " + repList);
		// System.out.println("pList 확인 : " + pList);
		
		String page = "";
		if (repList != null) {
			page = "views/purchase/branch/purchaseHistoryListBR.jsp";
			request.setAttribute("repList", repList);
			request.setAttribute("branchName", branchName);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "지점 발주내역 조회 실패");
		}
		request.getRequestDispatcher(page).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
