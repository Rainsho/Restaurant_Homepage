package com.restaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.NewsDAO;
import com.restaurant.entity.News;

public class NewsFilterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		String nauthor = request.getParameter("nauthor");

		NewsDAO dao = new NewsDAO();
		ArrayList<News> list_all = dao.filterAuthor(nauthor);
		// display at most 10 results
		List<News> list = list_all.subList(0, Math.min(list_all.size(), 10));
		request.getSession().setAttribute("news_list_all", list_all);
		request.getSession().setAttribute("news_list", list);
		// page initial
		request.getSession().setAttribute("news_page", 1);
		request.getSession().setAttribute("news_page_all",
				(int) Math.ceil(list_all.size() / 10.0));
		request.getRequestDispatcher("back-end/News/news.jsp").forward(request,
				response);
	}

}
