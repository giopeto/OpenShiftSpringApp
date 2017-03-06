package com.files.service;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public interface FilesService {
    Map store(MultipartFile f);
    GridFSDBFile getById(String id);
    void delete(String id);


    void delete(List<String> ids);
}
