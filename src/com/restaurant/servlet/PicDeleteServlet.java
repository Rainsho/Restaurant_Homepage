package com.restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.PictureDAO;

public class PicDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int pid = Integer.parseInt(request.getParameter("pid"));

		PictureDAO dao = new PictureDAO();
		dao.delPic(pid);

		request.getSession().setAttribute("pic_upload_msg", "删除成功");
		response.sendRedirect("PicAllServlet");

	}

}
