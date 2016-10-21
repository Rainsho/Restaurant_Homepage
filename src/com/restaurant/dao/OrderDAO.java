package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.restaurant.entity.Order;

public class OrderDAO extends BaseDAO {

	public Order newOrder(Order order) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "insert into orders (odate, oquant, ofee, ocheck) values (?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, order.fmtDate());
			pst.setInt(2, order.getOquant());
			pst.setFloat(3, order.getOfee());
			pst.setInt(4, order.getOcheck());
			if (pst.executeUpdate() == 1) {
				pst = con.prepareStatement("select @@IDENTITY");
				rs = pst.executeQuery();
				rs.next();
				order.setOid(rs.getInt(1));
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public ArrayList<Order> getPage(int page, String date_s, String date_t,
			int ocheck) {
		if (date_s == null || date_s.equals("")) {
			date_s = "1900-01-01";
		}
		if (date_t == null || date_t.equals("")) {
			date_t = "9999-12-31";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] s = date_t.split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(s[0]), Integer.parseInt(s[1]) - 1,
					Integer.parseInt(s[2]) + 1);
			date_t = sdf.format(cal.getTime());
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			if (ocheck == -1) {
				String sql = "select top 10 * from orders where odate between ? and ? and oid not in "
						+ "(select top (10*?-10) oid from orders where odate between ? and ? order by odate desc) "
						+ "order by odate desc";
				pst = con.prepareStatement(sql);
				pst.setString(1, date_s);
				pst.setString(2, date_t);
				pst.setInt(3, page);
				pst.setString(4, date_s);
				pst.setString(5, date_t);
				rs = pst.executeQuery();
			} else {
				String sql = "select top 10 * from orders where odate between ? and ? and ocheck = ? and oid not in "
						+ "(select top (10*?-10) oid from orders where odate between ? and ? and ocheck = ? order by odate desc) "
						+ "order by odate desc";
				pst = con.prepareStatement(sql);
				pst.setString(1, date_s);
				pst.setString(2, date_t);
				pst.setInt(3, ocheck);
				pst.setInt(4, page);
				pst.setString(5, date_s);
				pst.setString(6, date_t);
				pst.setInt(7, ocheck);
				rs = pst.executeQuery();
			}
			ArrayList<Order> list = new ArrayList<Order>();
			while (rs.next()) {
				Calendar odate = Calendar.getInstance();
				odate.setTime(rs.getTimestamp(2));
				Order order = new Order(rs.getInt(1), odate, rs.getInt(3),
						rs.getFloat(4), rs.getInt(5));
				list.add(order);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public int getQuant(String date_s, String date_t, int ocheck) {
		if (date_s == null || date_s.equals("")) {
			date_s = "1900-01-01";
		}
		if (date_t == null || date_t.equals("")) {
			date_t = "9999-12-31";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String[] s = date_t.split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(s[0]), Integer.parseInt(s[1]) - 1,
					Integer.parseInt(s[2]) + 1);
			date_t = sdf.format(cal.getTime());
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			if (ocheck == -1) {
				String sql = "select count(*) from orders where odate between ? and ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, date_s);
				pst.setString(2, date_t);
				rs = pst.executeQuery();
			} else {
				String sql = "select count(*) from orders where odate between ? and ? and ocheck = ?";
				pst = con.prepareStatement(sql);
				pst.setString(1, date_s);
				pst.setString(2, date_t);
				pst.setInt(3, ocheck);
				rs = pst.executeQuery();
			}
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return -1;
	}

	public void checkOut(int oid) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "update orders set ocheck = ? where oid = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, 1);
			pst.setInt(2, oid);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public Order getById(int oid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from orders where oid = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, oid);
			rs = pst.executeQuery();
			if (rs.next()) {
				Calendar odate = Calendar.getInstance();
				odate.setTime(rs.getTimestamp(2));
				Order order = new Order(rs.getInt(1), odate, rs.getInt(3),
						rs.getFloat(4), rs.getInt(5));
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

}
