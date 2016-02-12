package com.restaurant.business;

import java.util.List;

import com.restaurant.domain.Menu;

public interface MenuService {

	List<Menu> getMenus(Long restaurantId);

	Menu save(Menu r);

	/**
	 * Cascade delete is configured for dishes on foreign key 
	 * @param id
	 */
	void delete(Long id);

}
