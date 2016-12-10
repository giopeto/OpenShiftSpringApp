package com.account.repository;

import com.account.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findOneByEmail(String email);


    List<Account> findByIdIn (List<String> ids);


}