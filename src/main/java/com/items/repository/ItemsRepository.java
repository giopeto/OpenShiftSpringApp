package com.items.repository;

import com.items.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends MongoRepository<Item, String> {
    Item findById(String id);

    String deleteById(String id);

    List findByGroupId(String groupId);
}
