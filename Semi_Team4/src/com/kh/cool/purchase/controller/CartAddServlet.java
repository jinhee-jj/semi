package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.cool.purchase.model.vo.IngredientListBR;

/**
 * Servlet implementation class CarAddServlet
 */
@WebServlet("/addCart.pu")
public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] tdArr = request.getParameterValues("tdArr");
	      int size = tdArr.length;
	      
	      //파라미터로 가져온 변수 어레이리스트에 넣기
	      ArrayList list1 = new ArrayList();
	      for(int i=0; i<size; i++) {
	     /*    System.out.println("배열 확인 : " + tdArr[i]);*/
	         list1.add(tdArr[i]);
	         
	      }
	      ArrayList<IngredientListBR> list = new ArrayList<IngredientListBR>();
	      Iterator iter = list1.iterator();
	      
	      while(iter.hasNext()) {
	    	  IngredientListBR iarr = new IngredientListBR();
	    	  	    	  
	    	  iarr.setiCode((String) iter.next());
	    	  iarr.setiName((String) iter.next());
	    	  iarr.setiCapacity(Integer.parseInt((String)iter.next()));
	    	  iarr.setiUnit((String) iter.next());
	    	  iarr.setiNum(Integer.parseInt((String)iter.next()));
	    	  
	    	  list.add(iarr);	    	  
	    	  
	      }

	     // System.out.println("cart list:확인 " + list);
  
	       
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
