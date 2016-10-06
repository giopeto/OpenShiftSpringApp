package com.groups.service;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.groups.domain.Group;
import com.groups.repository.GroupRepository;
import org.springframework.stereotype.Service;

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
		System.out.println(repository);
		return repository.findAll();
	}


	public Group getById(String id) {
		return repository.findById(id);
	}

	public String delete(String id) {return repository.deleteById(id);
		 }

}
