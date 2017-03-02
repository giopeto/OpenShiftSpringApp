package com.items.service;

import com.items.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by George on 15.5.2016 г..
 */
@Service
public interface ItemService {
    Item save(Item item);
    List get();
    Item getById(String id);
    List getByGroupId(String groupId);
    String delete(String id);
    void deleteImageToItem(String id);
}
