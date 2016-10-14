package com.restaurant.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.NewsDAO;
import com.restaurant.entity.News;

public class EditNewsServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");

		News news = (News) request.getSession().getAttribute("news");
		news.setNauthor(request.getParameter("nauthor"));
		news.setNcontent(request.getParameter("ncontent"));
		news.setNtag(request.getParameter("ntag"));
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(request.getParameter("ndate")));
			news.setNdate(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}

		NewsDAO dao = new NewsDAO();
		dao.UpdateNews(news);

		request.getRequestDispatcher("AllNewsServlet").forward(request,
				response);

	}

}
