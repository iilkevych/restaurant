package com.restaurant.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.restaurant.business.VotesService;
import com.restaurant.domain.Account;
import com.restaurant.domain.Menu;
import com.restaurant.domain.Vote;
import com.restaurant.domain.aggregation.MenuRating;
import com.restaurant.repository.AccountRepository;
import com.restaurant.repository.MenuRepository;
import com.restaurant.repository.VotesRepository;
import com.restaurant.web.core.Status;

@Service
public class VotesServiceImpl implements VotesService {

	@Autowired
	VotesRepository votesRepository;
	@Autowired
	AccountRepository accountRepository;
	@Autowired
	MenuRepository menuRepository;

	@Override
	public List<MenuRating> top() {
		return votesRepository.getVotes();
	}

	@Override
	public Status vote(String menuId, String username) {
		Status s = new Status();
		Menu m = menuRepository.getOne(Long.valueOf(menuId));
		Account a = accountRepository.findByUsername(username);
		try {
			votesRepository.save(new Vote(a, m));
		} catch (JpaSystemException e) {
			s.setCode(-1);
			s.setErrormessage(e.getRootCause().getMessage());
		}
		return s;
	}

}
