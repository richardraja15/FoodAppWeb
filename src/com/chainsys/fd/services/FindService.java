package com.chainsys.fd.services;

import java.util.ArrayList;

import com.chainsys.fd.model.Restaurant;

public interface FindService {
	public ArrayList<Restaurant> getRestaurant(String search);

}
