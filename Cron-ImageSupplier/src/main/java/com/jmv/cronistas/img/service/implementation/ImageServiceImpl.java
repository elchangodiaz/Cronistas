package com.jmv.cronistas.img.service.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.jmv.cronistas.img.dto.ImageDTO;
import com.jmv.cronistas.img.repository.FirebaseConfig;
import com.jmv.cronistas.img.repository.FirestoreConfig;
import com.jmv.cronistas.img.repository.IFirestoreConfig;
import com.jmv.cronistas.img.service.IImageService;

@Service
public class ImageServiceImpl implements IImageService {
	private static Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

	@Autowired
	private FirebaseConfig firebaseConfig;
	
	@Autowired
	private IFirestoreConfig firestoreConfig;
	
 //   @Value("${upload.path}")
 //   private String uploadPath;
	
	@Override
	public List<ImageDTO> listPublications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getImageById(String filename)  throws IOException{
        String destFileName = UUID.randomUUID().toString().concat(firestoreConfig.getExtension(filename));     // to set random strinh for destination file name
        String destFilePath = "F:\\Descargas\\Cronistas\\" + destFileName;                                    // to set destination file path
        
        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
		
		/*
		 * Credentials credentials = firebaseConfig.getCredentials(); Storage storage =
		 * StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		 * Blob blob = storage.get(BlobId.of("cronistastopilejo-d617b.appspot.com",
		 * filename)); blob.downloadTo(Paths.get(destFilePath));
		 */
		 
        firestoreConfig.downloadFile(filename, destFilePath);
        return ResponseEntity.ok().body("200 Successfully Downloaded!");
	}

	@Override
	public Object createImage(MultipartFile multipartFile){
		log.debug(multipartFile.getName());
		String TEMP_URL;

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(firestoreConfig.getExtension(fileName));  // to generated random string values for file name. 

            File file = firestoreConfig.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            TEMP_URL = firestoreConfig.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return ResponseEntity.ok().body("Successfully Uploaded ! " +  TEMP_URL);                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body("500" + e  + " Unsuccessfully Uploaded!");
        }
		
		
	/*	try {
            Path root = Paths.get(uploadPath);
            Path resolve = root.resolve(file.getOriginalFilename());
            if (resolve.toFile()
                       .exists()) {
                throw new FileUploadException("File already exists: " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), resolve);
        } catch (Exception e) {
            throw new FileUploadException("Could not store the file. Error: " + e.getMessage());
            
        }*/
	}

	@Override
	public ImageDTO editImage(String id, ImageDTO imageDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageDTO deleteImage(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/*
	 * public Object uploadImage(MultipartFile multipartFile) { try { String
	 * fileName =multipartFile.getName(); fileName =
	 * UUID.randomUUID().toString().concat(this.getExtension(fileName)); File file =
	 * this.convertToFile(((ImageDTO) multipartFile).getFile(), fileName); String
	 * TEMP_URL = this.uploadFile(file, fileName); file.delete(); return
	 * ResponseEntity.ok("Successfully Uploaded !" + TEMP_URL); // Your customized
	 * response }catch(Exception e) { e.printStackTrace(); return
	 * ResponseEntity.badRequest(); } }
	 */
	
	

}
