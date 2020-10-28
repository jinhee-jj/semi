package com.kh.cool.orderManagement.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.cool.orderManagement.model.service.KioskService;
import com.kh.cool.orderManagement.model.vo.KioskCart;
import com.kh.cool.orderManagement.model.vo.OrderDetail;
import com.kh.cool.orderManagement.model.vo.OrderResult;
import com.kh.cool.member.model.vo.Member;

/**
 * Servlet implementation class KioskOrderResultServlet
 */
@WebServlet("/orderResult.kk")
public class KioskOrderResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KioskOrderResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String eat = request.getParameter("eat");
		String menu = request.getParameter("menu");
		int disPrice = Integer.parseInt(request.getParameter("disPrice"));	
		
		String togo = "";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginMember");
		String writer = loginUser.getDeptCode();
		
		ArrayList<KioskCart> cart = new KioskService().selectListCart(writer);
		int allPriceResult = new KioskService().allPriceCart(writer);
		
		int discountIncome = disPrice;
		int resultPrice = allPriceResult - disPrice;
		
		if(eat.equals("room")) {
			togo = "N";
		} else {
			togo = "Y";
		}
		
		OrderResult or = new OrderResult();
		or.setKioskCode(writer);
		or.setTogo(togo);
		or.setGrossIncome(allPriceResult);
		or.setDiscountIncome(discountIncome);
		or.setTotalIncome(resultPrice);
		
		int orResult = new KioskService().insertOrResult(or);
		
		or = new KioskService().selectOrder(or);
		
		OrderDetail od = new OrderDetail();
		KioskCart k = new KioskCart();
		int orResult2 = 0;
		for(int i = 0; i < cart.size(); i++) {
			
			k = cart.get(i);
			od.setMenuCode(k.getMenuCode());
			od.setOrderQuantity(k.getCount());
			od.setOrderPrice(k.getMenuClass());
			od.setOrderNo(or.getOrderNo());
			
			orResult2 = new KioskService().insertOrResult2(od);
		}
		
		
		int deleteResult = new KioskService().deleteAllCart(writer);
		
		String page = "";
		if(orResult > 0 && orResult2 > 0 && deleteResult > 0) {
			page = "views/kiosk/kioskPaymentResult.jsp";
			
			request.setAttribute("eat", eat);
			request.setAttribute("menu", "all");
			request.setAttribute("cart", cart);
			request.setAttribute("allP", allPriceResult);
			request.setAttribute("disPrice", disPrice);
			request.setAttribute("resultPrice", resultPrice);
			request.setAttribute("OrderNUM", or);
		} else {
			page = "views/common/errorPage.jsp";
			
			request.setAttribute("message", "주문확인서 불러오기 실패!!!");
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
