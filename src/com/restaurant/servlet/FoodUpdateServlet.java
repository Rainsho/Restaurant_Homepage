package com.restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.FoodDAO;

public class FoodUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int fid = Integer.parseInt(request.getParameter("fid"));
		// 直接通过fid改数据库，不修改对象
		int ftid = Integer.parseInt(request.getParameter("ftid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		String fname = request.getParameter("fname");
		String fdetial = request.getParameter("fdetial");
		float fprice = Float.parseFloat(request.getParameter("fprice"));

		FoodDAO dao = new FoodDAO();
		dao.updateFood(fid, ftid, pid, fname, fdetial, fprice);

		response.sendRedirect("FoodPageServlet");

	}

}
