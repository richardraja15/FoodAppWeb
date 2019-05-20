package com.chainsys.fd.services.impl;

import java.util.ArrayList;
import com.chainsys.fd.dao.FindDAO;
import com.chainsys.fd.model.Restaurant;
import com.chainsys.fd.services.FindService;

public class FindServiceImpl implements FindService {
	FindDAO dao=new FindDAO();
	@Override
	public ArrayList<Restaurant> getRestaurant(String search) {
		ArrayList<Restaurant> restaurants = null;
		try {
			restaurants=dao.getRestaurant(search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return restaurants;
	}
}
