package com.restaurant.business;

import java.util.List;

import com.restaurant.domain.Restaurant;

public interface RestaurantService {

	/**
	 * Fetch all restaurants with their menus. 
	 */
	public List<Restaurant> getRestaurants();

	public Restaurant save(Restaurant r);

	public Restaurant getRestaurant(Long id);

	public void delete(Long id);

}
