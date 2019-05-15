package com.chainsys.fd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.fd.model.Category;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.util.ConnectionUtil;

public class RestaurantDAO {

	public int getUserCity() throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id = 0;
		try {
			String sql = "select city_id from users where user_id=1";
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("emp_id");
			}
		} catch (Exception e) {
			throw new Exception("unable to get id");
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return id;

	}

	public ArrayList<Restaurant> getRestaurant() throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Restaurant> restaurants = new ArrayList<>();

		try {
			// String sql = "select restaurant_name from restaurant where restaurant_name
			// LIKE ?";
			String sql = "SELECT r.restaurant_id as restid, r.restaurant_name as restname,ct.category_id as categoryid FROM restaurant  r INNER JOIN category ct  ON r.category_id = ct.category_id";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(resultSet.getInt("restid"));
				restaurant.setRestaurantName(resultSet.getString("restname"));
				Category category = new Category();
				category.setCategoryId(resultSet.getInt("categoryid"));
				restaurant.setCategory(category);

				// System.out.println("name"+name);
				restaurants.add(restaurant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return restaurants;

	}

	public int getCategoryByRestaurant(int id) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int categoryId = 0;
		try {
			String sql = "select category_id from restaurant where restaurant_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				categoryId = resultSet.getInt("category_id");
			}
		} catch (Exception e) {
			throw new Exception("unable to get id");
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}

		return categoryId;
	}

}
