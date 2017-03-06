package com.files.service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    GridFsOperations operations;

    @Autowired
    GridFsTemplate gridFsTemplate;

    public Map store(MultipartFile f) {

        InputStream inputStream = null;
        try {
            inputStream = f.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DBObject metaData = new BasicDBObject();
        //metaData.put("type", "item");
        Map<String, String> myMap = new HashMap<String, String>();
        myMap.put("id", gridFsTemplate.store(inputStream, f.getOriginalFilename(), f.getContentType(), metaData).getId().toString());
        return myMap;
    }

    public GridFSDBFile getById(String id) {
        GridFSDBFile gridFile = gridFsTemplate.findOne(new Query(GridFsCriteria.where("_id").is(id)));
        return gridFile;
    }

    @Override
    public void delete(String id) {
        gridFsTemplate.delete(new Query(GridFsCriteria.where("_id").is(id)));
    }

    @Override
    public void delete(List<String> ids) {
        System.out.println("FilesServiceImpl delete" + ids.toString());
        gridFsTemplate.delete(new Query(GridFsCriteria.where("_id").is(ids)));
    }

}
