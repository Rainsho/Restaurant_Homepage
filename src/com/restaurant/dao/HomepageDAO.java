package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.restaurant.entity.Homepage;

public class HomepageDAO extends BaseDAO {

	public ArrayList<Homepage> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from homepage";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ArrayList<Homepage> list = new ArrayList<Homepage>();
			while (rs.next()) {
				Homepage obj = new Homepage(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5));
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public void update(Homepage obj) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "update homepage set pic=?, title=?, contents=?, type=? where hid=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, obj.getPic());
			pst.setString(2, obj.getTitle());
			pst.setString(3, obj.getContents());
			pst.setString(4, obj.getType());
			pst.setInt(5, obj.getHid());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

}
