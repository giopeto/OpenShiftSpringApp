package com.account.service;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    protected void initialize() {
        if(accountRepository.findOneByEmail("admin@admin.com")==null){
            save(new Account("admin@admin.com", "admin", "ROLE_ADMIN"));
        }
    }

    @Transactional
    public Account save(Account account) {
        if(accountRepository.findOneByEmail(account.getEmail()) != null){
            throw new UsernameNotFoundException("111111   There is an account with that email address: " + account.getEmail());
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);

        if (account.getId() != null) {
            signin(account);
        }

        return account;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findOneByEmail(username);
        if (account == null) {
            throw new UsernameNotFoundException("222222   User not found: " + account.getEmail());
        }
        return createUser(account);
    }

    public void signin(Account account) {
        Account dbAccount = accountRepository.findOneByEmail(account.getEmail());
        System.out.println("Before Error: "+dbAccount.toString());
        if(dbAccount!=null && passwordEncoder.matches(account.getPassword(), dbAccount.getPassword())){
            account.setRole(dbAccount.getRole());
            SecurityContextHolder.getContext().setAuthentication(authenticate(account));
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        }else{
            throw new UsernameNotFoundException("33333333   Username not found  or password not match.");
        }
    }

    private Authentication authenticate(Account account) {
        Authentication auth = new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
        return new UsernamePasswordAuthenticationToken(createUser(account), null, Collections.singleton(createAuthority(account)));
    }

    private User createUser(Account account) {
        return new User(account.getEmail(), account.getPassword(), Collections.singleton(createAuthority(account)));
    }

    private GrantedAuthority createAuthority(Account account) {
        return new SimpleGrantedAuthority(account.getRole());
    }

}
