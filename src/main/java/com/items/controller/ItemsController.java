package com.items.controller;

import com.items.domain.Item;
import com.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(
            method = RequestMethod.POST,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Item save(@RequestBody Item item) {
        return itemService.save(item);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public List get() {
        return itemService.get();
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Item getById(@PathVariable String id) {
        return itemService.getById(id);
    }


    @RequestMapping(
            value = "getByGroupId/{groupId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public List getByGroupId(@PathVariable String groupId) {
        return itemService.getByGroupId(groupId);
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public void update(@PathVariable String id, @RequestBody Item item) {
        itemService.save(item);
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE
    )
    public String delete(@PathVariable String id) {
        return itemService.delete(id);
    }
}
