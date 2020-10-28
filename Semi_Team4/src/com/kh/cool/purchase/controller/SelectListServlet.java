package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.member.model.vo.Member;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.IngredientListBR;
import com.kh.cool.purchase.model.vo.SupplierIngredient;

/**
 * Servlet implementation class SelectIngredientListServlet
 */
@WebServlet("/selectList.pu")
public class SelectListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 유저의 지점코드 가져오기
		String branchCode = ((Member) request.getSession().getAttribute("loginMember")).getDeptCode();
			
		//거래처취급품목 포함 조인해서 가져오는 원재료 목록 리스트
		ArrayList<SupplierIngredient> list = new PurchaseService().selectIngredientList(branchCode);
		
		//System.out.println("list 확인" + list);
		
		String page="";
		
		if(list != null) {
			page = "views/purchase/branch/purchaseRegisterBR.jsp";
			request.setAttribute("list", list);
			
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "원재료목록 조회 실패!");
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
