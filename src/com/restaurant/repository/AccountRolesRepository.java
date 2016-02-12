package com.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.domain.AccountRole;

public interface AccountRolesRepository extends JpaRepository<AccountRole, Long> {
	
	public List<AccountRole> findByAccountId(Long accountId); 
}