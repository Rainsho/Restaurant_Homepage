package com.restaurant.front;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.PictureDAO;
import com.restaurant.entity.Picture;

public class GalleryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PictureDAO dao = new PictureDAO();
		ArrayList<Picture> list = dao.getShow();
		request.getSession().setAttribute("gallery_list", list);

		response.sendRedirect("front-end/gallery.jsp");
	}

}
