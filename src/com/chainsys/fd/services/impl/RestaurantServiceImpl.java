package com.chainsys.fd.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.fd.dao.MenuDAO;
import com.chainsys.fd.dao.RestaurantDAO;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.services.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {

	@Override
	public int getCategoryByRestaurant(int restaurantId) {
		RestaurantDAO restaurantDAO=new RestaurantDAO();
		int categoryId = 0;
		try {
			categoryId=restaurantDAO.getCategoryByRestaurant(restaurantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categoryId;
	}

	@Override
	public ArrayList<Menu> getMenu(int categoryId) {
	MenuDAO menuDAO=new MenuDAO();
	ArrayList<Menu> list = null;
	try {
		list=menuDAO.getMenu(categoryId);
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return list;
	}

	@Override
	public ArrayList<Restaurant> getRestaurant() {
		RestaurantDAO restaurantDAO=new RestaurantDAO();
		ArrayList<Restaurant> list = null;
		try {
			list=restaurantDAO.getRestaurant();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) {
		Restaurant restaurant = null;
		RestaurantDAO restaurantDAO=new RestaurantDAO();
		try {
			restaurant=restaurantDAO.getRestaurantById(restaurantId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restaurant;
	}
}
