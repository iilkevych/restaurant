package com.restaurant.business;

import java.util.List;

import com.restaurant.domain.aggregation.MenuRating;
import com.restaurant.web.core.Status;

public interface VotesService {

	public List<MenuRating> top();

	public Status vote(String menuId, String username);
}
