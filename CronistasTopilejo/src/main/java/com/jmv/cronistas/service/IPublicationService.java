package com.jmv.cronistas.service;

import java.util.List;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QuerySnapshot;
import com.jmv.cronistas.dto.PublicationDTO;

public interface IPublicationService {
	
		List<PublicationDTO> listPublications();
		PublicationDTO getPublicationById(String id);
		PublicationDTO getPublication(String title);
		PublicationDTO createPublication(PublicationDTO publicationDto);
		PublicationDTO editPublication(String id, PublicationDTO publicationDto);
		PublicationDTO deletePublication(String id);
	
}
