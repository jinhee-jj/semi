package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.purchase.model.service.PurchaseService;

/**
 * Servlet implementation class HOregisterInsertServlet
 */
@WebServlet("/insertRegisterHO.pu")
public class HOregisterInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HOregisterInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] arr = request.getParameterValues("arr");
		//System.out.println("arr 확인 : " + Arrays.toString(arr));
		
		int result = new PurchaseService().insertRegisterHO(arr);
		
		if(result > 0) {
			
			response.setContentType("application/json; charset=UTF-8");
			new Gson().toJson("Y", response.getWriter());
		} else {
			String page = "views/common/errorPage.jsp";
			request.setAttribute("message", "본사 발주 등록 실패");
			request.getRequestDispatcher(page).forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
