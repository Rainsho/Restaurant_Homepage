package com.restaurant.front;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.ReservationDAO;
import com.restaurant.dao.UserDAO;
import com.restaurant.entity.Reservation;
import com.restaurant.entity.User;

public class ReservationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		response.sendRedirect("front-end/reservation.jsp");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uname = request.getParameter("uname");
		String utelphone = request.getParameter("utelphone");

		UserDAO dao1 = new UserDAO();
		User user = dao1.getResUser(uname, utelphone);
		if (user == null) {
			user = new User();
			user.setUname(uname);
			user.setUtelphone(utelphone);
			user.setUtype(3);
			dao1.addUser(user);
			user = dao1.getResUser(uname, utelphone);
		}

		String restitle = request.getParameter("restitle");
		String resdate = request.getParameter("resdate");
		String resinfo = request.getParameter("resinfo");
		int resseat = Integer.parseInt(request.getParameter("resseat"));

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			cal.setTime(sdf.parse(resdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Reservation res = new Reservation(-1, user, restitle, cal, resinfo,
				resseat);

		ReservationDAO dao2 = new ReservationDAO();
		dao2.addRes(res);

	}

}
