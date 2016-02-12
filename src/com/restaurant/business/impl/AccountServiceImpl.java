package com.restaurant.business.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.business.AccountService;
import com.restaurant.domain.Account;
import com.restaurant.domain.AccountRole;
import com.restaurant.repository.AccountRepository;
import com.restaurant.repository.AccountRolesRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountRolesRepository accountRolesRepository;

	@Override
	public Account getByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public Account save(Account account) {
		if(account.getId() != null){
			List<AccountRole> roles= accountRolesRepository.findByAccountId(account.getId());
			Set<AccountRole> input = account.getAccountRoles();
			Set<AccountRole> deleted = new HashSet<AccountRole>();
			for(AccountRole r : roles) {
				if(!input.contains(r)){
					deleted.add(r);
				}
			}

			accountRolesRepository.deleteInBatch(deleted);
		}

		return accountRepository.save(account);
	}

}
