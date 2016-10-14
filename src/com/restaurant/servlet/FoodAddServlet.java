package com.restaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.FoodTypeDAO;
import com.restaurant.entity.FoodType;

public class FoodAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		// load food_type
		
		FoodTypeDAO type_dao = new FoodTypeDAO();
		ArrayList<FoodType> type_list = type_dao.getAll();
		request.getSession().setAttribute("type_list", type_list);

		response.sendRedirect("back-end/Food/add.jsp");

	}

}
