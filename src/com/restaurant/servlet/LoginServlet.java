package com.restaurant.servlet;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		// 免验证码判断
		int failed_times = Integer.parseInt(request.getSession()
				.getAttribute("FAILED_TIMES").toString());

		// 重启服务器时，容易空指针异常
		if (request.getSession().getAttribute("verifycode") == null) {
			response.sendRedirect("back-end/login.jsp");
			return;
		}

		// check verify code
		if (failed_times >= 2) {
			if (!verifycode.equals(request.getSession()
					.getAttribute("verifycode").toString())) {
				request.getSession().setAttribute("loginmsg", "验证码错误");
				response.sendRedirect("back-end/login.jsp");
				return;
			}
		}

		UserDAO dao = new UserDAO();
		User usr = dao.login(username, password);

		if (usr != null) {
			// check logined
			@SuppressWarnings("unchecked")
			HashSet<HttpSession> session_set = (HashSet<HttpSession>) getServletContext()
					.getAttribute("session_set");
			for (HttpSession x : session_set) {
				if (x != request.getSession()) {
					User logined = (User) x.getAttribute("LOGINED_USER");
					if (logined != null && logined.getUid() == usr.getUid()) {
						x.removeAttribute("LOGINED_USER");
						x.setAttribute("loginmsg", "您的账号已在别处登录！");
						x.setAttribute("FAILED_TIMES", 9);
					}
				}
			}
			// clear loginmsg
			request.getSession().removeAttribute("loginmsg");
			// clear FAILED_TIMES
			request.getSession().setAttribute("FAILED_TIMES", 0);
			// cookie function
			// default encode with ASCII
			Cookie ck = new Cookie("usr", "find_usr_in_cookie");
			ck.setMaxAge(60);
			response.addCookie(ck);
			// original
			// request.setAttribute("usr", usr);
			// request.getRequestDispatcher("back-end/index.jsp").forward(request,
			// response);
			// 权限验证，单态模式
			request.getSession().setAttribute("LOGINED_USER", usr);
			response.sendRedirect("back-end/index.jsp");
		} else {
			request.getSession().setAttribute("loginmsg", "账号或密码错误");
			request.getSession().setAttribute("FAILED_TIMES", failed_times + 1);
			response.sendRedirect("back-end/login.jsp");
		}

	}

}
