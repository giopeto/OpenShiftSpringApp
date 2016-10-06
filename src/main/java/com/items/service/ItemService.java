package com.items.service;

import com.items.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by George on 15.5.2016 г..
 */
@Service
public interface ItemService {

	public Item save(Item item);
	public List get();
	public Item getById(String id);
	public List getByGroupId(String groupId);
	public String delete(String id);
	public void deleteImageToItem(String id);
}
