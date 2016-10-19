package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

import com.restaurant.entity.Reservation;
import com.restaurant.entity.User;

public class ReservationDAO extends BaseDAO {

	public ArrayList<Reservation> getPage(int page, String date_s, String date_t) {
		if (date_s == null || date_s.equals("")) {
			date_s = "1900-01-01";
		}
		if (date_t == null || date_t.equals("")) {
			date_t = "9999-12-31";
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select top 10 a.*, b.* from reservation as a, [user] as b where a.uid = b.uid "
					+ "and a.resdate between ? and ? and a.resid not in "
					+ "(select top (10*?-10) resid from reservation where resdate between ? and ? order by resdate desc) "
					+ "order by resdate desc";
			pst = con.prepareStatement(sql);
			pst.setString(1, date_s);
			pst.setString(2, date_t);
			pst.setInt(3, page);
			pst.setString(4, date_s);
			pst.setString(5, date_t);
			rs = pst.executeQuery();
			ArrayList<Reservation> list = new ArrayList<Reservation>();
			while (rs.next()) {
				User user = new User();
				user.setUname(rs.getString(8));
				user.setUtelphone(rs.getString(10));
				Calendar cal = Calendar.getInstance();
				cal.setTime(rs.getTimestamp(4));
				Reservation res = new Reservation(rs.getInt(1), user,
						rs.getString(3), cal, rs.getString(5), rs.getInt(6));
				list.add(res);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public int getQuant(String date_s, String date_t) {
		if (date_s == null || date_s.equals("")) {
			date_s = "1900-01-01";
		}
		if (date_t == null || date_t.equals("")) {
			date_t = "9999-12-31";
		}
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select count(*) from reservation where resdate between ? and ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, date_s);
			pst.setString(2, date_t);
			rs = pst.executeQuery();
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

	public void addRes(Reservation res) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "insert into reservation (uid, restitle, resdate, resinfo, resseat) values (?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, res.getUser().getUid());
			pst.setString(2, res.getRestitle());
			pst.setString(3, res.fmtDate());
			pst.setString(4, res.getResinfo());
			pst.setInt(5, res.getResseat());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

}
