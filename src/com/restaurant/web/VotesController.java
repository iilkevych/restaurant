package com.restaurant.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.business.VotesService;
import com.restaurant.domain.aggregation.MenuRating;
import com.restaurant.web.core.Status;

@RestController
public class VotesController {

	@Autowired
	VotesService votesService;
	
	@RequestMapping("/top")
	public List<MenuRating> top() {
		return votesService.top();
	}

	@RequestMapping("/vote")
	public Status vote(@RequestParam("menu") String menuId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return votesService.vote(menuId, authentication.getName());
	}
}
