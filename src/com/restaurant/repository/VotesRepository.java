package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.domain.Vote;
import com.restaurant.domain.aggregation.MenuRating;

public interface VotesRepository extends JpaRepository<Vote, Long> {

	@Query("SELECT new com.bikestore.domain.aggregation.MenuRating( v.menu as menu, count(v.account) as rating)"
			+ "FROM Vote v GROUP BY v.menu")
	List<MenuRating> getVotes();
}