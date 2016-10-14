package com.restaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.FoodDAO;
import com.restaurant.entity.Food;

public class FoodPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");

		// 此处将模糊查询功能与显示所有功能合并，确保翻页功能可用，逻辑有点复杂，你看不懂算了
		String fname = null;
		// fname为空的时候，经过过滤器的get方法会报null pointer
		try {
			fname = request.getParameter("fname");
		} catch (Exception e) {
		}

		if (fname == null) {
			fname = (String) request.getSession().getAttribute("food_filter");
			if (fname == null) {
				fname = "";
			}
		} else {
			request.getSession().setAttribute("food_filter", fname);
		}

		// page为空的时候，经过过滤器的get方法会报null pointer
		int page = 1;
		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			try {
				// 新增时留在原页面，筛选状态下不执行
				if (fname.equals("")) {
					page = Integer.parseInt(request.getSession()
							.getAttribute("food_page").toString());
				}
			} catch (Exception e2) {
			}
		}

		FoodDAO dao = new FoodDAO();
		int food_total = dao.totalFood(fname);
		int food_total_page = (food_total - 1) / 10 + 1;
		// 删除的时候留在原页面
		page = page > food_total_page ? food_total_page : page;

		ArrayList<Food> list = dao.getPage(page, fname);

		request.getSession().setAttribute("food_list", list);
		request.getSession().setAttribute("food_page", page);
		request.getSession().setAttribute("food_total", food_total);
		request.getSession().setAttribute("food_total_page", food_total_page);

		response.sendRedirect("back-end/Food/food.jsp");

	}

}
