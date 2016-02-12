package com.restaurant.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.restaurant.domain.Account;
import com.restaurant.domain.AccountRole;
import com.restaurant.repository.AccountRepository;

public class BSUserDetailsService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) 
               throws UsernameNotFoundException {

		Account account = accountRepository.findByUsername(username);

		if(account == null) throw new UsernameNotFoundException(username);
		account.getAccountRoles();
		return buildUserForAuthentication(account);
		

	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(Account account) {
		return new User(account.getUsername(), 
				account.getPassword(), account.isEnabled(), 
                        true, true, true, buildUserAuthority(account.getAccountRoles()));
	}

	private List<GrantedAuthority> buildUserAuthority(Set<AccountRole> accountRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (AccountRole accountRole : accountRoles) {
			setAuths.add(new SimpleGrantedAuthority(accountRole.getRole().toString()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}
