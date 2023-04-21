package com.example.demo.controller;

import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileUploadController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public void handleFileUpload(@RequestBody MultipartFile file) {
        storageService.store(file);
    }

    @GetMapping("/download")
    public Resource handleFileDownload(@RequestParam("filename") String filename) throws Exception {
        return storageService.loadAsResource(filename);
    }

}
