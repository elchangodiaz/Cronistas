package com.jmv.cronistas.img.service;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.jmv.cronistas.img.dto.ImageDTO;

public interface IImageService {

	List<ImageDTO> listPublications();
	Object getImageById(String id) throws IOException;
	Object createImage(MultipartFile file);
	ImageDTO editImage(String id, ImageDTO imageDto);
	ImageDTO deleteImage(String id);
}
