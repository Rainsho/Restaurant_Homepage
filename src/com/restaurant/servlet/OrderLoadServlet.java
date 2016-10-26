package com.restaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.OrderDAO;
import com.restaurant.entity.Order;
import com.restaurant.entity.OrderSum;

public class OrderLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int page = -1;
		String date_s = null;
		String date_t = null;
		int ocheck = -1;

		try {
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
			try {
				page = Integer.parseInt(request.getSession()
						.getAttribute("ord_page").toString());
			} catch (Exception e2) {
				page = 1;
			}
		}

		try {
			date_s = request.getParameter("date_s");
		} catch (Exception e) {
			try {
				date_s = request.getSession().getAttribute("ord_date_s")
						.toString();
			} catch (Exception e2) {
				date_s = "";
			}
		}

		try {
			date_t = request.getParameter("date_t");
		} catch (Exception e) {
			try {
				date_t = request.getSession().getAttribute("ord_date_t")
						.toString();
			} catch (Exception e2) {
				date_t = "";
			}
		}

		try {
			ocheck = Integer.parseInt(request.getParameter("ocheck"));
		} catch (Exception e) {
			try {
				ocheck = Integer.parseInt(request.getSession()
						.getAttribute("ord_ocheck").toString());
			} catch (Exception e2) {
				ocheck = -1;
			}
		}

		OrderDAO dao = new OrderDAO();

		int ord_total = dao.getQuant(date_s, date_t, ocheck);
		int ord_total_page = (ord_total - 1) / 10 + 1;
		page = page > ord_total_page ? ord_total_page : page;

		ArrayList<Order> list = dao.getPage(page, date_s, date_t, ocheck);

		request.getSession().setAttribute("ord_list", list);
		request.getSession().setAttribute("ord_page", page);
		request.getSession().setAttribute("ord_date_s", date_s);
		request.getSession().setAttribute("ord_date_t", date_t);
		request.getSession().setAttribute("ord_ocheck", ocheck);
		request.getSession().setAttribute("ord_total", ord_total);
		request.getSession().setAttribute("ord_total_page", ord_total_page);

		// 后台小计功能
		OrderSum ordersum = dao.getSum(date_s, date_t, ocheck);
		request.getSession().setAttribute("ordersum", ordersum);

		response.sendRedirect("back-end/Order/order.jsp");

	}
}
