package com.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
	// MSSQLSERVER
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "jdbc:sqlserver://192.168.5.55:1433;DataBaseName=";
	private static final String USER = "sa";
	private static final String PSWD = "root";
	private static final String DB = "Restaurant";
	private static final String SSL = "";

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
