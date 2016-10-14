package com.restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.NewsDAO;

public class DeleteNewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int nid = Integer.parseInt(request.getParameter("nid"));
		NewsDAO dao = new NewsDAO();
		dao.DelNews(nid);

		request.getRequestDispatcher("AllNewsServlet").forward(request,
				response);

	}

}
