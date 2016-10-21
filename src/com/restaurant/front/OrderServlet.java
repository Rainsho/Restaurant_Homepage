package com.restaurant.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.dao.OrderDAO;
import com.restaurant.dao.OrderDetailDAO;
import com.restaurant.entity.MenuItem;
import com.restaurant.entity.Order;
import com.restaurant.entity.OrderDetail;

public class OrderServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		@SuppressWarnings("unchecked")
		HashMap<Integer, MenuItem> menu_map = (HashMap<Integer, MenuItem>) request
				.getSession().getAttribute("menu_map");
		if (menu_map == null || menu_map.size() == 0) {
			return;
		}

		// 下单
		Calendar odate = Calendar.getInstance();
		odate.setTime(new Date());
		int oquant = 0;
		float ofee = 0.0f;
		for (MenuItem x : menu_map.values()) {
			oquant += x.getQuant();
			ofee += x.getFood().getFprice() * x.getQuant();
		}
		Order order = new Order(-1, odate, oquant, ofee, 0);
		OrderDAO o_dao = new OrderDAO();
		order = o_dao.newOrder(order);

		// 补充订单详情
		ArrayList<OrderDetail> od_list = new ArrayList<OrderDetail>();
		for (MenuItem x : menu_map.values()) {
			OrderDetail od = new OrderDetail(order, x.getFood(), x.getQuant());
			od_list.add(od);
		}
		OrderDetailDAO od_dao = new OrderDetailDAO();
		od_dao.addDetail(od_list);

		// 清除session
		request.getSession().removeAttribute("menu_map");
		request.getSession().removeAttribute("menu_item");
		request.getSession().removeAttribute("menu_chart_total_quant");
		request.getSession().removeAttribute("menu_chart_total_price");

	}

}
