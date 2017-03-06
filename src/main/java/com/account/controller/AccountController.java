package com.account.controller;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Account save(@RequestBody Account a) {return accountService.save(a);}

    @RequestMapping(
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Account getCurrentAccount(Principal principal) {
        Assert.notNull(principal);
        return accountRepository.findOneByEmail(principal.getName());
    }


    @RequestMapping(
            value = "/signin",
            method = RequestMethod.POST,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public void signin(@RequestBody Account a) {
        accountService.signin(a);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    @Secured("ROLE_USER")
    public Account account(@PathVariable("id") String id) {
        return accountRepository.findOne(id);
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public void update(@PathVariable String id, @RequestBody Account item) {
        accountRepository.save(item);
    }


    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public void logOut() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    }
}
