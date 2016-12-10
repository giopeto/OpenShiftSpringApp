package com.groups.service;

import com.groups.domain.Group;
import com.groups.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;

    @Autowired
    public GroupServiceImpl(GroupRepository repository) {
        this.repository = repository;
    }

    public Group save(Group g) {
        return repository.save(g);
    }

    public List<Group> get() {
        return repository.findAllByOrderByNameAsc();
    }

    public Group getById(String id) {
        return repository.findById(id);
    }

    public String delete(String id) {
        return repository.deleteById(id);
    }

}
