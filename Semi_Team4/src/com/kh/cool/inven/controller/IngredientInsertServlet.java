package com.kh.cool.inven.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.cool.common.MyFileRenamePolicy;
import com.kh.cool.inven.model.service.InvenService;
import com.kh.cool.inven.model.vo.Ingredient;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class IngredientInsertServlet
 */
@WebServlet("/iginsert.in")
public class IngredientInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredientInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize = 1258 * 1258 * 10;
			String root = request.getSession().getServletContext().getRealPath("/");
			//System.out.println(root);
			
			//이미지 자리
			String filePath = root +"resources/images/ingredientImg/";
			//이미지 받아오기(형식넣어서)
			MultipartRequest multiRequest = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			//파일 여러이름이면 받는것들
			String saveFile = null;
			String originFile = null;
			
			//파일이름들
			Enumeration<String> files = multiRequest.getFileNames();
			
			String name = (String) files.nextElement();
			
				//System.out.println("name : " + name);
				saveFile = multiRequest.getFilesystemName(name);
				originFile = multiRequest.getOriginalFileName(name);
				
			//System.out.println(saveFile);
			//System.out.println(originFile);
			
			
			//이미지 외의 값
			String igcode = multiRequest.getParameter("pCode");
			String igname = multiRequest.getParameter("pName");
			String igclasscode = multiRequest.getParameter("classCode");
			int igq = Integer.parseInt(multiRequest.getParameter("pQ"));
			String igunit = multiRequest.getParameter("pUnit");
			String igstorage = multiRequest.getParameter("pStorage");
			
			//System.out.println(igcode);
			//System.out.println(igname);
			
			//넣기
			Ingredient newingredient = new Ingredient();
			newingredient.setIgCode(igcode);
			newingredient.setIgName(igname);
			newingredient.setIgClass(igclasscode);
			newingredient.setIgUnit(igunit);
			newingredient.setIgCapacity(igq);
			newingredient.setIgSType(igstorage);
			newingredient.setIgImageFilePath(filePath);
			newingredient.setOriginName(originFile);
			newingredient.setSaveName(saveFile);
			
			switch (igclasscode) {
			case "BE": newingredient.setIgInClass("B"); break;
			case "BP": newingredient.setIgInClass("G"); break;
			case "CF": newingredient.setIgInClass("C"); break;
			case "CI": newingredient.setIgInClass("S"); break;
			case "CS": newingredient.setIgInClass("F"); break;
			case "PD": newingredient.setIgInClass("P"); break;
			case "PV": newingredient.setIgInClass("W"); break;
			case "SD": newingredient.setIgInClass("E"); break;
			case "TA": newingredient.setIgInClass("T"); break;
			case "TP": newingredient.setIgInClass("A"); break;
			case "UG": newingredient.setIgInClass("D"); break;

			default: newingredient.setIgInClass("오류나게되어있음");
				break;
			}
			
			
			
			
			int result = new InvenService().imgIgAdd(newingredient);
			
			if(result > 0) {
				response.sendRedirect(request.getContextPath() + "/igManageList.in");
			}else {
				filePath = null;
				saveFile = null;
				request.setAttribute("message", "원재료 등록 실패!");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
			
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
