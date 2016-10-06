package com.groups.service;

import com.groups.domain.Group;
import java.util.List;

public interface GroupService {

	public Group save(Group g);
	public List get();
	public Group getById(String id);
	public String delete(String id);

}
