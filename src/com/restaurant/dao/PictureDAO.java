package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.restaurant.entity.Picture;

public class PictureDAO extends BaseDAO {

	public ArrayList<Picture> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from picture";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ArrayList<Picture> list = new ArrayList<Picture>();
			while (rs.next()) {
				Picture obj = new Picture(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4));
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

	public void changeDisplay(int pid, int pdisplay) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = getCon();
			String sql = "update picture set pdisplay = ? where pid = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, pdisplay);
			pst.setInt(2, pid);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}

	}

	public void addPic(Picture pic) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = getCon();
			String sql = "insert into picture (pname, ppath) values (?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, pic.getPname());
			pst.setString(2, pic.getPpath());
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public void delPic(int pid) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = getCon();
			String sql = "delete from picture where pid = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, pid);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public ArrayList<Picture> getPage(int page) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select top 10 * from picture where pid not in (select top (10*?-10) pid from picture)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, page);
			rs = pst.executeQuery();
			ArrayList<Picture> list = new ArrayList<Picture>();
			while (rs.next()) {
				Picture obj = new Picture(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4));
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

	public Picture getPicByPname(String pname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from picture where pname = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, pname);
			rs = pst.executeQuery();
			if (rs.next()) {
				return new Picture(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

}
