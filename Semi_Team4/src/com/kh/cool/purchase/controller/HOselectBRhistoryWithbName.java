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
 * Servlet implementation class HOselectBRhistoryWithbName
 */
@WebServlet("/selectHistoryWithbName.pu")
public class HOselectBRhistoryWithbName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HOselectBRhistoryWithbName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bName = request.getParameter("bName");
		System.out.println("지점 이름 확인 : " + bName);
		
		
		ArrayList<PurchaseListBR> list = null;
		//전체지점 택한 경우 (마지막 글자인 '점'빼고 넘어옴)
		if(bName.equals("전체지")) {
	
			list = new PurchaseService().selectAllBRpList();
			System.out.println("list : " + list);
			
		}else {
			
			list = new PurchaseService().selectHistoryWithbName(bName);
			System.out.println("list : " + list);
		
		}
		
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
