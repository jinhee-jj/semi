package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Inven;

/**
 * Servlet implementation class InvenSelectBoxList
 */
@WebServlet("/igSelectList.in")
public class InvenSelectBoxList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvenSelectBoxList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Inven> list = new InvenService().invenListSelectBox();
		
		String path = "";
		if(list != null) {
			path =  "views/inven/InvenList.jsp";
			request.setAttribute("list", list);
		}else {
			path = "views/common/errorPage.jsp";
			request.setAttribute("message", "원제료 리스트 조회 실패");
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
