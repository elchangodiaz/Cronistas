package com.jmv.cronistas.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseConfig {

	@PostConstruct
	private void iniFirestore() throws IOException {
		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("cronistastopilejo-d617b-firebase-adminsdk-qpzh1-a058735a37.json");
		
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://cronistastopilejo-d617b.firebaseio.com")
				.build();
        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }
	}
	
	public Firestore getFirestore() {
		return FirestoreClient.getFirestore();
	}
	
}
