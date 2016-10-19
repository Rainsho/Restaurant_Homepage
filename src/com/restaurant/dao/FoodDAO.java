package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.restaurant.entity.Food;
import com.restaurant.entity.FoodType;
import com.restaurant.entity.Picture;

public class FoodDAO extends BaseDAO {

	public ArrayList<Food> getPage(int page, String fname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select top 10 a.*, b.*, c.* from food as a, foodtype as b, picture as c "
					+ "where a.ftid = b.ftid and a.pid = c.pid and a.fname like ? "
					+ "and a.fid not in (select top (10*?-10) fid from food where fname like ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + fname + "%");
			pst.setInt(2, page);
			pst.setString(3, "%" + fname + "%");
			rs = pst.executeQuery();
			ArrayList<Food> list = new ArrayList<Food>();
			while (rs.next()) {
				FoodType foodtype = new FoodType(rs.getInt(7), rs.getString(8));
				Picture picture = new Picture(rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getInt(12));
				Food food = new Food(rs.getInt(1), foodtype, picture,
						rs.getString(4), rs.getString(5), rs.getFloat(6));
				list.add(food);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public int totalFood(String fname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select count(*) from food where fname like ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + fname + "%");
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

	public void delFood(int fid) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "delete from food where fid=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, fid);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public void addFood(int ftid, int pid, String fname, String fdetial,
			float fprice) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "insert into food (ftid, pid, fname, fdetial, fprice) values (?, ?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, ftid);
			pst.setInt(2, pid);
			pst.setString(3, fname);
			pst.setString(4, fdetial);
			pst.setFloat(5, fprice);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public Food getById(int fid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select top 10 a.*, b.*, c.* from food as a, foodtype as b, picture as c "
					+ "where a.ftid = b.ftid and a.pid = c.pid and a.fid = ?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, fid);
			rs = pst.executeQuery();
			if (rs.next()) {
				FoodType foodtype = new FoodType(rs.getInt(7), rs.getString(8));
				Picture picture = new Picture(rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getInt(12));
				return new Food(rs.getInt(1), foodtype, picture,
						rs.getString(4), rs.getString(5), rs.getFloat(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, rs);
		}
		return null;
	}

	public void updateFood(int fid, int ftid, int pid, String fname,
			String fdetial, float fprice) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "update food set ftid=?, pid=?, fname=?, fdetial=?, fprice=? where fid=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, ftid);
			pst.setInt(2, pid);
			pst.setString(3, fname);
			pst.setString(4, fdetial);
			pst.setFloat(5, fprice);
			pst.setInt(6, fid);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}
	
	public ArrayList<Food> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select a.*, b.*, c.* from food as a, foodtype as b, picture as c "
					+ "where a.ftid = b.ftid and a.pid = c.pid";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ArrayList<Food> list = new ArrayList<Food>();
			while (rs.next()) {
				FoodType foodtype = new FoodType(rs.getInt(7), rs.getString(8));
				Picture picture = new Picture(rs.getInt(9), rs.getString(10),
						rs.getString(11), rs.getInt(12));
				Food food = new Food(rs.getInt(1), foodtype, picture,
						rs.getString(4), rs.getString(5), rs.getFloat(6));
				list.add(food);
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
