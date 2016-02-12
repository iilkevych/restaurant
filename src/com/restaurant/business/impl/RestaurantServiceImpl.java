package com.restaurant.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.business.RestaurantService;
import com.restaurant.domain.Restaurant;
import com.restaurant.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> getRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurant(Long id) {
		return restaurantRepository.findOne(id);
	}

	@Override
	public Restaurant save(Restaurant r) {
		return restaurantRepository.save(r);
	}

	@Override
	public void delete(Long id) {
		restaurantRepository.delete(id);
	}
}
