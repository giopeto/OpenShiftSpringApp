package com.files.controller;

import com.files.service.FilesService;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/files")
public class FilesController {
    @Autowired
    FilesService fs;

    @RequestMapping(
            method = RequestMethod.POST,
            headers = "Accept=application/json",
            produces = "application/json"
    )
    public Map<String, String> save(@RequestParam("file") MultipartFile f) {
        MultipartFile file = f;
        return fs.store(f);
    }

    @RequestMapping(
            value = "{fileId}",
            method = RequestMethod.GET,
            headers = "Accept=application/json"
    )
    public ResponseEntity<InputStreamResource> getById(@PathVariable String fileId) {

        System.out.println("FILE ID:::::::::::: " + fileId);

        if(fileId!=null){
            GridFSDBFile gridFsFile = fs.getById(fileId);
            return ResponseEntity.ok()
                    .contentLength(gridFsFile.getLength())
                    .contentType(MediaType.parseMediaType(gridFsFile.getContentType()))
                    .body(new InputStreamResource(gridFsFile.getInputStream()));
        }
        return null;
    }

}
