package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Inven;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class InvenInsertSelectServet
 */
@WebServlet("/insertSelect.in")
public class InvenInsertSelectServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvenInsertSelectServet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String barcode = request.getParameter("insertCode");
		int insertInt = Integer.parseInt(request.getParameter("insertInt"));
		
		
		HttpSession session = request.getSession();
		Member inMember = (Member) session.getAttribute("loginMember");
		String branchCode = inMember.getDeptCode();
		
		if(branchCode.equals("HOF01")) {
			branchCode = request.getParameter("selectBranchInven");
		}
		
		String str[] = barcode.split("[$]");
		String igretCode = str[0];
		
		Inven newSelectInven = new Inven();
		
		newSelectInven.setBarcode(barcode);
		newSelectInven.setAddQuantity(insertInt);
		newSelectInven.setBranchCode(branchCode);
		newSelectInven.setIgretCode(igretCode);
		
		//System.out.println("selectinven:"+newSelectInven);
	
		ArrayList<Inven> list = new InvenService().selectInsertBranchInven(newSelectInven);
	
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
