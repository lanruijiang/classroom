package org.soft.base.ctrl.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/yf5db?user=root&password=root&useUnicode=true&characterEncoding=utf8";
	private static Connection con = null;

	static {
		try {
			
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public static Connection getConnection() {
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	
	public static void close(ResultSet rs, Statement pstmt, Connection conn) {

		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
						con = null;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
