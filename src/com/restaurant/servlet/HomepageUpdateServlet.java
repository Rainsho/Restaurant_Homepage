package com.restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.HomepageDAO;
import com.restaurant.entity.Homepage;

public class HomepageUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int hid = Integer.parseInt(request.getParameter("hid"));
		String pic = request.getParameter("pic");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		String type = request.getParameter("type");

		Homepage obj = new Homepage(hid, pic, title, contents, type);
		HomepageDAO dao = new HomepageDAO();
		dao.update(obj);

	}

}
