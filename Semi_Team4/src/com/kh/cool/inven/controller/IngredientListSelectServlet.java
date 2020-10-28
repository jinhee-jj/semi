package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Ingredient;

/**
 * Servlet implementation class IngredientListSelectServlet
 */
@WebServlet("/igManageList.in")
public class IngredientListSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredientListSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("뭐야");
		ArrayList<Ingredient> list = new InvenService().ingredientList();
		
		
		String path = "";
		
		if(list != null) {
			path = "views/inven/InvenManagement.jsp";
			request.setAttribute("list", list);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "원제료 종류 목록 조회 실패");
		}
		request.getRequestDispatcher(path).forward(request, response);
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
