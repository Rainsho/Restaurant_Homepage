package com.restaurant.front;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.NewsDAO;
import com.restaurant.entity.News;

public class ArchiveServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		NewsDAO dao = new NewsDAO();
		News news = null;

		try {
			int nid = Integer.parseInt(request.getParameter("nid"));
			news = dao.getById(nid);
			if (news == null) {
				news = dao.getNewOne();
			}
		} catch (Exception e) {
			news = dao.getNewOne();
		}

		request.getSession().setAttribute("archive_news", news);
		request.getRequestDispatcher("front-end/archive.jsp").forward(request,
				response);

	}

}
