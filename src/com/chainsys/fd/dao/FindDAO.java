package com.chainsys.fd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.chainsys.fd.model.Category;
import com.chainsys.fd.model.City;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.util.ConnectionUtil;

public class FindDAO {

	/**
	 * This method is used to get restaurant details using search keyword.
	 * 
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public ArrayList<Restaurant> getRestaurant(String search) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Restaurant> restaurants = new ArrayList<>();
		try {
			String sql = "SELECT r.restaurant_id as restid, r.restaurant_name as restname,r.phone_number as phone,r.address as address,c.city_name as city,ct.category_id as categoryid,ct.NAME as name FROM ((restaurant  r  INNER JOIN city c  ON r.city_id = c.city_id)INNER JOIN category ct  ON r.category_id = ct.category_id) where r.restaurant_name LIKE ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, search + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantId(resultSet.getInt("restid"));
				restaurant.setRestaurantName(resultSet.getString("restname"));
				restaurant.setPhoneNumber(resultSet.getLong("phone"));
				restaurant.setAddress(resultSet.getString("address"));
				City city = new City();
				city.setCityName(resultSet.getString("city"));
				restaurant.setCity(city);
				Category category = new Category();
				category.setCategoryName(resultSet.getString("name"));
				category.setCategoryId(resultSet.getInt("categoryid"));
				restaurant.setCategory(category);
				restaurants.add(restaurant);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return restaurants;
	}

	/**
	 * This method is used to get menu details using categoryId.
	 * 
	 * @param categoryId
	 * @return
	 * @throws SQLException
	 */
}
