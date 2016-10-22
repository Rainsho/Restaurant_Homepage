package com.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {

	// MY SQL
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/";
	private static final String USER = "root";
	private static final String PSWD = "rain@sql";
	private static final String DB = "Restaurant";
	private static final String SSL = "?useSSL=false&characterEncoding=utf8";

	public Connection getCon() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL + DB + SSL, USER, PSWD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void closeCon(Connection con, PreparedStatement pst, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
