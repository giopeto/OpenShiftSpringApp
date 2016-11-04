package com.ps.repository;

import com.ps.domain.PS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PSRepository extends MongoRepository<PS, String> {
    PS getById(String userId);

    List<PS> getByUserId(String userId);
}
