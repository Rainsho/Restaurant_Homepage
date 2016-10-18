package com.restaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.HomepageDAO;
import com.restaurant.entity.Homepage;

public class HomepageLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		HomepageDAO dao = new HomepageDAO();
		ArrayList<Homepage> list = dao.getAll();
		request.getSession().setAttribute("homepage_list", list);

		response.sendRedirect("back-end/Home/home.jsp");

	}

}
