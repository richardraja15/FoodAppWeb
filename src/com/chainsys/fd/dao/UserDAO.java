package com.chainsys.fd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.chainsys.fd.util.ConnectionUtil;

public class UserDAO {
	public boolean validateLogin(String userId, String password) throws Exception {
		boolean isValid = false;
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = "select user_id,password from userlogin";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery(sql);
			while (resultSet.next()) {
				if (resultSet.getString("user_id").contentEquals(userId)
						&& resultSet.getString("password").contentEquals(password)) {
					isValid = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return isValid;
	}
}
