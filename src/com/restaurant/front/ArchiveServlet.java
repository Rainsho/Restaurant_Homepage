package com.restaurant.front;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.FoodDAO;
import com.restaurant.dao.NewsDAO;
import com.restaurant.dao.PictureDAO;
import com.restaurant.entity.Food;
import com.restaurant.entity.News;
import com.restaurant.entity.Picture;

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

		FoodDAO fdao = new FoodDAO();
		ArrayList<Food> flist = fdao.getTop(3);
		request.getSession().setAttribute("archive_flist", flist);

		PictureDAO pdao = new PictureDAO();
		ArrayList<Picture> plist = pdao.getTop(6);
		request.getSession().setAttribute("archive_plist", plist);

		request.getRequestDispatcher("front-end/archive.jsp").forward(request,
				response);

	}

}
