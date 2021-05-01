package com.jmv.cronistas.img.repository;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFirestoreConfig {
	
	String uploadFile(File file, String fileName) throws IOException;
	File convertToFile(MultipartFile multipartFile, String fileName) throws IOException;
	String getExtension(String fileName);
	Object downloadFile(String id, String destFilePath) throws IOException;
}
