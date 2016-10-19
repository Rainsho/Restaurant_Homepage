package com.restaurant.front;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.FoodDAO;
import com.restaurant.dao.FoodTypeDAO;
import com.restaurant.entity.Food;
import com.restaurant.entity.FoodType;

public class MenuServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		FoodTypeDAO dao1 = new FoodTypeDAO();
		ArrayList<FoodType> list1 = dao1.getAll();
		request.getSession().setAttribute("menu_list1", list1);

		FoodDAO dao2 = new FoodDAO();
		ArrayList<Food> list2 = dao2.getAll();
		request.getSession().setAttribute("menu_list2", list2);

		response.sendRedirect("front-end/menu.jsp");
	}

}
