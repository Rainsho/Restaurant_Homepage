package com.restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.UserDAO;
import com.restaurant.entity.User;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verifycode = request.getParameter("verifycode");

		// 重启服务器时，容易空指针异常
		if (request.getSession().getAttribute("verifycode") == null) {
			response.sendRedirect("back-end/login.jsp");
			return;
		}

		// check verify code
		if (!verifycode.equals(request.getSession().getAttribute("verifycode")
				.toString())) {
			request.getSession().setAttribute("loginmsg", "验证码错误");
			response.sendRedirect("back-end/login.jsp");
			return;
		}

		UserDAO dao = new UserDAO();
		User usr = dao.login(username, password);

		if (usr != null) {
			// clear loginmsg
			request.getSession().removeAttribute("loginmsg");
			// cookie function
			// default encode with ASCII
			Cookie ck = new Cookie("usr", "find_usr_in_cookie");
			ck.setMaxAge(60);
			response.addCookie(ck);
			// original
			request.setAttribute("usr", usr);
			request.getRequestDispatcher("back-end/index.jsp").forward(request,
					response);
		} else {
			request.getSession().setAttribute("loginmsg", "账号或密码错误");
			response.sendRedirect("back-end/login.jsp");
		}

	}

}
