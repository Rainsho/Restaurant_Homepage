package com.restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.restaurant.entity.FoodType;

public class FoodTypeDAO extends BaseDAO {

	public ArrayList<FoodType> getAll() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from foodtype";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			ArrayList<FoodType> list = new ArrayList<FoodType>();
			while (rs.next()) {
				FoodType obj = new FoodType(rs.getInt(1), rs.getString(2));
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

	public void addType(String ftname) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = getCon();
			String sql = "insert into foodtype (ftname) values (?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, ftname);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeCon(con, pst, null);
		}
	}

	public int getIdByName(String ftname) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select ftid from foodtype where ftname = ?";
			pst = con.prepareStatement(sql);
			pst.setString(1, ftname);
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

}
