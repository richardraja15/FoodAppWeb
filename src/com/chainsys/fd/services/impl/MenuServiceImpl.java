package com.chainsys.fd.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import com.chainsys.fd.dao.MenuDAO;
import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.services.MenuService;

public class MenuServiceImpl implements MenuService {

	@Override
	public ArrayList<Menu> getMenuItems() {
		MenuDAO menuDAO=new MenuDAO();
		ArrayList<Menu> menus = null;
		try {
			menus=menuDAO.getMenuItems();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}

	@Override
	public ArrayList<Restaurant> getRestaurantByCategory(int categoryId) {
		ArrayList<Restaurant> restaurants = null;
		MenuDAO menuDAO=new MenuDAO();
		try {
			restaurants=menuDAO.getRestaurantByCategory(categoryId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restaurants;
	}

	@Override
	public ArrayList<Menu> getMenu(int categoryId) {
		ArrayList<Menu> menus = null;
		MenuDAO menuDAO=new MenuDAO();
		try {
			menus=menuDAO.getMenu(categoryId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menus;
	}
}
