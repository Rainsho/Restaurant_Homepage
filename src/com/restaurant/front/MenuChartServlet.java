package com.restaurant.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.restaurant.dao.FoodDAO;
import com.restaurant.entity.BackMsg;
import com.restaurant.entity.MenuItem;

public class MenuChartServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		int fid = Integer.parseInt(request.getParameter("fid"));
		String type = request.getParameter("type");

		@SuppressWarnings("unchecked")
		HashMap<Integer, MenuItem> menu_map = (HashMap<Integer, MenuItem>) request
				.getSession().getAttribute("menu_map");
		if (menu_map == null) {
			menu_map = new HashMap<Integer, MenuItem>();
		}

		if (type.equals("add")) {
			FoodDAO dao = new FoodDAO();
			MenuItem mi = menu_map.get(fid);
			if (mi == null) {
				mi = new MenuItem(dao.getById(fid), 1);
			} else {
				mi.setQuant(mi.getQuant() + 1);
			}
			menu_map.put(fid, mi);
		}

		if (type.equals("del")) {
			menu_map.remove(fid);
		}

		request.getSession().setAttribute("menu_map", menu_map);

		// 处理预加载
		int total_quant = 0;
		float total_price = 0.0f;
		for (MenuItem x : menu_map.values()) {
			total_quant += x.getQuant();
			total_price += x.getFood().getFprice() * x.getQuant();
		}
		request.getSession().setAttribute("menu_item", menu_map.values());
		request.getSession()
				.setAttribute("menu_chart_total_quant", total_quant);
		request.getSession()
				.setAttribute("menu_chart_total_price", total_price);

		BackMsg msg = new BackMsg(200, "ok", menu_map.values());
		JSONObject jsonobj = JSONObject.fromObject(msg);
		out.print(jsonobj.toString());

		out.close();
	}

}
