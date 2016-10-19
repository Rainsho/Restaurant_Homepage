package com.restaurant.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.restaurant.dao.NewsDAO;
import com.restaurant.entity.BackMsg;
import com.restaurant.entity.News;

public class ArchiveMoreServlet extends HttpServlet {

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
		NewsDAO dao = new NewsDAO();
		ArrayList<News> list = dao.getPage(page);
		BackMsg msg = new BackMsg(200, "ok", list);
		if (list.size() < 10) {
			msg.setState(list.size());
			msg.setMsg("no more");
		}

		JSONObject jsonobj = JSONObject.fromObject(msg);
		out.print(jsonobj.toString());

		out.close();
	}

}
