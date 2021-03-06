package com.ps.service;


import com.ps.domain.PS;
import com.ps.repository.PSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PSServiceImpl implements PSService {

    private final PSRepository repository;

    @Autowired
    public PSServiceImpl(PSRepository repository) {
        this.repository = repository;
    }

    public PS save(PS ps) {
        repository.save(ps);
        return ps;
    }

    public List<PS> get() {
        return repository.findAll();
    }


    public List<PS> getByUserId(String id) {
        return repository.getByUserId(id);
    }


    public PS getById(String id) {
        return repository.getById(id);
    }


    public void delete(Long id) {

    }


    public PS createEmptyPS(String userId) {
        PS ps = new PS();
        ps.setStatus("tmp");
        ps.setDate(new Date());
        ps.setUserId(userId);
        repository.save(ps);
        return ps;
    }

}
