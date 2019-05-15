package com.chainsys.fd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.fd.model.Category;
import com.chainsys.fd.model.City;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.util.ConnectionUtil;

public class SearchDAO {

	public ArrayList<Restaurant> getRestaurant(String search) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<Restaurant> restaurants = new ArrayList<>();

		try {
			// String sql = "select restaurant_name from restaurant where restaurant_name
			// LIKE ?";
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

	public ArrayList<Menu> getMenu(int categoryId) throws SQLException {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		ArrayList<Menu> menuName = new ArrayList<>();
		if (categoryId == 4) {
			try {
				String sql = "select menu_id,menu_name,price from trn_menu where category_id=1 or category_id=2";
				preparedStatement = connection.prepareStatement(sql);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Menu menu = new Menu();
					menu.setMenuId(resultSet.getInt("menu_id"));
					menu.setName(resultSet.getString("menu_name"));
					menu.setPrice(resultSet.getLong("price"));
					menuName.add(menu);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionUtil.close(connection, preparedStatement, resultSet);
			}

		} else {
			try {
				String sql = "select menu_id,menu_name,price from trn_menu where category_id=? ";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, categoryId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					Menu menu = new Menu();
					menu.setMenuId(resultSet.getInt("menu_id"));
					menu.setName(resultSet.getString("menu_name"));
					menu.setPrice(resultSet.getLong("price"));
					menuName.add(menu);

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ConnectionUtil.close(connection, preparedStatement, resultSet);
			}
		}
		return menuName;

	}
	/*
	 * public int getCategory(int id) throws Exception { Connection connection =
	 * ConnectionUtil.getConnection(); PreparedStatement preparedStatement = null;
	 * ResultSet resultSet = null; int category; try { String sql =
	 * "select category_id from restaura where menu_name LIKE ? "; preparedStatement
	 * = connection.prepareStatement(sql); preparedStatement.setString(1,
	 * search+"%"); resultSet = preparedStatement.executeQuery(); while
	 * (resultSet.next()) { name = resultSet.getString("menu_name");
	 * System.out.println(name); names=new ArrayList<String>(); names.add(name); } }
	 * catch (Exception e) { e.printStackTrace(); } finally {
	 * ConnectionUtil.close(connection, preparedStatement, resultSet); } return
	 * names;
	 * 
	 */
}
