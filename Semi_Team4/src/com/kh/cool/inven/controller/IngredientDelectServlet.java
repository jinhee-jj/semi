package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Ingredient;

/**
 * Servlet implementation class IngredientDelectServlet
 */
@WebServlet("/ingredientDel.in")
public class IngredientDelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredientDelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		//System.out.println(data);

		String datas[] = data.split(",");

		int result = 0;
		
		for(int i = 0; i < datas.length; i++) {
			Ingredient delIngredient = new Ingredient();
			delIngredient.setIgCode(datas[i]);
			result += new InvenService().dellIngredient(delIngredient);
			}
		
		String path = "";
		if(result > datas.length - 1) {
			response.sendRedirect(request.getContextPath() + "/igManageList.in");
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "삭재 실패!");
			request.getRequestDispatcher(path).forward(request, response);
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
