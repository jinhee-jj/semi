package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.member.model.vo.Member;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.SupplierIngredient;

/**
 * Servlet implementation class SelectListAjaxservlet
 */
@WebServlet("/selectListAjax.pu")
public class SelectListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectListAjaxServlet() {
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
		ArrayList<SupplierIngredient> list1 = new PurchaseService().selectIngredientList(branchCode);
		
		response.setContentType("application/json; charset=UTF-8");
		new Gson().toJson(list1, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
