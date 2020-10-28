package com.kh.cool.purchase.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.Supplier;

/**
 * Servlet implementation class SupplierUpdateServlet
 */
@WebServlet("/updateSup.pu")
public class SupplierUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupplierUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sCodeNew = request.getParameter("sCodeNew");
		String cNameNew = request.getParameter("cNameNew");
		String rNameNew = request.getParameter("rNameNew");
		String sPhoneNew = request.getParameter("sPhoneNew");
		String sAddressNew = request.getParameter("sAddressNew");
		String sNumNew = request.getParameter("sNumNew");
		String sEmailNew = request.getParameter("sEmailNew");
		String sBankNew = request.getParameter("sBankNew");
		String sAccountNew = request.getParameter("sAccountNew");
		String sFaxNew = request.getParameter("sFaxNew");
		
		
		Supplier editSupplier = new Supplier();
		
		editSupplier.setsCode(sCodeNew);
		editSupplier.setcName(cNameNew);
		editSupplier.setrName(rNameNew);
		editSupplier.setsPhone(sPhoneNew);
		editSupplier.setsAddress(sAddressNew);
		editSupplier.setsNum(sNumNew);
		editSupplier.setsEmail(sEmailNew);
		editSupplier.setsBank(sBankNew);
		editSupplier.setsAccount(sAccountNew);
		editSupplier.setsFax(sFaxNew);
		
	//	System.out.println("editsupplier check : " + editSupplier);
		
		int result1 = new PurchaseService().updateSupplier(editSupplier);
		String page="";

		if(result1 > 0) {
	
			JSONObject result = new JSONObject();
			result.put("sCode", editSupplier.getsCode());
			result.put("cName", editSupplier.getcName());
			result.put("rName", editSupplier.getrName());
			result.put("sPhone", editSupplier.getsPhone());
			result.put("sAddress", editSupplier.getsAddress());
			result.put("sNum", editSupplier.getsNum());
			result.put("sEmail", editSupplier.getsEmail());
			result.put("sBank", editSupplier.getsBank());
			result.put("sAccount", editSupplier.getsAccount());
			result.put("sFax", editSupplier.getsFax());
			
			String jsonString = result.toJSONString();
			
			response.setContentType("application/json; charset=UTF-8");

			response.getWriter().print(jsonString);
	
		} else {
			page = "views/common/errorPage.jsp";
			request.setAttribute("message", "거래처 업데이트 실패!");
			request.getRequestDispatcher(page).forward(request, response);
		}
		
		
	
	}

}

