package com.account.controller;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	@Autowired
	AccountService accountService;
	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping(
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public Account save(@RequestBody Account a) {
		return accountService.save(a);
	}

	/*@Secured({"ROLE_USER", "ROLE_ADMIN"})*/
	@RequestMapping(
			method = RequestMethod.GET,
			headers="Accept=application/json",
			produces="application/json"
	)
	public Account getCurrentAccount(Principal principal) {
		Assert.notNull(principal);

		System.out.println("Principal: " + principal.toString() );

		return accountRepository.findOneByEmail(principal.getName());
	}


	@RequestMapping(
			value="/signin",
			method = RequestMethod.POST,
			headers="Accept=application/json",
			produces="application/json"
	)
	public void signin(@RequestBody Account a) {
		accountService.signin(a);
	}

	@RequestMapping(value = "account/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	@Secured("ROLE_ADMIN")
	public Account account(@PathVariable("id") String id) {
		return accountRepository.findOne(id);
	}


	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public void logOut() {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		/*
		 * System.out.println("Auth: " + auth); System.out.println("Auth 2: " +
		 * auth.getCredentials()); System.out.println("Auth 3: " +
		 * auth.getPrincipal());
		 */
	}
}
