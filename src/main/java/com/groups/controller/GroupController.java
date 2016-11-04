package com.groups.controller;

import com.groups.domain.Group;
import com.groups.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupsService;

    @RequestMapping(
            method = RequestMethod.POST,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Group save(@RequestBody Group group) {
        return groupsService.save(group);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public List get() {
        return groupsService.get();
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.GET,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Group getById(@PathVariable String id) {
        return groupsService.getById(id);
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.PUT,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public void update(@PathVariable String id, @RequestBody Group group) {
        groupsService.save(group);
    }


    @RequestMapping(
            value = "{id}",
            method = RequestMethod.DELETE
    )
    public String delete(@PathVariable String id) {
        return groupsService.delete(id);
    }


}

