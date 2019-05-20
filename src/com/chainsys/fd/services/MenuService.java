package com.chainsys.fd.services;

import java.util.ArrayList;

import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;

public interface MenuService {
	public ArrayList<Menu> getMenuItems();
	public ArrayList<Restaurant> getRestaurantByCategory(int categoryId);
	public ArrayList<Menu> getMenu(int categoryId);
}
