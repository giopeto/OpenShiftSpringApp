package com.account.service;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostConstruct
	protected void initialize() {
		save(new Account("user", "demo", "ROLE_USER"));
		save(new Account("admin", "admin", "ROLE_ADMIN"));
	}

	@Transactional
	public Account save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountRepository.save(account);

		if (account.getId() != null) {
			authenticate(account);
		}

		return account;
	}

	public void authenticate(Account account) {
		Authentication auth = new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		return null;
	}
}
