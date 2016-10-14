package com.restaurant.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.NewsDAO;
import com.restaurant.entity.News;

public class AddNewsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");

		News obj = new News();
		obj.setNauthor(request.getParameter("nauthor"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		obj.setNdate(cal);
		obj.setNcontent(request.getParameter("ncontent"));
		obj.setNtag(request.getParameter("ntag"));

		NewsDAO dao = new NewsDAO();
		dao.AddNews(obj);

		request.getRequestDispatcher("AllNewsServlet").forward(request,
				response);

	}

}
