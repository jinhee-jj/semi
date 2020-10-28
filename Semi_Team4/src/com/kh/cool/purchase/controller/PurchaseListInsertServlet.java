package com.kh.cool.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.buf.StringUtils;

import com.kh.cool.member.model.vo.Member;
import com.kh.cool.purchase.model.service.PurchaseService;
import com.kh.cool.purchase.model.vo.PurchaseListBR;

/**
 * Servlet implementation class PurchaseListInsertServlet
 */
@WebServlet("/insertList.pu")
public class PurchaseListInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseListInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인 유저의 지점코드 가져오
		String branchCode = ((Member) request.getSession().getAttribute("loginMember")).getDeptCode();
		
		
		//발주 전체 재료 담긴 배열
		String[] cartArr = request.getParameterValues("cartArr");
		int csize = cartArr.length;
		/*System.out.println("size: " + size);*/
		
		//재료 목록만 담긴 배열
		String[] ingreArr = request.getParameterValues("ingreArr");
		int isize = ingreArr.length;
	//	System.out.println("ingreArr 사이즈 : " + isize);
		
		//파라미터로 가져온 변수 어레이리스트에 넣기
		ArrayList clist = new ArrayList();
		ArrayList ilist = new ArrayList();
		
		for(int i=0; i<csize; i++) {
			clist.add(cartArr[i]);	
		}
		for(int i=0; i<isize; i++) {
			ilist.add(ingreArr[i]);
		}
	
		//System.out.println("clist: " + clist);
		
		//System.out.println("ilist: " + ilist);
		
		//재료목록 string배열에 담아줌
		String[] strr = new String[ilist.size()];
		
		//재료목록을 보내 거래처코드 받아오기 (거래처취급품목과 조인)
		strr = new PurchaseService().getSupList(ilist);
		
		//System.out.println("str 확인 : " + strr);
		
		//document창에 넣을 때 거래처코드 중복 허용하여 각 품목당 발주번호 다 다르게 하여 넣음
		//받아온 거래처 어레이리스트에 넣어주기
		ArrayList<String> docList = new ArrayList<String>();
		for(int i = 0; i < strr.length; i++) {
			docList.add(strr[i]);
		}
		
		//System.out.println("docList check : " + docList);
		
		//branch_purchase_document테이블에 넣음
		int docResult = new PurchaseService().insertIntoDoc(docList);
		int result = 0;
		
		
		String page = "";
		if(docResult > 0) {
							//진짜 주문할 재료 정보가 담긴 배열
			Iterator iter = clist.iterator();
			ArrayList<PurchaseListBR> pList = new ArrayList<PurchaseListBR>();
			
			while(iter.hasNext()) {
				PurchaseListBR purchase = new PurchaseListBR();
				
				purchase.setIngredientCode((String) iter.next());
				purchase.setIngredientName((String) iter.next());
				purchase.setPurchaseUnit((String) iter.next());
				purchase.setPurchseQuantity(Integer.parseInt((String) iter.next()));
				purchase.setBranchCode((String) iter.next()); 
				pList.add(purchase);
				
			}
			System.out.println("pList: " + pList);
			
													//발주번호 가져올 재료목록 갯수 같이
			result = new PurchaseService().insertPList(pList, isize);
			
			
			System.out.println(result);
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/selectList.pu");
			}
			
		} else {
			page = "views/common/errorPage.jsp"; 
			request.setAttribute("message", "지점발주 실패");
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
