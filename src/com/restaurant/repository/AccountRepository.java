package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	
	public Account findByUsername(String username);
}