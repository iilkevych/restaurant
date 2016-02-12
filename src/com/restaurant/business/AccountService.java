package com.restaurant.business;

import com.restaurant.domain.Account;

public interface AccountService {

	public Account getByUsername(String username);

	public Account save(Account account);

}
