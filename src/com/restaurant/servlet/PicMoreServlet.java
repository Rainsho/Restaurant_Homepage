package com.restaurant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.restaurant.dao.PictureDAO;
import com.restaurant.entity.Picture;

public class PicMoreServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int page = Integer.parseInt(request.getParameter("page"));

		PictureDAO dao = new PictureDAO();
		ArrayList<Picture> list = dao.getPage(page);

		JSONArray jsobj = JSONArray.fromObject(list);
		out.print(jsobj.toString());

		out.close();
	}

}
