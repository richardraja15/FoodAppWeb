package com.chainsys.fd.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author rich2110
 *This class is used to connect with database
 */
public class ConnectionUtil {
	/**
	 * This method is used to get Connection
	 * @return Connection object
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			connection = DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("unable to load the class");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("unable to get connection");
		}
		return connection;
	}
/**
 * This method is used to close connection
 * @param con
 * @param pstmt
 * @param rs
 * @throws SQLException
 */
	public static void close(Connection con, PreparedStatement pstmt,
			ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (con != null) {
			con.close();
		}
	}
}
