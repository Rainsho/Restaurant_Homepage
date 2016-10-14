package com.restaurant.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.NewsDAO;
import com.restaurant.entity.News;

public class NewsPageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int page = Integer.parseInt(request.getParameter("page"));

		NewsDAO dao = new NewsDAO();
		@SuppressWarnings("unchecked")
		List<News> list_all = (List<News>) request.getSession().getAttribute(
				"news_list_all");

		//find list in the session so that when we do a filter the page function can still work
		List<News> list_page = dao.getPage(list_all, page);

		request.getSession().setAttribute("news_list", list_page);
		request.getSession().setAttribute("news_page", page);

		request.getRequestDispatcher("back-end/News/news.jsp").forward(request,
				response);

	}

}
