package com.chainsys.fd.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.fd.model.Category;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.util.ConnectionUtil;

public class MenuDAO {

	public ArrayList<Menu> getMenuItems() throws SQLException 
	{
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ArrayList< Menu> menuName=new ArrayList<>();
		try {
			String sql = "select category_id,menu_name from trn_menu";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Menu menu=new Menu();
				menu.setName(resultSet.getString("menu_name"));
				//menu.setPrice(resultSet.getLong("price"));
				menuName.add(menu);
			}
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return menuName;

	

	}

	public ArrayList<Restaurant> getRestaurantByMenu(int categoryId) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList< Restaurant> restaurants=new ArrayList<>();
		
		try {
			String sql = "select restaurant_name from restaurant where category_id = ?";
		//String sql="SELECT r.restaurant_id as restid, r.restaurant_name as restname,ct.category_id as categoryid FROM restaurant  r INNER JOIN category ct  ON r.category_id = ct.category_id";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, categoryId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Restaurant restaurant=new Restaurant();
				restaurant.setRestaurantName(resultSet.getString("restname")) ;
				//System.out.println("name"+name);
				restaurants.add(restaurant);
			}
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, resultSet);
		}
		return restaurants;

	}

}
