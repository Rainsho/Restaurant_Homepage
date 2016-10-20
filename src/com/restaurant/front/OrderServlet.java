package com.restaurant.front;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restaurant.entity.MenuItem;

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
		// 补充订单详情
		// 清除session
		request.getSession().removeAttribute("menu_map");
		request.getSession().removeAttribute("menu_item");
		request.getSession().removeAttribute("menu_chart_total_quant");
		request.getSession().removeAttribute("menu_chart_total_price");

	}

}
