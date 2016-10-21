package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.restaurant.entity.Food;
import com.restaurant.entity.FoodType;
import com.restaurant.entity.Order;
import com.restaurant.entity.OrderDetail;
import com.restaurant.entity.Picture;

public class OrderDetailDAO extends BaseDAO {

	public void addDetail(ArrayList<OrderDetail> od_list) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "insert into orderdetail (oid, fid, odquant) values (?, ?, ?)";
			pst = con.prepareStatement(sql);
			for (OrderDetail x : od_list) {
				pst.setInt(1, x.getOrder().getOid());
				pst.setInt(2, x.getFood().getFid());
				pst.setInt(3, x.getOdquant());
				pst.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public ArrayList<OrderDetail> loadDetails(int oid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select a.*, b.*, c.*, d.* from orderdetail as a, food as b, foodtype as c, picture as d "
					+ "where a.fid = b.fid and b.ftid = c.ftid and b.pid = d.pid "
					+ "and a.oid = ? order by b.ftid asc";
			pst = con.prepareStatement(sql);
			pst.setInt(1, oid);
			rs = pst.executeQuery();
			ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
			Order order = new OrderDAO().getById(oid);
			while (rs.next()) {
				Picture picture = new Picture(rs.getInt(12), rs.getString(13),
						rs.getString(14), rs.getInt(15));
				FoodType foodtype = new FoodType(rs.getInt(10),
						rs.getString(11));
				Food food = new Food(rs.getInt(4), foodtype, picture,
						rs.getString(7), rs.getString(8), rs.getFloat(9));
				OrderDetail od = new OrderDetail(order, food, rs.getInt(3));
				list.add(od);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

}
