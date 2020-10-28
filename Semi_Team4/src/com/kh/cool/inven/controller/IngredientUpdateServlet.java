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
 * Servlet implementation class IngredientUpdateServlet
 */
@WebServlet("/igUpdate.in")
public class IngredientUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IngredientUpdateServlet() {
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


			String pCode = multiRequest.getParameter("pCode");
			String pName = multiRequest.getParameter("pName");
			String classCode = multiRequest.getParameter("classCode");
			int pweight = Integer.parseInt(multiRequest.getParameter("pQ"));
			String pUnit = multiRequest.getParameter("pUnit");
			String pStorage = multiRequest.getParameter("pStorage");
			
			Ingredient updateIngredient = new Ingredient();
			updateIngredient.setIgCode(pCode);
			updateIngredient.setIgName(pName);
			updateIngredient.setIgClass(classCode);
			updateIngredient.setIgCapacity(pweight);
			updateIngredient.setIgUnit(pUnit);
			updateIngredient.setIgSType(pStorage);
			updateIngredient.setIgImageFilePath(filePath);
			updateIngredient.setOriginName(originFile);
			updateIngredient.setSaveName(saveFile);
			
			int result = InvenService.ingredientUpdate(updateIngredient);
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
