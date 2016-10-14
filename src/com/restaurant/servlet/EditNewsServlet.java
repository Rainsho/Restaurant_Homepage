package com.restaurant.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.entity.News;

public class EditNewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int nid = Integer.parseInt(request.getParameter("nid"));

		@SuppressWarnings("unchecked")
		List<News> list = (List<News>) request.getSession().getAttribute(
				"news_list");
		News obj = null;
		for (News news : list) {
			if (news.getNid() == nid) {
				obj = news;
				break;
			}
		}

		request.getSession().setAttribute("news", obj);
		request.getRequestDispatcher("back-end/News/edit.jsp").forward(request,
				response);

	}

}
