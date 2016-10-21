package com.restaurant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.restaurant.dao.OrderDAO;
import com.restaurant.dao.OrderDetailDAO;
import com.restaurant.entity.BackMsg;
import com.restaurant.entity.OrderDetail;

public class OrderCtrlServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int oid = Integer.parseInt(request.getParameter("oid"));
		String type = request.getParameter("type");

		if (type.equals("check_out")) {
			OrderDAO dao = new OrderDAO();
			dao.checkOut(oid);
		}
		if (type.equals("load_detail")) {
			OrderDetailDAO dao = new OrderDetailDAO();
			ArrayList<OrderDetail> list = dao.loadDetails(oid);
			BackMsg msg = new BackMsg(list.size(), "ok", list);
			JSONObject jsonobj = JSONObject.fromObject(msg);
			out.print(jsonobj.toString());
		}

		out.close();
	}

}
