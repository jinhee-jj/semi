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
import com.kh.cool.purchase.model.vo.IngredientListBR;

/**
 * Servlet implementation class PurchaseSearchIngredient
 */
@WebServlet("/selectWithiName.pu")
public class SelectListWithiName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectListWithiName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 유저의 지점코드 가져오기
		String branchCode = ((Member) request.getSession().getAttribute("loginMember")).getDeptCode();
	
		String searchValue = request.getParameter("searchValue");
		//System.out.println("searchValue 확인 : " + searchValue);
		
		ArrayList<IngredientListBR> list = new PurchaseService().selectListhWithiName(searchValue, branchCode);
	
		//System.out.println("재료명으로 검색 : " + list);
		
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
