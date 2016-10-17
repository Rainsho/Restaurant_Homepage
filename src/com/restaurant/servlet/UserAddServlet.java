package com.restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.UserDAO;
import com.restaurant.entity.User;

public class UserAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		String uname = request.getParameter("uname");
		String upassword = request.getParameter("upassword");
		String utelphone = request.getParameter("utelphone");
		int utype = Integer.parseInt(request.getParameter("utype"));
		String ustaffinfo = request.getParameter("ustaffinfo");
		int ustaffdisplay = Integer.parseInt(request
				.getParameter("ustaffdisplay"));
		String upic = request.getParameter("upic");

		User user = new User(-1, uname, upassword, utelphone, utype, ustaffinfo,
				ustaffdisplay, upic);
		UserDAO dao = new UserDAO();
		dao.addUser(user);

		response.sendRedirect("UserLoadServlet");

	}

}
