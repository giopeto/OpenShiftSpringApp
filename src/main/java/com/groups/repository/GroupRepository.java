package com.groups.repository;

import com.groups.domain.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
	Group findById(String id);
	String deleteById(String id);
}
