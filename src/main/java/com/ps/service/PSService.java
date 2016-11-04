package com.ps.service;

import com.ps.domain.PS;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PSService {

	public PS save(PS ps);
	public List<PS> get();
	public PS getById(String id);
	public List<PS> getByUserId(String id);
	public void delete(Long id);

	public PS createEmptyPS(String userId);

}
