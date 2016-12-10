package com.items.service;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import com.items.domain.Item;
import com.items.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemsRepository repository;
    private final AccountRepository accRepository;

    @Autowired
    public ItemServiceImpl(ItemsRepository repository, AccountRepository accRepository) {
        this.repository = repository;
        this.accRepository = accRepository;
    }
   /* @Autowired
    public ItemServiceImpl(AccountRepository accRepository) {
        this.accRepository = accRepository;
    }*/

    public Item save(Item item) {
        if (item.getDate() == null) {
            item.setDate(new Date());
        }
        repository.save(item);
        return item;
    }

    public List get() {
        return repository.findAll();
    }


    public Item getById(String id) {

        Item item = repository.findById(id);
        List accountIds = new ArrayList<String>();
        for(Map comment: item.getComments()){
            accountIds.add(comment.get("userId"));
        }
        System.out.println("****************************************************************************\n\n\n\n\n");

        List<Account> accountsList = accRepository.findByIdIn(accountIds);

        for(Map comment: item.getComments()){
            System.out.println(comment.get("userId"));
            for(Account acc: accountsList){

                System.out.println(comment.get("userId") + "==>>" + acc.getId());


                if(acc.getId().equals(comment.get("userId"))){
                    comment.put("userEmail", acc.getEmail());
                    comment.put("userFileId", acc.getFileId());
                    break;
                }
            }
        }

        System.out.println(accountsList);
        System.out.println(accountIds);
        System.out.println("****************************************************************************\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n ");

        return item;
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
