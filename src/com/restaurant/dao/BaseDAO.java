package com.restaurant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDAO {
	// MSSQLSERVER
	private static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String URL = "jdbc:sqlserver://127.0.0.1:1433;DataBaseName=";
	private static String USER = "sa";
	private static String PSWD = "root";
	private static String DB = "Restaurant";
	private static String SSL = "";

	// MY SQL
	static {
		if (!System.getProperties().getProperty("os.name").equals("Windows 7")) {
			DRIVER = "com.mysql.jdbc.Driver";
			URL = "jdbc:mysql://127.0.0.1:3306/";
			USER = "root";
			PSWD = "rain@sql";
			DB = "Restaurant";
			SSL = "?useSSL=false&characterEncoding=utf8";
		}
	}

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
