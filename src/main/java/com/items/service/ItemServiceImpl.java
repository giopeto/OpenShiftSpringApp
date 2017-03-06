package com.items.service;

import com.account.domain.Account;
import com.account.repository.AccountRepository;
import com.files.service.FilesService;
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
    private final FilesService filesService;

    @Autowired
    public ItemServiceImpl(ItemsRepository repository, AccountRepository accRepository, FilesService filesService) {
        this.repository = repository;
        this.accRepository = accRepository;
        this.filesService = filesService;
    }

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

        List<Account> accountsList = accRepository.findByIdIn(accountIds);

        for(Map comment: item.getComments()){
            System.out.println(comment.get("userId"));
            for(Account acc: accountsList){
                if(acc.getId().equals(comment.get("userId"))){
                    comment.put("userEmail", acc.getEmail());
                    comment.put("userFileId", acc.getFileId());
                    break;
                }
            }
        }

        return item;
    }


    public List getByGroupId(String groupId) {
        return repository.findByGroupId(groupId);
    }


    public String delete(String id) {
        deleteImageToItem(id);
        return repository.deleteById(id);
    }


    public void deleteImageToItem(String id) {
        Item i = getById(id);
        filesService.delete(i.getFileIds());
    }


}
