package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.Branch;
import com.kh.cool.purchase.model.vo.PurchaseListBR;

/**
 * Servlet implementation class AllBranchPurchaseSelectList
 */
@WebServlet("/selectAllBRpList.pu")
public class AllBranchPurchaseSelectList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllBranchPurchaseSelectList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//본사에서 보는 전제 지점 발주 내역
		
		
		ArrayList<Branch> bList = new PurchaseService().selectAllBranch();
		//System.out.println("bList 확인 : " + bList);
		ArrayList<PurchaseListBR> list  = new PurchaseService().selectAllBRpList();	
		
		String page = "";
		if(list != null && bList != null) {
			page = "views/purchase/headOffice/purchaseHistoryListBRforHO.jsp";
			request.setAttribute("bList", bList);
			request.setAttribute("list", list);
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "지점발주현황 조회 실패!");
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
