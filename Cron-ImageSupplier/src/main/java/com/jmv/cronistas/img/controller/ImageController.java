package com.jmv.cronistas.img.controller;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jmv.cronistas.CronImageSupplierApplication;
import com.jmv.cronistas.img.service.IImageService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/img")
public class ImageController {
    private final static Logger logger = LoggerFactory.getLogger(CronImageSupplierApplication.class);

    @Autowired
    IImageService ImageServiceImpl;
	
    @PostMapping(value = "/add")
    public Object createImage(@RequestParam("file") MultipartFile multipartFile) {
        logger.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return ImageServiceImpl.createImage(multipartFile);
    }
    
    @PostMapping("/blog/pic/{fileName}")
    public Object getImageById(@PathVariable String fileName) throws IOException {
        logger.info("HIT -/download | File Name : {}", fileName);
        return ImageServiceImpl.getImageById(fileName);
    }
	
}
