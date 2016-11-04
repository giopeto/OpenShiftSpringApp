package com.items.service;

import com.items.domain.Item;
import com.items.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class ItemServiceImpl implements ItemService{

	private final ItemsRepository repository;

	@Autowired
	public ItemServiceImpl(ItemsRepository repository) {
		this.repository = repository;
	}

	public Item save(Item item) {
		if(item.getDate()==null) {
			item.setDate(new Date());
		}
		repository.save(item);
		return item;
	}

	public List get() {
		return repository.findAll();
	}


	public Item getById(String id) {
		return repository.findById(id);
	}


	public List getByGroupId(String groupId) {
		//return objectifyService.ofy().load().type(Item.class).filter("groupId", groupId).order("-date").list();
		return repository.findByGroupId(groupId);
	}



	public String delete(String id) {

		deleteImageToItem(id);
		return repository.deleteById(id);
	}


	public void deleteImageToItem(String id) {
		/*BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

		Item item = getById(id);
		Map<String, String> fileIds = item.getFileIds();

		for (Map.Entry<String, String> entry : fileIds.entrySet()) {
			//System.out.println(entry.getKey() + "/" + entry.getValue());
			BlobKey blobKey = new BlobKey(entry.getKey());
			blobstoreService.delete(blobKey);
		}*/
	}


}
