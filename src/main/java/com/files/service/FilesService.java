package com.files.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public interface FilesService {
    public Map store(MultipartFile f);

    GridFSDBFile getById(String id);
}
