package com.restaurant.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.ReservationDAO;
import com.restaurant.entity.Reservation;

public class ResLoadServlet extends HttpServlet {

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
			date_s = request.getParameter("date_s");
		} catch (Exception e) {
			try {
				date_s = request.getSession().getAttribute("res_date_s")
						.toString();
			} catch (Exception e2) {
				date_s = "";
			}
		}

		try {
			date_t = request.getParameter("date_t");
		} catch (Exception e) {
			try {
				date_t = request.getSession().getAttribute("res_date_t")
						.toString();
			} catch (Exception e2) {
				date_t = "";
			}
		}

		ReservationDAO dao = new ReservationDAO();

		int res_total = dao.getQuant(date_s, date_t);
		int res_total_page = (res_total - 1) / 10 + 1;
		page = page > res_total_page ? res_total_page : page;

		ArrayList<Reservation> list = dao.getPage(page, date_s, date_t);

		request.getSession().setAttribute("res_list", list);
		request.getSession().setAttribute("res_page", page);
		request.getSession().setAttribute("res_date_s", date_s);
		request.getSession().setAttribute("res_date_t", date_t);
		request.getSession().setAttribute("res_total", res_total);
		request.getSession().setAttribute("res_total_page", res_total_page);

		response.sendRedirect("back-end/Reservation/reservation.jsp");

	}
}
