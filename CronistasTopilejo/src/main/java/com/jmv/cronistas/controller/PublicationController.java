package com.jmv.cronistas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jmv.cronistas.dto.PublicationDTO;
import com.jmv.cronistas.service.IPublicationService;
import com.jmv.cronistas.service.implementation.PublicationServiceImplementation;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/blog")
public class PublicationController {
	
	@Autowired
	private IPublicationService publicationService;
	
    @GetMapping(value = "/title/{title}")
    public ResponseEntity getByTitle(@PathVariable(value = "title") String title){
    	return  new ResponseEntity(publicationService.getPublication(title), HttpStatus.FOUND);
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") String id){
    	return  new ResponseEntity(publicationService.getPublicationById(id), HttpStatus.FOUND);
    }
	
	@GetMapping
	public List<PublicationDTO> getPublicationList() {
		return publicationService.listPublications();
	}
	
    @PostMapping(value = "/add")
    public ResponseEntity add(@RequestBody PublicationDTO publicationDto){
        return new ResponseEntity(publicationService.createPublication(publicationDto), HttpStatus.CREATED);	
    }
	
    @PutMapping(value = "/{id}/update")
    public ResponseEntity edit(@PathVariable(value = "id") String id, @RequestBody PublicationDTO publicationDTO){
        return new ResponseEntity(publicationService.editPublication(id,publicationDTO), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity delete(@PathVariable(value = "id") String id){
        return  new ResponseEntity(publicationService.deletePublication(id), HttpStatus.OK);
    }
    
}
