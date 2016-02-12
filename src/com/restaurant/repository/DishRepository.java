package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.domain.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

	public List<Dish> findByMenuId(Long menuId);
}