package com.jmv.cronistas.img.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


@Service
public class FirestoreConfig implements IFirestoreConfig{
	
	@Autowired
	FirebaseConfig firebaseConfig;
	public String uploadFile(File file, String fileName) throws IOException{
		BlobId blobId = BlobId.of("cronistastopilejo-d617b.appspot.com", fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
				.setContentType("media")
				.build();
		System.out.println(blobInfo);
		Storage storage = StorageOptions.newBuilder().setCredentials(firebaseConfig.getCredentials()).build().getService();
		storage .create(blobInfo, Files.readAllBytes(file.toPath()));
		return String.format("https://firebasestorage.googleapis.com/v0/b/cronistastopilejo-d617b.appspot.com/o//%s?alt=media", 
											URLEncoder.encode(fileName, StandardCharsets.UTF_8));
	}
	
	public Object downloadFile(String filename) throws IOException {
        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = firebaseConfig.getCredentials();
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("cronistastopilejo-d617b.appspot.com", filename));
        String URL =  "https://firebasestorage.googleapis.com/v0/b/" +blob.getBucket().toString() + "/o/" 
        		+ blob.getName().toString() + "?alt=media&token=" + blob.getMetadata().toString().replace("{firebaseStorageDownloadTokens=", "");
        return URL;
	}
	
	
	public File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

	public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
	
}