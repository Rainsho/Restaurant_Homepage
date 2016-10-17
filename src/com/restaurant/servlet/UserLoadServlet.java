package com.restaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.UserDAO;
import com.restaurant.entity.User;

public class UserLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int page = -1;
		String uname = null;
		int utype = -1;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			try {
				page = Integer.parseInt(request.getSession()
						.getAttribute("user_page").toString());
			} catch (Exception e2) {
				page = 1;
			}
		}

		try {
			utype = Integer.parseInt(request.getParameter("utype"));
		} catch (Exception e) {
			try {
				utype = Integer.parseInt(request.getSession()
						.getAttribute("user_utype").toString());
			} catch (Exception e2) {
				utype = 0;
			}
		}

		try {
			uname = request.getParameter("uname");
		} catch (Exception e) {
			try {
				uname = request.getSession().getAttribute("user_uname")
						.toString();
			} catch (Exception e2) {
				uname = "";
			}
		}

		UserDAO dao = new UserDAO();

		int user_total = dao.getQuant(uname, utype);
		int user_total_page = (user_total - 1) / 10 + 1;
		page = page > user_total_page ? user_total_page : page;

		ArrayList<User> list = dao.getPage(page, uname, utype);
		request.getSession().setAttribute("user_list", list);

		request.getSession().setAttribute("user_page", page);
		request.getSession().setAttribute("user_utype", utype);
		request.getSession().setAttribute("user_uname", uname);
		request.getSession().setAttribute("user_total", user_total);
		request.getSession().setAttribute("user_total_page", user_total_page);

		response.sendRedirect("back-end/User/user.jsp");

	}

}
