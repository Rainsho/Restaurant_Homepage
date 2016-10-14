package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.restaurant.entity.User;

public class UserDAO extends BaseDAO {

	public User login(String uname, String upassword) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from [user] where uname=? and upassword=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, uname);
			pst.setString(2, upassword);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5), rs.getString(6),
						rs.getInt(7), rs.getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

}
