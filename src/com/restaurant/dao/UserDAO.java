package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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

	public ArrayList<User> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from [user]";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ArrayList<User> list = new ArrayList<User>();
			while (rs.next()) {
				User obj = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
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

	public ArrayList<User> getPage(int page, String uname, int utype) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select top 10 * from [user] where uname like ? and utype like ? and uid not in"
					+ " (select top (10*?-10) uid from [user] where uname like ? and utype like ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + uname + "%");
			pst.setString(2, "%" + utype + "%");
			pst.setInt(3, page);
			pst.setString(4, "%" + uname + "%");
			pst.setString(5, "%" + utype + "%");
			if (utype == 0) {
				pst.setString(2, "%");
				pst.setString(5, "%");
			}
			rs = pst.executeQuery();
			ArrayList<User> list = new ArrayList<User>();
			while (rs.next()) {
				User obj = new User(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getInt(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
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

	public int getQuant(String uname, int utype) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select count(*) from [user] where uname like ? and utype like ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + uname + "%");
			pst.setString(2, "%" + utype + "%");
			if (utype == 0) {
				pst.setString(2, "%");
			}
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

	public void deleteById(int uid) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "delete from [user] where uid=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public User getById(int uid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from [user] where uid=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, uid);
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

	public void addUser(User user) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = null;
			switch (user.getUtype()) {
			case 1:
				sql = "insert into [user] (uname, upassword, utelphone, utype) values (?, ?, ?, ?)";
				pst = con.prepareStatement(sql);
				pst.setString(1, user.getUname());
				pst.setString(2, user.getUpassword());
				pst.setString(3, user.getUtelphone());
				pst.setInt(4, user.getUtype());
				break;
			case 2:
				sql = "insert into [user] (uname, utelphone, utype, ustaffinfo, ustaffdisplay, upic) values (?, ?, ?, ?, ?, ?)";
				pst = con.prepareStatement(sql);
				pst.setString(1, user.getUname());
				pst.setString(2, user.getUtelphone());
				pst.setInt(3, user.getUtype());
				pst.setString(4, user.getUstaffinfo());
				pst.setInt(5, user.getUstaffdisplay());
				pst.setString(6, user.getUpic());
				break;
			case 3:
				sql = "insert into [user] (uname, utelphone, utype) values (?, ?, ?)";
				pst = con.prepareStatement(sql);
				pst.setString(1, user.getUname());
				pst.setString(2, user.getUtelphone());
				pst.setInt(3, user.getUtype());
				break;
			}
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public void updateUser(User user) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = null;
			switch (user.getUtype()) {
			case 1:
				sql = "update [user] set uname=?, upassword=?, utelphone=?, utype=? where uid=?";
				pst = con.prepareStatement(sql);
				pst.setString(1, user.getUname());
				pst.setString(2, user.getUpassword());
				pst.setString(3, user.getUtelphone());
				pst.setInt(4, user.getUtype());
				pst.setInt(5, user.getUid());
				break;
			case 2:
				sql = "update [user] set uname=?, utelphone=?, utype=?, ustaffinfo=?, ustaffdisplay=?, upic=? where uid=?";
				pst = con.prepareStatement(sql);
				pst.setString(1, user.getUname());
				pst.setString(2, user.getUtelphone());
				pst.setInt(3, user.getUtype());
				pst.setString(4, user.getUstaffinfo());
				pst.setInt(5, user.getUstaffdisplay());
				pst.setString(6, user.getUpic());
				pst.setInt(7, user.getUid());
				break;
			case 3:
				sql = "update [user] set uname=?, utelphone=?, utype=? where uid=?";
				pst = con.prepareStatement(sql);
				pst.setString(1, user.getUname());
				pst.setString(2, user.getUtelphone());
				pst.setInt(3, user.getUtype());
				pst.setInt(4, user.getUid());
				break;
			}
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

}
