package com.chainsys.fd.services;

import java.util.ArrayList;

import com.chainsys.fd.model.Menu;
import com.chainsys.fd.model.Restaurant;

public interface RestaurantService {
	public int getCategoryByRestaurant(int restaurantId);
	public ArrayList<Menu> getMenu(int categoryId);
	public ArrayList<Restaurant> getRestaurant();
	public Restaurant getRestaurantById(int restaurantId);
}
