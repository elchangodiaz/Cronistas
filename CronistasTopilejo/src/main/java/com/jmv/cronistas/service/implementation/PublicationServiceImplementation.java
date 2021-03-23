package com.jmv.cronistas.service.implementation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.print.Doc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.jmv.cronistas.dto.PublicationDTO;
import com.jmv.cronistas.repository.FirebaseConfig;
import com.jmv.cronistas.service.IPublicationService;

@Service
public class PublicationServiceImplementation implements IPublicationService {
	private static Logger log = LoggerFactory.getLogger(PublicationServiceImplementation.class);
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	List<String> strings = new ArrayList<>();

	@Autowired
	private FirebaseConfig firebaseConfig;

	@Override
	public List<PublicationDTO> listPublications() {
		List<PublicationDTO> publications = new ArrayList<>();
		PublicationDTO publicationDTO;
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getCollection().get();

		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				publicationDTO = doc.toObject(PublicationDTO.class);
				publicationDTO.setIdPublication(doc.getId());
				if(publicationDTO.getStatus().equals("C") || publicationDTO.getStatus().equals("M")) {
					publications.add(publicationDTO);
				}
			}
			return publications;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PublicationDTO getPublicationById(String id) {
		PublicationDTO publicationDTO;
		ApiFuture<DocumentSnapshot> docSnapshot = getById(id).get();
		try {
			DocumentSnapshot doc = docSnapshot.get();
			publicationDTO = doc.toObject(PublicationDTO.class);
			publicationDTO.setIdPublication(doc.getId());
			return publicationDTO;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PublicationDTO getPublication(String title) {
		PublicationDTO publicationDTO = new PublicationDTO();
		ApiFuture<QuerySnapshot> querySnapshotApiFuture = getByTitle(title);
		try {
			for (DocumentSnapshot doc : querySnapshotApiFuture.get().getDocuments()) {
				publicationDTO = doc.toObject(PublicationDTO.class);
				publicationDTO.setIdPublication(doc.getId());
			}
			return publicationDTO;
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public PublicationDTO createPublication(PublicationDTO publicationDto) {
		String oldTitle = null;
		oldTitle = getPublication(publicationDto.getTitle()).getTitle();
		log.info(oldTitle);
		if (oldTitle!=null && oldTitle.equals(publicationDto.getTitle()) ) {
			return null;
		}else {
			publicationDto.setPostDate(dtf.format(now));
			publicationDto.setStatus("C");
			Map<String, Object> docData = getDocData(publicationDto);
			ApiFuture<WriteResult> writeResultApiFuture = getCollection().document().create(docData);

			try {
				if (writeResultApiFuture.get() != null) {
					return publicationDto;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public PublicationDTO editPublication(String id, PublicationDTO publicationDto) {
		PublicationDTO oldPublication = getPublicationById(id);
		publicationDto.setIdPublication(id);

		if (publicationDto.getTitle() != null) {
			publicationDto.setTitle(publicationDto.getTitle());
		} else {
			publicationDto.setTitle(oldPublication.getTitle());
		}
		if (publicationDto.getContent() != null || publicationDto.getContent() != "") {
			publicationDto.setContent(publicationDto.getContent());
		} else {
			publicationDto.setContent(oldPublication.getContent());
		}
		if (publicationDto.getImage().isEmpty()) {
			publicationDto.setImage(publicationDto.getImage());
		} else {
			publicationDto.setImage(oldPublication.getImage());
		}

		publicationDto.setPostDate(oldPublication.getPostDate());
		List<String> mods = new ArrayList<>();
		if (oldPublication.getModifications() != null) {
			mods.addAll(oldPublication.getModifications());
		}
		mods.add(oldPublication.getContent());
		publicationDto.setStatus("M");
		publicationDto.setModifications(mods);

		Map<String, Object> docData = getDocData(publicationDto);
		ApiFuture<WriteResult> writeResultApiFuture = getCollection().document(id).set(docData);
		try {
			if (writeResultApiFuture.get() != null) {
				return publicationDto;
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return publicationDto;
	}

	@Override
	public PublicationDTO deletePublication(String id) {
		PublicationDTO oldPublication = getPublicationById(id);
		oldPublication.setStatus("D");
		Map<String, Object> docData = getDocData(oldPublication);
		getCollection().document(id).set(docData);
		return oldPublication;
	}

	private CollectionReference getCollection() {
		return firebaseConfig.getFirestore().collection("publication");
	}

	private ApiFuture<QuerySnapshot> getByTitle(String title) {
		return getCollection().whereEqualTo("title", title).get();
	}

	private DocumentReference getById(String id) {
		return getCollection().document(id);
	}

	private Map<String, Object> getDocData(PublicationDTO publication) {
		Map<String, Object> docData = new HashMap<>();
		docData.put("title", publication.getTitle());
		docData.put("content", publication.getContent());
		docData.put("postDate", publication.getPostDate());
		docData.put("modifications", publication.getModifications());
		docData.put("status", publication.getStatus());
		docData.put("image", publication.getImage());
		return docData;
	}

}
