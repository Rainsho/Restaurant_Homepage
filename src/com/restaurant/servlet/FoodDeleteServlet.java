package com.restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.FoodDAO;

public class FoodDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int fid = Integer.parseInt(request.getParameter("fid"));
		FoodDAO dao = new FoodDAO();
		dao.delFood(fid);

		int page = (Integer) request.getSession().getAttribute("food_page");
		response.sendRedirect("FoodPageServlet?page=" + page);

	}

}
